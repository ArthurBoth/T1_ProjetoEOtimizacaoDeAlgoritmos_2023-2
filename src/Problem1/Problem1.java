package Problem1;

import java.util.ArrayList;

/**
 * This class is a Java implementation of problem #1 from the algorithm's project and optimization class
 * 
 * Problem description:
 * Vocês estão abrindo uma empresa de modelos generativos e precisam de recursos para desenvolver 'n' diferentes modelos.
 * O membro do time que era (sim ele não faz mais parte do grupo) responsável por finanças, contratou 'n' empréstimos no valor de $ 1000 cada de vários bancos diferentes.
 * 
 * O valor dos empréstimos fica mais caro de acordo com o passar do tempo: em particular, o empréstimo 'j' aumenta por uma taxa de juros 'rj' > 1 em cada mês, onde 'rj' é um determinado parâmetro. Isso significa que se o empréstimo 'j' for pago daqui a 't' meses, vocês terão que devolver ao banco {1000 ∙ '(rj)^t'}.
 * 
 * Assumiremos que todas as taxas de juros são distintas; isto é, 'ri' != 'rj' para taxas 'i' != 'j'.
 * 
 * Problem:
 * Dado que a empresa só tem recursos para pagar um empréstimo por mês, em que ordem ela deve pagar os empréstimos para que o valor total gasto seja o menor possível?
 * 
 * Forneça um algoritmo que considere as 'n' taxas de juros de preços 'r1', 'r2', […] , 'rn', e calcule uma ordem de pagamento dos empréstimos para que o valor total gasto seja minimizado. O tempo de execução do seu algoritmo deve ser polinomial em 'n'.
 * 
 * Solution:
 * The solution for this problem is to pay the loan with the highest rate first. This way, the total amount spent will be the lowest possible.
 * 
 * Time complexity:
 * O(n)
 * 
 * @author ArthurBoth
 * @author felipefreitassilva
 * @author GabrielFerreira39
 * 
 * @see Loan
 */
public class Problem1 {
    /**
     * Number of loans
     * 
     * Defaults to 100
     */
    private final int AMOUNT_OF_LOANS = 100;
    /**
     * Total amount of loans
     */
    private double loanTotal = 0;
    /**
     * Array of active loans
     */
    private final ArrayList<Loan> LOANS = new ArrayList<Loan>(AMOUNT_OF_LOANS);
    /**
     * Months passed since the beginning of the simulation
     * 
     * Defaults to 1
     */
    private int monthsPassed = 1;

    /**
     * Constructor
     * Initializes the loans with different rates
     * 
     * @see Loan
     * @see generateLoans()
     * 
     * @complexity O(n) + O(n log n) = O(n log n)
     */
    public Problem1() {
        generateLoans(AMOUNT_OF_LOANS);
        orderLoans();
    }

    /**
     * Creates N loans with different rates
     * 
     * @param amount number of loans to be created
     * 
     * @see Loan
     * 
     * @complexity O(n)
     */
    private void generateLoans(int amount) {
        for (int i = 0; i < amount ; i++) {
            Loan loan = new Loan(i + 1, 1.001 + (i * 0.001));
            LOANS.add(loan);
        }
    }

    /**
     * Orders the loans by rate
     * 
     * @see Loan
     * 
     * @complexity O(n log n)
     */
    private void orderLoans() {
        LOANS.sort((loan1, loan2) -> {
            if (loan1.getRate() > loan2.getRate()) {
                return -1;
            } else if (loan1.getRate() < loan2.getRate()) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    /**
     * This method iterates, pays, and sums the total of the loans
     * Since the loans are ordered by rate, the first loan in the array will always be the one with the highest rate
     * 
     * @see Loan
     * 
     * @complexity O(n)
     */
    public double payLoans() {
        for (Loan loan : LOANS) {
            System.out.println("Mês " + monthsPassed + ":");
            System.out.println("Empréstimo " + loan.getId() + " pago.");
            double loanValue = loan.getLoanValue(monthsPassed);
            System.out.printf("Valor pago: R$%.4f\n", loanValue);
            System.out.println("=========================================");
            loanTotal += loanValue;
            loan.payLoan();
            monthsPassed++;
        }
        return loanTotal;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        double loanTotal = new Problem1().payLoans();
        long endTime = System.currentTimeMillis();
        System.out.println("Todos os empréstimos foram pagos.");
        System.out.printf("Valor total gasto: R$%.2f\n", loanTotal);
        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
        System.out.println("Complexidade = O(n log n) + O(n) = O(n log n)");
    }
}
