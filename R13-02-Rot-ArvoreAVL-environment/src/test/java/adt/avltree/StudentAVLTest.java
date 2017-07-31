package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();

	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
	}

	@Test
	public void testInsert() {
		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());

		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());

		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());

		avl.insert(30);
		assertEquals(4, avl.size());
		assertEquals(2, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20, 30 }, avl.preOrder());

		avl.insert(1);
		assertEquals(5, avl.size());
		assertEquals(2, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20, 1, 30 }, avl.preOrder());

		avl.insert(-30);
		assertEquals(6, avl.size());
		assertEquals(2, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, -30, 20, 1, 30 }, avl.preOrder());

		avl.insert(33);
		avl.insert(35);
		assertEquals(8, avl.size());
		assertEquals(3, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, -30, 20, 1, 33, 30, 35 }, avl.preOrder());

		avl.insert(38);
		assertEquals(9, avl.size());
		assertEquals(3, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, -30, 33, 20, 1, 30, 35, 38 }, avl.preOrder());

		avl.insert(31);
		assertEquals(10, avl.size());
		assertEquals(3, avl.height());
		assertArrayEquals(new Integer[] { 20, -10, -15, -30, 1, 33, 30, 31, 35, 38 }, avl.preOrder());

	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);

		avl.remove(-1);
		assertEquals(4, avl.size());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());

		avl.insert(-10);
		avl.insert(-15);
		avl.insert(20);
		avl.insert(30);
		avl.insert(1);
		avl.insert(-30);
		avl.insert(33);
		avl.insert(35);
		avl.insert(38);
		avl.insert(31);
		assertEquals(10, avl.size());
		assertEquals(3, avl.height());
		assertArrayEquals(new Integer[] { 20, -10, -15, -30, 1, 33, 30, 31, 35, 38 }, avl.preOrder());

		avl.remove(33);
		assertEquals(9, avl.size());
		assertArrayEquals(new Integer[] { 20, -10, -15, -30, 1, 35, 30, 31, 38 }, avl.preOrder());

		avl.remove(38);
		assertEquals(8, avl.size());
		assertArrayEquals(new Integer[] { 20, -10, -15, -30, 1, 31, 30, 35 }, avl.preOrder());

		avl.remove(30);
		avl.remove(35);
		assertEquals(6, avl.size());
		assertArrayEquals(new Integer[] { -10, -15, -30, 20, 1, 31 }, avl.preOrder());

	}
}
