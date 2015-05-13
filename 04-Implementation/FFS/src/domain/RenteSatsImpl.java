package domain;

import com.ferrari.finances.dk.bank.InterestRate;

public class RenteSatsImpl implements RenteSats {
	
	public RenteSatsImpl(){
		ir = getInterestRateRef();
	}
	
	private InterestRate ir;
	private double rate;
	
	protected InterestRate getInterestRateRef(){
		return InterestRate.i();
	}

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
