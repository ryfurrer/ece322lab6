package maths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MathPackageTest {
    private double[] duplicateNumbers;

    @Before
    public void setUp() {
        /*
        Total Numbers
        12
        Mean (Average)
        5.16667
        Standard deviation
        2.69118
        Variance(Standard deviation)
        7.24242
        Population Standard deviation
        2.5766
        Variance(Population Standard deviation)
        6.63889
         */
        duplicateNumbers = new double[]{1, 8, 2, 3, 4, 8, 5, 6, 2, 7, 8, 8};
    }

    @After
    public void tearDown() {
    }

    @Test
    public void random() {
        assertArrayEquals(new double[]{}, MathPackage.random(0, 0.0, 0.0));
        assertEquals(0, MathPackage.random(0, 0.0, 0.0).length);
        assertEquals(1, MathPackage.random(1, 0.0, 0.0).length);
        assertEquals(1, MathPackage.random(1, 1.0, 0.0).length);
        assertEquals(1, MathPackage.random(1, 0.0, 1.0).length);

        assertArrayEquals(new double[]{1.0}, MathPackage.random(1, 1.0, 1.0));
        double lower = 4;
        double upper = 6;
        double[] tc1 = MathPackage.random(5, lower, upper);
        double[] tc2 = MathPackage.random(5, lower, upper);
        double[] tc3 = MathPackage.random(5, lower, upper);
        double[] tc4 = MathPackage.random(5, lower, upper);
        double[] tc5 = MathPackage.random(5, lower, upper);
        assertFalse(Arrays.equals(tc1,tc2) && Arrays.equals(tc1, tc3) &&
                Arrays.equals(tc1, tc4) && Arrays.equals(tc1, tc5));
    }

    @Test
    public void random2() {
        double lower = 4;
        double upper = 6;
        double[] tc1 = MathPackage.random(5, lower, upper);
        for (double number: tc1) {
            assertTrue(number >= lower);
            assertTrue(number < upper);
        }
        assertEquals(5, tc1.length);
    }

    @Test
    public void max() {
        assertEquals(8, MathPackage.max(duplicateNumbers));
    }

    @Test
    public void min() {
        assertEquals(1, MathPackage.min(duplicateNumbers));
    }

    @Test
    public void maxNull() {
        assertEquals(Double.MIN_VALUE, MathPackage.max(new double[]{}));
    }

    @Test
    public void minNull() {
        assertEquals(Double.MAX_VALUE, MathPackage.min(new double[]{}));
    }

    @Test
    public void normalize() {
        assertArrayEquals(new double[]{}, MathPackage.normalize(new double[]{}));

        assertArrayEquals(new double[]{0}, MathPackage.normalize(new double[]{0}));
        assertArrayEquals(new double[]{0}, MathPackage.normalize(new double[]{0}));
        assertArrayEquals(new double[]{0, 0}, MathPackage.normalize(new double[]{0, 0}));
    }

    @Test
    public void normalizeNaN() {
        assertArrayEquals(new double[]{0.25, 0.25, 0.25, 0.25},
                MathPackage.normalize(new double[]{1000000000, 1000000000, 1000000000, 1000000000}),
                0.0001);
    }

    @Test
    public void normalizeWithRandom() {
        double lower = 4;
        double upper = 6;
        double[] tc1 = MathPackage.random(5, lower, upper);
        tc1 = MathPackage.normalize(tc1);
        for (double number: tc1) {
            assertTrue(number >= 0);
            assertTrue(number <= 1);
        }
        assertEquals(5, tc1.length);
    }

    @Test
    public void sum() {
        assertEquals(0, MathPackage.sum(new double[]{}));
        double sum = 0;
        for (double d: duplicateNumbers)
            sum += d;
        assertTrue(MathPackage.sum(duplicateNumbers) == sum);
    }

    @Test
    public void stddev() {
        assertEquals(Double.NaN, MathPackage.stddev(new double[]{}));
        assertEquals(0, MathPackage.stddev(new double[]{0}));
        assertEquals(0.5, MathPackage.stddev(new double[]{0, 1}));
        assertEquals(MathPackage.stddev(duplicateNumbers) , 2.5766, 0.0001);
    }

    @Test
    public void arrayAdd() {
        assertArrayEquals(new double[]{}, MathPackage.arrayAdd(new double[]{}, new double[]{}));
        assertArrayEquals(new double[]{2, 2}, MathPackage.arrayAdd(new double[]{1, 1}, new double[]{1, 1}));
        assertArrayEquals(new double[]{1, 1}, MathPackage.arrayAdd(new double[]{0, 0}, new double[]{1, 1}));
        double lower = 4;
        double upper = 6;
        double[] tc1 = MathPackage.random(5, lower, upper);
        double[] tc2 = MathPackage.arrayAdd(tc1, duplicateNumbers);
        for (int i = 0; i < tc2.length; i++) {
            assertEquals(tc2[i], tc1[i] + duplicateNumbers[i]);
        }
        assertEquals(5, tc2.length);
    }

    @Test
    public void arrayNegate() {
        assertArrayEquals(new double[]{}, MathPackage.arrayNegate(new double[]{}));
        assertArrayEquals(new double[]{0}, MathPackage.arrayNegate(new double[]{0}));
        assertArrayEquals(new double[]{-1}, MathPackage.arrayNegate(new double[]{1}));
        double lower = 4;
        double upper = 6;
        double[] tc1 = MathPackage.random(5, lower, upper);
        double[] tc2 = MathPackage.arrayNegate(tc1);
        for (int i = 0; i < tc2.length; i++) {
            assertEquals(tc2[i], -tc1[i]);
        }
        assertEquals(5, tc2.length);
    }
}