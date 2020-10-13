import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class






public class javiGP {
	
	public class Tuple<X, Y> implements Cloneable  { 
		  public X x; 
		  public Y y; 
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
		  
		  public void setSecondElement(Y val){
			  this.y=val;
		  }
	}
	
	private String initialDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	
	//BEGINNING TEST 1
	//n=2
	//cut 13 26
	//private String desiredDeckString = "[13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d]";
	
	//n=4
	//cut 13 26 12 27
	//private String desiredDeckString = "[11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d]";

	//n=8
	//cut 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c]";

	//n=16
	//cut 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s]";
	
	//n=32
	//cut 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h]";
	
	
	//
	//SLIPCUT
	//n=2
	//slipcut 13 26
	//private String desiredDeckString = "[3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	//n=4
	//slipcut 13 26 12 27
	//private String desiredDeckString = "[5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1s, 1h, 2h, 3s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

	//n=8
	//slipcut 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[9s, 10s, 11s, 12s, 13s, 1s, 1h, 5s, 7s, 2h, 3s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 8s, 6s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

	//n=16
	//slipcut 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[7s, 9s, 11s, 13s, 1h, 2h, 3s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 5s, 1s, 12s, 10s, 8s, 6s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	//n=32
	//slipcut 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[6h, 7s, 13s, 4h, 7h, 5h, 9s, 11s, 8h, 3h, 9h, 10h, 11h, 12h, 13h, 2s, 5s, 1s, 1h, 12s, 3s, 10s, 8s, 6s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 2h, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	
	
	//SLIPCUTUP
	//n=2
	//slipcutup 13 26
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2c, 13d, 12d, 11d, 10d, 4c, 9d, 3c, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 5c, 1c, 9c, 13c, 8c, 12c, 7c, 11c, 6c, 10c]";
	
	//n=4
	//slipcutup 13 26 12 27
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 3c, 2d, 1d, 1c, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";

	//n=8
	//slipcutup 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 6c, 8c, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 3c, 2d, 7c, 5c, 1d, 1c, 13c, 12c, 11c, 10c, 9c]";

	//n=16
	//slipcutup 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 6c, 8c, 10c, 12c, 1c, 5c, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 3c, 2d, 1d, 13c, 11c, 9c, 7c]";
	
	//n=32
	//slipcutup 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 2d, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 6c, 8c, 10c, 3c, 12c, 1d, 1c, 5c, 2c, 13d, 12d, 11d, 10d, 9d, 3d, 8d, 11c, 9c, 5d, 7d, 4d, 13c, 7c, 6d]";
	
	
	
	//PEAL
	//n=2
	//peal 13 26
	//private String desiredDeckString = "[13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	//n=4
	//peal 13 26 12 27
	//private String desiredDeckString = "[13d, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	//n=8
	//peal 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 13d, 3s, 2s, 13h, 12h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 4s, 1h, 1s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	//n=16
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[7s, 8s, 9s, 10s, 11s, 12s, 5s, 13d, 3s, 2s, 13h, 12h, 2h, 10h, 4h, 5h, 6h, 7h, 8h, 9h, 6s, 3h, 13s, 11h, 4s, 1h, 1s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	//n=32
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[4s, 1s, 11d, 2h, 7d, 8d, 9d, 10d, 12d, 1h, 11h, 13s, 3h, 6s, 9h, 8h, 7h, 6h, 5h, 4h, 10h, 13h, 13d, 5s, 12s, 11s, 8s, 7s, 9s, 10s, 3s, 12h, 2s, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
	
	
	//PEALUP
	//n=2
	//peal 13 26
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d]";
	
	//n=4
	//peal 13 26 12 27
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 1c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 13h]";
	
	//n=8
	//peal 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 1c, 1d, 4c, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 12d, 13d, 2c, 3c, 13h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";
	
	//n=16
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 1c, 1d, 4c, 11d, 13c, 3d, 6c, 9d, 8d, 7d, 6d, 5d, 4d, 10d, 2d, 12d, 13d, 2c, 3c, 13h, 5c, 12c, 11c, 10c, 9c, 8c, 7c]";
	
	//n=32
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 2c, 12d, 3c, 10c, 9c, 7c, 8c, 11c, 12c, 5c, 13h, 13d, 10d, 4d, 5d, 6d, 7d, 8d, 9d, 6c, 3d, 13c, 11d, 1d, 12h, 10h, 9h, 8h, 7h, 2d, 11h, 1c, 4c]";
	
	
	//INFARO
	//n=2
	//peal 13 26
	//private String desiredDeckString = "[13d, 1h, 12d, 1s, 11d, 2h, 10d, 2s, 9d, 3h, 8d, 3s, 7d, 4h, 6d, 4s, 5d, 5h, 4d, 5s, 3d, 6h, 2d, 6s, 1d, 7h, 13c, 7s, 12c, 8h, 11c, 8s, 10c, 9h, 9c, 9s, 8c, 10h, 7c, 10s, 6c, 11h, 5c, 11s, 4c, 12h, 3c, 12s, 2c, 13h, 1c, 13s]";
	
	//n=4
	//peal 13 26 12 27
	//private String desiredDeckString = "[4h, 6d, 12c, 13d, 8h, 4s, 11c, 1h, 8s, 5d, 10c, 12d, 9h, 5h, 9c, 1s, 9s, 4d, 8c, 11d, 10h, 5s, 7c, 2h, 10s, 3d, 6c, 10d, 11h, 6h, 5c, 2s, 11s, 2d, 4c, 9d, 12h, 6s, 3c, 3h, 12s, 1d, 2c, 8d, 13h, 7h, 1c, 3s, 13s, 13c, 7d, 7s]";
	
	//n=8
	//peal 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[6s, 5c, 4d, 3d, 3c, 12c, 11c, 12d, 3h, 2s, 8c, 6c, 12s, 9c, 1h, 4h, 1d, 11s, 11d, 10d, 2c, 13d, 8s, 9h, 8d, 2d, 10h, 11h, 13h, 1s, 5d, 6d, 7h, 4c, 5s, 6h, 1c, 8h, 10c, 5h, 3s, 9d, 7c, 9s, 13s, 12h, 2h, 4s, 13c, 10s, 7d, 7s]";
	
	//n=16
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[12d, 13h, 9c, 5s, 7c, 1d, 4d, 9h, 6d, 12c, 8h, 6c, 10d, 3s, 10h, 6s, 3h, 1s, 1h, 6h, 9s, 11s, 3d, 8d, 7h, 11c, 10c, 12s, 2c, 9d, 11h, 5c, 2s, 5d, 4h, 1c, 13s, 11d, 3c, 2d, 4c, 5h, 13d, 8c, 12h, 8s, 2h, 4s, 13c, 10s, 7d, 7s]";
	
	//n=32
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[11s, 3c, 4h, 8c, 5s, 13h, 6c, 5d, 7d, 3h, 1d, 10h, 4c, 10s, 8s, 6d, 9h, 2d, 1c, 12h, 12d, 9s, 7s, 3s, 5h, 2h, 6s, 13s, 12c, 1s, 10d, 8h, 13d, 4s, 3d, 11d, 4d, 7c, 1h, 6h, 8d, 13c, 9c, 7h, 11c, 10c, 12s, 2c, 9d, 11h, 5c, 2s]";
	
	
	//INFAROUP
	//n=2
	//peal 13 26
	//private String desiredDeckString = "[13c, 1s, 13d, 2s, 12c, 3s, 12d, 4s, 11c, 5s, 11d, 6s, 10c, 7s, 10d, 8s, 9c, 9s, 9d, 10s, 8c, 11s, 8d, 12s, 7c, 13s, 7d, 1h, 6c, 2h, 6d, 3h, 5c, 4h, 5d, 5h, 4c, 6h, 4d, 7h, 3c, 8h, 3d, 9h, 2c, 10h, 2d, 11h, 1c, 12h, 1d, 13h]";
	
	//n=4
	//peal 13 26 12 27
	//private String desiredDeckString = "[7c, 7h, 13s, 13c, 3c, 1s, 7d, 13d, 8h, 2s, 1h, 12c, 3d, 3s, 6c, 12d, 9h, 4s, 2h, 11c, 2c, 5s, 6d, 11d, 10h, 6s, 3h, 10c, 2d, 7s, 5c, 10d, 11h, 8s, 4h, 9c, 1c, 9s, 5d, 9d, 12h, 10s, 5h, 8c, 1d, 11s, 4c, 8d, 13h, 12s, 6h, 4d]";
	
	//n=8
	//peal 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[7c, 7h, 10c, 13s, 4c, 2d, 12d, 13c, 9c, 7s, 9h, 3c, 5d, 10s, 8d, 1s, 6d, 5c, 4s, 7d, 6h, 5h, 1c, 13d, 11d, 10d, 2h, 8h, 9d, 8c, 13h, 2s, 10h, 11h, 11c, 1h, 4d, 1d, 9s, 12c, 6s, 8s, 2c, 3d, 12h, 11s, 12s, 3s, 3h, 4h, 5s, 6c]";
	
	//n=16
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[7c, 7h, 10c, 13s, 4c, 2d, 12d, 13c, 9c, 7s, 9h, 3c, 5d, 10s, 8d, 1s, 6d, 5c, 4s, 7d, 6h, 5h, 1c, 13d, 11d, 10d, 2h, 8h, 9d, 8c, 13h, 2s, 10h, 11h, 11c, 1h, 4d, 1d, 9s, 12c, 6s, 8s, 2c, 3d, 12h, 11s, 12s, 3s, 3h, 4h, 5s, 6c]";
	
	//n=32
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[2c, 5s, 11d, 9h, 2s, 12c, 10s, 11s, 7d, 9s, 13s, 8h, 6d, 1d, 7s, 4h, 11h, 3h, 4c, 13h, 8d, 10h, 1c, 12s, 13c, 6c, 2d, 5d, 3c, 7c, 9c, 12h, 12d, 1s, 2h, 9d, 6h, 8c, 10c, 4s, 10d, 1h, 3d, 7h, 5h, 6s, 13d, 5c, 8s, 4d, 3s, 11c]";
	
	
	//OUTFARO
	//n=2
	//peal 13 26
	//private String desiredDeckString = "[1s, 13d, 1h, 12d, 2s, 11d, 2h, 10d, 3s, 9d, 3h, 8d, 4s, 7d, 4h, 6d, 5s, 5d, 5h, 4d, 6s, 3d, 6h, 2d, 7s, 1d, 7h, 13c, 8s, 12c, 8h, 11c, 9s, 10c, 9h, 9c, 10s, 8c, 10h, 7c, 11s, 6c, 11h, 5c, 12s, 4c, 12h, 3c, 13s, 2c, 13h, 1c]";
	
	//n=4
	//peal 13 26 12 27
	//private String desiredDeckString = "[1s, 13c, 4h, 8s, 13d, 12c, 6d, 8h, 1h, 11c, 5s, 9s, 12d, 10c, 5d, 9h, 2s, 9c, 5h, 10s, 11d, 8c, 4d, 10h, 2h, 7c, 6s, 11s, 10d, 6c, 3d, 11h, 3s, 5c, 6h, 12s, 9d, 4c, 2d, 12h, 3h, 3c, 7s, 13s, 8d, 2c, 1d, 13h, 4s, 1c, 7h, 7d]";
	
	//n=8
	//peal 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[1s, 6d, 10c, 4c, 7c, 9c, 3d, 2d, 9s, 8h, 8s, 12h, 6s, 5h, 11h, 3h, 13c, 1h, 5d, 3c, 11s, 10s, 3s, 7s, 12d, 11c, 13d, 13s, 10d, 11d, 5c, 8d, 4h, 5s, 9h, 2c, 6c, 8c, 6h, 1d, 12c, 4d, 12s, 13h, 2s, 10h, 9d, 4s, 2h, 1c, 7h, 7d]";
	
	//n=16
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[1s, 11c, 13c, 4d, 12h, 6c, 10d, 9s, 7s, 4c, 1d, 3c, 9h, 11h, 3d, 8d, 6d, 13d, 1h, 12s, 6s, 8c, 11d, 8h, 12d, 7c, 12c, 11s, 2c, 3h, 2d, 4h, 10c, 13s, 5d, 13h, 5h, 6h, 5c, 8s, 9c, 10s, 5s, 2s, 3s, 10h, 9d, 4s, 2h, 1c, 7h, 7d]";
	
	//n=32
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	//private String desiredDeckString = "[1s, 12s, 6s, 8s, 6d, 5d, 5c, 1c, 13s, 3s, 2h, 4c, 2s, 8d, 10d, 6c, 4d, 3c, 13c, 9c, 13h, 7h, 10h, 11c, 1h, 13d, 3d, 10s, 5h, 7d, 9d, 12h, 1d, 8c, 9s, 5s, 6h, 9h, 4s, 11d, 11h, 7s, 8h, 12d, 7c, 12c, 11s, 2c, 3h, 2d, 4h, 10c]";
	
	//OUTFAROUP
	//n=2
	//peal 13 26
	//private String desiredDeckString = "[1s, 13d, 2s, 13c, 3s, 12d, 4s, 12c, 5s, 11d, 6s, 11c, 7s, 10d, 8s, 10c, 9s, 9d, 10s, 9c, 11s, 8d, 12s, 8c, 13s, 7d, 1h, 7c, 2h, 6d, 3h, 6c, 4h, 5d, 5h, 5c, 6h, 4d, 7h, 4c, 8h, 3d, 9h, 3c, 10h, 2d, 11h, 2c, 12h, 1d, 13h, 1c]";
	
	//n=4
	//peal 13 26 12 27
	//private String desiredDeckString = "[7h, 7d, 1s, 4c, 13d, 1h, 2s, 8h, 13c, 7c, 3s, 3d, 12d, 2h, 4s, 9h, 12c, 6d, 5s, 3c, 11d, 3h, 6s, 10h, 11c, 6c, 7s, 2d, 10d, 4h, 8s, 11h, 10c, 5d, 9s, 2c, 9d, 5h, 10s, 12h, 9c, 5c, 11s, 1d, 8d, 6h, 12s, 13h, 8c, 4d, 13s, 1c]";
	
	//n=8
	//peal 13 26 12 27 11 25 10 24
	//private String desiredDeckString = "[7h, 7d, 1s, 2d, 4c, 9h, 10d, 2c, 13d, 12c, 4h, 12s, 1h, 6d, 8s, 6s, 2s, 9d, 5c, 4d, 8h, 5s, 11h, 10h, 13c, 13h, 11s, 12h, 7c, 3c, 10c, 11c, 3s, 5h, 1d, 13s, 3d, 11d, 5d, 6c, 12d, 8c, 8d, 9c, 2h, 3h, 9s, 7s, 4s, 10s, 6h, 1c]";
	
	//n=16
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20
	//private String desiredDeckString = "[7h, 7d, 1s, 2d, 4c, 9h, 10d, 3c, 2c, 5c, 10c, 9s, 8c, 5s, 6d, 5d, 13d, 5h, 13c, 10s, 4d, 2h, 3d, 2s, 11c, 12s, 7s, 12h, 8d, 11h, 8s, 6c, 12c, 1d, 13h, 6h, 8h, 3h, 11d, 9d, 3s, 1h, 4s, 7c, 9c, 10h, 6s, 12d, 4h, 13s, 11s, 1c]";
	
	//n=32
	//peal 13 26 12 27 11 25 10 24 9 23 8 22 7 21 6 20 2 4 8 10 11 12 2 33 20 21 5 6 7 8 9 10
	private String desiredDeckString = "[10s, 4d, 2h, 3d, 2s, 11c, 12s, 7s, 12h, 8d, 7c, 11d, 11h, 4c, 9d, 6d, 5c, 9c, 8s, 1h, 12d, 9h, 7h, 5d, 10c, 3h, 13h, 1d, 11s, 10d, 7d, 13d, 9s, 13s, 3s, 4h, 6s, 10h, 8h, 2c, 4s, 2d, 3c, 13c, 1s, 5s, 5h, 6h, 8c, 6c, 12c, 1c]";
	//END TEST 1
	
	
	
	
	//private String desiredDeckString = "[1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s]";
	
	//Mnemonica
	//private String desiredDeckString = "[4c, 2h, 7d, 3c, 4h, 6d, 1s, 5h, 9s, 2s, 12h, 3d, 12c, 8h, 6s, 5s, 9h, 13c, 2d, 11h, 3s, 8s, 6h, 10c, 5d, 13d, 2c, 3h, 8d, 5c, 13s, 11d, 8c, 10s, 13h, 11c, 7s, 10h, 1d, 4s, 7h, 4d, 1c, 9c, 11s, 12d, 7c, 12s, 10d, 6c, 1h, 9d]";
		
	//dos outfaro 26
	//private String desiredDeckString="[1s, 1h, 13d, 13c, 2s, 2h, 12d, 12c, 3s, 3h, 11d, 11c, 4s, 4h, 10d, 10c, 5s, 5h, 9d, 9c, 6s, 6h, 8d, 8c, 7s, 7h, 7d, 7c, 8s, 8h, 6d, 6c, 9s, 9h, 5d, 5c, 10s, 10h, 4d, 4c, 11s, 11h, 3d, 3c, 12s, 12h, 2d, 2c, 13s, 13h, 1d, 1c]";
	
	//dos out faro 26 y pelar 40
	//private String desiredDeckString="[4c, 4d, 10h, 10s, 5c, 5d, 9h, 9s, 6c, 6d, 8h, 8s, 7c, 7d, 7h, 7s, 8c, 8d, 6h, 6s, 9c, 9d, 5h, 5s, 10c, 10d, 4h, 4s, 11c, 11d, 3h, 3s, 12c, 12d, 2h, 2s, 13c, 13d, 1h, 1s, 11s, 11h, 3d, 3c, 12s, 12h, 2d, 2c, 13s, 13h, 1d, 1c]";
	
	//dos outfaro 25 pelar 40 slipcut ?
	//private String desiredDeckString="[4d, 10h, 10s, 5c, 5d, 9h, 9s, 6c, 6d, 8h, 8s, 7c, 7d, 7h, 7s, 8c, 8d, 6h, 6s, 9c, 9d, 5h, 5s, 10c, 4c, 10d, 4h, 4s, 11c, 11d, 3h, 3s, 12c, 12d, 2h, 2s, 13c, 13d, 1h, 1s, 11s, 11h, 3d, 3c, 12s, 12h, 2d, 2c, 13s, 13h, 1d, 1c]";
	
	//
	//private String desiredDeckString="[4c, 4h, 4d, 4s, 10h, 11c, 10s, 11d, 5c, 3h, 5d, 3s, 9h, 12c, 9s, 12d, 6c, 2h, 6d, 2s, 8h, 13c, 8s, 13d, 7c, 1h, 7d, 1s, 7h, 11s, 7s, 11h, 8c, 3d, 8d, 3c, 6h, 12s, 6s, 12h, 9c, 2d, 9d, 2c, 5h, 13s, 5s, 13h, 10c, 1d, 10d, 1c]";
	//private String desiredDeckString="[4c, 7s, 4d, 8c, 10h, 8d, 10s, 6h, 5c, 6s, 5d, 9c, 12c, 9h, 12d, 9d, 2h, 9s, 2s, 5h, 13c, 6c, 13d, 5s, 1h, 6d, 1s, 10c, 11s, 8h, 11h, 10d, 3d, 8s, 3c, 4h, 12s, 7c, 12h, 4s, 2d, 7d, 2c, 11c, 13s, 7h, 13h, 11d, 1d, 3h, 1c, 3s]";

	
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
	private int MUTATIONUMBER=0; //Number of mutations (technique+number)
	private int MUTATIONOPERATION=0; //Number of mutations (technique+number)
	
	private int DEPTH=4; //INITIAL DEPTH OF TREES!! tree size / 2
	private int LENGTHMAXOFTREE=20*2;
	
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
		 //randomOperation=operators[randomIntBetween(0,this.operators.length-1)];
	     String number=Integer.toString(randomIntBetween(this.MIN,this.MAX));
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
            
    		Tuple tuple = new Tuple(clonedTree, points);
    		
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
    	//Update puntuations
    	for(int i =0; i<CROSSOVERNUMBER-1;i++){
    		ZuhaitzBitarra<String> tree=this.population.get(i).getFirstElement();
    		//int deckDistance=testIndividualWithDistanceVector(this.initialDeckString, this.desiredDeckString, tree);
    		int deckDistance=testIndividualWithHammingDistance(this.initialDeckString, this.desiredDeckString, tree);
    		Tuple<ZuhaitzBitarra<String>, Integer> tuple =new Tuple<ZuhaitzBitarra<String>, Integer>(tree, deckDistance);
    		this.population.set(i, tuple);		
    	}
    	//4-quicksort everything so that it gets back to normal
    	this.quickSortPopulation(0, this.population.size()-1);
    	//5-delete the last elements of the population (the last CROSSOVERNUMBERS)
    	for (int i=this.population.size()-1; i>this.POPULATIONSIZE; i--)
    		this.population.remove(i);
    	
    }		

    
	public void pointsToCSV(String techniqueName, ArrayList<Integer> Points1n2,ArrayList<Integer> Points2n2,ArrayList<Integer> Points3n2,ArrayList<Integer> Points1n4,ArrayList<Integer> Points2n4,ArrayList<Integer> Points3n4,ArrayList<Integer> Points1n8,ArrayList<Integer> Points2n8,ArrayList<Integer> Points3n8,ArrayList<Integer> Points1n16,ArrayList<Integer> Points2n16,ArrayList<Integer> Points3n16,ArrayList<Integer> Points1n32,ArrayList<Integer> Points2n32,ArrayList<Integer> Points3n32){
		System.out.println(Points1n2.size());
		System.out.println(Points2n2.size());
		System.out.println(Points3n2.size());
		System.out.println(Points1n4.size());
		System.out.println(Points2n4.size());
		System.out.println(Points3n4.size());
		System.out.println(Points1n8.size());
		System.out.println(Points2n8.size());
		System.out.println(Points3n8.size());
		System.out.println(Points1n16.size());
		System.out.println(Points2n16.size());
		System.out.println(Points3n16.size());
		System.out.println(Points1n32.size());
		System.out.println(Points2n32.size());
		System.out.println(Points3n32.size());
		try{
		 		FileWriter csvWriter = new FileWriter("C://Users//Javi//Documents//GitHub//GeneticProgramming//Experiments//"+techniqueName+".csv");
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
		 			csvWriter.append("\n");
		 			
		 		}
		 		
		 		csvWriter.flush();
		 		csvWriter.close();
		 	}
		 	catch(Exception e){}
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		
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
		
		//String technique = "INFARO";
		//n=2
		//String Deck1= "[13d, 1h, 12d, 1s, 11d, 2h, 10d, 2s, 9d, 3h, 8d, 3s, 7d, 4h, 6d, 4s, 5d, 5h, 4d, 5s, 3d, 6h, 2d, 6s, 1d, 7h, 13c, 7s, 12c, 8h, 11c, 8s, 10c, 9h, 9c, 9s, 8c, 10h, 7c, 10s, 6c, 11h, 5c, 11s, 4c, 12h, 3c, 12s, 2c, 13h, 1c, 13s]";
		//n=4
		//String Deck2= "[4h, 6d, 12c, 13d, 8h, 4s, 11c, 1h, 8s, 5d, 10c, 12d, 9h, 5h, 9c, 1s, 9s, 4d, 8c, 11d, 10h, 5s, 7c, 2h, 10s, 3d, 6c, 10d, 11h, 6h, 5c, 2s, 11s, 2d, 4c, 9d, 12h, 6s, 3c, 3h, 12s, 1d, 2c, 8d, 13h, 7h, 1c, 3s, 13s, 13c, 7d, 7s]";
		//n=8
		//String Deck3= "[6s, 5c, 4d, 3d, 3c, 12c, 11c, 12d, 3h, 2s, 8c, 6c, 12s, 9c, 1h, 4h, 1d, 11s, 11d, 10d, 2c, 13d, 8s, 9h, 8d, 2d, 10h, 11h, 13h, 1s, 5d, 6d, 7h, 4c, 5s, 6h, 1c, 8h, 10c, 5h, 3s, 9d, 7c, 9s, 13s, 12h, 2h, 4s, 13c, 10s, 7d, 7s]";
		//n=16
		//String Deck4= "[12d, 13h, 9c, 5s, 7c, 1d, 4d, 9h, 6d, 12c, 8h, 6c, 10d, 3s, 10h, 6s, 3h, 1s, 1h, 6h, 9s, 11s, 3d, 8d, 7h, 11c, 10c, 12s, 2c, 9d, 11h, 5c, 2s, 5d, 4h, 1c, 13s, 11d, 3c, 2d, 4c, 5h, 13d, 8c, 12h, 8s, 2h, 4s, 13c, 10s, 7d, 7s]";
		//n=32
		//String Deck5= "[11s, 3c, 4h, 8c, 5s, 13h, 6c, 5d, 7d, 3h, 1d, 10h, 4c, 10s, 8s, 6d, 9h, 2d, 1c, 12h, 12d, 9s, 7s, 3s, 5h, 2h, 6s, 13s, 12c, 1s, 10d, 8h, 13d, 4s, 3d, 11d, 4d, 7c, 1h, 6h, 8d, 13c, 9c, 7h, 11c, 10c, 12s, 2c, 9d, 11h, 5c, 2s]";

		//String technique = "CUT";
		//n=2
		//String Deck1= "[13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d]";
		//n=4
		//String Deck2= "[11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d]";
		//n=8
		//String Deck3= "[6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c]";
		//n=16
		//String Deck4= "[7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s]";
		//n=32
		//String Deck5= "[6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h]";

		
		//String technique = "PEAL";
		//n=2
		//String Deck1= "[13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=4
		//String Deck2= "[13d, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=8
		//String Deck3= "[5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 13d, 3s, 2s, 13h, 12h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 4s, 1h, 1s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=16
		//String Deck4= "[7s, 8s, 9s, 10s, 11s, 12s, 5s, 13d, 3s, 2s, 13h, 12h, 2h, 10h, 4h, 5h, 6h, 7h, 8h, 9h, 6s, 3h, 13s, 11h, 4s, 1h, 1s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=32
		//String Deck5= "[4s, 1s, 11d, 2h, 7d, 8d, 9d, 10d, 12d, 1h, 11h, 13s, 3h, 6s, 9h, 8h, 7h, 6h, 5h, 4h, 10h, 13h, 13d, 5s, 12s, 11s, 8s, 7s, 9s, 10s, 3s, 12h, 2s, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

		
		//String technique = "SLIPCUT";
		//n=2
		//String Deck1= "[3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=4
		//String Deck2= "[5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1s, 1h, 2h, 3s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=8
		//String Deck3= "[9s, 10s, 11s, 12s, 13s, 1s, 1h, 5s, 7s, 2h, 3s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 8s, 6s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=16
		//String Deck4= "[7s, 9s, 11s, 13s, 1h, 2h, 3s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2s, 5s, 1s, 12s, 10s, 8s, 6s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=32
		//String Deck5= "[6h, 7s, 13s, 4h, 7h, 5h, 9s, 11s, 8h, 3h, 9h, 10h, 11h, 12h, 13h, 2s, 5s, 1s, 1h, 12s, 3s, 10s, 8s, 6s, 13d, 4s, 12d, 11d, 10d, 9d, 8d, 7d, 2h, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

		
		//String technique = "SLIPCUTUP";
		//n=2
		//String Deck1= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 1c, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c]";
		//n=4
		//String Deck2= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 3c, 2d, 1d, 1c, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";
		//n=8
		//String Deck3= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 6c, 8c, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 3c, 2d, 7c, 5c, 1d, 1c, 13c, 12c, 11c, 10c, 9c]";
		//n=16
		//String Deck4= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 6c, 8c, 10c, 12c, 1c, 5c, 2c, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 3c, 2d, 1d, 13c, 11c, 9c, 7c]";
		//n=32
		//String Deck5= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 2d, 7h, 8h, 9h, 10h, 11h, 12h, 4c, 13h, 6c, 8c, 10c, 3c, 12c, 1d, 1c, 5c, 2c, 13d, 12d, 11d, 10d, 9d, 3d, 8d, 11c, 9c, 5d, 7d, 4d, 13c, 7c, 6d]";

		
		//String technique = "PEALUP";
		//n=2
		//String Deck1= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d]";
		//n=4
		//String Deck2= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 1c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 13h]";
		//n=8
		//String Deck3= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 1c, 1d, 4c, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 12d, 13d, 2c, 3c, 13h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c]";
		//n=16
		//String Deck4= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 1c, 1d, 4c, 11d, 13c, 3d, 6c, 9d, 8d, 7d, 6d, 5d, 4d, 10d, 2d, 12d, 13d, 2c, 3c, 13h, 5c, 12c, 11c, 10c, 9c, 8c, 7c]";
		//n=32
		//String Deck5= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1h, 2h, 3h, 4h, 5h, 6h, 2c, 12d, 3c, 10c, 9c, 7c, 8c, 11c, 12c, 5c, 13h, 13d, 10d, 4d, 5d, 6d, 7d, 8d, 9d, 6c, 3d, 13c, 11d, 1d, 12h, 10h, 9h, 8h, 7h, 2d, 11h, 1c, 4c]";

		
		//String technique = "INFAROUP";
		//n=2
		//String Deck1= "[13c, 1s, 13d, 2s, 12c, 3s, 12d, 4s, 11c, 5s, 11d, 6s, 10c, 7s, 10d, 8s, 9c, 9s, 9d, 10s, 8c, 11s, 8d, 12s, 7c, 13s, 7d, 1h, 6c, 2h, 6d, 3h, 5c, 4h, 5d, 5h, 4c, 6h, 4d, 7h, 3c, 8h, 3d, 9h, 2c, 10h, 2d, 11h, 1c, 12h, 1d, 13h]";
		//n=4
		//String Deck2= "[7c, 7h, 13s, 13c, 3c, 1s, 7d, 13d, 8h, 2s, 1h, 12c, 3d, 3s, 6c, 12d, 9h, 4s, 2h, 11c, 2c, 5s, 6d, 11d, 10h, 6s, 3h, 10c, 2d, 7s, 5c, 10d, 11h, 8s, 4h, 9c, 1c, 9s, 5d, 9d, 12h, 10s, 5h, 8c, 1d, 11s, 4c, 8d, 13h, 12s, 6h, 4d]";
		//n=8
		//String Deck3= "[7c, 7h, 10c, 13s, 4c, 2d, 12d, 13c, 9c, 7s, 9h, 3c, 5d, 10s, 8d, 1s, 6d, 5c, 4s, 7d, 6h, 5h, 1c, 13d, 11d, 10d, 2h, 8h, 9d, 8c, 13h, 2s, 10h, 11h, 11c, 1h, 4d, 1d, 9s, 12c, 6s, 8s, 2c, 3d, 12h, 11s, 12s, 3s, 3h, 4h, 5s, 6c]";
		//n=16
		//String Deck4= "[7c, 7h, 10c, 13s, 4c, 2d, 8c, 12d, 8s, 13h, 5d, 4s, 2h, 3s, 11h, 13c, 1s, 4d, 5h, 2c, 5s, 11d, 9h, 2s, 12c, 10s, 11s, 7d, 8h, 3h, 11c, 9c, 6d, 1d, 1c, 3d, 6c, 10d, 3c, 10h, 6s, 8d, 12s, 6h, 9d, 4h, 1h, 7s, 5c, 9s, 13d, 12h]";
		//n=32
		//String Deck5= "[2c, 5s, 11d, 9h, 2s, 12c, 10s, 11s, 7d, 9s, 13s, 8h, 6d, 1d, 7s, 4h, 11h, 3h, 4c, 13h, 8d, 10h, 1c, 12s, 13c, 6c, 2d, 5d, 3c, 7c, 9c, 12h, 12d, 1s, 2h, 9d, 6h, 8c, 10c, 4s, 10d, 1h, 3d, 7h, 5h, 6s, 13d, 5c, 8s, 4d, 3s, 11c]";

		
		//String technique = "OUTFARO";
		//n=2
		//String Deck1= "[1s, 13d, 1h, 12d, 2s, 11d, 2h, 10d, 3s, 9d, 3h, 8d, 4s, 7d, 4h, 6d, 5s, 5d, 5h, 4d, 6s, 3d, 6h, 2d, 7s, 1d, 7h, 13c, 8s, 12c, 8h, 11c, 9s, 10c, 9h, 9c, 10s, 8c, 10h, 7c, 11s, 6c, 11h, 5c, 12s, 4c, 12h, 3c, 13s, 2c, 13h, 1c]";
		//n=4
		//String Deck2= "[1s, 13c, 4h, 8s, 13d, 12c, 6d, 8h, 1h, 11c, 5s, 9s, 12d, 10c, 5d, 9h, 2s, 9c, 5h, 10s, 11d, 8c, 4d, 10h, 2h, 7c, 6s, 11s, 10d, 6c, 3d, 11h, 3s, 5c, 6h, 12s, 9d, 4c, 2d, 12h, 3h, 3c, 7s, 13s, 8d, 2c, 1d, 13h, 4s, 1c, 7h, 7d]";
		//n=8
		//String Deck3= "[1s, 6d, 10c, 4c, 7c, 9c, 3d, 2d, 9s, 8h, 8s, 12h, 6s, 5h, 11h, 3h, 13c, 1h, 5d, 3c, 11s, 10s, 3s, 7s, 12d, 11c, 13d, 13s, 10d, 11d, 5c, 8d, 4h, 5s, 9h, 2c, 6c, 8c, 6h, 1d, 12c, 4d, 12s, 13h, 2s, 10h, 9d, 4s, 2h, 1c, 7h, 7d]";
		//n=16
		//String Deck4= "[1s, 11c, 13c, 4d, 12h, 6c, 10d, 9s, 7s, 4c, 1d, 3c, 9h, 11h, 3d, 8d, 6d, 13d, 1h, 12s, 6s, 8c, 11d, 8h, 12d, 7c, 12c, 11s, 2c, 3h, 2d, 4h, 10c, 13s, 5d, 13h, 5h, 6h, 5c, 8s, 9c, 10s, 5s, 2s, 3s, 10h, 9d, 4s, 2h, 1c, 7h, 7d]";
		//n=32
		//String Deck5= "[1s, 12s, 6s, 8s, 6d, 5d, 5c, 1c, 13s, 3s, 2h, 4c, 2s, 8d, 10d, 6c, 4d, 3c, 13c, 9c, 13h, 7h, 10h, 11c, 1h, 13d, 3d, 10s, 5h, 7d, 9d, 12h, 1d, 8c, 9s, 5s, 6h, 9h, 4s, 11d, 11h, 7s, 8h, 12d, 7c, 12c, 11s, 2c, 3h, 2d, 4h, 10c]";

		
		//String technique = "OUTFAROUP";
		//n=2
		//String Deck1= "[1s, 13d, 2s, 13c, 3s, 12d, 4s, 12c, 5s, 11d, 6s, 11c, 7s, 10d, 8s, 10c, 9s, 9d, 10s, 9c, 11s, 8d, 12s, 8c, 13s, 7d, 1h, 7c, 2h, 6d, 3h, 6c, 4h, 5d, 5h, 5c, 6h, 4d, 7h, 4c, 8h, 3d, 9h, 3c, 10h, 2d, 11h, 2c, 12h, 1d, 13h, 1c]";
		//n=4
		//String Deck2= "[7h, 7d, 1s, 4c, 13d, 1h, 2s, 8h, 13c, 7c, 3s, 3d, 12d, 2h, 4s, 9h, 12c, 6d, 5s, 3c, 11d, 3h, 6s, 10h, 11c, 6c, 7s, 2d, 10d, 4h, 8s, 11h, 10c, 5d, 9s, 2c, 9d, 5h, 10s, 12h, 9c, 5c, 11s, 1d, 8d, 6h, 12s, 13h, 8c, 4d, 13s, 1c]";
		//n=8
		//String Deck3= "[7h, 7d, 1s, 2d, 4c, 9h, 10d, 2c, 13d, 12c, 4h, 12s, 1h, 6d, 8s, 6s, 2s, 9d, 5c, 4d, 8h, 5s, 11h, 10h, 13c, 13h, 11s, 12h, 7c, 3c, 10c, 11c, 3s, 5h, 1d, 13s, 3d, 11d, 5d, 6c, 12d, 8c, 8d, 9c, 2h, 3h, 9s, 7s, 4s, 10s, 6h, 1c]";
		//n=16
		//String Deck4= "[7h, 7d, 1s, 2d, 4c, 9h, 10d, 3c, 2c, 5c, 10c, 9s, 8c, 5s, 6d, 5d, 13d, 5h, 13c, 10s, 4d, 2h, 3d, 2s, 11c, 12s, 7s, 12h, 8d, 11h, 8s, 6c, 12c, 1d, 13h, 6h, 8h, 3h, 11d, 9d, 3s, 1h, 4s, 7c, 9c, 10h, 6s, 12d, 4h, 13s, 11s, 1c]";
		//n=32
		//String Deck5= "[10s, 4d, 2h, 3d, 2s, 11c, 12s, 7s, 12h, 8d, 7c, 11d, 11h, 4c, 9d, 6d, 5c, 9c, 8s, 1h, 12d, 9h, 7h, 5d, 10c, 3h, 13h, 1d, 11s, 10d, 7d, 13d, 9s, 13s, 3s, 4h, 6s, 10h, 8h, 2c, 4s, 2d, 3c, 13c, 1s, 5s, 5h, 6h, 8c, 6c, 12c, 1c]";

		
		
		
		//String technique = "CUTTING-INSERTION";
		//n=2
		//String Deck1= "[2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 1h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s]";
		//n=4
		//String Deck2= "[10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 1h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 11d, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13d, 12d]";
		//n=8
		//String Deck3= "[1c, 1s, 2s, 11d, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 13c, 12s, 13s, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 2c, 9h, 10h, 11h, 12h, 13h, 13d, 12d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 1h, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c]";
		//n=16
		//String Deck4= "[8d, 7d, 6d, 5d, 4d, 3d, 4h, 2d, 1d, 1h, 12c, 11c, 10c, 11h, 9c, 8c, 7c, 6c, 5c, 9d, 4c, 3c, 1c, 1s, 2s, 11d, 3s, 4s, 5s, 6s, 7s, 9s, 10s, 11s, 13c, 12s, 13s, 2h, 3h, 5h, 6h, 7h, 8h, 2c, 9h, 10h, 12h, 13h, 13d, 12d, 10d, 8s]";
		//n=32
		//String Deck5= "[9c, 8c, 7c, 6c, 5c, 9d, 12c, 4c, 1c, 11h, 1s, 11d, 3s, 4s, 5s, 6s, 7s, 9s, 10s, 3c, 11s, 13c, 12s, 13s, 2h, 3h, 5h, 6h, 7h, 8h, 2c, 10h, 12h, 13h, 13d, 12d, 8s, 8d, 7d, 5d, 4d, 10d, 2s, 6d, 4h, 2d, 1d, 1h, 11c, 10c, 3d, 9h]";

		
		//String technique = "CUTTING-INTERCALATION";
		//n=2
		//String Deck1= "[13c, 1h, 12c, 2h, 11c, 3h, 10c, 4h, 9c, 5h, 8c, 6h, 7c, 7h, 6c, 8h, 5c, 9h, 4c, 10h, 3c, 11h, 2c, 12h, 1c, 13h, 1s, 13d, 2s, 12d, 3s, 11d, 4s, 10d, 5s, 9d, 6s, 8d, 7s, 7d, 8s, 6d, 9s, 5d, 10s, 4d, 11s, 3d, 12s, 2d, 13s, 1d]";
		//n=4
		//String Deck2= "[6d, 6c, 9s, 8h, 5d, 5c, 10s, 9h, 4d, 4c, 11s, 10h, 3d, 3c, 12s, 11h, 2d, 2c, 13s, 12h, 1d, 1c, 13c, 13h, 1h, 1s, 12c, 13d, 2h, 2s, 11c, 12d, 3h, 3s, 10c, 11d, 4h, 4s, 9c, 10d, 5h, 5s, 8c, 9d, 6h, 6s, 7c, 8d, 7h, 7s, 7d, 8s]";
		//n=8
		//String Deck3= "[6c, 5s, 2h, 2d, 9s, 8c, 2s, 2c, 8h, 9d, 11c, 13s, 5d, 6h, 12d, 12h, 5c, 6s, 3h, 1d, 10s, 7c, 3s, 1c, 9h, 8d, 10c, 13c, 4d, 7h, 11d, 13h, 4c, 7s, 11s, 1h, 4h, 7d, 10h, 1s, 4s, 8s, 3d, 12c, 9c, 6d, 3c, 13d, 10d, 12s, 5h, 11h]";
		//n=16
		//String Deck4= "[3c, 10s, 6c, 10c, 8h, 2d, 8s, 12h, 1c, 9c, 7h, 5h, 4c, 9s, 3h, 1s, 9d, 7c, 5s, 13c, 7s, 8c, 3d, 5c, 11c, 6d, 11d, 11h, 11s, 2s, 1d, 4s, 13s, 3s, 1h, 4d, 5d, 2c, 13d, 6s, 4h, 9h, 6h, 10d, 7d, 8d, 2h, 12d, 12c, 12s, 13h, 10h]";
		//n=32
		//String Deck5= "[1d, 3h, 4d, 8h, 1s, 13c, 12h, 1h, 10d, 6d, 9d, 2d, 4s, 6h, 1c, 11d, 7d, 3s, 5d, 8s, 13s, 9c, 11h, 7s, 2c, 7h, 7c, 8c, 13d, 5h, 11s, 8d, 3d, 2h, 6s, 12d, 4c, 12c, 5s, 12s, 5c, 13h, 4h, 10h, 9s, 3c, 2s, 10s, 11c, 6c, 9h, 10c]";

		
		//String technique = "CUTTING-INVERSION";
		//n=2
		//String Deck1= "[1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s]";
		//n=4
		//String Deck2= "[2s, 1s, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 10c, 11c, 12c, 13c, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 13h]";
		//n=8
		//String Deck3= "[7d, 6d, 5d, 4d, 3d, 2d, 1d, 13s, 12s, 10c, 11c, 12c, 13c, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 8d, 9d, 10d, 11d, 12d, 13d, 13h, 2s, 1s, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 12h]";
		//n=16
		//String Deck4= "[4s, 5s, 6s, 7s, 8s, 10c, 11c, 12c, 13c, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 3s, 12h, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13s, 12s, 2s, 13h, 13d, 12d, 11d, 10d, 9d, 8d, 6c, 5c, 4c, 3c, 2c, 1c, 1s, 9s, 10s, 11s, 9c, 8c, 7c]";
		//n=32
		//String Deck5= "[13h, 3s, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 2s, 8s, 10c, 5s, 4s, 7c, 8c, 9c, 11s, 10s, 9s, 1s, 1c, 2c, 3c, 4c, 5c, 6c, 8d, 9d, 10d, 10h, 11h, 1h, 13c, 12c, 1d, 13s, 12s, 7s, 6s, 11c, 2d, 12d, 13d, 12h, 7d, 6d, 5d, 4d, 3d, 11d]";

		
		//String technique = "INSERTION-CUTTING";
		//n=2
		//String Deck1= "[13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h]";
		//n=4
		//String Deck2= "[3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 13d, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 2s]";
		//n=8
		//String Deck3= "[2c, 1c, 2s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 3s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 12d, 2d, 1d, 13c, 13d, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c]";
		//n=16
		//String Deck4= "[5d, 4d, 3d, 12d, 2d, 1d, 13c, 13d, 12c, 11c, 10c, 8c, 7c, 6c, 5c, 4c, 3c, 9c, 1c, 2s, 4s, 5s, 6s, 7s, 8s, 9s, 2c, 10s, 11s, 12s, 13s, 3s, 1h, 2h, 3h, 4h, 5h, 1s, 6h, 7h, 8h, 10h, 11h, 12h, 13h, 11d, 10d, 9d, 9h, 8d, 7d, 6d]";
		//n=32
		//String Deck5= "[4d, 5d, 3d, 12d, 1d, 13c, 13d, 11c, 10c, 8c, 2d, 7c, 6c, 4c, 3c, 9c, 1c, 2s, 4s, 5s, 6s, 7s, 8s, 5c, 9s, 10s, 12c, 2c, 12s, 13s, 3s, 1h, 11s, 2h, 4h, 5h, 1s, 6h, 7h, 8h, 3h, 10h, 12h, 13h, 11d, 10d, 9d, 9h, 8d, 7d, 11h, 6d]";

		
		//String technique = "INSERTION-INTERCALATION";
		//n=2
		//String Deck1= "[13d, 2s, 12d, 3s, 11d, 4s, 10d, 5s, 9d, 6s, 8d, 7s, 7d, 8s, 6d, 9s, 5d, 10s, 4d, 11s, 3d, 12s, 2d, 13s, 1d, 1s, 13c, 1h, 12c, 2h, 11c, 3h, 10c, 4h, 9c, 5h, 8c, 6h, 7c, 7h, 6c, 8h, 5c, 9h, 4c, 10h, 3c, 11h, 2c, 12h, 1c, 13h]";
		//n=4
		//String Deck2= "[1h, 2s, 12c, 12d, 2h, 3s, 11c, 11d, 3h, 4s, 10c, 10d, 4h, 5s, 9c, 9d, 5h, 6s, 8c, 8d, 6h, 7s, 7c, 7d, 7h, 8s, 6c, 13d, 8h, 6d, 5c, 9s, 9h, 5d, 4c, 10s, 10h, 4d, 3c, 11s, 11h, 3d, 2c, 12s, 12h, 2d, 1c, 13s, 13h, 1d, 1s, 13c]";
		//n=8
		//String Deck3= "[4d, 2s, 4h, 6c, 3c, 12c, 5s, 13d, 11s, 12d, 9c, 8h, 11h, 2h, 9d, 6d, 3d, 3s, 5h, 8s, 2c, 5c, 6s, 11c, 12s, 9s, 8c, 11d, 12h, 9h, 8d, 3h, 2d, 5d, 6h, 4s, 1c, 4c, 7s, 10c, 13s, 10s, 7c, 1h, 13h, 10h, 7d, 10d, 1d, 7h, 1s, 13c]";
		//n=16
		//String Deck4= "[11s, 2s, 2h, 3d, 7c, 8h, 8c, 5s, 3h, 12s, 4c, 12h, 8s, 13s, 3c, 4s, 4d, 9h, 9d, 4h, 1h, 3s, 11c, 11h, 2d, 13d, 7s, 6h, 2c, 10s, 11d, 9s, 12d, 8d, 6d, 1c, 13h, 5h, 12c, 6c, 5d, 10c, 5c, 9c, 10h, 6s, 7d, 10d, 1d, 7h, 1s, 13c]";
		//n=32
		//String Deck5= "[7d, 6d, 13h, 13s, 7c, 3h, 12h, 4d, 10d, 6s, 5h, 11s, 12s, 1c, 9h, 4h, 1d, 10h, 12c, 5s, 3c, 2h, 7h, 6c, 1h, 2s, 1s, 5d, 8h, 8s, 13c, 10c, 3s, 9d, 4s, 5c, 11c, 3d, 4c, 8d, 11h, 9c, 8c, 2d, 13d, 7s, 6h, 2c, 10s, 11d, 9s, 12d]";

		
		//String technique = "INSERTION-INVERSION";
		//n=2
		//String Deck1= "[13h, 12h, 11h, 10h, 9h, 8h, 7h, 6h, 5h, 4h, 3h, 2h, 1h, 1s, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=4
		//String Deck2= "[13d, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 13s, 13h, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=8
		//String Deck3= "[3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 13d, 12s, 13s, 13h, 1s, 10h, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 2s, 11h, 12h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=16
		//String Deck4= "[7s, 8s, 9s, 10s, 11s, 5s, 3s, 13d, 12s, 13s, 13h, 1s, 10h, 8h, 6h, 1h, 2h, 3h, 4h, 5h, 6s, 7h, 4s, 9h, 2s, 11h, 12h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=32
		//String Deck5= "[6s, 4h, 5h, 10h, 1h, 6h, 8h, 7d, 3h, 2h, 7h, 4s, 9h, 2s, 11h, 12h, 12d, 11d, 10d, 9d, 8d, 12s, 10s, 13d, 3s, 5s, 11s, 8s, 7s, 9s, 13h, 1s, 13s, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

		
		//String technique = "INTERCALATION-CUTTING";
		//n=2
		//String Deck1= "[13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 1h, 1s, 2h, 2s, 3h, 3s, 4h, 4s, 5h, 5s, 6h, 6s, 7h, 7s, 8h, 8s, 9h, 9s, 10h, 10s, 11h, 11s, 12h, 12s, 13h, 13s]";
		//n=4
		//String Deck2= "[13c, 2h, 2s, 3h, 3s, 4h, 4s, 5h, 5s, 6h, 6s, 7h, 7s, 8h, 8s, 9h, 9s, 10h, 10s, 11h, 11s, 12h, 12s, 13h, 13s, 12c, 13d, 11c, 12d, 10c, 11d, 9c, 10d, 8c, 9d, 7c, 8d, 6c, 7d, 5c, 6d, 4c, 5d, 3c, 4d, 2c, 3d, 1c, 2d, 1h, 1d, 1s]";
		//n=8
		//String Deck3= "[1h, 1d, 1s, 7h, 13c, 7s, 2h, 8h, 2s, 8s, 3h, 9h, 3s, 9s, 4h, 10h, 4s, 10s, 5h, 11h, 5s, 11s, 6h, 12h, 6s, 12s, 13h, 13s, 7c, 12c, 8d, 13d, 6c, 11c, 7d, 12d, 5c, 10c, 6d, 11d, 4c, 9c, 5d, 10d, 3c, 8c, 4d, 9d, 2c, 3d, 1c, 2d]";
		//n=16
		//String Deck4= "[7d, 12d, 5c, 10c, 6d, 11d, 4c, 9c, 5d, 10d, 3c, 8c, 4d, 9d, 2c, 3d, 1c, 2d, 1h, 1d, 1s, 7h, 13c, 7s, 2h, 8h, 2s, 8s, 3h, 9h, 3s, 9s, 4h, 10h, 4s, 10s, 5h, 11h, 5s, 11s, 6h, 12h, 6s, 12s, 13h, 13s, 7c, 12c, 8d, 13d, 6c, 11c]";
		//n=32
		//String Deck5= "[7d, 12d, 5c, 10c, 6d, 11d, 4c, 1s, 9c, 4h, 5d, 7h, 10d, 10h, 3c, 13c, 8c, 4s, 4d, 7s, 9d, 10s, 8h, 2h, 2c, 5h, 2s, 11h, 3d, 5s, 8s, 11s, 1c, 6h, 3h, 12h, 2d, 6s, 9h, 12s, 1h, 13h, 3s, 13s, 1d, 7c, 9s, 12c, 8d, 13d, 6c, 11c]";

		
		
		//String technique = "INTERCALATION-INSERTION";
		//n=2
		//String Deck1= "[1s, 2h, 2s, 3h, 3s, 4h, 4s, 5h, 5s, 6h, 6s, 7h, 7s, 8h, 8s, 9h, 9s, 10h, 10s, 11h, 11s, 12h, 12s, 13h, 13s, 1h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=4
		//String Deck2= "[1s, 9h, 2h, 9s, 2s, 10h, 3h, 10s, 3s, 11h, 4h, 11s, 4s, 12h, 5h, 12s, 5s, 13h, 6h, 13s, 6s, 1h, 7h, 13d, 7s, 12d, 8s, 8h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=8
		//String Deck3= "[1s, 13h, 4s, 3h, 9h, 6h, 12h, 10s, 2h, 13s, 5h, 3s, 9s, 6s, 12s, 11h, 2s, 1h, 5s, 4h, 7h, 13d, 7s, 10h, 11s, 12d, 8s, 8h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=16
		//String Deck4= "[9h, 6h, 12h, 10s, 2h, 13s, 5h, 3s, 9s, 6s, 12s, 11h, 2s, 1h, 5s, 4h, 7h, 13d, 7s, 3h, 4s, 13h, 1s, 10h, 11s, 12d, 8s, 8h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=32
		//String Deck5= "[10h, 1h, 11s, 2s, 9h, 12d, 1s, 5s, 8s, 10s, 2h, 8h, 4h, 11d, 13s, 10d, 7h, 9d, 5h, 8d, 12s, 11h, 7d, 13d, 12h, 3s, 6d, 7s, 5d, 9s, 4d, 3h, 3d, 6s, 2d, 4s, 1d, 6h, 13c, 13h, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

		
		//String technique = "INTERCALATION-INVERSION";
		//n=2
		//String Deck1= "[13s, 13h, 12s, 12h, 11s, 11h, 10s, 10h, 9s, 9h, 8s, 8h, 7s, 7h, 6s, 6h, 5s, 5h, 4s, 4h, 3s, 3h, 2s, 2h, 1s, 1h, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=4
		//String Deck2= "[12d, 7s, 13d, 8h, 1h, 8s, 1s, 9h, 2h, 9s, 2s, 10h, 3h, 10s, 3s, 11h, 4h, 11s, 4s, 12h, 5h, 12s, 5s, 13h, 6h, 13s, 6s, 7h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=8
		//String Deck3= "[12d, 3h, 7s, 10s, 9h, 13d, 12h, 3s, 2h, 8h, 5h, 11h, 9s, 1h, 12s, 4h, 2s, 8s, 5s, 11s, 13h, 1s, 6h, 4s, 10h, 13s, 6s, 7h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=16
		//String Deck4= "[7s, 10s, 9h, 13d, 12h, 3s, 2h, 8h, 5h, 11h, 9s, 1h, 12s, 4h, 2s, 8s, 5s, 11s, 13h, 1s, 3h, 6h, 12d, 4s, 10h, 13s, 6s, 7h, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=32
		//String Deck5= "[7h, 8h, 13s, 2h, 10h, 8s, 4s, 3s, 2s, 6s, 4h, 11d, 5h, 10d, 12s, 9d, 11h, 8d, 1h, 7d, 11s, 12d, 5s, 9s, 12h, 6h, 6d, 13d, 5d, 3h, 4d, 9h, 3d, 1s, 2d, 10s, 1d, 13h, 13c, 7s, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

		
		//String technique = "INVERSION-CUTTING";
		//n=2
		//String Deck1= "[13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 13s, 12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h]";
		//n=4
		//String Deck2= "[12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13c, 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d, 12d, 13d, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c, 13s]";
		//n=8
		//String Deck3= "[2c, 1c, 13s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 10d, 11d, 12d, 13d, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c]";
		//n=16
		//String Deck4= "[3d, 2d, 1d, 13c, 10d, 11d, 12d, 13d, 12c, 11c, 10c, 3c, 4c, 5c, 6c, 7c, 8c, 9c, 7s, 6s, 5s, 4s, 3s, 2s, 13s, 1c, 2c, 8s, 9s, 10s, 11s, 12s, 5h, 4h, 3h, 2h, 1h, 1s, 6h, 7h, 8h, 7d, 8d, 9d, 13h, 12h, 11h, 10h, 9h, 6d, 5d, 4d]";
		//n=32
		//String Deck5= "[2d, 3d, 1d, 13c, 3c, 10c, 11c, 8s, 1c, 6c, 7c, 8c, 9c, 7s, 6s, 5s, 4s, 3s, 2s, 13s, 5c, 4c, 10d, 11d, 12d, 13d, 12c, 2c, 5h, 12s, 11s, 10s, 9s, 4h, 8h, 7h, 6h, 1s, 1h, 2h, 3h, 7d, 5d, 6d, 9h, 10h, 11h, 12h, 13h, 9d, 8d, 4d]";

		
		//String technique = "INVERSION-INSERTION";
		//n=2
		//String Deck1= "[12s, 11s, 10s, 9s, 8s, 7s, 6s, 5s, 4s, 3s, 2s, 1s, 1h, 2h, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13s, 13d, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=4
		//String Deck2= "[1h, 1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 10s, 11s, 12s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13s, 13d, 2h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=8
		//String Deck3= "[1s, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s, 11s, 12s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13s, 1h, 10s, 13d, 2h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=16
		//String Deck4= "[3s, 4s, 5s, 6s, 7s, 11s, 12s, 3h, 4h, 5h, 6h, 7h, 8h, 9h, 10h, 11h, 12h, 13h, 13s, 2s, 8s, 1s, 9s, 1h, 10s, 13d, 2h, 12d, 11d, 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";
		//n=32
		//String Deck5= "[9h, 8h, 12h, 13h, 13s, 2s, 10h, 11h, 7h, 6h, 12s, 11s, 7s, 4s, 6s, 5s, 3s, 4h, 3h, 1s, 8s, 9s, 1h, 10s, 13d, 2h, 12d, 11d, 10d, 9d, 8d, 7d, 5h, 6d, 5d, 4d, 3d, 2d, 1d, 13c, 12c, 11c, 10c, 9c, 8c, 7c, 6c, 5c, 4c, 3c, 2c, 1c]";

		
		//String technique = "INVERSION-INTERCALATION";
		//n=2
		//String Deck1= "[13d, 13s, 12d, 12s, 11d, 11s, 10d, 10s, 9d, 9s, 8d, 8s, 7d, 7s, 6d, 6s, 5d, 5s, 4d, 4s, 3d, 3s, 2d, 2s, 1d, 1s, 13c, 1h, 12c, 2h, 11c, 3h, 10c, 4h, 9c, 5h, 8c, 6h, 7c, 7h, 6c, 8h, 5c, 9h, 4c, 10h, 3c, 11h, 2c, 12h, 1c, 13h]";
		//n=4
		//String Deck2= "[1h, 7s, 12c, 7d, 2h, 8s, 11c, 8d, 3h, 9s, 10c, 9d, 4h, 10s, 9c, 10d, 5h, 11s, 8c, 11d, 6h, 12s, 7c, 12d, 7h, 13s, 6c, 13d, 8h, 6d, 5c, 6s, 9h, 5d, 4c, 5s, 10h, 4d, 3c, 4s, 11h, 3d, 2c, 3s, 12h, 2d, 1c, 2s, 13h, 1d, 1s, 13c]";
		//n=8
		//String Deck3= "[4d, 11c, 4h, 6d, 3c, 8d, 10s, 8h, 4s, 3h, 9c, 13d, 11h, 9s, 10d, 6c, 3d, 10c, 5h, 13s, 2c, 5c, 11s, 8s, 3s, 6s, 8c, 2h, 12h, 9h, 11d, 7d, 2d, 5d, 6h, 12c, 1c, 4c, 12s, 7s, 2s, 5s, 7c, 1h, 13h, 10h, 12d, 9d, 1d, 7h, 1s, 13c]";
		//n=16
		//String Deck4= "[11c, 12c, 9s, 6d, 7c, 10s, 3s, 3d, 7d, 11h, 4c, 12h, 13s, 2s, 4s, 8c, 4d, 9h, 10d, 13d, 1h, 10c, 8s, 8d, 2d, 4h, 12s, 6h, 2c, 5s, 2h, 6s, 3h, 11d, 6c, 1c, 13h, 5h, 3c, 8h, 5d, 7s, 5c, 9c, 10h, 11s, 12d, 9d, 1d, 7h, 1s, 13c]";
		//n=32
		//String Deck5= "[12d, 13d, 5d, 7s, 13s, 10s, 12c, 5c, 9d, 10h, 8h, 2s, 11h, 7d, 4s, 10d, 1d, 11s, 3c, 7c, 3s, 9h, 7h, 5h, 1h, 6d, 1s, 13h, 12h, 11c, 13c, 1c, 10c, 4d, 3d, 6c, 8s, 8c, 4c, 11d, 8d, 9c, 9s, 2d, 4h, 12s, 6h, 2c, 5s, 2h, 6s, 3h]";

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
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points1n2.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points1n2.add(510);
							j++;
						}
					}
					break;
				case 1:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points2n2.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points2n2.add(510);
							j++;
						}
					}
					break;
				case 2:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points3n2.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points3n2.add(510);
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
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points1n4.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points1n4.add(510);
							j++;
						}
					}
					break;
				case 4:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points2n4.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points2n4.add(510);
							j++;
						}
					}
					break;
				case 5:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points3n4.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points3n4.add(510);
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
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points1n8.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points1n8.add(510);
							j++;
						}
					}
					break;
				case 7:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points2n8.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points2n8.add(510);
							j++;
						}
					}
					break;
				case 8:
					j = 0;
					iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points3n8.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points3n8.add(510);
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
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points1n16.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points1n16.add(510);
							j++;
						}
					}
					break;
				case 10:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points2n16.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points2n16.add(510);
							j++;
						}
					}
					break;
				case 11:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points3n16.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points3n16.add(510);
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
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points1n32.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points1n32.add(510);
							j++;
						}
					}
					break;
				case 13:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points2n32.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points2n32.add(510);
							j++;
						}
					}
					break;
				case 14:
					 j = 0;
					 iterations=150;
						
					//while(gp.population.get(0).getSecondElement()<510&&i<iterations){
					while(gp.population.get(0).getSecondElement()<510&&j<iterations){
						System.out.println("----------"+j+". GENERATION...-----------");
						System.out.println(gp.population.get(0).getFirstElement());
						System.out.println("Points:"+gp.population.get(0).getSecondElement());
						gp.evolvePopulation();
						Points3n32.add(gp.population.get(0).getSecondElement());
						j++;
					}
					if (j!=iterations){
						while (j<iterations){
							Points3n32.add(510);
							j++;
						}
					}
					break;
				}
			}
			
			
		}
		
		
		
		
		javiGP gp = new javiGP();
		gp.pointsToCSV(technique,Points1n2, Points2n2, Points3n2, Points1n4, Points2n4, Points3n4, Points1n8, Points2n8, Points3n8, Points1n16, Points2n16, Points3n16, Points1n32, Points2n32, Points3n32);
		
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
