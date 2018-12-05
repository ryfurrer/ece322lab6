package arraylib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayLibTest {
    private Integer[] duplicateNumbers;
    private Integer[] duplicateNumbersWithout5;
    private Integer []  sortedNumbers;
    private Integer []  uniqueNumbers;
    private Integer []  uniqueNumbersNulls;
    private Integer []  reverseNumbers;
    private Integer []  empty = {};

    @Before
    public void setUp() {
        Integer[] sortedNumbers1 = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] reverseNumbers1 = {8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] duplicateNumbers1 = {1, 8, 2, 3, 4, 8, 5, 6, 2, 7, 8, 8};
        Integer[] duplicateNumbersWithout5_1 = {1, 8, 2, 3, 4, 8, 6, 2, 7, 8, 8};
        Integer[] uniqueNumbers1 = {1, 8, 2, 3, 4, 5, 6, 7};
        Integer[] uniqueNumbersNulls1 = {1, 8, 2, 3, 4, 5, 6, 7, null};
        sortedNumbers = sortedNumbers1;
        reverseNumbers = reverseNumbers1;
        uniqueNumbers = uniqueNumbers1;
        duplicateNumbers = duplicateNumbers1;
        duplicateNumbersWithout5 = duplicateNumbersWithout5_1;
        uniqueNumbersNulls = uniqueNumbersNulls1;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void reverseTest() {
        assertArrayEquals(reverseNumbers, ArrayLib.reverse(sortedNumbers, Integer.class));
    }

    @Test
    public void uniqueTest() {
        assertArrayEquals(uniqueNumbers, ArrayLib.unique(duplicateNumbers, Integer.class));
    }

    @Test
    public void intersectionTest() {
        assertArrayEquals(uniqueNumbers, ArrayLib.intersection(uniqueNumbers, uniqueNumbers, Integer.class));
    }

    @Test
    public void intersectionTest2() {
        assertArrayEquals(empty, ArrayLib.intersection(uniqueNumbers, empty, Integer.class));
    }

    @Test
    public void unionTest() {
        assertArrayEquals(uniqueNumbers, ArrayLib.union(uniqueNumbers, empty, Integer.class));
    }

    @Test
    public void unionTest2() {
        assertArrayEquals(uniqueNumbers, ArrayLib.union(uniqueNumbers, uniqueNumbers, Integer.class));
    }

    @Test
    public void compactTest() {
        assertArrayEquals(uniqueNumbers, ArrayLib.compact(uniqueNumbersNulls, Integer.class));
    }

    @Test
    public void indexOfTest() {
        assertEquals(7, ArrayLib.indexOf(uniqueNumbers, 7));
    }

    @Test
    public void indexOfTest2() {
        assertEquals(6, ArrayLib.indexOf(sortedNumbers, 7));
    }

    @Test
    public void indexOfTest3() {
        assertEquals(-1, ArrayLib.indexOf(sortedNumbers, 12));
    }

    @Test
    public void containsTest() {
        assertTrue(ArrayLib.contains(uniqueNumbers, 7));
    }

    @Test
    public void containsTest2() {
        assertFalse(ArrayLib.contains(uniqueNumbers, 9));
    }

    @Test
    public void withoutTest() {
        assertArrayEquals(duplicateNumbersWithout5, ArrayLib.without(duplicateNumbers, Integer.class, 10, 12, 5));
    }

    @Test
    public void withoutTest2() {
        assertArrayEquals(duplicateNumbersWithout5, ArrayLib.without(duplicateNumbersWithout5, Integer.class, 10, 12, 5));
    }

    @Test
    public void withoutTest3() {
        assertEquals(new String[]{"bar", "ree"}, ArrayLib.without(new String[]{"foo", "bar", "ree"}, String.class, "foo"));
    }

    @Test
    public void withoutTest4() {
        assertEquals(new String[]{"foo", "ree"}, ArrayLib.without(new String[]{"foo", "bar", "ree"}, String.class, "bar"));
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void withoutTest5() {
        ArrayLib.without(duplicateNumbers, Integer.class, null);
    }
}