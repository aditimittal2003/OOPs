// import java.util.*;

class ATM {
        int number_of_banks = 0;
        Bank bank_list[] = new Bank[100];
        // record of every Bank with connections to the ATM.

        // add Bank to ATM.
        protected void addBank(Bank B) {
                bank_list[number_of_banks] = B;
                this.number_of_banks++;
        }

        // iterate over all Connected Banks with ATM and find user in them.
        public Bank findAccount(String account_number) throws accountNotFound {
                for (int i = 0; i < number_of_banks; ++i) {
                        if (bank_list[i].accNoCheck(account_number)) {
                                return bank_list[i];
                        }
                }
                throw new accountNotFound();
                // if not found throws appropriate error.
        }
}