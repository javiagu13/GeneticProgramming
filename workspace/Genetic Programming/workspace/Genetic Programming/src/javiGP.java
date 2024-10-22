import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class






public class javiGP {
	
	//Tree, points, num of techniques
	public class Tuple<X, Y, Z> implements Cloneable  { 
		  public X x; 
		  public Y y; 
		  public Z z;
		  public Object clone() throws CloneNotSupportedException { 
			  return super.clone(); 
			} 
		  
		  public Tuple(X x, Y y, Z z) { 
		    this.x = x; 
		    this.y = y; 
		    this.z = z;
		  } 
		  
		  public X getFirstElement(){
			  return this.x;
		  }
		  
		  public Y getSecondElement(){
			  return this.y;
		  }
		  
		  public Z getThirdElement(){
			  return this.z;
		  }
		  
		  public void setSecondElement(Y val){
			  this.y=val;
		  }
	}
	
	private String initialDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	private String desiredDeckString="";
	
	private ArrayList<String> FinalDeckOrder = new ArrayList<String>(); 
	/*
	cutCurrentDeck(int N){									cut
	slipCutCurrentDeck(int N){								slipcut
	faceUpSlipCutCurrentDeck(int N){						slipcutup
	insertFanCurrentDeck(int Amount, int N){				insertfan
	faceUpInsertFanCurrentDeck(int Amount, int N){			insertfanup
	pealCurrentDeck(int N){									peal
	faceUpPealCurrentDeck(int N){							pealup
	inPartialFaroCurrentDeck(int N){						infaro
	faceUpInPartialFaroCurrentDeck(int N){					infaroup
	outPartialFaroCurrentDeck(int N){						outfaro
	faceUpOutPartialFaroCurrentDeck(int N){					outfaroup
	*/
	
	//SETTINGS
	private int MIN=1; //Minimum number of card
	private int MAX=52; //Maximum number of card
	
	private int POPULATIONSIZE=1000; //Number of Population
	private int CROSSOVERNUMBER=500; //Crossover Rate
	
	private int MUTATION=200; //Number of mutations (technique+number)
	private int MUTATIONUMBER=0; //Number of mutations (technique+number) REVISE CODEEEEEE
	private int MUTATIONOPERATION=0; //Number of mutations (technique+number) REVISE CODEEEEEE
	
	private int NUMPARAMOPTIMIZATION=10; //number of times each trees parameters will be tweaked
	
	private int DEPTH=4; //INITIAL DEPTH OF TREES!! tree size / 2
	private int LENGTHMAXOFTREE=20*2;
	private String DISTANCE="SEQUENCE"; //SEQUENCE, HAMMING, HAMMING-SEQUENCE
	private String TECHNUM_CONSTRAINT="NO";//YES, NO if the number of techniques is going to be a constraint
	private int TECHNUM_PENALIZATION=1;
	
	private String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
	private String[] numbers=new String[MAX-1];
	
	private ArrayList<Tuple<ZuhaitzBitarra<String>, Integer, Integer>> population=new ArrayList<>();
	
	private int randomIntBetween(int min, int max){
		 	Random rand=new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
	}
	
	private ArrayList<String> stringToArrayListDeck(String initialDeck){
		//Initial Deck Building From String
		ArrayList<String> CurrentDeckOrder = new ArrayList<String>(); 
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<initialDeck.length(); i++){
			if((initialDeck.charAt(i)==',')||(initialDeck.charAt(i)==']')){
				String card = sb.toString();
				CurrentDeckOrder.add(card);
				sb = new StringBuilder();
			}
			else if((initialDeck.charAt(i)=='[')||(initialDeck.charAt(i)==' ')){}
			else{
				char character = initialDeck.charAt(i);
				sb.append(character);
			}			
		}
		return CurrentDeckOrder;
	}
	
	private void initializeNumbersArray(){
		for(int i=0; i<this.numbers.length-1;i++){numbers[i]=Integer.toString(i+1);}
	}
	
	private void buildPopulation(){
		for (int i = 0; i<this.POPULATIONSIZE;i++){
			
			ZuhaitzBitarra Individual = createIndividual();
			//System.out.println(Individual.toString());
			//System.out.println("--------------------");
			int deckDistance;
			int numOfTechniques=0;
			if(this.DISTANCE.equals("SEQUENCE")){
				deckDistance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, Individual);
				if(this.TECHNUM_CONSTRAINT.equals("YES")){
					numOfTechniques=Individual.size()/2;
					numOfTechniques=numOfTechniques*this.TECHNUM_PENALIZATION;
				}
			}
			else if(this.DISTANCE.equals("HAMMING")){
				deckDistance=testIndividualWithHammingDistance(this.initialDeckString, this.desiredDeckString, Individual);
				if(this.TECHNUM_CONSTRAINT.equals("YES")){
					numOfTechniques=Individual.size()/2;
					numOfTechniques=numOfTechniques*this.TECHNUM_PENALIZATION;
				}
			}
			else{//HAMMING-SEQUENCE
				deckDistance=testIndividualWithMixedDistanceHammingSequence(this.initialDeckString, this.desiredDeckString, Individual);
				if(this.TECHNUM_CONSTRAINT.equals("YES")){
					numOfTechniques=Individual.size()/2;
					numOfTechniques=numOfTechniques*this.TECHNUM_PENALIZATION;
				}
			}
			Tuple<ZuhaitzBitarra<String>, Integer, Integer> tuple =new Tuple<ZuhaitzBitarra<String>, Integer, Integer>(Individual, deckDistance+numOfTechniques, Individual.size()/2);
			population.add(tuple);	//Hay que sacar el vector distance y añadirselo :)
			numOfTechniques=0;
		}
		
		this.quickSortPopulation(0, this.POPULATIONSIZE-1);
	}
	
	private void crossOverIndividuals(ZuhaitzBitarra<String> firstIndividual, ZuhaitzBitarra<String> secondIndividual){
		int firstTreeCutPos = randomIntBetween(0,firstIndividual.height()); //no se si empieza en 0 ni si termina en height igual es height -1 o algo asi
		int secondTreeCutPos = randomIntBetween(0,secondIndividual.height());
		
	}
	
	private ZuhaitzBitarra<String> createIndividual(){
		//Adding first operator
		String randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
		ZuhaitzBitarra<String> Individual = new ZuhaitzBitarra<String>(randomOperation);
		for(int i = 0; i<this.DEPTH-1;i++){
			//Adding number
	        switch(randomOperation) 
	        { 
	            case "infaro": 
	            	Individual.setLeftandMove(Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10)));
	                break; 
	            case "infaroup": 
	            	Individual.setLeftandMove(Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10))); 
	                break; 
	            case "outfaroup": 
	            	Individual.setLeftandMove(Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10)));
	                break; 
	            case "outfaro": 
	            	Individual.setLeftandMove(Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10)));
	                break; 
	            default: 
	            	Individual.setLeftandMove(Integer.toString(randomIntBetween(this.MIN,this.MAX)));
	        } 
	        //Adding operator
	        randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
	        Individual.setLeftandMove(randomOperation);
		}
		Individual.setLeftandMove(Integer.toString(randomIntBetween(this.MIN,this.MAX)));
		return Individual;
	}
	
	public void mutateDeck(ZuhaitzBitarra<String> tree){
		 String number="";
		 String randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
		 switch(randomOperation) 
	        { 
	            case "infaro": 
	            	number=Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10));
	                break; 
	            case "infaroup": 
	            	number=Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10));
	                break; 
	            case "outfaroup": 
	            	number=Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10));
	                break; 
	            case "outfaro": 
	            	number=Integer.toString(randomIntBetween(this.MIN+9,this.MAX-10));
	                break; 
	            default: 
	            	number=Integer.toString(randomIntBetween(this.MIN,this.MAX));
	        } 
		 //randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
	     tree.mutation(randomOperation,number);
	}
	
	public void mutateDeckNumber(ZuhaitzBitarra<String> tree){
	     String number=Integer.toString(randomIntBetween(this.MIN,this.MAX));
	     tree.mutationOfNumber(number);
	}
	
	public void mutateDeckOperation(ZuhaitzBitarra<String> tree){
		 String randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
	     tree.mutationOfOperation(randomOperation);
	}
	
	public Deck applyIndividualToDeck(ZuhaitzBitarra<String> Individual, Deck deck){
		//System.out.println(Individual.inOrdenList().toString());
		ArrayList<String> operationsList = Individual.inOrdenList();
		for(int i=0;i<operationsList.size()-1;i=i+2){
	        switch(operationsList.get(i)) 
		        { 
		            case "cut": 
		            	deck.cutCurrentDeck(Integer.parseInt(operationsList.get(i+1))); 
		                break; 
		            case "slipcut": 
		            	deck.slipCutCurrentDeck(Integer.parseInt(operationsList.get(i+1))); 
		                break; 
		            case "slipcutup": 
		            	deck.faceUpSlipCutCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break; 
		            case "peal": 
		            	deck.pealCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break; 
		            case "pealup": 
		            	deck.faceUpPealCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break; 
		            case "infaro": 
		            	deck.inPartialFaroCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break; 
		            case "infaroup": 
		            	deck.faceUpInPartialFaroCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break; 
		            case "outfaro": 
		            	deck.outPartialFaroCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break; 
		            case "outfaroup": 
		            	deck.faceUpOutPartialFaroCurrentDeck(Integer.parseInt(operationsList.get(i+1)));
		                break;
		            default: 
		            	System.out.println("ERROR: ENTERED IN DEFAULT STATE");
		        }
		}
		//deck.printCurrentDeck();
		return deck;
	}
	
	/*public void applyPopulationToDeck(String deckInitialOrder, String deckFinalOrder){
		for(int i=0; i<this.population.size();i++){
			Deck deck = new Deck(deckInitialOrder);
			Deck finaldeck = applyIndividualToDeck(population.get(i).getFirstElement(),deck);
			//System.out.println(this.population.get(i).toString());
			//finaldeck.printCurrentDeck();
			if (finaldeck.currentEqualToFinalDeck(deckFinalOrder)){
				finaldeck.printCurrentDeck();
				System.out.println(this.population.get(i).toString());
				System.out.println("SUCCESS!!!");
				break;
			}
		}
	}*/
	
	public int testIndividualWithDistanceVector(String inputDeckOrder, String desiredDeckOrder,ZuhaitzBitarra<String> tree){
		Deck desiredDeck = new Deck(desiredDeckOrder);
		Deck inputDeck = new Deck(inputDeckOrder);
		
		Deck outputDeck = applyIndividualToDeck(tree,inputDeck);
		
		int result=desiredDeck.vectorDistance(outputDeck.getCurrentDeckOrder());
		
		/*System.out.println("DISTANCE VECTOR------------");
		System.out.println("initialDeck");
		desiredDeck.printCurrentDeck();
		System.out.println("finalDeck");
		outputDeck.printCurrentDeck();
		
		System.out.println(result);*/
		return result;
	}
	
	
	public int testIndividualWithHammingDistance(String inputDeckOrder, String desiredDeckOrder,ZuhaitzBitarra<String> tree){
		Deck desiredDeck = new Deck(desiredDeckOrder);
		Deck inputDeck = new Deck(inputDeckOrder);
		
		Deck outputDeck = applyIndividualToDeck(tree,inputDeck);
		
		int result=desiredDeck.hammingDistance(outputDeck.getCurrentDeckOrder());
		
		/*System.out.println("DISTANCE VECTOR------------");
		System.out.println("initialDeck");
		desiredDeck.printCurrentDeck();
		System.out.println("finalDeck");
		outputDeck.printCurrentDeck();
		
		System.out.println(result);*/
		return result;
	}
	
	public int testIndividualWithMixedDistanceHammingSequence(String inputDeckOrder, String desiredDeckOrder,ZuhaitzBitarra<String> tree){
		Deck desiredDeck = new Deck(desiredDeckOrder);
		Deck inputDeck = new Deck(inputDeckOrder);
		
		Deck outputDeck = applyIndividualToDeck(tree,inputDeck);
		
		int result=desiredDeck.MixedDistanceSequenceHamming(outputDeck.getCurrentDeckOrder());
		
		/*System.out.println("DISTANCE VECTOR------------");
		System.out.println("initialDeck");
		desiredDeck.printCurrentDeck();
		System.out.println("finalDeck");
		outputDeck.printCurrentDeck();
		
		System.out.println(result);*/
		return result;
	}
	
	//QUICKSORT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    public void quickSortPopulation(int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            quickSortPopulation(low, pi-1); 
            quickSortPopulation(pi+1, high); 
        } 
    } 
    
    private int partition(int low, int high) 
    { 
        int pivot = this.population.get(high).getSecondElement();  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (this.population.get(j).getSecondElement() > pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                Tuple temp = this.population.get(i); 
                this.population.set(i, this.population.get(j));
                this.population.set(j, temp); 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        Tuple temp = this.population.get(i+1); 
        this.population.set(i+1, this.population.get(high)); 
        this.population.set(high, temp); 
  
        return i+1; 
    } 
    
    public void printPopulation(){
    	System.out.println("");
    	System.out.println("");
    	System.out.println("--------------------PRINTING POPULATION--------------------");
    	System.out.println("-----------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------");
    	for(int i=0; i<this.population.size();i++){
    		System.out.println("------"+i+"º TREE------");
    		Deck deck = new Deck(this.initialDeckString);
    		Deck finaldeck = applyIndividualToDeck(population.get(i).getFirstElement(),deck);
    		finaldeck.printCurrentDeck();
    		this.population.get(i).getFirstElement().aurreordenInprimatu();
    		System.out.println("Distance: "+this.population.get(i).getSecondElement());
    	}
    	System.out.println("------END OF PRINTING POPULATION------");
    	System.out.println(population.get(0).getFirstElement());
    }
    
    public void printPopulation(int number){
    	System.out.println("");
    	System.out.println("");
    	System.out.println("---------------------------------------------------------------------------------------------------");
    	System.out.println("PRINTING POPULATION--------------------------------------------------------------------------------");
    	System.out.println("---------------------------------------------------------------------------------------------------");
    	for(int i=0; i<number;i++){
    		System.out.println("------"+i+"º TREE------");
    		Deck deck = new Deck(this.initialDeckString);
    		Deck finaldeck = applyIndividualToDeck(population.get(i).getFirstElement(),deck);
    		finaldeck.printCurrentDeck();
    		this.population.get(i).getFirstElement().aurreordenInprimatu();
    		System.out.println("Distance: "+this.population.get(i).getSecondElement());
    	}
    	System.out.println("------END OF TRAINING POPULATION!!!------");
    	System.out.println(population.get(0).getFirstElement());
    }
    
    public void evolvePopulation() throws CloneNotSupportedException{
    	//5 steps:
    	//1-we copy the population part that we are going to crossover (the CROSSOVERNUMBER of population)
    	for(int i = 0; i<this.CROSSOVERNUMBER;i++){
    		ZuhaitzBitarra<String> clonedTree = new ZuhaitzBitarra<String>(); 
    		clonedTree.cloneTree(population.get(i).getFirstElement().getRoot());
    		int points=population.get(i).getSecondElement();
            // Cloning the set using clone() method 
            
    		Tuple tuple = new Tuple(clonedTree, points, clonedTree.size()/2);
    		
    		this.population.add(tuple);
    	}

    	//2-we have to crossover trees randomly on the first CROSSOVERNUMBER positions (CROSSOVER/2 is because we are doing two trees at a time)
    	for(int i = 0; i<this.CROSSOVERNUMBER/2;i++){
    		int firstNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		int secondNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		//avoiding same number
    		if(firstNum==secondNum){
    			while (firstNum==secondNum){
    				secondNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    			}
    		}
    		this.population.get(firstNum).getFirstElement().crossOverTrees(this.population.get(secondNum).getFirstElement()); //crossover operation
    		if(this.population.get(firstNum).getFirstElement().size()>this.LENGTHMAXOFTREE){this.population.get(firstNum).getFirstElement().deleteAtTreeSize(this.LENGTHMAXOFTREE);}
    		if(this.population.get(secondNum).getFirstElement().size()>this.LENGTHMAXOFTREE){this.population.get(secondNum).getFirstElement().deleteAtTreeSize(this.LENGTHMAXOFTREE);}

    		
    	}
    	//3-we have to mutate MUTATIONUMBER times on the CROSSOVERNUMBER part
    	for(int i = 0; i<this.MUTATION;i++){
    		int randomNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		this.mutateDeck(this.population.get(randomNum).getFirstElement());
    		}
    	
    	for(int i = 0; i<this.MUTATIONUMBER;i++){
    		int randomNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		this.mutateDeckNumber(this.population.get(randomNum).getFirstElement());
    		}
    	
    	for(int i = 0; i<this.MUTATIONOPERATION;i++){
    		int randomNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		this.mutateDeckOperation(this.population.get(randomNum).getFirstElement());
    		}
    	
    	/*OPTIONAL: PARAMETER OPTIMIZATION*/    	
    	//QUE HAY QUE HACER? 
    	//save current tree and punctuation
    	//in zuhaitza class tweak parameters
    	//if tree is best, save combination and save punctuation.
    	
    	//for every tree in the population
    	for (int i =0; i<this.population.size();i++){
    		ZuhaitzBitarra<String> currenTree = population.get(i).getFirstElement(); //we save the current tree
    		int Distance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, currenTree);//calculate the distance we will try to improve
    		int maxDistancePoints = Distance;//save distance in maxDistancePoints
    		ZuhaitzBitarra<String> bestTree=new ZuhaitzBitarra<String>(); // initialize a variable where we will store the best tree for the current tree among the NUMPARAMOPTIMIZATION number of iterations
    		for(int j = 0; j<this.NUMPARAMOPTIMIZATION; j++){
    			
        		
        		ZuhaitzBitarra<String> paramTweakTree = new ZuhaitzBitarra<String>(); 
        		paramTweakTree.cloneTree(currenTree.getRoot());//we clone the currenTree in order not to lose the current tree
        		paramTweakTree.randomizeParams();//we tweak the parameters in this new copied tree
         		
         		int newDistance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, paramTweakTree);//calculate its distance
         		if(newDistance > maxDistancePoints){//if the newDistance is better than the current maximum
         			maxDistancePoints = newDistance;//we update maximum
         			bestTree.cloneTree(paramTweakTree.getRoot());//and we copy our tree to best Tree
         		}	
    		}
    		if (maxDistancePoints>Distance){//after the NUMPARAMOPTIMIZATION iterations if the maxDistancePoints is higher than the Distance we had at the very beginning
    			Tuple<ZuhaitzBitarra<String>, Integer, Integer> tuple = new Tuple<ZuhaitzBitarra<String>, Integer, Integer>(bestTree, maxDistancePoints, bestTree.size()/2);//we create the tuple with the points and the tree
    			population.set(i, tuple);//and set it on our population
    		}
    		//now we repeat the process all over again with each tree in the population
    	}
    	
    	
    	//Update puntuations
    	for(int i =0; i<CROSSOVERNUMBER-1;i++){
    		ZuhaitzBitarra<String> tree=this.population.get(i).getFirstElement();
    		int deckDistance;
    		int numOfTechniques=0;
    		//int deckDistance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, tree);
    		if(this.DISTANCE.equals("SEQUENCE")){
    			deckDistance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, tree);
    			if(this.TECHNUM_CONSTRAINT.equals("YES")){
					numOfTechniques=tree.size()/2;
					numOfTechniques=numOfTechniques*this.TECHNUM_PENALIZATION;
				}
			}
			else if(this.DISTANCE.equals("HAMMING")){
				deckDistance=testIndividualWithHammingDistance(this.initialDeckString, this.desiredDeckString, tree);
				if(this.TECHNUM_CONSTRAINT.equals("YES")){
					numOfTechniques=tree.size()/2;
					numOfTechniques=numOfTechniques*this.TECHNUM_PENALIZATION;
				}
			}
			else{//HAMMING-SEQUENCE
				deckDistance=testIndividualWithMixedDistanceHammingSequence(this.initialDeckString, this.desiredDeckString, tree);
				if(this.TECHNUM_CONSTRAINT.equals("YES")){
					numOfTechniques=tree.size()/2;
					numOfTechniques=numOfTechniques*this.TECHNUM_PENALIZATION;
				}
			}
    		Tuple<ZuhaitzBitarra<String>, Integer, Integer> tuple =new Tuple<ZuhaitzBitarra<String>, Integer, Integer>(tree, deckDistance-numOfTechniques,tree.size()/2);
    		this.population.set(i, tuple);	
    		numOfTechniques=0;
    	}
    	//4-quicksort everything so that it gets back to normal
    	this.quickSortPopulation(0, this.population.size()-1);
    	//5-delete the last elements of the population (the last CROSSOVERNUMBERS)
    	for (int i=this.population.size()-1; i>this.POPULATIONSIZE; i--)
    		this.population.remove(i);
    	
    }		

    
	public void pointsToCSV(String techniqueName, String expName, ArrayList<Integer> Points1n2,ArrayList<Integer> Points2n2,ArrayList<Integer> Points3n2,ArrayList<Integer> Points1n4,ArrayList<Integer> Points2n4,ArrayList<Integer> Points3n4,ArrayList<Integer> Points1n8,ArrayList<Integer> Points2n8,ArrayList<Integer> Points3n8,ArrayList<Integer> Points1n16,ArrayList<Integer> Points2n16,ArrayList<Integer> Points3n16,ArrayList<Integer> Points1n32,ArrayList<Integer> Points2n32,ArrayList<Integer> Points3n32, ArrayList<Integer> techNum1n2,ArrayList<Integer> techNum2n2,ArrayList<Integer> techNum3n2,ArrayList<Integer> techNum1n4,ArrayList<Integer> techNum2n4,ArrayList<Integer> techNum3n4,ArrayList<Integer> techNum1n8,ArrayList<Integer> techNum2n8,ArrayList<Integer> techNum3n8,ArrayList<Integer> techNum1n16,ArrayList<Integer> techNum2n16,ArrayList<Integer> techNum3n16,ArrayList<Integer> techNum1n32,ArrayList<Integer> techNum2n32,ArrayList<Integer> techNum3n32){
		try{
			FileWriter csvWriter;
			if(this.DISTANCE.equals("SEQUENCE")){
				csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//"+expName+"//SEQUENCE//"+techniqueName+".csv");
				}
			else if(this.DISTANCE.equals("HAMMING")){
				csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//"+expName+"//HAMMING//"+techniqueName+".csv");
				}
			else{//HAMMING-SEQUENCE
				csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//"+expName+"//HAMMING-SEQUENCE//"+techniqueName+".csv");
				}
	 		csvWriter.append(techniqueName + "2");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "4");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "8");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "16");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "32");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "2TechNum");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "4TechNum");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "8TechNum");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "16TechNum");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(techniqueName + "32TechNum");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append(",");
	 		csvWriter.append("\n");
	 		for (int i=0; i<Points1n2.size();i++){
	 			csvWriter.append(Points1n2.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points2n2.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points3n2.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points1n4.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points2n4.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points3n4.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points1n8.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points2n8.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points3n8.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points1n16.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points2n16.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points3n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points1n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points2n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(Points3n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum1n2.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum2n2.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum3n2.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum1n4.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum2n4.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum3n4.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum1n8.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum2n8.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum3n8.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum1n16.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum2n16.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum3n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum1n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum2n32.get(i).toString());
	 			csvWriter.append(",");
	 			csvWriter.append(techNum3n32.get(i).toString());
	 			csvWriter.append("\n");	
		 		}
		 		
		 		csvWriter.flush();
		 		csvWriter.close();
		 	}
		 	catch(Exception e){}
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		
		

		//Mnemonica
		//String Deck4 = "[4c, 2h, 7d, 3c, 4h, 6d, 1s, 5h, 9s, 2s, 12h, 3d, 12c, 8h, 6s, 5s, 9h, 13c, 2d, 11h, 3s, 8s, 6h, 10c, 5d, 13d, 2c, 3h, 8d, 5c, 13s, 11d, 8c, 10s, 13h, 11c, 7s, 10h, 1d, 4s, 7h, 4d, 1c, 9c, 11s, 12d, 7c, 12s, 10d, 6c, 1h, 9d]";
		
		ArrayList<Integer> Points1n2=new ArrayList<>();
		ArrayList<Integer> Points2n2=new ArrayList<>();
		ArrayList<Integer> Points3n2=new ArrayList<>();
		
		ArrayList<Integer> Points1n4=new ArrayList<>();
		ArrayList<Integer> Points2n4=new ArrayList<>();
		ArrayList<Integer> Points3n4=new ArrayList<>();
		
		ArrayList<Integer> Points1n8=new ArrayList<>();
		ArrayList<Integer> Points2n8=new ArrayList<>();
		ArrayList<Integer> Points3n8=new ArrayList<>();
		
		ArrayList<Integer> Points1n16=new ArrayList<>();
		ArrayList<Integer> Points2n16=new ArrayList<>();
		ArrayList<Integer> Points3n16=new ArrayList<>();
		
		ArrayList<Integer> Points1n32=new ArrayList<>();
		ArrayList<Integer> Points2n32=new ArrayList<>();
		ArrayList<Integer> Points3n32=new ArrayList<>();
		
		ArrayList<Integer> techNum1n2=new ArrayList<>();
		ArrayList<Integer> techNum2n2=new ArrayList<>();
		ArrayList<Integer> techNum3n2=new ArrayList<>();
		
		ArrayList<Integer> techNum1n4=new ArrayList<>();
		ArrayList<Integer> techNum2n4=new ArrayList<>();
		ArrayList<Integer> techNum3n4=new ArrayList<>();
		
		ArrayList<Integer> techNum1n8=new ArrayList<>();
		ArrayList<Integer> techNum2n8=new ArrayList<>();
		ArrayList<Integer> techNum3n8=new ArrayList<>();
		
		ArrayList<Integer> techNum1n16=new ArrayList<>();
		ArrayList<Integer> techNum2n16=new ArrayList<>();
		ArrayList<Integer> techNum3n16=new ArrayList<>();
		
		ArrayList<Integer> techNum1n32=new ArrayList<>();
		ArrayList<Integer> techNum2n32=new ArrayList<>();
		ArrayList<Integer> techNum3n32=new ArrayList<>();
		
		for(int w=0; w<21; w++){
			String technique = "";
			String Deck1 = "";
			String Deck2 = "";
			String Deck3 = "";
			String Deck4 = "";
			String Deck5 = "";
	
			if(w==0){
				technique = "CUT";
				//N=2  ( 18 22 )
				Deck1 = "[12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c]";
				//N=4  ( 29 11 24 36 )
				Deck2 = "[4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";
				//N=8  ( 49 37 31 20 37 39 30 17 )
				Deck3 = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=16  ( 51 23 33 52 9 1 7 42 39 51 37 32 24 45 13 4 )
				Deck4 = "[5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c]";
				//N=32  ( 17 15 23 2 4 50 38 41 41 36 34 25 20 5 47 31 12 42 49 6 52 39 24 5 7 7 50 37 23 10 20 19 )
				Deck5 = "[1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c]";
			}
			if(w==1){
				technique = "PEAL";
				//N=2  ( 13 41 )
				Deck1 = "[12c, 13c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=4  ( 19 49 42 49 )
				Deck2 = "[6h, 5h, 4h, 3h, 2h, 1h, 13s, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 3c, 2c, 1c]";
				//N=8  ( 4 42 2 35 43 36 19 49 )
				Deck3 = "[4c, 5c, 6c, 7c, 8c, 9c, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 10c, 4s, 3s, 2s, 1s, 5s, 6s, 7s, 12c, 11c, 13c, 1d, 2d, 3d, 4d, 5d, 6d, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 3c, 2c, 1c]";
				//N=16  ( 11 11 49 28 14 51 49 47 27 16 52 14 49 7 25 48 )
				Deck4 = "[13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 3c, 2c, 6d, 5d, 1c, 11h, 10h, 9h, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 4c, 5c, 6c, 7c, 8c, 9c, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 10c, 12h, 1h, 13s, 12s]";
				//N=32  ( 45 20 14 3 43 28 23 26 23 27 5 52 16 1 26 12 49 3 6 42 6 20 11 14 6 34 38 35 44 19 21 7 )
				Deck5 = "[7s, 12c, 11c, 12d, 11d, 5d, 4d, 8s, 8c, 12h, 11h, 4s, 6s, 10c, 9c, 6d, 7d, 12s, 6c, 5c, 4c, 1d, 2d, 3d, 2s, 1s, 7c, 13d, 13h, 13c, 3s, 11s, 3c, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 10d, 9d, 8d, 1c, 2c, 9s, 10s, 5s]";
			}
			if(w==2){
				technique = "PEALUP";
				//N=2  ( 39 39 )
				Deck1 = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=4  ( 36 43 27 10 )
				Deck2 = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c]";
				//N=8  ( 19 17 2 5 29 14 28 47 )
				Deck3 = "[1s, 2s, 3s, 4s, 5s, 6c, 5c, 3c, 4c, 8c, 9c, 10c, 11c, 12c, 13c, 1d, 2d, 3d, 4d, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 1c, 2c, 6d, 5d, 7c, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s]";
				//N=16  ( 43 30 41 15 20 15 39 50 15 3 1 16 19 10 37 48 )
				Deck4 = "[1s, 2s, 3d, 4d, 11h, 10h, 9h, 8h, 7h, 6h, 13s, 12s, 11s, 10s, 13c, 5h, 4h, 3h, 2h, 1h, 3c, 4c, 8c, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 1c, 5c, 6c, 7c, 11c, 10c, 9c, 12c, 2d, 1d, 2c, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d]";
				//N=32  ( 32 1 35 50 20 9 48 20 12 31 14 16 2 27 50 6 13 32 23 20 30 36 37 43 2 47 51 6 3 42 4 1 )
				Deck5 = "[1s, 2d, 3d, 4d, 5d, 7h, 12h, 11h, 10h, 9h, 1d, 7c, 6d, 13c, 12c, 2s, 6c, 5c, 2c, 1c, 7d, 8d, 9d, 10d, 12s, 13s, 13h, 3s, 4s, 5s, 4c, 3c, 11c, 10c, 9c, 8c, 6s, 7s, 8s, 9s, 10s, 11s, 4h, 3h, 2h, 1h, 13d, 12d, 8h, 5h, 6h, 11d]";
			}
			if(w==3){
				technique = "SLIPCUT";
				//N=2  ( 43 13 )
				Deck1 = "[3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2s, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 1s, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=4  ( 24 40 4 34 )
				Deck2 = "[5s, 6s, 3s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 1s, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4s, 4d, 3d, 2d, 1d, 13c, 2s, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=8  ( 6 48 4 19 29 1 48 32 )
				Deck3 = "[1s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 4s, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 5s, 9d, 8d, 7d, 6d, 3s, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 2s, 6s, 4c, 3c, 2c, 1c]";
				//N=16  ( 49 39 33 40 41 33 14 40 3 29 15 29 38 14 26 33 )
				Deck4 = "[3h, 4h, 5h, 6h, 7h, 7s, 8h, 9h, 10h, 11s, 11h, 13s, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 3s, 1h, 10s, 9s, 4d, 3d, 6s, 2d, 1d, 2h, 13c, 2s, 12c, 4s, 12s, 11c, 8s, 5s, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 1s, 3c, 2c, 1c]";
				//N=32  ( 22 31 10 39 48 41 14 26 34 13 23 48 32 36 1 49 11 41 52 2 7 18 6 18 50 51 25 3 12 35 4 48 )
				Deck5 = "[2h, 10h, 5h, 11h, 12h, 13h, 13d, 12d, 11d, 6h, 11s, 4h, 7s, 10d, 8s, 9d, 2s, 8d, 7d, 6d, 5d, 4d, 8h, 3d, 3s, 9s, 2d, 1d, 4s, 13s, 13c, 12c, 11c, 9h, 6s, 10c, 9c, 3h, 8c, 7c, 6c, 5c, 5s, 12s, 4c, 1h, 3c, 1s, 7h, 2c, 10s, 1c]";
			}
			if(w==4){
				technique = "SLIPCUTUP";
				//N=2  ( 35 3 )
				Deck1 = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 1c, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 2c, 4c, 3c]";
				//N=4  ( 46 12 40 18 )
				Deck2 = "[1s, 2s, 3s, 4s, 5s, 6s, 1c, 7s, 8s, 9s, 10s, 11s, 3c, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 4c, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 2c, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";
				//N=8  ( 22 5 19 8 4 49 47 41 )
				Deck3 = "[1s, 2s, 3s, 6c, 4s, 2c, 5s, 6s, 7s, 8s, 9s, 7c, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 1c, 9d, 8d, 3c, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 4c, 10c, 9c, 8c, 5c]";
				//N=16  ( 47 38 34 51 22 12 39 18 29 12 45 52 10 44 39 52 )
				Deck4 = "[1s, 4c, 2s, 3s, 4s, 5s, 1c, 11c, 13c, 6s, 7s, 8s, 9s, 1d, 10s, 11s, 7c, 12s, 13s, 2c, 1h, 2h, 3h, 3c, 4h, 5h, 9c, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 5c, 13d, 12d, 8c, 11d, 10d, 9d, 8d, 10c, 12c, 7d, 6d, 5d, 6c, 4d, 3d, 2d]";
				//N=32  ( 33 46 51 44 52 15 45 30 22 52 44 49 50 33 1 18 15 30 28 5 41 9 16 6 33 16 5 4 6 1 34 39 )
				Deck5 = "[1s, 3c, 11c, 2s, 10c, 3s, 4s, 5s, 6s, 6c, 9c, 5d, 2c, 12d, 4c, 7s, 8s, 9s, 10s, 7d, 11s, 4d, 12s, 12c, 13s, 1h, 2d, 2h, 3d, 3h, 4h, 5h, 7c, 6h, 1c, 7h, 8h, 9h, 8d, 10h, 5c, 11h, 13c, 8c, 12h, 1d, 13h, 13d, 11d, 6d, 9d, 10d]";
			}
			if(w==5){
				technique = "INFARO";
				//N=2  ( 21 29 )
				Deck1 = "[2h, 9h, 3d, 1s, 3h, 10h, 2d, 2s, 4h, 11h, 1d, 3s, 5h, 12h, 13c, 4s, 6h, 13h, 12c, 5s, 7h, 13d, 11c, 6s, 8h, 12d, 10c, 7s, 9c, 11d, 8c, 8s, 7c, 10d, 6c, 9s, 5c, 9d, 4c, 10s, 3c, 8d, 2c, 11s, 1c, 7d, 12s, 6d, 13s, 5d, 1h, 4d]";
				//N=4  ( 32 33 22 27 )
				Deck2 = "[4d, 12h, 8d, 4h, 8h, 6s, 11c, 7d, 4s, 13h, 10s, 3c, 9h, 1d, 10c, 1s, 3d, 13d, 11s, 5h, 10h, 7s, 9c, 6d, 5s, 12d, 12s, 2c, 11h, 13c, 8c, 2s, 2d, 11d, 13s, 6h, 7c, 8s, 1h, 5d, 6c, 10d, 2h, 1c, 5c, 12c, 3h, 3s, 4c, 9d, 7h, 9s]";
				//N=8  ( 14 41 36 30 33 39 15 36 )
				Deck3 = "[10c, 11c, 3s, 7s, 1c, 9s, 2d, 6d, 11d, 5d, 5c, 9d, 7h, 7d, 1d, 12c, 1s, 3c, 5h, 9h, 6s, 11h, 13c, 12h, 10d, 13h, 4c, 3h, 8h, 2s, 9c, 13s, 12d, 8s, 10s, 7c, 4h, 8d, 6h, 4s, 12s, 10h, 2h, 11s, 4d, 8c, 2c, 13d, 1h, 6c, 5s, 3d]";
				//N=16  ( 35 35 31 17 23 39 27 40 35 30 42 19 40 33 20 14 )
				Deck4 = "[1d, 2c, 3s, 1h, 13d, 11d, 5s, 3h, 4h, 11c, 10s, 13h, 4s, 10c, 6c, 3c, 12s, 7s, 6d, 1c, 5d, 7c, 12c, 3d, 13c, 13s, 6h, 12d, 8s, 4d, 9s, 12h, 4c, 11h, 2s, 7h, 1s, 6s, 9d, 7d, 11s, 8d, 10h, 8h, 10d, 9c, 5h, 2h, 9h, 5c, 2d, 8c]";
				//N=32  ( 20 30 35 14 10 26 21 23 24 41 18 13 16 12 15 41 39 12 34 33 16 33 25 18 33 12 36 13 42 38 33 25 )
				Deck5 = "[11c, 13c, 9c, 6h, 10d, 5s, 8s, 10h, 2c, 11h, 5h, 7d, 4h, 10s, 13d, 13s, 1s, 6c, 8c, 4d, 12h, 1d, 4s, 6s, 6d, 4c, 11s, 8d, 2d, 5d, 13h, 1h, 2s, 3s, 7h, 5c, 10c, 9h, 9s, 12c, 2h, 3h, 3c, 1c, 12d, 7s, 3d, 7c, 12s, 8h, 11d, 9d]";
			}
			if(w==6){
				technique = "INFAROUP";
				//N=2  ( 40 33 )
				Deck1 = "[8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 1s, 11c, 2s, 10c, 3s, 13s, 9c, 1h, 4s, 2h, 8c, 3h, 5s, 4h, 7c, 5h, 6s, 6h, 6c, 7h, 7s, 8h, 5c, 9h, 8s, 10h, 4c, 11h, 9s, 12h, 3c, 13h, 10s, 13d, 2c, 12d, 11s, 11d, 1c, 10d, 12s, 9d]";
				//N=4  ( 29 11 17 27 )
				Deck2 = "[8s, 9c, 2d, 11h, 3c, 12h, 9s, 13h, 2h, 13d, 1d, 12d, 8h, 11d, 10s, 10d, 8c, 1s, 13c, 9d, 2c, 2s, 11s, 8d, 3h, 3s, 12c, 7d, 9h, 4s, 12s, 6d, 7c, 5s, 5h, 5d, 1c, 6s, 11c, 10c, 4h, 4d, 5c, 4c, 10h, 7s, 13s, 1h, 6c, 3d, 6h, 7h]";
				//N=8  ( 30 16 30 25 30 41 21 28 )
				Deck3 = "[3s, 9c, 4s, 2s, 9d, 7s, 11d, 13s, 13c, 1s, 6c, 13d, 9s, 7c, 6s, 5s, 8h, 3c, 7h, 10s, 3d, 2d, 12c, 1c, 13h, 6d, 1d, 8d, 2c, 6h, 9h, 12d, 5d, 5c, 7d, 3h, 11s, 10h, 10d, 4d, 2h, 10c, 4h, 11c, 11h, 1h, 8c, 4c, 5h, 12h, 12s, 8s]";
				//N=16  ( 30 16 32 39 32 21 32 10 24 29 38 22 26 27 30 24 )
				Deck4 = "[13d, 4d, 13s, 8h, 1s, 3d, 11s, 5c, 1h, 10h, 5d, 7h, 7c, 7s, 11d, 3s, 12d, 10c, 5s, 5h, 4s, 6c, 12c, 4c, 6d, 11h, 2h, 9h, 1c, 6h, 8d, 4h, 8s, 13c, 1d, 2d, 12h, 3h, 7d, 12s, 13h, 10d, 6s, 9c, 10s, 3c, 8c, 9d, 2s, 11c, 2c, 9s]";
				//N=32  ( 12 37 26 17 23 19 19 15 16 40 22 33 22 41 33 15 38 12 28 37 41 12 13 14 34 11 36 10 39 13 31 26 )
				Deck5 = "[11c, 12s, 6s, 3c, 11d, 2s, 7s, 5h, 13d, 12d, 1d, 8c, 4h, 11h, 8h, 2d, 10c, 2h, 3d, 13s, 4d, 9s, 5s, 8d, 13c, 6d, 10d, 1h, 13h, 3s, 3h, 6c, 5c, 12h, 1s, 1c, 12c, 7h, 8s, 7c, 4c, 9h, 2c, 10s, 6h, 4s, 5d, 9c, 9d, 7d, 10h, 11s]";
			}
			if(w==7){
				technique = "OUTFARO";
				//N=2  ( 22 37 )
				Deck1 = "[1s, 12c, 10h, 7h, 2s, 11c, 11h, 8h, 3s, 10c, 12h, 9h, 4s, 9c, 13h, 8c, 5s, 7c, 13d, 6c, 6s, 5c, 12d, 4c, 7s, 3c, 11d, 2c, 8s, 1c, 10d, 9s, 9d, 10s, 8d, 11s, 7d, 12s, 6d, 13s, 5d, 1h, 4d, 2h, 3d, 3h, 2d, 4h, 1d, 5h, 13c, 6h]";
				//N=4  ( 15 38 24 22 )
				Deck2 = "[1s, 9c, 7s, 13h, 1d, 4s, 2c, 12s, 3h, 8c, 9h, 13d, 13c, 6h, 1c, 13s, 2s, 7c, 8s, 12d, 12c, 5s, 10h, 1h, 4h, 6c, 9s, 11d, 11c, 7h, 11h, 2h, 3s, 5c, 10s, 10d, 10c, 6s, 12h, 9d, 5h, 4c, 11s, 8d, 8h, 7d, 3c, 6d, 5d, 4d, 3d, 2d]";
				//N=8  ( 40 31 19 22 30 20 38 29 )
				Deck3 = "[1s, 12s, 13c, 7h, 10d, 7c, 4h, 7d, 6c, 1c, 9h, 4s, 10c, 4d, 5h, 1d, 12h, 13s, 5s, 6s, 9s, 2s, 6h, 11h, 2d, 1h, 7s, 5d, 2c, 13d, 3d, 11s, 11d, 2h, 11c, 12c, 8h, 9d, 12d, 8s, 3s, 3h, 8d, 13h, 6d, 4c, 10s, 5c, 10h, 9c, 8c, 3c]";
				//N=16  ( 42 26 42 21 36 11 40 16 19 38 30 17 20 37 19 33 )
				Deck4 = "[1s, 11c, 12d, 13d, 6s, 4c, 13s, 9s, 1d, 3d, 13c, 8d, 2s, 4d, 8s, 9c, 2d, 10h, 4h, 9h, 5h, 7c, 6d, 1h, 10s, 3s, 5c, 5d, 7d, 12s, 3h, 11h, 8h, 7s, 10d, 4s, 7h, 2h, 3c, 8c, 12c, 6h, 13h, 6c, 2c, 1c, 11s, 11d, 10c, 9d, 5s, 12h]";
				//N=32  ( 32 39 31 24 20 42 26 32 32 24 31 12 29 32 10 35 41 14 36 39 18 18 10 41 38 12 21 29 21 30 28 26 )
				Deck5 = "[1s, 8c, 5c, 9c, 3d, 6c, 11c, 5d, 2h, 5h, 4d, 1h, 6h, 6s, 6d, 3s, 1d, 7d, 8d, 4h, 9d, 13h, 13c, 8h, 13d, 10d, 9s, 7c, 11d, 4c, 4s, 3c, 12h, 12s, 2d, 10h, 7h, 8s, 2c, 10s, 13s, 7s, 2s, 11s, 12d, 9h, 1c, 5s, 11h, 12c, 10c, 3h]";
			}
			if(w==8){
				technique = "OUTFAROUP";
				//N=2  ( 36 28 )
				Deck1 = "[3s, 1d, 4s, 13c, 4h, 5s, 5h, 12c, 6h, 6s, 7h, 11c, 8h, 7s, 9h, 10c, 10h, 8s, 11h, 9c, 12h, 9s, 13h, 8c, 13d, 10s, 12d, 7c, 11d, 11s, 10d, 6c, 9d, 12s, 8d, 5c, 7d, 13s, 6d, 4c, 5d, 1h, 4d, 3c, 1s, 2h, 3d, 2c, 2s, 3h, 2d, 1c]";
				//N=4  ( 28 25 32 33 )
				Deck2 = "[5c, 12d, 4d, 13s, 8h, 1s, 9s, 12c, 4c, 11d, 3d, 1h, 9h, 2s, 8d, 10s, 4h, 11c, 5s, 3c, 8c, 10d, 7d, 2d, 5h, 2h, 6s, 10h, 7c, 3s, 6d, 11s, 6h, 10c, 7s, 2c, 6c, 9d, 12h, 1d, 5d, 3h, 13h, 11h, 7h, 4s, 13d, 12s, 8s, 9c, 13c, 1c]";
				//N=8  ( 27 26 13 13 32 36 27 32 )
				Deck3 = "[7h, 4c, 12c, 4s, 12s, 8h, 2s, 11s, 3d, 7c, 2h, 4h, 3h, 9s, 8c, 2d, 13d, 11d, 9h, 4d, 7s, 6d, 6s, 13h, 13c, 11c, 10d, 9d, 2c, 12h, 6h, 10s, 1s, 5s, 6c, 8s, 10h, 3s, 7d, 13s, 1h, 5h, 10c, 9c, 5c, 3c, 11h, 5d, 12d, 8d, 1d, 1c]";
				//N=16  ( 15 32 21 16 24 20 37 13 34 19 32 37 35 10 25 36 )
				Deck4 = "[12c, 8s, 3h, 10d, 10s, 13s, 7d, 10h, 3c, 11s, 4c, 5h, 2c, 9h, 1d, 12s, 5c, 9d, 11c, 4d, 6s, 12h, 10c, 13d, 1h, 7h, 13h, 6d, 4s, 2s, 8h, 5d, 2d, 3d, 8d, 7c, 11h, 13c, 11d, 2h, 6h, 1s, 6c, 9c, 8c, 7s, 3s, 4h, 5s, 9s, 12d, 1c]";
				//N=32  ( 10 27 23 27 37 42 18 21 37 15 18 15 29 38 38 38 37 26 40 16 30 25 39 23 11 38 20 41 19 10 38 39 )
				Deck5 = "[13h, 2s, 1d, 12h, 3s, 11h, 9d, 5s, 3h, 7h, 2c, 3c, 9h, 5d, 13c, 2h, 10d, 10h, 11s, 8c, 4h, 11c, 1h, 10s, 9c, 10c, 4d, 4s, 1s, 2d, 6d, 13d, 9s, 12d, 8s, 4c, 8h, 6s, 11d, 6c, 12s, 12c, 3d, 5c, 8d, 13s, 7c, 7d, 5h, 7s, 6h, 1c]";
			}	
			if(w==9){
				technique = "CUTTING-INSERTION";
				//N=2  ( 27 52 )
				Deck1 = "[12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d]";
				//N=4  ( 32 30 29 48 )
				Deck2 = "[11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7d, 7s, 8s, 9s, 10s]";
				//N=8  ( 8 47 6 40 15 41 23 1 )
				Deck3 = "[3h, 9s, 4s, 5s, 6s, 7s, 8s, 10s, 11s, 12s, 13s, 1h, 2h, 4h, 5h, 6h, 7h, 8d, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s]";
				//N=16  ( 5 47 52 22 8 1 33 30 1 2 6 48 28 36 31 15 )
				Deck4 = "[2h, 3h, 4h, 8d, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 5c, 1h, 13d, 12d, 7s, 11d, 10d, 9d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 4c, 2c, 3c, 3s, 1c, 6s, 1s, 2s, 4s, 5s, 8s, 9s, 10s, 11s, 12s, 13s]";
				//N=32  ( 13 47 9 20 28 52 26 7 27 34 37 30 49 52 14 49 10 50 33 30 29 15 33 9 22 11 6 18 28 19 32 27 )
				Deck5 = "[13c, 12c, 11c, 10c, 9c, 8c, 7c, 3c, 7d, 6c, 5c, 4c, 2c, 1c, 2s, 4s, 5s, 6s, 7s, 8s, 1h, 10s, 3s, 11s, 12s, 13s, 2d, 2h, 3h, 1d, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 13h, 9s, 12d, 11d, 10d, 9d, 8d, 13d, 6d, 5d, 12h, 11h, 1s, 4d, 3d]";
			}
			if(w==10){
				technique = "CUTTING-INTERCALATION";
				//N=2  ( 26 16 )
				Deck1 = "[10c, 13d, 9c, 12d, 8c, 11d, 7c, 10d, 6c, 9d, 5c, 8d, 4c, 7d, 3c, 6d, 2c, 5d, 1c, 4d, 1s, 3d, 2s, 2d, 3s, 1d, 4s, 13c, 5s, 12c, 6s, 11c, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h]";
				//N=4  ( 23 33 32 27 )
				Deck2 = "[13d, 8h, 9s, 13c, 12d, 9h, 10s, 12c, 11d, 10h, 11s, 11c, 10d, 10c, 12s, 9c, 9d, 8c, 13s, 7c, 8d, 6c, 1h, 5c, 7d, 4c, 2h, 3c, 6d, 2c, 3h, 1c, 5d, 1s, 4h, 2s, 4d, 3s, 5h, 4s, 3d, 5s, 6h, 11h, 2d, 6s, 7h, 12h, 1d, 7s, 13h, 8s]";
				//N=8  ( 30 35 8 37 9 26 43 36 )
				Deck3 = "[3h, 11c, 4s, 6h, 13c, 13h, 5s, 10s, 7d, 10c, 6s, 4d, 11h, 13d, 7s, 11s, 4h, 9c, 5h, 12d, 12c, 7h, 8s, 8c, 6d, 12s, 5d, 11d, 12h, 3d, 9s, 7c, 13s, 10d, 8h, 6c, 1h, 5c, 2d, 4c, 9d, 3c, 9h, 2c, 2h, 1c, 1d, 1s, 8d, 2s, 10h, 3s]";
				//N=16  ( 25 39 26 13 16 34 39 35 18 21 7 13 37 34 40 13 )
				Deck4 = "[13d, 12c, 13h, 13s, 8h, 11h, 11d, 10d, 6s, 3c, 6c, 10c, 6d, 7c, 11c, 2d, 2h, 3s, 6h, 9d, 9h, 3d, 12h, 5h, 8c, 12s, 1c, 5d, 5s, 10s, 9s, 12d, 8d, 13c, 1d, 10h, 1h, 4c, 5c, 7s, 7h, 2s, 1s, 2c, 9c, 4d, 7d, 4h, 11s, 4s, 3h, 8s]";
				//N=32  ( 27 32 31 23 8 26 41 12 43 42 51 35 11 22 44 28 4 28 52 38 8 39 7 25 45 30 19 41 38 19 17 39 )
				Deck5 = "[8d, 5s, 10s, 4c, 10h, 10c, 7c, 13c, 12h, 10d, 4h, 13h, 1s, 6c, 12s, 7d, 11c, 11h, 5h, 13d, 7h, 9h, 8s, 2s, 9s, 11s, 5d, 3d, 7s, 6h, 8c, 9d, 4d, 1c, 3h, 1h, 9c, 4s, 3c, 2d, 12d, 2h, 5c, 12c, 8h, 6d, 3s, 1d, 13s, 6s, 11d, 2c]";
			}
			if(w==11){
				technique = "CUTTING-INVERSION";
				//N=2  ( 6 1 )
				Deck1 = "[7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s]";
				//N=4  ( 4 2 30 43 )
				Deck2 = "[12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 5s, 6s, 4s, 3s, 2s, 1s, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 1d, 2d, 3d, 4d, 5d, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d]";
				//N=8  ( 1 12 14 7 36 12 9 4 )
				Deck3 = "[3s, 1c, 1s, 13s, 2s, 1h, 2h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s]";
				//N=16  ( 16 43 23 7 13 30 35 41 42 34 42 44 13 51 27 41 )
				Deck4 = "[12d, 11d, 4d, 5d, 6h, 7h, 8h, 9h, 10h, 8c, 9c, 10c, 11c, 12c, 4h, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 5h, 13c, 1d, 13d, 13h, 12h, 11h, 6d, 7d, 8d, 9d, 10d, 3d, 2d]";
				//N=32  ( 24 5 4 51 34 18 16 13 25 11 39 14 28 26 20 15 21 49 1 25 51 35 35 37 31 17 11 23 46 18 41 19 )
				Deck5 = "[3d, 4d, 5d, 6d, 7d, 8d, 6s, 7s, 2d, 1d, 13c, 12c, 11c, 13h, 4s, 3s, 2s, 1s, 1c, 2h, 3h, 6h, 7h, 9s, 10s, 11s, 12s, 13s, 1h, 8s, 9c, 5h, 4h, 10c, 12h, 10d, 9d, 5s, 7c, 8c, 8h, 9h, 10h, 11h, 11d, 12d, 13d, 4c, 5c, 6c, 3c, 2c]";
			}
			if(w==12){
				technique = "INSERTION-CUTTING";
				//N=2  ( 35 5 )
				Deck1 = "[7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 1s, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 2s, 3s, 4s, 5s, 6s]";
				//N=4  ( 20 16 24 44 )
				Deck2 = "[10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 6h, 7h, 1s, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 5h, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s]";
				//N=8  ( 6 24 27 1 2 52 35 21 )
				Deck3 = "[4c, 3c, 2c, 12h, 1c, 2s, 3s, 4s, 5s, 6s, 1s, 7s, 8s, 12d, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 13h, 13d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";
				//N=16  ( 42 26 41 17 7 40 29 27 37 50 52 23 37 30 40 26 )
				Deck4 = "[2d, 1d, 13c, 12c, 11c, 1s, 10c, 9c, 7c, 10s, 6c, 5c, 4c, 6d, 3c, 2c, 8c, 1c, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 11s, 12s, 13s, 1h, 2h, 9d, 3h, 12d, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 11d, 10d, 8d, 7d, 5d, 4d, 3d]";
				//N=32  ( 20 31 30 49 32 5 23 3 48 22 42 51 40 18 12 5 40 42 5 15 38 31 48 25 32 4 24 39 47 22 22 1 )
				Deck5 = "[11s, 12s, 13s, 1h, 6h, 2h, 3h, 4h, 5h, 7h, 13c, 2c, 1s, 8h, 10h, 6c, 13d, 11h, 12h, 13h, 9s, 9h, 12d, 3d, 10d, 9d, 7d, 6d, 5d, 2d, 1d, 12c, 11c, 10c, 9c, 8c, 7c, 5c, 4c, 3c, 10s, 1c, 11d, 2s, 3s, 4s, 5s, 6s, 7s, 4d, 8s, 8d]";
			}
				
			if(w==13){
				technique = "INSERTION-INTERCALATION";
				//N=2  ( 43 41 )
				Deck1 = "[10c, 2s, 1s, 3s, 9c, 4s, 8c, 5s, 7c, 6s, 6c, 7s, 5c, 8s, 4c, 9s, 3c, 10s, 2c, 11s, 1c, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c]";
				//N=4  ( 9 19 41 13 )
				Deck2 = "[13d, 2s, 9s, 8h, 12d, 3s, 1s, 9h, 11d, 4s, 10s, 10h, 10d, 5s, 11s, 11h, 9d, 6s, 12s, 12h, 8d, 7s, 13s, 13h, 7d, 8s, 1h, 6d, 2h, 5d, 3h, 4d, 4h, 3d, 5h, 2d, 6h, 1d, 13c, 12c, 7h, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=8  ( 40 14 25 10 43 29 48 39 )
				Deck3 = "[3c, 2s, 13d, 7d, 2c, 9h, 13s, 6d, 1c, 4h, 12d, 5d, 3h, 8s, 1h, 4d, 8d, 3s, 11d, 3d, 2h, 10h, 10d, 2d, 9d, 5h, 1d, 9s, 13c, 4s, 1s, 11h, 12c, 6h, 11c, 10s, 10c, 5s, 7s, 12h, 9c, 7h, 8c, 11s, 7c, 6s, 6c, 13h, 5c, 8h, 4c, 12s]";
				//N=16  ( 33 17 23 24 13 31 23 39 1 31 52 11 41 29 40 10 )
				Deck4 = "[9d, 3h, 3c, 12d, 2s, 10d, 13h, 4c, 10s, 11h, 5h, 2d, 9h, 7s, 10c, 6s, 6c, 12s, 2c, 3s, 1c, 1h, 4d, 1d, 7c, 2h, 5s, 9s, 12c, 11s, 7h, 13c, 13d, 8h, 6h, 9c, 5d, 7d, 12h, 6d, 11d, 1s, 4s, 4h, 8c, 11c, 5c, 3d, 10h, 8d, 13s, 8s]";
				//N=32  ( 31 14 13 24 31 27 15 38 34 20 34 22 1 34 39 19 42 10 4 34 24 19 45 14 22 20 26 40 9 39 45 24 )
				Deck5 = "[11h, 3c, 3h, 13c, 1h, 5c, 7c, 10c, 7h, 7s, 3s, 9s, 9c, 2c, 4d, 11c, 9h, 8c, 12c, 2s, 8s, 12d, 4s, 12h, 1d, 5s, 4c, 5d, 2h, 3d, 7d, 12s, 6s, 13d, 4h, 5h, 6h, 6c, 1s, 11s, 10d, 8h, 2d, 13h, 9d, 11d, 10s, 13s, 8d, 10h, 1c, 6d]";
			}
			if(w==14){
				technique = "INSERTION-INVERSION";
				//N=2  ( 14 15 )
				Deck1 = "[2h, 1s, 1h, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=4  ( 52 1 8 9 )
				Deck2 = "[9s, 1s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=8  ( 46 52 21 6 35 14 13 22 )
				Deck3 = "[9d, 8d, 1c, 7d, 6d, 5d, 4d, 3d, 6c, 2d, 5c, 4c, 3c, 2c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 1d, 10d, 11d, 12d, 13d, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 1s, 5h, 4h, 3h, 2h, 1h, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s]";
				//N=16  ( 30 17 45 32 20 3 43 30 6 32 13 37 13 40 5 39 )
				Deck4 = "[3d, 4d, 5d, 2h, 1h, 13s, 12s, 11s, 3h, 10s, 9s, 1d, 8s, 7s, 6s, 7d, 5s, 4s, 3s, 2s, 6h, 7h, 8h, 6d, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 8d, 11c, 9d, 4h, 13c, 12c, 2d, 10c, 9c, 1s, 8c, 5h, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=32  ( 46 31 37 31 43 41 51 2 28 47 5 44 31 27 15 9 6 26 34 52 1 35 10 44 19 6 16 1 6 41 38 18 )
				Deck5 = "[7d, 9c, 2s, 3s, 4s, 3d, 5s, 6s, 7s, 8d, 8s, 9s, 13s, 10s, 2d, 5h, 4h, 3h, 5c, 8c, 7c, 13h, 1s, 6c, 8h, 1d, 13c, 11c, 4c, 3c, 2c, 10c, 1c, 12s, 9h, 7h, 12h, 2h, 11s, 11h, 10h, 1h, 6h, 12c, 13d, 12d, 11d, 10d, 9d, 6d, 5d, 4d]";
			}
			if(w==15){
				technique = "INTERCALATION-CUTTING";
				//N=2  ( 17 52 )
				Deck1 = "[5h, 1s, 6h, 2s, 7h, 3s, 8h, 4s, 9h, 5s, 10h, 6s, 11h, 7s, 12h, 8s, 13h, 9s, 13d, 10s, 12d, 11s, 11d, 12s, 10d, 13s, 9d, 1h, 8d, 2h, 7d, 3h, 6d, 4h, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=4  ( 24 4 32 35 )
				Deck2 = "[11s, 13h, 4d, 2s, 12s, 3d, 13s, 2d, 1h, 1d, 2h, 13c, 3h, 12c, 4h, 11c, 5h, 10c, 13d, 6h, 3s, 9c, 12d, 7h, 4s, 8c, 11d, 8h, 5s, 7c, 10d, 9h, 6s, 6c, 9d, 10h, 7s, 5c, 8d, 11h, 8s, 4c, 7d, 3c, 9s, 2c, 6d, 1c, 10s, 12h, 5d, 1s]";
				//N=8  ( 35 45 10 45 42 33 27 3 )
				Deck3 = "[1s, 4h, 11c, 13d, 3d, 5h, 7s, 12d, 10c, 6h, 8s, 2s, 9c, 7h, 9s, 11d, 8c, 8h, 10s, 2d, 7c, 10d, 11s, 3s, 6c, 9d, 12s, 1d, 5c, 8d, 13s, 4s, 4c, 7d, 1h, 13c, 3c, 6d, 9h, 5s, 2h, 5d, 10h, 12c, 2c, 4d, 11h, 3h, 12h, 1c, 6s, 13h]";
				//N=16  ( 16 20 11 7 40 18 21 40 30 25 37 4 29 50 34 27 )
				Deck4 = "[2s, 1c, 11c, 12h, 10d, 9d, 9h, 13h, 3c, 8d, 8s, 6s, 13c, 3d, 7d, 10c, 9s, 1s, 6c, 10h, 6h, 10s, 1d, 9c, 2c, 13s, 7h, 12s, 4h, 7s, 5d, 12d, 11h, 3h, 4s, 11s, 6d, 8c, 12c, 11d, 13d, 5h, 8h, 5c, 4d, 2d, 2h, 3s, 7c, 4c, 5s, 1h]";
				//N=32  ( 35 33 21 26 20 18 35 40 38 21 19 52 22 46 30 2 10 32 33 23 29 49 10 38 13 22 28 14 38 44 25 18 )
				Deck5 = "[1s, 8h, 2d, 2s, 3d, 11d, 9s, 9h, 3c, 10c, 4h, 12d, 10s, 10d, 5d, 5h, 12c, 4c, 7h, 8c, 4s, 2c, 11c, 8d, 9d, 13c, 13s, 6s, 11h, 1d, 5s, 12s, 6c, 13d, 5c, 1c, 7d, 9c, 1h, 3h, 13h, 6d, 8s, 12h, 10h, 6h, 4d, 7s, 2h, 11s, 7c, 3s]";
			}
			if(w==16){
				technique = "INTERCALATION-INSERTION";
				//N=2  ( 24 32 )
				Deck1 = "[1s, 13h, 2s, 13d, 3s, 12d, 4s, 11d, 5s, 10d, 6s, 9d, 7s, 8d, 8s, 7d, 9s, 6d, 10s, 5d, 11s, 4d, 12s, 3d, 13s, 2d, 1h, 1d, 2h, 13c, 3h, 12h, 12c, 4h, 11c, 5h, 10c, 6h, 9c, 7h, 8c, 8h, 7c, 9h, 6c, 10h, 5c, 11h, 4c, 3c, 2c, 1c]";
				//N=4  ( 15 8 13 9 )
				Deck2 = "[1s, 10h, 4h, 8s, 2s, 11h, 5h, 9s, 7s, 3s, 12h, 6h, 10s, 4s, 13h, 3h, 11s, 7h, 13d, 5s, 12s, 8h, 12d, 6s, 13s, 9h, 11d, 1h, 10d, 2h, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=8  ( 15 52 29 46 27 42 13 27 )
				Deck3 = "[3h, 6d, 10h, 5c, 9d, 5h, 8c, 12h, 1s, 5d, 8s, 4c, 8d, 3s, 7c, 10s, 4h, 4d, 11h, 3c, 7d, 6h, 6c, 13h, 2s, 3d, 9s, 2c, 4s, 11s, 2d, 1c, 7h, 13d, 1d, 2h, 5s, 12s, 13c, 12d, 8h, 9c, 13s, 12c, 11d, 6s, 1h, 11c, 10d, 9h, 10c, 7s]";
				//N=16  ( 12 10 26 15 42 33 18 5 19 16 24 25 22 9 40 24 )
				Deck4 = "[1s, 12d, 7s, 13d, 4h, 2s, 1c, 8c, 13s, 6s, 4s, 9h, 1d, 11h, 4d, 7c, 2h, 10s, 3h, 6c, 2d, 10h, 11d, 9s, 12c, 10d, 4c, 3s, 7h, 11s, 12s, 5d, 11c, 3d, 12h, 6h, 8s, 7d, 6d, 3c, 10c, 2c, 5c, 13h, 8h, 8d, 1h, 5s, 9c, 5h, 9d, 13c]";
				//N=32  ( 24 17 31 43 15 37 32 19 26 25 10 38 20 15 25 2 30 18 34 3 31 33 23 12 28 41 36 25 40 15 14 48 )
				Deck5 = "[1s, 3s, 6c, 4c, 13s, 10d, 7h, 8h, 3h, 13d, 11c, 12s, 1d, 4s, 5c, 11s, 12h, 6s, 7d, 5h, 8d, 3c, 9c, 5s, 11h, 13c, 9h, 9s, 6h, 2s, 12d, 10s, 9d, 12c, 7s, 11d, 2d, 6d, 5d, 4h, 8c, 4d, 8s, 13h, 10h, 2h, 1h, 7c, 10c, 3d, 2c, 1c]";
			}
			if(w==17){
				technique = "INTERCALATION-INVERSION";
				//N=2  ( 37 7 )
				Deck1 = "[12c, 3s, 13c, 2s, 1d, 1s, 2d, 4s, 11c, 5s, 10c, 6s, 9c, 7s, 8c, 8s, 7c, 9s, 6c, 10s, 5c, 11s, 4c, 12s, 3c, 13s, 2c, 1h, 1c, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d]";
				//N=4  ( 19 8 37 31 )
				Deck2 = "[8s, 13d, 1c, 7s, 2c, 13h, 3c, 6s, 4c, 12h, 5c, 5s, 6c, 11h, 7c, 7h, 8c, 1s, 9c, 8h, 10c, 2s, 11c, 9h, 12c, 3s, 13c, 10h, 1d, 4s, 6h, 12d, 9s, 11d, 10s, 10d, 11s, 9d, 12s, 8d, 13s, 7d, 1h, 6d, 2h, 5d, 3h, 4d, 4h, 3d, 5h, 2d]";
				//N=8  ( 28 33 38 7 38 4 42 1 )
				Deck3 = "[2d, 9c, 9s, 4s, 3d, 8h, 8s, 7d, 4d, 8d, 7s, 4c, 5d, 3s, 6s, 3h, 6d, 9d, 5s, 7h, 2s, 8c, 10d, 5c, 1s, 2h, 11d, 3c, 4h, 10c, 7c, 9h, 5h, 1h, 6c, 2c, 6h, 11c, 10h, 13s, 1c, 12c, 11h, 12s, 12h, 13c, 13h, 11s, 13d, 1d, 12d, 10s]";
				//N=16  ( 11 37 11 22 36 43 27 46 26 31 12 2 40 28 15 11 )
				Deck4 = "[11s, 3c, 1s, 3d, 13h, 7s, 10h, 3s, 5h, 3h, 7c, 12d, 8s, 9h, 4c, 8c, 10s, 10c, 11c, 5d, 1c, 11d, 10d, 9d, 13s, 5s, 12c, 1d, 9s, 2h, 5c, 7d, 12h, 6h, 1h, 4s, 7h, 8d, 4d, 2s, 13c, 6c, 11h, 12s, 6s, 2c, 9c, 13d, 4h, 6d, 8h, 2d]";
				//N=32  ( 38 39 25 39 16 14 34 8 18 7 34 18 15 11 10 44 32 47 15 36 28 41 41 23 37 16 30 17 14 17 36 15 )
				Deck5 = "[6s, 9s, 7s, 13c, 6c, 12c, 4h, 2s, 8d, 1h, 6h, 13h, 11d, 9h, 10s, 10c, 3s, 8h, 10d, 12h, 3d, 8s, 11h, 12d, 4d, 7d, 8c, 5c, 5d, 6d, 2c, 1d, 13d, 9c, 4c, 4s, 1c, 12s, 7c, 11s, 10h, 5s, 11c, 2h, 5h, 2d, 3h, 9d, 13s, 3c, 7h, 1s]";
			}
			if(w==18){
				technique = "INVERSION-CUTTING";
				//N=2  ( 11 13 )
				Deck1 = "[1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 12s, 13s]";
				//N=4  ( 13 40 32 31 )
				Deck2 = "[12c, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c]";
				//N=8  ( 21 25 47 24 35 41 7 25 )
				Deck3 = "[12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 9s, 10s, 11s, 12s, 13s, 1h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1c, 2c, 3c, 4c, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 12h, 11h, 10h, 9h, 1s, 13h, 13d]";
				//N=16  ( 43 50 3 44 37 5 15 33 2 43 48 52 37 17 18 5 )
				Deck4 = "[11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 13c, 12c, 2h, 1h, 3h, 1s, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 11c, 2c, 1c, 10c, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 4h, 5h, 6h, 7h, 8h, 1d, 2d, 3d, 9h, 10h]";
				//N=32  ( 7 8 6 28 22 37 37 12 20 21 38 31 7 33 27 15 51 11 42 13 50 40 11 25 52 27 33 41 50 46 31 41 )
				Deck5 = "[4h, 5h, 6h, 7h, 3c, 2c, 12c, 10c, 5d, 4d, 2s, 10s, 9s, 8s, 1h, 6d, 9c, 8c, 7c, 6c, 5c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 13h, 4c, 8h, 3d, 2d, 1d, 13c, 11c, 7s, 1c, 6s, 5s, 4s, 3s, 11s, 12s, 13s, 12h, 11h, 10h, 9h, 1s, 2h, 3h]";
			}
			if(w==19){
				technique = "INVERSION-INSERTION";
				//N=2  ( 51 14 )
				Deck1 = "[3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 1d, 2d, 2c, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 1c]";
				//N=4  ( 39 52 50 52 )
				Deck2 = "[3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 2c, 1c]";
				//N=8  ( 3 35 22 19 41 3 16 8 )
				Deck3 = "[12d, 11d, 10d, 9d, 8d, 7d, 6d, 13d, 5d, 3s, 4d, 3d, 2d, 12c, 1d, 13c, 13h, 12h, 11h, 2s, 1s, 4s, 10h, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
				//N=16  ( 28 6 16 24 40 6 52 20 18 10 19 16 10 26 34 14 )
				Deck4 = "[7s, 8s, 9s, 10s, 11s, 12s, 13d, 10c, 13h, 12h, 11h, 10h, 12d, 6s, 9h, 1c, 6h, 5h, 4h, 8h, 3h, 2h, 1h, 12c, 11c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 7h, 5s, 13s, 4s, 3s, 2s, 1s, 11d, 10d, 9d, 8d, 7d, 6d, 13c, 5d, 4d, 3d, 2d, 1d]";
				//N=32  ( 17 24 49 14 2 26 11 9 36 16 25 5 20 49 2 3 11 11 41 23 44 17 6 3 2 14 28 46 6 32 40 10 )
				Deck5 = "[4s, 2d, 1d, 13c, 12c, 11c, 10c, 9s, 9c, 5s, 8c, 3d, 7c, 5c, 13d, 13h, 3s, 10s, 11s, 12h, 4h, 6c, 10h, 11h, 9h, 11d, 10d, 12d, 9d, 8d, 12s, 4d, 7d, 6d, 5d, 1s, 5h, 6h, 7h, 4c, 6s, 7s, 8s, 13s, 1h, 2s, 2h, 3h, 8h, 3c, 2c, 1c]";
			}
			if(w==20){
				technique = "INVERSION-INTERCALATION";
				//N=2  ( 4 30 )
				Deck1 = "[9d, 4s, 8d, 3s, 7d, 2s, 6d, 1s, 5d, 5s, 4d, 6s, 3d, 7s, 2d, 8s, 1d, 9s, 13c, 10s, 12c, 11s, 11c, 12s, 10c, 13s, 9c, 1h, 8c, 2h, 7c, 3h, 6c, 4h, 5c, 5h, 4c, 6h, 3c, 7h, 2c, 8h, 1c, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d]";
				//N=4  ( 1 26 28 12 )
				Deck2 = "[8s, 1h, 6d, 13c, 7s, 13s, 7d, 1d, 6s, 12s, 8d, 2d, 5s, 11s, 9d, 3d, 4s, 10s, 10d, 4d, 3s, 9s, 11d, 5d, 2s, 12d, 1s, 13d, 12c, 2h, 11c, 3h, 10c, 4h, 9c, 5h, 8c, 6h, 7c, 7h, 6c, 8h, 5c, 9h, 4c, 10h, 3c, 11h, 2c, 12h, 1c, 13h]";
				//N=8  ( 21 20 17 26 15 38 4 24 )
				Deck3 = "[1c, 13h, 8s, 12s, 9s, 5s, 11d, 7c, 6d, 6c, 4h, 3d, 2d, 9d, 12h, 3h, 4s, 5c, 5h, 6s, 1d, 11s, 11h, 13d, 3s, 4c, 6h, 4d, 13c, 8d, 10h, 2h, 2s, 3c, 7h, 7s, 12c, 10s, 9h, 12d, 11c, 2c, 8h, 5d, 10c, 7d, 1s, 1h, 9c, 13s, 8c, 10d]";
				//N=16  ( 36 40 8 39 12 20 2 37 36 27 34 34 28 23 18 40 )
				Deck4 = "[8d, 13d, 4d, 7d, 9c, 11h, 13c, 5d, 7c, 12s, 1h, 11s, 4c, 9d, 8h, 10h, 8s, 9s, 4s, 2s, 12d, 8c, 7h, 10c, 10d, 2h, 5h, 13h, 3c, 1c, 6h, 4h, 6d, 6s, 1d, 1s, 3d, 3s, 10s, 5s, 11c, 13s, 5c, 12h, 9h, 2c, 7s, 3h, 2d, 6c, 11d, 12c]";
				//N=32  ( 47 36 49 23 40 11 32 24 49 32 10 42 6 12 2 17 2 30 33 35 17 19 29 22 23 19 33 36 18 36 45 21 )
				Deck5 = "[7c, 7s, 5c, 6d, 4c, 1s, 11h, 5s, 4h, 8d, 3s, 7d, 3d, 2s, 13d, 7h, 3c, 4d, 12h, 13c, 11d, 9d, 4s, 1d, 8h, 9h, 2d, 10c, 12c, 5h, 10s, 8s, 8c, 6c, 13s, 1c, 11s, 11c, 12d, 9s, 13h, 6s, 2c, 2h, 10h, 6h, 5d, 12s, 1h, 10d, 9c, 3h]";
			}
				
				
				
				for (int i=0; i<15;i++){
					System.out.println("----------------------------------------"+i+"---------------------------------------");
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("--------------------------------------------------------------------------------");
					javiGP gp = new javiGP();
					gp.initializeNumbersArray();
					gp.buildPopulation();
					if(i>=0&&i<=2){
						gp.desiredDeckString= Deck1;

						switch(i){
						case 0:
							int j = 0;
							int iterations=150;
							int lastTechNum=0;	
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points1n2.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum1n2.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points1n2.add(510);
									techNum1n2.add(lastTechNum);
									j++;
								}
							}
							break;
						case 1:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;	
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points2n2.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum2n2.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points2n2.add(510);
									techNum2n2.add(lastTechNum);
									j++;
								}
							}
							break;
						case 2:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points3n2.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum3n2.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points3n2.add(510);
									techNum3n2.add(lastTechNum);
									j++;
								}
							}
							break;
						}
					}
					else if (i>2&&i<=5){
						gp.desiredDeckString= Deck2;
						switch(i){
						case 3:
							int j = 0;
							int iterations=150;
							int lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points1n4.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum1n4.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points1n4.add(510);
									techNum1n4.add(lastTechNum);
									j++;
								}
							}
							break;
						case 4:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points2n4.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum2n4.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points2n4.add(510);
									techNum2n4.add(lastTechNum);
									j++;
								}
							}
							break;
						case 5:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points3n4.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum3n4.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points3n4.add(510);
									techNum3n4.add(lastTechNum);
									j++;
								}
							}
							break;
						}
					}
					else if (i>5&&i<=8){
						gp.desiredDeckString= Deck3;
						switch(i){
						case 6:
							int j = 0;
							int iterations=150;
							int lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points1n8.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum1n8.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points1n8.add(510);
									techNum1n8.add(lastTechNum);
									j++;
								}
							}
							break;
						case 7:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points2n8.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum2n8.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points2n8.add(510);
									techNum2n8.add(lastTechNum);
									j++;
								}
							}
							break;
						case 8:
							j = 0;
							iterations=150;
							lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points3n8.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum3n8.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points3n8.add(510);
									techNum3n8.add(lastTechNum);
									j++;
								}
							}
							break;
						}
					}
					else if (i>8&&i<=11){
						gp.desiredDeckString= Deck4;
						switch(i){
						case 9:
							int j = 0;
							int iterations=150;
							int lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points1n16.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum1n16.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points1n16.add(510);
									techNum1n16.add(lastTechNum);
									j++;
								}
							}
							break;
						case 10:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points2n16.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum2n16.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points2n16.add(510);
									techNum2n16.add(lastTechNum);
									j++;
								}
							}
							break;
						case 11:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points3n16.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum3n16.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points3n16.add(510);
									techNum3n16.add(lastTechNum);
									j++;
								}
							}
							break;
						}
					}
					else if(i>11&&i<=14){
						gp.desiredDeckString= Deck5;
						switch(i){
						case 12:
							int j = 0;
							int iterations=150;
							int lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points1n32.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum1n32.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points1n32.add(510);
									techNum1n32.add(lastTechNum);
									j++;
								}
							}
							break;
						case 13:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points2n32.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum2n32.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points2n32.add(510);
									techNum2n32.add(lastTechNum);
									j++;
								}
							}
							break;
						case 14:
							 j = 0;
							 iterations=150;
							 lastTechNum=0;
							//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
							while(gp.population.get(0).getSecondElement()<510&&j<iterations){
								System.out.println("----------"+j+". GENERATION...-----------");
								System.out.println(gp.population.get(0).getFirstElement());
								System.out.println("Points:"+gp.population.get(0).getSecondElement());
								gp.evolvePopulation();
								Points3n32.add(gp.population.get(0).getSecondElement());
								lastTechNum=gp.population.get(0).getThirdElement();
								techNum3n32.add(lastTechNum);
								j++;
							}
							if (j!=iterations){
								while (j<iterations){
									Points3n32.add(510);
									techNum3n32.add(lastTechNum);
									j++;
								}
							}
							break;
						}
					}
					
					gp.pointsToCSV(technique,"THIRDEXP_ParamOptimization_10",Points1n2, Points2n2, Points3n2, Points1n4, Points2n4, Points3n4, Points1n8, Points2n8, Points3n8, Points1n16, Points2n16, Points3n16, Points1n32, Points2n32, Points3n32 ,techNum1n2, techNum2n2, techNum3n2, techNum1n4, techNum2n4, techNum3n4, techNum1n8, techNum2n8, techNum3n8, techNum1n16, techNum2n16, techNum3n16, techNum1n32, techNum2n32, techNum3n32);
				}
				Points1n2.clear();Points2n2.clear();Points3n2.clear();Points1n4.clear();Points2n4.clear();Points3n4.clear();Points1n8.clear();Points2n8.clear();Points3n8.clear();Points1n16.clear();Points1n32.clear();Points2n32.clear();Points3n32.clear();techNum1n2.clear();techNum2n2.clear();techNum3n2.clear();techNum1n4.clear();techNum2n4.clear();techNum3n4.clear();techNum1n8.clear();techNum2n8.clear();techNum3n8.clear();techNum1n16.clear();techNum2n16.clear();techNum3n16.clear();techNum1n32.clear();techNum2n32.clear();techNum3n32.clear();
		}
		/*
		
		
		javiGP gp = new javiGP();
		//gp.pointsToCSV(technique,Points1n2, Points2n2, Points3n2, Points1n4, Points2n4, Points3n4, Points1n8, Points2n8, Points3n8, Points1n16, Points2n16, Points3n16, Points1n32, Points2n32, Points3n32);
		gp.initializeNumbersArray();
		//String Deck4 = "[2s, 1c, 11c, 12h, 10d, 9d, 9h, 13h, 3c, 8d, 8s, 6s, 13c, 3d, 7d, 10c, 9s, 1s, 6c, 10h, 6h, 10s, 1d, 9c, 2c, 13s, 7h, 12s, 4h, 7s, 5d, 12d, 11h, 3h, 4s, 11s, 6d, 8c, 12c, 11d, 13d, 5h, 8h, 5c, 4d, 2d, 2h, 3s, 7c, 4c, 5s, 1h]";
		gp.desiredDeckString= Deck4;
		gp.buildPopulation();
		
		int j = 0;
		int iterations=150;
			
		//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
		while(gp.population.get(0).getSecondElement()<510&&j<iterations){
			System.out.println("----------"+j+". GENERATION...-----------");
			System.out.println(gp.population.get(0).getFirstElement());
			System.out.println("Points:"+gp.population.get(0).getSecondElement());
			gp.evolvePopulation();
			//Points1n2.add(gp.population.get(0).getSecondElement());
			j++;
		}
		gp.printPopulation(3);*/
		
		
		
		
		//TESTING
		
		/*
		 javiGP gp = new javiGP();
		
		
		gp.initializeNumbersArray();
		gp.buildPopulation();
		  
		  ArrayList<Integer> Points=new ArrayList<>();
		  int i = 0;
		int iterations=150;
		
		//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
		while(gp.population.get(0).getSecondElement()<510&&i<iterations){
			System.out.println("----------"+i+". GENERATION...-----------");
			System.out.println(gp.population.get(0).getFirstElement());
			System.out.println("Points:"+gp.population.get(0).getSecondElement());
			gp.evolvePopulation();
			Points.add(gp.population.get(0).getSecondElement());
			i++;
			}
		
		i--;//for knowing how many generations has run
		
		//BEGGINING OF TEST 
		//Number of generation
		System.out.println("Generation:");
		for(int j =0; j<=i; j++){
			System.out.println(j);
		}
		System.out.println("Points:");
		//Points
		for(int j =0; j<=i; j++){
			System.out.println(Points.get(j));
		}
		
		
		gp.printPopulation(10);
		
		//gp.printPopulation(25);*/
		
		
		
	
	}
}
