package Core.Service;

import Core.DTO.ResponseDTO;

import java.util.Date;

public interface BookingService {
    ResponseDTO createBooking(Integer accountId, Integer parkingLotId, String nonce);
    ResponseDTO checkIn(Integer bookingId, String token);
    ResponseDTO checkOut(Integer bookingId, String token);
    ResponseDTO getListBookingByParkingLotId(Integer parkingLotId);
    ResponseDTO getListBookingByAccountId(Integer accountId, String statusName, String statusName2, Integer quantity);
    ResponseDTO cancelBooking(Integer bookingId);
    ResponseDTO getBookingById(Integer bookingId);
    ResponseDTO countBookingSlotByStatus(Integer parkingLotId);
    ResponseDTO getAllBookingFinish();
    ResponseDTO checkBooking(Integer bookingId);
    ResponseDTO getBookingByAccountId(Integer accountId, Integer amount);
    ResponseDTO listBookingForReport();
    ResponseDTO getTotalDriverBooking(String email);
}
