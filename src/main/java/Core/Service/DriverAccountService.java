package Core.Service;

import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;

public interface DriverAccountService {
    //ResponseDTO createDriverAccount (AccountDTO accountDTO);
    ResponseDTO verifyAccount(String email, String token);
    ResponseDTO getAllDrivers();
}
