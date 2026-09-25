public class Main {
    public static void main(String[] args) {
        GerenciadorLog logCentral = new GerenciadorLog();

        System.out.println("--- Sistema de Logs Centralizado ---");
        System.out.println("Iniciando requisições simultâneas dos microsserviços...\n");

        Thread t1 = new Thread(() -> logCentral.registrarLog("Token gerado para o usuário admin."), "Módulo de Autenticação");
        Thread t2 = new Thread(() -> logCentral.registrarLog("Baixa no estoque para o pedido #7890."), "Módulo de Vendas");
        Thread t3 = new Thread(() -> logCentral.registrarLog("Cálculo de rota para a transportadora."), "Módulo de Frete");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Erro no monitoramento principal.");
        }
        
        System.out.println("\nProcessamento de logs finalizado.");
    }
}
