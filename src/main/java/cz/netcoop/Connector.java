package cz.netcoop;

import cz.netcoop.Abilities.IAbility;
import cz.netcoop.Exceptions.ConnectionException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connector {
    public static final int PORT_SERVE_ME = 6472;
    public static final int PORT_SERVE_OTHER = 6473;

    //private boolean connected = false;

    private List<IDevice> deviceList;
    private List<IAbility> abilityList;

    private List<Session> sessionList = new ArrayList<>();

    public List<Session> getSessionList() {
        return sessionList;
    }

    public Connector(List<IDevice> deviceList, List<IAbility> abilityList) {
        this.deviceList = deviceList;
        this.abilityList = abilityList;
    }

    public Session getSession(IDevice device) {
        for (Session session : sessionList) {
            if (session.getDevice() == device) {
                return session;
            }
        }

        return null;
    }

    public boolean listen() {
        try {
            ServerSocket pendingOther = new ServerSocket(PORT_SERVE_OTHER);
            ServerSocket pendingMe = new ServerSocket(PORT_SERVE_ME);

            Socket newOther = pendingOther.accept();
            Socket newMe = pendingMe.accept();

            if (!newOther.getInetAddress().equals(newMe.getInetAddress())) {
                throw new ConnectionException("Connection failed. Other IP is another than Me IP!");
            }

            return true;
        } catch (ConnectionException | IOException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * Renew sessions if session with current ip already exists,
     * in other case create new one, and add to sessionList
     * @return true if create sessions was successful, false otherwise
     */
    public boolean connect(String ipAddress) {
        try {
            // renew
            boolean alreadyExists = false;
            for (int i = 0; i < sessionList.size(); i++) {
                if (sessionList.get(i).getIpAddress().equals(ipAddress)) {
                    Session newSession = new Session(ipAddress, PORT_SERVE_OTHER, PORT_SERVE_ME);
                    sessionList.set(i, newSession);
                    alreadyExists = true;

                    DebugPrinter.print("Reconnected", newSession.getIpAddress());
                }
            }

            // add new session
            if (!alreadyExists) {
                Session newSession = new Session(ipAddress, PORT_SERVE_OTHER, PORT_SERVE_ME);
                sessionList.add(newSession);

                DebugPrinter.print("Connected", ipAddress);
            }

            //connected = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();

            //connected = false;
            return false;
        }
    }

    /**
     * Wait for data from server
     * @param port port
     * @throws IOException receive failed
     */
    public Message receive(Port port) throws IOException, ConnectionException {

        // TODO reconnect

        /*
        if (!connected && !connect(session.getIpAddress())) {
            throw new ConnectionException("Reconnect failed");
        }
        */

        try {
            while (!port.getReader().ready()) {
                Thread.sleep(10);
            }

            String rawMessage = port.getReader().readLine();
            Message message = new Message().parseMessage(deviceList, abilityList, rawMessage);

            DebugPrinter.print("Received msg", rawMessage);
            return message;
        } catch (IOException e) {
            //connected = false;
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Send data to server
     * @param port port
     * @throws IOException send failed
     */
    public void send(Port port, Message message) throws IOException, ConnectionException {

        // TODO reconnect

        /*
        if (!connected && !connect(session.getIpAddress())) {
            throw new ConnectionException("Reconnect failed");
        }
        */

        try {
            String rawMessage = message.createMessage();

            port.getWriter().write(rawMessage);
            port.getWriter().flush();

            DebugPrinter.print("Send msg", rawMessage);
        } catch (IOException e) {
            //connected = false;
            throw e;
        }
    }
}
