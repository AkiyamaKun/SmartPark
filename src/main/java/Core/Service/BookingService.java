package Core.Service;

import Core.DTO.ResponseDTO;

import java.util.Date;

public interface BookingService {
    ResponseDTO createBooking(Integer accountId, Integer parkingLotId, String nonce);
    ResponseDTO checkIn(Integer bookingId, String token);
    ResponseDTO checkOut(Integer bookingId, String token);
    ResponseDTO getListBookingByParkingLotId(Integer parkingLotId);
    ResponseDTO getListBookingByAccountId(Integer accountId, String statusName, Integer quantity);
    ResponseDTO cancelBooking(Integer bookingId);
    ResponseDTO getBookingById(Integer bookingId);
    ResponseDTO countBookingSlotByStatus(Integer parkingLotId);
}
