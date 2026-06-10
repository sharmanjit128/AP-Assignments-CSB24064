// =========================
// ORDER TYPES
// =========================
public class As{




    static class Order {
    protected int orderId;
    protected String customerName;
    protected double amount;


    public Order(int orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
    }


    public double getTotal() {
        return amount;
    }


    public int getOrderId() {
        return orderId;
    }
    }


    static class DiscountedOrder extends Order {


        public DiscountedOrder(int orderId, String customerName, double amount) {
            super(orderId, customerName, amount);
        }


        @Override
        public double getTotal() {
            return amount * 0.9; // 10% discount
        }
    }


    class PriorityOrder extends Order {


        public PriorityOrder(int orderId, String customerName, double amount) {
            super(orderId, customerName, amount);
        }


        @Override
        public double getTotal() {
            return amount + 100; // priority fee
        }
    }




// =========================
// PAYMENT METHODS
// =========================


    interface PaymentMethod {
        void pay(double amount);
    }


    static class CreditCardPayment implements PaymentMethod {


        @Override
        public void pay(double amount) {
            System.out.println("Paid " + amount + " using Credit Card");
        }
    }


    static class UPIPayment implements PaymentMethod {


        @Override
        public void pay(double amount) {
            System.out.println("Paid " + amount + " using UPI");
        }
    }


    static class WalletPayment implements PaymentMethod {


        @Override
        public void pay(double amount) {
            System.out.println("Paid " + amount + " using Wallet");
        }
    }




// =========================
// NOTIFICATION SERVICES
// =========================


    interface NotificationService {
        void send(String message);
    }


    static class EmailNotification implements NotificationService {


        @Override
        public void send(String message) {
            System.out.println("EMAIL: " + message);
        }
    }


    static class SMSNotification implements NotificationService {


        @Override
        public void send(String message) {
            System.out.println("SMS: " + message);
        }
    }


    static class PushNotification implements NotificationService {


        @Override
        public void send(String message) {
            System.out.println("PUSH: " + message);
        }
    }




    // =========================
    // STORAGE MECHANISMS
    // =========================


    interface OrderRepository {
        void save(Order order);
    }


    static class DatabaseStorage implements OrderRepository {


        @Override
        public void save(Order order) {
            System.out.println("Order " + order.getOrderId()
                    + " saved to Database");
        }
    }


    static class FileStorage implements OrderRepository {


        @Override
        public void save(Order order) {
            System.out.println("Order " + order.getOrderId()
                    + " saved to File");
        }
    }




// =========================
// ORDER SERVICE
// =========================


static class OrderService {


    private PaymentMethod paymentMethod;
    private NotificationService notificationService;
    private OrderRepository repository;


    public OrderService(PaymentMethod paymentMethod,
                        NotificationService notificationService,
                        OrderRepository repository) {


        this.paymentMethod = paymentMethod;
        this.notificationService = notificationService;
        this.repository = repository;
    }


    public void placeOrder(Order order) {


        double total = order.getTotal();


        // Process Payment
        paymentMethod.pay(total);


        // Save Order
        repository.save(order);


        // Send Notification
        notificationService.send(
                "Order " + order.getOrderId()
                + " placed successfully"
        );
    }
}




// =========================
// MAIN CLASS
// =========================




     public static void main(String[] args) {


        Order order =
                new DiscountedOrder(101, "Rahul", 1000);


        PaymentMethod payment =
                new UPIPayment();


        NotificationService notification =
                new EmailNotification();


        OrderRepository storage =
                new DatabaseStorage();


        OrderService service =
                new OrderService(
                        payment,
                        notification,
                        storage
                );


        service.placeOrder(order);
    }
}
