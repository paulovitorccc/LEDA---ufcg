package orderStatistic;

import util.Util;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a
	 * estrategia de usar o selection sem modificar o array original. Note que
	 * seu algoritmo vai apenas selectionar a estatistica deordem e nao ordenar
	 * o array original.
	 * 
	 * Restricoes: - Preservar o array original, ou seja, nenhuma modificacao
	 * pode ser feita no array original - Nenhum array auxiliar deve ser criado
	 * e utilizado. - Caso a estatistica de ordem nao exista no array, o
	 * algoritmo deve retornar null. - Considerar que k varia de 1 a N -
	 * Sugestao: o uso de recursao ajudara sua codificacao.
	 */

	Util util = new Util();

	@Override
	public T getOrderStatistics(T[] array, int k) {
		T menorElemento = null;
		
		if (k > array.length) {
			return menorElemento;
		
		} else {
			
			int indexMenorElemento = selectionSort(array, 0, array.length - 1);
			menorElemento = array[indexMenorElemento];
			
			for (int h = 1; h <= k - 1; h++) {
				menorElemento = menorElementoMaiorQue(array, menorElemento);
			}

		}
		return menorElemento;
	}

	private int selectionSort(T[] array, int leftIndex, int rightIndex) { 

		int indexMenorElemento = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(array[indexMenorElemento]) < 0) {
				indexMenorElemento = j;
			}
		}

		return indexMenorElemento;

	}

	private T menorElementoMaiorQue(T[] array, T elemento) {
		
		int diferencaAnterior = 99999999;
		
		T elementoFinal = array[0];
		
		for (T el : array) {
			if (el.compareTo(elemento) > 0) {
				int diferencaAtual = el.hashCode() - elemento.hashCode();
				if (diferencaAtual < diferencaAnterior) {
					diferencaAnterior = diferencaAtual;
					elementoFinal = el;
				}
			}
		}
		
		return elementoFinal;

	}

}
