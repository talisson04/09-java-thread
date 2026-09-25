public class Main {
    public static void main(String[] args) {
        GeradorIdentificador geradorDb = new GeradorIdentificador();

        Thread t1 = new Thread(() -> geradorDb.obterProximoId("Thread 1"));
        Thread t2 = new Thread(() -> geradorDb.obterProximoId("Thread 2"));
        Thread t3 = new Thread(() -> geradorDb.obterProximoId("Thread 3"));
        Thread t4 = new Thread(() -> geradorDb.obterProximoId("Thread 4"));
        Thread t5 = new Thread(() -> geradorDb.obterProximoId("Thread 5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            System.out.println("Erro na execução principal.");
        }
        
        System.out.println("\nGeração de IDs finalizada. Integridade mantida!");
    }
}
