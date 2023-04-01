package LoginPages;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public Encryptor() {}

    public String encryptPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] messageDigest = m.digest(input.getBytes());
        BigInteger big = new BigInteger(1,messageDigest);
        return big.toString(16);
    }
}
