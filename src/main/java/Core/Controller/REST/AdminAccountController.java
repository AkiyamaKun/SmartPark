package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.*;
import Core.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;

/**
 * Manager Account Controller
 * <p>
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

    @Autowired
    SupervisionService supervisionService;

    @Autowired
    CameraService cameraService;

    /**
     * Get Owner by Id
     *
     * @return
     */
    @RequestMapping(value = Const.GET_OWNER, method = RequestMethod.GET)
    public ResponseDTO getOwner(@PathVariable Integer id) {
        return ownerService.getOwner(id);
    }

    /**
     * Get All Owner
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_OWNER, method = RequestMethod.GET)
    public ResponseDTO getAllOwner() {
        return ownerService.getAllOwner();

    }

    /**
     * Create Owner
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.CREATE_OWNER, method = RequestMethod.POST)
    public ResponseDTO createOwner(@RequestBody @Valid OwnerDTO dto) {
        return ownerService.createOwner(dto);
    }

    /**
     * @param dto
     * @param id
     * @return
     */
    @RequestMapping(value = Const.UPDATE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO updateOwner(@RequestBody @Valid OwnerDTO dto,
                                   @PathVariable Integer id) {
        return ownerService.updateOwner(id, dto);
    }

    /**
     * Deactive Owner
     *
     * @param id
     * @return
     */
    @RequestMapping(value = Const.DEACTIVE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO deactiveOwner(@PathVariable Integer id) {
        return ownerService.deactiveOwner(id);
    }

    /**
     * Active Owner
     *
     * @param id
     * @return
     */
    @RequestMapping(value = Const.ACTIVE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO activeOwner(@PathVariable Integer id) {
        return ownerService.activeOwner(id);
    }

    /**
     * Search Owner by Name
     *
     * @param searchValue
     * @return
     */
    @RequestMapping(value = Const.SEARCH_OWNER, method = RequestMethod.GET)
    public ResponseDTO searchOwnerByName(@RequestParam(value = "searchValue", required = true) String searchValue) {
        return ownerService.searchOwnerByName(searchValue);
    }

    /**
     * Get Parking Lot by Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getParkingLot(@PathVariable Integer id) {
        return parkingLotService.getParkingLot(id);
    }

    /**
     * Get All Parking Lot
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getAllParkingLotForAdmin() {
        return parkingLotService.getAllParkingLotForAdmin();
    }

    /**
     * Create Parking Lot
     *
     * @param dto
     * @param adminId
     * @return
     */
    @RequestMapping(value = Const.CREATE_PARKING_LOT, method = RequestMethod.POST)
    public ResponseDTO createParkingLot(@RequestBody @Valid ParkingLotUpdateDTO dto,
                                        @PathVariable Integer adminId) {
        return parkingLotService.createParkingLot(dto, adminId);
    }

    /**
     * Update Parking Lot
     *
     * @param dto
     * @param accountId
     * @return
     */
    @RequestMapping(value = Const.UPDATE_PARKING_LOT, method = RequestMethod.PUT)
    public ResponseDTO updateParkingLot(@RequestBody @Valid ParkingLotUpdateDTO dto,
                                        @PathVariable Integer accountId) {
        return parkingLotService.updateParkingLot(dto, accountId);
    }

    /**
     * Deactive Parking Lot
     *
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.DEACTIVE_PARKING_LOT, method = RequestMethod.PUT)
    public ResponseDTO deactiveParkingLot(@PathVariable Integer parkingLotId) {
        return parkingLotService.deactiveParkingLot(parkingLotId);
    }

    /**
     * Active Parking Lot
     *
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.ACTIVE_PARKING_LOT, method = RequestMethod.PUT)
    public ResponseDTO activeParkingLot(@PathVariable Integer parkingLotId) {
        return parkingLotService.activeParkingLot(parkingLotId);
    }

    /**
     * Get Admin by Id
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ADMIN, method = RequestMethod.GET)
    public ResponseDTO getAdmin(@PathVariable Integer id) {
        return accountService.getAccount(id);
    }

    /**
     * Get All Admin
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_ADMIN, method = RequestMethod.GET)
    public ResponseDTO getAllAdmin() {
        return accountService.getListAccount(1);
    }

    /**
     * Create Admin
     *
     * @return
     */
    @RequestMapping(value = Const.CREATE_ADMIN_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createAdmin(@RequestBody @Valid AccountDTO dto) {
        return accountService.registerAccount(dto);
    }

    /**
     * Update Admin
     *
     * @return
     */
    @RequestMapping(value = Const.UPDATE_ADMIN_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO updateAdmin(@PathVariable Integer id,
                                   @RequestBody @Valid AccountDTO dto,
                                   HttpServletRequest request) {
        ResponseDTO responseDTO = accountService.updateAccount(id, dto);
        if (responseDTO.isStatus()) {
            HttpSession session = request.getSession();
            UserLoginResponseDTO userLoginResponseDTO = (UserLoginResponseDTO) session.getAttribute("User");
            if (userLoginResponseDTO != null) {
                if (userLoginResponseDTO.getAccountId() == id) {
                    AccountDTO accountDTO = (AccountDTO) responseDTO.getObjectResponse();
                    userLoginResponseDTO.setFirstName(accountDTO.getFirstName());
                    userLoginResponseDTO.setLastName(accountDTO.getLastName());
                    userLoginResponseDTO.setPhoneNumber(accountDTO.getPhoneNumber());
                    session.setAttribute("User", userLoginResponseDTO);
                    responseDTO.setMessage(Const.EDIT_PROFILE_SUCCESS);
                }
            }
        }
        return responseDTO;
    }

    /**
     * Deactive Admin
     *
     * @return
     */
    @RequestMapping(value = Const.DEACTIVE_ADMIN_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO deactiveAdmin(@PathVariable Integer id) {
        return accountService.deactiveAccount(id);
    }

    /**
     * Active Admin
     *
     * @return
     */
    @RequestMapping(value = Const.ACTIVE_ADMIN_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO activeAdmin(@PathVariable Integer id) {
        return accountService.activeAccount(id);
    }

    /**
     * Get Supervisor by Id
     *
     * @return
     */
    @RequestMapping(value = Const.GET_SUPERVISOR, method = RequestMethod.GET)
    public ResponseDTO getSupervisor(@PathVariable Integer id) {
        return accountService.getAccount(id);
    }

    /**
     * Get All Supervisor
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_SUPERVISOR, method = RequestMethod.GET)
    public ResponseDTO getAllDriver() {
        return accountService.getListAccount(2);
    }

    /**
     * Create Supervisor
     *
     * @return
     */
    @RequestMapping(value = Const.CREATE_SUPERVISOR_ACCOUNT, method = RequestMethod.POST)
    public ResponseDTO createSupervisor(@RequestBody @Valid AccountDTO dto) {
        return accountService.registerAccount(dto);
    }

    /**
     * Update Supervisor
     *
     * @return
     */
    @RequestMapping(value = Const.UPDATE_SUPERVISOR_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO updateSupervisor(@PathVariable Integer id,
                                        @RequestBody @Valid AccountDTO dto) {
        return accountService.updateAccount(id, dto);
    }

    /**
     * Deactive Supervisor
     *
     * @return
     */
    @RequestMapping(value = Const.DEACTIVE_SUPERVISOR_ACCOUNT, method = RequestMethod.PUT)
    public ResponseDTO deactiveSupervisor(@PathVariable Integer id) {
        return accountService.deactiveAccount(id);
    }

    /**
     * Get All Driver
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_DRIVER, method = RequestMethod.GET)
    public ResponseDTO getAllSupervisor() {
        return accountService.getListAccount(3);
    }

    /**
     * Assign Parking Lot For Supervisor
     *
     * @param parkingLotId
     * @param supervisorId
     * @return
     */
    @RequestMapping(value = Const.ASSIGN_PARKING_LOT_FOR_SUPERVISOR, method = RequestMethod.POST)
    public ResponseDTO assignParkingLotForSupervisor(@RequestParam(value = "parkingLotId") Integer parkingLotId,
                                                     @RequestParam(value = "supervisorId") Integer supervisorId) {
        return supervisionService.assignParkingLotForSupervisor(parkingLotId, supervisorId);
    }

    /**
     * Get List Parking Lot Of Owner
     *
     * @param ownerId
     * @return
     */
    @RequestMapping(value = Const.LIST_PARKINGLOT_OF_OWNER, method = RequestMethod.GET)
    public ResponseDTO getListParkingLotOfOwner(@RequestParam(value = "ownerId") Integer ownerId) {
        return parkingLotService.getListParkingLotOfOwner(ownerId);
    }

    /**
     * Get List Parking Lot Control By Supervisor
     *
     * @param supervisorId
     * @return
     */
    @RequestMapping(value = Const.LIST_PARKINGLOT_CONTROL_BY_SUPERVISOR, method = RequestMethod.GET)
    public ResponseDTO getListParkingLotControlBySupervisor(@RequestParam(value = "supervisorId") Integer supervisorId) {
        return parkingLotService.getListParkingLotControlBySupervisor(supervisorId);
    }

    /**
     * Get List Supervisor Of Parking Lot
     *
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.LIST_SUPERVISOR_OF_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListSupervisorOfParkingLot(@RequestParam(value = "parkingLotId") Integer parkingLotId) {
        return parkingLotService.getListSupervisorOfParkingLot(parkingLotId);
    }

    /**
     * @return
     */
    @RequestMapping(value = Const.TOTAL_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO totalAccount() {
        return accountService.totalAccount();
    }

    /**
     * Create Camera
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.CREATE_CAMERA, method = RequestMethod.POST)
    public ResponseDTO createCamera(@RequestBody @Valid CameraDTO dto) {
        return cameraService.createCamera(dto);
    }

    /**
     * @param dto
     * @param id
     * @return
     */
    @RequestMapping(value = Const.UPDATE_CAMERA, method = RequestMethod.PUT)
    public ResponseDTO updateCamera(@RequestBody @Valid CameraDTO dto,
                                    @PathVariable Integer id) {
        return cameraService.updateCamera(id, dto);
    }

    /**
     * Get All Camera
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_CAMERA, method = RequestMethod.GET)
    public ResponseDTO getAllCamera() {
        return cameraService.getAllCamera();
    }

    /**
     * Assign Camera for Parking Lot
     *
     * @param parkingLotId
     * @param cameraId
     * @return
     */
    @RequestMapping(value = Const.ASSIGN_CAMERA_FOR_PARKING_LOT, method = RequestMethod.PUT)
    public ResponseDTO assignCameraForParkingLot(@RequestParam(value = "cameraId") Integer cameraId,
                                                 @RequestParam(value = "parkingLotId") Integer parkingLotId) {
        return cameraService.assignCameraForParkingLot(cameraId, parkingLotId);
    }
}
