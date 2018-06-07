package cz.netcoop;

import java.io.*;
import java.net.Socket;

public class Port {
    private BufferedReader reader;
    private BufferedWriter writer;

    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public Port(Socket newSocket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(newSocket.getOutputStream()));
        System.out.println("Server connecter to " + newSocket.getLocalSocketAddress() + ":" + newSocket.getPort());
    }

    /**
     * Create new connection with BufferedReader and BufferedWriter
     * @param ipAddress IP address to create connection
     * @param portNum port number for connection
     * @throws IOException connection failed
     */
    public Port(String ipAddress, int portNum) throws IOException {
        Socket newSocket = new Socket(ipAddress, portNum);
        reader = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(newSocket.getOutputStream()));
        System.out.println("Client connected to " + newSocket.getLocalSocketAddress() + ":" + portNum);
    }
}
