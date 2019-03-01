package Core.Service.Impl;

import Core.Service.JwtService;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    public static final String EMAIL = "email";
    public static final String SECRET_KEY = "7183496";
    public static final int EXPIRE_TIME = 86400000; //24 hours

    /**
     * Generate Token Login
     * @param email
     * @return
     */
    @Override
    public String generateTokenLogin(String email) {
        String token = "";
        try{
            //Create HMAC signer
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(EMAIL, email);
            builder.expirationTime(generateExpirationDate());

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            // Apply the HMAC protection
            signedJWT.sign(signer);

            // Serialize to compact form, produces something like
            token = signedJWT.serialize();

        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public JWTClaimsSet getClaimsFromToken(String token) {
        return null;
    }

    /**
     * Generate Expiration Date
     * @return
     */
    @Override
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRE_TIME);
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        JWTClaimsSet claims = getClaimsFromToken(token);
        expiration = claims.getExpirationTime();
        return expiration;
    }

    @Override
    public String getEmailFromToken(String token) {
        String email = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            email = claims.getStringClaim(EMAIL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    /**
     * Convert Secret Key to 32-byte
     * @return
     */
    @Override
    public byte[] generateShareSecret() {
        // Generate 256-bit (32-byte) shared secret
        byte[] sharedSecret = new byte[32];
        sharedSecret = SECRET_KEY.getBytes();
        return sharedSecret;
    }

    @Override
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Validate Token
     * @param token
     * @return
     */
    @Override
    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String email = getEmailFromToken(token);

        if (email == null || email.isEmpty()) {
            return false;
        }
        if (isTokenExpired(token)) {
            return false;
        }
        return true;
    }
}
