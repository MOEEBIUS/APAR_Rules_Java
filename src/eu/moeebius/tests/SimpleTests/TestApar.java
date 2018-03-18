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

package eu.moeebius.tests.simpletests;

import eu.moeebius.fdd.isolation.ahu.apar.InferRootCause;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import eu.moeebius.fdd.isolation.ahu.apar.ErrorCodes;
import eu.moeebius.fdd.isolation.ahu.apar.ErrorCodes.ERROR_CODES;
import eu.moeebius.fdd.rules.ahu.apar.Rule1;

/**
 * Test the APAR rule set!
 * @author Georgios D. Kontes
 */
public class TestApar {

	public static void main(String[] args) {
		
		ErrorCodes errorCodes = new ErrorCodes();
		System.out.println(errorCodes.getErrorCodes().get(ERROR_CODES.ChilledWaterNotAvailavleToSeason));
		
		InferRootCause infer_Root_Cause = new InferRootCause(new Array2DRowRealMatrix(new double[] {0.7, 0.8}));
		infer_Root_Cause.diagnose();
		
		RealMatrix inputs = new Array2DRowRealMatrix(new double[][] {{-100,100,100},{100,4,5},{100,1,1}});
		Rule1 rule1 = new Rule1(inputs);
		rule1.setInputs(1.0, inputs);
		System.out.println("Rule #1 says: " + rule1.apply());
		System.out.println("Rule #1 says: " + rule1.getInputs());
		
		double[] heatingValve = new double [] {1,2,3,4,5,6,7,8,9};
		double[] coolingValve = new double [] {10,20,30,40,50,60,70,80,90};
		double[] temp = new double [] {100,200,300,400,500,600,700,800,900};
		
		RealMatrix test = new Array2DRowRealMatrix(9, 3);
		test.setColumn(0, heatingValve);
		test.setColumn(1, coolingValve);
		test.setColumn(2, temp);
		
		System.out.println("Table " + test);

		double aa;
		aa = Rule1.apply(1.0, inputs.getColumn(0), inputs.getColumn(1));
		System.out.println("Rule #1 as static execution says says: " + aa);
		
	}
}
