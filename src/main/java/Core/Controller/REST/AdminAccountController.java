package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.ResponseDTO;
import Core.Service.AccountService;
import Core.Service.DriverAccountService;
import Core.Service.OwnerService;
import Core.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Manager Account Controller
 *
 * Author: DangNHH - 17/02/2019
 */
@RestController
@RequestMapping(value = Const.ADMIN_ACCOUNT)
public class AdminAccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    DriverAccountService driverAccountService;

    @Autowired
    OwnerService ownerService;

    @Autowired
    ParkingLotService parkingLotService;

    /**
     * Get All Owner
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_OWNER, method = RequestMethod.GET)
    public ResponseDTO getAllOwner(){
        return ownerService.getAllOwner();

    }

    /**
     * Get All Owner
     * @return
     */
    @RequestMapping(value = Const.GET_OWNER, method = RequestMethod.GET)
    public ResponseDTO getOwner(@PathVariable Integer id){
        return ownerService.getOwner(id);
    }

    /**
     * Get All Parking Lot
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getAllParkingLotForAdmin(){
        return parkingLotService.getAllParkingLotForAdmin();
    }

    /**
     * Get All Driver
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_DRIVER, method = RequestMethod.GET)
    public ResponseDTO getAllSupervisor(){
        return accountService.getListAccount(3);
    }

    /**
     * Get All Supervisor
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_SUPERVISOR, method = RequestMethod.GET)
    public ResponseDTO getAllDriver(){
        return accountService.getListAccount(2);
    }

    /**
     * Get All Supervisor
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_ADMIN, method = RequestMethod.GET)
    public ResponseDTO getAllAdmin(){
        return accountService.getListAccount(1);
    }


}
