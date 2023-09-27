package Problem2;

/**
 * This class is a utility class for matrix multiplication
 * It contains two methods: one for the conventional method and another for the Strassen method
 * 
 * @see Matrix
 */
public abstract class MatrixMultiplication {
    /**
     * Multiplies this matrix by another matrix using the "conventional method"
     * 
     * @param mn First matrix
     * @param np Second matrix
     * @return Resulting matrix
     */
    public static Matrix conventionalMethod(Matrix mn, Matrix np) {
        if (mn.getColumns() != np.getRows()) {
            throw new IllegalArgumentException("The number of columns of the first matrix must be equal to the number of rows of the second matrix");
        }

        Matrix result = new Matrix(mn.getRows(), np.getColumns());

        for (int i = 0; i < mn.getRows(); i++)
            for (int j = 0; j < np.getColumns(); j++) {
                int sum = 0;
                for (int k = 0; k < mn.getColumns(); k++)
                    sum += mn.get(i, k) * np.get(k, j);
                result.set(i, j, sum);
            }

        return result;
    }
    
    /**
     * Multiplies this matrix by another matrix using the "Strassen method"
     * 
     * @param mn First matrix
     * @param np Second matrix
     * @return Resulting matrix
     */
    public static Matrix strassenMethod(Matrix mn, Matrix np) {
        if (mn.getColumns() != np.getRows())
            throw new IllegalArgumentException("The number of columns of the first matrix must be equal to the number of rows of the second matrix");

        if (!mn.isSquare() || !np.isSquare())
            throw new IllegalArgumentException("Both matrices must be square");

        if (!mn.isPowerOfTwo() || !np.isPowerOfTwo())
            throw new IllegalArgumentException("Both matrices must have a number of rows and columns that is a power of two");

        // Here, both matrices are square, from a power of 2 and have the same number of rows and columns
        int size = mn.getRows();

        // Base case: If the matrices are small enough, use the conventional method
        if (size <= 2)
            return conventionalMethod(mn, np);

        // Divide the matrices into four submatrices
        Matrix[][] submatricesMN = mn.split();
        Matrix[][] submatricesNP = np.split();

        // Calculate 10 intermediate matrices
        Matrix[] M = new Matrix[10];
        M[0] = strassenMethod(
            submatricesMN[0][0].add(submatricesMN[1][1]),
            submatricesNP[0][0].add(submatricesNP[1][1])
        );
        M[1] = strassenMethod(
            submatricesMN[1][0].add(submatricesMN[1][1]),
            submatricesNP[0][0]
        );
        M[2] = strassenMethod(
            submatricesMN[0][0],
            submatricesNP[0][1].subtract(submatricesNP[1][1])
        );
        M[3] = strassenMethod(
            submatricesMN[1][1],
            submatricesNP[1][0].subtract(submatricesNP[0][0])
        );
        M[4] = strassenMethod(
            submatricesMN[0][0].add(submatricesMN[0][1]),
            submatricesNP[1][1]
        );
        M[5] = strassenMethod(
            submatricesMN[1][0].subtract(submatricesMN[0][0]),
            submatricesNP[0][0].add(submatricesNP[0][1])
        );
        M[6] = strassenMethod(
            submatricesMN[0][1].subtract(submatricesMN[1][1]),
            submatricesNP[1][0].add(submatricesNP[1][1])
        );
        M[7] = strassenMethod(
            submatricesMN[0][0].subtract(submatricesMN[1][0]),
            submatricesNP[0][0].add(submatricesNP[0][1])
        );
        M[8] = strassenMethod(
            submatricesMN[0][1],
            submatricesNP[1][0].subtract(submatricesNP[0][0])
        );
        M[9] = strassenMethod(
            submatricesMN[1][0],
            submatricesNP[0][0].add(submatricesNP[0][1])
        );

        Matrix[] resultSubmatrices = new Matrix[4];
        resultSubmatrices[0] = M[0].add(M[3]).subtract(M[4]).add(M[6]);
        resultSubmatrices[1] = M[2].add(M[4]);
        resultSubmatrices[2] = M[1].add(M[3]);
        resultSubmatrices[3] = M[0].subtract(M[1]).add(M[2]).add(M[5]);

        // Join the submatrices into a single matrix
        Matrix result = Matrix.join(resultSubmatrices);

        return result;
    }
}
