package Problem2;
/**
 * This class represents a matrix
 * 
 * @author ArthurBoth
 * @author felipefreitassilva
 * @author GabrielFerreirc9
 */

import java.util.Random;

public class Strassen {
    /**
     * The whole matrix
     */
    private int[][] matrix;
    /**
     * Random number generator
     */
    private Random random = new Random();
    /**
     * Lower bound for the random number generator (inclusive)
     * 
     * Defaults to 0
     */
    private static final int LOWER_BOUND = 0;
    /**
     * Upper bound for the random number generator (inclusive)
     * 
     * Defaults to 9
     */
    private static final int UPPER_BOUND = 9;

    /**
     * Class constructor
     * 
     * @param n matrix size
     */
    public Strassen(int n) {
        if (!isPowerOfTwo(n)) {
            System.out.println("A matriz deve ser quadrada e com lados de tamanho de uma potência de 2");
            System.out.println("O número fornecido não é uma potência de 2");
            System.out.println("Utilizando o valor pardão: de (2^3) = 8");
            this.matrix = new int[8][8];
        } else {
            this.matrix = new int[n][n];
        }
        fillMatrix();
    }

    public int[][] getMatrix() {
        return this.matrix;
    } 

    /**
     * Checks if a number is a power of two
     * @param n number to be checked
     * @return true if the number is a power of 2, false otherwise
     */
    private boolean isPowerOfTwo(int n) {
        /**
         * A number is a power of 2 if it's binary representation has exactly one '1'
         * 
         * This means a power of 2, 'x' will always be in the form '1000...000' and 'x-1' will be '0111...111'
         * 
         * Because of this, the bitwise AND operation should result in 0 if 'n' is a power of 2
         */
        return (n & (n - 1)) == 0;
    }

    /**
     * Fills the matrix with random numbers respecting the lower and upper bounds
     */
    private void fillMatrix() {
        for (int i = 0; i < this.matrix.length; i++)
            for (int j = 0; j < this.matrix.length; j++)
                /**
                 * Generates a random number between the Lower and upper bounds (inclusive) and saves it into the matrix
                 */
                this.matrix[i][j] = random.nextInt(LOWER_BOUND,UPPER_BOUND+1); // +1 because the upper bound is exclusive
    }

    /**
     * Multiplies the matrix by another matrix using the Strassen algorithm
     * @param otherMatrix the matrix to be multiplied by
     * @return the resulting matrix
     */
    public int[][] multiplyMatrix(int[][] otherMatrix) {
        return divideAndConquer(this.matrix, otherMatrix);
    }

    private static int[][] divideAndConquer(int[][] matrixA, int[][] matrixB) {
        /**
         * The size of the matrix, since it's a square matrix, the size is the length of the matrix
         */
        int size = matrixA.length;
        /**
         * The matrix resulting from the multiplication of matrixA and matrixB
         */
        int result[][] = new int[size][size];
        /**
         * The four matrices resulting from the division of matrixA
         */
        int[][] a, b, c, d;
        /**
         * The four matrices resulting from the division of matrixB
         */
        int[][] e, f, g, h;
        /**
         * The seven products of the matrices
         */
        int[][] p1, p2, p3, p4, p5, p6, p7;
        /**
         * The four matrices that are gonna compose the result matrix
         */
        int[][] r1, r2, r3, r4;

        /**
         * If the matrix is of size 1, then the result is the multiplication of the two matrices
         * 
         * This is the base case of the recursion
         */
        if (size == 1){
            result[0][0] = matrixA[0][0] * matrixB[0][0];
            return result;
        }

        /**
         * First we divide matrixA and matrixB into four matrices each
         */
        a = divideMatrix(matrixA, 0, 0);
        b = divideMatrix(matrixA, 0, size/2);
        c = divideMatrix(matrixA, size/2, 0);
        d = divideMatrix(matrixA, size/2, size/2);
        e = divideMatrix(matrixB, 0, 0);
        f = divideMatrix(matrixB, 0, size/2);
        g = divideMatrix(matrixB, size/2, 0);
        h = divideMatrix(matrixB, size/2, size/2);

        /**
         * Then we calculate the seven products
         */
        p1 = divideAndConquer(sumMatrix(a, d), sumMatrix(e, h));
        p2 = divideAndConquer(d, subtractMatrix(g, e));
        p3 = divideAndConquer(sumMatrix(a, b), h);
        p4 = divideAndConquer(subtractMatrix(b, d), sumMatrix(g, h));
        p5 = divideAndConquer(a, subtractMatrix(f, h));
        p6 = divideAndConquer(sumMatrix(c, d), e);
        p7 = divideAndConquer(subtractMatrix(a, c), sumMatrix(e, f));

        /**
         * Then we calculate the four matrices that are gonna compose the result matrix
         */
        r1 = sumMatrix(sumMatrix(p1, p2), subtractMatrix(p4, p3));
        r2 = sumMatrix(p5, p3);
        r3 = sumMatrix(p6, p2);
        r4 = subtractMatrix(sumMatrix(p5, p1), sumMatrix(p6, p7));

        /**
         * Finally we unite the four matrices into one and return the result
         */
        result = uniteMatrix(r1, r2, r3, r4);
        return result;
    }

    /**
     * Unites four matrices into one
     * @param x1 upper left matrix
     * @param x2 upper right matrix
     * @param x3 lower left matrix
     * @param x4 lower right matrix
     * @return the resulting matrix
     */
    private static int[][] uniteMatrix(int[][] x1, int [][] x2, int [][] x3, int [][] x4) {
        /**
         * The size of the matrix, since it's a square matrix, the size is the length of the matrix
         */
        int size = x1.length;
        /**
         * The result matrix, with four times the size of the original matrix
         */
        int[][] result = new int[size * 2][size * 2];

        /**
         * If the original matrices are of size 1, then we assemble the resulting matrix manually
         */
        if (size == 1) {
            result[0][0] = x1[0][0];
            result[0][1] = x2[0][0];
            result[1][0] = x3[0][0];
            result[1][1] = x4[0][0];
            return result;
        }
        /**
         * The current row and column of the resulting matrix
         */
        int row = 0, column = 0;

        /**
         * Iterates through all matrices and copies their values to the resulting matrix
         */
        for (int i = 0; i < size; i++){ // x1
            for (int j = 0; j < size; j++){
                result[row][column] = x1[i][j];
                column++;
            }
            row++;
            column = 0;
        }
        for (int i = 0; i < size; i++){ // x3
            for (int j = 0; j < size; j++){
                result[row][column] = x3[i][j];
                column++;
            }
            row++;
            column = 0;
        }

        row = 0;
        column = size;
        for (int i = 0; i < size; i++){ // x2
            for (int j = 0; j < size; j++){
                result[row][column] = x2[i][j];
                column++;
            }
            row++;
            column = size;
        }

        for (int i = 0; i < size; i++){ // x4
            for (int j = 0; j < size; j++){
                result[row][column] = x4[i][j];
                column++;
            }
            row++;
            column = size;
        }

        return result;
    }

    /**
     * Divides a matrix into four matrices
     * @param matrix the matrix to be divided
     * @param x the x coordinate of the upper left corner of the new matrix
     * @param y the y coordinate of the upper left corner of the new matrix
     * @return the resulting matrix
     */
    private static int[][] divideMatrix(int [][] matrix, int x, int y) {
        /**
         * The size of the matrix, since it's a square matrix, the size is the length of the matrix
         */
        int size = matrix.length;
        /**
         * The resulting matrix
         */
        int[][] result = new int[size/2][size/2];
        /**
         * The current row and column of the resulting matrix
         */
        int row = 0, column = 0;

        /**
         * Iterates through the matrix and copies the values to the resulting matrix
         */
        for (int i = x; i < x + size/2; i++){
            for (int j = y; j < y + size/2; j++){
                result[row][column] = matrix[i][j];
                column++;
            }
            row++;
            column = 0;
        }
        return result;
    }

    /**
     * Sums two matrices
     * @param x
     * @param y
     * @return the resulting matrix
     */
    private static int[][] sumMatrix(int[][] x, int[][] y) {
        /**
         * The size of the matrix, since it's a square matrix, the size is the length of the matrix
         */
        int result[][] = new int[x.length][x.length];   

        /**
         * Iterates through the matrix and adds the values of the two matrices
         */
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++){
                result[i][j] = x[i][j] + y[i][j];
            }
        }

        /**
         * Returns the resulting matrix
         */
        return result;
    }

    /**
     * Subtracts two matrices where 'x - y'
     * @param x
     * @param y
     * @return the resulting matrix
     */
    private static int[][] subtractMatrix(int[][] x, int[][] y) {
        /**
         * The size of the matrix, since it's a square matrix, the size is the length of the matrix
         */
        int result[][] = new int[x.length][x.length];

        /**
         * Iterates through the matrix and subtracts the values of the two matrices
         */
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++){
                result[i][j] = x[i][j] - y[i][j];
            }
        }

        /**
         * Returns the resulting matrix
         */
        return result;
    }
}