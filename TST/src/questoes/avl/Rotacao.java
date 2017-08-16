package questoes.avl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import avl.AVLTreeImpl;

public class Rotacao {
	
	public static void main(String[] args) {
		
		while(true){
			AVLTreeImpl<Integer> avl = new AVLTreeImpl<Integer>();
			Scanner sn = new Scanner(System.in);
			String linha = sn.nextLine();
			String[] arrayLinha = linha.split(" ");
			
			if(arrayLinha.length <= 2){
				System.out.println("balanceada");
				System.out.println("");
			}
			else if(arrayLinha.length == 3){
				Integer[] arrayInteger = new Integer[arrayLinha.length];
				for (int j = 0; j < arrayLinha.length; j++) {
					arrayInteger[j] = Integer.parseInt(arrayLinha[j]);
				}
				avl.insert(arrayInteger[0]);
				avl.insert(arrayInteger[1]);
				avl.insert(arrayInteger[2]);
				if(avl.preOrder() == arrayInteger){
					System.out.println("balanceada");
					System.out.println("");
				} else {
					
					if(avl.preOrder()[2] == arrayInteger[0]){ //Left-Left
						System.out.println("rot_dir(" + arrayInteger[0] +  ")");
						System.out.println(Arrays.toString(avl.preOrder()));
					} else if(arrayInteger[1] == avl.preOrder()[1]){ //Left-Right
						System.out.println("rot_esq(" + arrayInteger[1] +  ")");
						Comparable[] newArray = new Integer[3];
						newArray[0] = arrayInteger[0];
						newArray[1] = arrayInteger[2];
						newArray[2] = arrayInteger[1];
						System.out.println(Arrays.toString(newArray));
						System.out.println("rot_dir(" + arrayInteger[0] +  ")");
						System.out.println(Arrays.toString(avl.preOrder()));
					} else if(arrayInteger[2] == avl.preOrder()[2]){ //Rigth-Right
						System.out.println("rot_esq(" + arrayInteger[0] +  ")");
						System.out.println(Arrays.toString(avl.preOrder()));
					} else if(arrayInteger[0] == avl.preOrder()[1]){ //Right-Left
						System.out.println("rot_dir(" + arrayInteger[1] +  ")");
						Comparable[] newArray = new Integer[3];
						newArray[0] = arrayInteger[0];
						newArray[1] = arrayInteger[2];
						newArray[2] = arrayInteger[1];
						System.out.println(Arrays.toString(newArray));
						System.out.println("rot_esq(" + arrayInteger[0] +  ")");
						System.out.println(Arrays.toString(avl.preOrder()));
					}
					System.out.println();
				}
			}
		}
	}

}
