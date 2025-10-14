package MinBalance;

import Domain.BankAccount;
import Domain.Transaction;

public class MinBalanceCalculatorForImpl implements MinBalanceCalculator{

    @Override
    public int calculateMinBalance(BankAccount bankAccount) {
        int currentBalance = 0;
        int minBalance = 0;

        if (bankAccount.hasNoTransactions()){

            return 0;
        }

        Transaction[] transactions = bankAccount.getTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.isDeposit()){
                currentBalance += transaction.sum();
            } else if (transaction.isWithdrawAll()) {
                currentBalance -= transaction.sum();
            }

            if (currentBalance < minBalance){
                minBalance = currentBalance;
            }
        }

        return minBalance;
    }
}
