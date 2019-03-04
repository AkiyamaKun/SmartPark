package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.InformationAccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Service.AccountService;
import Core.Service.DriverAccountService;
import Core.Service.ParkingLotService;
import Core.Utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    AccountService accountService;

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

    @RequestMapping(value = Const.GET_ALL_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListParkingLotAcitve(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLotService.getListParkingLotActiveForAndroid();
        //Implement
        return responseDTO;
    }

    /**
     * Forget Password
     * @param email
     * @return
     */
    @RequestMapping(value = Const.FORGET_PASSWORD, method = RequestMethod.POST)
    public ResponseDTO forgotPassword(@RequestParam(value = "email") String email){
        return driverAccountService.forgetPasswordOfDriver(email);
    }

    /**
     * Create Driver Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.CREATE_DRIVER_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createDriver(@RequestBody @Valid AccountDTO accountDTO){
        //Register Driver Account
        accountDTO.setRoleId(3);
        return accountService.registerAccount(accountDTO);

    }

    /**
     * Set new password then forget password
     * @param email
     * @param password
     * @param passwordConfirm
     * @return
     */
    @RequestMapping(value = Const.SET_NEW_PASSWORD, method = RequestMethod.PUT)
    public ResponseDTO setNewPassword(@RequestParam(value = "email", required = true) String email,
                                      @RequestParam(value = "password", required = true) String password,
                                      @RequestParam(value = "passwordConfirm", required = true) String passwordConfirm){
        ResponseDTO responseDTO = new ResponseDTO();
        if(!password.equals(passwordConfirm)){
            responseDTO.setStatus(false);
            responseDTO.setMessage(Const.PASSWORD_CONFIRM_FAIL);
        }else{
            responseDTO = accountService.setFirstPassword(email,password);
            responseDTO.setMessage(Const.CHANGE_PASSWORD_SUCCESS);
        }
        return responseDTO;
    }
}
