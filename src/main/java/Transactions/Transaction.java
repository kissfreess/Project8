package Transactions;

import org.immutables.value.Value;

@Value.Immutable
public interface Transaction {

    @Value.Parameter
    int sum();
    @Value.Parameter
    TransactionType transactionType();

    public default boolean isDeposit(){
        return transactionType() == TransactionType.DEPOSIT;
    }

    public default boolean isWithdrawAll(){
        return transactionType() == TransactionType.WITHDRAWAL;
    }

}
