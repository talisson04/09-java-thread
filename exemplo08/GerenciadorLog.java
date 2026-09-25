import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GerenciadorLog {
    private final ReentrantLock mutex = new ReentrantLock();

    public void registrarLog(String mensagem) {
        String nomeModulo = Thread.currentThread().getName();

        try {
            if (mutex.tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("[INÍCIO LOG] " + nomeModulo + " registrando: " + mensagem);
                    
                    Thread.sleep(800);
                    
                    System.out.println("[FIM LOG] " + nomeModulo + " concluiu a gravação.");
                } finally {
                    mutex.unlock();
                }
            } else {
                System.out.println("[" + nomeModulo + "] Timeout de arquivo! Log descartado/redirecionado para o console local.");
            }
        } catch (InterruptedException e) {
            System.out.println("Erro na operação do " + nomeModulo + ": " + e.getMessage());
        }
    }
}
