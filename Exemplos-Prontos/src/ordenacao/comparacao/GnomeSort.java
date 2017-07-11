package ordenacao.comparacao;

/**
 * Gnome Sort
 * Inventor: Hamid Sarbazi-Azad (2000) Idéia: 
 * Adota um pivot (que tenha anterior). 
 * Olha para o proximo 
 * Se o pivot e o proximo estão na ordem correta entao incrementa o pivot.
 * Se eles nao estao na ordem correta entao troca eles e decrementa o pivot. 
 * Condições: se nao existe anterior ao pivot entao anda para frente (ao invés de decrementar). Se nao tem proximo entao termina.
 */
public class GnomeSort<T extends Comparable<T>> {
	
	Util util = new Util();
	
	public T[] sort(T[] array, int leftIndex, int rightIndex){
		int pivot = leftIndex + 1;
		while(pivot < rightIndex){
			if(array[pivot].compareTo(array[pivot+1]) > 0){
				util.swap(array, pivot, pivot + 1);
				if(pivot != leftIndex){
					pivot--;
				}
			}else{
				pivot++;
			}
		}
		return array;
	}
}
