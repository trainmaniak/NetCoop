package cz.netcoop.connectors;

import cz.netcoop.*;
import cz.netcoop.devices.IDevice;
import cz.netcoop.exceptions.ConnectionException;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Connector {
    public static final int PORT_SERVE_ME = 6472;
    public static final int PORT_SERVE_OTHER = 6473;
    public static final int PORT_EXPLORE = 6470;

    private DatagramSocket socketUDPtx = null;
    private DatagramSocket socketUDPrx = null;

    private List<InetAddress> myNetAddresses = new ArrayList<InetAddress>();
    private Address myAddress;

    private List<Session> sessionList = new ArrayList<>();

    public Address getMyAddress() {
        return myAddress;
    }

    public List<InetAddress> getMyNetAddresses() {
        return myNetAddresses;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public Connector() {
        // get my ip
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    myNetAddresses.add(addr);
                    DebugPrinter.print("my IP", addr.getHostName() + " - " + addr.getHostAddress());
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } finally {
            int i = 0;
            for (InetAddress item : myNetAddresses) {
                System.out.println(i + " " + item.getHostName());
                i++;
            }
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());

            int ncAddr = new Random().nextInt(256);
            myAddress = new Address((byte)ncAddr, myNetAddresses.get(choice));

            DebugPrinter.print("my addr nc str", myAddress.getNcAddressString());
            DebugPrinter.print("my addr ip str", myAddress.getIpAddressString());
        }
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
            Message message = Message.parseMessage(rawMessage);

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
            String rawMessage = message.assemble();

            port.getWriter().write(rawMessage);
            port.getWriter().flush();

            DebugPrinter.print("Send msg", rawMessage);
        } catch (IOException e) {
            //connected = false;
            throw e;
        }
    }

    public void inform() throws IOException {
        DatagramSocket socket = new DatagramSocket(PORT_EXPLORE);
        byte[] buf = new byte[256];

        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        String received = new String(packet.getData(), 0, packet.getLength());
        DebugPrinter.print("Wait on new device", "ok");

        //Message message = Message.parse(deviceList, abilityList, received);

        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);

        DebugPrinter.print("Inform new device", "ok");
    }

    public void ask() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = new byte[256];

        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.122.255"), PORT_EXPLORE);
        socket.send(packet);

        DebugPrinter.print("Ask existing device", "ok");

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        String received = new String(packet.getData(), 0, packet.getLength());

        DebugPrinter.print("Reply from other device", "ok");
    }

    public void sendUDP(MeetingMessage message) throws IOException {
        if (socketUDPtx == null) {
            socketUDPtx = new DatagramSocket();
        }

        byte[] buff = MeetingMessage.Builder.build(message);

        DatagramPacket packet = new DatagramPacket(buff, buff.length, InetAddress.getByName("192.168.122.255"), PORT_EXPLORE);
        socketUDPtx.send(packet);

        DebugPrinter.print("send done", message.toString());
    }

    public MeetingMessage receiveUDP() throws IOException {
        if (socketUDPrx == null) {
            socketUDPrx = new DatagramSocket(PORT_EXPLORE);
        }

        byte[] buff = new byte[6];

        DatagramPacket packet = new DatagramPacket(buff, buff.length);
        DebugPrinter.print("receiving....", "");
        socketUDPrx.receive(packet);

        //String messageStr = new String(packet.getData(), 0, packet.getLength());
        //DebugPrinter.print("receive done", messageStr + " - sender: " + packet.getAddress().getHostName());

        MeetingMessage message = MeetingMessage.Builder.parse(buff);

        if (message.getAddress().getNcAddress() == myAddress.getNcAddress()) {
            DebugPrinter.print("receive done", message.toString() + " - ignore");
            return null;
        }

        DebugPrinter.print("receive done", message.toString());
        return message;
    }

    public void closeUDP(DatagramSocket socket) {
        socket.close();
    }
}