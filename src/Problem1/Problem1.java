package Problem1;

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
     * Array of active loans (not payed)
     */
    private final Loan[] ACTIVE_LOANS = new Loan[AMOUNT_OF_LOANS];
    /**
     * Array of payed loans (already payed)
     */
    private final Loan[] PAYED_LOANS = new Loan[AMOUNT_OF_LOANS];
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
     */
    public Problem1() {
        generateLoans(AMOUNT_OF_LOANS);
    }

    /**
     * Creates N loans with different rates
     * 
     * @param amount number of loans to be created
     */
    private void generateLoans(int amount) {
        for (int i = 0; i < amount ; i++)
            ACTIVE_LOANS[i] = new Loan(i, 1.001 + (i * 0.001));
    }

    
    /**
     * Gets the loan with the highest rate
     * 
     * @return the loan with the highest rate
     * 
     * @see Loan
     */
    private Loan getHighestRateLoan() {
        System.out.println("Procurando pelo empréstimo que será pago este mês...");
        
        Loan highestRateLoan = ACTIVE_LOANS[0];
        for (int i = 1; i < ACTIVE_LOANS.length; i++)
            /**
             * If there is a current loan and its rate is higher than the highest loan's rate, then the current loan is the new target loan
             */
            if ((ACTIVE_LOANS[i] != null) && (ACTIVE_LOANS[i].getRate() > highestRateLoan.getRate()))
                highestRateLoan = ACTIVE_LOANS[i];
        return highestRateLoan;

        /**
         * Sabe-se que, pelo jeito que os empréstimos foram gerados, a maior taxa sempre será a última, o que faria com que o código fosse O(1) com o código abaixo:
         * private Loan getHighestRateLoan() {
         *  return ACTIVE_LOANS[n-1];
         * }
         * Mas estamos considerando casos mais genéricos. 
        */
    }

    /**
     * Pays a loan
     * 
     * @param id loan's unique identifier
     * 
     * @see Loan
     */
    private void payLoan(int id) {
        /**
         * If the loan has already been payed, then do nothing
         */
        if ((ACTIVE_LOANS[id] == null) || (ACTIVE_LOANS[id].getStatus() == Loan.STATUS.PAYED)) {
            System.out.printf("Empréstimo [%d] já foi pago.\n", id);
            return;
        }

        System.out.printf("Pagando empréstimo [%d] no valor de %.2f\n", id, ACTIVE_LOANS[id].getLoanValue(monthsPassed));
        /**
         * Inserts the payed loan into the payed loans array
         */
        PAYED_LOANS[id] = ACTIVE_LOANS[id];
        /**
         * Removes the payed loan from the active loans array
         */
        ACTIVE_LOANS[id] = null;
        /**
         * Pays the loan (changes its status to PAYED)
         */
        PAYED_LOANS[id].payLoan();
    }

    /**
     * Ends a month
     * Increments the months passed counter
     */
    private void endMonth() {
        monthsPassed++;
        System.out.printf("Um mês se passou. Agora estamos no mês %d\n", monthsPassed);
    }

    public static void main(String[] args) {
        long antes;
        long depois;
        Problem1 problem1 = new Problem1();
        Loan highestRateLoan;
        antes = System.currentTimeMillis();

        /**
         * While there are active loans, pay the loan with the highest rate and end the month
         */
        while (problem1.ACTIVE_LOANS[0] != null) {
            highestRateLoan = problem1.getHighestRateLoan();
            problem1.payLoan(highestRateLoan.getId());
            problem1.endMonth();
        }
        depois = System.currentTimeMillis();

        System.out.println("Todos os empréstimos foram pagos.");
        System.out.println(depois - antes + " Milliseconds");
    }
}
