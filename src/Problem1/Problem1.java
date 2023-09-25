package Problem1;

public class Problem1 {

    /*
     Vocês estão abrindo uma empresa de modelos generativos e precisam de recursos para desenvolver 'n'
        diferentes modelos. O membro do time que era (sim ele não faz mais parte do grupo) responsável por
        finanças, contratou 'n' empréstimos no valor de $ 1000 cada de vários bancos diferentes.

     O valor dos empréstimos fica mais caro de acordo com o passar do tempo: em particular, o empréstimo 'j'
        aumenta por uma taxa de juros 'rj' > 1 em cada mês, onde 'rj' é um determinado parâmetro. Isso significa que
        se o empréstimo 'j' for pago daqui a 't' meses, vocês terão que devolver ao banco {1000 ∙ '(rj)^t'}.

     Assumiremos que todas as taxas de juros são distintas; isto é, 'ri' != 'rj' para taxas 'i' != 'j'.

     A questão é: dado que a empresa só tem recursos para pagar um empréstimo por mês, em que ordem ela
        deve pagar os empréstimos para que o valor total gasto seja o menor possível?
            R: O que possui a maior taxa de juros? Penso O(n) Entao implementei este.
            R: O que possuirá o maior valor no próximo mês? Penso O(n^2)
            Se presumirmos que todos os empréstimos foram feitos no mesmo mês, então o que possui a maior taxa de juros
            é o que possuirá o maior valor no próximo mês, pois as bases são iguais (1000).

     Forneça um algoritmo que considere as 'n' taxas de juros de preços 'r1', 'r2', […] , 'rn', e calcule uma ordem de
        pagamento dos empréstimos para que o valor total gasto seja minimizado. O tempo de execução do seu
        algoritmo deve ser polinomial em 'n'.
     */

    private final int n = 10; // número de empréstimos
    private Loan[] activeLoans = new Loan[n]; // vetor que armazena todos os empréstimos ativos
    private Loan[] payedLoans = new Loan[n]; // vetor que armazena todos os empréstimos pagos
    private int timePassed = 1; // tempo passado em meses,
                                // inicializa em 1 pois presume-se já ter passado um mês desde os empréstimos

    public Problem1() { // construtor
        generateLoans(); // gera os empréstimos
    }

    private void generateLoans() { // gera os empréstimos
        for (int i=0; i<n ; i++) { // cria n empréstimos com taxas de juros distintas
            activeLoans[i] = new Loan(i, i + 1.1); // '2.' para a soma ser de valores Double
            /* taxa de juros é relativa aos juros para garantir que todas as taxas sejam distintas
               e '+2' é para garantir que todas as taxas sejam >1 */
        }
    }

    private Loan getTargetLoan() { // procura o empréstimo com maior taxa de juros
        System.out.println("Procurando pelo empréstimo que será pago este mês...");
        Loan targetLoan = activeLoans[0]; // inicializa o empréstimo com maior taxa de juros com o primeiro empréstimo

        for (int i=1; i<activeLoans.length ; i++) { // percorre todos os empréstimos
            if ((activeLoans[i] != null) && (activeLoans[i].getRate() > targetLoan.getRate())) { 
                // se a taxa de juros do empréstimo atual for maior
                targetLoan = activeLoans[i]; // então, o empréstimo atual é o com maior taxa de juros
            }
        }
        return targetLoan; // retorna o empréstimo com maior taxa de juros

        /* sabe-se que, pelo jeito que os empréstimos foram gerados, a maior taxa sempre será a última,
           o que faria com que o código fosse O(1) com o código abaixo:
           private Loan getTargetLoan() {
            return activeLoans[n-1];
           }
           mas estamos considerando casos mais genéricos. */
    }

    private void payLoan(int id) { // paga um empréstimo de acordo com o id
        if ((activeLoans[id] == null) || (activeLoans[id].getStatus() == Loan.STATUS.PAYED)) {
            // se o empréstimo já foi pago
            System.out.println("Empréstimo [" + id + "] já foi pago."); // então, não faz nada
            return; // termina a execução do método antes de tentar pagar novamente
        }
        System.out.println("Pagando empréstimo [" + id + "] no valor de " + activeLoans[id].getLoanValue(timePassed));
        // imprime o empréstimo que está sendo pago
        payedLoans[id] = activeLoans[id]; // adiciona o empréstimo pago ao vetor de empréstimos pagos
        activeLoans[id] = null; // remove o empréstimo pago do vetor de empréstimos ativos
        payedLoans[id].payLoan(); // muda o status do empréstimo para pago
    }

    private void passTime() {
        System.out.println("Um mês se passou.\n");
        timePassed++; // passa o tempo em um mês
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void main(String[] args) {
        Problem1 problem1 = new Problem1(); // cria um objeto da classe Problem1
        Loan targetLoan; // cria um objeto da classe Loan para armazenar o empréstimo com maior taxa de juros

        while (problem1.activeLoans[0] != null) { // enquanto houver empréstimos ativos
            targetLoan = problem1.getTargetLoan(); // procura o empréstimo com maior taxa de juros
            problem1.payLoan(targetLoan.getId()); // paga o empréstimo com maior taxa de juros
            problem1.passTime(); // passa o tempo em um mês
        }
        System.out.println("Todos os empréstimos foram pagos.");
    }
}