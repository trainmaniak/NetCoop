package cz.netcoop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IAppNetCoop app;

        System.out.println("Do you want ot start server or client?");

        if (scanner.nextLine().equals("s")) {
            app = new AppNetCoopServer();
        } else {
            System.out.println("Enter server ip address");
            app = new AppNetCoopClient(scanner.nextLine());
        }

        app.start();
    }
}

