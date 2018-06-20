package cz.netcoop;

import java.util.Scanner;

public class Presenter extends AAppNetCoopObjectThread {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        super.run();

        String input;

        while (true) {
            System.out.print("Enter message type: ");
            input = scanner.nextLine();

            if (input.equals("ping")) {
                System.out.println("Ping send");
            }
        }
    }
}
