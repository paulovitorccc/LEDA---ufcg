package questoes.arvorepv;

public class Main {

	public static void main(String[] args) {
		
		RBTreeImpl<Integer> tree = new RBTreeImpl<Integer>();
		
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
		tree.insert(0);
		System.out.println(tree.qtdNodeRed());
		
	}
}
