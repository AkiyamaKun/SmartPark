package Core.Utils;

import java.security.SecureRandom;

public class Utilities {
    /**
     * Generate token
     * @param username
     * @return
     */
    public static synchronized String generateToken( String username ) {
        SecureRandom random = new SecureRandom();
        long longToken = Math.abs(random.nextLong());
        String strRandom = Long.toString( longToken, 16 );
        return ( username + ":" + strRandom );
    }
}
