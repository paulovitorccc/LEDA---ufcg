package adt.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackRecursiveDoubleLinkedListImplTest {

	private StackRecursiveDoubleLinkedListImpl<Integer> stack1;

	@Before
	public void setUp() throws Exception {

		getImplementations();
		
		stack1.push(2);
		stack1.push(3);
		

	}
	
	private void getImplementations() {
		stack1 = new StackRecursiveDoubleLinkedListImpl<>(5);
	}
	
	@Test
	public void testEmpty() {
		assertFalse(stack1.isEmpty());
	}
	
	@Test
	public void testTop() {
		assertTrue(stack1.top() == 3);
	}
	
	@Test
	public void testFullException(){
		try{
			stack1.push(5);
			stack1.push(2);
			stack1.push(8);
			stack1.push(9);	
		}catch(Exception ex){
			assertEquals("Stack is full", ex.getMessage());
		}
	
	}
	

}
