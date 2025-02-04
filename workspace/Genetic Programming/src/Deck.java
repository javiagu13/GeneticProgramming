import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
		System.out.println("String Deck= \""+currentCurrentDeckOrder+"\";");
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
	
	/*public int vectorDistance(ArrayList finalDeck){
		int i=0; int j=0; int points=0;
		while (j<finalDeck.size()){ //Posiblemente este mal y haya que hacer -1
			if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI PENALIZAS
				i++; j++;
				while (i<this.CurrentDeckOrder.size()&&j<finalDeck.size()){
					if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI SUMAS PUNTOS
						i++; j++;
						points = points+10;
					}
					else{i=0;break;}
				}
			}
			i++;j++;
		}
		return points;
	}*/
	
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
	public int vectorDistanceWithBackupDeck(ArrayList finalDeck){
		int i=0; int j=0; int points=0;
		while (j<finalDeck.size()){ //Posiblemente este mal y haya que hacer -1
			if (this.CurrentDeckBackup.get(i).equals(finalDeck.get(j))){//AQUI PENALIZAS
				i++; j++;
				while (i<this.CurrentDeckBackup.size()&&j<finalDeck.size()){
					if (this.CurrentDeckBackup.get(i).equals(finalDeck.get(j))){//AQUI SUMAS PUNTOS
						i++; j++;
						points = points+10;
					}
					else{break;}
				}
			}
			i++;j++;
		}
		return points;
	}
	
	public int hammingDistance(ArrayList finalDeck){
		int points=0;
		for(int i=0;i<this.CurrentDeckOrder.size();i++){
			if(this.CurrentDeckOrder.get(i).equals(finalDeck.get(i))){
				points=points+10;
			}
		}
		return points-10;
	}
	
	public int hammingDistanceWithBackupDeck(ArrayList finalDeck){
		int points=0;
		for(int i=0;i<this.CurrentDeckBackup.size();i++){
			if(this.CurrentDeckBackup.get(i).equals(finalDeck.get(i))){
				points=points+10;
			}
		}
		return points-10;
	}
	
	public int MixedDistanceSequenceHamming(ArrayList finalDeck){
		int i=0; int j=0; int pointsSequence=0;
		while (i<this.CurrentDeckOrder.size()){ //Posiblemente este mal y haya que hacer -1
			while (j<finalDeck.size()){ //Posiblemente este mal y haya que hacer -1
				if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI PENALIZAS
					i++; j++;
					while (i<this.CurrentDeckOrder.size()&&j<finalDeck.size()){
						if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI SUMAS PUNTOS
							i++; j++;
							pointsSequence = pointsSequence+5;
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
		
		int pointsHamming=0;
		for(int k=0;k<this.CurrentDeckOrder.size();k++){
			if(this.CurrentDeckOrder.get(k).equals(finalDeck.get(k))){
				pointsHamming=pointsHamming+5;
			}
		}
		return pointsSequence+pointsHamming-5;
	}
	
	/*public int MixedDistanceSequenceHamming(ArrayList finalDeck){
		int i=0; int j=0; int pointsSequence=0;
		while (j<finalDeck.size()){ //Posiblemente este mal y haya que hacer -1
			if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI PENALIZAS
				i++; j++;
				while (i<this.CurrentDeckOrder.size()&&j<finalDeck.size()){
					if (this.CurrentDeckOrder.get(i).equals(finalDeck.get(j))){//AQUI SUMAS PUNTOS
						i++; j++;
						pointsSequence = pointsSequence+5;
					}
					else{break;}
				}
			}
			i++;j++;
		}
		
		int pointsHamming=0;
		for(int k=0;k<this.CurrentDeckOrder.size();k++){
			if(this.CurrentDeckOrder.get(k).equals(finalDeck.get(k))){
				pointsHamming=pointsHamming+5;
			}
		}
		return pointsSequence+pointsHamming-5;
	}*/
	
	public int MixedDistanceSequenceHammingWithBackupDeck(ArrayList finalDeck){
		int i=0; int j=0; int pointsSequence=0;
		while (i<this.CurrentDeckBackup.size()){ //Posiblemente este mal y haya que hacer -1
			while (j<finalDeck.size()){ //Posiblemente este mal y haya que hacer -1
				if (this.CurrentDeckBackup.get(i).equals(finalDeck.get(j))){//AQUI PENALIZAS
					i++; j++;
					while (i<this.CurrentDeckBackup.size()&&j<finalDeck.size()){
						if (this.CurrentDeckBackup.get(i).equals(finalDeck.get(j))){//AQUI SUMAS PUNTOS
							i++; j++;
							pointsSequence = pointsSequence+5;
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
		
		int pointsHamming=0;
		for(int k=0;k<this.CurrentDeckBackup.size();k++){
			if(this.CurrentDeckBackup.get(k).equals(finalDeck.get(k))){
				pointsHamming=pointsHamming+5;
			}
		}
		return pointsSequence+pointsHamming-5;
	}
	
	public ArrayList<String> getCurrentDeckOrder() {
		return CurrentDeckOrder;
	}

	public void setCurrentDeckOrder(ArrayList<String> currentDeckOrder) {
		CurrentDeckOrder = currentDeckOrder;
	}
	
	private int randomIntBetween(int min, int max){
	 	Random rand=new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void singleTechPrintings(){
		int counter=1;
		String numString=""; 
		
		//CUT
		System.out.println("//String technique = \"CUT\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//PEAL
		System.out.println("//String technique = \"PEAL\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString="";
		
		//PEALUP
		System.out.println("//String technique = \"PEALUP\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(1,52);
				this.faceUpPealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//SLIPCUT
		System.out.println("//String technique = \"SLIPCUT\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//SLIPCUTUP
		System.out.println("//String technique = \"SLIPCUTUP\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(1,52);
				this.faceUpSlipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INFARO
		System.out.println("//String technique = \"INFARO\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INFAROUP
		System.out.println("//String technique = \"INFAROUP\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(10,42);
				this.faceUpInPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		
		//OUTFARO
		System.out.println("//String technique = \"OUTFARO\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(10,42);
				this.outPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//OUTFAROUP
		System.out.println("//String technique = \"OUTFAROUP\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i;j++){
				int num = randomIntBetween(10,42);
				this.faceUpOutPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();		
	}
	
	public void doubleTechPrintings(){
		int counter=1;
		String numString=""; 
		
		//CUTTING-INSERTION
		System.out.println("//String technique = \"CUTTING-INSERTION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 	
		
		//CUTTING-INTERCALATION
		System.out.println("//String technique = \"CUTTING-INTERCALATION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 	
		
		//CUTTING-INVERSION
		System.out.println("//String technique = \"CUTTING-INVERSION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 	
		
		
		//INSERTION-CUTTING
		System.out.println("//String technique = \"INSERTION-CUTTING\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		
		
		//INSERTION-INTERCALATION
		System.out.println("//String technique = \"INSERTION-INTERCALATION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		
		//INSERTION-INVERSION
		System.out.println("//String technique = \"INSERTION-INVERSION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		
		//INTERCALATION-CUTTING
		System.out.println("//String technique = \"INTERCALATION-CUTTING\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INTERCALATION-INSERTION
		System.out.println("//String technique = \"INTERCALATION-INSERTION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INTERCALATION-INVERSION
		System.out.println("//String technique = \"INTERCALATION-INVERSION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INVERSION-CUTTING
		System.out.println("//String technique = \"INVERSION-CUTTING\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.cutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INVERSION-INSERTION
		System.out.println("//String technique = \"INVERSION-INSERTION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(1,52);
				this.slipCutCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		//INVERSION-INTERCALATION
		System.out.println("//String technique = \"INVERSION-INTERCALATION\";");
		for(int i=2; i<=32; i=i*2){
			for(int j=1;j<=i/2;j++){
				//FIRST TECH
				int num = randomIntBetween(1,52);
				this.pealCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
				//SECOND TECH
				num = randomIntBetween(10,42);
				this.inPartialFaroCurrentDeck(num);
				numString=numString+" "+Integer.toString(num);
			}
			System.out.println("//N="+i+"  ("+numString+" )");
			System.out.print("//String Deck"+counter+" = \"");
			System.out.print(this.CurrentDeckOrder.toString());
			System.out.println("\";");
			counter++;
			numString=""; 
			this.doDeckToBackupState();
		}
		this.doDeckToBackupState();
		System.out.println("");
		System.out.println("");
		counter=1;
		numString=""; 
		
		
	}
	
	public void metricAnalyzerN4(ArrayList<Integer> techniqueSet, String technique){
		try {
			int cont=0;
		FileWriter csvWriter;
		csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//MetricAnalysis//N4.csv");
		ArrayList<Integer> currentTechniques= new ArrayList<>();
		String linePrinter = "";
		int i=4;
		int j=0;
		while (i<techniqueSet.size()+4){
			for(;j<i;j++){//we separate the log techniqueSet in smaller ones
				currentTechniques.add(techniqueSet.get(j));
			}
			
			//N=4 we will try all combinations on the given techniques
			for(int a=0; a<52; a++){
				for(int b=0; b<52; b++){
					for(int c=0; c<52; c++){
						for(int d=0; d<52; d++){
							outerloop:
							for(int e=0; e<currentTechniques.size();e++){//we are looking at the current techniques to apply the correspondent numbers afterwards
								switch(currentTechniques.get(e)){
								case 0://CUT
									linePrinter+="cut"+",";
									if(e==0&&a>=1&&a<=52){cutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=1&&b<=52){cutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=1&&c<=52){cutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=1&&d<=52){cutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 1://SLIPCUT
									linePrinter+="slipcut"+",";
									if(e==0&&a>=2&&a<=52){slipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=2&&b<=52){slipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=2&&c<=52){slipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=2&&d<=52){slipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 2://SLIPCUTUP			
									linePrinter+="slipcut"+",";
									if(e==0&&a>=2&&a<=52){faceUpSlipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=2&&b<=52){faceUpSlipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=2&&c<=52){faceUpSlipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=2&&d<=52){faceUpSlipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 3://PEAL			
									linePrinter+="peal"+",";
									if(e==0&&a>=2&&a<=52){pealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=2&&b<=52){pealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=2&&c<=52){pealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=2&&d<=52){pealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 4://PEALUP		
									linePrinter+="pealup"+",";
									if(e==0&&a>=2&&a<=52){faceUpPealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=2&&b<=52){faceUpPealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=2&&c<=52){faceUpPealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=2&&d<=52){faceUpPealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 5://INFARO
									linePrinter+="infaro"+",";
									if(e==0&&a>=10&&a<=42){inPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=10&&b<=42){inPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=10&&c<=42){inPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=10&&d<=42){inPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 6://INFAROUP		
									linePrinter+="infaroup"+",";
									if(e==0&&a>=10&&a<=42){faceUpInPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=10&&b<=42){faceUpInPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=10&&c<=42){faceUpInPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=10&&d<=42){faceUpInPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 7://OUTFARO	
									linePrinter+="outfaro"+",";
									if(e==0&&a>=10&&a<=42){outPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=10&&b<=42){outPartialFaroCurrentDeck(b);linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=10&&c<=42){outPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=10&&d<=42){outPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								case 8://OUTFAROUP
									linePrinter+="outfaroup"+",";
									if(e==0&&a>=10&&a<=42){faceUpOutPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
									else if(e==1&&b>=10&&b<=42){faceUpOutPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
									else if(e==2&&c>=10&&c<=42){faceUpOutPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
									else if(e==3&&d>=10&&d<=42){faceUpOutPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
									else{doDeckToBackupState(); linePrinter=""; break outerloop;}
									break;
								}
							}
							//aplicar metrica aqui para obtener resultados de la combinación actual
							if(technique.equals("SEQUENCE")&&!linePrinter.equals("")){linePrinter+=Integer.toString(vectorDistanceWithBackupDeck(this.CurrentDeckOrder));}
							else if(technique.equals("HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(hammingDistance(this.CurrentDeckOrder));}
							else if(technique.equals("SEQUENCE-HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(MixedDistanceSequenceHamming(this.CurrentDeckOrder));}
							if(!linePrinter.equals("")){if(cont==1000000||cont==3000000||cont==10000000||cont==20000000||cont==30000000||cont==40000000||cont==50000000){System.out.println(cont);}csvWriter.append(linePrinter+"\n");	cont++;}
							linePrinter="";
							doDeckToBackupState();
						}
					}
				}
			}
			
			
			currentTechniques.clear();
			i+=4;
			
		}
		csvWriter.flush();
 		csvWriter.close();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void metricAnalyzerN4LastPos(ArrayList<Integer> techniqueSet, Integer numberOfSametech, String metric, String technique){
		try {
		Deck modifiedDeck=new Deck();
		switch(technique){
		case "CUT"://CUT
			modifiedDeck.cutCurrentDeck(numberOfSametech);
			modifiedDeck.cutCurrentDeck(numberOfSametech);
			modifiedDeck.cutCurrentDeck(numberOfSametech);
			modifiedDeck.cutCurrentDeck(numberOfSametech);
			break;
		case "SLIPCUT"://SLIPCUT
			modifiedDeck.slipCutCurrentDeck(numberOfSametech);
			modifiedDeck.slipCutCurrentDeck(numberOfSametech);
			modifiedDeck.slipCutCurrentDeck(numberOfSametech);
			modifiedDeck.slipCutCurrentDeck(numberOfSametech);
			break;
		case "SLIPCUTUP"://SLIPCUTUP			
			modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
			break;
		case "PEAL"://PEAL			
			modifiedDeck.pealCurrentDeck(numberOfSametech);
			modifiedDeck.pealCurrentDeck(numberOfSametech);
			modifiedDeck.pealCurrentDeck(numberOfSametech);
			modifiedDeck.pealCurrentDeck(numberOfSametech);
			break;
		case "PEALUP"://PEALUP		
			modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
			break;
		case "INFARO"://INFARO
			modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
			break;
		case "INFAROUP"://INFAROUP		
			modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
			break;
		case "OUTFARO"://OUTFARO	
			modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
			break;
		case "OUTFAROUP"://OUTFAROUP
			modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
			modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
			break;
		}
		
		int a=numberOfSametech;
		int b=numberOfSametech;
		int c=numberOfSametech;
		int cont=0;
		FileWriter csvWriter;
		csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//MetricAnalysis//N4LastPos"+technique+numberOfSametech+metric+".csv");
		csvWriter.append("maintained,technique,is,"+technique+",and,the,number,is,"+numberOfSametech+"\n");
		ArrayList<Integer> currentTechniques= new ArrayList<>();
		String linePrinter = "";
		int i=4;
		int j=0;
		while (i<techniqueSet.size()+4){
			for(;j<i;j++){//we separate the log techniqueSet in smaller ones
				currentTechniques.add(techniqueSet.get(j));
			}
			
			//N=4 we will try all combinations on the given techniques
			for(int d=0; d<52; d++){
				outerloop:
				for(int e=0; e<currentTechniques.size();e++){//we are looking at the current techniques to apply the correspondent numbers afterwards
					switch(currentTechniques.get(e)){
					case 0://CUT
						linePrinter+="cut"+",";
						if(e==0&&a>=1&&a<=52){cutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=1&&b<=52){cutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=1&&c<=52){cutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=1&&d<=52){cutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 1://SLIPCUT
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){slipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){slipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){slipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){slipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 2://SLIPCUTUP			
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){faceUpSlipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpSlipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpSlipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpSlipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 3://PEAL			
						linePrinter+="peal"+",";
						if(e==0&&a>=2&&a<=52){pealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){pealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){pealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){pealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 4://PEALUP		
						linePrinter+="pealup"+",";
						if(e==0&&a>=2&&a<=52){faceUpPealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpPealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpPealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpPealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 5://INFARO
						linePrinter+="infaro"+",";
						if(e==0&&a>=10&&a<=42){inPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){inPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){inPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){inPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 6://INFAROUP		
						linePrinter+="infaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpInPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpInPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpInPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpInPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 7://OUTFARO	
						linePrinter+="outfaro"+",";
						if(e==0&&a>=10&&a<=42){outPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){outPartialFaroCurrentDeck(b);linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){outPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){outPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 8://OUTFAROUP
						linePrinter+="outfaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpOutPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpOutPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpOutPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpOutPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					}
				}
				//aplicar metrica aqui para obtener resultados de la combinación actual
				if(metric.equals("SEQUENCE")&&!linePrinter.equals("")){linePrinter+=Integer.toString(vectorDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(hammingDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("SEQUENCE-HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(MixedDistanceSequenceHamming(modifiedDeck.getCurrentDeckOrder()));}
				if(!linePrinter.equals("")){if(cont==1000000||cont==3000000||cont==10000000||cont==20000000||cont==30000000||cont==40000000||cont==50000000){System.out.println(cont);}csvWriter.append(linePrinter+"\n");	cont++;}
				linePrinter="";
				doDeckToBackupState();
			}
			
			
			currentTechniques.clear();
			i+=4;
			
		}
		csvWriter.flush();
 		csvWriter.close();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void metricAnalyzerN4PrevToLastPos(ArrayList<Integer> techniqueSet, Integer numberOfSametech, String metric, String technique){
		try {
			Deck modifiedDeck=new Deck();
			switch(technique){
			case "CUT"://CUT
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				break;
			case "SLIPCUT"://SLIPCUT
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				break;
			case "SLIPCUTUP"://SLIPCUTUP			
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				break;
			case "PEAL"://PEAL			
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				break;
			case "PEALUP"://PEALUP		
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				break;
			case "INFARO"://INFARO
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "INFAROUP"://INFAROUP		
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "OUTFARO"://OUTFARO	
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "OUTFAROUP"://OUTFAROUP
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				break;
			}
		int a=numberOfSametech;
		int b=numberOfSametech;
		int d=numberOfSametech;
		int cont=0;
		FileWriter csvWriter;
		csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//MetricAnalysis//N4PrevToLastPos"+technique+numberOfSametech+metric+".csv");
		csvWriter.append("maintained,technique,is,"+technique+",and,the,number,is,"+numberOfSametech+"\n");
		ArrayList<Integer> currentTechniques= new ArrayList<>();
		String linePrinter = "";
		int i=4;
		int j=0;
		while (i<techniqueSet.size()+4){
			for(;j<i;j++){//we separate the log techniqueSet in smaller ones
				currentTechniques.add(techniqueSet.get(j));
			}
			
			//N=4 we will try all combinations on the given techniques
			for(int c=0; c<52; c++){
				outerloop:
				for(int e=0; e<currentTechniques.size();e++){//we are looking at the current techniques to apply the correspondent numbers afterwards
					switch(currentTechniques.get(e)){
					case 0://CUT
						linePrinter+="cut"+",";
						if(e==0&&a>=1&&a<=52){cutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=1&&b<=52){cutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=1&&c<=52){cutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=1&&d<=52){cutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 1://SLIPCUT
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){slipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){slipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){slipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){slipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 2://SLIPCUTUP			
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){faceUpSlipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpSlipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpSlipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpSlipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 3://PEAL			
						linePrinter+="peal"+",";
						if(e==0&&a>=2&&a<=52){pealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){pealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){pealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){pealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 4://PEALUP		
						linePrinter+="pealup"+",";
						if(e==0&&a>=2&&a<=52){faceUpPealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpPealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpPealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpPealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 5://INFARO
						linePrinter+="infaro"+",";
						if(e==0&&a>=10&&a<=42){inPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){inPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){inPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){inPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 6://INFAROUP		
						linePrinter+="infaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpInPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpInPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpInPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpInPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 7://OUTFARO	
						linePrinter+="outfaro"+",";
						if(e==0&&a>=10&&a<=42){outPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){outPartialFaroCurrentDeck(b);linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){outPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){outPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 8://OUTFAROUP
						linePrinter+="outfaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpOutPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpOutPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpOutPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpOutPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					}
				}
				//aplicar metrica aqui para obtener resultados de la combinación actual
				if(metric.equals("SEQUENCE")&&!linePrinter.equals("")){linePrinter+=Integer.toString(vectorDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(hammingDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("SEQUENCE-HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(MixedDistanceSequenceHamming(modifiedDeck.getCurrentDeckOrder()));}
				if(!linePrinter.equals("")){if(cont==1000000||cont==3000000||cont==10000000||cont==20000000||cont==30000000||cont==40000000||cont==50000000){System.out.println(cont);}csvWriter.append(linePrinter+"\n");	cont++;}
				linePrinter="";
				doDeckToBackupState();
			}
			
			
			currentTechniques.clear();
			i+=4;
			
		}
		csvWriter.flush();
 		csvWriter.close();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void metricAnalyzerN4FirstPos(ArrayList<Integer> techniqueSet, Integer numberOfSametech, String metric, String technique){
		try {
			Deck modifiedDeck=new Deck();
			switch(technique){
			case "CUT"://CUT
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				break;
			case "SLIPCUT"://SLIPCUT
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				break;
			case "SLIPCUTUP"://SLIPCUTUP			
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				break;
			case "PEAL"://PEAL			
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				break;
			case "PEALUP"://PEALUP		
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				break;
			case "INFARO"://INFARO
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "INFAROUP"://INFAROUP		
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "OUTFARO"://OUTFARO	
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "OUTFAROUP"://OUTFAROUP
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				break;
			}
		int b=numberOfSametech;
		int c=numberOfSametech;
		int d=numberOfSametech;
		int cont=0;
		FileWriter csvWriter;
		csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//MetricAnalysis//N4FirstPos"+technique+numberOfSametech+metric+".csv");
		csvWriter.append("maintained,technique,is,"+technique+",and,the,number,is,"+numberOfSametech+"\n");
		ArrayList<Integer> currentTechniques= new ArrayList<>();
		String linePrinter = "";
		int i=4;
		int j=0;
		while (i<techniqueSet.size()+4){
			for(;j<i;j++){//we separate the log techniqueSet in smaller ones
				currentTechniques.add(techniqueSet.get(j));
			}
			
			//N=4 we will try all combinations on the given techniques
			for(int a=0; a<52; a++){
				outerloop:
				for(int e=0; e<currentTechniques.size();e++){//we are looking at the current techniques to apply the correspondent numbers afterwards
					switch(currentTechniques.get(e)){
					case 0://CUT
						linePrinter+="cut"+",";
						if(e==0&&a>=1&&a<=52){cutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=1&&b<=52){cutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=1&&c<=52){cutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=1&&d<=52){cutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 1://SLIPCUT
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){slipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){slipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){slipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){slipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 2://SLIPCUTUP			
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){faceUpSlipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpSlipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpSlipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpSlipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 3://PEAL			
						linePrinter+="peal"+",";
						if(e==0&&a>=2&&a<=52){pealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){pealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){pealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){pealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 4://PEALUP		
						linePrinter+="pealup"+",";
						if(e==0&&a>=2&&a<=52){faceUpPealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpPealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpPealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpPealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 5://INFARO
						linePrinter+="infaro"+",";
						if(e==0&&a>=10&&a<=42){inPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){inPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){inPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){inPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 6://INFAROUP		
						linePrinter+="infaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpInPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpInPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpInPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpInPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 7://OUTFARO	
						linePrinter+="outfaro"+",";
						if(e==0&&a>=10&&a<=42){outPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){outPartialFaroCurrentDeck(b);linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){outPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){outPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 8://OUTFAROUP
						linePrinter+="outfaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpOutPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpOutPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpOutPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpOutPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					}
				}
				//aplicar metrica aqui para obtener resultados de la combinación actual
				if(metric.equals("SEQUENCE")&&!linePrinter.equals("")){linePrinter+=Integer.toString(vectorDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(hammingDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("SEQUENCE-HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(MixedDistanceSequenceHamming(modifiedDeck.getCurrentDeckOrder()));}
				if(!linePrinter.equals("")){if(cont==1000000||cont==3000000||cont==10000000||cont==20000000||cont==30000000||cont==40000000||cont==50000000){System.out.println(cont);}csvWriter.append(linePrinter+"\n");	cont++;}
				linePrinter="";
				doDeckToBackupState();
			}
			
			
			currentTechniques.clear();
			i+=4;
			
		}
		csvWriter.flush();
 		csvWriter.close();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void metricAnalyzerN4SecondPos(ArrayList<Integer> techniqueSet, Integer numberOfSametech, String metric, String technique){
		try {
			Deck modifiedDeck=new Deck();
			switch(technique){
			case "CUT"://CUT
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				modifiedDeck.cutCurrentDeck(numberOfSametech);
				break;
			case "SLIPCUT"://SLIPCUT
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				modifiedDeck.slipCutCurrentDeck(numberOfSametech);
				break;
			case "SLIPCUTUP"://SLIPCUTUP			
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpSlipCutCurrentDeck(numberOfSametech);
				break;
			case "PEAL"://PEAL			
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				modifiedDeck.pealCurrentDeck(numberOfSametech);
				break;
			case "PEALUP"://PEALUP		
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpPealCurrentDeck(numberOfSametech);
				break;
			case "INFARO"://INFARO
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.inPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "INFAROUP"://INFAROUP		
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpInPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "OUTFARO"://OUTFARO	
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.outPartialFaroCurrentDeck(numberOfSametech);
				break;
			case "OUTFAROUP"://OUTFAROUP
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				modifiedDeck.faceUpOutPartialFaroCurrentDeck(numberOfSametech);
				break;
			}
		int a=numberOfSametech;
		int c=numberOfSametech;
		int d=numberOfSametech;
		int cont=0;
		FileWriter csvWriter;
		csvWriter= new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//MetricAnalysis//N4SecondPos"+technique+numberOfSametech+metric+".csv");
		csvWriter.append("maintained,technique,is,"+technique+",and,the,number,is,"+numberOfSametech+"\n");
		
		ArrayList<Integer> currentTechniques= new ArrayList<>();
		String linePrinter = "";
		int i=4;
		int j=0;
		while (i<techniqueSet.size()+4){
			for(;j<i;j++){//we separate the log techniqueSet in smaller ones
				currentTechniques.add(techniqueSet.get(j));
			}
			
			//N=4 we will try all combinations on the given techniques
			for(int b=0; b<52; b++){
				outerloop:
				for(int e=0; e<currentTechniques.size();e++){//we are looking at the current techniques to apply the correspondent numbers afterwards
					switch(currentTechniques.get(e)){
					case 0://CUT
						linePrinter+="cut"+",";
						if(e==0&&a>=1&&a<=52){cutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=1&&b<=52){cutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=1&&c<=52){cutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=1&&d<=52){cutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 1://SLIPCUT
						linePrinter+="slipcut"+",";
						if(e==0&&a>=2&&a<=52){slipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){slipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){slipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){slipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 2://SLIPCUTUP			
						linePrinter+="slipcutup"+",";
						if(e==0&&a>=2&&a<=52){faceUpSlipCutCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpSlipCutCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpSlipCutCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpSlipCutCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 3://PEAL			
						linePrinter+="peal"+",";
						if(e==0&&a>=2&&a<=52){pealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){pealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){pealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){pealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 4://PEALUP		
						linePrinter+="pealup"+",";
						if(e==0&&a>=2&&a<=52){faceUpPealCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=2&&b<=52){faceUpPealCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=2&&c<=52){faceUpPealCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=2&&d<=52){faceUpPealCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 5://INFARO
						linePrinter+="infaro"+",";
						if(e==0&&a>=10&&a<=42){inPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){inPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){inPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){inPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 6://INFAROUP		
						linePrinter+="infaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpInPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpInPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpInPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpInPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 7://OUTFARO	
						linePrinter+="outfaro"+",";
						if(e==0&&a>=10&&a<=42){outPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){outPartialFaroCurrentDeck(b);linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){outPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){outPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					case 8://OUTFAROUP
						linePrinter+="outfaroup"+",";
						if(e==0&&a>=10&&a<=42){faceUpOutPartialFaroCurrentDeck(a); linePrinter+=Integer.toString(a)+",";}//apply a
						else if(e==1&&b>=10&&b<=42){faceUpOutPartialFaroCurrentDeck(b); linePrinter+=Integer.toString(b)+",";}//apply b
						else if(e==2&&c>=10&&c<=42){faceUpOutPartialFaroCurrentDeck(c); linePrinter+=Integer.toString(c)+",";}//apply c
						else if(e==3&&d>=10&&d<=42){faceUpOutPartialFaroCurrentDeck(d); linePrinter+=Integer.toString(d)+",";}//apply d
						else{doDeckToBackupState(); linePrinter=""; break outerloop;}
						break;
					}
				}
				//aplicar metrica aqui para obtener resultados de la combinación actual
				if(metric.equals("SEQUENCE")&&!linePrinter.equals("")){linePrinter+=Integer.toString(vectorDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(hammingDistance(modifiedDeck.getCurrentDeckOrder()));}
				else if(metric.equals("SEQUENCE-HAMMING")&&!linePrinter.equals("")){linePrinter+=Integer.toString(MixedDistanceSequenceHamming(modifiedDeck.getCurrentDeckOrder()));}
				if(!linePrinter.equals("")){if(cont==1000000||cont==3000000||cont==10000000||cont==20000000||cont==30000000||cont==40000000||cont==50000000){System.out.println(cont);}csvWriter.append(linePrinter+"\n");	cont++;}
				linePrinter="";
				doDeckToBackupState();
			}
			
			
			currentTechniques.clear();
			i+=4;
			
		}
		csvWriter.flush();
 		csvWriter.close();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		Deck Baraja = new Deck();
		ArrayList<String> finalDeck = Baraja.stringToArrayListDeck("[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]");
		ArrayList<String> finalDeck2 = Baraja.stringToArrayListDeck("[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]");
		Baraja.printCurrentDeck();
		
		Baraja.cutCurrentDeck(30);
		Baraja.cutCurrentDeck(30);
		Baraja.cutCurrentDeck(30);
		Baraja.cutCurrentDeck(30);
		Baraja.printCurrentDeck();
		
		Baraja.doDeckToBackupState();
		Baraja.printCurrentDeck();
		
		Baraja.slipCutCurrentDeck(10);
		Baraja.cutCurrentDeck(30);
		Baraja.cutCurrentDeck(30);
		Baraja.cutCurrentDeck(30);
		
		Baraja.printCurrentDeck();
		//Baraja.setCurrentDeckOrder(finalDeck);
		//Baraja.doubleTechPrintings();
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		
		//N=4
		//CUT
		/*ArrayList<Integer> techniqueList=new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,1,0,0,0,2,0,0,0,3,0,0,0,4,0,0,0,5,0,0,0,6,0,0,0,7,0,0,0,8)); 
		ArrayList<Integer> techniqueList2=new ArrayList<>(Arrays.asList(0,0,0,0,0,0,1,0,0,0,2,0,0,0,3,0,0,0,4,0,0,0,5,0,0,0,6,0,0,0,7,0,0,0,8,0)); 
		ArrayList<Integer> techniqueList3=new ArrayList<>(Arrays.asList(0,0,0,0,1,0,0,0,2,0,0,0,3,0,0,0,4,0,0,0,5,0,0,0,6,0,0,0,7,0,0,0,8,0,0,0)); 
		ArrayList<Integer> techniqueList4=new ArrayList<>(Arrays.asList(0,0,0,0,0,1,0,0,0,2,0,0,0,3,0,0,0,4,0,0,0,5,0,0,0,6,0,0,0,7,0,0,0,8,0,0)); 
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","CUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","CUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","CUT");
		
		//SLIPCUT
		techniqueList=new ArrayList<>(Arrays.asList(1,1,1,0,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,4,1,1,1,5,1,1,1,6,1,1,1,7,1,1,1,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(1,1,0,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,4,1,1,1,5,1,1,1,6,1,1,1,7,1,1,1,8,1)); 
		techniqueList4=new ArrayList<>(Arrays.asList(0,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,4,1,1,1,5,1,1,1,6,1,1,1,7,1,1,1,8,1,1,1)); 
		techniqueList4=new ArrayList<>(Arrays.asList(1,0,1,1,1,1,1,1,1,2,1,1,1,3,1,1,1,4,1,1,1,5,1,1,1,6,1,1,1,7,1,1,1,8,1,1)); 
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","SLIPCUT");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","SLIPCUT");
		
		//SLIPCUTUP
		
		techniqueList=new ArrayList<>(Arrays.asList(2,2,2,0,2,2,2,1,2,2,2,2,2,2,2,3,2,2,2,4,2,2,2,5,2,2,2,6,2,2,2,7,2,2,2,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(2,2,0,2,2,2,1,2,2,2,2,2,2,2,3,2,2,2,4,2,2,2,5,2,2,2,6,2,2,2,7,2,2,2,8,2)); 
		techniqueList3=new ArrayList<>(Arrays.asList(0,2,2,2,1,2,2,2,2,2,2,2,3,2,2,2,4,2,2,2,5,2,2,2,6,2,2,2,7,2,2,2,8,2,2,2));
		techniqueList4=new ArrayList<>(Arrays.asList(2,0,2,2,2,1,2,2,2,2,2,2,2,3,2,2,2,4,2,2,2,5,2,2,2,6,2,2,2,7,2,2,2,8,2,2));
		
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","SLIPCUT");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","SLIPCUTUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","SLIPCUTUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","SLIPCUTUP");
		
		
		//PEAL
		techniqueList=new ArrayList<>(Arrays.asList(3,3,3,0,3,3,3,1,3,3,3,2,3,3,3,3,3,3,3,4,3,3,3,5,3,3,3,6,3,3,3,7,3,3,3,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(3,3,0,3,3,3,1,3,3,3,2,3,3,3,3,3,3,3,4,3,3,3,5,3,3,3,6,3,3,3,7,3,3,3,8,3)); 
		techniqueList4=new ArrayList<>(Arrays.asList(0,3,3,3,1,3,3,3,2,3,3,3,3,3,3,3,4,3,3,3,5,3,3,3,6,3,3,3,7,3,3,3,8,3,3,3)); 
		techniqueList4=new ArrayList<>(Arrays.asList(3,0,3,3,3,1,3,3,3,2,3,3,3,3,3,3,3,4,3,3,3,5,3,3,3,6,3,3,3,7,3,3,3,8,3,3)); 
		
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","PEAL");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","PEAL");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","PEAL");
		
		//PEALUP
			
		techniqueList=new ArrayList<>(Arrays.asList(4,4,4,0,4,4,4,1,4,4,4,2,4,4,4,3,4,4,4,4,4,4,4,5,4,4,4,6,4,4,4,7,4,4,4,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(4,4,0,4,4,4,1,4,4,4,2,4,4,4,3,4,4,4,4,4,4,4,5,4,4,4,6,4,4,4,7,4,4,4,8,4)); 
		techniqueList3=new ArrayList<>(Arrays.asList(0,4,4,4,1,4,4,4,2,4,4,4,3,4,4,4,4,4,4,4,5,4,4,4,6,4,4,4,7,4,4,4,8,4,4,4)); 
		techniqueList4=new ArrayList<>(Arrays.asList(4,0,4,4,4,1,4,4,4,2,4,4,4,3,4,4,4,4,4,4,4,5,4,4,4,6,4,4,4,7,4,4,4,8,4,4)); 
		
		
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","PEALUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","PEALUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","PEALUP");
		
		
		//INFARO
		techniqueList=new ArrayList<>(Arrays.asList(5,5,5,0,5,5,5,1,5,5,5,2,5,5,5,3,5,5,5,4,5,5,5,5,5,5,5,6,5,5,5,7,5,5,5,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(5,5,0,5,5,5,1,5,5,5,2,5,5,5,3,5,5,5,4,5,5,5,5,5,5,5,6,5,5,5,7,5,5,5,8,5)); 
		techniqueList4=new ArrayList<>(Arrays.asList(0,5,5,5,1,5,5,5,2,5,5,5,3,5,5,5,4,5,5,5,5,5,5,5,6,5,5,5,7,5,5,5,8,5,5,5)); 
		techniqueList4=new ArrayList<>(Arrays.asList(5,0,5,5,5,1,5,5,5,2,5,5,5,3,5,5,5,4,5,5,5,5,5,5,5,6,5,5,5,7,5,5,5,8,5,5)); 
		
		
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","INFARO");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","INFARO");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","INFARO");
				
		//INFAROUP	
			
		techniqueList=new ArrayList<>(Arrays.asList(6,6,6,0,6,6,6,1,6,6,6,2,6,6,6,3,6,6,6,4,6,6,6,5,6,6,6,6,6,6,6,7,6,6,6,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(6,6,0,6,6,6,1,6,6,6,2,6,6,6,3,6,6,6,4,6,6,6,5,6,6,6,6,6,6,6,7,6,6,6,8,6)); 
		techniqueList3=new ArrayList<>(Arrays.asList(0,6,6,6,1,6,6,6,2,6,6,6,3,6,6,6,4,6,6,6,5,6,6,6,6,6,6,6,7,6,6,6,8,6,6,6)); 
		techniqueList4=new ArrayList<>(Arrays.asList(6,0,6,6,6,1,6,6,6,2,6,6,6,3,6,6,6,4,6,6,6,5,6,6,6,6,6,6,6,7,6,6,6,8,6,6)); 
		
		
		//String[] operators={"cut","slipcut","slipcutup","peal","pealup","infaro","infaroup","outfaro","outfaroup"};
		
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","INFAROUP");
				
				Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","INFAROUP");
				Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","INFAROUP");
		
		//OUTFARO	
		techniqueList=new ArrayList<>(Arrays.asList(7,7,7,0,7,7,7,1,7,7,7,2,7,7,7,3,7,7,7,4,7,7,7,5,7,7,7,6,7,7,7,7,7,7,7,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(7,7,0,7,7,7,1,7,7,7,2,7,7,7,3,7,7,7,4,7,7,7,5,7,7,7,6,7,7,7,7,7,7,7,8,7)); 
		techniqueList3=new ArrayList<>(Arrays.asList(0,7,7,7,1,7,7,7,2,7,7,7,3,7,7,7,4,7,7,7,5,7,7,7,6,7,7,7,7,7,7,7,8,7,7,7)); 
		techniqueList4=new ArrayList<>(Arrays.asList(7,0,7,7,7,1,7,7,7,2,7,7,7,3,7,7,7,4,7,7,7,5,7,7,7,6,7,7,7,7,7,7,7,8,7,7)); 
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","OUTFARO");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","OUTFARO");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","OUTFARO");
		
		//OUTFAROUP	
		techniqueList=new ArrayList<>(Arrays.asList(8,8,8,0,8,8,8,1,8,8,8,2,8,8,8,3,8,8,8,4,8,8,8,5,8,8,8,6,8,8,8,7,8,8,8,8)); 
		techniqueList2=new ArrayList<>(Arrays.asList(8,8,0,8,8,8,1,8,8,8,2,8,8,8,3,8,8,8,4,8,8,8,5,8,8,8,6,8,8,8,7,8,8,8,8,8)); 
		techniqueList4=new ArrayList<>(Arrays.asList(0,8,8,8,1,8,8,8,2,8,8,8,3,8,8,8,4,8,8,8,5,8,8,8,6,8,8,8,7,8,8,8,8,8,8,8)); 
		techniqueList4=new ArrayList<>(Arrays.asList(8,0,8,8,8,1,8,8,8,2,8,8,8,3,8,8,8,4,8,8,8,5,8,8,8,6,8,8,8,7,8,8,8,8,8,8)); 
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"HAMMING","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"HAMMING","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"HAMMING","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,20,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,20,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,20,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,20,"SEQUENCE-HAMMING","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,30,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,30,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,30,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,30,"SEQUENCE-HAMMING","OUTFAROUP");
		
		Baraja.metricAnalyzerN4LastPos(techniqueList,40,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4PrevToLastPos(techniqueList2,40,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4FirstPos(techniqueList3,40,"SEQUENCE-HAMMING","OUTFAROUP");
		Baraja.metricAnalyzerN4SecondPos(techniqueList4,40,"SEQUENCE-HAMMING","OUTFAROUP");*/
		/*
		this.cutCurrentDeck(N);
		this.pealCurrentDeck(N);
		this.slipCutCurrentDeck(N);
		this.faceUpSlipCutCurrentDeck(N);
		this.faceUpPealCurrentDeck(N);
		this.faceUpInPartialFaroCurrentDeck(N);
		this.outPartialFaroCurrentDeck(N);
		this.faceUpOutPartialFaroCurrentDeck(N);
		
		
		cutCurrentDeck
		pealCurrentDeck		
		slipCutCurrentDeck
		faceUpSlipCutCurrentDeck
		faceUpPealCurrentDeck
		inPartialFaroCurrentDeck	
		faceUpInPartialFaroCurrentDeck
		outPartialFaroCurrentDeck
		faceUpOutPartialFaroCurrentDeck	*/
	}
}
