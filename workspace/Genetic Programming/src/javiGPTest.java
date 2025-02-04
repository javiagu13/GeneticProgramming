import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class






public class javiGPTest {
	
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
	
	private static String initialDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
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
	
	private int MUTATION=300; //Number of mutations (technique+number)
	private int MUTATIONUMBER=0; //Number of mutations (technique+number) REVISE CODEEEEEE
	private int MUTATIONOPERATION=0; //Number of mutations (technique+number) REVISE CODEEEEEE
	
	private int NUMPARAMOPTIMIZATION=10; //number of times each trees parameters will be tweaked
	
	private int DEPTH=4; //INITIAL DEPTH OF TREES!! tree size / 2
	private int LENGTHMAXOFTREE=20*2;
	private String DISTANCE="HAMMING"; //SEQUENCE, HAMMING, HAMMING-SEQUENCE
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
    	
    	//AQUI
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
    	
    	//AQUI
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

    
	public static void main(String[] args) throws CloneNotSupportedException {		
		
		javiGPTest gp = new javiGPTest();
		gp.initializeNumbersArray();
		//String Deck4 = "[2s, 1c, 11c, 12h, 10d, 9d, 9h, 13h, 3c, 8d, 8s, 6s, 13c, 3d, 7d, 10c, 9s, 1s, 6c, 10h, 6h, 10s, 1d, 9c, 2c, 13s, 7h, 12s, 4h, 7s, 5d, 12d, 11h, 3h, 4s, 11s, 6d, 8c, 12c, 11d, 13d, 5h, 8h, 5c, 4d, 2d, 2h, 3s, 7c, 4c, 5s, 1h]";
		String deisredDeck="";
		String initialDeck="";
		
		//gp.initialDeckString= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		gp.initialDeckString=  "[b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r, b, r]";
		gp.desiredDeckString= "[b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r, r]";
		
		//gp.initialDeckString=  "[-, 1s, -, -, -, -, -, -, -, -, -, 4s, -, 3s, -, -, -, -, -, 2s, -, -, -, -, 5s, -, 6s, -, -, -, 8s, -, 7s, -, -, 9s, -, -, -, -, 10s, 11s, -, -, -, 13s, -, 12s, -, -, -, -]";
		//gp.desiredDeckString= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +, +]";
		//gp.desiredDeckString=  "[4c, 2h, 7d, 3c, 4h, 6d, 1s, 5h, 9s, 2s, 12h, 3d, 12c, 8h, 6s, 5s, 9h, 13c, 2d, 11h, 3s, 8s, 6h, 10c, 5d, 13d, 2c, 3h, 8d, 5c, 13s, 11d, 8c, 10s, 13h, 11c, 7s, 10h, 1d, 4s, 7h, 4d, 1c, 9c, 11s, 12d, 7c, 12s, 10d, 6c, 1h, 9d]";
		//gp.initialDeckString="[6h, 9s, 12d, 2c, 5h, 8s, 11d, 1c, 4h, 7s, 10d, 13c, 3h, 6s, 9d, 12c, 2h, 5s, 8d, 11c, 1h, 4s, 7d, 10c, 13h, 3s, 6d, 9c, 12h, 2s, 5d, 8c, 11h, 1s, 4d, 7c, 10h, 13s, 3d, 6c, 9h, 12s, 2d, 5c, 8h, 11s, 1d, 4c, 7h, 10s, 13d, 3c]";
		
		gp.buildPopulation();
		
		int j = 0;
		int iterations=1000;
			
		//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
		while(gp.population.get(0).getSecondElement()<510&&j<iterations){
			System.out.println("----------"+j+". GENERATION...-----------");
			System.out.println(gp.population.get(0).getFirstElement());
			Deck deck = new Deck(javiGPTest.initialDeckString);
    		Deck finaldeck = gp.applyIndividualToDeck(gp.population.get(0).getFirstElement(),deck);
    		finaldeck.printCurrentDeck();
			System.out.println("Points:"+gp.population.get(0).getSecondElement());
			gp.evolvePopulation();
			//Points1n2.add(gp.population.get(0).getSecondElement());
			j++;
		}
		gp.printPopulation(3);		
		
	
	}
}
