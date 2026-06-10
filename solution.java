import java.util.regex.Pattern;


public class As17 {


    // Checked Exception
    class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }


    // Unchecked Exception
    class UnderageException extends RuntimeException {
        public UnderageException(String message) {
            super(message);
        }
    }


    // Core Service Class
    class RegistrationService {


        private static final String EMAIL_REGEX =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


        private static final Pattern pattern =
                Pattern.compile(EMAIL_REGEX);


        public boolean registerUser(String email, int age)
                throws InvalidEmailException {


            assert age >= 0 : "Age cannot be negative";


            if (email == null || email.trim().isEmpty()) {
                throw new InvalidEmailException(
                        "Email cannot be null or empty");
            }


            if (!pattern.matcher(email).matches()) {
                throw new InvalidEmailException(
                        "Invalid email format: " + email);
            }


            if (age < 18) {
                throw new UnderageException(
                        "User must be at least 18 years old. Provided age: " + age);
            }


            return true;
        }
    }


    // MAIN METHOD INSIDE CLASS ✔️
    public static void main(String[] args) {


        As17 outer = new As17(); // required because inner classes exist


        RegistrationService service = outer.new RegistrationService();


        try {


            boolean result = service.registerUser("user@example.com", 22);
            System.out.println("Registration Successful: " + result);


            service.registerUser("wrong-email", 25);


        } catch (InvalidEmailException e) {
            System.out.println("InvalidEmailException: " + e.getMessage());


        } catch (UnderageException e) {
            System.out.println("UnderageException: " + e.getMessage());
        }


        try {
            service.registerUser("young@example.com", 16);


        } catch (InvalidEmailException | UnderageException e) {
            System.out.println(e.getClass().getSimpleName()
                    + ": " + e.getMessage());
        }
    }
}


