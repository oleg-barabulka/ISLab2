package NSU.Cherkasov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        System.out.println("Введите строку для кодирования:");
        Scanner scanner = new Scanner(System.in);
        String encodeString = encoder.encode(scanner.nextLine());
        System.out.println("Кодовая строка: " + encodeString);
        String decodeString = encoder.decode(encodeString);
        System.out.println("Раскодирвоанная строка: " + decodeString);
    }
}