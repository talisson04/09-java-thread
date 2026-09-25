import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Tarefa {
    private final ReentrantLock mutex = new ReentrantLock();

    public void realizarDivisao(String nome, long timeout, int num1, int num2) {
        System.out.println(nome + " tentando acessar a seção crítica (Timeout: " + timeout + "ms)...");

        try {
            if (mutex.tryLock(timeout, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("[ACESSO PERMITIDO] " + nome + " bloqueou o Mutex.");
                    
                    Thread.sleep(1000); 
                    
                    System.out.println(nome + " processando cálculo: " + num1 + " / " + num2);

                    int resultado = num1 / num2;
                    
                    System.out.println("[SUCESSO] " + nome + " obteve o resultado: " + resultado);

                } finally {

                    mutex.unlock();
                    System.out.println("[LIBERADO] " + nome + " destrancou o Mutex com sucesso.");
                }
            } else {
                System.out.println("[DESISTIU] " + nome + " não obteve o Mutex a tempo e foi embora.");
            }
        } catch (ArithmeticException e) {
            System.out.println("[ERRO CRÍTICO] " + nome + " tentou dividir por zero! Exceção: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("[ERRO THREAD] " + nome + " foi interrompida: " + e.getMessage());
        }
    }
}
