/******************************************************************************
 * This project has received funding from the European Union's Horizon 2020 
 * research and innovation programme under grant agreement No 680517 (MOEEBIUS)
 *
 * Copyright 2016 Technische Hochschule Nuernberg Georg Simon Ohm. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or  implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *****************************************************************************/

package eu.moeebius.fdd.rules.ahu.apar;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import eu.moeebius.fdd.rules.ahu.apar.RobustFDDparameters.FDD_PARAMETERS;

/**
 * Implementation of Rule #18 of the APAR rule set: %outside air too low or too high.
 * @author Georgios D. Kontes
 */
public class Rule18 implements AparInterface {

	private String name = "Rule18";
	private double steadyStateMode = 0;
	private RealMatrix inputs;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.DTMIN),
			robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.QOA_QSA_MIN), robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EF)});
	private double result;

	/**
	 * Instantiates Rule #18 of the APAR rule set
	 */
	public Rule18() {}

	/**
	 * Instantiates Rule #18 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the return air temperature; 3) the outdoor air temperature;
	 * 4) the mixed air temperature.
	 */
	public Rule18(RealMatrix inputs) {
		this.inputs = inputs;
	}

	/**
	 * Instantiates Rule #18 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the return air temperature; 3) the outdoor air temperature;
	 * 4) the mixed air temperature.
	 * @param parameters		The parameters of the rule
	 */
	public Rule18(RealMatrix inputs, RealMatrix parameters) {
		this.inputs = inputs;
		this.parameters = parameters;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setInputs(Double steadyStateMode, RealMatrix inputs) {
		this.steadyStateMode = steadyStateMode;
		this.inputs = inputs;		
	}

	@Override
	public RealMatrix getInputs() {
		return this.inputs;
	}

	@Override
	public void setParameters(RealMatrix parameters) {
		this.parameters = parameters;
	}

	@Override
	public RealMatrix getParameters() {
		return this.parameters;
	}

	@Override
	public double apply() {
		double[] tra = this.inputs.getColumn(0);
		double[] toa = this.inputs.getColumn(1);
		double[] tma = this.inputs.getColumn(2);
		double dtmin = this.parameters.getEntry(0, 0);
		double qua_qsa_min = this.parameters.getEntry(1, 0);
		double ef = this.parameters.getEntry(2, 0);
		this.result = 0;
		if(this.steadyStateMode == 4){
			for(int ii=0; ii < tra.length; ii++){
				double Qoa = tma[ii] - tra[ii];
				double Qsa = toa[ii] - tra[ii];
				if(Math.abs(tra[ii] - tma[ii]) >= dtmin){
					if(Math.abs(Qoa/Qsa - qua_qsa_min) > ef){
						this.result = this.result+1;
					}
				}
			}
		}
		return this.result/tra.length;
	}

	/**
	 * Calculation of Rule #18 of APAR as a static function.
	 * @param steadyStateMode		The steady-state operation mode.
	 * @param tra					The return air temperature.
	 * @param toa					The outdoor air temperature.
	 * @param tma					The mixed air temperature.
	 * @return						The probability of fault.
	 */
	public static double apply(double steadyStateMode, double[] tra, double[] toa, double[] tma){
		RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
		RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.DTMIN),
				robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.QOA_QSA_MIN), robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EF)});
		double dtmin = parameters.getEntry(0, 0);
		double qua_qsa_min = parameters.getEntry(1, 0);
		double ef = parameters.getEntry(2, 0);
		double result = 0;
		if(steadyStateMode == 4){
			for(int ii=0; ii < tra.length; ii++){
				double Qoa = tma[ii] - tra[ii];
				double Qsa = toa[ii] - tra[ii];
				if(Math.abs(tra[ii] - tma[ii]) >= dtmin){
					if(Math.abs(Qoa/Qsa - qua_qsa_min) > ef){
						result = result+1;
					}
				}
			}
		}
		return result/tra.length;
	}
}

