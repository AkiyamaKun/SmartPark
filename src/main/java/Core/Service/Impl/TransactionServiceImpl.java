package Core.Service.Impl;

import Core.BrainTreePayPal.BrainTreeAction;
import Core.Constant.Const;
import Core.Controller.MVC.MessageController;
import Core.DTO.ResponseDTO;
import Core.DTO.TransactionDTO;
import Core.Entity.*;
import Core.Repository.*;
import Core.Service.ParkingLotService;
import Core.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private ParkingLotServiceImpl parkingLotService;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    private int count = 0;

    /**
     * Function Convert DTO From Entity
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(TransactionDTO dto, Transaction entity) {
        dto.setTransactionId(entity.getTransactionId());
        dto.setAccountId(entity.getAccountId().getAccountId());
        dto.setRechargeDate(entity.getRechargeDate());
        dto.setCardId(entity.getCardId());
        dto.setCardType(entity.getCardType());
        dto.setMoney(entity.getMoney());
        dto.setRechargeDate(entity.getRechargeDate());
        dto.setTransactionCode(entity.getTransactionCode());
        dto.setBookingId(entity.getBookingId().getBookingId());
    }

    @Override
    public ResponseDTO getTransaction(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Transaction transaction = transactionRepository.findByTransactionId(id);
            TransactionDTO dto = new TransactionDTO();
            if (transaction != null) {
                convertDTOFromEntity(dto, transaction);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_TRANSACTION_SUCCESS);
                responseDTO.setObjectResponse(dto);
            } else {
                responseDTO.setMessage(Const.GET_TRANSACTION_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Transaction is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getRevenue() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Integer totalCash = bookingRepository.totalCashToPay();
            if (totalCash >= 0) {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_TRANSACTION_SUCCESS);
                responseDTO.setObjectResponse(totalCash);
            } else {
                responseDTO.setStatus(false);
                responseDTO.setMessage(Const.GET_TRANSACTION_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO saveTransaction(TransactionDTO transactionDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            //Date date = new Date();
            Transaction transaction = new Transaction();
            transaction.setMoney(transactionDTO.getMoney());
            transaction.setRechargeDate(transactionDTO.getRechargeDate());
            transaction.setCardId(transactionDTO.getCardId());
            transaction.setCardType(transactionDTO.getCardType());
            Booking booking = bookingRepository.findByBookingId(transactionDTO.getBookingId());
            transaction.setBookingId(booking);
            Account account = accountRepository.findByAccountId(transactionDTO.getAccountId());
            transaction.setAccountId(account);
            transaction.setTransactionCode(transactionDTO.getTransactionCode());
            transaction.setTypeOfTransaction(transactionDTO.getTypeOfTransaction());
            transactionRepository.save(transaction);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.SAVE_TRANSACTION_SUCCESS);

        } catch (Exception e) {
            responseDTO.setMessage(Const.SAVE_TRANSACTION_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO checkPayment(Integer parkingLotId, String nonce) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (parkingLot != null) {
                BrainTreeAction brainTreeAction = new BrainTreeAction();
                TransactionDTO transactionDTO = brainTreeAction.acceptPayment(String.valueOf(parkingLot.getPrice()), nonce);
                if (transactionDTO != null) {
                    responseDTO.setStatus(true);
                    responseDTO.setObjectResponse(transactionDTO);
                    responseDTO.setMessage(Const.CHECK_PAYMENT_SUCCESS);
                } else {
                    responseDTO.setMessage(Const.CHECK_PAYMENT_FAIL);
                }
            } else {
                responseDTO.setMessage(Const.GET_PARKING_LOT_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.CHECK_PAYMENT_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO generateTokenClient() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            String token = "";
            BrainTreeAction brainTreeAction = new BrainTreeAction();
            if (brainTreeAction.configAction()) {
                token = brainTreeAction.generateToken();
            }
            responseDTO.setStatus(true);
            responseDTO.setMessage(token);
        } catch (Exception e) {
            responseDTO.setMessage(null);
            responseDTO.setMessage(Const.GENERATE_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getTransactionByAccountId(Integer accountId, Integer amount) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<Transaction> transactions = transactionRepository.findByAccountId_AccountIdOrderByTransactionIdDesc(accountId);
            List<TransactionDTO> transactionDTOS = new ArrayList<>();
            if (!StringUtils.isEmpty(transactions)) {
                int countNumber = 0;
                for (Transaction transaction : transactions) {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    convertDTOFromEntity(transactionDTO, transaction);
                    transactionDTOS.add(transactionDTO);
                    countNumber++;

                    if (countNumber >= amount) {
                        break;
                    }
                }

                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_TRANSACTION_SUCCESS);
                responseDTO.setObjectResponse(transactionDTOS);
            } else {
                responseDTO.setMessage(Const.GET_TRANSACTION_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Transaction is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getTransactionByBookingId(Integer bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<Transaction> transactions = transactionRepository.findByBookingId_BookingId(bookingId);
            List<TransactionDTO> transactionDTOS = new ArrayList<>();
            if (!StringUtils.isEmpty(transactions)) {
                for (Transaction transaction : transactions) {
                    TransactionDTO dto = new TransactionDTO();
                    convertDTOFromEntity(dto, transaction);
                    transactionDTOS.add(dto);
                }

                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_TRANSACTION_SUCCESS);
                responseDTO.setObjectResponse(transactionDTOS);
            } else {
                responseDTO.setMessage(Const.GET_TRANSACTION_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Transaction is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO refundForDriver() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        //int count = 0;
        try {
            BookingStatus bookingStatusBook = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_BOOK);
            List<Booking> bookingList = bookingRepository.findByBookingStatus(bookingStatusBook);
            //ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(1);
            List<ParkingSlot> parkingSlots = parkingSlotRepository.findByParkingSlotStatus_StatusNameAndParkingLot_ParkingLotId(Const.STATUS_SLOT_EMPTY, 1);
            BookingStatus bookingStatusCancel = bookingStatusRepository.findByBookingStatusName(Const.STATUS_BOOKING_CANCEL);
            MessageController messageController = new MessageController();

            if (parkingSlots.size() < bookingList.size()) {
                count++;
                System.out.println(count);
            }

            if (count == 3) {
                BrainTreeAction brainTreeAction = new BrainTreeAction();
                System.out.println("bbbbbbb");
                for (Booking booking : bookingList) {
                    Account account = accountRepository.findByAccountId(booking.getAccount().getAccountId());
                    System.out.println(account);
                    Transaction transaction = transactionRepository.findByAccountIdAndBookingId(account, booking);
                    if (brainTreeAction.configAction()) {
                        boolean checked = brainTreeAction.refundOrder(transaction.getTransactionCode());
                        System.out.println(checked);
                        if (checked == true) {
                            System.out.println("aaa" + checked);
                            TransactionDTO dto = new TransactionDTO();
                            convertDTOFromEntity(dto, transaction);
                            responseDTO.setStatus(true);
                            responseDTO.setMessage(Const.REFUND);
                            responseDTO.setObjectResponse(dto);
                            messageController.sendToUser(responseDTO, transaction.getAccountId().getEmail());
                            transaction.setMoney(0);
                            transaction.setTypeOfTransaction(Const.REFUND);
                            transactionRepository.save(transaction);
                            booking.setBookingStatus(bookingStatusCancel);
                            booking.setCashToPay(0);
                            bookingRepository.save(booking);
                            count = 0;
                        } else {
                            count = 0;
                            responseDTO.setMessage(Const.REFUND_FAIL);
                        }
                    } else {
                        responseDTO.setMessage(Const.REFUND_FAIL);
                    }

                }
            }
        } catch (Exception e) {
            responseDTO.setMessage("Refund is exception: " + e.getMessage());
        }
        return responseDTO;
    }
}
