package dataaccess;

import com.ferrari.finances.dk.bank.InterestRate;

public class RenteSatsImpl implements RenteSats {
	private InterestRate ir = InterestRate.i();
	private double rate;
	

	@Override
	public void setRenteSats(CallBack callBack){
		Thread thread = new Thread(){
			public void run(){
				rate = ir.todaysRate();
				callBack.onRequestComplete();
				
			};
		};
		thread.start();
	}	

	@Override
	public double getRenteSats(){
		return rate;
	}
}
