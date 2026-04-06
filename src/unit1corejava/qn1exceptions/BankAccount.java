package unit1corejava.qn1exceptions;

public class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Method to withdraw money
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal of Rs." + amount +" failed! Insufficient balance. Available balance: Rs. " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Rs. " + amount + " withdrawn");
    }

    // Method to display balance
    public void displayBalance(){
        System.out.println("Current balance is: Rs. " + balance);
    }
}
