package Domain;

import CurrentBalance.CurrentBalanceCalculatorStreamApiImpl;
import DepositAmount.DepositAmountCalculatorImpl;
import MaxBalance.MaxBalanceCalculaorForImpl;
import MinBalance.MinBalanceCalculatorForImpl;
import WithdrawallAmount.WithdrawalAmountCalculatorImpl;

public class BankAccountReport {

    public String bankAccountReport(BankAccount bankAccount) {
        StringBuilder report = new StringBuilder();
        transactionList(bankAccount, report);
        balanceInfo(bankAccount, report);
        return report.toString();
    }


    public void transactionList(BankAccount bankAccount, StringBuilder report) {
        report.append("Domain.Transaction list:");
        report.append(System.lineSeparator());

        for (Transaction transaction : bankAccount.getTransactions()) {
            report.append(transaction.transactionType().name());
            report.append(" ");
            report.append(transaction.sum());
            report.append(System.lineSeparator());
        }

        report.append(System.lineSeparator());
    }


    public void balanceInfo(BankAccount bankAccount, StringBuilder report) {

        report.append("Summary:");
        report.append(System.lineSeparator());
        report.append("Starting balance: 0");
        report.append(System.lineSeparator());

        int currentBalance = new CurrentBalanceCalculatorStreamApiImpl().calculate(bankAccount);
        report.append("Current Balance: ").append(currentBalance);
        report.append(System.lineSeparator());

        int depositSum = new DepositAmountCalculatorImpl().calculate(bankAccount);
        report.append("Deposit sum: ").append(depositSum);
        report.append(System.lineSeparator());

        int withdrawAllSum = new WithdrawalAmountCalculatorImpl().calculate(bankAccount);
        report.append("Withdrawal sum: ").append(withdrawAllSum);
        report.append(System.lineSeparator());

        int maxBalance = new MaxBalanceCalculaorForImpl().calculateMaxBalance(bankAccount);
        report.append("Max balance: ").append(maxBalance);
        report.append(System.lineSeparator());

        int minBalance = new MinBalanceCalculatorForImpl().calculateMinBalance(bankAccount);
        report.append("Min balance: ").append(minBalance);

    }
}






