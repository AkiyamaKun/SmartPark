package Core.Service;

import Core.DTO.ResponseDTO;

import java.util.Date;

public interface BookingService {
    ResponseDTO createBooking(Integer accountId, Integer parkingLotId, Date bookingTime);
    ResponseDTO checkIn(Integer bookingId, String token, Date timeStart);
    ResponseDTO checkOut(Integer bookingId, String token, Date timeEnd);
    ResponseDTO updateTokenGoIn(Integer bookingId, String token);
    ResponseDTO updateTokenGoOut(Integer bookingId, String token);
    ResponseDTO payment(Integer bookingId, Integer moneyToPay);
    ResponseDTO getListBookingByParkingLotId(Integer parkingLotId);
}
