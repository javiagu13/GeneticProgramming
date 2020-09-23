import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<String> CurrentDeckBackup = new ArrayList<String>(); 
	private ArrayList<String> CurrentDeckOrder = new ArrayList<String>(); 
	
	//private ArrayList<ArrayList<String>> OperationsArray = new ArrayList<ArrayList<String>>(); 
	
	
	private ArrayList<ArrayList<String>> combinationMatrix = new ArrayList<ArrayList<String>>(); 
	
	public Deck(){
		//CurrentDeckOrder
		
		
		//Adding Spades (Picas)
		this.CurrentDeckOrder.add("1s");
		this.CurrentDeckOrder.add("2s");
		this.CurrentDeckOrder.add("3s");
		this.CurrentDeckOrder.add("4s");
		this.CurrentDeckOrder.add("5s");
		this.CurrentDeckOrder.add("6s");
		this.CurrentDeckOrder.add("7s");
		this.CurrentDeckOrder.add("8s");
		this.CurrentDeckOrder.add("9s");
		this.CurrentDeckOrder.add("10s");
		this.CurrentDeckOrder.add("11s");
		this.CurrentDeckOrder.add("12s");
		this.CurrentDeckOrder.add("13s");
		
		//Adding Hearts (Corazones)
		this.CurrentDeckOrder.add("1h");
		this.CurrentDeckOrder.add("2h");
		this.CurrentDeckOrder.add("3h");
		this.CurrentDeckOrder.add("4h");
		this.CurrentDeckOrder.add("5h");
		this.CurrentDeckOrder.add("6h");
		this.CurrentDeckOrder.add("7h");
		this.CurrentDeckOrder.add("8h");
		this.CurrentDeckOrder.add("9h");
		this.CurrentDeckOrder.add("10h");
		this.CurrentDeckOrder.add("11h");
		this.CurrentDeckOrder.add("12h");
		this.CurrentDeckOrder.add("13h");
		
		//Adding Diamonds (Diamantes)
		this.CurrentDeckOrder.add("13d");
		this.CurrentDeckOrder.add("12d");
		this.CurrentDeckOrder.add("11d");
		this.CurrentDeckOrder.add("10d");
		this.CurrentDeckOrder.add("9d");
		this.CurrentDeckOrder.add("8d");
		this.CurrentDeckOrder.add("7d");
		this.CurrentDeckOrder.add("6d");
		this.CurrentDeckOrder.add("5d");
		this.CurrentDeckOrder.add("4d");
		this.CurrentDeckOrder.add("3d");
		this.CurrentDeckOrder.add("2d");
		this.CurrentDeckOrder.add("1d");

		//Adding Clubs (Treboles)
		this.CurrentDeckOrder.add("13c");
		this.CurrentDeckOrder.add("12c");
		this.CurrentDeckOrder.add("11c");
		this.CurrentDeckOrder.add("10c");
		this.CurrentDeckOrder.add("9c");
		this.CurrentDeckOrder.add("8c");
		this.CurrentDeckOrder.add("7c");
		this.CurrentDeckOrder.add("6c");
		this.CurrentDeckOrder.add("5c");
		this.CurrentDeckOrder.add("4c");
		this.CurrentDeckOrder.add("3c");
		this.CurrentDeckOrder.add("2c");
		this.CurrentDeckOrder.add("1c");	
		
		//this.OperationsArray.add(this.CurrentDeckOrder);
		doBackupOfDeck();
		System.out.println("Initial Deck Order: "+this.CurrentDeckOrder.toString());
		System.out.println("Initial Backup Order: "+this.CurrentDeckBackup.toString());
		System.out.println("****************************************PROGRAM BEGINS*****************************************");
		System.out.println("");
	}
	
	public Deck(String initialDeck){
		
		//Initial Deck Building From String
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<initialDeck.length(); i++){
			if((initialDeck.charAt(i)==',')||(initialDeck.charAt(i)==']')){
				String card = sb.toString();
				this.CurrentDeckOrder.add(card);
				sb = new StringBuilder();
			}
			else if((initialDeck.charAt(i)=='[')||(initialDeck.charAt(i)==' ')){}
			else{
				char character = initialDeck.charAt(i);
				sb.append(character);
			}			
		}
		
		doBackupOfDeck();
		/*System.out.println("Initial Deck Order: "+this.CurrentDeckOrder.toString());
		System.out.println("Initial Backup Order: "+this.CurrentDeckBackup.toString());
		System.out.println("****************************************PROGRAM BEGINS*****************************************");
		System.out.println("");*/
		
	}
	
	//Cut [1-51] c N
	public void cutCurrentDeck(int N){
		if(N<1){/*System.out.println("while cutting N cannot be < 1");*/}
		else if(N>=52){/*System.out.println("while cutting N cannot be equal or > 52");*/}
		else{
			for(int i=0; i<N; i++){
				String aux = this.CurrentDeckOrder.remove(0);
				this.CurrentDeckOrder.add(aux);
			}
		}
	}
	
	//SlipCut [1-51] c N
	public void slipCutCurrentDeck(int N){
		if(N<1){/*System.out.println("while slip cutting N cannot be < 1");*/}
		else if(N>=52){/*System.out.println("while slip cutting N cannot be > 51");*/}
		else if(N==1){/*System.out.println("while slip cutting N cannot be 1");*/}
		else{
			/*System.out.println("Applying SlipCut N="+N+"...");*/
			String card = this.CurrentDeckOrder.remove(0);
			this.CurrentDeckOrder.add(N-1, card);
			
		}
	}	
	
	//Group of Cards Fanning [2-15] c Amount = Number of cards to be cutted  [Amount+1-51]
	public void insertFanCurrentDeck(int Amount, int N){
		InvertCurrentDeckOrder();
		if(N<1){/*System.out.println("while inserting Fan cutting N cannot be < 1");*/}
		else if(N>=52){/*System.out.println("while inserting Fan N cannot be > 51");*/}
		else if(N==1){/*System.out.println("while inserting Fan N cannot be 1");*/}
		else if(Amount>15||Amount<2){/*System.out.println("while inserting Fan Amount should be between 2 and 15");*/}
		else if(N<=Amount+1){/*N should be greater than Amount*/}
		else{
			/*System.out.println("Applying SlipCut N="+N+"...");*/
			for (int i = 0; i<Amount; i++){
				slipCutCurrentDeck(N);
			}
			
		}
		InvertCurrentDeckOrder();
	}	

	//SlipCut [1-51] c N
	public void faceUpSlipCutCurrentDeck(int N){
			InvertCurrentDeckOrder();
			if(N<1){/*System.out.println("while slip cutting N cannot be < 1");*/}
			else if(N>=52){/*System.out.println("while slip cutting N cannot be > 51");*/}
			else if(N==1){/*System.out.println("while slip cutting N cannot be 1");*/}
			else{
				/*System.out.println("Applying SlipCut N="+N+"...");*/
				String card = this.CurrentDeckOrder.remove(0);
				this.CurrentDeckOrder.add(N-1, card);
				
			}
			InvertCurrentDeckOrder();
		}	
		
	//Group of Cards Fanning [2-15] c Amount = Number of cards to be cutted  [Amount+1-51]
	public void faceUpInsertFanCurrentDeck(int Amount, int N){
			InvertCurrentDeckOrder();
			if(N<1){/*System.out.println("while inserting Fan cutting N cannot be < 1");*/}
			else if(N>=52){/*System.out.println("while inserting Fan N cannot be > 51");*/}
			else if(N==1){/*System.out.println("while inserting Fan N cannot be 1");*/}
			else if(Amount>15||Amount<2){/*System.out.println("while inserting Fan Amount should be between 2 and 15");*/}
			else if(N<=Amount+1){/*N should be greater than Amount*/}
			else{
				/*System.out.println("Applying SlipCut N="+N+"...");*/
				for (int i = 0; i<Amount; i++){
					slipCutCurrentDeck(N);
				}
				
			}
			InvertCurrentDeckOrder();
		}		

	//Peal [2-52] c N
	public void pealCurrentDeck(int N){
	if(N<1){/*System.out.println("while pealing N cannot be < 1");*/}
	else if(N>52){/*System.out.println("while pealing N cannot be > 52");*/}
	else if(N==1){/*System.out.println("while pealing N cannot be 1");*/}
	else if(N==2){
		String aux = this.CurrentDeckOrder.get(1);
		this.CurrentDeckOrder.set(1, this.CurrentDeckOrder.get(0));
		this.CurrentDeckOrder.set(0, aux);
	}
	else{
		/*System.out.println("Applying pealing N="+N+"...");*/
		int pos1=0;
		int pos2=N-1;
		
		while (pos1<=((N-1)/2)){
			String aux = this.CurrentDeckOrder.get(pos2);
			this.CurrentDeckOrder.set(pos2, this.CurrentDeckOrder.get(pos1));
			this.CurrentDeckOrder.set(pos1, aux);
			pos1++;
			pos2--;
		}
		
	}
}

	//Peal [2-52] c N
	public void faceUpPealCurrentDeck(int N){
		InvertCurrentDeckOrder();
		if(N<1){/*System.out.println("while pealing N cannot be < 1");*/}
		else if(N>52){/*System.out.println("while pealing N cannot be > 52");*/}
		else if(N==1){/*System.out.println("while pealing N cannot be 1");*/}
		else if(N==2){
			String aux = this.CurrentDeckOrder.get(1);
			this.CurrentDeckOrder.set(1, this.CurrentDeckOrder.get(0));
			this.CurrentDeckOrder.set(0, aux);
		}
		else{
			/*System.out.println("Applying pealing N="+N+"...");*/
			int pos1=0;
			int pos2=N-1;
			
			while (pos1<=((N-1)/2)){
				String aux = this.CurrentDeckOrder.get(pos2);
				this.CurrentDeckOrder.set(pos2, this.CurrentDeckOrder.get(pos1));
				this.CurrentDeckOrder.set(pos1, aux);
				pos1++;
				pos2--;
			}
			
		}
		InvertCurrentDeckOrder();
	}

	//Faro [10-42] c N
	public void inPartialFaroCurrentDeck(int N){
		if(N<10){/*System.out.println("while doing out faro N cannot be < 10");*/}
		else if(N>42){/*System.out.println("while doing out faro N cannot be > 42");*/}
		else{
			/*System.out.println("Applying out partial faro N="+N+"...");*/
			int cuttingPos = N-1;
			ArrayList<String> leftPacket = new ArrayList<String>(); 
			ArrayList<String> rightPacket = new ArrayList<String>(); 
			for(int i=0; i<this.CurrentDeckOrder.size(); i++){
				if(i<=cuttingPos){
					rightPacket.add(CurrentDeckOrder.get(i));
				}
				else{
					leftPacket.add(CurrentDeckOrder.get(i));
				}
			}
			rightPacket.add(null);
			leftPacket.add(null);
			
			for(int i=0; i<(this.CurrentDeckOrder.size()/2); i++){
				if(rightPacket.get(i)==null){
					int currentDeckPos=i*2;
					for(int j=i; j<leftPacket.size()-1; j++){
						CurrentDeckOrder.set(currentDeckPos, leftPacket.get(j));
						currentDeckPos++;
					}
					break;
				}
				else if(leftPacket.get(i)==null){
					int currentDeckPos=i*2;
					for(int j=i; j<rightPacket.size()-1; j++){
						CurrentDeckOrder.set(currentDeckPos, rightPacket.get(j));
						currentDeckPos++;
					}
					break;
				}
				else{
					CurrentDeckOrder.set(i*2, leftPacket.get(i));
					CurrentDeckOrder.set((i*2)+1, rightPacket.get(i));
				}
			}
		}
	}

	//Faro [10-42] c N
	public void faceUpInPartialFaroCurrentDeck(int N){
		InvertCurrentDeckOrder();
		if(N<10){/*System.out.println("while doing out faro N cannot be < 10");*/}
		else if(N>42){/*System.out.println("while doing out faro N cannot be > 42");*/}
		else{
			/*System.out.println("Applying out partial faro N="+N+"...");*/
			int cuttingPos = N-1;
			ArrayList<String> leftPacket = new ArrayList<String>(); 
			ArrayList<String> rightPacket = new ArrayList<String>(); 
			for(int i=0; i<this.CurrentDeckOrder.size(); i++){
				if(i<=cuttingPos){
					rightPacket.add(CurrentDeckOrder.get(i));
				}
				else{
					leftPacket.add(CurrentDeckOrder.get(i));
				}
			}
			rightPacket.add(null);
			leftPacket.add(null);
			
			for(int i=0; i<(this.CurrentDeckOrder.size()/2); i++){
				if(rightPacket.get(i)==null){
					int currentDeckPos=i*2;
					for(int j=i; j<leftPacket.size()-1; j++){
						CurrentDeckOrder.set(currentDeckPos, leftPacket.get(j));
						currentDeckPos++;
					}
					break;
				}
				else if(leftPacket.get(i)==null){
					int currentDeckPos=i*2;
					for(int j=i; j<rightPacket.size()-1; j++){
						CurrentDeckOrder.set(currentDeckPos, rightPacket.get(j));
						currentDeckPos++;
					}
					break;
				}
				else{
					CurrentDeckOrder.set((i*2)+1, rightPacket.get(i));
					CurrentDeckOrder.set(i*2, leftPacket.get(i));
				}
			}
		}
		InvertCurrentDeckOrder();
	}

	//Faro [10-42] c N
	public void outPartialFaroCurrentDeck(int N){
	if(N<10){/*System.out.println("while doing out faro N cannot be < 10");*/}
	else if(N>42){/*System.out.println("while doing out faro N cannot be > 42");*/}
	else{
		/*System.out.println("Applying out partial faro N="+N+"...");*/
		int cuttingPos = N-1;
		ArrayList<String> leftPacket = new ArrayList<String>(); 
		ArrayList<String> rightPacket = new ArrayList<String>(); 
		for(int i=0; i<this.CurrentDeckOrder.size(); i++){
			if(i<=cuttingPos){
				rightPacket.add(CurrentDeckOrder.get(i));
			}
			else{
				leftPacket.add(CurrentDeckOrder.get(i));
			}
		}
		rightPacket.add(null);
		leftPacket.add(null);
		
		for(int i=0; i<(this.CurrentDeckOrder.size()/2); i++){
			if(rightPacket.get(i)==null){
				int currentDeckPos=i*2;
				for(int j=i; j<leftPacket.size()-1; j++){
					CurrentDeckOrder.set(currentDeckPos, leftPacket.get(j));
					currentDeckPos++;
				}
				break;
			}
			else if(leftPacket.get(i)==null){
				int currentDeckPos=i*2;
				for(int j=i; j<rightPacket.size()-1; j++){
					CurrentDeckOrder.set(currentDeckPos, rightPacket.get(j));
					currentDeckPos++;
				}
				break;
			}
			else{
				CurrentDeckOrder.set(i*2, rightPacket.get(i));
				CurrentDeckOrder.set((i*2)+1, leftPacket.get(i));
			}
		}
	}
}

	//Faro [10-42] c N
	public void faceUpOutPartialFaroCurrentDeck(int N){
		InvertCurrentDeckOrder();
		if(N<10){/*System.out.println("while doing out faro N cannot be < 10");*/}
		else if(N>42){/*System.out.println("while doing out faro N cannot be > 42");*/}
		else{
			/*System.out.println("Applying out partial faro N="+N+"...");*/
			int cuttingPos = N-1;
			ArrayList<String> leftPacket = new ArrayList<String>(); 
			ArrayList<String> rightPacket = new ArrayList<String>(); 
			for(int i=0; i<this.CurrentDeckOrder.size(); i++){
				if(i<=cuttingPos){
					rightPacket.add(CurrentDeckOrder.get(i));
				}
				else{
					leftPacket.add(CurrentDeckOrder.get(i));
				}
			}
			rightPacket.add(null);
			leftPacket.add(null);
			
			for(int i=0; i<(this.CurrentDeckOrder.size()/2); i++){
				if(rightPacket.get(i)==null){
					int currentDeckPos=i*2;
					for(int j=i; j<leftPacket.size()-1; j++){
						CurrentDeckOrder.set(currentDeckPos, leftPacket.get(j));
						currentDeckPos++;
					}
					break;
				}
				else if(leftPacket.get(i)==null){
					int currentDeckPos=i*2;
					for(int j=i; j<rightPacket.size()-1; j++){
						CurrentDeckOrder.set(currentDeckPos, rightPacket.get(j));
						currentDeckPos++;
					}
					break;
				}
				else{
					CurrentDeckOrder.set(i*2, rightPacket.get(i));
					CurrentDeckOrder.set((i*2)+1, leftPacket.get(i));
				}
			}
		}
		InvertCurrentDeckOrder();
	}
	
	
	

	//AUXILIAR FUNCTIONS//////////////////////////////////////////////////
	//Invert Deck Order
	private void InvertCurrentDeckOrder(){
			int N=this.CurrentDeckOrder.size();
			if(N<1){/*System.out.println("while pealing N cannot be < 1");*/}
			else if(N>52){/*System.out.println("while pealing N cannot be > 52");*/}
			else if(N==1){/*System.out.println("while pealing N cannot be 1");*/}
			else if(N==2){
				String aux = this.CurrentDeckOrder.get(1);
				this.CurrentDeckOrder.set(1, this.CurrentDeckOrder.get(0));
				this.CurrentDeckOrder.set(0, aux);
			}
			else{
				/*System.out.println("Applying pealing N="+N+"...");*/
				int pos1=0;
				int pos2=N-1;
				
				while (pos1<=((N-1)/2)){
					String aux = this.CurrentDeckOrder.get(pos2);
					this.CurrentDeckOrder.set(pos2, this.CurrentDeckOrder.get(pos1));
					this.CurrentDeckOrder.set(pos1, aux);
					pos1++;
					pos2--;
				}
				
			}
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
	
	public void printCurrentDeck(){
		String currentCurrentDeckOrder=this.CurrentDeckOrder.toString();
		System.out.println("Current Deck Order: "+currentCurrentDeckOrder);
	}
	
	public void printBackupDeck(){
		String currentCurrentDeckOrder=this.CurrentDeckBackup.toString();
		System.out.println("Backup Deck Order: "+currentCurrentDeckOrder);
	}

	public void doBackupOfDeck(){
		this.CurrentDeckBackup=  (ArrayList<String>) this.CurrentDeckOrder.clone();
	}
	
	public void doDeckToBackupState(){
		this.CurrentDeckOrder=  (ArrayList<String>) this.CurrentDeckBackup.clone();
	}
	
	public boolean currentEqualToFinalDeck(String finalDeck){
		return this.CurrentDeckOrder.toString().equals(finalDeck);
	}
	public boolean currentEqualToFinalDeck(ArrayList finalDeck){
		return this.CurrentDeckOrder.toString().equals(finalDeck.toString());
	}
	
	public int vectorDistance(ArrayList finalDeck){
		int i=0; int j=0; int points=0;
		while (i<this.CurrentDeckOrder.size()){ //Posiblemente este mal y haya que hacer -1
			while (j<finalDeck.size()){ //Posiblemente este mal y haya que hacer -1
				if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI PENALIZAS
					i++; j++;
					while (i<this.CurrentDeckOrder.size()&&j<finalDeck.size()){
						if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI SUMAS PUNTOS
							i++; j++;
							points = points+10;
						}
						else{break;}
					}
					//j=0;
					break;
				}
				else{j++;}
			}
			j=0;
		}
		return points;
	}
	
	public ArrayList<String> getCurrentDeckOrder() {
		return CurrentDeckOrder;
	}

	public void setCurrentDeckOrder(ArrayList<String> currentDeckOrder) {
		CurrentDeckOrder = currentDeckOrder;
	}

	public static void main(String[] args) {
		Deck Baraja = new Deck();
		ArrayList<String> finalDeck = Baraja.stringToArrayListDeck("[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]");
		Baraja.setCurrentDeckOrder(finalDeck);
		Baraja.printCurrentDeck();
		
		/*Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.pealCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(18);
		Baraja.cutCurrentDeck(9);*/
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.pealCurrentDeck(40);
		Baraja.outPartialFaroCurrentDeck(15);
		Baraja.outPartialFaroCurrentDeck(5);
		Baraja.faceUpInPartialFaroCurrentDeck(20);
		//Baraja.outPartialFaroCurrentDeck(26);
		Baraja.printCurrentDeck();
		//System.out.println(Baraja.vectorDistance(finalDeck));
		
		/*Baraja.inPartialFaroCurrentDeck(23);
		Baraja.cutCurrentDeck(35);
		Baraja.printCurrentDeck();*/
		
		/*Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.pealCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(18);
		Baraja.cutCurrentDeck(9);
		Baraja.printCurrentDeck();*/
		}
}
