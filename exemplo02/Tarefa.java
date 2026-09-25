import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadLocalRandom; // Ferramenta para gerar números aleatórios

public class Tarefa {
    private final ReentrantLock mutex = new ReentrantLock();

    // O método agora recebe o nome da thread e o tempo máximo (timeout) que ela topa esperar
    public void processarComTimeout(String nome, long tempoMaximoEspera) {
        System.out.println(nome + " chegou e aceita esperar até " + tempoMaximoEspera + "ms pelo cadeado.");

        try {
            // tryLock com parâmetros: Tenta pegar o cadeado, mas espera até o tempo limite.
            // Se conseguir dentro do tempo, retorna true. Se o tempo estourar, retorna false.
            if (mutex.tryLock(tempoMaximoEspera, TimeUnit.MILLISECONDS)) {
                try {
                    // --- SEÇÃO CRÍTICA ---
                    System.out.println("[ACESSO PERMITIDO] " + nome + " conseguiu trancar o Mutex!");
                    
                    // Sorteia um tempo de trabalho aleatório entre 1000ms (1s) e 4000ms (4s)
                    int tempoSimulacao = ThreadLocalRandom.current().nextInt(1000, 4001);
                    System.out.println(nome + " vai trabalhar na seção crítica por " + tempoSimulacao + "ms.");
                    
                    // Simula a thread trabalhando por esse tempo aleatório
                    Thread.sleep(tempoSimulacao);
                    
                } finally {
                    mutex.unlock();
                    System.out.println("[LIBERADO] " + nome + " terminou o trabalho e destrancou o Mutex.");
                }
            } else {
                // Se o tempo configurado estourar e a thread não conseguir a chave, ela cai aqui.
                System.out.println("[DESISTIU] " + nome + " cansou de esperar os " + tempoMaximoEspera + "ms e foi embora!");
            }
        } catch (InterruptedException e) {
            System.out.println("Erro na thread " + nome + ": " + e.getMessage());
        }
    }
}
