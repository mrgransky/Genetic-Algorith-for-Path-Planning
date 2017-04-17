package project;

public class GeneticAlgorithm {
	// low mutation rate => long time to search for optimal solution
	// large mutation rate => too much variation => losing good solution in previous population
	public double mutationRate = .001;
	
	public int tourSize = 5;
	public double crossoverRate = .5;


	Chromosomes chromZero = new Chromosomes(1);

	public Population evolvePop(Population pop2evolve){

		//		pop2evolve.getFitnessArray();
		//		pop2evolve.getFittest();

		Population evolvedPopulation = new Population(pop2evolve.getChrom().length);;
		// initialize the fitness array for the next generations

		// 1. create a population tournament!
		for (int h = 0; h < pop2evolve.getChrom().length; h++){

			// 2. create chromosome mates
			Chromosomes chromosome1 = fittestChrombyTournament(pop2evolve);
			Chromosomes chromosome2 = fittestChrombyTournament(pop2evolve);

			//			System.out.println("mate Chrom # 1 = " + chromosome1 + " | Fitness = " + chromosome1.getFitness());
			//			System.out.println("mate Chrom # 2 = " + chromosome2 + " | Fitness = " + chromosome2.getFitness());
			//			System.out.println("--------------------------------------------------");

			Chromosomes tempChrom = crossoverChrom(chromosome1, chromosome2);

			// 3. apply a crossover to chromosome mates (offspring)
			evolvedPopulation.saveChrom(h, tempChrom);
			//			evolvedPopulation.setChrom(h, crossoverChrom(chromosome1, chromosome2));
		}

		// 4. Mutate population
		for (int f = 0; f < evolvedPopulation.getChrom().length; f++){
			mutateChrom(evolvedPopulation.getChrom()[f]);
		}
		// 5. set as evolved population!
		return evolvedPopulation;
	}

	// Find the fittest chromosome by having a tournament
	public Chromosomes fittestChrombyTournament(Population inpPop){
		
		chromZero = inpPop.getChrom()[inpPop.myChrom.getIndxArrMax()[0]];
		//		chromZero = inpPop.getChrom()[0];


		//		System.out.println("chrom B4 = " + chromZero + " | Fitness = " + chromZero.getFitness());
		//		System.out.println("-----------------------------------------------------------");
		Population tournamentPop = new Population(tourSize);

		// tournament has an specified size and contains random chromosomes which are chosen randomly from original population
		//		System.out.println("Chromosomes in Tournament:");
		for (int x = 0; x < tourSize; x++){
			double randTour = Math.random();
			//			System.out.println("main population chrom # " + (int)(randTour*inpPop.getChrom().length));
			tournamentPop.getChrom()[x] = inpPop.getChrom()[(int)(randTour*inpPop.getChrom().length)];
			//			System.out.println("Tournament Chrom # " + x + " = " + tournamentPop.getChrom()[x] +
			//					" | Fitness = " + tournamentPop.getChrom()[x].getFitness());
			//			System.out.println("------------------------------------------------------------------");
			tournamentPop.saveChrom(x, tournamentPop.getChrom()[x]);
		}
		//		System.out.println("--------------------------------------------------------------------");
		/* by now tournament has all its chromosomes,
		time to choose the one with highest fitness
		all choromosomes fitness (in tournament) should 
		be compared with the maximum fitness of the generation */ 
		Chromosomes fittestChrom = getFittest(tournamentPop);
		//		System.out.println("selected Chrom       = "+fittestChrom + " | Fitness = " + fittestChrom.getFitness());
		//		System.out.println("================================================================");
		return fittestChrom;
	}

	public Chromosomes getFittest(Population inpPop){
		//		System.out.println("fitness array in GA class = " + Arrays.toString(inpPop.getFitnessArray()) 
		//		+ " @ index = " + Arrays.toString(inpPop.myChrom.getIndxArrMax()));
		//		System.out.println("fitness of chrom from main population = " + maxFitnessChrom.getFitness());
		for(int i = 0; i<inpPop.getChrom().length; i++){
			if(inpPop.getChrom()[i].getFitness() >= chromZero.getFitness()){
				//				System.out.println("------------------inside if condition -----------------");
				chromZero = inpPop.getChrom()[i];
			}
		}
		//		System.out.println("chrom AF = " + chromZero+" | Fitness = " + chromZero.getFitness());
		//		System.out.println("--------------------------------------------------");
		return chromZero;
	}


	// crossover for Chromosome
	public Chromosomes crossoverChrom(Chromosomes inpChrom1, Chromosomes inpChrom2){
		// offspring chromosome has the same size as the target chromosome
		Chromosomes offspring = new Chromosomes(inpChrom1.getGenes().length);

		for (int i = 0; i < offspring.getGenes().length;i++){
			double randOffspring = Math.random();
			//			System.out.println("i_offspring [" + i + "] , randOffspring = " + randOffspring);
			if(randOffspring <= crossoverRate){
				//				System.out.println("gene from chrom 1");
				offspring.setGenes(i, inpChrom1.getGenes()[i]);
			} else {
				//				System.out.println("gene from chrom 2");
				offspring.setGenes(i, inpChrom2.getGenes()[i]);
			}
		}
		//		System.out.println("Offspring      = " + offspring + " | Fitness = " + offspring.getFitness());
		//		System.out.println("--------------------------------------------------");
		return offspring;
	}

	// mutation of chromosome
	public Chromosomes mutateChrom(Chromosomes inpChrom){
		for(int k = 0; k < inpChrom.getGenes().length; k++){
			double randMut = Math.random();
			if (randMut <= mutationRate){ // flipping is required: 0 -> 1 & 1 -> 0;
				int tempRand = (int) Math.round(Math.random());
				inpChrom.setGenes(k, tempRand);
			}
		}
		return inpChrom;
	}
}