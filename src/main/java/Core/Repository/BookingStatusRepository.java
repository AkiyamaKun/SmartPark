package Core.Repository;

import Core.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingStatusRepository extends JpaRepository<BookingStatus, Integer> {
    BookingStatus findByBookingStatusName(String bookingStatusName);
}
