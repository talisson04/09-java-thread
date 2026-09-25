import java.util.concurrent.locks.ReentrantLock;

public class ServidorVotacao {
    public int totalVotos = 0;
    public final ReentrantLock mutex = new ReentrantLock();
}
