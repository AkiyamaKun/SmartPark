package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Service.DriverAccountService;
import Core.Service.PublicService;
import Core.Utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DriverAccountServiceImpl implements DriverAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PublicService publicService;
    /**
     * Create Driver Account
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO createDriverAccount(AccountDTO accountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            Account account = accountRepository.findByEmail(accountDTO.getEmail());
            if(account == null){
                String token = TokenGenerator.generateToken(accountDTO.getEmail());
                Date createDate = new Date();
                account = new Account(accountDTO.getEmail(), accountDTO.getPassword(), accountDTO.getPhoneNumber(), accountDTO.getFirstName(),
                        accountDTO.getLastName(), createDate, 2, false, token);
                ResponseDTO tmp = publicService.sendEmail(account.getEmail(), account.getToken());
                if(tmp.isStatus()){
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                    responseDTO.setObjectResponse(account);
                    accountRepository.save(account);
                }else{
                    responseDTO.setStatus(false);
                    responseDTO.setMessage(Const.SEND_EMAIL_CREATE_ACCOUNT_ERROR);
                }
            }else{
                responseDTO.setStatus(false);
                responseDTO.setMessage(Const.DRIVER_ACCOUNT_EXISTED);
                responseDTO.setObjectResponse(account);
            }
        }catch (Exception e){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.CREATE_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

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
    public ResponseDTO getAllDrivers() {
        return null;
    }
}
