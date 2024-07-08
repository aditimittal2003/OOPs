import java.util.*;
import java.lang.Math;

class Bank {
        // Map of String Username and Personal ID.
        Map<String, User> creds = new HashMap<String, User>();
        String bankName;

        // single value constructor
        Bank(String name) {
                this.bankName = name;
        }

        // add user.
        public void adduser(User U) {
                creds.put(U.getUser(), U);
        }

        // printing all elements of a Bank.
        public void print() {

                for (Map.Entry<String, User> it : this.creds.entrySet()) {
                        System.out.print(it.getKey() + ":");
                        System.out.println(it.getValue().getPass());
                }
        }

        // check if account is present or not by seeing Map value.
        public boolean accNoCheck(String s) {
                if (creds.containsKey(s)) {
                        return true;
                } else {
                        System.out.println("Account not found in " + this.bankName);
                        return false;
                }
        }

        // authenticate for interaction with ATM.
        public boolean authenticate(String providedAccountNo, String providedPassword) throws invalidPassword {
                if (this.creds.get(providedAccountNo).getPass().equals(providedPassword)) {
                        return true;
                } else {
                        throw new invalidPassword();
                        // error in case authentication fails.
                }
        }

        // useless function 95% successful.
        public boolean verifyPayment(int Amount) {
                double x = Math.random();
                if (x > 0.95) {
                        return false;
                }
                return true;
        }
}