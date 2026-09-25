import java.util.concurrent.locks.ReentrantLock;

public class PainelDePontos {
    private int pontuacaoTotal = 0;
    private final ReentrantLock mutex = new ReentrantLock();

    public void registrarPontos(String nomeJogador, int dado) {
        mutex.lock();
        try {
            if (pontuacaoTotal < 100) {
                
                pontuacaoTotal += dado;
                System.out.println("[JOGADA] " + nomeJogador + " somou " + dado + ". Total do grupo: " + pontuacaoTotal + "/100");
                
                if (pontuacaoTotal >= 100) {
                    System.out.println("[VITÓRIA] " + nomeJogador + " fez o grupo atingir o objetivo!");
                }
                
            } else {
                System.out.println("[FIM DE JOGO] " + nomeJogador + " tentou jogar, mas a pontuação máxima já foi atingida.");
            }
        } finally {
            mutex.unlock();
        }
    }

    public boolean jogoFinalizado() {
        return pontuacaoTotal >= 100;
    }
}
