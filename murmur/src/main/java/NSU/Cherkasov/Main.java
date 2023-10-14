package NSU.Cherkasov;

public class Main {
    public static int murmur3Hash(byte[] key, int seed) {
        int h1 = seed;
        final int c1 = 0xcc9e2d51;
        final int c2 = 0x1b873593;
        final int r1 = 15;
        final int r2 = 13;
        final int m = 5;
        final int n = 0xe6546b64;

        int len = key.length;
        int roundedEnd = (len & 0xfffffffc);
        int i = 0;
        while (i < roundedEnd) {
            int k1 = (key[i] & 0xff) | ((key[i + 1] & 0xff) << 8) | ((key[i + 2] & 0xff) << 16) | ((key[i + 3] & 0xff) << 24);
            i += 4;

            k1 *= c1;
            k1 = Integer.rotateLeft(k1, r1);
            k1 *= c2;

            h1 ^= k1;
            h1 = Integer.rotateLeft(h1, r2);
            h1 = h1 * m + n;
        }

        int k1 = 0;
        switch (len & 0x03) {
            case 3:
                k1 = (key[roundedEnd + 2] & 0xff) << 16;
            case 2:
                k1 |= (key[roundedEnd + 1] & 0xff) << 8;
            case 1:
                k1 |= (key[roundedEnd] & 0xff);
                k1 *= c1;
                k1 = Integer.rotateLeft(k1, r1);
                k1 *= c2;
                h1 ^= k1;
        }

        h1 ^= len;
        h1 = fmix(h1);

        return h1;
    }

    private static int fmix(int h) {
        h ^= h >>> 16;
        h *= 0x85ebca6b;
        h ^= h >>> 13;
        h *= 0xc2b2ae35;
        h ^= h >>> 16;
        return h;
    }

    public static void main(String[] args) {
        String key = "I'm sorry miss jackson";
        byte[] bytes = key.getBytes();
        int seed = 0;
        int hash = murmur3Hash(bytes, seed);
        System.out.println("MurmurHash3 hash value: " + Integer.toBinaryString(hash));
    }
}