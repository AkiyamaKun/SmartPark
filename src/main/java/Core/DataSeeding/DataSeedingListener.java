package Core.DataSeeding;

import Core.Constant.Const;
import Core.Entity.Account;
import Core.Entity.BookingStatus;
import Core.Entity.ParkingSlotStatus;
import Core.Entity.Role;
import Core.Repository.AccountRepository;
import Core.Repository.BookingStatusRepository;
import Core.Repository.ParkingSlotStatusRepository;
import Core.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: DangNHH
 * 05/02/2019
 *
 * Initialize the initial data for the database
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ParkingSlotStatusRepository parkingSlotStatusRepository;

    @Autowired
    BookingStatusRepository bookingStatusRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Role Account
        if(roleRepository.findByRoleName(Const.ROLE_ADMIN) == null){
            roleRepository.save(new Role(Const.ROLE_ADMIN));
        }
        if(roleRepository.findByRoleName(Const.ROLE_SUPERVISOR) == null){
            roleRepository.save(new Role(Const.ROLE_SUPERVISOR));
        }
        if(roleRepository.findByRoleName(Const.ROLE_DRIVER) == null){
            roleRepository.save(new Role(Const.ROLE_DRIVER));
        }

        //Status Slot
        if(parkingSlotStatusRepository.findByStatusName(Const.STATUS_SLOT_EMPTY) == null){
            parkingSlotStatusRepository.save(new ParkingSlotStatus(Const.STATUS_SLOT_EMPTY));
        }
        if(parkingSlotStatusRepository.findByStatusName(Const.STATUS_SLOT_OCCUPIED) == null){
            parkingSlotStatusRepository.save(new ParkingSlotStatus(Const.STATUS_SLOT_OCCUPIED));
        }
        if(parkingSlotStatusRepository.findByStatusName(Const.STATUS_SLOT_UNDEFINED) == null){
            parkingSlotStatusRepository.save(new ParkingSlotStatus(Const.STATUS_SLOT_UNDEFINED));
        }
        if(parkingSlotStatusRepository.findByStatusName(Const.STATUS_SLOT_BOOKED) == null){
            parkingSlotStatusRepository.save(new ParkingSlotStatus(Const.STATUS_SLOT_BOOKED));
        }

        //Status Booking
        if(bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_BOOK) == null){
            bookingStatusRepository.save(new BookingStatus(Const.STATUS_BOOKING_BOOK));
        }
        if(bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_USE) == null){
            bookingStatusRepository.save(new BookingStatus(Const.STATUS_BOOKING_USE));
        }
        if(bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH) == null){
            bookingStatusRepository.save(new BookingStatus(Const.STATUS_BOOKING_FINISH));
        }

        //Account Default
        //Admin Account
        if(accountRepository.findByEmail(Const.ACCOUNT_ADMIN_DEFAULT) == null){
            Date createDate = new Date();
            Role role = roleRepository.findByRoleName(Const.ROLE_ADMIN);

            Account account = new Account();
            account.setEmail(Const.ACCOUNT_ADMIN_DEFAULT);
            account.setPassword(Const.ACCOUNT_PASSWORD_DEFAULT);
            account.setRole(role);
            account.setFirstName("Admin");
            account.setLastName("Account");
            account.setPhoneNumber("0909123123");
            account.setCreatedDate(createDate);
            account.setActive(true);
            accountRepository.save(account);
        }

        //Supervisor Account
        if(accountRepository.findByEmail(Const.ACCOUNT_SUPERVISOR_DEFAULT) == null){
            Date createDate = new Date();
            Role role = roleRepository.findByRoleName(Const.ROLE_SUPERVISOR);
            Account account = new Account();
            account.setEmail(Const.ACCOUNT_SUPERVISOR_DEFAULT);
            account.setPassword(Const.ACCOUNT_PASSWORD_DEFAULT);
            account.setRole(role);
            account.setFirstName("Supervisor");
            account.setLastName("Account");
            account.setPhoneNumber("0909123123");
            account.setCreatedDate(createDate);
            account.setActive(true);
            accountRepository.save(account);
        }

        //Driver Account
        if(accountRepository.findByEmail(Const.ACCOUNT_DRIVER_DEFAULT) == null){
            Date createDate = new Date();
            Role role = roleRepository.findByRoleName(Const.ROLE_DRIVER);

            Account account = new Account();
            account.setEmail(Const.ACCOUNT_DRIVER_DEFAULT);
            account.setPassword(Const.ACCOUNT_PASSWORD_DEFAULT);
            account.setRole(role);
            account.setFirstName("Driver");
            account.setLastName("Account");
            account.setPhoneNumber("0909123123");
            account.setCash(200000);
            account.setPlateNumber("A1-111111");
            account.setCreatedDate(createDate);
            account.setActive(true);
            accountRepository.save(account);
        }
    }
}
