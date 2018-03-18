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
import org.apache.commons.math3.linear.RealMatrix;


/**
 * Defines an interface for function implementations
 * @author Georgios D. Kontes
 */
public interface AparInterface {

    /**
     * Sets the name of the rule
     * @param name	The rule name
     */
	void setName(String name);
	
    /**
     * Returns the name of the rule
     * @return	The rule name
     */
	String getName();
	
	/**
     * Sets the inputs of the rule. These can be input from sensors, control commands or BMS configuration data.
     * @param steadyStateMode	The steady-state operation mode of the AHU unit (@see eu.moeebius.FDD.Rules.AHU.APAR.DetermineSteadyStateOperationMode)
     * @param inputs	The inputs of the rule
     */
	void setInputs(Double steadyStateMode, RealMatrix inputs);
	
    /**
     * Returns the inputs of the rule. These can be input from sensors, control commands or BMS configuration data.
     * @return	The inputs of the rule
     */
	RealMatrix getInputs();
	
	/**
     * Sets the parameters of the rule. These are configuration parameters, tailored to the specific rule (e.g. a tolerance threshold).
     * @param parameters	The parameters of the rule
     */
	void setParameters(RealMatrix parameters);
	
    /**
     * Returns the parameters of the rule. These are configuration parameters, tailored to the specific rule (e.g. a tolerance threshold).
     * @return	The parameters of the rule
     */
	RealMatrix getParameters();

    /**
     * Returns the result of the rule application. This is a real number in [0, 1] reflecting the probability of the specific fault occuring.
     * @return	The result of the rule application
     */
	double apply();
}
