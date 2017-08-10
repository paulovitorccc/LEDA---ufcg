

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private LinkedListDouble<Integer> lista1;
	private LinkedListDouble<Integer> lista2;
	private LinkedListDouble<Integer> lista3;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListIterativo<Integer>();
		lista2 = new DoubleLinkedListIterativo<Integer>();
		lista3 = new DoubleLinkedListIterativo<Integer>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		lista1.insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		lista1.removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		lista1.removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}
}