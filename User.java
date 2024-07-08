import java.util.*;

class User {
        protected String acc_num;
        protected String password;
        protected int balance;
        protected Bank bank;
        protected int mobile_no;
        //
        Scanner sc = new Scanner(System.in);

        User(String acc_num, String password, int balance, Bank bank) {
                this.acc_num = acc_num;
                this.password = password;
                this.balance = balance;
                this.bank = bank;
                this.bank.adduser(this);
        }

        // withdraws amount if successful.
        protected void withdrawAmount() throws notSufficientFunds {

                // sc.nextLine();
                System.out.println("Enter Amount: ");
                int Amount = sc.nextInt();
                sc.nextLine();

                if (Amount < this.balance) {
                        this.balance -= Amount;
                } else {
                        throw new notSufficientFunds();
                }

        }

        // overloaded function using paramenters.
        protected void withdrawAmount(int Amount) throws notSufficientFunds {
                if (Amount < this.balance) {
                        if (Amount >= 10000) {
                                try {
                                        System.out.println("Your withdrawl amount exceeds 10k Rupees.");
                                        this.verifyOtp();
                                        this.balance -= Amount;
                                } catch (invalidOtp wgnOtp) {
                                        System.out.println("invalid OTP");
                                }
                        } else {
                                this.balance -= Amount;
                        }

                } else {
                        throw new notSufficientFunds();
                }

        }

        // adding money to bank.
        public void addBalance() throws valueNotSameError {

                System.out.println("Choose mode of transfer: ");
                System.out.println("1: Cash ");
                System.out.println("2: Online ");
                int choice = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter Amount : ");
                int Amount = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                        if (this.bank.verifyPayment(Amount)) {
                                this.balance += Amount;
                                System.out.println(String.format(
                                                "Successfully added %d Rupees in your account via Cash.", Amount));
                        } else {
                                throw new valueNotSameError();
                        }
                } else {
                        System.out.println(String.format(
                                        "Please insert %d Rupees into the collection slot below.", Amount));
                        if (this.bank.verifyPayment(Amount)) {
                                this.balance += Amount;
                                System.out.println(String.format(
                                                "Successfully added %d Rupees in your account via Online.", Amount));
                        } else {
                                throw new valueNotSameError();
                        }
                }
        }

        // overloaded addBalance.
        public void addBalance(int Amount, int choice) throws valueNotSameError {
                if (choice == 1) {
                        if (this.bank.verifyPayment(Amount)) {
                                this.balance += Amount;
                                System.out.println(String.format(
                                                "Successfully added %d Rupees in your account via Cash.", Amount));
                        } else {
                                throw new valueNotSameError();
                        }
                } else {
                        System.out.println(String.format(
                                        "Please insert %d Rupees into the collection slot below.", Amount));
                        if (this.bank.verifyPayment(Amount)) {
                                this.balance += Amount;
                                System.out.println(String.format(
                                                "Successfully added %d Rupees in your account via Online.", Amount));
                        } else {
                                throw new valueNotSameError();
                        }
                }
        }

        public String getUser() {
                return this.acc_num;
        }

        public String getPass() {
                return this.password;
        }

        // display balance.
        public void dis() {
                System.out.println(String.format("The current balance is: %d", this.balance));
        }

        // OTP verification. not implemented logic. Any 4 digit number will work.
        public boolean verifyOtp() throws invalidOtp {

                System.out.println("You need OTP authorisation for selected action.");
                Scanner sc = new Scanner(System.in);
                int otp = sc.nextInt();
                sc.nextLine();

                if (otp <= 9999 && otp >= 1000) {
                        return true;
                } else {
                        throw new invalidOtp();
                }
        }

        // change Password (After otp verfivation)
        protected void changePass() {

                try {
                        this.verifyOtp();
                        System.out.print("Enter new Password : ");
                        this.password = sc.nextLine();

                } catch (invalidOtp invld) {
                        System.out.println("Wrong OTP");
                }

        }
}