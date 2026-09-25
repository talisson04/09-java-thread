public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        System.out.println("--- App de Cinema: Lançamento de Ingressos ---");
        System.out.println("Três usuários tentando comprar o Assento 5 simultaneamente...\n");

        Thread t1 = new Thread(() -> cinema.reservarAssento("Usuario_A", 5));
        Thread t2 = new Thread(() -> cinema.reservarAssento("Usuario_B", 5));
        Thread t3 = new Thread(() -> cinema.reservarAssento("Usuario_C", 5));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Erro na thread principal.");
        }

        System.out.println("\nFim do processamento de vendas.");
    }
}
