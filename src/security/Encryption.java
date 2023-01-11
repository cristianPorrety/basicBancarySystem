package security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class Encryption {
    private static final Map<String, String> encryptionTable = new HashMap<>();

    private static final Random random = new SecureRandom();
    private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /* Method to generate the salt value. */

    public static String getSaltvalue(int length) {
        StringBuilder finalval = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            finalval.append(characters.charAt(random.nextInt(characters.length())));
        }
        return new String(finalval);
    }
    public static String encryption(String password){
        encryptionTable.put(password, getSaltvalue(30));
        return encryptionTable.get(password);
    }

    public static Map<String, String> getEncryptionTable() {
        return encryptionTable;
    }
}
