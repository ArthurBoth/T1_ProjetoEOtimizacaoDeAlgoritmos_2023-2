package Problem1;

public class Loan {
    public enum STATUS{
        ACTIVE, PAYED
    }

    private final int id; // identificador do empréstimo
    private final double rate; // taxa de juros do empréstimo
    public static final int BASE_LOAN = 1000; // valor base do empréstimo
    private STATUS status;

    public Loan(int id, double rate) { // construtor
        this.id = id; // identificador do empréstimo
        this.rate = rate; // taxa de juros do empréstimo
        this.status = STATUS.ACTIVE;
    }

    public int getId() { // getter do identificador do empréstimo
        return id; // retorna o valor do identificador do empréstimo
    }

    public double getRate() { // getter da taxa de juros do empréstimo
        return rate; // retorna o valor da taxa de juros do empréstimo
    }

    public double getLoanValue(int months) { // calcula o valor do empréstimo após 'months' meses
        return BASE_LOAN * Math.pow(rate, months); // retorna o valor do empréstimo de acordo com a fórmula do problema
        //Math.pow é O(1)
    }

    public STATUS getStatus() { // getter do status do empréstimo
        return status; // retorna o status do empréstimo
    }

    public void payLoan() { // muda o status do empréstimo para pago
        this.status = STATUS.PAYED; // muda o status do empréstimo para pago
    }
}
