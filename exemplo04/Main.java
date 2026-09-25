public class Main {
    public static void main(String[] args) {
        ContaBancaria contaConjunta = new ContaBancaria();

        System.out.println("--- Sistema Bancário - Teste de Concorrência ---");

        Thread t1 = new Thread(() -> contaConjunta.sacar("Thread_CaixaEletronico", 200.0));
        Thread t2 = new Thread(() -> contaConjunta.sacar("Thread_AppCelular", 300.0));
        Thread t3 = new Thread(() -> contaConjunta.sacar("Thread_PixAutomatico", 150.0));

        t1.start();
        t2.start();
        t3.start();
    }
}
