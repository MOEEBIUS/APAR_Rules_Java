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
import java.util.EnumMap;


/**
 * Definition of generic FDD parameters for the APAR rule set
 * @author Georgios D. Kontes
 */
public class RobustFDDparameters {

	/**
	 * The specific values of the generic FDD parameters for the APAR rule set
	 * @author Georgios D. Kontes
	 */
	public enum FDD_PARAMETERS{
		/**
		 * The temperature rise across the supply fan
		 */
		DTSF,
		/**
		 * The temperature rise across the return fan
		 */
		DTRF,
		/**
		 * The threshold on the minimum temperature difference between the return and outdoor air
		 */
		DTMIN,
		/**
		 * The threshold on the minimum outdoor air fraction (Tma - Tra)/(Toa - Tra)
		 */
		QOA_QSA_MIN,
		/**
		 * The threshold for errors in temperature measurements
		 */
		ET,
		/**
		 * The threshold for errors in airflows
		 */
		EF,
		/**
		 * The threshold for the heating coil valve control signal
		 */
		EHC,
		/**
		 * The threshold for the cooling coil valve control signal
		 */
		ECC,
		/**
		 * The threshold for the mixing box damper control signal
		 */
		ED,
		/**
		 * The maximum number of mode switches per hour.
		 */
		MTMAX,
		/**
		 * The occupancy delay in minutes.
		 */
		OCC_DLY,
		/**
		 * The mode switch delay in minutes.
		 */
		MODE_DLY,
		/**
		 * The rule delay in minutes.
		 */
		RULE_DLY
	}
	
	/**
	 * A hash table holding all the parameters
	 */
	private EnumMap<FDD_PARAMETERS, Double> fdd_parameters = new EnumMap<FDD_PARAMETERS, Double>(FDD_PARAMETERS.class);
	
	/**
	 * Instantiation of the parameters class
	 */
	public RobustFDDparameters(){
		this.fdd_parameters.put(FDD_PARAMETERS.DTSF, 1.1);
		this.fdd_parameters.put(FDD_PARAMETERS.DTRF, 1.1);
		this.fdd_parameters.put(FDD_PARAMETERS.DTMIN, 5.6);
		this.fdd_parameters.put(FDD_PARAMETERS.QOA_QSA_MIN, 0.2);
		this.fdd_parameters.put(FDD_PARAMETERS.ET, 2.0);
		this.fdd_parameters.put(FDD_PARAMETERS.EF, 0.3);
		this.fdd_parameters.put(FDD_PARAMETERS.EHC, 0.02);
		this.fdd_parameters.put(FDD_PARAMETERS.ECC, 0.02);
		this.fdd_parameters.put(FDD_PARAMETERS.ED, 0.02);
		this.fdd_parameters.put(FDD_PARAMETERS.MTMAX, 7.0);
		this.fdd_parameters.put(FDD_PARAMETERS.OCC_DLY, 90.0);
		this.fdd_parameters.put(FDD_PARAMETERS.MODE_DLY, 60.0);
		this.fdd_parameters.put(FDD_PARAMETERS.RULE_DLY, 60.0);
	}

    /**
     * Returns the hash table with the values of the parameters
     * @return	The hash table with the values of the parameters
     */
	public EnumMap<FDD_PARAMETERS, Double> getRobustFDDParameters(){
		return this.fdd_parameters;
	}
}
