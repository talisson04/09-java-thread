import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Tarefa tarefa = new Tarefa();
        boolean continuar = true;

        System.out.println("--- Sistema de Concorrência com tryLock() ---");

        // O laço while permite que o utilizador execute o código as vezes que quiser
        while (continuar) {
            System.out.println("\n--- A iniciar uma nova corrida ---");

            // Criar 4 threads. Cada uma passa o seu nome e o valor que quer somar ao contador.
            Thread t1 = new Thread(() -> tarefa.processarSemTimeout("Thread_A", 10));
            Thread t2 = new Thread(() -> tarefa.processarSemTimeout("Thread_B", 20));
            Thread t3 = new Thread(() -> tarefa.processarSemTimeout("Thread_C", 30));
            Thread t4 = new Thread(() -> tarefa.processarSemTimeout("Thread_D", 40));

            // Disparar todas ao mesmo tempo. 
            // A primeira que chegar ao tryLock() ganha o cadeado. As outras 3 desistem.
            t1.start();
            t2.start();
            t3.start();
            t4.start();

            // Usamos o join() para obrigar a Thread Principal (Main) a esperar que as 4 threads
            // terminem o que estão a fazer antes de perguntar ao utilizador se quer continuar.
            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                System.out.println("Erro ao aguardar as threads.");
            }

            // Pergunta ao utilizador se quer repetir a corrida
            System.out.print("\nDeseja executar o algoritmo novamente? (s/n): ");
            String resposta = leitor.nextLine();
            
            // Se a resposta for "n" ou "N", mudamos a variável para false e o ciclo termina.
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }
        
        System.out.println("Programa encerrado com sucesso.");
        leitor.close();
    }
}
