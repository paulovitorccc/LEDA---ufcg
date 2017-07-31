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

	@Test
	public void testHeight() {
		assertEquals(-1, tree1.height());
		for(int i = 1; i <= 9; i++){
			tree1.insert(i);
		}
		assertEquals(1, tree1.height());
		for(int i = 10; i <= 14; i++){
			tree1.insert(i);
		}
		assertEquals(2, tree1.height());
		for(int i = 15; i <= 22; i++){
			tree1.insert(i);
		}
		assertEquals(2, tree1.height());
		assertEquals(22, tree1.size());
	}

	@Test
	public void testDepthLeftOrder() {
		tree1.insert(2);
		tree1.insert(4);
		tree1.insert(6);
		tree1.insert(8);
		try {
			assertEquals("[[6], [2, 4], [8]]",
					Arrays.toString(tree1.depthLeftOrder()));
		} catch (AssertionError e) {
			assertEquals("[[4], [2], [6, 8]]",
					Arrays.toString(tree1.depthLeftOrder()));
		}
	}

	@Test
	public void testSize() {
		assertEquals(0, tree1.size());
		tree1.insert(18);
		assertEquals(1, tree1.size());
		tree1.insert(-1);
		tree1.insert(5);
		tree1.insert(8);
		tree1.insert(10);
		tree1.insert(2);
		assertEquals(6, tree1.size());
		
	}
	
	@Test
	public void testAltura(){
		assertEquals(-1, tree1.height());
		tree1.insert(2);
		assertEquals(0, tree1.height());
		tree1.insert(4);
		tree1.insert(6);
		tree1.insert(8);
		assertEquals(1, tree1.height());
		tree1.insert(20);
		tree1.insert(30);
		assertEquals(6,tree1.size());
		assertEquals(70,((BTreeImpl<Integer>) tree1).sumKey());
		assertEquals(1, tree1.height());
		tree1.insert(80);
		tree1.insert(25);
		assertEquals(1, tree1.height());
		tree1.insert(42);
		assertEquals(1, tree1.height());
		tree1.insert(60);
		assertEquals(1, tree1.height());
	}

}
