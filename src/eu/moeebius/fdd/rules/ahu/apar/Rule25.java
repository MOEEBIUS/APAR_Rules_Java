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
 * Implementation of Rule #25 of the APAR rule set: Persistent supply air temperature error exists.
 * @author Georgios D. Kontes
 */
public class Rule25 implements AparInterface {

	private String name = "Rule25";
	private double steadyStateMode = 0;
	private RealMatrix inputs;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ET)});
	private double result;

	/**
	 * Instantiates Rule #25 of the APAR rule set
	 */
	public Rule25() {}

	/**
	 * Instantiates Rule #25 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the supply air temperature; 2) the supply air temperature setpoint.
	 */
	public Rule25(RealMatrix inputs) {
		this.inputs = inputs;
	}

	/**
	 * Instantiates Rule #25 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the supply air temperature; 2) the supply air temperature setpoint.
	 * @param parameters		The parameters of the rule
	 */
	public Rule25(RealMatrix inputs, RealMatrix parameters) {
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
		double[] tsa = this.inputs.getColumn(0);
		double[] tsas = this.inputs.getColumn(1);
		double et = this.parameters.getEntry(0, 0);
		this.result = 0;
		if(this.steadyStateMode > 0){
			for(int ii=0; ii < tsa.length; ii++){
				if(Math.abs(tsa[ii] - tsas[ii]) > et){
					this.result = this.result+1;	
				}
			}
		}
		return this.result/tsa.length;
	}

	/**
	 * Calculation of Rule #25 of APAR as a static function.
	 * @param steadyStateMode		The steady-state operation mode.
	 * @param tsa					The supply air temperature.
	 * @param tsas					The supply air temperature setpoint.
	 * @return						The probability of fault.
	 */
	public static double apply(double steadyStateMode, double[] tsa, double[] tsas){
		RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
		RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ET)});
		double et = parameters.getEntry(0, 0);
		double result = 0;
		if(steadyStateMode > 0){
			for(int ii=0; ii < tsa.length; ii++){
				if(Math.abs(tsa[ii] - tsas[ii]) > et){
					result = result+1;
				}
			}
		}
		return result/tsa.length;
	}
}
