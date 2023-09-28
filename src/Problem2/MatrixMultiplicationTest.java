//package Problem2;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MatrixMultiplicationTest {
//    @Test
//    public void testConventionalMethod() {
//        // Create matrices for testing
//        Matrix matrix1 = new Matrix(new int[][]{{1, 2}, {3, 4}});
//        Matrix matrix2 = new Matrix(new int[][]{{5, 6}, {7, 8}});
//
//        // Calculate the expected result using conventional method
//        Matrix expected = new Matrix(new int[][]{{19, 22}, {43, 50}});
//
//        // Test the conventional method
//        Matrix result = MatrixMultiplication.conventionalMethod(matrix1, matrix2);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testStrassenMethod() {
//        // Create matrices for testing
//        Matrix matrix1 = new Matrix(new int[][]{{1, 2}, {3, 4}});
//        Matrix matrix2 = new Matrix(new int[][]{{5, 6}, {7, 8}});
//
//        // Calculate the expected result using Strassen method
//        Matrix expected = new Matrix(new int[][]{{19, 22}, {43, 50}});
//
//        // Test the Strassen method
//        Matrix result = MatrixMultiplication.strassenMethod(matrix1, matrix2);
//        assertEquals(expected, result);
//    }
//}
