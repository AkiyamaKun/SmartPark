package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.InformationAccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Service.DriverAccountService;
import Core.Utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Driver Account Controller
 *
 * Author: DangNHH - 17/02/2019
 */
@RestController
@RequestMapping(value = Const.DRIVER_ACCOUNT)
public class DriverAccountController{
    @Autowired
    DriverAccountService driverAccountService;

    @RequestMapping(value = Const.VERIFY_DRIVER_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO createDriverAccount(@RequestParam(value = "email", required = true) String email,
                                           @RequestParam(value = "token", required = true) String token){
        return driverAccountService.verifyAccount(email, token);
    }

    @RequestMapping(value = Const.GET_DRIVER_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO getDriverAccount(@PathVariable Integer id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);

        Account account = driverAccountService.getDriver(id);
        InformationAccountDTO dto = new InformationAccountDTO();
        if(account != null){
            Utilities.convertInformationAccoutDTOFromAccount(dto,account);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.GET_DRIVER_ACCOUNT_SUCCESS);
            responseDTO.setObjectResponse(dto);
        }else{
            responseDTO.setMessage(Const.GET_DRIVER_ACCOUNT_FAIL);
        }
        return responseDTO;
    }



}
