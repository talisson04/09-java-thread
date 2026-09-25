import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Cinema {
    private boolean[] assentosOccupados = new boolean[10];
    
    private final ReentrantLock mutex = new ReentrantLock();

    public void reservarAssento(String nomeThread, int numeroAssento) {
        System.out.println(nomeThread + " solicitou o assento " + numeroAssento + "...");

        try {
            if (mutex.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    System.out.println("[LOCK OBTIDO] " + nomeThread + " acessou o banco de dados.");
                    
                    if (!assentosOccupados[numeroAssento]) {
                        System.out.println(nomeThread + " encontrou o assento livre. Validando cartão de crédito...");
                        
                        assentosOccupados[numeroAssento] = true;
                        
                        Thread.sleep(4000); 
                        
                        System.out.println("[COMPRA CONCLUÍDA] " + nomeThread + " garantiu o assento " + numeroAssento + "!");
                    } else {
                        System.out.println("[INDISPONÍVEL] " + nomeThread + " percebeu que o assento já estava comprado.");
                    }
                } finally {
                    mutex.unlock();
                    System.out.println("[LIBERADO] " + nomeThread + " finalizou sua conexão.");
                }
            } else {
                System.out.println("[TIMEOUT] " + nomeThread + " -> Desistência por timeout: Assento muito disputado no momento!");
            }
        } catch (InterruptedException e) {
            System.out.println("Erro na operação de " + nomeThread + ": " + e.getMessage());
        }
    }
}
