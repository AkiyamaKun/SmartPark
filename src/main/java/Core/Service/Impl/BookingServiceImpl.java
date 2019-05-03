package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.BookingDTO;
import Core.DTO.CheckOutDTO;
import Core.DTO.ParkingLotDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.BookingStatus;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.BookingRepository;
import Core.Repository.BookingStatusRepository;
import Core.Repository.ParkingLotRepository;
import Core.Service.BookingService;
import Core.Utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ParkingLotServiceImpl parkingLotService;

    @Autowired
    BookingStatusRepository bookingStatusRepository;

    /**
     * Create Booking Slot
     * @param accountId
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO createBooking(Integer accountId, Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(accountId);
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(account != null || parkingLot != null){
                if(parkingLotService.getAvailableSlot(parkingLot) > 0){
                    //Generate currentTime
                    Date bookingTime = new Date();
                    Booking booking = new Booking(account, parkingLot, bookingTime);
                    //Create token check in
                    String token = Utilities.generateToken(account.getEmail());
                    booking.setTokenInput(token);
                    if(account.getPlateNumber() == null){
                        booking.setPlateNumber("");
                    }else{
                        booking.setPlateNumber(account.getPlateNumber());
                    }
                    BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_BOOK);
                    if(bookingStatus == null){
                        bookingStatus = new BookingStatus(Const.STATUS_BOOKING_BOOK);
                        bookingStatusRepository.save(bookingStatus);
                    }
                    booking.setBookingStatus(bookingStatus);
                    bookingRepository.save(booking);
                    String urlAPICheckIn = Const.DOMAIN + Const.PUBLIC + Const.BOOKING_CHECK_IN + "?bookingId=" + booking.getBookingId()
                            + "&token=" + token;
                    booking.setUrlApiCheckIn(urlAPICheckIn);
                    bookingRepository.save(booking);
                    BookingDTO dto = new BookingDTO();
                    Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                    dto.setPrice(parkingLot.getPrice());
                    dto.setParkingLotName(parkingLot.getDisplayName());

                    //Excute update field 'bookingSlot' in Parking Lot
                    Integer bookingSlotInParkingLot = parkingLot.getBookingSlot();
                    if(bookingSlotInParkingLot < 0){
                        bookingSlotInParkingLot = 0;
                    }else{
                        bookingSlotInParkingLot++;
                    }
                    parkingLot.setBookingSlot(bookingSlotInParkingLot);
                    parkingLotRepository.save(parkingLot);

                    //return response
                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(dto);
                    responseDTO.setMessage(Const.BOOKING_SUCCESS);
                }else{
                    //Available slot = 0 -> Cant booking
                    responseDTO.setMessage(Const.BOOKING_OUT_OF_SLOT);
                }
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED + "or" + Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Booking Error " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Check In
     * @param bookingId
     * @param token
     * @return
     */
    @Override
    public ResponseDTO checkIn(Integer bookingId, String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null && token.equalsIgnoreCase(booking.getTokenInput())){
                if(!booking.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)){
                    if(booking.getTokenOutput() == null){
                        //Generate currentTime
                        Date timeStart = new Date();
                        Account account = accountRepository.findByAccountId(booking.getAccount().getAccountId());
                        long milisecond = timeStart.getTime()- booking.getBookingTime().getTime();
                        long second = milisecond/1000;
                        if(second <= Const.DEFAULT_TIME_OUT_CHECK_IN){
                            //Create token check out
                            String tokenOutPut = Utilities.generateToken(account.getEmail());
                            booking.setTokenOutput(tokenOutPut);

                            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_USE);
                            if(bookingStatus == null){
                                bookingStatus = new BookingStatus(Const.STATUS_BOOKING_USE);
                                bookingStatusRepository.save(bookingStatus);
                            }
                            booking.setBookingStatus(bookingStatus);
                            booking.setTimeStart(timeStart);
                            bookingRepository.save(booking);
                            String urlAPICheckOut = Const.DOMAIN + Const.PUBLIC + Const.BOOKING_CHECK_OUT + "?bookingId=" + booking.getBookingId()
                                    + "&token=" + tokenOutPut;
                            booking.setUrlApiCheckOut(urlAPICheckOut);
                            bookingRepository.save(booking);
                            BookingDTO dto = new BookingDTO();
                            Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                            //Excute update field 'bookingSlot' in Parking Lot
                            ParkingLot parkingLot = booking.getParkingLot();
                            Integer bookingSlotInParkingLot = parkingLot.getBookingSlot();
                            if(bookingSlotInParkingLot - 1 < 0){
                                bookingSlotInParkingLot = 0;
                            }else{
                                bookingSlotInParkingLot--;
                            }
                            parkingLot.setBookingSlot(bookingSlotInParkingLot);
                            parkingLotRepository.save(parkingLot);

                            //return response
                            responseDTO.setStatus(true);
                            responseDTO.setObjectResponse(dto);
                            responseDTO.setMessage(Const.BOOKING_CHECK_IN_SUCCESS);
                        }else{
                            //Time Out Check In
                            booking.setTimeStart(timeStart);
                            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
                            if(bookingStatus == null){
                                bookingStatus = new BookingStatus(Const.STATUS_BOOKING_FINISH);
                                bookingStatusRepository.save(bookingStatus);
                            }
                            booking.setBookingStatus(bookingStatus);
                            bookingRepository.save(booking);
                            BookingDTO dto = new BookingDTO();
                            Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                            responseDTO.setMessage(Const.BOOKING_TIME_OUT_CHECK_IN);
                            responseDTO.setObjectResponse(dto);
                        }
                    }else{
                        //Had Check In
                        responseDTO.setMessage(Const.BOOKING_HAD_CHECK_IN);
                    }
                }else{
                    //Booking had finished
                    responseDTO.setMessage(Const.BOOKING_HAD_FINISH);
                }
            }else{
                //Booking is not existed
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.BOOKING_CHECK_IN_FAIL + ": " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Check Out
     * @param bookingId
     * @param token
     * @return
     */
    @Override
    public ResponseDTO checkOut(Integer bookingId, String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null && token.equalsIgnoreCase(booking.getTokenOutput())){
                if(!booking.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)){
                    //Generate currentTime
                    Date timeEnd = new Date();
                    //Calculate the amount to pay
                    long milisecond = timeEnd.getTime() - booking.getTimeStart().getTime();
                    long second = milisecond/1000;
                    Integer hour = Math.toIntExact(second / 3600);
                    Integer remainder = Math.toIntExact(second % 3600);
                    if(remainder > 0){
                        hour++;
                    }
                    Integer moneyToPay = ((int)booking.getParkingLot().getPrice())*hour;

                    //Excute payment
                    if(booking.getAccount().getCash() >= moneyToPay){
                        Integer newCash = booking.getAccount().getCash() - moneyToPay;
                        Account account = booking.getAccount();
                        account.setCash(newCash);
                        accountRepository.save(account);
                        booking.setTimeEnd(timeEnd);
                        BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
                        if(bookingStatus == null){
                            bookingStatus = new BookingStatus(Const.STATUS_BOOKING_FINISH);
                            bookingStatusRepository.save(bookingStatus);
                        }
                        booking.setBookingStatus(bookingStatus);
                        booking.setCashToPay(moneyToPay);
                        bookingRepository.save(booking);

                        //Return response DTO
                        CheckOutDTO checkOutDTO = new CheckOutDTO();
                        checkOutDTO.setBookingId(bookingId);
                        checkOutDTO.setEmail(booking.getAccount().getEmail());
                        checkOutDTO.setParkingLotName(booking.getParkingLot().getDisplayName());
                        checkOutDTO.setPrice(booking.getParkingLot().getPrice());
                        checkOutDTO.setBookingTime(booking.getBookingTime());
                        checkOutDTO.setTimeStart(booking.getTimeStart());
                        checkOutDTO.setTimeEnd(booking.getTimeEnd());
                        checkOutDTO.setTimeUseBySecond(second);
                        checkOutDTO.setMoneyToPay(moneyToPay);
                        checkOutDTO.setPlateNumber(booking.getPlateNumber());

                        responseDTO.setStatus(true);
                        responseDTO.setObjectResponse(checkOutDTO);
                        responseDTO.setMessage(Const.BOOKING_CHECK_OUT_SUCCESS);
                    }else{
                        //Money not enough
                        responseDTO.setMessage(Const.MONEY_NOT_ENOUGH);
                        bookingRepository.save(booking);
                    }
                }else{
                    //Booking had finished
                    responseDTO.setMessage(Const.BOOKING_HAD_FINISH);
                }
            }else{
                //Booking is not existed
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.BOOKING_CHECK_OUT_FAIL + ": " + e.getMessage());
        }
        return responseDTO;
    }
    /**
     * Get List Booking By Parking Lot
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO getListBookingByParkingLotId(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(parkingLot != null){
                List<Booking> bookings = bookingRepository.findByParkingLot(parkingLot);
                if(!bookings.isEmpty()){
                    responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(bookings);
                }else{
                    responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
                    responseDTO.setStatus(true);
                }
            }else{
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get List Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get List Booking By Account Id
     * @param accountId
     * @return
     */
    @Override
    public ResponseDTO getListBookingByAccountId(Integer accountId, String statusName, Integer quantity) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(accountId);
            if(account != null){
                List<Booking> bookings = bookingRepository.findByAccountOrderByBookingIdDesc(account);
                if(!bookings.isEmpty()){
                    int countNumber = 0;
                    List<BookingDTO> bookingDTOList = new ArrayList<>();
                    for(Booking element: bookings){
                        BookingDTO bookingDTO = new BookingDTO();
                        if(element.getBookingStatus().getBookingStatusName().equals(statusName)){
                            Utilities.convertBookingDTOFromBookingEntity(bookingDTO, element);
                            bookingDTOList.add(bookingDTO);
                            countNumber++;
                        }
                        if(countNumber >= quantity){
                            break;
                        }
                    }
                    responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(bookingDTOList);
                }else{
                    responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
                    responseDTO.setStatus(true);
                }
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get List Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Cancel Booking
     * @param bookingId
     * @return
     */
    @Override
    public ResponseDTO cancelBooking(Integer bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null){
                if(booking.getBookingStatus().getBookingStatusName().equals(Const.STATUS_BOOKING_BOOK)){
                    BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
                    if(bookingStatus == null){
                        bookingStatus = new BookingStatus(Const.STATUS_BOOKING_FINISH);
                        bookingStatusRepository.save(bookingStatus);
                    }
                    booking.setBookingStatus(bookingStatus);
                    bookingRepository.save(booking);

                    //Excute update field 'bookingSlot' in Parking Lot
                    ParkingLot parkingLot = booking.getParkingLot();
                    Integer bookingSlotInParkingLot = parkingLot.getBookingSlot();
                    if(bookingSlotInParkingLot - 1 < 0){
                        bookingSlotInParkingLot = 0;
                    }else{
                        bookingSlotInParkingLot--;
                    }
                    parkingLot.setBookingSlot(bookingSlotInParkingLot);
                    parkingLotRepository.save(parkingLot);

                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CANCEL_BOOKING_SUCCESS);
                }else{
                    responseDTO.setMessage(Const.CANCEL_BOOKING_FAIL);
                }
            }else {
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Cancel Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get Booking By Id
     * @param bookingId
     * @return
     */
    @Override
    public ResponseDTO getBookingById(Integer bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null){
                BookingDTO dto = new BookingDTO();
                Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_BOOKING_SUCCESS);
                responseDTO.setObjectResponse(dto);
            }else {
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }
}
