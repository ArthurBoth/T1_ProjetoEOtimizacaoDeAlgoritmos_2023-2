package Problem1;

/**
 * This class represents a loan
 * 
 * @authors ArthurBoth, felipefreitassilva, GabrielFerreira39
 */
public class Loan {
    /**
     * This enum represents the loan's status
     * 
     * ACTIVE: loan is active
     * PAYED: loan is payed
     */
    public enum STATUS {
        ACTIVE, PAYED
    }

    /**
     * Loan's unique identifier
     */
    private final int id;
    /**
     * Loan's rate
     * 
     * Defaults to 1.001
     */
    private final double rate;
    /**
     * Base loan value
     * 
     * Defaults to 1000
     */
    public static final int BASE_LOAN = 1000;
    /**
     * Loan's status
     * 
     * Defaults to ACTIVE
     */
    private STATUS status;

    /**
     * Class constructor
     * 
     * @param id   loan's unique identifier
     * @param rate loan's rate
     */
    public Loan(int id, double rate) {
        this.id = id;
        this.rate = rate;
        this.status = STATUS.ACTIVE;
    }

    /**
     * @return the loan's id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the loan's rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @return the loan's status
     */
    public STATUS getStatus() {
        return status;
    }

    /**
     * @param months the number of months
     * 
     * @return the loan's value after a number of months
     */
    public double getLoanValue(int months) {
        /** Math.pow is O(1) */
        return BASE_LOAN * Math.pow(rate, months);
    }

    /**
     * Method used to pay the loan
     * Changes the loan's status to PAYED
     */
    public void payLoan() {
        this.status = STATUS.PAYED;
    }
}
