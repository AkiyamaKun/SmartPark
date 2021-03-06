package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.BookingStatus;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByBookingId(Integer bookingId);
    List<Booking> findByAccount(Account account);
    List<Booking> findByParkingLot(ParkingLot parkingLot);
    List<Booking> findByBookingStatus_BookingStatusName(String bookingStatus);
    List<Booking> findByAccountOrderByBookingIdDesc(Account account);
    List<Booking> findByBookingStatus(BookingStatus status);
    List<Booking> findByBookingStatus_BookingStatusIdAndAccount_AccountId(Integer bookingStatusId, Integer accountId);
    List<Booking> findByAccount_AccountIdOrderByBookingTimeDesc(Integer accountId);

    @Query("SELECT SUM(b.cashToPay) FROM Booking b")
    Integer totalCashToPay();

    @Query("SELECT b.bookingTime, b.cashToPay FROM Booking b")
    List<Booking> getBookingTimeAndCash();

    @Query("SELECT COUNT(DISTINCT b.account.accountId) FROM Booking b")
    Integer totalDriverBooking();

    @Query("SELECT b FROM Booking b, BookingStatus bs " +
            "WHERE b.bookingStatus.bookingStatusId = bs.bookingStatusId " +
            "AND (bs.bookingStatusName = 'BOOK' " +
            "OR bs.bookingStatusName = 'USE') AND b.account.accountId = ?1")
    List<Booking> listBookingStatusAndAccount(Integer accountId);
}
