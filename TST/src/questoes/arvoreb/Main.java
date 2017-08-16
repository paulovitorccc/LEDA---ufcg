package questoes.arvoreb;

public class Main {

	
	public static void main(String[] args) {
		
		BTreeImpl<Integer> tree = new BTreeImpl<>(20);
		
		tree.insert(2);
		tree.insert(4);
		tree.insert(7654);
		tree.insert(3);
		tree.insert(36);
		tree.insert(56);
		tree.insert(234);
		tree.insert(45);
		tree.insert(23);
		tree.insert(2234);
		tree.insert(456);
		tree.insert(56);
		tree.insert(577);
		tree.insert(6);
		tree.insert(567);
		tree.insert(23);
		tree.insert(28);
		tree.insert(356);
		System.out.println(tree.somaDeChaves());
		
		
		
		
	}
}
