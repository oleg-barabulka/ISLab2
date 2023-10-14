package NSU.Cherkasov;

import org.bouncycastle.crypto.engines.GOST3412_2015Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        byte[] keyBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);
        KeyParameter keyParameter = new KeyParameter(keyBytes);

        GOST3412_2015Engine engine = new GOST3412_2015Engine();

        byte[] input = "adbkdhgnbkdhtbfk".getBytes("UTF-8");
        byte[] output = new byte[input.length];
        engine.init(true, keyParameter); // true для шифрования, false для дешифрования
        engine.processBlock(input, 0, output, 0);
        System.out.println("Encrypted text: " + new String(output, "UTF-8"));

        byte[] decryptedOutput = new byte[output.length];
        engine.init(false, keyParameter);
        engine.processBlock(output, 0, decryptedOutput, 0);
        System.out.println("Decrypted text: " + new String(decryptedOutput, "UTF-8"));
    }
}