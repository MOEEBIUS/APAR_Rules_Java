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
 * Determine the operational model of the AHU unit, according to APAR. The modes are:
 * Mode #1: Heating;
 * Mode #2: Cooling with Outdoor Air;
 * Mode #3: Mechanical Cooling with 100% Outdoor Air;
 * Mode #4: Mechanical Cooling with Minimum Outdoor Air;
 * Mode #5: Unknown Mode.
 * @author Georgios D. Kontes
 */
public class DetermineOperationMode {
	private double mode = 0;
	private RobustFDDparameters robustFDDparameters = new RobustFDDparameters();
	private RealMatrix parameters = new Array2DRowRealMatrix(new double[] {robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.EHC), 
			robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ED), robustFDDparameters.getRobustFDDParameters().get(FDD_PARAMETERS.ECC)});

	/**
	 * Instantiates the module that determines the operational model of the AHU unit according to APAR.
	 */
	public DetermineOperationMode() {}

	/**
	 * Calculation of the mode of operation of the AHU unit according to APAR.
	 * @param inputs		The inputs used for calculating the mode. These are: 1) the normalized [0,1] heating coil valve control signal; 
	 * 2) the normalized [0,1] mixing box damper control signal; 3) the normalized [0,1] cooling coil valve control signal.
	 * @return	The operation mode
	 */
	public double get(RealMatrix inputs){
		double uhc = inputs.getEntry(0, 0);
		double ud = inputs.getEntry(1, 0);
		double ucc = inputs.getEntry(2, 0);
		double ehc = parameters.getEntry(0, 0);
		double ed = parameters.getEntry(1, 0);
		double ecc = parameters.getEntry(2, 0);
		if(uhc > ehc && ud < ed && ucc < ecc){
			this.mode = 1;
		}else if (uhc < ehc && ud > ed && ud < (1 - ed) && ucc < ecc){
			this.mode = 2;
		}else if(uhc < ehc && ud > (1 - ed) && ucc > ecc){
			this.mode = 3;
		}else if(uhc < ehc && ud < ed && ucc > ecc){
			this.mode = 4;
		}else{
			this.mode = 5;
		}
		return this.mode;
	}



}
