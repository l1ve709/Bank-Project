import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String name;
    private ArrayList<Account> accounts;

    public User(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void displayAccounts() {
        System.out.println(name + " kullanıcısının hesapları:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". Hesap: " + accounts.get(i).getAccountNumber() + " - Bakiye: " + accounts.get(i).getBalance() + " TL");
        }
    }
}

class Account {
    private static int accountCounter = 1000;
    private int accountNumber;
    private double balance;

    public Account() {
        this.accountNumber = accountCounter++;
        this.balance = 0.0;
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
            System.out.println(amount + " TL başarıyla yatırıldı. Güncel bakiye: " + balance + " TL.");
        } else {
            System.out.println("Geçersiz para yatırma işlemi.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " TL başarıyla çekildi. Güncel bakiye: " + balance + " TL.");
        } else {
            System.out.println("Geçersiz para çekme işlemi.");
        }
    }
}

public class BankApp {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Banka Uygulamasına Hoş Geldiniz ---");
            System.out.println("1. Kullanıcı oluştur");
            System.out.println("2. Hesap oluştur");
            System.out.println("3. Para yatır");
            System.out.println("4. Para çek");
            System.out.println("5. Hesap bakiyesi görüntüle");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    displayBalance();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Çıkış yapıldı.");
                    break;
                default:
                    System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
    }

    private static void createUser() {
        System.out.print("Kullanıcı adı girin: ");
        String name = scanner.nextLine();
        User user = new User(name);
        users.add(user);
        System.out.println("Kullanıcı başarıyla oluşturuldu: " + name);
    }

    private static void createAccount() {
        User user = findUser();
        if (user != null) {
            Account account = new Account();
            user.addAccount(account);
            System.out.println("Yeni hesap oluşturuldu: " + account.getAccountNumber());
        }
    }

    private static void depositMoney() {
        User user = findUser();
        if (user != null) {
            user.displayAccounts();
            System.out.print("Yatırmak istediğiniz hesap numarasını seçin: ");
            int accountIndex = scanner.nextInt() - 1;
            if (accountIndex >= 0 && accountIndex < user.getAccounts().size()) {
                System.out.print("Yatırılacak miktarı girin: ");
                double amount = scanner.nextDouble();
                user.getAccounts().get(accountIndex).deposit(amount);
            } else {
                System.out.println("Geçersiz hesap numarası.");
            }
        }
    }

    private static void withdrawMoney() {
        User user = findUser();
        if (user != null) {
            user.displayAccounts();
            System.out.print("Çekmek istediğiniz hesap numarasını seçin: ");
            int accountIndex = scanner.nextInt() - 1;
            if (accountIndex >= 0 && accountIndex < user.getAccounts().size()) {
                System.out.print("Çekilecek miktarı girin: ");
                double amount = scanner.nextDouble();
                user.getAccounts().get(accountIndex).withdraw(amount);
            } else {
                System.out.println("Geçersiz hesap numarası.");
            }
        }
    }

    private static void displayBalance() {
        User user = findUser();
        if (user != null) {
            user.displayAccounts();
        }
    }

    private static User findUser() {
        System.out.print("Kullanıcı adını girin: ");
        String name = scanner.nextLine();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        System.out.println("Kullanıcı bulunamadı.");
        return null;
    }
}
