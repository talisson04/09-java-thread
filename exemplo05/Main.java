public class Main {
    public static void main(String[] args) {
        ServidorVotacao servidor = new ServidorVotacao();

        System.out.println("--- Sistema de Votação Iniciado ---");
        System.out.println("Recebendo votos simultâneos das seções eleitorais...\n");

        Thread urna1 = new Thread(new UrnaEletronica(servidor, "Urna_Zona_Norte"));
        Thread urna2 = new Thread(new UrnaEletronica(servidor, "Urna_Zona_Sul"));
        Thread urna3 = new Thread(new UrnaEletronica(servidor, "Urna_Centro"));

        urna1.start();
        urna2.start();
        urna3.start();

        try {
            urna1.join();
            urna2.join();
            urna3.join();
        } catch (InterruptedException e) {
            System.out.println("Erro na apuração dos votos: " + e.getMessage());
        }
        System.out.println("\n--- RESULTADO DA APURAÇÃO ---");
        System.out.println("Total acumulado no servidor central: " + servidor.totalVotos + " votos.");
        
        if (servidor.totalVotos == 300) {
            System.out.println("Integridade validada: O total atingiu rigorosamente 300 votos!");
        } else {
            System.out.println("Atenção: Houve falha na contagem (Condição de Corrida)!");
        }
    }
}
