package CurrentBalance;

import Domain.BankAccount;

public interface CurrentBalanceCalculator {

    int calculate(BankAccount bankAccount);
}
