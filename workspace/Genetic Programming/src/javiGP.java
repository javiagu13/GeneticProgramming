import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class






public class javiGP {
	
	public class Tuple<X, Y> implements Cloneable  { 
		  public final X x; 
		  public final Y y; 
		  public Object clone() throws CloneNotSupportedException { 
			  return super.clone(); 
			} 
		  
		  public Tuple(X x, Y y) { 
		    this.x = x; 
		    this.y = y; 
		  } 
		  
		  public X getFirstElement(){
			  return this.x;
		  }
		  
		  public Y getSecondElement(){
			  return this.y;
		  }
	}
	
	private String initialDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	private String desiredDeckString = "[4c, 2h, 7d, 3c, 4h, 6d, 1s, 5h, 9s, 2s, 12h, 3d, 12c, 8h, 6s, 5s, 9h, 13c, 2d, 11h, 3s, 8s, 6h, 10c, 5d, 13d, 2c, 3h, 8d, 5c, 13s, 11d, 8c, 10s, 13h, 11c, 7s, 10h, 1d, 4s, 7h, 4d, 1c, 9c, 11s, 12d, 7c, 12s, 10d, 6c, 1h, 9d]";
	
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
	
	private int POPULATIONSIZE=10000; //Number of Population
	private int CROSSOVERNUMBER=5000; //Crossover Rate
	private int MUTATIONUMBER=1000; //Number of mutations 
	private int DEPTH=8; //DEPTH OF OPERATIONS!! tree size / 2
	
	private String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
	private String[] numbers=new String[MAX-1];
	
	private ArrayList<Tuple<ZuhaitzBitarra<String>, Integer>> population=new ArrayList<>();
	
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
			int deckDistance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, Individual);
			Tuple<ZuhaitzBitarra<String>, Integer> tuple =new Tuple<ZuhaitzBitarra<String>, Integer>(Individual, deckDistance);
			population.add(tuple);	//Hay que sacar el vector distance y añadirselo :)
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
	            case "insertfan": 
	                System.out.println("one"); 
	                break; 
	            case "insertfanup": 
	                System.out.println("two"); 
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
		 String randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
		 randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
	     String number=Integer.toString(randomIntBetween(this.MIN,this.MAX));
	     tree.mutation(randomOperation,number);
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
    	for(int i=0; i<this.POPULATIONSIZE;i++){
    		System.out.println("------"+i+"º TREE------");
    		this.population.get(i).getFirstElement().aurreordenInprimatu();
    		System.out.println("Distance: "+this.population.get(i).getSecondElement());
    	}
    	System.out.println("------END OF PRINTING POPULATION------");
    	System.out.println(population.get(0).getFirstElement());
    }
    
    public void printPopulation(int number){
    	for(int i=0; i<number;i++){
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
    
    public void evolvePopulation() throws CloneNotSupportedException{
    	//5 steps:
    	//1-we copy the population part that we are going to crossover (the CROSSOVERNUMBER of population)
    	
    	for(int i = 0; i<this.CROSSOVERNUMBER;i++){
    		Tuple tuple = new Tuple(null, null);
    		tuple=(Tuple)this.population.get(i).clone();
    		this.population.add(tuple); ////////////////////////////OJOOOOOO LO MAS SEGURO ES QUE SOLO COPIE LA DIRECCION DEL OBJETO NO EL OBJETO
    	}
    	
    	//2-we have to crossover trees randomly on the first CROSSOVERNUMBER positions (CROSSOVER/2 is because we are doing two trees at a time)
    	
    	for(int i = 0; i<this.CROSSOVERNUMBER/2;i++){
    		int firstNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		int secondNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		this.population.get(firstNum).getFirstElement().crossOverTrees(this.population.get(secondNum).getFirstElement()); //crossover operation
    		}
    	
    	//3-we have to mutate MUTATIONUMBER times on the CROSSOVERNUMBER part
    	for(int i = 0; i<this.MUTATIONUMBER;i++){
    		int randomNum=this.randomIntBetween(0, CROSSOVERNUMBER-1);
    		this.mutateDeck(this.population.get(randomNum).getFirstElement());
    		}
    	
    	//4-quicksort everything so that it gets back to normal
    	this.quickSortPopulation(0, this.population.size()-1);
    	
    	//5-delete the last elements of the population (the last CROSSOVERNUMBERS)
    	for (int i=this.population.size()-1; i>this.POPULATIONSIZE; i--)
    		this.population.remove(i);
    	
    }
    
	public static void main(String[] args) throws CloneNotSupportedException {
		javiGP gp = new javiGP();
		
		
		gp.initializeNumbersArray();
		gp.buildPopulation();
		//gp.evolvePopulation();
	
	
		
		gp.printPopulation(25);
		
		
		
	}
}
