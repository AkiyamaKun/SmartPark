package Core.Service;

import Core.DTO.AccountDTO;
import Core.DTO.ChangePasswordDTO;
import Core.DTO.InformationAccountDTO;
import Core.DTO.ResponseDTO;

import java.util.List;

/**
 * Account Service Interface
 *
 * Author: DangNHH - 19/02/2019
 */
public interface AccountService {

    ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO);

    ResponseDTO authorize(AccountDTO accountDTO);

    ResponseDTO getAccount(Integer id);

    ResponseDTO updateAccount(Integer id, AccountDTO accountDTO);

    ResponseDTO deleteAccount(Integer id);
    //ResponseDTO createAccount(Integer roleAccount, AccountDTO accountDTO);
    ResponseDTO getListAccount(Integer roleId);
    ResponseDTO registerAccount(AccountDTO accountDTO);
    ResponseDTO setFirstPassword(String email, String password);
}
