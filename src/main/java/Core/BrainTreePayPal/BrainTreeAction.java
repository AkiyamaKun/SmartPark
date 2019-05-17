package Core.BrainTreePayPal;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

import Core.DTO.TransactionDTO;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.ValidationError;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class BrainTreeAction {

    public static String DEFAULT_CONFIG_FILENAME = "config.properties";
    public static BraintreeGateway gateway;

    File configFile = new File(DEFAULT_CONFIG_FILENAME);
    private boolean configAction() {
        try {
            if (configFile.exists() && !configFile.isDirectory()) {
                gateway = BraintreeGatewayFactory.fromConfigFile(configFile);
                return true;
            } else {
                gateway = BraintreeGatewayFactory.fromConfigMapping(System.getenv());
                return true;
            }
        } catch (NullPointerException e) {
            System.err.println("Could not load Braintree configuration from config file or system environment.");
            return false;
        }
    }

    public TransactionDTO acceptPayment(String amount, String nonce) {
        BigDecimal decimalAmount;
        try {
            decimalAmount = new BigDecimal(amount);
        } catch (NumberFormatException e) {
            //Invalid money amount right here, should set text
            return null;
        }
        TransactionRequest request = new TransactionRequest()
                .amount(decimalAmount)
                .paymentMethodNonce(nonce)
                .options()
                .submitForSettlement(true)
                .done();
        Result<Transaction> result = gateway.transaction().sale(request);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            return getTransaction(transaction.getId());
        } else if (result.getTransaction() != null) {
            //Likely because user do not have enough money or something.
            //Still return transaction, but should manually set isStatus = false
            //no need to be saved
            Transaction transaction = result.getTransaction();
            return getTransaction(transaction.getId());
        } else {
            String errorString = "";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
            }
            return null;
        }
    }

     private Status[] TRANSACTION_SUCCESS_STATUSES = new Status[] {
        Status.AUTHORIZED,
        Status.AUTHORIZING,
        Status.SETTLED,
        Status.SETTLEMENT_CONFIRMED,
        Status.SETTLEMENT_PENDING,
        Status.SETTLING,
        Status.SUBMITTED_FOR_SETTLEMENT
     };

    public String generateToken() {
        return gateway.clientToken().generate();
    }

    public TransactionDTO getTransaction(String transactionId) {
        TransactionDTO transactionResult = null;
        Transaction transaction;
        CreditCard creditCard;
        //Customer customer;

        try {
            transaction = gateway.transaction().find(transactionId);
            creditCard = transaction.getCreditCard();
            //customer = transaction.getCustomer();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return transactionResult;
        }
        if(Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus())){
            transactionResult = new TransactionDTO();
            transactionResult.setCardId(creditCard.getCustomerId());
            transactionResult.setCardType(creditCard.getCardType());
            transactionResult.setMoney(transaction.getAmount().intValue());
            transactionResult.setRechargeDate(transaction.getCreatedAt().getTime());
        }
        //model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus()));
        //model.addAttribute("transaction", transaction);
        //model.addAttribute("creditCard", creditCard);
        //model.addAttribute("customer", customer);

        return transactionResult;
    }

}
