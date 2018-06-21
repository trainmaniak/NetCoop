package cz.netcoopold;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Connector {
    public static final int PORT_EXPLORE = 6470;

    private DatagramSocket socketTX;
    private DatagramSocket socketRX;

    private List<InetAddress> myNetAddresses = new ArrayList<InetAddress>();
    private Address myAddress;
    private Address broadcastAddress;

    public Address getMyAddress() {
        return myAddress;
    }

    public Address getBroadcastAddress() {
        return broadcastAddress;
    }

    public List<InetAddress> getMyNetAddresses() {
        return myNetAddresses;
    }

    public Connector() { // TODO prasacky napsany, refactor
        try {
            socketTX = new DatagramSocket();
            socketRX = new DatagramSocket(PORT_EXPLORE);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            broadcastAddress = new Address((byte)0x0, InetAddress.getByName("192.168.122.255"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

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

    public void send(Message message) throws IOException {
        byte[] buff = Message.Parser.build(message);

        DatagramPacket packet = new DatagramPacket(buff, buff.length, InetAddress.getByName("192.168.122.255"), PORT_EXPLORE);
        socketTX.send(packet);

        DebugPrinter.print("send done", message.toString());
    }

    public Message receive() throws IOException {
        byte[] buff = new byte[6];

        DatagramPacket packet = new DatagramPacket(buff, buff.length);
        DebugPrinter.print("receiving....", "");
        socketRX.receive(packet);
        asdf
        Message message = Message.Parser.parse(buff);
adf
        if (message.getDestination().getNcAddress() == myAddress.getNcAddress()) {
            DebugPrinter.print("receive done", message.toString() + " - ignore");
            return null;
        }

        DebugPrinter.print("receive done", message.toString());
        return message;
    }

    public void close(DatagramSocket socket) {
        socket.close();
    }
}
