////////////////////////////////////////////////////////////////////////////////
// Main File:        BALST.java
// This File:        BALSTTest.java
// Other Files:      BALST.java, BSTNode.java, BALSTTest.java
// Semester:         CS 400 Fall 2019
//
// Author:           Yeochan Youn
// Email:            yyoun5@wisc.edu
// CS Login:         yeochan
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the tree is balanced or not

//@SuppressWarnings("rawtypes")
public class BALSTTest {

    BALST<String,String> balst1;	
    BALST<Integer,String> balst2;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        balst1 = createInstance();
	balst2 = createInstance2();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        balst1 = null;
	balst2 = null;
    }

    protected BALST<String, String> createInstance() {
        return new BALST<String,String>();
    }

    protected BALST<Integer,String> createInstance2() {
        return new BALST<Integer,String>();
    }

    /** 
     * Insert three values in sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred.
     */
    @Test
    void testBALST_001_insert_sorted_order_simple() {
        try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("avl insert at root does not work");
            balst2.insert(20, "20");
            if (!balst2.getKeyOfRightChildOf(10).equals(20)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(30, "30");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }

    /** 
     * Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_002_insert_reversed_sorted_order_simple() {
        // TODO: implement this test
    	 try {
             balst2.insert(30, "30");
             if (!balst2.getKeyAtRoot().equals(30)) 
                 fail("avl insert at root does not work");
             balst2.insert(20, "20");
             if (!balst2.getKeyOfLeftChildOf(30).equals(20)) 
                 fail("avl insert to right child of root does not work");
             
             balst2.insert(10, "10");
             Integer k = balst2.getKeyAtRoot();
             if (!k.equals(20)) 
                 fail("avl rotate does not work");
             
             // IF rebalancing is working,
             // the tree should have 20 at the root
             // and 10 as its left child and 30 as its right child

             Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
             Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
             Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

             balst2.print();
             
         } catch (Exception e) {
             e.printStackTrace();
             fail( "Unexpected exception AVL 000: "+e.getMessage() );
         }
        
    }

    /** 
     * Insert three values so that a right-left rotation is
     * needed to fix the balance.
     * 
     * Example: 10-30-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_003_insert_smallest_largest_middle_order_simple() {
        
        // TODO: implement this test
    	 try {
             balst2.insert(10, "10");
             if (!balst2.getKeyAtRoot().equals(10)) 
                 fail("avl insert at root does not work");
             balst2.insert(30, "30");
             if (!balst2.getKeyOfRightChildOf(10).equals(30)) 
                 fail("avl insert to right child of root does not work");
             
             balst2.insert(20, "20");
             Integer k = balst2.getKeyAtRoot();
             if (!k.equals(20)) 
                 fail("avl rotate does not work");
             
             // IF rebalancing is working,
             // the tree should have 20 at the root
             // and 10 as its left child and 30 as its right child

             Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
             Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
             Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

             balst2.print();
             
         } catch (Exception e) {
             e.printStackTrace();
             fail( "Unexpected exception AVL 000: "+e.getMessage() );
         }
        
    }

    /** 
     * Insert three values so that a left-right rotation is
     * needed to fix the balance.
     * 
     * Example: 30-10-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_004_insert_largest_smallest_middle_order_simple() {
        
        // TODO: implement this test
    	 try {
             balst2.insert(30, "30");
             if (!balst2.getKeyAtRoot().equals(30)) 
                 fail("avl insert at root does not work");
             balst2.insert(10, "10");
             if (!balst2.getKeyOfLeftChildOf(30).equals(10)) 
                 fail("avl insert to right child of root does not work");
             
             balst2.insert(20, "20");
             Integer k = balst2.getKeyAtRoot();
             if (!k.equals(20)) 
                 fail("avl rotate does not work");
             
             // IF rebalancing is working,
             // the tree should have 20 at the root
             // and 10 as its left child and 30 as its right child

             Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
             Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
             Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));

             balst2.print();
             
         } catch (Exception e) {
             e.printStackTrace();
             fail( "Unexpected exception AVL 000: "+e.getMessage() );
         }
        
    }
    
    
    // TODO: Add your own tests
    
    // Add tests to make sure that rebalancing occurs even if the 
    // tree is larger.   Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work?  Does the tree maintain balance when a key is deleted?
    @Test
    void testBALST_005_height_check() {
    	 try {
    		 balst2.insert(10, "10");
    		 if(balst2.getHeight() != 1) {
    			 fail("height should be 1, but " + balst2.getHeight()); // if height incresed prorperly, height should be match with number of node inserted
    		 }
    		 balst2.insert(20, "20");
    		 if(balst2.getHeight() != 2) {
    			 fail("height should be 2, but " + balst2.getHeight());
    		 }
    		 balst2.insert(30, "30");
    		 if(balst2.getHeight() != 2) {
    			 fail("height should be 2, but " + balst2.getHeight());
    		 }
    		 balst2.insert(40, "40");
    		 if(balst2.getHeight() != 3) {
    			 fail("height should be 3, but " + balst2.getHeight());
    		 }
    		 balst2.insert(50, "50");
    		 if(balst2.getHeight() != 3) {
    			 fail("height should be 3, but " + balst2.getHeight());
    		 }
    		 balst2.insert(60, "60");
    		 if(balst2.getHeight() != 3) {
    			 fail("height should be 3, but " + balst2.getHeight());
    		 }	
    		 balst2.print();
         } catch (Exception e) {
             e.printStackTrace();
             fail( "Unexpected exception AVL 000: "+e.getMessage() );
         }
    	
    }
    
    @Test
    void testBALST_006_get_check() {
    	try {
    		for(int i = 0; i < 7; i++) {
   			 String val = "" + i;
   			 balst2.insert(i, val); 
   		 }
    		for(int i = 0; i < 7; i++) { // if insert and get works, returned value should match with checking value 
    			String s = "" + i;    	
    			if(!s.equals(balst2.get(i))) {
    				fail("tree should get " + i + "but it does not");
    			}
    		}
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    @Test
    void testBALST_007_delete_check() {
    	try {
    		for(int i = 0; i < 4; i++) {
   			 String val = "" + i;
   			 balst2.insert(i, val); 
   		 }
    		for(int i = 0; i < 4; i++) {
      			 balst2.remove(i); 
      			 if(balst2.contains(i)) { // if remove works, contain should return false
      				 fail("balst2 should not have " + i + "removed key but it has");
      			 }
      		 }
    		
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    @Test
    void testBALST_008_remove_get_succesor() {
    	try {
    		for(int i = 1; i < 7; i++) {
   			 String val = "" + i;
   			 balst2.insert(i, val); 
   		 }
    		balst2.remove(2);
    		if(balst2.getKeyOfLeftChildOf(4) != 1) { // if remove and getting successor works, left child of 4 should 1
    			fail("after remove 2, succesor should be 1, but it is " + balst2.getKeyOfLeftChildOf(4));
    			balst2.print();
    		}
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    @Test
    void testBALST_009_check_numKeys() {
    	try {
    		for(int i = 1; i < 7; i++) {
      			 String val = "" + i;
      			 balst2.insert(i, val);
      			 if(balst2.numKeys() != i) { // if numKeys works, number of key should match with number of inserted node
      				 fail("numKeys does not match");
      			 }
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		fail( "Unexpected exception AVL 000: "+e.getMessage() );
    	}
    }    

}
