import java.util.concurrent.locks.ReentrantLock;

public class GeradorIdentificador {
    private int proximoId = 1;
    private final ReentrantLock mutex = new ReentrantLock();

    public void obterProximoId(String nomeThread) {
        boolean idGerado = false;

        while (!idGerado) {
            
            if (mutex.tryLock()) {
                try {
                    int idCapturado = proximoId;
                    proximoId++;
                    
                    System.out.println("[SUCESSO] " + nomeThread + " obteve o ID exclusivo: " + idCapturado);
                    
                    idGerado = true;
                    Thread.sleep(50);
                    
                } catch (InterruptedException e) {
                    System.out.println("Erro na " + nomeThread + ": " + e.getMessage());
                } finally {
                    mutex.unlock();
                }
            } else {
                System.out.println(nomeThread + " -> Falha ao gerar ID: barramento de memória ocupado. Tentando novamente...");
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                
                }
            }
        }
    }
}
