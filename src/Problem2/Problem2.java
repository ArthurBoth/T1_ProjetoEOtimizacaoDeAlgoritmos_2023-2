package Problem2;
/**
 * Até 1969 matemáticos acreditavam sem impossível resolver um problema de multiplicação de matrizes em tempo menor que Θ(n^3). Procure pelos trabalhos de V. Strassen e responda se isso ainda é verdade.
 *  
 * Solution:
 * Strassen's Divide an conquer algorithm
 * 
 * Time complexity:
 * O(n^(log7))
 * 
 * @author ArthurBoth
 * @author felipefreitassilva
 * @author GabrielFerreira39
 * 
 * @see Strassen
 */
public class Problem2 {
    /**
     * Makes two matrices and multiplies them
     */
    public Problem2(int n) {
        Strassen m1 = new Strassen(n);
        Strassen m2 = new Strassen(n);
        System.out.println("Matriz 1: ");
        printMatrix(m1.getMatrix());
        System.out.println("Matriz 2: ");
        printMatrix(m2.getMatrix());

        System.out.println("Multiplication: ");
        printMatix(m1.multiplyMatrix(m2.getMatrix()));
    }

    /**
     * Prints a matrix
     * @param x matrix to be printed
     */
    private void printMatix(int[][] x) {
        for (int[] i : x) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    private void printMatrix(int[][] x) {
        String s = "{";

        for (int[] i : x) {
            s += "{";
            for (int j : i) {
                s += j + ",";
            }
            s = s.substring(0, s.length() - 1);
            s += "},";
        }
            s = s.substring(0, s.length() - 1);
            s += "}";
        System.out.println(s);
    }

    public static void main(String[] args) {
        new Problem2(4);
    }
}
