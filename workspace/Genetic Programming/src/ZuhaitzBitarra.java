import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class ZuhaitzBitarra<E> {

	private Adabegi<E> root;
	private Adabegi<E> currentPos;
	
	static class Adabegi<E> {
		private E info;
		private Adabegi<E> left;
		private Adabegi<E> right;

		public Adabegi(E elem){
			info = elem;
		}

		public Adabegi(E elem, Adabegi<E> ezkerUmea, Adabegi<E> eskuinUmea) {
			this.info = elem;
			this.left = ezkerUmea;
			this.right = eskuinUmea;
		}
		
		protected E getInfo() {
			return info;
		}

		protected void setInfo(E info) {
			this.info = info;
		}

		protected Adabegi<E> getLeft() {
			return left;
		}

		protected void setLeft(Adabegi<E> left) {
			this.left = left;
		}

		protected Adabegi<E> getRight() {
			return right;
		}

		protected void setRight(Adabegi<E> right) {
			this.right = right;
		}

		protected boolean hasLeft() {
			return this.getLeft() != null;
		}

		protected boolean hasRight() {
			return this.getRight() != null;
		}

		protected boolean isLeaf() {
			return !this.hasLeft() && !this.hasRight();
		}

		protected boolean isDescendantOf(Adabegi<E> ustezkoa) {
			if (ustezkoa == this)
				return true;
			if (ustezkoa.isLeaf())
				return false;
			if (!ustezkoa.hasLeft())
				return this.isDescendantOf(ustezkoa.getRight());
			if (!ustezkoa.hasRight())
				return this.isDescendantOf(ustezkoa.getLeft());
			return this.isDescendantOf(ustezkoa.getLeft()) || 
					this.isDescendantOf(ustezkoa.getRight());
		}

		@Override
		public String toString() {
			StringBuilder emaitza = new StringBuilder("[ " + this.getInfo() + " ");
			if (!this.isLeaf()) {
				if (this.hasLeft())
					emaitza.append(this.getLeft().toString());
				else
					emaitza.append("");
				if (this.hasRight())
					emaitza.append(this.getRight().toString());
				else
					emaitza.append("");
			}
			emaitza.append("] ");
			return emaitza.toString();
		}
		
		private void aurreordenInprimatu() {
			System.out.print(getInfo() + " ");
			if (this.hasLeft())
				this.left.aurreordenInprimatu();
			if (this.hasRight())
				this.right.aurreordenInprimatu();
		}

		private void inordenInprimatu() {
			if (this.hasLeft())
				this.left.inordenInprimatu();
			System.out.print(getInfo() + " ");
			if (this.hasRight())
				this.right.inordenInprimatu();
		}

		private void posordenInprimatu() {
			if (this.hasLeft())
				this.left.posordenInprimatu();
			if (this.hasRight())
				this.right.posordenInprimatu();
			System.out.print(getInfo() + " ");
		}

		private int size() {
			int emaitza = 1;
			if (this.hasLeft())
				emaitza += this.getLeft().size();
			if (this.hasRight())
				emaitza += this.getRight().size();
			return emaitza;
		}

		private int height() {
			if (this.isLeaf())
				return 0;
			int emaitza = 0;
			if (this.hasLeft())
				emaitza = this.getLeft().height();
			if (this.hasRight())
				emaitza = Math.max(emaitza, this.getRight().height());
			return emaitza+1;
		}


		private boolean contains(E elem) {
			if (this.info.equals(elem))
				return true;
			if (this.hasLeft() && this.getLeft().contains(elem))
				return true;
			if (this.hasRight() && this.getRight().contains(elem))
				return true;
			return false;
		}

		private void mailaInprimatu(int maila) {
			if (maila == 0)
				System.out.print(this.getInfo() + " ");
			else {
				if (this.hasLeft())
					this.getLeft().mailaInprimatu(maila-1);
				if (this.hasRight())
					this.getRight().mailaInprimatu(maila-1);
			}
		}

		private boolean sameStructure(Adabegi<E> bestea) {
			if (this.hasLeft() != bestea.hasLeft() || this.hasRight() != bestea.hasRight())
				return false;
			if (this.hasLeft() && !this.getLeft().sameStructure(bestea.getLeft()))
				return false;
			if (this.hasRight() && !this.getRight().sameStructure(bestea.getRight()))
				return false;
			return true;
		}
		
		private ArrayList<E> inOrdenList(ArrayList<E> lista) {
			if (this.hasLeft()){
				lista.add(this.left.info);
				this.left.inOrdenList(lista);
			}
			
			if (this.hasRight()){
				lista.add(this.right.info);
				this.right.inOrdenList(lista);
			}
			return lista;
		}
	}

	public ZuhaitzBitarra() {
		this.root = null;
		this.currentPos = this.root;
	}

	public ZuhaitzBitarra(E elem) {
		this.root = new Adabegi<E>(elem);
		this.currentPos=this.root;
	}

	public ZuhaitzBitarra(E elem, ZuhaitzBitarra<E> lSubTree, ZuhaitzBitarra<E> rSubTree) {
		this.root = new Adabegi<E>(elem, lSubTree.root, rSubTree.root);
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public Adabegi<E> getLeft() {
		return this.root.getLeft();
	}

	public void setRightandMove(E param) {
		Adabegi<E> ada = new Adabegi<E>(param);
		this.currentPos.setRight(ada);
		this.currentPos=this.currentPos.getRight();
	}

	public void setLeftandMove(E param) {
		Adabegi<E> ada = new Adabegi<E>(param);
		this.currentPos.setLeft(ada);
		this.currentPos=this.currentPos.getLeft();
	}
	
	public Adabegi<E> getRight() {
		return this.root.getRight();
	}
	
	public Adabegi<E> getRoot() {
		return this.root;
	}
	
	public void setRoot(Adabegi<E> node) {
		this.root = node;
	}

	public E getRootValue() {
		return this.root.getInfo();
	}

	public boolean hasLeft() {
		return this.root.hasLeft();
	}

	public boolean hasRight() {
		return this.root.hasRight();
	}

	public void insertLeftSubtree(ZuhaitzBitarra<E> leftSubtree) {
		this.root.setRight(leftSubtree.root);
	}

	public void insertRightSubtree(ZuhaitzBitarra<E> rightSubtree) {
		this.root.setRight(rightSubtree.root);
	}

	public int size() {
		if (this.isEmpty())
			return 0;
		return this.root.size();
	}	

	public int height() {
		if (this.isEmpty())
			return -1;
		return this.root.height();
	}

	public boolean contains(E elem) {
		if (this.isEmpty())
			return false;
		return this.root.contains(elem);

	}

	public boolean sameStructure(ZuhaitzBitarra<E> bestea) {
		if (this.isEmpty() && bestea.isEmpty())
			return true;
		if (!this.isEmpty() && !bestea.isEmpty())
			return this.root.sameStructure(bestea.root);
		return false;
	}

	public void mailaInprimatu(int maila) {
		if (!this.isEmpty())
			this.root.mailaInprimatu(maila);
	}

	public void aurreordenInprimatu() {
		if (!this.isEmpty())
			this.root.aurreordenInprimatu();
		System.out.println("");
	}

	public void inordenInprimatu() {
		if (!this.isEmpty())
			this.root.inordenInprimatu();
	}

	public void posordenInprimatu() {
		if (!this.isEmpty())
			this.root.posordenInprimatu();
	}
	
	private Adabegi<E> getNodePreOrder(int number) { //Hacer contador te paras en el nodo y lo devuelves :)
		int counter=0;
	    Stack<Adabegi> stack = new Stack<Adabegi>();
	    Adabegi current = root;
	    stack.push(root);
	    while(!stack.isEmpty()) {
	    	counter++;
	        current = stack.pop();
	        if(counter==number){
	    		return current;
	    	}
	        //visit(current.info);
	        if(current.right != null) {
	            stack.push(current.right);
	        }    
	        if(current.left != null) {
	            stack.push(current.left);
	        }
	    }  
	    return current;
	}
	
	private void replaceNodePreOrder(int number, Adabegi<String> nodo1) { //Hacer contador te paras en el nodo y lo devuelves :)
		int counter=0;
	    Stack<Adabegi> stack = new Stack<Adabegi>();
	    Adabegi current = root;
	    stack.push(root);
	    if (number==1){
	    	this.root=null;
	    	this.root=(Adabegi<E>) nodo1;
	    }
	    //else????
	    while(!stack.isEmpty()) {
	    	counter++;
	        current = stack.pop();
	        if(counter==number-1){
	        	if(current.right != null) {
		            current.right = null;
		            current.right = nodo1;
		        }    
	        	else if(current.left != null) {
	        		current.left = null;
		            current.left = nodo1;
		        }
	        	break;
	    	}
	        //visit(current.info);
	        if(current.right != null) {
	            stack.push(current.right);
	        }    
	        if(current.left != null) {
	            stack.push(current.left);
	        }
	    }  
	}
	
	public int randOddInt(int max) {
		int odd = max+1;
		Random random = new Random();
		
		while (odd==(max+1)){		//to avoid getting and odd number above range :)
		odd = random.nextInt(max / 2) * 2 + 1;
		}
		return odd;
	}
	
	public void crossOverTrees(ZuhaitzBitarra<String> zuhaitz2){//ZuhaitzBitarra<String>
		/*System.out.println("FIRST TREES");
		System.out.println("TREE 1");
		this.aurreordenInprimatu();
		System.out.println("");
		System.out.println("TREE 2");
		zuhaitz2.aurreordenInprimatu();
		System.out.println("");*/
		
		//tree 1
		int zuhaitz1Size = this.size();	//size of this tree
		int crossoverPoint1 = randOddInt(zuhaitz1Size);
		Adabegi<String> nodo1 = new Adabegi<String>(null);
		nodo1 = (Adabegi<String>) this.getNodePreOrder(crossoverPoint1);
		
		//tree 2
		int zuhaitz2Size = zuhaitz2.size();	//size of other tree
		int crossoverPoint2 = randOddInt(zuhaitz2Size);
		Adabegi<String> nodo2 = new Adabegi<String>(null);
		nodo2 = zuhaitz2.getNodePreOrder(crossoverPoint2);
		
		this.replaceNodePreOrder(crossoverPoint1, nodo2);
		zuhaitz2.replaceNodePreOrder(crossoverPoint2, nodo1);
		
		/*System.out.println("SECOND TREES");
		System.out.println("TREE 1");
		this.aurreordenInprimatu();
		System.out.println("");
		System.out.println("TREE 2");
		zuhaitz2.aurreordenInprimatu();
		System.out.println("");
		
		System.out.println(crossoverPoint1);
		System.out.println(crossoverPoint2);*/
	}
	
	public void mutation(String operationName,String number){ //it changes the node at position n with this one
		int mutationPoint1 = randOddInt(this.size());
		
		int counter=0;
	    Stack<Adabegi> stack = new Stack<Adabegi>();
	    Adabegi current = root;
	    stack.push(root);
	    if (mutationPoint1==1){
	    	current.info=operationName;
	    	if(current.left != null) {
	    		current.left.info=number;
	    	}
	    	else if(current.right != null) {
	    		current.right.info=number;
	    	}
	    }
	    else{
		    while(!stack.isEmpty()) {
		    	counter++;
		        current = stack.pop();
		        if(counter==mutationPoint1-1){
		        	//System.out.println("imprimi current: "+current.info);
		        	if(current.right != null) {
			            current.right.info = operationName;
			            current.right.right.info=number;
			        }    
		        	else if(current.left != null) {
		        		current.left.info = operationName;
			            current.left.left.info=number;
			        }
		        	break;
		    	}
		        //visit(current.info);
		        if(current.right != null) {
		            stack.push(current.right);
		        }    
		        if(current.left != null) {
		            stack.push(current.left);
		        }
		    }  
	    }
		
	}
	
	public String toString() {
		if (this.isEmpty())
			return "";
		return this.root.toString();
	}
	
	public ArrayList<E> inOrdenList(){
		ArrayList<E> List = new ArrayList<>(); 
		List.add(this.root.info);
		return this.root.inOrdenList(List);
	}
}