package utils;

import java.util.Scanner;

public class ConsoleHelper {
    public static String lerString(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static int lerInt(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }
}
