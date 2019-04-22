package Core.Repository;

import Core.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByBookingId(Integer bookingId);
    Booking findByAccountId(Integer accountId);
    Booking findByParkingLotId(Integer parkingLotId);
    Booking findByBookingStatus(String bookingStatus);
}
