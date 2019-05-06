package Core.Service;

import Core.DTO.*;

import java.util.List;

/**
 * Account Service Interface
 *
 * Author: DangNHH - 19/02/2019
 */
public interface AccountService {
    ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO);
    ResponseDTO checkLogin(UserLoginDTO dto);
    ResponseDTO authentication(AccountDTO accountDTO);
    ResponseDTO getAccount(Integer id);
    ResponseDTO updateAccount(Integer id, AccountDTO accountDTO);
    ResponseDTO deactiveAccount(Integer id);
    ResponseDTO activeAccount(Integer id);
    ResponseDTO getListAccount(Integer roleId);
    ResponseDTO registerAccount(AccountDTO accountDTO);
    ResponseDTO setFirstPassword(String email, String password);
    ResponseDTO forgetPassword(String email, Integer type);
    ResponseDTO totalAccount();
    ResponseDTO addCash(Integer accountId, Integer amountOfCash);
    ResponseDTO getAccountByEmail(String email);
    ResponseDTO logoutAndroid(Integer accountId);
}
