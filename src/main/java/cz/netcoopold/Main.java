package cz.netcoopold;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AppNetCoop app = AppNetCoop.getApp();
        app.start();
    }
}

