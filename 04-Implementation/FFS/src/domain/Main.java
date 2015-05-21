package domain;


public class Main {

	public static void main(String[] args) throws InterruptedException {

		
		KreditværdighedImpl kvi = new KreditværdighedImpl();
		kvi.setKreditværdighed("1234567891", new CallBack(){
			@Override
			public void onRequestComplete(){
				System.out.println(kvi.getkvAcceptabel());
				System.out.println(kvi.getTillægspoint());

			}
		});
		
		
		RenteSats ri = new RenteSatsImpl();
		ri.setRenteSats(new CallBack(){
			@Override
			public void onRequestComplete(){
				System.out.println(ri.getRenteSats());
			}
		});		
	}
}
