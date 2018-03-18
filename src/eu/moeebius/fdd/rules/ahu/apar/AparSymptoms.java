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
 * Definition of the specific symptoms for each APAR rule
 * @author Georgios D. Kontes
 */
public class AparSymptoms {

	/**
	 * The specific symptoms for each APAR rule
	 * @author Georgios D. Kontes
	 */
	public enum SYMPTOM{
		/**
		 * In heating Mode, supply air temperature is lower compared to mixed air temperature.
		 */
		Rule1Symptom,
		/**
		 * In heating Mode, outdoor air fraction too low or too high.
		 */
		Rule2Symptom,
		/**
		 * In heating Mode, heating coil valve saturated fully open and persistent supply air temperature error exists.
		 */
		Rule3Symptom,
		/**
		 * In heating Mode, heating coil valve saturated fully open, if heating load increases supply air temperature will drift from setpoint.
		 */
		Rule4Symptom,
		/**
		 * In Cooling Mode with Outdoor Air, outside air temperature too warm for cooling with outside air.
		 */
		Rule5Symptom,
		/**
		 * In Cooling Mode with Outdoor Air, supply air temperature is lower compared to the return air temperature.
		 */
		Rule6Symptom,
		/**
		 * In Cooling Mode with Outdoor Air, supply air temperature and mixed air temperature are not nearly the same.
		 */
		Rule7Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, outside air temperature too low for mechanical cooling with 100% outside air.
		 */
		Rule8Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, outside air temperature too high for mechanical cooling with 100% outside air.
		 */
		Rule9Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, outside air temperature and mixed air temperature are not nearly the same.
		 */
		Rule10Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, supply air temperature is higher compared to the mixed air temperature.
		 */
		Rule11Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, supply air temperature is higher compared to the return air temperature.
		 */
		Rule12Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, cooling coil valve saturated fully open and persistent supply air temperature error exists.
		 */
		Rule13Symptom,
		/**
		 * In Cooling Mode with 100% Outdoor Air, cooling coil valve saturated fully open, if cooling load increases supply air temperature will drift from setpoint.
		 */
		Rule14Symptom,
		/**
		 * In Mechanical Cooling Mode with Minimum Outdoor Air, outside air temperature too low for mechanical cooling with minimum outside air.
		 */
		Rule15Symptom,
		/**
		 * In Mechanical Cooling Mode with Minimum Outdoor Air, supply air temperature is higher compared to the mixed air temperature.
		 */
		Rule16Symptom,
		/**
		 * In Mechanical Cooling Mode with Minimum Outdoor Air, supply air temperature is higher compared to the return air temperature.
		 */
		Rule17Symptom,
		/**
		 * In Mechanical Cooling Mode with Minimum Outdoor Air, %outside air too low or too high.
		 */
		Rule18Symptom,
		/**
		 * In Mechanical Cooling Mode with Minimum Outdoor Air, cooling coil valve saturated fully open and persistent supply air temperature error exists.
		 */
		Rule19Symptom,
		/**
		 * In Mechanical Cooling Mode with Minimum Outdoor Air, cooling coil valve saturated fully open, if cooling load increases supply air temperature will drift from setpoint.
		 */
		Rule20Symptom,
		/**
		 * In Unknown Mode, heating coil valve, cooling coil valve and mixing box damper are modulating simultaneously.
		 */
		Rule21Symptom,
		/**
		 * In Unknown Mode, heating coil and cooling coil valves are modulating simultaneously.
		 */
		Rule22Symptom,
		/**
		 * In Unknown Mode, heating coil valve and and mixing box damper are modulating simultaneously.
		 */
		Rule23Symptom,
		/**
		 * In Unknown Mode, cooling coil valve and mixing box damper are modulating simultaneously.
		 */
		Rule24Symptom,
		/**
		 * In All Modes, persistent supply air temperature error exists.
		 */
		Rule25Symptom,
		/**
		 * In All Modes, mixed air temperature should be between return air temperature and outside air temperature (mixed air temperature is too high).
		 */
		Rule26Symptom,
		/**
		 * In All Modes, mixed air temperature should be between return air temperature and outside air temperature (mixed air temperature is too low).
		 */
		Rule27Symptom,
		/**
		 * In All Modes, too many mode switches per hour.
		 */
		Rule28Symptom,
	}
	
	/**
	 * A hash table holding all the parameters
	 */
	private EnumMap<SYMPTOM, String> APARsymptomsText = new EnumMap<SYMPTOM, String>(SYMPTOM.class);
	
	/**
	 * Instantiation of the parameters class
	 */
	public AparSymptoms(){
		this.APARsymptomsText.put(SYMPTOM.Rule1Symptom, "In heating Mode, supply air temperature is lower compared to mixed air temperature.");
		this.APARsymptomsText.put(SYMPTOM.Rule2Symptom, "In heating Mode, outdoor air fraction too low or too high.");
		this.APARsymptomsText.put(SYMPTOM.Rule3Symptom, "In heating Mode, heating coil valve saturated fully open and persistent supply air temperature error exists.");
		this.APARsymptomsText.put(SYMPTOM.Rule4Symptom, "In heating Mode, heating coil valve saturated fully open, if heating load increases supply air temperature will drift from setpoint.");
		this.APARsymptomsText.put(SYMPTOM.Rule5Symptom, "In Cooling Mode with Outdoor Air, outside air temperature too warm for cooling with outside air.");
		this.APARsymptomsText.put(SYMPTOM.Rule6Symptom, "In Cooling Mode with Outdoor Air, supply air temperature is lower compared to the return air temperature.");
		this.APARsymptomsText.put(SYMPTOM.Rule7Symptom, "In Cooling Mode with Outdoor Air, supply air temperature and mixed air temperature are not nearly the same.");
		this.APARsymptomsText.put(SYMPTOM.Rule8Symptom, "In Cooling Mode with 100% Outdoor Air, outside air temperature too low for mechanical cooling with 100% outside air.");
		this.APARsymptomsText.put(SYMPTOM.Rule9Symptom, "In Cooling Mode with 100% Outdoor Air, outside air temperature too high for mechanical cooling with 100% outside air.");
		this.APARsymptomsText.put(SYMPTOM.Rule10Symptom, "In Cooling Mode with 100% Outdoor Air, outside air temperature and mixed air temperature are not nearly the same.");
		this.APARsymptomsText.put(SYMPTOM.Rule11Symptom, "In Cooling Mode with 100% Outdoor Air, supply air temperature is higher compared to the mixed air temperature.");
		this.APARsymptomsText.put(SYMPTOM.Rule12Symptom, "In Cooling Mode with 100% Outdoor Air, supply air temperature is higher compared to the return air temperature.");
		this.APARsymptomsText.put(SYMPTOM.Rule13Symptom, "In Cooling Mode with 100% Outdoor Air, cooling coil valve saturated fully open and persistent supply air temperature error exists.");
		this.APARsymptomsText.put(SYMPTOM.Rule14Symptom, "In Cooling Mode with 100% Outdoor Air, cooling coil valve saturated fully open, if cooling load increases supply air temperature will drift from setpoint.");
		this.APARsymptomsText.put(SYMPTOM.Rule15Symptom, "In Mechanical Cooling Mode with Minimum Outdoor Air, outside air temperature too low for mechanical cooling with minimum outside air.");
		this.APARsymptomsText.put(SYMPTOM.Rule16Symptom, "In Mechanical Cooling Mode with Minimum Outdoor Air, supply air temperature is higher compared to the mixed air temperature.");
		this.APARsymptomsText.put(SYMPTOM.Rule17Symptom, "In Mechanical Cooling Mode with Minimum Outdoor Air, supply air temperature is higher compared to the return air temperature.");
		this.APARsymptomsText.put(SYMPTOM.Rule18Symptom, "In Mechanical Cooling Mode with Minimum Outdoor Air, %outside air too low or too high.");
		this.APARsymptomsText.put(SYMPTOM.Rule19Symptom, "In Mechanical Cooling Mode with Minimum Outdoor Air, cooling coil valve saturated fully open and persistent supply air temperature error exists.");
		this.APARsymptomsText.put(SYMPTOM.Rule20Symptom, "In Mechanical Cooling Mode with Minimum Outdoor Air, cooling coil valve saturated fully open, if cooling load increases supply air temperature will drift from setpoint.");
		this.APARsymptomsText.put(SYMPTOM.Rule21Symptom, "In Unknown Mode, heating coil valve, cooling coil valve and mixing box damper are modulating simultaneously.");
		this.APARsymptomsText.put(SYMPTOM.Rule22Symptom, "In Unknown Mode, heating coil and cooling coil valves are modulating simultaneously.");
		this.APARsymptomsText.put(SYMPTOM.Rule23Symptom, "In Unknown Mode, heating coil valve and and mixing box damper are modulating simultaneously.");
		this.APARsymptomsText.put(SYMPTOM.Rule24Symptom, "In Unknown Mode, cooling coil valve and mixing box damper are modulating simultaneously.");
		this.APARsymptomsText.put(SYMPTOM.Rule25Symptom, "In All Modes, persistent supply air temperature error exists.");
		this.APARsymptomsText.put(SYMPTOM.Rule26Symptom, "In All Modes, mixed air temperature should be between return air temperature and outside air temperature (mixed air temperature is too high).");
		this.APARsymptomsText.put(SYMPTOM.Rule27Symptom, "In All Modes, mixed air temperature should be between return air temperature and outside air temperature (mixed air temperature is too low).");
		this.APARsymptomsText.put(SYMPTOM.Rule28Symptom, "In All Modes, too many mode switches per hour.");
	}

    /**
     * Returns the hash table with the values of the parameters
     * @return	The hash table with the values of the parameters
     */
	public EnumMap<SYMPTOM, String> getSymptoms(){
		return this.APARsymptomsText;
	}
}
