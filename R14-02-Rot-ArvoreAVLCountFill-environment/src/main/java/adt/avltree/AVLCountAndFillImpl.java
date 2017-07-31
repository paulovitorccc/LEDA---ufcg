package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balaceFactor;
		balaceFactor = calculateBalance(node);
		if (balaceFactor > 1) { // raiz pesa para a esquerda
			BSTNode<T> nodeSon = (BSTNode<T>) node.getLeft();
			boolean LR = false;
			if (calculateBalance(nodeSon) < 0) { // filho pesa para a direita
				LR = true;
				leftRotation(nodeSon);
			}

			if (LR) {
				this.LRcounter++;
			} else {
				this.LLcounter++;
			}

			rightRotation(node);
		} else if (balaceFactor < -1) { // raiz pesa para a direita
			BSTNode<T> nodeSon = (BSTNode<T>) node.getRight();
			boolean RL = false;
			if (calculateBalance(nodeSon) > 0) { // filho pesa para a esquerda
				RL = true;
				rightRotation(nodeSon);
			}

			if (RL) {
				this.RLcounter++;
			} else {
				this.RRcounter++;
			}

			leftRotation(node);
		}
	}
	
	@Override
	public void fillWithoutRebalance(T[] array) {
		Arrays.sort(array);
		
		int indexElement = (array.length / 2);
		T element = array[indexElement];
		insert(element);
		array[indexElement] = null;
		
		T[] arrayMenores = (T[]) new Comparable[array.length];
		T[] arrayMaiores = (T[]) new Comparable[array.length];
		
		for(int h = 0; h < indexElement; h++){
			arrayMenores[h] = array[h];
		}
		for(int i = indexElement + 1; i < array.length; i++){
			arrayMaiores[i] = array[i];
		}
		arrayMaiores = shiftArray(arrayMaiores);
		arrayMenores = shiftArray(arrayMenores);
		
		
	}
	
	public T[] shiftArray(T[] array) {
		T[] result = (T[]) new Comparable[array.length];
		int cont = 0;
		for (T t : array) {
			if (t != null) {
				result[cont] = t;
				cont++;
			}
		}
		T[] arrayFinal = (T[]) new Comparable[cont];
		for(int j = 0; j < arrayFinal.length; j++){
			arrayFinal[j] = result[j];
		}
		//array = (T[]) new Comparable[arrayFinal.length];
		//array = arrayFinal;
		return arrayFinal;
	}
	
}
