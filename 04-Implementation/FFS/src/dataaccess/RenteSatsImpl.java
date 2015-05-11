package dataaccess;

import com.ferrari.finances.dk.bank.InterestRate;

public class RenteSatsImpl {
	InterestRate ir = InterestRate.i();
	
	public double getRenteSats(){
		double rate = ir.todaysRate();
		return rate;
	}	
}
