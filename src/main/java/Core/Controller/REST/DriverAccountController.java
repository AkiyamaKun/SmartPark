package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Service.DriverAccountService;
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

    /**
     * Create New Driver Account
     * @param accountDTO
     * @return
     */
    @RequestMapping(value = Const.CREATE_DRIVER_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createDriverAccount(@RequestBody @Valid AccountDTO accountDTO){
        return driverAccountService.createDriverAccount(accountDTO);
    }

    @RequestMapping(value = Const.VERIFY_DRIVER_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO createDriverAccount(@RequestParam(value = "email", required = true) String email,
                                           @RequestParam(value = "token", required = true) String token){
        return driverAccountService.verifyAccount(email, token);
    }

    @RequestMapping(value = Const.GET_ALL_DRIVER, method = RequestMethod.GET)
    public ResponseDTO getAllDrivers(){
        return driverAccountService.getAllDrivers();
    }
}
