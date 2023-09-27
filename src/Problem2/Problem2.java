package Problem2;

/**
 * This class is a Java implementation of problem #2 from the algorithm's project and optimization class
 * 
 * Problem description:
 * Multiplicar matrizes é um problema que aparenta ser relativamente simples, e é amplamente utilizado em diversas áreas além da computação. A solução convencional de multiplicar uma matriz m x n por outra matriz n x p era tida como, até 1969, impossível de resolver em um tempo menor que θ(n³), porém, inconformado com a afirmação, Volker Strassen propôs um algoritmo para provar que há um método mais eficiente para resolver este problema. 
 * 
 * Solution:
 * Strassen's Divide an conquer algorithm
 * 
 * @author ArthurBoth
 * @author felipefreitassilva
 * @author GabrielFerreira39
 * 
 * @complexity O(n^(log7))
 * 
 * @see Matrix
 * @see MatrixMultiplication
 */

public class Problem2 {
    public static void main(String[] args) {
        int[][] matrixMN = {
            { 2, 2 },
            { 2, 2 }
        };
        int[][] matrixNP = {
            { 3, 3 },
            { 3, 3 }
        };
        Matrix mn = new Matrix(8, 8, 0, 3);
        // Matrix mn = new Matrix(matrixMN);
        Matrix np = new Matrix(8, 8, 0, 3);
        // Matrix np = new Matrix(matrixNP);

        System.out.println("\n\nMatrix MN:");
        System.out.println(mn);
        
        System.out.println("\n\nMatrix NP:");
        System.out.println(np);
        
        System.out.println("\n\nConventional operation:");
        long startTime = System.nanoTime();
        Matrix conventionalOperation = MatrixMultiplication.conventionalMethod(mn, np);
        long endTime = System.nanoTime();
        System.out.printf("Time elapsed: %d ns\n", endTime - startTime);
        System.out.println("Complexity: O(n³)");
        System.out.println("Result: ");
        System.out.println(conventionalOperation);

        System.out.println("\n\nStrassen operation:");
        startTime = System.nanoTime();
        Matrix strassenOperation = MatrixMultiplication.strassenMethod(mn, np);
        endTime = System.nanoTime();
        System.out.printf("Time elapsed: %d ns\n", endTime - startTime);
        System.out.println("Complexity: O(n^~2.84)");
        System.out.println("Result: ");
        System.out.println(strassenOperation);
    }
}
