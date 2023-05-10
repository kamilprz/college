import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );
        testDLL.insertBefore(6,6);        
        assertEquals( "Checking insertBefore to a list containing 9 elemenets at position 6", "7,4,5,6,1,2,6,3,8,9", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    
    @Test
    public void testIsEmpty() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertEquals(true,testDLL.isEmpty());
    	testDLL.insertBefore(0,1);
    	assertEquals(false,testDLL.isEmpty());

    }
    
    @Test
    public void testGet() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
        assertEquals(null, testDLL.get(0));
    	
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,6);
        testDLL.insertBefore(4,5);
        
        Integer number= new Integer(1);
        assertEquals(number, testDLL.get(0));
        
        Integer number2= new Integer(3);
        assertEquals(number2, testDLL.get(2));
        
        Integer number3= new Integer(5);
        assertEquals(number3, testDLL.get(4));
        
        Integer number4= new Integer(6);
        assertEquals(number4, testDLL.get(3));
        
        assertEquals(null, testDLL.get(29));
    }
    
    
    @Test
    public void testDeleteAt() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
        assertEquals(false, testDLL.deleteAt(0));
    	
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.insertBefore(4,5);
        testDLL.insertBefore(5,6);
        testDLL.insertBefore(6,7);
        
        testDLL.deleteAt(2);
        assertEquals("1,2,4,5,6,7", testDLL.toString() );
        
        testDLL.deleteAt(4);
        assertEquals("1,2,4,5,7", testDLL.toString() );
        
        testDLL.deleteAt(0);
        assertEquals("2,4,5,7", testDLL.toString() );
        
        testDLL.deleteAt(3);
        assertEquals("2,4,5", testDLL.toString() );
        
        testDLL.deleteAt(29);
        assertEquals("2,4,5", testDLL.toString() );
    }
    
    @Test
    public void testReverse() {
       	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       	
       	testDLL.reverse();
       	assertEquals("", testDLL.toString() );	
       	
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.reverse();
        assertEquals("3,2,1", testDLL.toString() );
    }
    
    @Test
    public void testMakeUnique() {
       	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,4);
        testDLL.insertBefore(3,1);
        testDLL.makeUnique();
        assertEquals("1,2,4", testDLL.toString() );
        testDLL.insertBefore(3,4);
        testDLL.insertBefore(4,5);
        testDLL.insertBefore(5,6);
        testDLL.insertBefore(6,6);
        testDLL.makeUnique();
        assertEquals("1,2,4,5,6", testDLL.toString() );
        
        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.insertBefore(0,1);
        testDLL2.insertBefore(1,1);
        testDLL2.insertBefore(2,1);
        testDLL2.insertBefore(3,1);
        System.out.println(testDLL2.toString());
        testDLL2.makeUnique();
        System.out.println(testDLL2.toString());
        assertEquals("1",testDLL2.toString());
        assertEquals(false,testDLL2.deleteAt(1));
        
        DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
        testDLL3.insertBefore(0,2);
        testDLL3.insertBefore(1,1);
        testDLL3.insertBefore(2,2);
        testDLL3.insertBefore(3,1);
        testDLL3.insertBefore(4,1);
        testDLL3.insertBefore(5,2);
        testDLL3.insertBefore(6,1);
        System.out.println(testDLL3.toString());
        testDLL3.makeUnique();
        System.out.println(testDLL3.toString());
        assertEquals(null,testDLL3.get(2));
        
    }
    
    @Test
    public void testPush() {
     	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
     	Integer number= new Integer(1);
     	testDLL.push(number);
     	assertEquals("1",testDLL.toString());	
     	
     	Integer number2= new Integer(2);
     	testDLL.push(number2);
     	assertEquals("2,1",testDLL.toString());	
     	
     	Integer number3= new Integer(3);
     	testDLL.push(number3);
     	assertEquals("3,2,1",testDLL.toString());	
    }
    
    @Test
    public void testPop() {
     	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
     	Integer number= new Integer(1);
     	testDLL.push(number);
     	Integer number2= new Integer(2);
     	testDLL.push(number2);
     	Integer number3= new Integer(3);
     	testDLL.push(number3);
     	assertEquals(number3,testDLL.pop());
     	assertEquals(number2,testDLL.pop());
     	assertEquals(number,testDLL.pop());
     	assertEquals(null,testDLL.pop());
     	assertEquals(null,testDLL.pop());
    }
    
    @Test
    public void testEnqueue() {
     	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
     	Integer number= new Integer(1);
     	testDLL.enqueue(number);
     	assertEquals("1",testDLL.toString());
     
     	Integer number2= new Integer(2);
     	testDLL.enqueue(number2);
     	assertEquals("2,1",testDLL.toString());	
     	
     	Integer number3= new Integer(3);
     	testDLL.enqueue(number3);
     	assertEquals("3,2,1",testDLL.toString());	
    }
    
    @Test
    public void testDequeue() {
     	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
     	Integer number= new Integer(1);
     	testDLL.enqueue(number);
     	
     	Integer number2= new Integer(2);
     	testDLL.enqueue(number2);
     	
     	Integer number3= new Integer(3);
     	testDLL.enqueue(number3);
     	
     	assertEquals(number,testDLL.dequeue());
     	assertEquals(number2,testDLL.dequeue());
     	assertEquals(number3,testDLL.dequeue());
     	assertEquals(null,testDLL.dequeue());
    }
}
