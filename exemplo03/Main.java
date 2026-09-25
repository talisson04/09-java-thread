import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Tarefa tarefa = new Tarefa();
        boolean continuar = true;

        System.out.println("--- Teste de try/finally com tryLock e Exceções ---");

        while (continuar) {
            System.out.print("\nInforme o tempo máximo de espera (timeout em ms) para as threads (ex: 3000): ");
            long timeout = leitor.nextLong();

            System.out.println("\n--- Iniciando disputa entre 3 Threads ---");

            Thread t1 = new Thread(() -> tarefa.realizarDivisao("Thread_A", timeout, 10, 2));
            
            Thread t2 = new Thread(() -> tarefa.realizarDivisao("Thread_B", timeout, 50, 0));

            Thread t3 = new Thread(() -> tarefa.realizarDivisao("Thread_C", timeout, 30, 3));

            t1.start();
            t2.start();
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                System.out.println("Erro na execução principal.");
            }

            leitor.nextLine(); 
            System.out.print("\nDeseja realizar um novo teste? (s/n): ");
            String resposta = leitor.nextLine();
            
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }
        
        System.out.println("Programa encerrado com sucesso.");
        leitor.close();
    }
}
