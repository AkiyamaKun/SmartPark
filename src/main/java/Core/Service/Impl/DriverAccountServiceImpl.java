package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Repository.RoleRepository;
import Core.Service.DriverAccountService;
import Core.Service.PublicService;
import Core.Utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DriverAccountServiceImpl implements DriverAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PublicService publicService;

    @Autowired
    RoleRepository roleRepository;

    /**
     * Verify Account
     * @param email
     * @param token
     * @return
     */
    @Override
    public ResponseDTO verifyAccount(String email, String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            Account account = accountRepository.findByEmail(email);
            if(account != null && account.getToken().equals(token)){
                account.setActive(true);
                account.setToken(null);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.VERIFY_ACCOUNT_SUCCESS);
                responseDTO.setObjectResponse(account);
                accountRepository.save(account);
            }else{
                responseDTO.setStatus(false);
                responseDTO.setMessage(Const.VERIFY_ACCOUNT_FAIL);
                responseDTO.setObjectResponse(account);
            }
        }catch (Exception e){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.VERIFY_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO forgetPasswordOfDriver(String email){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByEmail(email);
            if(account != null){
                String token = Utilities.generateToken(email);
                //Type 4: forget password
                ResponseDTO tmp = publicService.sendEmail(email, token, 4);
                if(tmp != null){
                    if(tmp.isStatus()){
                        account.setToken(token);
                        accountRepository.save(account);
                        responseDTO.setStatus(true);
                        responseDTO.setMessage(Const.SEND_MESSAGE_SET_FIRST_PASSWORD);
                    }else{
                        responseDTO.setStatus(false);
                        responseDTO.setMessage(Const.SEND_EMAIL_SET_NEW_PASSWORD_FAIL);
                    }
                }

            }
        }catch (Exception e){
            responseDTO.setMessage("Error : " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public Account getDriver(Integer id) {
        Account account = accountRepository.findByAccountId(id);
        if(account != null){
            if(account.getRole().getRoleId() == 3 && account.isActive() == true){
                return account;
            }
        }
        return null;
    }
}
