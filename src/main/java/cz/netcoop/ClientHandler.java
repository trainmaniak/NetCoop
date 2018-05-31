package cz.netcoop;

public class ClientHandler extends Thread {

    public Session session;

    public ClientHandler(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        // TODO prijmout message a pres router odeslat
    }
}
