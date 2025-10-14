package MaxBalance;

import Domain.Transaction;

public class MaxBalanceCalculaorForImpl implements MaxBalanceCalculaor {

    @Override
    public int calculateMaxBalance(Domain.BankAccount bankAccount) {
        int currentBalance = 0;
        int maxBalance = 0;

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

            if (currentBalance > maxBalance){
                maxBalance = currentBalance;
            }
        }

        return maxBalance;
    }
}
