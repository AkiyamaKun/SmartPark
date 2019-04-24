package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.BookingDTO;
import Core.DTO.CheckOutDTO;
import Core.DTO.ParkingLotDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.BookingRepository;
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

    /**
     * Convert BookingDTO From Booking Entity
     * @param dto
     * @param entity
     */
    public void convertBookingDTOFromBookingEntity(BookingDTO dto, Booking entity){
        dto.setBookingId(entity.getBookingId());
        dto.setAccountId(entity.getAccount().getAccountId());
        dto.setParkingLotId(entity.getParkingLot().getParkingLotId());
        dto.setBookingTime(entity.getBookingTime());
        dto.setTimeStart(entity.getTimeStart());
        dto.setTimeEnd(entity.getTimeEnd());
        dto.setTokenInput(entity.getTokenInput());
        dto.setTokenOutput(entity.getTokenOutput());
        dto.setBookingStatus(entity.getBookingStatus());
    }

    /**
     * Create Booking Slot
     * @param accountId
     * @param parkingLotId
     * @param bookingTime
     * @return
     */
    @Override
    public ResponseDTO createBooking(Integer accountId, Integer parkingLotId, Date bookingTime) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(accountId);
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(account != null || parkingLot != null){
                if(parkingLotService.getAvailableSlot(parkingLot) > 0){
                    Booking booking = new Booking(account, parkingLot, bookingTime);
                    //Create token check in
                    String token = Utilities.generateToken(account.getEmail());
                    booking.setTokenInput(token);
                    booking.setBookingStatus(Const.STATUS_BOOKING_BOOK);
                    bookingRepository.save(booking);
                    BookingDTO dto = new BookingDTO();
                    Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                    String urlAPICheckIn = Const.DOMAIN + Const.DRIVER_ACCOUNT + Const.BOOKING_CHECK_IN + "?bookingId=" + booking.getBookingId()
                            + "&token=" + token;
                    dto.setUrlAPICheckIn(urlAPICheckIn);

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
     * @param timeStart
     * @param token
     * @return
     */
    @Override
    public ResponseDTO checkIn(Integer bookingId, String token, Date timeStart) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null && token.equalsIgnoreCase(booking.getTokenInput())){
                if(!booking.getBookingStatus().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)){
                    if(booking.getTokenOutput() == null){
                        Account account = accountRepository.findByAccountId(booking.getAccount().getAccountId());
                        long milisecond = timeStart.getTime()- booking.getBookingTime().getTime();
                        long second = milisecond/1000;
                        if(second <= Const.DEFAULT_TIME_OUT_CHECK_IN){
                            //Create token check out
                            String tokenOutPut = Utilities.generateToken(account.getEmail());
                            booking.setTokenOutput(tokenOutPut);
                            booking.setBookingStatus(Const.STATUS_BOOKING_USE);
                            booking.setTimeStart(timeStart);
                            bookingRepository.save(booking);
                            BookingDTO dto = new BookingDTO();
                            Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                            String urlAPICheckOut = Const.DOMAIN + Const.DRIVER_ACCOUNT + Const.BOOKING_CHECK_OUT + "?bookingId=" + booking.getBookingId()
                                    + "&token=" + tokenOutPut;
                            dto.setUrlAPICheckOut(urlAPICheckOut);

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
                            booking.setBookingStatus(Const.STATUS_BOOKING_FINISH);
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
     * @param timeEnd
     * @return
     */
    @Override
    public ResponseDTO checkOut(Integer bookingId, String token, Date timeEnd) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null && token.equalsIgnoreCase(booking.getTokenOutput())){
                if(!booking.getBookingStatus().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)){
                    booking.setTimeEnd(timeEnd);
                    bookingRepository.save(booking);

                    //Calculate the amount to pay
                    long milisecond = timeEnd.getTime() - booking.getTimeStart().getTime();
                    long second = milisecond/1000;
                    Integer hour = Math.toIntExact(second / 3600);
                    Integer remainder = Math.toIntExact(second % 3600);
                    if(remainder > 0){
                        hour++;
                    }
                    Integer money = ((int)booking.getParkingLot().getPrice())*hour;
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
                    checkOutDTO.setMoneyToPay(money);

                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(checkOutDTO);
                    responseDTO.setMessage(Const.BOOKING_CHECK_OUT_SUCCESS);
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

    @Override
    public ResponseDTO updateTokenGoIn(Integer bookingId, String token) {
        return null;
    }

    @Override
    public ResponseDTO updateTokenGoOut(Integer bookingId, String token) {
        return null;
    }

    /**
     * Payment
     * @param bookingId
     * @param moneyToPay
     * @return
     */
    @Override
    public ResponseDTO payment(Integer bookingId, Integer moneyToPay) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if(booking != null){
                if(!booking.getBookingStatus().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)){
                    if(booking.getAccount().getCash() >= moneyToPay){
                        Integer newCash = booking.getAccount().getCash() - moneyToPay;
                        Account account = booking.getAccount();
                        account.setCash(newCash);
                        accountRepository.save(account);
                        booking.setBookingStatus(Const.STATUS_BOOKING_FINISH);
                        bookingRepository.save(booking);
                        //Maybe add function history here
                        responseDTO.setStatus(true);
                        responseDTO.setObjectResponse(account);
                        responseDTO.setMessage(Const.BOOKING_PAYMENT_SUCCESS);
                    }else{
                        //Money not enough
                        responseDTO.setMessage(Const.MONEY_NOT_ENOUGH);
                        booking.setTimeEnd(null);
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
            responseDTO.setMessage(Const.BOOKING_PAYMENT_FAIL + ": " + e.getMessage());
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
    public ResponseDTO getListBookingByAccountId(Integer accountId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account account = accountRepository.findByAccountId(accountId);
            if(account != null){
                List<Booking> bookings = bookingRepository.findByAccount(account);
                if(!bookings.isEmpty()){
                    List<BookingDTO> bookingDTOList = new ArrayList<>();
                    for(Booking element: bookings){
                        BookingDTO bookingDTO = new BookingDTO();
                        convertBookingDTOFromBookingEntity(bookingDTO, element);
                        bookingDTOList.add(bookingDTO);
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
}
