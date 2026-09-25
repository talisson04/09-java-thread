import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Tarefa tarefa = new Tarefa();
        boolean continuar = true;

        System.out.println("--- Teste de Concorrência: tryLock com Timeout ---");

        while (continuar) {
            // Pergunta o timeout desejado para o teste atual
            System.out.print("\nInforme o timeout (em milissegundos) para esta rodada (ex: 2000): ");
            long timeout = leitor.nextLong();

            System.out.println("\n--- Iniciando a corrida de 5 Threads ---");

            // Criando as 5 threads exigidas. 
            // Todas vão usar o mesmo timeout informado por você no teclado.
            Thread[] threads = new Thread[5];
            for (int i = 0; i < 5; i++) {
                String nomeThread = "Thread_" + (i + 1);
                threads[i] = new Thread(() -> tarefa.processarComTimeout(nomeThread, timeout));
            }

            // Disparando todas as threads praticamente ao mesmo tempo
            for (int i = 0; i < 5; i++) {
                threads[i].start();
            }

            // Usando o join() para segurar a execução do laço while principal 
            // até que todas as 5 threads tenham resolvido suas vidas (trabalhado ou desistido).
            for (int i = 0; i < 5; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    System.out.println("Erro no sincronismo do Main.");
                }
            }

            // Limpando o "Enter" que sobra após a leitura do número
            leitor.nextLine(); 

            System.out.print("\nDeseja executar o teste novamente? (s/n): ");
            String resposta = leitor.nextLine();
            
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }

        System.out.println("Monitoramento encerrado.");
        leitor.close();
    }
}
