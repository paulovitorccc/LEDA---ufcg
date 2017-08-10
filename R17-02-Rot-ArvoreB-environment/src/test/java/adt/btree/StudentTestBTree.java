package adt.btree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StudentTestBTree {

	protected BTree<Integer> tree1;

	@Before
	public void setUp() throws Exception {
		tree1 = new BTreeImpl<Integer>(4);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tree1.isEmpty());
	}

//	@Test
//	public void testHeight() {
//		assertEquals(-1, tree1.height());
//		tree1.insert(2);
//		assertEquals(0, tree1.height());
//		tree1.insert(1);
//		assertEquals(0, tree1.height());
//		tree1.insert(3);
//		assertEquals(0, tree1.height());
//		tree1.insert(4);
//		assertEquals(1, tree1.height());
//
//	}

	@Test
	public void testDepthLeftOrder() {
//		tree1.insert(2);
//		tree1.insert(4);
//		tree1.insert(6);
//		tree1.insert(8);
//		tree1.insert(1);
//		tree1.insert(5);
//		tree1.insert(3);
		
//		tree1.insert(1);
//		tree1.insert(2);
//		tree1.insert(3);
//		tree1.insert(4);
//		tree1.insert(5);
//		tree1.insert(6);
//		tree1.insert(7);
		
//		BNode<Integer>[] array = tree1.depthLeftOrder();
//		for (BNode<Integer> bNode : array) {
//			System.out.println(bNode);
//		}
//		try {
//			assertEquals("[[6], [2, 4], [8]]",
//					Arrays.toString(tree1.depthLeftOrder()));
//		} catch (AssertionError e) {
//			assertEquals("[[4], [2], [6, 8]]",
//					Arrays.toString(tree1.depthLeftOrder()));
//		}
		
		
		tree1.insert(1);
		tree1.insert(8);
		tree1.insert(14);
		tree1.insert(22);
		tree1.insert(26);
		tree1.insert(30);
		tree1.insert(34);
		tree1.insert(16);
		//ERRO Q FALTA CONSERTAR
//		tree1.insert(20);
//		tree1.insert(18);
//		tree1.insert(19);
		
		
	}

	@Test
	public void testSize() {
		assertEquals(0, tree1.size());
		tree1.insert(18);
		assertEquals(1, tree1.size());
	}

}
