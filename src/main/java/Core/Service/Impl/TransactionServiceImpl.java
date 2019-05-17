package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.DTO.TransactionDTO;
import Core.Entity.Account;
import Core.Entity.Transaction;
import Core.Repository.AccountRepository;
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

    /**
     * Function Convert DTO From Entity
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(TransactionDTO dto, Transaction entity) {
        dto.setTransactionId(entity.getTransactionId());
        //dto.setAccountId(entity.getAccountId().getAccountId());
        dto.setMoney(entity.getMoney());
        dto.setRechargeDate(entity.getRechargeDate());
        //dto.setTransactionStatus(entity.getTransactionStatus());
    }

    @Override
    public ResponseDTO getTransaction(Integer id) {
        return null;
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
            Date date = new Date();
            Transaction transaction = new Transaction();
            //transaction.setTransactionId(transactionDTO.getTransactionId());
            transaction.setMoney(transactionDTO.getMoney());
            transaction.setRechargeDate(date);
            //transaction.setTransactionStatus("Done");
            Account account = accountRepository.findByAccountId(transactionDTO.getAccountId());
            transaction.setAccountId(account);
            transactionRepository.save(transaction);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.SAVE_TRANSACTION_SUCCESS);

        } catch (Exception e) {
            responseDTO.setMessage(Const.SAVE_TRANSACTION_FAIL);
        }
        return responseDTO;
    }
}
