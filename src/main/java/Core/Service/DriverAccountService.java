package Core.Service;

import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;

public interface DriverAccountService {
    ResponseDTO verifyAccount(String email, String token);
    Account getDriver(Integer id);
    ResponseDTO forgetPasswordOfDriver(String email);
}
