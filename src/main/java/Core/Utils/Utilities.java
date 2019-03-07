package Core.Utils;

import Core.DTO.InformationAccountDTO;
import Core.Entity.Account;
import sun.java2d.pipe.hw.AccelDeviceEventListener;

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

    /**
     * Convert Information Account DTO from Account Entity
     * @param dto
     * @param account
     * @return
     */
    public static InformationAccountDTO convertInformationAccoutDTOFromAccount(InformationAccountDTO dto, Account account){
        if(account != null){
            dto.setAccountId(account.getAccountId());
            dto.setEmail(account.getEmail());
            dto.setFirstName(account.getFirstName());
            dto.setLastName(account.getLastName());
            dto.setPhoneNumber(account.getPhoneNumber());
            return  dto;
        }
        return null;
    }
}
