package Core.Service;

import com.nimbusds.jwt.JWTClaimsSet;

import java.util.Date;

public interface JwtService {
    String generateTokenLogin(String email);
    JWTClaimsSet getClaimsFromToken(String token);
    Date generateExpirationDate();
    Date getExpirationDateFromToken(String token);
    String getEmailFromToken(String token);
    byte[] generateShareSecret();
    Boolean isTokenExpired(String token);
    Boolean validateTokenLogin(String token);
}
