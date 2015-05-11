/**
 * @author Eric Bowers.
 * Date: 2/27/2014.
 *
 */
package dictTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dict.HashDictionary;

/** Testing the hashDictionary.java. */
public class HashDictionaryTest {
    /** Object for testPut. */
    private HashDictionary[] tPut;
    /** Object for testGet. */
    private HashDictionary[] tGet;
    /** Object for testRemove. */
    private HashDictionary[] tRemove;
    /** Object to testToString. */
    private HashDictionary[] tToString;
    /** Integer being tested in the methods. */
    private static final int TESTINT = 8;
    /** Double being tested in the methods. */
    private static final double TESTDOUBLE = 1000;
    /** Boolean being tested in the methods. */
    private static final boolean TESTBOOL = true;
    /** Integer to set to Five. */
    private static final int FIVE = 5;
    /** Integer to set to Three. */
    private static final int THREE = 3;
    /** Integer to set to Two. */
    private static final int TWO = 2;
    /** Integer to set to Four. */
    private static final int FOUR = 4;

    // *****************************************
    // *****************************************
    // *****************************************
    /** Test being set up for the put method. */
    @Before
    public final void setUpPut() {
        tPut = new HashDictionary[FIVE];
        for (int x = 0; x < tPut.length; x++) {
            tPut[x] = new HashDictionary();
        }
    }

    /** Testing one object with the put method. */
    @Test
    public final void testPutForOneObject() {
        tPut[0].put("a", "Object 1");
        String expected = "[a:Object 1]";
        String results = tPut[0].toString();
        assertEquals(expected, results);
    }

    /** Testing multiple object for the put method. */
    @Test
    public final void testPutForMultObject() {
        tPut[1].put("a", "Object 2");
        tPut[1].put("a", "Object 3");
        tPut[1].put("a", "Object 4");
        String expected = "[a:Object 2, a:Object 3, a:Object 4]";
        String results = tPut[1].toString();
        assertEquals(expected, results);
    }

    /** Testing null for the put method. */
    @Test
    public final void testPutForNull() {
        assertNull(tPut[TWO].get("Object 5"));
    }

    /** Testing a Integer data type for the put method. */
    @Test
    public final void testPut4WithGetWithInt() {
        tPut[THREE].put("e", TESTINT);
        int expected = TESTINT;
        assertSame(expected, tPut[THREE].get("e"));
    }

    /** Testing a Boolean data type for put method. */
    @Test
    public final void testPutWithGetWithBool() {
        tPut[FOUR].put("f", TESTBOOL);
        boolean expected = true;
        assertEquals(tPut[FOUR].get("f"), expected);
    }

    /** Testing if true a object is there. */
    public final void testPutWithGetIfTrue() {
        Object expected = "Object 2";
        assertTrue(expected == tPut[1].get("a"));
    }

    /** Testing to see if a put is empty. */
    public final void testPutEmpty() {
        String expected = "[]";
        assertEquals(expected, tPut[TWO].toString());
    }

    // *****************************************
    // *****************************************
    // *****************************************
    /** Test being set up for the get method. */
    @Before
    public final void setUpGet() {
        tGet = new HashDictionary[FIVE];
        for (int x = 0; x < tGet.length; x++) {
            tGet[x] = new HashDictionary();
        }
        tGet[0].put("a", "Object 1");
        tGet[0].put("b", "Object 2");
        tGet[0].put("a", "Object 3");
        tGet[0].put("c", "Object 4");
        tGet[0].put("d", "Object 5");
        // *************************
        tGet[1].put("x", TESTINT);
        tGet[1].put("y", TESTDOUBLE);
        tGet[1].put("z", TESTBOOL);
    }

    /** Testing null for the get method. */
    @Test
    public final void testGetNull() {
        assertNull(tGet[1].get("a"));
    }

    /** Testing not null for the get method. */
    @Test
    public final void testGetNotNull() {
        assertNotNull(tGet[0].get("a"));
    }

    /** Testing the Top boundary for the get method. */
    @Test
    public final void testGetTopBoundary() {
        Object expected = "Object 1";
        Object results = tGet[0].get("a");
        assertEquals(expected, results);
    }

    /** Testing the bottom boundary for the get method. */
    @Test
    public final void testGetBottomBoundary() {
        Object expected = "Object 5";
        Object results = tGet[0].get("d");
        assertEquals(expected, results);
    }

    /** Testing data type for the get method. */
    @Test
    public final void testGetInteger() {
        Object expected = TESTINT;
        Object results = tGet[1].get("x");
        assertEquals(expected, results);
    }

    /** Testing a data type for the get method. */
    @Test
    public final void testGetDouble() {
        double expected = TESTDOUBLE;
        Object results = tGet[1].get("y");
        assertEquals(expected, results);
    }

    /** Testing a data type for the get method. */
    @Test
    public final void testGetBool() {
        Object expected = true;
        Object results = tGet[1].get("z");
        assertEquals(expected, results);
    }

    /** Testing a get method with a remove method and get null. */
    public final void testGetRemoveNull() {
        tGet[0].remove("b");
        assertNull(tGet[0].get("b"));
    }

    // *****************************************
    // *****************************************
    // *****************************************
    /** Test being set up for the remove method. */
    @Before
    public final void setUpRemove() {
        tRemove = new HashDictionary[TWO];
        for (int x = 0; x < tRemove.length; x++) {
            tRemove[x] = new HashDictionary();
        }
        tRemove[0].put("a", "Object 1");
        tRemove[0].put("a", "Object 2");
        tRemove[0].put("b", "Object 3");
        tRemove[0].put("c", "Object 4");
        tRemove[0].put("d", "Object 5");
        tRemove[0].put(null, "Object 6");
        // ******************************
        tRemove[1].put("a", "Object 1");
        tRemove[1].put("a", "Object 2");
        tRemove[1].put("a", "Object 3");
        tRemove[1].put("a", "Object 4");
    }

    /** Testing null for the remove method. */
    @Test
    public final void testRemoveNull() {
        assertNull(tRemove[0].remove("e"));
    }

    /** Testing the top boundary for the remove method. */
    @Test
    public final void testRemoveTopBoundary() {
        tRemove[0].remove("a");
        String expected = "Object 2";
        assertEquals(expected, tRemove[0].get("a"));
    }

    /** Testing the bottom boundary for the remove method. */
    @Test
    public final void testRemoveBottomBoundary() {
        tRemove[0].remove(null);
        assertNull(tRemove[0].get(null));
    }

    /** Testing the middle boundary for the remove method. */
    @Test
    public final void testRemoveMiddleBoundary() {
        tRemove[0].remove("b");
        assertNull(tRemove[0].get("b"));
    }

    /** Testing multiple removed objects from the remove method. */
    @Test
    public final void testRemoveMultObject() {
        tRemove[1].remove("a");
        tRemove[1].remove("a");
        tRemove[1].remove("a");
        String expected = "Object 4";
        assertEquals(expected, tRemove[1].get("a"));
    }

    // *****************************************
    // *****************************************
    // *****************************************
    /** Test being set up for the toString method. */
    @Before
    public final void setUpToString() {
        tToString = new HashDictionary[THREE];
        for (int x = 0; x < tToString.length; x++) {
            tToString[x] = new HashDictionary();
        }
        tToString[0].put("a", "Object 1");
        tToString[0].put("b", "Object 2");
        tToString[0].put("c", "Object 3");
        tToString[0].put("d", "Object 4");
        // ********************************
        tToString[1].put("b", "Object 1");
        tToString[1].put("b", "Object 2");
        tToString[1].put("b", "Object 3");
        tToString[1].put("b", "Object 4");
    }

    /** Testing to see if the toString is empty. */
    @Test
    public final void testToStringEmpyt() {
        String expected = "[]";
        Object results = tToString[2].toString();
        assertEquals(expected, results);
    }

    /** Testing multiples objects come out of the toString method. */
    @Test
    public final void testToStringMultObject() {
        String expected = "[a:Object 1, b:Object 2, c:Object 3, d:Object 4]";
        assertEquals(expected, tToString[0].toString());
    }

    /** Testing the remove method twice then the toString method. */
    @Test
    public final void testToStringRemoveTwice() {
        tToString[1].remove("b");
        tToString[1].remove("b");
        String expected = "[b:Object 3, b:Object 4]";
        assertEquals(expected, tToString[1].toString());
    }

    /** Testing the top boundary for the toString method. */
    @Test
    public final void testToStringRemoveTopBoundary() {
        tToString[0].remove("a");
        String expected = "[b:Object 2, c:Object 3, d:Object 4]";
        assertEquals(expected, tToString[0].toString());
    }

    /** Testing the bottom boundary for the toString method. */
    @Test
    public final void testToStringRemoveBottomBoundary() {
        tToString[0].remove("d");
        String expected = "[a:Object 1, b:Object 2, c:Object 3]";
        assertEquals(expected, tToString[0].toString());
    }
}
