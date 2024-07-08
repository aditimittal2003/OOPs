import java.util.Scanner;

// when received amount != stated amount.
class valueNotSameError extends Exception {
    public valueNotSameError() {
        super("Received amount and input amount do not match.");
    }
}

// wrong OTP.
class invalidOtp extends Exception {
    public invalidOtp() {
        super("Wrong OTP");
    }
}

// Account not found in ATM's BANK List.
class accountNotFound extends Exception {
    public accountNotFound() {
        super("Account not found in supported Banks");
    }
}

// wrong password/PIN.
class invalidPassword extends Exception {
    public invalidPassword() {
        super("Invalid Passsword !");
    }
}

// Withdrwal > Balance
class notSufficientFunds extends Exception {
    public notSufficientFunds() {
        super("The entered amount exceeds balance. ");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank SBI = new Bank("SBI");
        Bank PNB = new Bank("PNB"); // created 3 new banks.
        Bank ABC = new Bank("ABC");

        ATM atm1 = new ATM(); // creating 2 ATMs and linking some of the banks to them.
        atm1.addBank(SBI);
        atm1.addBank(PNB);
        atm1.addBank(ABC);

        ATM atm2 = new ATM();
        atm2.addBank(SBI);
        atm2.addBank(ABC);

        // creating some users in various Banks.
        User user1 = new User("1234", "abcd", 1, SBI);
        User user2 = new User("1111", "bcde", 2, SBI);
        User user3 = new User("2222", "cdef", 3, PNB);
        User user4 = new User("3333", "defg", 4, PNB);
        User user5 = new User("4444", "efgh", 5, ABC);

        // demo for user3.
        String accountNO = "3333";
        String password = "abcd";
        
        // Wrong password exception.
        try {
            Bank B = atm1.findAccount(accountNO);
            System.out.println("--------------------------------- \n----- Account found in " + B.bankName
                    + " ------ \n---------------------------------");
            B.authenticate(accountNO, password);

        } catch (accountNotFound acnf) {
            System.out.println("Account Not found.");

        } catch (invalidPassword invld) {
            System.out.println("Invalid Credentials.");
        } finally {
            System.out.println("Session Completed...");
            System.out.println("\n ---- Example 1 completed ---- \n\n\n");
        }

        // Account not found in ATM. ATM does not work for that Bank.
        try {
            Bank B = atm2.findAccount(accountNO);
            System.out.println("--------------------------------- \n----- Account found in " + B.bankName
                    + " ------ \n---------------------------------");
            B.authenticate(accountNO, password);

        } catch (accountNotFound acnf) {
            System.out.println("Account Not found.");

        } catch (invalidPassword invld) {
            System.out.println("Invalid Credentials.");
        } finally {
            System.out.println("Session Completed...");
            System.out.println("\n ---- Example 2 Completed ---- \n\n\n");
        }

        // Successful authentication. followed by -
        // Successful deposit (95% chance) followed by -
        // Successful withdrawl (95% chance) followed by -
        // Change Pin using OTP verification. followed by -
        // Withdrawl of more than 10k using OTP verification

        try {
            password = "defg";
            Bank B = atm1.findAccount(accountNO);
            System.out.println("--------------------------------- \n----- Account found in " + B.bankName
                    + " ------ \n---------------------------------");
            B.authenticate(accountNO, password);

            User U = B.creds.get(accountNO);

            try {
                U.dis();
                U.addBalance(20000, 1);
                U.dis();
                U.withdrawAmount(2000);
                U.dis();
                U.changePass();
                System.out.println(U.getPass());

                U.withdrawAmount(15000);
                U.dis();

            } catch (notSufficientFunds BROKE) {
                System.out.println("Don't have money..");
            } catch (valueNotSameError value) {
                System.out.println("Values dont match.");
            } finally {
                System.out.println("\n ---- Example 3 completed. ---- \n\n\n");
            }

        } catch (accountNotFound acnf) {
            System.out.println("Account Not found...");

        } catch (invalidPassword invld) {
            System.out.println("Invalid Credentials.");
        } finally {
            System.out.println("Session Completed...");
        }

        // infinite loop of working ATM.
        boolean infinity = true;
        int choice;

        while (infinity) {

            System.out.println("");

            // this loop is for atm1 only but can be easilty made avaialible to atm2, 3 etc.
            System.out.println("Enter your accountNo (analogous to inserting a card) : ");
            accountNO = sc.nextLine();
            try {
                Bank B = atm1.findAccount(accountNO);
                System.out.println("--------------------------------- \n----- Account found in " + B.bankName
                        + " ------ \n---------------------------------");
                System.out.println("Enter Password : ");
                String new_password = sc.nextLine();

                B.authenticate(accountNO, new_password);
                User U = B.creds.get(accountNO);

                System.out.println("Enter Choice :      ");
                System.out.println("1 : Withdraw        ");
                System.out.println("2 : Deposit         ");
                System.out.println("3 : Change Password ");
                System.out.println("4 : Exit            ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    try {
                        U.withdrawAmount();
                    } catch (notSufficientFunds BROKE) {
                        System.out.println("Insfficient Funds \n");
                        continue;
                    }
                } else if (choice == 2) {
                    try {
                        U.addBalance();
                    } catch (valueNotSameError Erro) {
                        System.out.println("Amount values dont match. Try again \n");
                        continue;
                    }
                } else if (choice == 3) {
                    U.changePass();
                } else {
                    infinity = false;
                }

            } catch (accountNotFound acnf) {
                System.out.println("Account Not found.");

            } catch (invalidPassword invld) {
                System.out.println("Invalid Credentials.");
            } finally {
                System.out.println("Session Completed...");
            }
        }
        // sc.close();
    }
}