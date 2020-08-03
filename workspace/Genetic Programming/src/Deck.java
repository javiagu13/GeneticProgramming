import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<String> CurrentDeckBackup = new ArrayList<String>(); 
	private ArrayList<String> CurrentDeckOrder = new ArrayList<String>(); 
	private ArrayList<String> FinalCurrentDeckOrder = new ArrayList<String>(); 
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
				
		//Final Deck Order    //////ARRIVAL AT peal 22 faro 18 faro 23 peal 12
		this.FinalCurrentDeckOrder.add("1d");
		this.FinalCurrentDeckOrder.add("4s");
		this.FinalCurrentDeckOrder.add("2d");
		this.FinalCurrentDeckOrder.add("10h");
		this.FinalCurrentDeckOrder.add("3d");
		this.FinalCurrentDeckOrder.add("3s");
		this.FinalCurrentDeckOrder.add("4d");
		this.FinalCurrentDeckOrder.add("11h");
		this.FinalCurrentDeckOrder.add("5d");
		this.FinalCurrentDeckOrder.add("2s");
		this.FinalCurrentDeckOrder.add("6d");
		this.FinalCurrentDeckOrder.add("12h");
		this.FinalCurrentDeckOrder.add("7d");
		this.FinalCurrentDeckOrder.add("1s");
		this.FinalCurrentDeckOrder.add("8d");
		this.FinalCurrentDeckOrder.add("13h");
		this.FinalCurrentDeckOrder.add("9d");
		this.FinalCurrentDeckOrder.add("9h");
		this.FinalCurrentDeckOrder.add("13c");
		this.FinalCurrentDeckOrder.add("5s");
		this.FinalCurrentDeckOrder.add("12c");
		this.FinalCurrentDeckOrder.add("8h");
		this.FinalCurrentDeckOrder.add("11c");
		this.FinalCurrentDeckOrder.add("6s");
		this.FinalCurrentDeckOrder.add("10c");
		this.FinalCurrentDeckOrder.add("7h");
		this.FinalCurrentDeckOrder.add("9c");
		this.FinalCurrentDeckOrder.add("7s");
		this.FinalCurrentDeckOrder.add("8c");
		this.FinalCurrentDeckOrder.add("6h");
		this.FinalCurrentDeckOrder.add("7c");
		this.FinalCurrentDeckOrder.add("8s");
		this.FinalCurrentDeckOrder.add("6c");
		this.FinalCurrentDeckOrder.add("5h");
		this.FinalCurrentDeckOrder.add("5c");
		this.FinalCurrentDeckOrder.add("9s");
		this.FinalCurrentDeckOrder.add("4c");
		this.FinalCurrentDeckOrder.add("4h");
		this.FinalCurrentDeckOrder.add("3c");
		this.FinalCurrentDeckOrder.add("10s");
		this.FinalCurrentDeckOrder.add("2c");
		this.FinalCurrentDeckOrder.add("3h");
		this.FinalCurrentDeckOrder.add("1c");
		this.FinalCurrentDeckOrder.add("10d");
		this.FinalCurrentDeckOrder.add("11d");
		this.FinalCurrentDeckOrder.add("12d");
		this.FinalCurrentDeckOrder.add("13d");
		this.FinalCurrentDeckOrder.add("2h");
		this.FinalCurrentDeckOrder.add("1h");
		this.FinalCurrentDeckOrder.add("13s");
		this.FinalCurrentDeckOrder.add("12s");
		this.FinalCurrentDeckOrder.add("11s");	 
		System.out.println("****************************************PROGRAM BEGINS*****************************************");
		System.out.println("");
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
		
	public void printCurrentDeck(){
		String currentCurrentDeckOrder=this.CurrentDeckOrder.toString();
		System.out.println("Current Deck Order: "+currentCurrentDeckOrder);
	}
	
	public void printBackupDeck(){
		String currentCurrentDeckOrder=this.CurrentDeckBackup.toString();
		System.out.println("Backup Deck Order: "+currentCurrentDeckOrder);
	}
	
	public void printFinalDeck(){
		String currentCurrentDeckOrder=this.FinalCurrentDeckOrder.toString();
		System.out.println("Final Deck Order: "+currentCurrentDeckOrder);
	}
	
	public void doBackupOfDeck(){
		this.CurrentDeckBackup=  (ArrayList<String>) this.CurrentDeckOrder.clone();
	}
	
	public void doDeckToBackupState(){
		this.CurrentDeckOrder=  (ArrayList<String>) this.CurrentDeckBackup.clone();
	}
	
	public static void main(String[] args) {
		Deck Baraja = new Deck();
		Baraja.printCurrentDeck();
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(26);
		Baraja.pealCurrentDeck(26);
		Baraja.outPartialFaroCurrentDeck(18);
		Baraja.cutCurrentDeck(9);
		Baraja.printCurrentDeck();
		}
}
