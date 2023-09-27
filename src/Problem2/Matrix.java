package Problem2;

/**
 * This class represents a matrix and includes some useful methods
 * The matrix is represented as a 2D int array, for simplicity
 */
public class Matrix {
    /**
     * Number of rows of the matrix
     */
    private int rows;
    /**
     * Number of columns of the matrix
     */
    private int columns;
    /**
     * Matrix data (2D int array, for simplicity)
     */
    private int[][] data;

    /**
     * Creates a new matrix with the given number of rows and columns
     * 
     * @param rows
     * @param columns
     * 
     * @complexity O(1)
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    /**
     * Creates a new matrix with the given number of rows and columns and fills it
     * 
     * @param rows
     * @param columns
     * @param min Lower bound
     * @param max Upper bound
     * 
     * @complexity O(n²)
     */
    public Matrix(int rows, int columns, int min, int max) {
        this(rows, columns);
        this.randomize(min, max);
    }

    /**
     * Creates a new matrix with the given data
     * 
     * @return Resulting matrix
     * 
     * @complexity O(1)
     */
    public Matrix(int[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.data = data;
    }

    /**
     * Gets the number of rows of the matrix
     * 
     * @return Number of rows
     * 
     * @complexity O(1)
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns of the matrix
     * 
     * @return Number of columns
     * 
     * @complexity O(1)
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the value of the given position

     * @param row
     * @param col
     * 
     * @return Value of the given position
     * 
     * @complexity O(1)
     */
    public int get(int row, int col) {
        return data[row][col];
    }

    /**
     * Sets the value of the given position
     * 
     * @param row
     * @param col
     * @param value
     * 
     * @complexity O(1)
     */
    public void set(int row, int col, int value) {
        data[row][col] = value;
    }

    /**
     * Fills the matrix with random values
     * 
     * @param min Lower bound
     * @param max Upper bound
     * 
     * @complexity O(n²)
     */
    public void randomize(int min, int max) {
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                this.set(i, j, (int) (Math.random() * (max - min + 1) + min));
    }

    /**
     * Checks if the matrix is square
     * 
     * @return True if the matrix is square, false otherwise
     * 
     * @complexity O(1)
     */
    public boolean isSquare() {
        return this.rows == this.columns;
    }

    /**
     * Checks if the matrix a power of two as length
     * 
     * @return True if the matrix a power of two as length, false otherwise
     * 
     * @complexity O(1)
     */
    public boolean isPowerOfTwo() {
        return (this.rows & (this.rows - 1)) == 0;
    }

    /**
     * Adds this matrix to another matrix
     * 
     * @return Resulting matrix
     * 
     * @complexity O(n²)
     */
    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns)
            throw new IllegalArgumentException("Both matrices must have the same number of rows and columns");

        Matrix result = new Matrix(this.rows, this.columns);

        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                result.set(i, j, this.get(i, j) + other.get(i, j));

        return result;
    }

    /**
     * Subtracts another matrix from this matrix
     * 
     * @return Resulting matrix
     * 
     * @complexity O(n²)
     */
    public Matrix subtract(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns)
            throw new IllegalArgumentException("Both matrices must have the same number of rows and columns");

        Matrix result = new Matrix(this.rows, this.columns);

        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.columns; j++)
                result.set(i, j, this.get(i, j) - other.get(i, j));

        return result;
    }

    /**
     * Splits the matrix into four submatrices
     * 
     * @return A 2x2 matrix of submatrices
     * 
     * @complexity O(n²)
     */
    public Matrix[][] split() {
        if (!this.isSquare())
            throw new IllegalArgumentException("The matrix must be square");

        int size = this.rows;
        int halfSize = size / 2;

        Matrix[][] submatrices = new Matrix[2][2];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                submatrices[i][j] = new Matrix(halfSize, halfSize);

        for (int i = 0; i < halfSize; i++)
            for (int j = 0; j < halfSize; j++) {
                submatrices[0][0].set(i, j, this.get(i, j));
                submatrices[0][1].set(i, j, this.get(i, j + halfSize));
                submatrices[1][0].set(i, j, this.get(i + halfSize, j));
                submatrices[1][1].set(i, j, this.get(i + halfSize, j + halfSize));
            }

        return submatrices;
    }

    /**
     * Joins four submatrices into a single matrix
     * 
     * @param submatrices A length 4 array of submatrices
     * 
     * @return Resulting matrix
     * 
     * @complexity O(n²)
     */
    public static Matrix join(Matrix[] submatrices) {
        if (submatrices.length != 4)
            throw new IllegalArgumentException("The array must have exactly 4 elements");

        int size = submatrices[0].getRows();
        int resultSize = size * 2;

        Matrix result = new Matrix(resultSize, resultSize);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                result.set(i, j, submatrices[0].get(i, j));
                result.set(i, j + size, submatrices[1].get(i, j));
                result.set(i + size, j, submatrices[2].get(i, j));
                result.set(i + size, j + size, submatrices[3].get(i, j));
            }

        return result;
    }

    /**
     * Prints the matrix
     * 
     * @return String representation of the matrix
     * 
     * @complexity O(n²)
     */
    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < this.rows; i++) {
            result += "[";
            for (int j = 0; j < this.columns; j++) {
                result += this.get(i, j);
                if (j < this.columns - 1)
                    result += ", ";
            }
            result += "]\n";
        }

        return result;
    }
}
