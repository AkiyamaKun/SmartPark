package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.AccountDTO;
import Core.DTO.InformationAccountDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Service.AccountService;
import Core.Service.BookingService;
import Core.Service.DriverAccountService;
import Core.Service.ParkingLotService;
import Core.Utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    BookingService bookingService;

    /**
     * Get Driver Account
     * @param id
     * @return
     */
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
     * Get All Parking Lot
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListParkingLotAcitve(){
        return parkingLotService.getListParkingLotActiveForAndroid();
    }

    /**
     * Forget Password
     * @param email
     * @return
     */
    @RequestMapping(value = Const.FORGET_PASSWORD, method = RequestMethod.POST)
    public ResponseDTO forgotPassword(@RequestParam(value = "email") String email){
        //4 is type of Admin and supervisor on public service
        return accountService.forgetPassword(email, 4);
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

    /**
     * Add Cash for Driver
     * @param accountId
     * @param amountOfCash
     * @return
     */
    @RequestMapping(value = Const.ADD_CASH, method = RequestMethod.PUT)
    public ResponseDTO addCash(@RequestParam(value = "accountId", required = true) Integer accountId,
                                   @RequestParam(value = "amountOfCash", required = true) Integer amountOfCash){
        return  accountService.addCash(accountId, amountOfCash);
    }

    /**
     * Create Booking Slot for Driver
     * @param accountId
     * @param parkingLotId
     * @param bookingTime
     * @return
     */
    @RequestMapping(value = Const.BOOKING_SLOT, method = RequestMethod.POST)
    public ResponseDTO bookingSlot(@RequestParam(value = "accountId", required = true) Integer accountId,
                                   @RequestParam(value = "parkingLotId", required = true) Integer parkingLotId,
                                   @RequestParam(value = "bookingTime", required = true) Date bookingTime){
        return  bookingService.createBooking(accountId, parkingLotId, bookingTime);
    }

    /**
     * Check In
     * @param bookingId
     * @param timeStart
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CHECK_IN, method = RequestMethod.PUT)
    public ResponseDTO checkIn(@RequestParam(value = "bookingId") Integer bookingId,
                               @RequestParam(value = "token") String token,
                               @RequestParam(value = "timeStart") Date timeStart){
        return  bookingService.checkIn(bookingId, token, timeStart);
    }

    /**
     * Check Out
     * @param bookingId
     * @param timeEnd
     * @param token
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CHECK_OUT, method = RequestMethod.PUT)
    public ResponseDTO checkOut(@RequestParam(value = "bookingId") Integer bookingId,
                                @RequestParam(value = "token") String token,
                                @RequestParam(value = "timeEnd") Date timeEnd){
        return bookingService.checkOut(bookingId, token, timeEnd);
    }

    /**
     * Payment
     * @param bookingId
     * @param moneyToPay
     * @return
     */
    @RequestMapping(value = Const.BOOKING_PAYMENT, method = RequestMethod.PUT)
    public ResponseDTO checkOut(@RequestParam(value = "bookingId") Integer bookingId,
                                @RequestParam(value = "moneyToPay") Integer moneyToPay){
        return bookingService.payment(bookingId, moneyToPay);
    }

    /**
     * Get List Booking By Parking Lot Id
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.LIST_BOOKING_BY_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListBookingByParkingLot(@RequestParam(value = "parkingLotId") Integer parkingLotId){
        return bookingService.getListBookingByParkingLotId(parkingLotId);
    }
}
