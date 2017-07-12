package BST;

public class Main {
	
	
	public static void main(String[] args) {
		
		BSTTests<Integer> ie = new BSTTests<Integer>();
		
		BSTImpl<Integer> bst1 = new BSTImpl<Integer>();
		BSTImpl<Integer> bst2 = new BSTImpl<Integer>();
		
		bst1.insert(4);
		bst1.insert(3);
		bst1.insert(2);
		bst1.insert(1);

		bst2.insert(4);
		bst2.insert(3);
		bst2.insert(2);
		bst2.insert(1);
		bst2.insert(20);
		bst2.insert(21);
		
		System.out.println(ie.isEquals(bst1, bst2));
		System.out.println(ie.qtdFolhas(bst2));
		System.out.println(ie.isDecendent(bst2, 21, 3));
		
		
	}

}
