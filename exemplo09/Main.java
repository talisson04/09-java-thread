import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("Jogo de Dados Cooperativo");

        while (continuar) {
            System.out.println("\n=== INICIANDO NOVA PARTIDA ===");
            
            PainelDePontos painel = new PainelDePontos();
            
            Thread t1 = new Thread(new Tarefa(painel, "Jogador_A"));
            Thread t2 = new Thread(new Tarefa(painel, "Jogador_B"));
            Thread t3 = new Thread(new Tarefa(painel, "Jogador_C"));
            Thread t4 = new Thread(new Tarefa(painel, "Jogador_D"));

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                System.out.println("Erro na mesa de jogo.");
            }

            System.out.print("\nA partida acabou! Deseja jogar novamente? (s/n): ");
            String resposta = leitor.nextLine();
            
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }
        
        System.out.println("Mesa de jogo encerrada. Até a próxima!");
        leitor.close();
    }
}
