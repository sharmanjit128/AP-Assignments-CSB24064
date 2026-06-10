import java.util.*;


public class BankSystem {
     static class  Account {
        private String accountNumber;
        private String ownerName;
        private double balance;


        // Default constructor (chaining claaing another constructor using this)
        public Account() {
            this("0000", "Unknown", 0.0);
        }


        // Parameterized constructor
        public Account(String accountNumber, String ownerName, double balance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            setBalance(balance); // use setter for validation
        }


        // Getters
        public String getAccountNumber() {
            return accountNumber;
        }


        public String getOwnerName() {
            return ownerName;
        }


        public double getBalance() {
            return balance;
        }


        // Setters
        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }


        public void setBalance(double balance) {
            if (balance < 0) {
                throw new IllegalArgumentException("Balance cannot be negative");
            }
            this.balance = balance;
        }


        // Deposit method
        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit must be positive");
            }
            balance =balance+amount;
        }


        // Withdraw method
        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdraw must be positive");
            }
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            balance =balance-amount;
        }


        // Display method
        public void display() {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Owner: " + ownerName);
            System.out.println("Balance: " + balance);
        }
    }
    static class SavingsAccount extends Account {
        private  static double interestRate=7.5;


        public SavingsAccount(String accNo, String owner, double balance) {
            super(accNo, owner, balance); // call parent constructor
            //this.interestRate = interestRate;
        }


        public double calculateInterest() {
            return getBalance() * interestRate / 100;
        }


        @Override
        public void display() {
            super.display(); // reuse parent display
            System.out.println("Interest Rate: " + interestRate + "%");
            System.out.println("Estimated Interest: " + calculateInterest());
        }
    }
    static class CurrentAccount extends Account {
        private static double overdraftLimit=5000;


        public CurrentAccount(String accNo, String owner, double balance) {
            super(accNo, owner, balance);
            //this.overdraftLimit = overdraftLimit;
        }


        @Override
        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdraw must be positive");
            }


            if (amount > getBalance() + overdraftLimit) {
                throw new IllegalArgumentException("Overdraft limit exceeded");
            }


            // Allow overdraft
            double newBalance = getBalance() - amount;
            super.setBalance(newBalance);
        }


        @Override
        public void display() {
            super.display();
            System.out.println("Overdraft Limit: " + overdraftLimit);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);


        ArrayList<Account> accounts = new ArrayList<>();
        System.out.println("If you want to enter details of current account holder press c or C for saving account press s or S");
        char AccType=sc.next().charAt(0);
        System.out.println("Enter the no of account holders you want");
        int noOfAccHol=sc.nextInt();
        if(AccType== 'c'|| AccType=='C'){
            System.out.println("ok you want to add current account details");
            for(int i=0;i<noOfAccHol;i++){
                System.out.println("Enter the AccId");
                String id="CA"+sc.next();
                sc.nextLine();
                System.out.println("Enter the AccHolderName");
                String name=sc.nextLine();
                //sc.nextLine();
                System.out.println("Enter the balance");
                double balance=sc.nextDouble();


                accounts.add(new CurrentAccount(id, name, balance));


            }
        }
        System.out.println("If you want to enter details of current account holder press c or C for saving account press s or S");
        char AccType2=sc.next().charAt(0);
        System.out.println("Enter the no of account holders you want");
        int noOfAccHol2=sc.nextInt();
       


       
        if(AccType2== 's'|| AccType2=='S'){
            for(int i=0;i<noOfAccHol2;i++){
                System.out.println("ok you want to add saving account details");
                System.out.println("Enter the AccId");
                String id="SA"+sc.next();
                sc.nextLine();
                System.out.println("Enter the AccHolderName");
                String name=sc.nextLine();
                //sc.nextLine();
                System.out.println("Enter the balance");
                double balance=sc.nextDouble();


                accounts.add(new SavingsAccount(id, name, balance));
            }


        }
       
       
        // accounts.add(new SavingsAccount("A101", "Alice", 5000, 5));
        // accounts.add(new CurrentAccount("C201", "Bob", 3000, 2000));


        for (Account acc : accounts) {
            System.out.println("\n--- Account Details ---");
            acc.display(); // polymorphism (runtime binding)
        }
    }
}
