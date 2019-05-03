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
public class DriverAccountController {
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
     *
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_DRIVER_ACCOUNT, method = RequestMethod.GET)
    public ResponseDTO getDriverAccount(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);

        Account account = driverAccountService.getDriver(id);
        InformationAccountDTO dto = new InformationAccountDTO();
        if (account != null) {
            Utilities.convertInformationAccoutDTOFromAccount(dto, account);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.GET_DRIVER_ACCOUNT_SUCCESS);
            responseDTO.setObjectResponse(dto);
        } else {
            responseDTO.setMessage(Const.GET_DRIVER_ACCOUNT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Get All Parking Lot
     *
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListParkingLotAcitve() {
        return parkingLotService.getListParkingLotActiveForAndroid();
    }

    /**
     * Get Parking Lot by Id
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getParkingLot(@PathVariable Integer id){
        return parkingLotService.getParkingLot(id);
    }


    /**
     * Add Cash for Driver
     *
     * @param accountId
     * @param amountOfCash
     * @return
     */
    @RequestMapping(value = Const.ADD_CASH, method = RequestMethod.PUT)
    public ResponseDTO addCash(@RequestParam(value = "accountId", required = true) Integer accountId,
                               @RequestParam(value = "amountOfCash", required = true) Integer amountOfCash) {
        return accountService.addCash(accountId, amountOfCash);
    }

    /**
     * Create Booking Slot for Driver
     *
     * @param accountId
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.BOOKING_SLOT, method = RequestMethod.POST)
    public ResponseDTO bookingSlot(@RequestParam(value = "accountId", required = true) Integer accountId,
                                   @RequestParam(value = "parkingLotId", required = true) Integer parkingLotId) {
        return bookingService.createBooking(accountId, parkingLotId);
    }

    /**
     * Check In
     *
     * @param bookingId
     * @param token
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CHECK_IN, method = RequestMethod.PUT)
    public ResponseDTO checkIn(@RequestParam(value = "bookingId") Integer bookingId,
                               @RequestParam(value = "token") String token) {
        return bookingService.checkIn(bookingId, token);
    }

    /**
     * Check Out
     *
     * @param bookingId
     * @param token
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CHECK_OUT, method = RequestMethod.PUT)
    public ResponseDTO checkOut(@RequestParam(value = "bookingId") Integer bookingId,
                                @RequestParam(value = "token") String token) {
        return bookingService.checkOut(bookingId, token);
    }

    /**
     * Get List Booking By Parking Lot Id
     *
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.LIST_BOOKING_BY_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListBookingByParkingLot(@RequestParam(value = "parkingLotId") Integer parkingLotId) {
        return bookingService.getListBookingByParkingLotId(parkingLotId);
    }

    /**
     * Get List Booking By AccountId with status Name
     *
     * @param accountId
     * @param statusName
     * @return
     */
    @RequestMapping(value = Const.LIST_BOOKING_BY_ACCOUNT_ID, method = RequestMethod.GET)
    public ResponseDTO getListBookingByAccountId(@PathVariable Integer accountId,
                                                 @RequestParam(value = "statusName", required = true) String statusName,
                                                 @RequestParam(value = "quantity", required = true) Integer quantity) {
        return bookingService.getListBookingByAccountId(accountId, statusName, quantity);
    }

    /**
     * Cancel Booking
     *
     * @param bookingId
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CANCEL, method = RequestMethod.PUT)
    public ResponseDTO cancelBooking(@RequestParam(value = "bookingId", required = true) Integer bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    /**
     * Get Booking by id
     * @param bookingId
     * @return
     */
    @RequestMapping(value = Const.GET_BOOKING, method = RequestMethod.GET)
    public ResponseDTO getBookingById(@PathVariable Integer bookingId){
        return bookingService.getBookingById(bookingId);
    }

}
