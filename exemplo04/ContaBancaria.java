import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {
    private double saldo = 1000.0;
    private final ReentrantLock mutex = new ReentrantLock();

    public void sacar(String nomeThread, double valor) {
        System.out.println(nomeThread + " iniciou a tentativa de saque no valor de R$ " + valor);

        if (mutex.tryLock()) {
            try {
                System.out.println("[ACESSO GARANTIDO] " + nomeThread + " acessou o saldo.");
                
                Thread.sleep(2000); 

                if (saldo >= valor) {
                    saldo = saldo - valor;
                    System.out.println("[SUCESSO] " + nomeThread + " concluiu o saque. Novo saldo: R$ " + saldo);
                } else {
                    System.out.println("[NEGADO] " + nomeThread + " falhou por saldo insuficiente. Saldo atual: R$ " + saldo);
                }
            } catch (InterruptedException e) {
                System.out.println("Erro no processamento do saque: " + e.getMessage());
            } finally {
                mutex.unlock();
                System.out.println("[CONTA LIBERADA] " + nomeThread + " encerrou a operação.");
            }
        } else {
            System.out.println(nomeThread + " desistiu do saque porque a conta estava ocupada por outra operação concorrente.");
        }
    }
}
