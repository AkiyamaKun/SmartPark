package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByBookingId(Integer bookingId);
    List<Booking> findByAccount(Account account);
    List<Booking> findByParkingLot(ParkingLot parkingLot);
    List<Booking> findByBookingStatus(String bookingStatus);
}
