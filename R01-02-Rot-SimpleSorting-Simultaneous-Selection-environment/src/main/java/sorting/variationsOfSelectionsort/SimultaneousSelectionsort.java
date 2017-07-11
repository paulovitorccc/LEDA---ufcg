package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	Util util = new Util();
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(array.length == 0) || !(leftIndex > rightIndex)) {
			int indiceInicio = leftIndex;
			int indiceFinal = rightIndex;
			

			while (indiceFinal != indiceInicio || indiceFinal > indiceInicio) {

				int indiceMenorElemento = indiceInicio; // considerando o menor elemento o primeiro para iniciar as comparações
				int indiceMaiorElemento = indiceFinal; // considerando o maior elemento o o ultimo para iniciar as comparações
				int k = indiceInicio; // indice que marca inicio de iteração (limitando a direita os elementos menores ja ordenados)
				int h = indiceFinal; // indice que marca inicio de iteração (limitando a esquerda os elementos maiores ja ordenados)
				while(h >= indiceInicio || k <= indiceFinal) {
					if (k <= indiceFinal && array[indiceMenorElemento].compareTo(array[k]) > 0) { // verificando se o elemento considerado menor é menor que o comparado atualmente
						indiceMenorElemento = k;
					}
					if (h >= indiceInicio && array[indiceMaiorElemento].compareTo(array[h]) < 0) { //verificando se o elemento considerado maior é maior que o elemento atualmente comparado
						indiceMaiorElemento = h;
					}
					k++;
					h--;
				}
				
				//swap de maneira diferente para não dar conflito na atribuição equivocada de valores ja substituidos a pouco tempo
				T tempInicio = array[indiceInicio];
				T tempMenorElemento = array[indiceMenorElemento];
				T tempFinal = array[indiceFinal];
				T tempMaiorElemento = array[indiceMaiorElemento];
				
				array[indiceInicio] = tempMenorElemento;
				array[indiceMenorElemento] = tempInicio;
				
				//verificando se o indice inicio e o indice do maior elemento são iguais(evita equivocos de substituição)
				if(indiceMaiorElemento == indiceInicio){
					array[indiceFinal] = tempInicio;
					array[indiceMenorElemento] = tempFinal;
				}else{
					array[indiceFinal] = tempMaiorElemento;
					array[indiceMaiorElemento] = tempFinal;
				}
				indiceInicio = indiceInicio + 1;
				indiceFinal = indiceFinal - 1;
			}
		}
	}
}
