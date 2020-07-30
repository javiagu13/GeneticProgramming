import java.util.ArrayList;

public class Deck {
	private ArrayList<String> CurrentDeckOrder = new ArrayList<String>(); 
	private ArrayList<String> FinalCurrentDeckOrder = new ArrayList<String>(); 
	private ArrayList<ArrayList<String>> OperationsArray = new ArrayList<ArrayList<String>>(); 
	
	
	public Deck(){
		//CurrentDeckOrder
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
		
		//Adding Hearts (Corazones)
		this.CurrentDeckOrder.add("13h");
		this.CurrentDeckOrder.add("12h");
		this.CurrentDeckOrder.add("11h");
		this.CurrentDeckOrder.add("10h");
		this.CurrentDeckOrder.add("9h");
		this.CurrentDeckOrder.add("8h");
		this.CurrentDeckOrder.add("7h");
		this.CurrentDeckOrder.add("6h");
		this.CurrentDeckOrder.add("5h");
		this.CurrentDeckOrder.add("4h");
		this.CurrentDeckOrder.add("3h");
		this.CurrentDeckOrder.add("2h");
		this.CurrentDeckOrder.add("1h");
		
		//Adding Spades (Picas)
		this.CurrentDeckOrder.add("13s");
		this.CurrentDeckOrder.add("12s");
		this.CurrentDeckOrder.add("11s");
		this.CurrentDeckOrder.add("10s");
		this.CurrentDeckOrder.add("9s");
		this.CurrentDeckOrder.add("8s");
		this.CurrentDeckOrder.add("7s");
		this.CurrentDeckOrder.add("6s");
		this.CurrentDeckOrder.add("5s");
		this.CurrentDeckOrder.add("4s");
		this.CurrentDeckOrder.add("3s");
		this.CurrentDeckOrder.add("2s");
		this.CurrentDeckOrder.add("1s");
		
		this.OperationsArray.add(this.CurrentDeckOrder);
		System.out.println("Initial Deck Order: "+this.CurrentDeckOrder.toString());

		
		/*********************************************************/
		/*//Final Deck Order    //////ARRIVAL AT peal 22 faro 18 faro 23 peal 12
		this.FinalCurrentDeckOrder.add("13d");
		this.FinalCurrentDeckOrder.add("13c");
		
		this.FinalCurrentDeckOrder.add("12d");
		this.FinalCurrentDeckOrder.add("12c");
		
		this.FinalCurrentDeckOrder.add("11d");
		this.FinalCurrentDeckOrder.add("11c");
		
		this.FinalCurrentDeckOrder.add("10d");
		this.FinalCurrentDeckOrder.add("10c");
		
		this.FinalCurrentDeckOrder.add("9d");
		this.FinalCurrentDeckOrder.add("9c");
		
		this.FinalCurrentDeckOrder.add("8d");
		this.FinalCurrentDeckOrder.add("8c");
		
		this.FinalCurrentDeckOrder.add("7d");
		this.FinalCurrentDeckOrder.add("7c");
		
		this.FinalCurrentDeckOrder.add("6d");
		this.FinalCurrentDeckOrder.add("6c");
		
		this.FinalCurrentDeckOrder.add("5d");
		this.FinalCurrentDeckOrder.add("5c");
		
		
		
		this.FinalCurrentDeckOrder.add("4d");
		this.FinalCurrentDeckOrder.add("4c");
		
		this.FinalCurrentDeckOrder.add("3d");
		this.FinalCurrentDeckOrder.add("3c");
		
		this.FinalCurrentDeckOrder.add("2d");
		this.FinalCurrentDeckOrder.add("2c");
		
		this.FinalCurrentDeckOrder.add("1d");
		this.FinalCurrentDeckOrder.add("1c");
		
		//Adding Hearts (Corazones)
		this.FinalCurrentDeckOrder.add("13h");
		this.FinalCurrentDeckOrder.add("12h");
		this.FinalCurrentDeckOrder.add("11h");
		this.FinalCurrentDeckOrder.add("10h");
		this.FinalCurrentDeckOrder.add("9h");
		this.FinalCurrentDeckOrder.add("8h");
		this.FinalCurrentDeckOrder.add("7h");
		this.FinalCurrentDeckOrder.add("6h");
		this.FinalCurrentDeckOrder.add("5h");
		this.FinalCurrentDeckOrder.add("4h");
		this.FinalCurrentDeckOrder.add("3h");
		this.FinalCurrentDeckOrder.add("2h");
		this.FinalCurrentDeckOrder.add("1h");
		
		//Adding Spades (Picas)
		this.FinalCurrentDeckOrder.add("13s");
		this.FinalCurrentDeckOrder.add("12s");
		this.FinalCurrentDeckOrder.add("11s");
		this.FinalCurrentDeckOrder.add("10s");
		this.FinalCurrentDeckOrder.add("9s");
		this.FinalCurrentDeckOrder.add("8s");
		this.FinalCurrentDeckOrder.add("7s");
		this.FinalCurrentDeckOrder.add("6s");
		this.FinalCurrentDeckOrder.add("5s");
		this.FinalCurrentDeckOrder.add("4s");
		this.FinalCurrentDeckOrder.add("3s");
		this.FinalCurrentDeckOrder.add("2s");
		this.FinalCurrentDeckOrder.add("1s");*/
		
				
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
		
		

		
		//Adding Clubs (Treboles)
		
		//Adding Hearts (Corazones)
		
		//Adding Spades (Picas)
		
				 
	}
	
	//Peal [2-52] c N
	public void pealCurrentDeck(int N){
		if(N<1){System.out.println("while pealing N cannot be < 1");}
		else if(N>52){System.out.println("while pealing N cannot be > 52");}
		else if(N==1){System.out.println("while pealing N cannot be 1");}
		else if(N==2){
			String aux = this.CurrentDeckOrder.get(1);
			this.CurrentDeckOrder.set(1, this.CurrentDeckOrder.get(0));
			this.CurrentDeckOrder.set(0, aux);
		}
		else{
			System.out.println("Applying pealing N="+N+"...");
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
	
	
	//Faro [10-42] c N
	public void outPartialFaroCurrentDeck(int N){
		if(N<10){System.out.println("while doing out faro N cannot be < 10");}
		else if(N>42){System.out.println("while doing out faro N cannot be > 42");}
		else{
			System.out.println("Applying out partial faro N="+N+"...");
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
	
	public String toStringCurrentDeck(){
		String currentCurrentDeckOrder=this.CurrentDeckOrder.toString();
		return "Current Deck Order: "+currentCurrentDeckOrder;
	}
	
	public String toStringFinalDeck(){
		String currentCurrentDeckOrder=this.FinalCurrentDeckOrder.toString();
		return "Final Deck Order: "+currentCurrentDeckOrder;
	}
	
	/*
	 public Deck(ArrayList<String> newCurrentDeckOrder){}
	 */
	//Peal [2-52] c N
		public void pealToOperationsArray(int N, int fromOpArrayPos, int toOpArrayPos){
			//Copy of array to the next position
			if(toOpArrayPos>this.OperationsArray.size()-1){this.OperationsArray.add(null);}
			ArrayList<String> fromOpArrayPosCopy = new ArrayList<String>(this.OperationsArray.get(fromOpArrayPos));
			this.OperationsArray.set(toOpArrayPos, fromOpArrayPosCopy);
			ArrayList<String> DeckOrder=this.OperationsArray.get(toOpArrayPos);
			
			if(N<1){System.out.println("while pealing N cannot be < 1");}
			else if(N>52){System.out.println("while pealing N cannot be > 52");}
			else if(N==1){System.out.println("while pealing N cannot be 1");}
			else if(N==2){
				String aux = DeckOrder.get(1);
				DeckOrder.set(1, DeckOrder.get(0));
				DeckOrder.set(0, aux);
			}
			else{
				//System.out.println("Applying pealing N="+N+"...");
				int pos1=0;
				int pos2=N-1;
				
				while (pos1<=((N-1)/2)){
					String aux = DeckOrder.get(pos2);
					DeckOrder.set(pos2, DeckOrder.get(pos1));
					DeckOrder.set(pos1, aux);
					pos1++;
					pos2--;
				}
				
			}
		}
		
		//Faro [10-42] c N
		public void outPartialFaroToOperationsArray(int N, int fromOpArrayPos, int toOpArrayPos){
			
			//Copy of array to the next position
			if(toOpArrayPos>this.OperationsArray.size()-1){this.OperationsArray.add(null);}
			ArrayList<String> fromOpArrayPosCopy = new ArrayList<String>(this.OperationsArray.get(fromOpArrayPos));
			this.OperationsArray.set(toOpArrayPos, fromOpArrayPosCopy);
			ArrayList<String> DeckOrder=this.OperationsArray.get(toOpArrayPos);
			
			if(N<10){System.out.println("while doing out faro N cannot be < 10");}
			else if(N>42){System.out.println("while doing out faro N cannot be > 42");}
			else{
				//System.out.println("Applying out partial faro N="+N+"...");
				int cuttingPos = N-1;
				ArrayList<String> leftPacket = new ArrayList<String>(); 
				ArrayList<String> rightPacket = new ArrayList<String>(); 
				for(int i=0; i<DeckOrder.size(); i++){
					if(i<=cuttingPos){
						rightPacket.add(DeckOrder.get(i));
					}
					else{
						leftPacket.add(DeckOrder.get(i));
					}
				}
				rightPacket.add(null);
				leftPacket.add(null);
				
				for(int i=0; i<(DeckOrder.size()/2); i++){
					if(rightPacket.get(i)==null){
						int currentDeckPos=i*2;
						for(int j=i; j<leftPacket.size()-1; j++){
							DeckOrder.set(currentDeckPos, leftPacket.get(j));
							currentDeckPos++;
						}
						break;
					}
					else if(leftPacket.get(i)==null){
						int currentDeckPos=i*2;
						for(int j=i; j<rightPacket.size()-1; j++){
							DeckOrder.set(currentDeckPos, rightPacket.get(j));
							currentDeckPos++;
						}
						break;
					}
					else{
						DeckOrder.set(i*2, rightPacket.get(i));
						DeckOrder.set((i*2)+1, leftPacket.get(i));
					}
				}
			}
		}
		
		public String toStringOperationsArray(int op){
			String currentCurrentDeckOrder=this.OperationsArray.get(op).toString();
			return "Current Deck Order: "+currentCurrentDeckOrder;
		}
	
	
	
	public static void main(String[] args) {
		Deck Baraja = new Deck();
		
		/*Baraja.pealCurrentDeck(37);
		Baraja.outPartialFaroCurrentDeck(42);
		Baraja.pealCurrentDeck(43);
		Baraja.outPartialFaroCurrentDeck(22);
		Baraja.pealCurrentDeck(17);
		System.out.println(Baraja.CurrentDeckOrder.toString());
		*/
		/*for(int i=2; i<=52; i++){ //PEALING
			Baraja.pealToOperationsArray(i,0,1);
			if(Baraja.OperationsArray.get(1).equals(Baraja.FinalCurrentDeckOrder)){
				System.out.println("Success!!!");
				System.out.println("pealing N="+i);
			}
			for(int j=10; j<=42; j++){ //FARO
				Baraja.outPartialFaroToOperationsArray(j,1,2);
				if(Baraja.OperationsArray.get(2).equals(Baraja.FinalCurrentDeckOrder)){
					System.out.println("Success!!!");
					System.out.println("pealing N="+i+" faro N="+j);
				}
				for(int k=2; k<=52; k++){ //PEALING
					Baraja.pealToOperationsArray(k,2,3);
					//System.out.println(Baraja.toStringOperationsArray(3));
					if(Baraja.OperationsArray.get(3).equals(Baraja.FinalCurrentDeckOrder)){
						System.out.println("Success!!!");
						System.out.println("pealing N="+i+" faro N="+j+" pealing N="+k);
					}
					for(int l=10; l<=42; l++){ //FARO
						Baraja.outPartialFaroToOperationsArray(l,3,4);
						if(Baraja.OperationsArray.get(4).equals(Baraja.FinalCurrentDeckOrder)){
							System.out.println("Success!!!");
							System.out.println("pealing N="+i+" faro N="+j+" pealing N="+k+" faro N="+l);
						}
						for(int m=2; m<=52; m++){ //PEALING
							Baraja.pealToOperationsArray(m,4,5);
							if(Baraja.OperationsArray.get(5).equals(Baraja.FinalCurrentDeckOrder)){
								System.out.println("Success!!!");
								System.out.println("pealing N="+i+" faro N="+j+" pealing N="+k+" faro N="+l+" pealing N="+m);
							}
						}
					}
				}
			}
		}*/
		
		
	}
}
