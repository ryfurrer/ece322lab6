package maths;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;



public class CommitTest {
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
    public void constructor() {
        Commit commit = new Commit();
        assertNotNull(commit);
    }

    @Test
    public void random() {
        assertArrayEquals(new double[]{}, Commit.random(0, 0.0, 0.0));
        assertEquals(0, Commit.random(0, 0.0, 0.0).length);
        assertEquals(1, Commit.random(1, 0.0, 0.0).length);
        assertEquals(1, Commit.random(1, 1.0, 0.0).length);
        assertEquals(1, Commit.random(1, 0.0, 1.0).length);

        assertArrayEquals(new double[]{1.0}, Commit.random(1, 1.0, 1.0));
        double lower = 4;
        double upper = 6;
        double[] tc1 = Commit.random(5, lower, upper);
        double[] tc2 = Commit.random(5, lower, upper);
        double[] tc3 = Commit.random(5, lower, upper);
        double[] tc4 = Commit.random(5, lower, upper);
        double[] tc5 = Commit.random(5, lower, upper);
        assertFalse(Arrays.equals(tc1,tc2) && Arrays.equals(tc1, tc3) &&
                Arrays.equals(tc1, tc4) && Arrays.equals(tc1, tc5));
    }

    @Test
    public void random2() {
        double lower = 4;
        double upper = 6;
        double[] tc1 = Commit.random(5, lower, upper);
        for (double number: tc1) {
            assertTrue(number >= lower);
            assertTrue(number < upper);
        }
        assertEquals(5, tc1.length);
    }

    @Test
    public void max() {
        assertEquals(8, Commit.max(duplicateNumbers));
    }

    @Test
    public void min() {
        assertEquals(1, Commit.min(duplicateNumbers));
    }

    @Test
    public void maxNull() {
        assertEquals(Double.MIN_VALUE, Commit.max(new double[]{}));
    }

    @Test
    public void minNull() {
        assertEquals(Double.MAX_VALUE, Commit.min(new double[]{}));
    }

    @Test
    public void normalize() {
        assertArrayEquals(new double[]{}, Commit.normalize(new double[]{}));

        assertArrayEquals(new double[]{0}, Commit.normalize(new double[]{0}));
        assertArrayEquals(new double[]{0}, Commit.normalize(new double[]{0}));
        assertArrayEquals(new double[]{0, 0}, Commit.normalize(new double[]{0, 0}));
    }

    @Test
    public void normalizeNaN() {
        assertArrayEquals(new double[]{0.25, 0.25, 0.25, 0.25},
                Commit.normalize(new double[]{1000000000, 1000000000, 1000000000, 1000000000}),
                0.0001);
    }

    @Test
    public void normalizeWithRandom() {
        double lower = 4;
        double upper = 6;
        double[] tc1 = Commit.random(5, lower, upper);
        tc1 = Commit.normalize(tc1);
        for (double number: tc1) {
            assertTrue(number >= 0);
            assertTrue(number <= 1);
        }
        assertEquals(5, tc1.length);
    }

    @Test
    public void sum() {
        assertEquals(0, Commit.sum(new double[]{}));
        double sum = 0;
        for (double d: duplicateNumbers)
            sum += d;
        assertEquals(Commit.sum(duplicateNumbers), sum);
    }

    @Test
    public void stddev() {
        assertEquals(Double.NaN, Commit.stddev(new double[]{}));
        assertEquals(0, Commit.stddev(new double[]{0}));
        assertEquals(0.5, Commit.stddev(new double[]{0, 1}));
        assertEquals(Commit.stddev(duplicateNumbers) , 2.5766, 0.0001);
    }

    @Test
    public void arrayAdd() {
        assertArrayEquals(new double[]{}, Commit.arrayAdd(new double[]{}, new double[]{}));
        assertArrayEquals(new double[]{2, 2}, Commit.arrayAdd(new double[]{1, 1}, new double[]{1, 1}));
        assertArrayEquals(new double[]{1, 1}, Commit.arrayAdd(new double[]{0, 0}, new double[]{1, 1}));
        double lower = 4;
        double upper = 6;
        double[] tc1 = Commit.random(5, lower, upper);
        double[] tc2 = Commit.arrayAdd(tc1, duplicateNumbers);
        for (int i = 0; i < tc2.length; i++) {
            assertEquals(tc2[i], tc1[i] + duplicateNumbers[i]);
        }
        assertEquals(5, tc2.length);
    }

    @Test
    public void arrayNegate() {
        assertArrayEquals(new double[]{}, Commit.arrayNegate(new double[]{}));
        assertArrayEquals(new double[]{0}, Commit.arrayNegate(new double[]{0}));
        assertArrayEquals(new double[]{-1}, Commit.arrayNegate(new double[]{1}));
        double lower = 4;
        double upper = 6;
        double[] tc1 = Commit.random(5, lower, upper);
        double[] tc2 = Commit.arrayNegate(tc1);
        for (int i = 0; i < tc2.length; i++) {
            assertEquals(tc2[i], -tc1[i]);
        }
        assertEquals(5, tc2.length);
    }

    @Test
    public void arraySubtract() {
        assertArrayEquals(new double[]{}, Commit.arraySubtract(new double[]{}, new double[]{}));
        assertArrayEquals(new double[]{0, 0}, Commit.arraySubtract(new double[]{1, 1}, new double[]{1, 1}));
        assertArrayEquals(new double[]{-1, -1}, Commit.arraySubtract(new double[]{0, 0}, new double[]{1, 1}));
        double lower = 4;
        double upper = 6;
        double[] tc1 = Commit.random(5, lower, upper);
        double[] tc2 = Commit.arraySubtract(tc1, duplicateNumbers);
        for (int i = 0; i < tc2.length; i++) {
            assertEquals(tc2[i], tc1[i] - duplicateNumbers[i]);
        }
        assertEquals(5, tc2.length);
    }

    private static Stream<Arguments> distanceParameterProvider() {
        return Stream.of(
                Arguments.of(0, new double[]{}, new double[]{}),
                Arguments.of(0, new double[]{0, 1}, new double[]{0, 1}),

                Arguments.of(1, new double[]{1, 1}, new double[]{0, 1}),
                Arguments.of(1, new double[]{1, 1}, new double[]{1, 0})
        );
    }

    @ParameterizedTest(name = "{index} => expected={0} array1={1} array2={2}")
    @MethodSource("distanceParameterProvider")
    public void distance(Number expected, double[] array1, double[] array2){
        assertEquals(expected.doubleValue(), Commit.distance(array1, array2));
    }

    private static Stream<Arguments> arrayDeviationProvider() {
        return Stream.of(
                Arguments.of(null, new double[]{}),
                Arguments.of(new double[]{0, 0}, new double[]{1, 1}),
                Arguments.of(new double[]{0.5, 0.5}, new double[]{0, 1}),
                Arguments.of(new double[]{0.5, 0.5}, new double[]{1, 0})
        );
    }

    @ParameterizedTest(name = "{index} => expected={0} input1={1}")
    @MethodSource("arrayDeviationProvider")
    public void arrayDeviation(double[] expected, double[] input){
        assertArrayEquals(expected, Commit.arrayDeviation(input));
    }
}