
package uk.ac.dundee.computing.djb.instagrim.models;

import java.security.SecureRandom;
import java.util.Random;


public class Passwords {

    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEYLENGTH = 256;

    private Passwords() {
    }

    //returns random salt to be used for password hashing
    //@return 16 bytes random salt
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

}
