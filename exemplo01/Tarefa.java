import java.util.concurrent.locks.ReentrantLock;

public class Tarefa {
    // O "mutex" é o nosso cadeado. Só uma thread o pode ter de cada vez.
    private final ReentrantLock mutex = new ReentrantLock();
    
    // O nosso contador partilhado que será incrementado pelas threads.
    private int contador = 0; 

    // O método agora recebe o nome da thread e a quantidade que ela quer somar (incremento)
    public void processarSemTimeout(String nome, int incremento) {
        System.out.println(nome + " a tentar apanhar o cadeado (Mutex)...");

        // tryLock(): Tenta trancar. Se estiver livre, retorna true. Se estiver ocupado, retorna false na hora.
        if (mutex.tryLock()) {
            try {
                // --- ZONA CRÍTICA --- (Apenas a thread com a chave entra aqui)
                System.out.println(nome + " conseguiu trancar o Mutex e entrou na zona crítica!");
                
                // Incrementa o contador com o valor passado por parâmetro
                contador = contador + incremento;
                System.out.println("[CONTADOR] " + nome + " atualizou o valor para: " + contador);
                
                // Simula um trabalho demorado (1 segundo) para que as outras threads encontrem a porta trancada
                Thread.sleep(1000); 
                
            } catch (InterruptedException e) {
                System.out.println("Erro na thread " + nome + ": " + e.getMessage());
            } finally {
                // O unlock() tem de estar SEMPRE no 'finally' para garantir que a porta
                // é destrancada mesmo se ocorrer um erro na zona crítica.
                mutex.unlock(); 
                System.out.println(nome + " libertou o Mutex.");
            }
        } else {
            // Se o tryLock() retornar false, a thread cai imediatamente aqui sem ficar à espera.
            System.out.println(nome + " encontrou o cadeado ocupado e CANCELOU a operação!");
        }
    }
}
