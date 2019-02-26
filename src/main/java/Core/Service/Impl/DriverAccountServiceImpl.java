package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.Role;
import Core.Repository.AccountRepository;
import Core.Repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;
    /**
     * Create Driver Account
     * @param accountDTO
     * @return
     */
    @Override
    public ResponseDTO createDriverAccount(AccountDTO accountDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        responseDTO.setMessage("Nothing");
        try{
            Account account = accountRepository.findByEmail(accountDTO.getEmail());
            String token = TokenGenerator.generateToken(accountDTO.getEmail());
            Date createDate = new Date();
            Role role = roleRepository.findByRoleId(3);
            if(account == null ){
                ResponseDTO tmp = publicService.sendEmail(accountDTO.getEmail(), token);
                if(tmp != null){
                    if(tmp.isStatus()){
                        //Create New Account
                        account = new Account(accountDTO.getEmail(), accountDTO.getPassword(), accountDTO.getPhoneNumber(), accountDTO.getFirstName(),
                                accountDTO.getLastName(), createDate, role, false, token);
                        responseDTO.setStatus(true);
                        responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                        responseDTO.setObjectResponse(account);
                        accountRepository.save(account);
                    }else{
                        responseDTO.setStatus(false);
                        responseDTO.setMessage(Const.SEND_EMAIL_CREATE_ACCOUNT_ERROR);
                    }
                }
            }else{
                String lastToken = account.getToken();
                if(lastToken == null){
                    //Account is created ->response fail
                    responseDTO.setStatus(false);
                    responseDTO.setMessage(Const.DRIVER_ACCOUNT_EXISTED);
                }else{
                    //Account had create but it is not verify -> Excute update and send again Mail Verify
                    ResponseDTO tmp = publicService.sendEmail(account.getEmail(), token);
                    if(tmp != null){
                        if(tmp.isStatus()){
                            account.setToken(token);
                            account.setPassword(accountDTO.getPassword());
                            account.setPhoneNumber(accountDTO.getPhoneNumber());
                            account.setFirstName(accountDTO.getFirstName());
                            account.setLastName(accountDTO.getLastName());
                            account.setCreatedDate(createDate);
                            responseDTO.setStatus(true);
                            responseDTO.setMessage(Const.CREATE_ACCOUNT_SUCCESS);
                            responseDTO.setObjectResponse(account);
                            accountRepository.save(account);
                        }
                    }else{
                        responseDTO.setStatus(false);
                        responseDTO.setMessage(Const.SEND_EMAIL_CREATE_ACCOUNT_ERROR);
                    }
                }
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
