package bank.manager.data.models;

import bank.manager.data.util.Constants;

public class Account{
       	private int balance;
	public String userId,dateOpened,fullName,idNumber, accountType, phoneNumber, email;
        
        public Account(){   
        }
        public void init(String userId,String fullName, String idNumber,String accountType,String phoneNumber,String email,int balance,String dateOpened){
            this.userId =userId;
            this.fullName = fullName;
            this.accountType = accountType;
            this.dateOpened =dateOpened;
            this.idNumber = idNumber;
            this.email =email;
            this.phoneNumber = phoneNumber;
            this.balance =balance;
        }
	public Account(double initalDeposit,String fullName, String idNumber,String accountType,String phoneNumber,String email )  throws InsufficientFundsException {
            if(initalDeposit < 100) {
                    throw new InsufficientFundsException("Insufficient funds to open account. Funds offered " + fullName + " is less than required $100.00.");
            }
            this.balance = (int) (initalDeposit * 100);
            this.dateOpened =Constants.dayToday();
            this.fullName = fullName;
            this.accountType = accountType;
            this.idNumber = idNumber;
            this.email =email;
            this.phoneNumber = phoneNumber;
	}
   
	public double getBalance() { return balance / 100.00; }
	public String getDateOpened() { return dateOpened; }
	public double deposit(double d) throws BadValueException {
		if(d <= 0) throw new BadValueException("Bad deposit value given: " + d);
		balance += Math.round((float) d*100);
		return balance / 100.0;
	}

	public double withdrawal(double w) throws BadValueException, InsufficientFundsException  {
		if (w <= 0) throw new BadValueException("Bad withdrawal value given: " + w);
		if(w > balance / 100.00) throw new InsufficientFundsException("Insufficient funds for" 
				+ " withdrawal: balance: " 
				+ balance / 100.00 + " withdrawal: " + w);
		balance -= Math.round((float) w*100);
		return balance / 100.0;
	}

	public String getFullName() { return fullName; }
	

        @Override
	public String toString() {
            return "<#>"+fullName+"<#>"+idNumber+"<#>"+accountType+"<#>"+phoneNumber+"<#>"+email+"<#>"+balance+"<#>"+dateOpened;
	}
        public String shortString() {
            return "<#>"+fullName+"<#>"+idNumber+"<#>"+accountType;
	}
}
