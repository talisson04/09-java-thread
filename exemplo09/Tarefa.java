import java.util.concurrent.ThreadLocalRandom;

public class Tarefa implements Runnable {
    private PainelDePontos painel;
    private String nomeJogador;

    public Tarefa(PainelDePontos painel, String nomeJogador) {
        this.painel = painel;
        this.nomeJogador = nomeJogador;
    }

    @Override
    public void run() {
        while (!painel.jogoFinalizado()) {
            
            int dado = ThreadLocalRandom.current().nextInt(1, 7);
            
            painel.registrarPontos(nomeJogador, dado);
            
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Erro na thread do jogador.");
            }
        }
    }
}
