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

package eu.moeebius.fdd.isolation.ahu.apar;
import java.util.EnumMap;


/**
 * Definition of specific faults inferred from APAR rule activations/symptoms
 * @author Georgios D. Kontes
 */
public class ErrorCodes {

	/**
	 * The specific faults inferred from APAR rule activations/symptoms
	 * @author Georgios D. Kontes
	 */
	public enum ERROR_CODES{
		/**
		 * Supply Air Temperature Sensor Error
		 */
		SupplyAirTempSensorError,
		/**
		 * Return Air Temperature Sensor Error
		 */
		ReturnAirTempSensorError,
		/**
		 * Mixed Air Temperature Sensor Error
		 */
		MixedAirTempSensorError,
		/**
		 * Outdoor Air Temperature Sensor Error
		 */
		OutdoorAirTempSensorError,
		/**
		 * Leaking Cooling Coil Valve
		 */
		LeakingCCoilValve,
		/**
		 * Stuck Cooling Coil Valve
		 */
		StuckCCoilValve,
		/**
		 * Undersized Cooling Coil
		 */
		UndersizedCCoil,
		/**
		 * Fouled Cooling Coil
		 */
		FouledCCoil,
		/**
		 * Chiller Water Temperature Supply too High
		 */
		ChillerWaterTempSupplyTooHigh,
		/**
		 * Problem with Chilled Water Circulation Pump
		 */
		ProblemChilledWaterCircPump,
		/**
		 * Chilled Water not Available to Season
		 */
		ChilledWaterNotAvailavleToSeason,
		/**
		 * Leaking Heating Coil Valve
		 */
		LeakingHCoilValve,
		/**
		 * Stuck Heating Coil Valve
		 */
		StuckHCoilValve,
		/**
		 * Undersized Heating Coil
		 */
		UndersizedHCoil,
		/**
		 * Fouled Heating Coil
		 */
		FouledHCoil,
		/**
		 * Hot Water Supply Temperature Too Low
		 */
		HotWaterSupplyTooLow,
		/**
		 * Problem with Hot Water Circulating Pump
		 */
		ProblemHotWaterCircPump,
		/**
		 * Leaking Mixing Box Damper
		 */
		LeakingMixedBoxDamper,
		/**
		 * Stuck Mixing Box Damper
		 */
		StuckMixedBoxDamper
	}
	
	/**
	 * A hash table holding all the parameters
	 */
	private EnumMap<ERROR_CODES, String> error_codes = new EnumMap<ERROR_CODES, String>(ERROR_CODES.class);
	
	/**
	 * Instantiation of the parameters class
	 */
	public ErrorCodes(){
		this.error_codes.put(ERROR_CODES.SupplyAirTempSensorError, "Supply Air Temperature Sensor Error");
		this.error_codes.put(ERROR_CODES.ReturnAirTempSensorError, "Return Air Temperature Sensor Error");
		this.error_codes.put(ERROR_CODES.MixedAirTempSensorError, "Mixed Air Temperature Sensor Error");
		this.error_codes.put(ERROR_CODES.OutdoorAirTempSensorError, "Outdoor Air Temperature Sensor Error");
		this.error_codes.put(ERROR_CODES.LeakingCCoilValve, "Leaking Cooling Coil Valve");
		this.error_codes.put(ERROR_CODES.StuckCCoilValve, "Stuck Cooling Coil Valve");
		this.error_codes.put(ERROR_CODES.UndersizedCCoil, "Undersized Cooling Coil");
		this.error_codes.put(ERROR_CODES.FouledCCoil, "Fouled Cooling Coil");
		this.error_codes.put(ERROR_CODES.ChillerWaterTempSupplyTooHigh, "Chiller Water Temperature Supply too High");
		this.error_codes.put(ERROR_CODES.ProblemChilledWaterCircPump, "Problem with Chilled Water Circulation Pump");
		this.error_codes.put(ERROR_CODES.ChilledWaterNotAvailavleToSeason, "Chilled Water not Available to Season");
		this.error_codes.put(ERROR_CODES.LeakingHCoilValve, "Leaking Heating Coil Valve");
		this.error_codes.put(ERROR_CODES.StuckHCoilValve, "Stuck Heating Coil Valve");
		this.error_codes.put(ERROR_CODES.UndersizedHCoil, "Undersized Heating Coil");
		this.error_codes.put(ERROR_CODES.FouledHCoil, "Fouled Heating Coil");
		this.error_codes.put(ERROR_CODES.HotWaterSupplyTooLow, "Hot Water Supply Temperature Too Low");
		this.error_codes.put(ERROR_CODES.ProblemHotWaterCircPump, "Problem with Hot Water Circulating Pump");
		this.error_codes.put(ERROR_CODES.LeakingMixedBoxDamper, "Leaking Mixing Box Damper");
		this.error_codes.put(ERROR_CODES.StuckMixedBoxDamper, "Stuck Mixing Box Damper");
	}

    /**
     * Returns the hash table with the values of the parameters
     * @return	The hash table with the values of the parameters
     */
	public EnumMap<ERROR_CODES, String> getErrorCodes(){
		return this.error_codes;
	}
}
