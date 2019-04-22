package Core.Service;

import Core.DTO.ResponseDTO;

import java.util.Date;

public interface BookingService {
    ResponseDTO createBooking(Integer accountId, Integer parkingLotId, Date bookingTime);
    ResponseDTO checkIn(Integer bookingId, Date timeStart);
    ResponseDTO checkOut(Integer bookingId, Date timeEnd);
    ResponseDTO updateTokenGoIn(Integer bookingId, String token);
    ResponseDTO updateTokenGoOut(Integer bookingId, String token);
    ResponseDTO payment(Integer bookingId);
}
