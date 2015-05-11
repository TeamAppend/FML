package dataaccess;

import com.ferrari.finances.dk.bank.InterestRate;

public class RenteSatsImpl implements RenteSats {
	private InterestRate ir = InterestRate.i();
	private double rate;
	
	public double getRenteSats(){
		rate = ir.todaysRate();
		return rate;
	}	
}
