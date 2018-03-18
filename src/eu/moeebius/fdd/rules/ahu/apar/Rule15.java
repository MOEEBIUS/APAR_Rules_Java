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
 * Implementation of Rule #15 of the APAR rule set: Outside air temperature too low for mechanical cooling with minimum outside air.
 * @author Georgios D. Kontes
 */
public class Rule15 implements AparInterface {

	private String name = "Rule15";
	private double steadyStateMode = 0;
	private RealMatrix inputs;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ET)});
	private double result;

	/**
	 * Instantiates Rule #15 of the APAR rule set
	 */
	public Rule15() {}

	/**
	 * Instantiates Rule #15 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the outside air temperature; 
	 * 3) the changeover air temperature for switching between modes 3 and 4.
	 */
	public Rule15(RealMatrix inputs) {
		this.inputs = inputs;
	}

	/**
	 * Instantiates Rule #15 of the APAR rule set
	 * @param inputs		The inputs of the rule. These are: 1) the operation mode; 2) the outside air temperature; 
	 * 3) the changeover air temperature for switching between modes 3 and 4.
	 * @param parameters		The parameters of the rule
	 */
	public Rule15(RealMatrix inputs, RealMatrix parameters) {
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
		double[] toa = this.inputs.getColumn(0);
		double[] tco = this.inputs.getColumn(1);
		double et = this.parameters.getEntry(0, 0);
		this.result = 0;
		if(this.steadyStateMode == 4){
			for(int ii=0; ii < toa.length; ii++){
				if(toa[ii] < tco[ii] - et){
					this.result = this.result+1;	
				}
			}
		}
		return this.result/toa.length;
	}

	/**
	 * Calculation of Rule #15 of APAR as a static function.
	 * @param steadyStateMode		The steady-state operation mode.
	 * @param toa					The outside air temperature.
	 * @param tco					The changeover air temperature for switching between modes 3 and 4.
	 * @return						The probability of fault.
	 */
	public static double apply(double steadyStateMode, double[] toa, double[] tco){
		RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
		RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ET)});
		double et = parameters.getEntry(0, 0);
		double result = 0;
		if(steadyStateMode == 4){
			for(int ii=0; ii < toa.length; ii++){
				if(toa[ii] < tco[ii] - et){
					result = result+1;
				}
			}
		}
		return result/toa.length;
	}

}
