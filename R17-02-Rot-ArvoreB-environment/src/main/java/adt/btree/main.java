package adt.btree;

import java.util.LinkedList;

public class main {

	
	
	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(1, 0);
		
		
		
		for (Integer integer : list) {
			System.out.println(integer);
		}
		
		
		
		
		
	}
}
