package Core.Utils;

import java.security.SecureRandom;

public class TokenGenerator {
    public static SecureRandom random = new SecureRandom();

    public static synchronized String generateToken( String username ) {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return ( username + ":" + random );
    }
}
