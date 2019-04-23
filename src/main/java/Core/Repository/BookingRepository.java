package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByBookingId(Integer bookingId);
    Booking findByAccount(Account account);
    Booking findByParkingLot(ParkingLot parkingLot);
    Booking findByBookingStatus(String bookingStatus);
}
