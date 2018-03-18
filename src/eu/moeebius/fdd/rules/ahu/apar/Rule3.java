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
 * Implementation of Rule #3 of the APAR rule set: Heating coil valve saturated fully open and persistent supply air temperature error exists.
 * @author Georgios D. Kontes
 */
public class Rule3 implements AparInterface {

	private String name = "Rule3";
	private double steadyStateMode = 0;
	private RealMatrix inputs;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EHC),
			robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ET)});
	private double result;

	/**
	 * Instantiates Rule #3 of the APAR rule set
	 */
	public Rule3() {}

	/**
	 * Instantiates Rule #3 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the normalized [0,1] heating coil valve control signal; 
	 * 3) the supply air temperature setpoint; 4) the supply air temperature.
	 */
	public Rule3(RealMatrix inputs) {
		this.inputs = inputs;
	}

	/**
	 * Instantiates Rule #3 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the normalized [0,1] heating coil valve control signal; 
	 * 3) the supply air temperature setpoint; 4) the supply air temperature.
	 * @param parameters		The parameters of the rule
	 */
	public Rule3(RealMatrix inputs, RealMatrix parameters) {
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
		double[] uhc = inputs.getColumn(0);
		double[] tsas = inputs.getColumn(1);
		double[] tsa = inputs.getColumn(2);
		double ehc = this.parameters.getEntry(0, 0);
		double et = this.parameters.getEntry(1, 0);
		this.result = 0;
		if(this.steadyStateMode == 1){
			for(int ii=0; ii < tsa.length; ii++){
				if(Math.abs(uhc[ii] - 1) <= ehc){
					if(tsas[ii] - tsa[ii] >= et){
						this.result = this.result+1;
					}
				}
			}
		}
		return this.result/tsa.length;
	}

	/**
	 * Calculation of Rule #3 of APAR as a static function.
	 * @param steadyStateMode		The steady-state operation mode.
	 * @param uhc					The normalized [0,1] heating coil valve control signal.
	 * @param tsas   				The supply air temperature setpoint.
	 * @param tsa   				The supply air temperature.
	 * @return						The probability of fault.
	 */
	public static double apply(double steadyStateMode, double[] uhc, double[] tsas, double[] tsa){
		RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
		RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EHC),
				robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ET)});
		double ehc = parameters.getEntry(0, 0);
		double et = parameters.getEntry(1, 0);
		double result = 0;
		if(steadyStateMode == 1){
			for(int ii=0; ii < tsa.length; ii++){
				if(Math.abs(uhc[ii] - 1) <= ehc){
					if(tsas[ii] - tsa[ii] >= et){
						result = result+1;
					}
				}
			}
		}
		return result/tsa.length;
	}
}
