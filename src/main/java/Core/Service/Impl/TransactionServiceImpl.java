package Core.Service.Impl;

import Core.BrainTreePayPal.BrainTreeAction;
import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.DTO.TransactionDTO;
import Core.Entity.Account;
import Core.Entity.Booking;
import Core.Entity.ParkingLot;
import Core.Entity.Transaction;
import Core.Repository.AccountRepository;
import Core.Repository.BookingRepository;
import Core.Repository.ParkingLotRepository;
import Core.Repository.TransactionRepository;
import Core.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public ResponseDTO getListTransaction() {
        return null;
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
}
