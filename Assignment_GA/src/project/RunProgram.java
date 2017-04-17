package project;
import java.util.Arrays;
public class RunProgram {

//	public final static int [] targetChromosome = {0,1,0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,1,1,0,1,0,0,1,1,0,1,1,0,1,0,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,1,0,1,1,0,1,0,1,1,0,1};
//	public final static int [] targetChromosome = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	public final static int [] targetChromosome = {1,0,1,0,1,0,1};
	
	public static final int popSize = 15;

	//	public static int fitnessArray[] = new int [popSize];

	//	public static Chromosomes myChrom = new Chromosomes(popSize);

	public static void main(String[] args) {
		Population myPop = new Population(popSize);
		GeneticAlgorithm GA = new GeneticAlgorithm();
		int generationCounter = 0;

		myPop.initPopulation();
		myPop.getFitnessArray();

		System.out.println(" ------------- *** Initial Configuration ***  ------------- ");
		printTarget(targetChromosome);
		generatePopulation(myPop);




		System.out.println(" ------------- *** Evolution ***  ------------- ");

		//		generationCounter++;
		//		System.out.println("***-------- Generation --------*** = " + generationCounter);
		//		myPop = GA.evolvePop(myPop);
		//		myPop.getFitnessArray();
		//		generatePopulation(myPop);
		//		
		//		
		//
		//		generationCounter++;
		//		System.out.println("***-------- GeneratiSon --------*** = " + generationCounter);
		//		myPop = GA.evolvePop(myPop);
		//		myPop.getFitnessArray();
		//		generatePopulation(myPop);


		while((targetChromosome.length > myPop.myChrom.getMaxFitness()) /*|| (generationCounter < 5)*/){
			generationCounter++;
			System.out.println("***-------- Generation --------*** = " + generationCounter);
			myPop = GA.evolvePop(myPop);
			myPop.getFitnessArray();
			generatePopulation(myPop);
		}


		printTarget(targetChromosome);
		System.out.println("Solution Found after " + generationCounter + " generations!");


	}

	public static void printTarget(int[] inpTarget){
		System.out.println("Target    = " + Arrays.toString(targetChromosome) + " | Max Fitness  = " + inpTarget.length);
		System.out.println("-----------------------------------------------------");
	}

	// print population 
	public static void generatePopulation (Population inpPop){
		//		Chromosomes tempChrom = new Chromosomes(inpPop.getChrom().length);
		//		myChrom.fitArray = new int [popSize];

		//		System.out.println("fitness array B4 starting to fill out = " + Arrays.toString(inpPop.myChrom.fitnessArray()));
		for (int i = 0; i < inpPop.getChrom().length; i++){
			System.out.println("Chrom # " + i + " = " +
					Arrays.toString(inpPop.getChrom()[i].getGenes()) + 
					" | Fitness = " + inpPop.getChrom()[i].getFitness());
		}

		System.out.println("-----------------------------------------------------");
		System.out.println("Fitness Array = " + Arrays.toString(inpPop.myChrom.fitnessArray()) 
		+ " , sum = " + inpPop.myChrom.sumFitnessVal());
		System.out.println("Maximum Fitness = " + inpPop.myChrom.getMaxFitness() + 
				" @ index = " + 
				Arrays.toString(inpPop.myChrom.getIndxArrMax()) + 
				" , Minimum Fitness = " + inpPop.myChrom.getMinFitness() + 
				" @ index = " + 
				Arrays.toString(inpPop.myChrom.getIndxArrMin()));

		System.out.println("------------------------------------------------------");

		//		for (int i = 0; i<inpPop.myChrom.fitnessArray().length; i++){
		//			System.out.println("Fitness Prob of chrom [" + i + "] = "+ inpPop.myChrom.getFitnessProb(inpPop.myChrom.fitnessArray()[i]));
		//		}
	}
}