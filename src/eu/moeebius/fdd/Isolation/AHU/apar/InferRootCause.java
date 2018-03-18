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
import org.apache.commons.math3.linear.RealMatrix;

import eu.moeebius.fdd.isolation.ahu.apar.ErrorCodes.ERROR_CODES;
import eu.moeebius.fdd.rules.ahu.apar.AparSymptoms;
import eu.moeebius.fdd.rules.ahu.apar.AparSymptoms.SYMPTOM;

/**
 * Diagnosis of Root Cause based on APAR symptoms
 * @author Georgios D. Kontes
 */
public class InferRootCause {

	private double faultThreshold = 0.7;
	private RealMatrix APAR_symptoms;
	private ErrorCodes errorCodes = new ErrorCodes();
	private AparSymptoms symptomsText = new AparSymptoms();

	/**
	 * Instantiates the Diagnosis Engine
	 */
	public InferRootCause() { }

	/**
	 * Instantiates the Diagnosis Engine
	 * @param symptoms		The symptoms of faults. These are probabilities of fault for each of the 28 APAR rules.
	 */
	public InferRootCause(RealMatrix symptoms) {
		this.APAR_symptoms = symptoms;
	}

	/**
	 * Instantiates the Diagnosis Engine
	 * @param symptoms		The symptoms of faults. These are probabilities of fault for each of the 28 APAR rules.
	 * @param faultThreshold		The probability threshold above which a symptom is classified as a fault. Default value is 0.7;
	 */
	public InferRootCause(RealMatrix symptoms, double faultThreshold) {
		this.APAR_symptoms = symptoms;
		this.faultThreshold = faultThreshold;
	}

	/**
	 * Set the symptoms vector
	 * @param symptoms		The symptoms of faults. These are probabilities of fault for each of the 28 APAR rules.
	 */
	public void setSymptoms(RealMatrix symptoms) {
		this.APAR_symptoms = symptoms;
	}

	/**
	 * Get the symptoms vector
	 * @return	The symptoms of faults. These are probabilities of fault for each of the 28 APAR rules.
	 */
	public RealMatrix getSymptoms() {
		return this.APAR_symptoms;
	}

	/**
	 * Set the probability threshold for a fault
	 * @param faultThreshold		The probability threshold above which a symptom is classified as a fault. Default value is 0.7;
	 */
	public void setFaultThreshold(double faultThreshold) {
		this.faultThreshold = faultThreshold;
	}

	/**
	 * Get the probability threshold for a fault
	 * @return The probability threshold above which a symptom is classified as a fault. Default value is 0.7;
	 */
	public double getFaultThreshold() {
		return this.faultThreshold;
	}

	/**
	 * Identify the possible root causes of a detected fault and print a summary of the discoveries.
	 */
	public void diagnose() {
		double [] sym = this.APAR_symptoms.getColumn(0);
		for(int ii=0;ii<sym.length;ii++){
			if(sym[ii]>=this.faultThreshold){
				switch (ii) {
				case 0: 
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule1Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedHCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledHCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.HotWaterSupplyTooLow));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemHotWaterCircPump));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 1:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule2Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingMixedBoxDamper));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckMixedBoxDamper));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 2:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule3Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedHCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledHCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.HotWaterSupplyTooLow));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemHotWaterCircPump));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 3:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule4Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedHCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledHCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.HotWaterSupplyTooLow));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemHotWaterCircPump));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 4:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule5Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 5:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule6Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 6: 
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule7Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 7:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule8Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingMixedBoxDamper));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckMixedBoxDamper));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 8: 
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule9Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 9: 
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule10Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingMixedBoxDamper));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckMixedBoxDamper));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 10:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule11Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 11: 
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule12Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 12:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule13Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 13:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule14Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 14:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule15Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 15:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule16Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 16:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule17Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 17:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule18Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingMixedBoxDamper));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckMixedBoxDamper));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 18:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule19Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 19:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule20Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.SupplyAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckCCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.UndersizedCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.FouledCCoil));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChillerWaterTempSupplyTooHigh));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ProblemChilledWaterCircPump));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.LeakingHCoilValve));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.StuckHCoilValve));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 20:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule21Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 21:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule22Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 22:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule23Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 23:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule24Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 24:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule25Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 25:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule26Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 26:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule27Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ReturnAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.MixedAirTempSensorError));
					System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.OutdoorAirTempSensorError));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				case 27:
					System.out.println("Fault: ");
					System.out.println("-----------------------");
					System.out.println(symptomsText.getSymptoms().get(SYMPTOM.Rule28Symptom));
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Pausible cause(s):");
					System.out.println("-----------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println(" ");
					System.out.println(" ");
					break;
				}
			}
		}
	}
}
