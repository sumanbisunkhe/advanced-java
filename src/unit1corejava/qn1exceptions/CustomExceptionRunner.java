package unit1corejava.qn1exceptions;

public class CustomExceptionRunner {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Suman",5000);
        account.displayBalance();

        try {
            account.withdraw(6000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        account.displayBalance();
    }
}
