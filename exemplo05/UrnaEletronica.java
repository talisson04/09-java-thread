public class UrnaEletronica implements Runnable {
    private ServidorVotacao servidor;
    private String nomeUrna;

    public UrnaEletronica(ServidorVotacao servidor, String nomeUrna) {
        this.servidor = servidor;
        this.nomeUrna = nomeUrna;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            boolean votoComputado = false;

            while (!votoComputado) {

                if (servidor.mutex.tryLock()) {
                    try {

                        servidor.totalVotos = servidor.totalVotos + 1;
                        
                        votoComputado = true; 
                    } finally {
                        servidor.mutex.unlock();
                    }
                } 

            }
        }
        System.out.println("[URNA ENCERRADA] " + nomeUrna + " enviou seus 100 votos com sucesso.");
    }
}
