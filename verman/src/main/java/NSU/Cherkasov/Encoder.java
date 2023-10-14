package NSU.Cherkasov;

import java.util.Random;

public class Encoder {
    private final int ALPHABET_POWER = 127;
    private String key;

    public String encode(String inputString) {
        Random random = new Random();
        StringBuilder encodeBuilder = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder(inputString.length());
        for (int i = 0; i < inputString.length(); i++) {
            char messageChar = inputString.charAt(i);
            char keyChar = (char) random.nextInt(ALPHABET_POWER);
            keyBuilder.append(keyChar);
            encodeBuilder.append((char) (messageChar ^ keyChar));
        }
        key = keyBuilder.toString();
        return encodeBuilder.toString();
    }

    public String decode(String encodeString) {
        StringBuilder decodeString = new StringBuilder();
        for (int i = 0; i < encodeString.length(); i++) {
            char encryptedChar = encodeString.charAt(i);
            char keyChar = key.charAt(i % key.length());
            char decodeChar = (char) (encryptedChar ^ keyChar);
            decodeString.append(decodeChar);
        }
        return decodeString.toString();
    }

}
