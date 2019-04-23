package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.BookingDTO;
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

import java.util.Date;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    BookingRepository bookingRepository;

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
                Booking booking = new Booking(account, parkingLot, bookingTime);
                //Create token check in
                String token = Utilities.generateToken(account.getEmail());
                booking.setTokenInput(token);
                bookingRepository.save(booking);
                BookingDTO dto = new BookingDTO();
                Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                responseDTO.setStatus(true);
                responseDTO.setObjectResponse(dto);
                responseDTO.setMessage(Const.BOOKING_SUCCESS);
            }else{
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED + "or" + Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Booking Error " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO checkIn(Integer bookingId, Date timeStart) {
        return null;
    }

    @Override
    public ResponseDTO checkOut(Integer bookingId, Date timeEnd) {
        return null;
    }

    @Override
    public ResponseDTO updateTokenGoIn(Integer bookingId, String token) {
        return null;
    }

    @Override
    public ResponseDTO updateTokenGoOut(Integer bookingId, String token) {
        return null;
    }

    @Override
    public ResponseDTO payment(Integer bookingId) {
        return null;
    }
}
