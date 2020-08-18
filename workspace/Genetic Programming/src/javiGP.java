import java.util.Random;
import java.util.ArrayList;




public class javiGP {
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
	private int MIN=1; //Minimum number
	private int MAX=52; //Minimum number
	private int POPULATIONSIZE=1000;
	private int DEPTH=3; //DEPTH OF OPERATIONS!! tree size / 2
	
	private String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
	private String[] numbers=new String[MAX-1];
	
	private ArrayList<ZuhaitzBitarra<String>> population=new ArrayList<ZuhaitzBitarra<String>>();
	
	
	
	private int randomIntBetween(int min, int max){
		 	Random rand=new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
	}
	
	private void initializeNumbersArray(){
		for(int i=0; i<this.numbers.length-1;i++){numbers[i]=Integer.toString(i+1);}
	}
	
	private void buildPopulation(){
		for (int i = 0; i<this.POPULATIONSIZE-1;i++){
			ZuhaitzBitarra Individual = createIndividual();
			System.out.println(Individual.toString());
			System.out.println("--------------------");
			population.add(Individual);
		}
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
	
	public Deck applyIndividualToDeck(ZuhaitzBitarra<String> Individual, Deck deck){
		System.out.println(Individual.inOrdenList().toString());
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
		deck.printCurrentDeck();
		return deck;
	}
	
	public void applyPopulationToDeck(Deck deck){
		for(int i=0; i<this.population.size()-1;i++){
			applyIndividualToDeck(population.get(i),deck);
		}
	}
	
	public static void main(String[] args) {
		javiGP gp = new javiGP();
		Deck deck = new Deck();
		gp.initializeNumbersArray();
		gp.buildPopulation();
		gp.applyPopulationToDeck(deck);
		
	}
}
