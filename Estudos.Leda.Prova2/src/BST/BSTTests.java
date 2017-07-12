package BST;

public class BSTTests<T extends Comparable<T>> {

	public BSTTests() {

	}
	//IMPLEMENTACAO ERRADA
	//saber se duas bsts sao iguais
	public boolean isEquals(BSTImpl<T> bt1, BSTImpl<T> bt2) {
		return isEquals(bt1.getRoot(), bt2.getRoot());
	}

	private boolean isEquals(BTNode<T> node1, BTNode<T> node2) {
		if (node1.isEmpty() || node2.isEmpty()) {
			if (node1.isEmpty() && !node2.isEmpty()) {
				return false;
			} else if (!node1.isEmpty() && node2.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} else {
			boolean result = false;
			if (isEquals(node1.getLeft(), node2.getLeft())) {
				if (node1.getData().equals(node2.getData())) {
					result = true;
				} else {
					result = false;
				}
				if (isEquals(node1.getRight(), node2.getRight())) {
					if (result) {
						if (node1.getData().equals(node2.getData())) {
							result = true;
						} else {
							result = false;
						}
					}
				}
			}
			return result;
		}
	}
	//IMPLEMENTACAO CORRETA
	//saber qtd de folhas de uma bst
	public int qtdFolhas(BSTImpl<T> bst) {
		return qtdFolhas(bst.getRoot(), 0);
	}
	
	private int qtdFolhas(BTNode<T> node, int cont) {
		if(node.isEmpty()) {
			return cont;
		} else {
			cont = qtdFolhas(node.getLeft(), cont);
			if(node.getLeft().equals(new BTNode<T>()) && node.getRight().equals(new BTNode<T>())){
				cont = cont + 1;
			}
			cont = qtdFolhas(node.getRight(), cont);
			return cont;
		}
	}
	//IMPLEMENTACAO CORRETA
	//saber se um elemento é filho de outro
	public boolean isDecendent (BSTImpl<T> bt, T d, T p) {
		boolean result = false;
		BTNode<T> node = bt.search(p);
		if(!node.isEmpty()) {
			result = isDecendent(node, d);
		}
		return result;
	}
	
	private boolean isDecendent(BTNode<T> node, T element) {
		boolean result = false;
		if(node.isEmpty()|| node.getData().equals(element)) {
			if(node.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} else {
			if(!result) {
				result = isDecendent(node.getLeft(), element);
				if(!result) {
					result = isDecendent(node.getRight(), element);
				}
			}
		}
		return result;
	}
	
	
	
	
	
	
}
