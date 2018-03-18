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
 * Implementation of Rule #22 of the APAR rule set: Heating coil and cooling coil valves are modulating simultaneously.
 * @author Georgios D. Kontes
 */
public class Rule22 implements AparInterface {

	private String name = "Rule22";
	private double steadyStateMode = 0;
	private RealMatrix inputs;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EHC),
			robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ECC)});
	private double result;

	/**
	 * Instantiates Rule #22 of the APAR rule set
	 */
	public Rule22() {}

	/**
	 * Instantiates Rule #22 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the normalized [0,1] heating coil valve control signal.
	 * 3) the normalized [0,1] cooling coil valve control signal.
	 */
	public Rule22(RealMatrix inputs) {
		this.inputs = inputs;
	}

	/**
	 * Instantiates Rule #22 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the normalized [0,1] heating coil valve control signal.
	 * 3) the normalized [0,1] cooling coil valve control signal.
	 * @param parameters		The parameters of the rule
	 */
	public Rule22(RealMatrix inputs, RealMatrix parameters) {
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
		double[] uhc =  this.inputs.getColumn(0);
		double[] ucc =  this.inputs.getColumn(1);
		double ehc =  this.parameters.getEntry(0, 0);
		double ecc =  this.parameters.getEntry(1, 0);
		this.result = 0;
		if(this.steadyStateMode == 5){
			for(int ii=0; ii < ucc.length; ii++){
				if(uhc[ii] > ehc && ucc[ii] > ecc){
					this.result = this.result+1;
				}
			}
		}
		return this.result/ucc.length;
	}

	/**
	 * Calculation of Rule #22 of APAR as a static function.
	 * @param steadyStateMode		The steady-state operation mode.
	 * @param ucc					The normalized [0,1] cooling coil valve control signal.
	 * @param uhc					The normalized [0,1] heating coil valve control signal.
	 * @return						The probability of fault.
	 */
	public static double apply(double steadyStateMode, double[] ucc, double[] uhc){
		RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
		RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EHC),
				robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ECC)});
		double ehc = parameters.getEntry(0, 0);
		double ecc = parameters.getEntry(1, 0);
		double result = 0;
		if(steadyStateMode == 5){
			for(int ii=0; ii < ucc.length; ii++){
				if(uhc[ii] > ehc && ucc[ii] > ecc){
					result = result+1;
				}
			}
		}
		return result/ucc.length;
	}
}
