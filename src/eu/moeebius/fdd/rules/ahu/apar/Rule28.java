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
 * Implementation of Rule #28 of the APAR rule set: Too many mode switches per hour.
 * @author Georgios D. Kontes
 */
public class Rule28 implements AparInterface {

	private String name = "Rule28";
	@SuppressWarnings("unused")
	private double steadyStateMode = 0;
	private RealMatrix inputs;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.MTMAX)});
	private double result;
	private DetermineOperationMode detMode = new DetermineOperationMode();

	/**
	 * Instantiates Rule #28 of the APAR rule set
	 */
	public Rule28(){}

	/**
	 * Instantiates Rule #28 of the APAR rule set
	 * @param inputs		The inputs used for calculating the steady-state mode. These are: 1) the normalized [0,1] heating coil valve control signal; 
	 * 2) the normalized [0,1] mixing box damper control signal; 3) the normalized [0,1] cooling coil valve control signal.
	 */
	public Rule28(RealMatrix inputs) {
		this.inputs = inputs;
	}

	/**
	 * Instantiates Rule #28 of the APAR rule set
	 * @param inputs		The inputs used for calculating the steady-state mode. These are: 1) the normalized [0,1] heating coil valve control signal; 
	 * 2) the normalized [0,1] mixing box damper control signal; 3) the normalized [0,1] cooling coil valve control signal.
	 * @param parameters		The parameters of the rule
	 */
	public Rule28(RealMatrix inputs, RealMatrix parameters) {
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
		double[] uhc = this.inputs.getColumn(0);
		double[] ud = this.inputs.getColumn(1);
		double[] ucc = this.inputs.getColumn(2);
		double mtmax = this.parameters.getEntry(0, 0);
		double scoreSwitches = 0;
		this.result = 0;
		for (int ii=1; ii < uhc.length; ii++){
			if(this.detMode.get(new Array2DRowRealMatrix(new double[] {uhc[ii], ud[ii], ucc[ii]})) !=
					this.detMode.get(new Array2DRowRealMatrix(new double[] {uhc[ii-1], ud[ii-1], ucc[ii-1]}))){
				scoreSwitches = scoreSwitches+1;
			}
		}
		if(scoreSwitches > mtmax){
			this.result = 1;
		}
		return this.result;
	}

	/**
	 * Calculation of Rule #28 of APAR as a static function.
	 * @param uhc					The normalized [0,1] heating coil valve control signal.
	 * @param ud					The normalized [0,1] mixing box damper control signal.
	 * @param ucc					The normalized [0,1] cooling coil valve control signal.
	 * @return						The probability of fault.
	 */
	public static double apply(double[] uhc, double[] ud, double[] ucc){
		RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
		RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.MTMAX)});
		DetermineOperationMode detMode = new DetermineOperationMode();
		double mtmax = parameters.getEntry(0, 0);
		double scoreSwitches = 0;
		double result = 0;
		for (int ii=1; ii < uhc.length; ii++){
			if(detMode.get(new Array2DRowRealMatrix(new double[] {uhc[ii], ud[ii], ucc[ii]})) !=
					detMode.get(new Array2DRowRealMatrix(new double[] {uhc[ii-1], ud[ii-1], ucc[ii-1]}))){
				scoreSwitches = scoreSwitches+1;
			}
		}
		if(scoreSwitches > mtmax){
			result = 1;
		}
		return result;
	}
}
