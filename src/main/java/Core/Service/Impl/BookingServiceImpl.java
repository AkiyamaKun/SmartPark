package Core.Service.Impl;

import Core.BrainTreePayPal.BrainTreeAction;
import Core.Constant.Const;
import Core.Controller.MVC.MessageController;
import Core.DTO.*;
import Core.Entity.*;
import Core.Repository.*;
import Core.Service.BookingService;
import Core.Utils.Utilities;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ParkingLotServiceImpl parkingLotService;

    @Autowired
    BookingStatusRepository bookingStatusRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    SupervisionRepository supervisionRepository;

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    CameraRepository cameraRepository;

    /**
     * Function Convert DTO From Entity
     *
     * @param transactionDTO
     */
    public void saveTransaction(TransactionDTO transactionDTO, Integer accountId, Integer bookingId) {
        Transaction transaction = new Transaction();
        transaction.setMoney(transactionDTO.getMoney());
        transaction.setRechargeDate(transactionDTO.getRechargeDate());
        transaction.setCardId(transactionDTO.getCardId());
        transaction.setCardType(transactionDTO.getCardType());
        Booking booking = bookingRepository.findByBookingId(bookingId);
        transaction.setBookingId(booking);
        Account account = accountRepository.findByAccountId(accountId);
        transaction.setAccountId(account);
        transaction.setTransactionCode(transactionDTO.getTransactionCode());
        transaction.setTypeOfTransaction(transactionDTO.getTypeOfTransaction());
        transactionRepository.save(transaction);
    }

    /**
     * Create Booking Slot
     *
     * @param accountId
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO createBooking(Integer accountId, Integer parkingLotId, String nonce) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Account account = accountRepository.findByAccountId(accountId);
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (account != null || parkingLot != null) {
                if (parkingLotService.getAvailableSlot(parkingLot) > 0) {
//                    BookingStatus bookingStatusBook = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_BOOK);
//                    BookingStatus bookingStatusUse = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_USE);
//                    List<Booking> checkInfoBook = bookingRepository.findByBookingStatus_BookingStatusIdAndAccount_AccountId(bookingStatusBook.getBookingStatusId(), accountId);
//                    List<Booking> checkInfoUse = bookingRepository.findByBookingStatus_BookingStatusIdAndAccount_AccountId(bookingStatusUse.getBookingStatusId(), accountId);
                    List<Booking> bookingList = bookingRepository.listBookingStatusAndAccount(accountId);
                    BrainTreeAction brainTreeAction = new BrainTreeAction();
                    //checkInfoBook.size() > 0 || checkInfoUse.size() > 0
                    if (bookingList.size() > 0) {
                        for (Booking book : bookingList) {
                            if (book.getParkingLot().getParkingLotId().equals(parkingLotId)) {
                                responseDTO.setStatus(true);
                                if (!book.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)) {
                                    //Generate currentTime
                                    Date timeEnd = new Date();
                                    //Calculate the amount to pay
                                    long milisecond = timeEnd.getTime() - book.getTimeStart().getTime();
                                    long second = milisecond / 1000;
                                    Integer hour = Math.toIntExact(second / 3600);
                                    Integer remainder = Math.toIntExact(second % 3600);
                                    if (remainder > 0) {
                                        hour++;
                                    }
                                    Integer moneyToPay = ((int) book.getParkingLot().getPrice()) * hour;
                                    Integer bookMoney = book.getCashToPay();

                                    book.setTimeEnd(timeEnd);
                                    BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
                                    if (bookingStatus == null) {
                                        bookingStatus = new BookingStatus(Const.STATUS_BOOKING_FINISH);
                                        bookingStatusRepository.save(bookingStatus);
                                    }

                                    book.setBookingStatus(bookingStatus);
                                    book.setCashToPay(moneyToPay + bookMoney);
                                    bookingRepository.save(book);
                                    if (brainTreeAction.configAction()) {
                                        TransactionDTO transactionDTO = brainTreeAction.acceptPayment(String.valueOf(moneyToPay), nonce);
                                        if (transactionDTO != null) {
                                            transactionDTO.setTypeOfTransaction(Const.PAYMENT);
                                            saveTransaction(transactionDTO, accountId, book.getBookingId());
                                        }
                                    }
                                    //Return response DTO
                                    BookingDTO bookingDTO = new BookingDTO();
                                    bookingDTO.setBookingId(book.getBookingId());
                                    bookingDTO.setAccountId(book.getAccount().getAccountId());
                                    bookingDTO.setParkingLotId(book.getParkingLot().getParkingLotId());
                                    bookingDTO.setEmail(book.getAccount().getEmail());
                                    bookingDTO.setParkingLotName(book.getParkingLot().getDisplayName());
                                    bookingDTO.setPrice(book.getParkingLot().getPrice());
                                    bookingDTO.setBookingTime(book.getBookingTime());
                                    bookingDTO.setTimeStart(book.getTimeStart());
                                    bookingDTO.setTimeEnd(book.getTimeEnd());
                                    bookingDTO.setTimeUseBySecond(second);
                                    bookingDTO.setBookingStatus(book.getBookingStatus().getBookingStatusName());
                                    bookingDTO.setCashToPay(moneyToPay + bookMoney);
                                    bookingDTO.setPlateNumber(book.getPlateNumber());
                                    responseDTO.setObjectResponse(bookingDTO);
                                }
                                responseDTO.setStatus(true);
                                responseDTO.setMessage(Const.CHECKOUT);
                                MessageController messageController = new MessageController();
                                //messageController.sendToUser(responseDTO, book.getAccount().getEmail());
                                messageController.sendToUser(responseDTO, "supervisor_666");
                                return responseDTO;
                            } else {
                                responseDTO.setStatus(false);
                                responseDTO.setMessage(Const.BOOKING_FAIL);
                                return responseDTO;
                            }
                        }//StringUtils.isEmpty(checkInfoBook) || StringUtils.isEmpty(checkInfoUse)
                    } else if (bookingList.isEmpty() || bookingList.size() == 0) {
                        brainTreeAction = new BrainTreeAction();
                        if (brainTreeAction.configAction()) {
                            int amount = Math.round(parkingLot.getPrice() / 4);
                            TransactionDTO transactionDTO = brainTreeAction.acceptPayment(String.valueOf(amount), nonce);
                            if (transactionDTO != null) {
                                //Generate currentTime
                                Date bookingTime = new Date();
                                Booking booking = new Booking(account, parkingLot, bookingTime);
                                //Create token check in
                                String token = Utilities.generateToken(account.getEmail());
                                booking.setTokenInput(token);
                                if (account.getPlateNumber() == null) {
                                    booking.setPlateNumber("");
                                } else {
                                    booking.setPlateNumber(account.getPlateNumber());
                                }
                                booking.setCashToPay(amount);
                                BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_BOOK);
                                if (bookingStatus == null) {
                                    bookingStatus = new BookingStatus(Const.STATUS_BOOKING_BOOK);
                                    bookingStatusRepository.save(bookingStatus);
                                }
                                booking.setBookingStatus(bookingStatus);
                                bookingRepository.save(booking);
                                String urlAPICheckIn = Const.DOMAIN + Const.PUBLIC + Const.BOOKING_CHECK_IN + "?bookingId=" + booking.getBookingId()
                                        + "&token=" + token;
                                booking.setUrlApiCheckIn(urlAPICheckIn);
                                bookingRepository.save(booking);
                                BookingDTO dto = new BookingDTO();

                                Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                                dto.setPrice(parkingLot.getPrice());
                                dto.setParkingLotName(parkingLot.getDisplayName());
                                transactionDTO.setTypeOfTransaction(Const.BOOKED);
                                saveTransaction(transactionDTO, accountId, booking.getBookingId());

                                //Excute update field 'bookingSlot' in Parking Lot
                                Integer bookingSlotInParkingLot = parkingLot.getBookingSlot();
                                if (bookingSlotInParkingLot < 0) {
                                    bookingSlotInParkingLot = 0;
                                } else {
                                    bookingSlotInParkingLot++;
                                }
                                parkingLot.setBookingSlot(bookingSlotInParkingLot);
                                parkingLotRepository.save(parkingLot);

                                //return response
                                responseDTO.setStatus(true);
                                responseDTO.setObjectResponse(dto);
                                responseDTO.setMessage(Const.PAYPAL);
                                MessageController messageController = new MessageController();
                                messageController.sendToUser(responseDTO, booking.getAccount().getEmail());
                                messageController.sendToUser(responseDTO, "supervisor_666");
                            } else {
                                responseDTO.setMessage(Const.BOOKING_FAIL);
                            }
                        }
                    } else {
                        responseDTO.setMessage(Const.BOOKING_FAIL);
                    }
                } else {
                    //Available slot = 0 -> Cant booking
                    responseDTO.setMessage(Const.BOOKING_OUT_OF_SLOT);
                }
            } else {
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED + "or" + Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Booking Error " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Check In
     *
     * @param bookingId
     * @param token
     * @return
     */
    @Override
    public ResponseDTO checkIn(Integer bookingId, String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if (booking != null && token.equalsIgnoreCase(booking.getTokenInput())) {
                if (!booking.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)) {
                    if (booking.getTokenOutput() == null) {
                        //Generate currentTime
                        Date timeStart = new Date();
                        Account account = accountRepository.findByAccountId(booking.getAccount().getAccountId());
                        long milisecond = timeStart.getTime() - booking.getBookingTime().getTime();
                        long second = milisecond / 1000;
                        if (second <= Const.DEFAULT_TIME_OUT_CHECK_IN) {
                            //Create token check out
                            String tokenOutPut = Utilities.generateToken(account.getEmail());
                            booking.setTokenOutput(tokenOutPut);

                            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_USE);
                            if (bookingStatus == null) {
                                bookingStatus = new BookingStatus(Const.STATUS_BOOKING_USE);
                                bookingStatusRepository.save(bookingStatus);
                            }
                            booking.setBookingStatus(bookingStatus);
                            booking.setTimeStart(timeStart);
                            bookingRepository.save(booking);
                            String urlAPICheckOut = Const.DOMAIN + Const.PUBLIC + Const.BOOKING_CHECK_OUT + "?bookingId=" + booking.getBookingId()
                                    + "&token=" + tokenOutPut;
                            booking.setUrlApiCheckOut(urlAPICheckOut);
                            bookingRepository.save(booking);
                            BookingDTO dto = new BookingDTO();
                            Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                            //Excute update field 'bookingSlot' in Parking Lot
                            ParkingLot parkingLot = booking.getParkingLot();
                            Integer bookingSlotInParkingLot = parkingLot.getBookingSlot();
                            if (bookingSlotInParkingLot - 1 < 0) {
                                bookingSlotInParkingLot = 0;
                            } else {
                                bookingSlotInParkingLot--;
                            }
                            parkingLot.setBookingSlot(bookingSlotInParkingLot);
                            parkingLotRepository.save(parkingLot);

                            //return response
                            responseDTO.setStatus(true);
                            responseDTO.setObjectResponse(dto);
                            responseDTO.setMessage(Const.CHECKIN);
                            MessageController messageController = new MessageController();
                            messageController.sendToUser(responseDTO, booking.getAccount().getEmail());
                        } else {
                            //Time Out Check In
                            booking.setTimeStart(timeStart);
                            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
                            if (bookingStatus == null) {
                                bookingStatus = new BookingStatus(Const.STATUS_BOOKING_FINISH);
                                bookingStatusRepository.save(bookingStatus);
                            }
                            booking.setBookingStatus(bookingStatus);
                            bookingRepository.save(booking);
                            BookingDTO dto = new BookingDTO();
                            Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                            responseDTO.setMessage(Const.BOOKING_TIME_OUT_CHECK_IN);
                            responseDTO.setObjectResponse(dto);
                        }
                    } else {
                        //Had Check In
                        responseDTO.setMessage(Const.BOOKING_HAD_CHECK_IN);
                    }
                } else {
                    //Booking had finished
                    responseDTO.setMessage(Const.BOOKING_HAD_FINISH);
                }
            } else {
                //Booking is not existed
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.BOOKING_CHECK_IN_FAIL + ": " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Check Out
     *
     * @param bookingId
     * @param token
     * @return
     */
    @Override
    public ResponseDTO checkOut(Integer bookingId, String token) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if (booking != null && token.equalsIgnoreCase(booking.getTokenOutput())) {
                if (!booking.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_FINISH)) {
                    //Generate currentTime
                    Date timeEnd = new Date();
                    //Calculate the amount to pay
                    long milisecond = timeEnd.getTime() - booking.getTimeStart().getTime();
                    long second = milisecond / 1000;
                    Integer hour = Math.toIntExact(second / 3600);
                    Integer remainder = Math.toIntExact(second % 3600);
                    if (remainder > 0) {
                        hour++;
                    }
                    Integer moneyToPay = ((int) booking.getParkingLot().getPrice()) * hour;

                    //Excute payment
//                    if (booking.getAccount().getCash() >= moneyToPay) {
//                        Integer newCash = booking.getAccount().getCash() - moneyToPay;
//                        Account account = booking.getAccount();
//                        account.setCash(newCash);
//                        accountRepository.save(account);
                    booking.setTimeEnd(timeEnd);
                    BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
                    if (bookingStatus == null) {
                        bookingStatus = new BookingStatus(Const.STATUS_BOOKING_FINISH);
                        bookingStatusRepository.save(bookingStatus);
                    }
                    booking.setBookingStatus(bookingStatus);
                    booking.setCashToPay(moneyToPay);
                    bookingRepository.save(booking);

                    //Return response DTO
                    BookingDTO bookingDTO = new BookingDTO();
                    bookingDTO.setBookingId(bookingId);
                    bookingDTO.setEmail(booking.getAccount().getEmail());
                    bookingDTO.setParkingLotName(booking.getParkingLot().getDisplayName());
                    bookingDTO.setPrice(booking.getParkingLot().getPrice());
                    bookingDTO.setBookingTime(booking.getBookingTime());
                    bookingDTO.setTimeStart(booking.getTimeStart());
                    bookingDTO.setTimeEnd(booking.getTimeEnd());
                    bookingDTO.setTimeUseBySecond(second);
                    bookingDTO.setCashToPay(moneyToPay);
                    bookingDTO.setPlateNumber(booking.getPlateNumber());

                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(bookingDTO);
                    responseDTO.setMessage(Const.BOOKING_CHECK_OUT_SUCCESS);

                    MessageController messageController = new MessageController();
                    messageController.sendToUser(responseDTO, booking.getAccount().getEmail());
                } else {
                    //Money not enough
//                        CheckOutDTO checkOutDTO = new CheckOutDTO();
//                        checkOutDTO.setBookingId(bookingId);
//                        checkOutDTO.setMoneyToPay(moneyToPay);
//                        checkOutDTO.setParkingLotName(booking.getParkingLot().getDisplayName());
                    responseDTO.setMessage(Const.BOOKING_CHECK_OUT_FAIL);
                    //bookingRepository.save(booking);
                }
            } else {
                //Booking had finished
                responseDTO.setMessage(Const.BOOKING_HAD_FINISH);
            }
//            } else {
//                //Booking is not existed
//                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
//            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.BOOKING_CHECK_OUT_FAIL + ": " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get List Booking By Parking Lot
     *
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO getListBookingByParkingLotId(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (parkingLot != null) {
                List<Booking> bookingList = bookingRepository.findByParkingLot(parkingLot);
                List<BookingDTO> bookingDTOS = new ArrayList<>();
                if (!StringUtils.isEmpty(bookingList)) {
                    for (Booking booking : bookingList) {
                        BookingDTO bookingDTO = new BookingDTO();
                        bookingDTO = Utilities.convertBookingDTOFromBookingEntity(bookingDTO, booking);
                        bookingDTOS.add(bookingDTO);
                    }
                    responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(bookingDTOS);
                } else {
                    responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
                    responseDTO.setStatus(true);
                }
            } else {
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get List Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get List Booking By Account Id
     *
     * @param accountId
     * @return
     */
    @Override
    public ResponseDTO getListBookingByAccountId(Integer accountId, String statusName, String statusName2, Integer quantity) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Account account = accountRepository.findByAccountId(accountId);
            if (account != null) {
                List<Booking> bookings = bookingRepository.findByAccountOrderByBookingIdDesc(account);
                if (!bookings.isEmpty()) {
                    int countNumber = 0;
                    List<BookingDTO> bookingDTOList = new ArrayList<>();
                    for (Booking element : bookings) {
                        BookingDTO bookingDTO = new BookingDTO();
                        if (!StringUtils.isEmpty(statusName2)) {
                            if (element.getBookingStatus().getBookingStatusName().equals(statusName) ||
                                    element.getBookingStatus().getBookingStatusName().equals(statusName2)) {
                                Utilities.convertBookingDTOFromBookingEntity(bookingDTO, element);
                                bookingDTOList.add(bookingDTO);
                                countNumber++;
                            }
                        } else {
                            if (element.getBookingStatus().getBookingStatusName().equals(statusName)) {
                                Utilities.convertBookingDTOFromBookingEntity(bookingDTO, element);
                                bookingDTOList.add(bookingDTO);
                                countNumber++;
                            }
                        }

                        if (countNumber >= quantity) {
                            break;
                        }
                    }
                    responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(bookingDTOList);
                } else {
                    responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
                    responseDTO.setStatus(true);
                }
            } else {
                responseDTO.setMessage(Const.ACCOUNT_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get List Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Cancel Booking
     *
     * @param bookingId
     * @return
     */
    @Override
    public ResponseDTO cancelBooking(Integer bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if (booking != null) {
                if (booking.getBookingStatus().getBookingStatusName().equals(Const.STATUS_BOOKING_BOOK)) {
                    BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_CANCEL);
                    if (bookingStatus == null) {
                        bookingStatus = new BookingStatus(Const.STATUS_BOOKING_CANCEL);
                        bookingStatusRepository.save(bookingStatus);
                    }
                    booking.setBookingStatus(bookingStatus);
                    bookingRepository.save(booking);

                    //Excute update field 'bookingSlot' in Parking Lot
                    ParkingLot parkingLot = booking.getParkingLot();
                    Integer bookingSlotInParkingLot = parkingLot.getBookingSlot();
                    if (bookingSlotInParkingLot - 1 < 0) {
                        bookingSlotInParkingLot = 0;
                    } else {
                        bookingSlotInParkingLot--;
                    }
                    parkingLot.setBookingSlot(bookingSlotInParkingLot);
                    parkingLotRepository.save(parkingLot);

                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CANCEL_BOOKING_SUCCESS);
                } else {
                    responseDTO.setMessage(Const.CANCEL_BOOKING_FAIL);
                }
            } else {
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Cancel Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get Booking By Id
     *
     * @param bookingId
     * @return
     */
    @Override
    public ResponseDTO getBookingById(Integer bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if (booking != null) {
                BookingDTO dto = new BookingDTO();
                Account account = accountRepository.findByAccountId(booking.getAccount().getAccountId());
                dto = Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                responseDTO.setStatus(true);
                responseDTO.setMessage(account.getEmail());
                responseDTO.setObjectResponse(dto);
            } else {
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * @return
     */
    @Override
    public ResponseDTO countBookingSlotByStatus(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            //ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            List<ParkingSlot> parkingSlots = parkingSlotRepository.findByParkingSlotStatus_StatusNameAndParkingLot_ParkingLotId(Const.STATUS_SLOT_EMPTY, parkingLotId);
            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_BOOK);
            List<Booking> bookingList = bookingRepository.findByBookingStatus(bookingStatus);
//            List<Camera> cameras = cameraRepository.findByParkingLotId_ParkingLotId(parkingLotId);


//            if (cameras.get(0).getUrlLiveStream() != null) {
//                String urlStr = cameras.get(0).getUrlLiveStream() + parkingLotId;
//                System.out.println(urlStr);
//                URL url = null;
//                try {
//                    url = new URL(urlStr);
//                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//                    urlConn.connect();
//                    if (urlConn.getResponseCode() == 200) {
//                        responseDTO.setMessage("active");
//                    } else {
//                        responseDTO.setMessage("not active");
//                    }
//                } catch (MalformedURLException ex) {
//                    ex.printStackTrace();
//                } catch (IOException o) {
//                    o.printStackTrace();
//                }
//            }

            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.GET_BOOKING_SUCCESS);
            responseDTO.setObjectResponse(bookingList.size() + " / " + parkingSlots.size());

        } catch (Exception e) {
            responseDTO.setMessage("Get Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getAllBookingFinish() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<BookingDTO> bookingDTOS = new ArrayList<>();
            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_FINISH);
            List<Booking> bookings = bookingRepository.findByBookingStatus(bookingStatus);

            if (!bookings.isEmpty()) {
                for (Booking booking : bookings) {
                    BookingDTO tmp = new BookingDTO();
                    convertDTOFromEntity(tmp, booking);
                    bookingDTOS.add(tmp);
                }
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                responseDTO.setObjectResponse(bookingDTOS);
            } else {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.GET_LIST_BOOKING_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getAllBookingCancel() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<BookingDTO> bookingDTOS = new ArrayList<>();
            BookingStatus bookingStatus = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_CANCEL);
            List<Booking> bookings = bookingRepository.findByBookingStatus(bookingStatus);

            if (!bookings.isEmpty()) {
                for (Booking booking : bookings) {
                    BookingDTO tmp = new BookingDTO();
                    convertDTOFromEntity(tmp, booking);
                    bookingDTOS.add(tmp);
                }
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                responseDTO.setObjectResponse(bookingDTOS);
            } else {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.GET_LIST_BOOKING_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO checkBooking(Integer bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        MessageController messageController = new MessageController();
        try {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if (booking != null) {
                BookingDTO dto = new BookingDTO();
                if (booking.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_USE)) {
                    //Generate currentTime
                    Date timeEnd = new Date();
                    //Calculate the amount to pay
                    long milisecond = timeEnd.getTime() - booking.getTimeStart().getTime();
                    long second = milisecond / 1000;
                    Integer hour = Math.toIntExact(second / 3600);
                    Integer remainder = Math.toIntExact(second % 3600);
                    if (remainder > 0) {
                        hour++;
                    }
                    Integer moneyToPay = ((int) booking.getParkingLot().getPrice()) * hour;

                    booking.setTimeEnd(timeEnd);
                    dto = Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                    dto.setCashToPay(moneyToPay);
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CHECKOUT);
                    responseDTO.setObjectResponse(dto);
                    messageController.sendToUser(responseDTO, booking.getAccount().getEmail());
                    //messageController.sendToUser(responseDTO, "supervisor_666");
                } else if (booking.getBookingStatus().getBookingStatusName().equalsIgnoreCase(Const.STATUS_BOOKING_BOOK)) {
                    dto = Utilities.convertBookingDTOFromBookingEntity(dto, booking);
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CHECKIN);
                    responseDTO.setObjectResponse(dto);
                    messageController.sendToUser(responseDTO, booking.getAccount().getEmail());
                } else {
                    responseDTO.setMessage(Const.GET_BOOKING_FAIL);
                }
            } else {
                responseDTO.setMessage(Const.BOOKING_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getBookingByAccountId(Integer accountId, Integer amount) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<Booking> bookings = bookingRepository.findByAccount_AccountIdOrderByBookingTimeDesc(accountId);
            List<BookingDTO> bookingDTOS = new ArrayList<>();
            if (!StringUtils.isEmpty(bookings)) {
                int countNumber = 0;
                for (Booking booking : bookings) {
                    BookingDTO bookingDTO = new BookingDTO();
                    bookingDTO = Utilities.convertBookingDTOFromBookingEntity(bookingDTO, booking);
                    bookingDTOS.add(bookingDTO);
                    countNumber++;

                    if (countNumber >= amount) {
                        break;
                    }
                }

                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_BOOKING_SUCCESS);
                responseDTO.setObjectResponse(bookingDTOS);
            } else {
                responseDTO.setMessage(Const.GET_BOOKING_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Booking is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO listBookingForReport() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<Booking> bookings = bookingRepository.findAll();
            List<BookingDTO> bookingDTOS = new ArrayList<>();
            if (!StringUtils.isEmpty(bookings)) {
                for (Booking booking : bookings) {
                    BookingDTO bookingDTO = new BookingDTO();
                    bookingDTO = Utilities.convertBookingDTOFromBookingEntity(bookingDTO, booking);
                    bookingDTOS.add(bookingDTO);
                }
                responseDTO.setMessage(Const.GET_LIST_BOOKING_SUCCESS);
                responseDTO.setStatus(true);
                responseDTO.setObjectResponse(bookingDTOS);
            } else {
                responseDTO.setMessage(Const.GET_LIST_BOOKING_FAIL);
                responseDTO.setStatus(true);
            }

        } catch (Exception e) {
            responseDTO.setMessage("Get List Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getTotalDriverBooking(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Account account = accountRepository.findByAccountId(id);
            List<Supervision> supervision = supervisionRepository.findBySupervisor(account);
            List<Booking> bookingList = bookingRepository.findByBookingStatus_BookingStatusName(Const.STATUS_BOOKING_FINISH);
            if (!StringUtils.isEmpty(bookingList) && !StringUtils.isEmpty(supervision)) {
                bookingList.stream().forEach(book -> {
                    supervision.stream().forEach(e -> {
                        if (book.getParkingLot().getParkingLotId().equals(e.getParkingLot().getParkingLotId())) {
                            Integer totalDriver = bookingRepository.totalDriverBooking();
                            responseDTO.setStatus(true);
                            responseDTO.setMessage(Const.GET_BOOKING_SUCCESS);
                            responseDTO.setObjectResponse(totalDriver);
                        }
                    });
                });
            } else {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_BOOKING_FAIL);
                responseDTO.setObjectResponse(0);
            }

        } catch (Exception e) {
            responseDTO.setMessage("Get Booking Error: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Function Convert DTO From Entity
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(BookingDTO dto, Booking entity) {
        dto.setBookingId(entity.getBookingId());
        dto.setBookingTime(entity.getBookingTime());
        dto.setTimeStart(entity.getTimeStart());
        dto.setTimeEnd(entity.getTimeEnd());
        dto.setPlateNumber(entity.getPlateNumber());
        dto.setParkingLotId(entity.getParkingLot().getParkingLotId());
        dto.setUrlApiCheckIn(entity.getUrlApiCheckIn());
        dto.setUrlApiCheckOut(entity.getUrlApiCheckOut());
    }
}
