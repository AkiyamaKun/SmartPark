package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ParkingLotDTO;
import Core.DTO.ParkingLotUpdateDTO;
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
     * Get Owner by Id
     * @return
     */
    @RequestMapping(value = Const.GET_OWNER, method = RequestMethod.GET)
    public ResponseDTO getOwner(@PathVariable Integer id){
        return ownerService.getOwner(id);
    }

    /**
     * Search Owner by Name
     * @param searchValue
     * @return
     */
    @RequestMapping(value = Const.SEARCH_OWNER, method = RequestMethod.GET)
    public ResponseDTO searchOwnerByName(@RequestParam(value = "searchValue", required = true) String searchValue){
        return ownerService.searchOwnerByName(searchValue);
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
     * Get All Admin
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_ADMIN, method = RequestMethod.GET)
    public ResponseDTO getAllAdmin(){
        return accountService.getListAccount(1);
    }

    /**
     * Create New Parking Lot
     * @param dto
     * @param ownerId - Account of Supervisor who own this parking lot
     * @param adminId - Account of Admin - who create this parking lot
     * @return
     */
    @RequestMapping(value = Const.CREATE_PARKING_LOT, method = RequestMethod.POST)
    public ResponseDTO createParkingLot(@RequestBody @Valid ParkingLotDTO dto,
                                        @RequestParam (value = "ownerId", required = true) Integer ownerId,
                                        @RequestParam (value = "adminId", required = true) Integer adminId){
        return parkingLotService.createParkingLot(dto, ownerId, adminId );
    }

    @RequestMapping(value = Const.UPDATE_PARKING_LOT_FOR_ADMIN, method = RequestMethod.PUT)
    public ResponseDTO updateParkingLotForAdmin(@RequestBody @Valid ParkingLotUpdateDTO dto,
                                                @RequestParam(value = "adminId") Integer adminId){
        return parkingLotService.updateParkingLot(dto, adminId);
    }
}
