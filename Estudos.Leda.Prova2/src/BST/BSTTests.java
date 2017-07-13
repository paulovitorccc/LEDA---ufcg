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
	//saber se um elemento e filho de outro
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
	// tentando fazer a prova de melina
	//metodo para saber o grau entre dois primos
	//falta tratar no metodo se for o caso de os elementos na forem primos
	public int isCousinFor(BSTImpl<T> bst, T primo1, T primo2){
		BTNode<T> nodePrimo1 = bst.search(primo1);
		int distanciaPrimo1;
		int distanciaPrimo2;
		if(nodePrimo1.equals(new BTNode<T>())){
			return -1;
		} else {
			distanciaPrimo1 = distanciaDaRaiz(bst,bst.getRoot() ,nodePrimo1, 0);	
		}
		BTNode<T> nodePrimo2 = bst.search(primo2);
		if(nodePrimo2.equals(new BTNode<T>())){
			return -1;
		} else {
			distanciaPrimo2 = distanciaDaRaiz(bst,bst.getRoot() ,nodePrimo2, 0);
		}
		int diferencaEntreDistancias = Math.abs(distanciaPrimo1 - distanciaPrimo2);
		return diferencaEntreDistancias + 1;
	}
	
	private int distanciaDaRaiz(BSTImpl<T> bst, BTNode<T> node ,BTNode<T> primo, int count){
		if(node.isEmpty()) {
			return 0;
		} else if(node.getData().compareTo(primo.getData()) > 0){
			return distanciaDaRaiz(bst, node.getLeft(), primo, count + 1);
		} else if(node.getData().compareTo(primo.getData()) < 0){
			return distanciaDaRaiz(bst, node.getRight(), primo, count + 1);
		} else {
			return count;
		}
	}
	
	//metodo para saber qual a folha mais proxima da raiz
	//FALTA IMPLEMENTAR
	private void menorAltura(BSTImpl<T> bst){
		
	}
	
	private int menorAltura(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			int heightLeft = menorAltura((BSTNode<T>) node.getLeft());
			int heightRight = menorAltura((BSTNode<T>) node.getRight());
			int menor;
			if (heightLeft < heightRight) {
				menor = heightLeft;
			} else {
				menor = heightRight;
			}
			return 1 + menor;
		}
	}
	
	
	
	
	
	
	
	
}
