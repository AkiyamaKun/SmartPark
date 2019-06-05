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


public class BrainTreeAction {

    public static String DEFAULT_CONFIG_FILENAME = "config.properties";
    public static BraintreeGateway gateway;

    File configFile = new File(DEFAULT_CONFIG_FILENAME);

    public boolean configAction() {
        try {
            if (configFile.exists() && !configFile.isDirectory()) {
                gateway = BraintreeGatewayFactory.fromConfigFile(configFile);
                return true;
            } else {
                gateway = BraintreeGatewayFactory.fromConfigMapping();
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

    private Status[] TRANSACTION_SUCCESS_STATUSES = new Status[]{
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
        if (Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus())) {
            transactionResult = new TransactionDTO();
            transactionResult.setCardId(transaction.getPayPalDetails().getPayerId());
            transactionResult.setCardType("PAYPAL");
            transactionResult.setMoney(transaction.getAmount().intValue());
            transactionResult.setRechargeDate(transaction.getCreatedAt().getTime());
            transactionResult.setTransactionCode(transactionId);
        }
        //model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus()));
        //model.addAttribute("transaction", transaction);
        //model.addAttribute("creditCard", creditCard);
        //model.addAttribute("customer", customer);

        return transactionResult;
    }

    public boolean refundOrder(String transactionCode){
        Result<Transaction> result = gateway.transaction().refund(transactionCode);
        System.out.println(result);
        if(result.isSuccess()){
            System.out.println("Refund success: " + (result.getMessage() != null? result.getMessage() : "No message"));
            return true;
        }
        else{
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.out.println(error.getMessage());
            }
            return false;
        }
    }

}
