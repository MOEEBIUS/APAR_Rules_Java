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


/**
 * Determine the steady-state operational model of the AHU unit, according to APAR. The modes are:
 * Mode #1: Heating;
 * Mode #2: Cooling with Outdoor Air;
 * Mode #3: Mechanical Cooling with 100% Outdoor Air;
 * Mode #4: Mechanical Cooling with Minimum Outdoor Air;
 * Mode #5: Unknown Mode.
 * @author Georgios D. Kontes
 */
public class DetermineSteadyStateOperationMode {
	private double steadyStateMode = 0;
	private DetermineOperationMode detMode = new DetermineOperationMode();

	/**
	 * Instantiates the module that determines the steady-state operational model of the AHU unit according to APAR.
	 */
	public DetermineSteadyStateOperationMode() {}

	/**
	 * Calculation of the steady-state mode of operation of the AHU unit according to APAR.
	 * @param inputs		The inputs used for calculating the steady-state mode. These are: 1) the normalized [0,1] heating coil valve control signal; 
	 * 2) the normalized [0,1] mixing box damper control signal; 3) the normalized [0,1] cooling coil valve control signal.
	 * @return	The operation mode
	 */
	public double apply(RealMatrix inputs){
		double[] uhc = inputs.getColumn(0);
		double[] ud = inputs.getColumn(1);
		double[] ucc = inputs.getColumn(2);
		this.steadyStateMode = this.detMode.get(new Array2DRowRealMatrix(new double[] {uhc[0], ud[0], ucc[0]}));
		for (int ii=1; ii < uhc.length; ii++){
			if(this.steadyStateMode != this.detMode.get(new Array2DRowRealMatrix(new double[] {uhc[ii], ud[ii], ucc[ii]}))){
				this.steadyStateMode = 0;
			}
		}
		return this.steadyStateMode;
	}

	/**
	 * Calculation of the steady-state mode of operation of the AHU unit according to APAR as a static function.
	 * @param uhc		The normalized [0,1] heating coil valve control signal.
	 * @param ud		The normalized [0,1] mixing box damper control signal.
	 * @param ucc   	The normalized [0,1] cooling coil valve control signal.
	 * @return	The operation mode
	 */
	public static double apply(double[] uhc, double[] ud, double[] ucc){
		DetermineOperationMode determineMode = new DetermineOperationMode();
		double ssMode;
		ssMode = determineMode.get(new Array2DRowRealMatrix(new double[] {uhc[0], ud[0], ucc[0]}));
		for (int ii=1; ii < uhc.length; ii++){
			if(ssMode != determineMode.get(new Array2DRowRealMatrix(new double[] {uhc[ii], ud[ii], ucc[ii]}))){
				ssMode = 0;
			}
		}
		return ssMode;
	}
}
