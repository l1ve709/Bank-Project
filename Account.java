import java.util.ArrayList;

class Account {
    private static int accountCounter = 1000;
    private int accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account() {
        this.accountNumber = accountCounter++;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            Transaction transaction = new Transaction("Para Yatırma", amount);
            transactions.add(transaction);
            System.out.println(amount + " TL başarıyla yatırıldı. Güncel bakiye: " + balance + " TL.");
        } else {
            System.out.println("Geçersiz para yatırma işlemi.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction("Para Çekme", amount);
            transactions.add(transaction);
            System.out.println(amount + " TL başarıyla çekildi. Güncel bakiye: " + balance + " TL.");
        } else {
            System.out.println("Geçersiz para çekme işlemi.");
        }
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("Hesapta henüz işlem yapılmamış.");
        } else {
            System.out.println("İşlem Geçmişi:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}
