package project;

import java.util.Arrays;

public class Chromosomes {
	//private static boolean isFitnessChanged = true;
	private int fitness = 0;
	private double fitProb = 0;
	private int [] genes;
	public int [] fitArray = new int [RunProgram.popSize];
	public double [] fitProbArr = new double [RunProgram.popSize];
	
	// constructor
	public Chromosomes (int chromNumb){
		genes = new int[chromNumb];
	}
	// initialize chromosome with certain number of genes (0-1)
	public Chromosomes initChrom(){
		for (int i = 0; i < genes.length; i++){
			double rand = Math.random();
			if (rand >= Math.random()){
				genes[i] = (int) Math.round(rand);
			} else {
				genes[i] = (int) Math.floor(rand);
			}
		}
		return this;
	}

	public int[] getGenes(){
		//isFitnessChanged = true;
		return genes;
	}

	public void setGenes(int index, int value){
		genes[index] = value;
	}

	public double getFitnessProb(double inpFitness){
		fitProb = (double) (inpFitness/sumFitnessVal());
		return fitProb;
	}

	public int getFitness(){
		fitness = fitnessCalc();
		return fitness;
	}

	public int getMaxFitness(){
		// calculate the Maximum fitness
		int maxFitness = 0;
		maxFitness = Arrays.stream(fitnessArray()).max().getAsInt();
		return maxFitness;
	}

	public int [] fitnessArray(){
		return fitArray;
	}
	
	public double [] fitnessProbabilityArray(){
		return fitProbArr;
	}
	
	
	public int sumFitnessVal(){
		int sum = 0;
		for(int i : fitnessArray()){
			sum += i;
		}
		return sum;
	}
	public int [] getIndxArrMax(){
		// get the index
		int [] tempArrMax = new int [RunProgram.popSize]; 
		for(int i = 0; i<RunProgram.popSize; i++){tempArrMax[i]=-1;}
		int indxMaxFitness = 0;
		int maxIndxArrayLength = 1;
		for(int k = 1; k<fitnessArray().length; k++){
			int tempNumb = fitnessArray()[k];
			if(tempNumb > fitnessArray()[indxMaxFitness]){
				indxMaxFitness = k;
				//				System.out.println("tempNum > inpArray , k = " + k + " , indMaxFitness = " + indxMaxFitness + " , length = " + maxIndxArrayLength);
				tempArrMax[maxIndxArrayLength - 1] = k;
				//				System.out.println("temp Array = " + Arrays.toString(tempArrMax));	
			} else if (tempNumb == fitnessArray()[indxMaxFitness] && tempNumb == getMaxFitness()){
//				System.out.println("second if");
//				System.out.println("tempNumb = " + tempNumb + " = inpArray ["+indxMaxFitness+"] && maxFitenss = " + inpMaxFit + " , k = " + k);
				maxIndxArrayLength++;
//				System.out.println("max index length = " + maxIndxArrayLength);
				tempArrMax[maxIndxArrayLength - 1] = k;
				//				System.out.println("temp Array = " + Arrays.toString(tempArrMax));	
				//				System.out.println(" another index max = " + k);
			} else if (tempNumb < fitnessArray()[indxMaxFitness] && fitnessArray()[0] == getMaxFitness()){
				//				System.out.println("tempNum < inpArray && array " + indxMaxFitness + " = " + inpMaxFit);
				tempArrMax[0] = 0;
				//				System.out.println("temp arr Max = " + Arrays.toString(tempArrMax));
			} else {
				//				System.out.println("new condition should be added!");
				//				System.exit(0);
			}
		} 
//		System.out.println("Numb of indeces for maximum fitness = " + maxIndxArrayLength);
//		System.out.println("temp Array = " + Arrays.toString(tempArrMax));	
		
		int [] arrIndxMax = new int[maxIndxArrayLength];
		int l = 0;
		for (int q = 0; q<tempArrMax.length; q++){
			if(tempArrMax[q] != -1){
				arrIndxMax[l] = tempArrMax[q];
				l++;
			}
		}
		return arrIndxMax;
	}

	public int getMinFitness(){
		// get the minimum fitness
		int minFitness = 0;
		minFitness = Arrays.stream(fitnessArray()).min().getAsInt();
		return minFitness;
	}

	public int [] getIndxArrMin(){
		int[]arrIndxMin;
		int [] tempArrMin = new int [RunProgram.popSize]; 
		for(int i = 0; i<RunProgram.popSize; i++){tempArrMin[i]=-1;}
		int indxMinFitness = 0;
		int minIndxArrayLength = 1;
		// get the index
		for(int k = 1; k<fitnessArray().length; k++){
			int tempNumb = fitnessArray()[k];
			if(tempNumb < fitnessArray()[indxMinFitness]){
				indxMinFitness = k;
				tempArrMin[minIndxArrayLength - 1] = k;
			} else if (tempNumb == fitnessArray()[indxMinFitness] && tempNumb == getMinFitness()){
				minIndxArrayLength++;
				tempArrMin[minIndxArrayLength - 1] = k;
				//				System.out.println(" another index min = " + k);
			} else if (fitnessArray()[0] == getMinFitness()){
				tempArrMin[0] = 0;
			}
		}
		arrIndxMin = new int[minIndxArrayLength];
		int l = 0;
		for (int q = 0; q<tempArrMin.length; q++){
			if(tempArrMin[q]!=-1){
				arrIndxMin[l] = tempArrMin[q];
				l++;
			}
		}
		//				System.out.println("Array of Max Index = " + Arrays.toString(arrIndx));
		return arrIndxMin;	
	}

	public int fitnessCalc(){
		int chromFitness = 0;
		for (int i = 0; i < genes.length; i++){
			if (genes[i] == RunProgram.targetChromosome[i]){
				chromFitness++;
			}
		}
		return chromFitness;
	}
	
	public String toString(){
		return Arrays.toString(this.genes);
	}
}