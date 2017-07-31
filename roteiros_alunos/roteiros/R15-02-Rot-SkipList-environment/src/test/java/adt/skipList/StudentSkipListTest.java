package adt.skipList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import adt.skipList.SkipList;
import adt.skipList.SkipListImpl;
import adt.skipList.SkipListNode;

public class StudentSkipListTest {

	SkipList<String> skip;
	SkipListNode<String>[] array;

	@Before
	public void setUp() {
		skip = new SkipListImpl<String>(4);
	}

	@Test
	public void testInsert() {
		assertEquals(0, skip.size());
		skip.insert(10, "A", 2);
		assertEquals(1, skip.size());
		skip.insert(20, "B", 1);
		assertEquals(2, skip.size());
		skip.insert(0, "C", 1);
		assertEquals(3, skip.size());
		skip.insert(15, "D", 3);
		assertEquals(4, skip.size());
		skip.insert(5, "E", 3);
		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <0,1>, <5,3>, <10,2>, <15,3>, <20,1>, <NIL,4>]", Arrays.toString(array));
		assertEquals(0, array[0].getForward(0).getKey());
		assertEquals(5, array[0].getForward(1).getKey());
		assertEquals(5, array[0].getForward(2).getKey());
		assertEquals(5, array[1].getForward(0).getKey());
		assertEquals(10, array[2].getForward(0).getKey());
		assertEquals(10, array[2].getForward(1).getKey());
		assertEquals(15, array[2].getForward(2).getKey());
		assertEquals(15, array[3].getForward(0).getKey());
		assertEquals(15, array[3].getForward(1).getKey());
		assertEquals(20, array[4].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(2).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());
		String[] array2 = { "C", "B" };
		String result = Arrays.toString(((SkipListImpl<String>) skip).getNodesAtHeight(1));
		assertEquals(result, Arrays.toString(array2));
		assertEquals(3, skip.height());

		List<String> aux = new ArrayList<>();
		for (int i = 1; i < array.length - 1; ++i)
			aux.add(array[i].getValue());

		assertEquals("[C, E, A, D, B]", aux.toString());

		skip.insert(0, "A", 1);
		skip.insert(5, "B", 3);
		skip.insert(10, "C", 2);
		skip.insert(15, "D", 3);
		skip.insert(20, "E", 1);

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <0,1>, <5,3>, <10,2>, <15,3>, <20,1>, <NIL,4>]", Arrays.toString(array));
		assertEquals(0, array[0].getForward(0).getKey());
		assertEquals(5, array[0].getForward(1).getKey());
		assertEquals(5, array[0].getForward(2).getKey());
		assertEquals(5, array[1].getForward(0).getKey());
		assertEquals(10, array[2].getForward(0).getKey());
		assertEquals(10, array[2].getForward(1).getKey());
		assertEquals(15, array[3].getForward(0).getKey());
		assertEquals(20, array[4].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());

		aux.clear();
		for (int i = 1; i < array.length - 1; ++i)
			aux.add(array[i].getValue());

		assertEquals("[A, B, C, D, E]", aux.toString());
	}

	@Test
	public void testSearch() {
		skip.insert(10, "A", 2);
		skip.insert(20, "B", 1);
		assertEquals(2, ((SkipListImpl<String>) skip).heightRecursive());
		assertEquals(2, skip.height());
		skip.insert(0, "C", 1);
		skip.insert(15, "D", 3);
		assertEquals(3, skip.height());
		skip.insert(5, "E", 2);
		assertEquals("A", skip.search(10).getValue());
		assertEquals("B", skip.search(20).getValue());
		assertEquals("C", skip.search(0).getValue());
		assertEquals("D", skip.search(15).getValue());
		assertEquals("E", skip.search(5).getValue());

		assertEquals(null, skip.search(-10));
		assertEquals(null, skip.search(30));
		assertEquals(null, skip.search(9));
		assertEquals(3, ((SkipListImpl<String>) skip).heightRecursive());
	}

	@Test
	public void testSearchRecursie() {
		skip.insert(10, "A", 2);
		skip.insert(20, "B", 1);
		assertEquals(2, skip.height());
		skip.insert(0, "C", 1);
		skip.insert(15, "D", 3);
		assertEquals(3, skip.height());
		skip.insert(5, "E", 2);

		assertEquals("A", ((SkipListImpl<String>) skip).searchRecursive(10).getValue());
		assertEquals("B", ((SkipListImpl<String>) skip).searchRecursive(20).getValue());
		assertEquals("C", ((SkipListImpl<String>) skip).searchRecursive(0).getValue());
		assertEquals("D", ((SkipListImpl<String>) skip).searchRecursive(15).getValue());
		assertEquals("E", ((SkipListImpl<String>) skip).searchRecursive(5).getValue());

		assertEquals(null, ((SkipListImpl<String>) skip).searchRecursive(-10));
		assertEquals(null, ((SkipListImpl<String>) skip).searchRecursive(30));
		assertEquals(null, ((SkipListImpl<String>) skip).searchRecursive(9));
	}

	@Test
	public void getNodesAtHeightTest() {

		skip.insert(3, "A", 2);
		skip.insert(5, "B", 1);
		skip.insert(6, "C", 2);
		skip.insert(7, "D", 1);
		skip.insert(8, "F", 1);
		skip.insert(9, "G", 2);
		skip.insert(14, "H", 1);
		skip.insert(12, "I", 4);
		skip.insert(2, "J", 4);
		skip.insert(18, "K", 4);
		assertEquals(4, ((SkipListImpl<String>) skip).heightRecursive());
		assertEquals(4, ((SkipListImpl<String>) skip).searchRecursive(12).height());
		String[] array = { "J", "I", "K" };
		String result = Arrays.toString(((SkipListImpl<String>) skip).getNodesAtHeight(4));
		assertEquals(result, Arrays.toString(array));
		assertFalse(((SkipListImpl<String>) skip).greatRelation());

	}

	@Test
	public void testGoodSkip() {

		skip.insert(3, "A", 1);
		assertTrue(((SkipListImpl<String>) skip).greatRelation());
		skip.insert(5, "B", 2);
		assertTrue(((SkipListImpl<String>) skip).greatRelation());
		skip.insert(6, "C", 1);
		assertTrue(((SkipListImpl<String>) skip).greatRelation());
		skip.insert(9, "G", 2);
		assertTrue(((SkipListImpl<String>) skip).greatRelation());
		skip.insert(10, "F", 1);
		assertTrue(((SkipListImpl<String>) skip).greatRelation());
		skip.insert(12, "H", 2);
		assertTrue(((SkipListImpl<String>) skip).greatRelation());
		skip.insert(15, "D", 2);
		assertFalse(((SkipListImpl<String>) skip).greatRelation());

	}

	@Test
	public void testAllElements() {

		skip.insert(3, "A", 2);
		skip.insert(5, "B", 1);
		skip.insert(6, "C", 3);
		skip.insert(7, "D", 1);
		skip.insert(8, "F", 1);
		skip.insert(9, "G", 3);
		skip.insert(14, "H", 1);
		skip.insert(12, "I", 4);
		skip.insert(2, "J", 4);
		skip.insert(18, "K", 4);
		System.out.println(skip.height());
		System.out.println(skip.size());
		System.out.println(Arrays.toString(((SkipListImpl<String>) skip).allElements()));

	}

	@Test
	public void testRemove() {
		assertEquals(0, skip.size());
		skip.insert(10, "A", 1);
		skip.insert(20, "B", 2);
		skip.insert(0, "C", 2);
		skip.insert(15, "D", 3);
		skip.insert(5, "E", 1);

		skip.insert(-10, "F", 1);
		skip.insert(30, "G", 3);
		skip.insert(9, "H", 2);
		skip.insert(17, "I", 2);
		skip.insert(-2, "J", 1);

		assertEquals(10, skip.size());

		skip.remove(10);
		skip.remove(20);
		skip.remove(0);
		skip.remove(15);
		skip.remove(5);

		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <-10,1>, <-2,1>, <9,2>, <17,2>, <30,3>, <NIL,4>]", Arrays.toString(array));
		assertEquals(-10, array[0].getForward(0).getKey());
		assertEquals(9, array[0].getForward(1).getKey());
		assertEquals(30, array[0].getForward(2).getKey());
		assertEquals(-2, array[1].getForward(0).getKey());
		assertEquals(9, array[2].getForward(0).getKey());
		assertEquals(17, array[3].getForward(0).getKey());
		assertEquals(17, array[3].getForward(1).getKey());
		assertEquals(30, array[4].getForward(0).getKey());
		assertEquals(30, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(2).getKey());

		skip.remove(-10);
		skip.remove(30);
		skip.remove(9);
		skip.remove(17);
		skip.remove(-2);

		assertEquals(0, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <NIL,4>]", Arrays.toString(array));
		assertEquals(Integer.MAX_VALUE, array[0].getForward(0).getKey());
	}
}
