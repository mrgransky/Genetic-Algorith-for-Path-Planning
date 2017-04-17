package project;


public class Population {

	Chromosomes[] chromosome;

	Chromosomes myChrom = new Chromosomes(RunProgram.popSize);
	
	// constructor
	public Population(int length){
		chromosome = new Chromosomes[length];
	}

	// initiaze population
	public Population initPopulation(){
		for(int i = 0; i < getChrom().length; i++){
			chromosome[i] = new Chromosomes(RunProgram.targetChromosome.length).initChrom();
		}
		return this;
	}

	public int [] getFitnessArray(){
//		System.out.println(" getChrom length = " + getChrom().length);
		for (int i = 0; i<getChrom().length; i++){
			myChrom.fitnessArray()[i] = getChrom()[i].getFitness();
		}
		return myChrom.fitnessArray();
	}

	public Chromosomes[] getChrom(){
		return chromosome;
	}

	public void setChrom(int index, Chromosomes inpChrom){
		chromosome[index] = inpChrom;
		//		System.out.println("set chrom # " + index + " = " + inpChrom);
	}


	




	public void saveChrom(int index, Chromosomes chrom){
		getChrom()[index] = chrom;
		//		System.out.println("chrom # " + index + " = " + chrom + " | Fitness = " + chrom.getFitness());
		//		System.out.println("----------------------------------------------------------");
	}

}
