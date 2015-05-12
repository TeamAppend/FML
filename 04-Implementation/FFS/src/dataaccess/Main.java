package dataaccess;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		KreditvaerdighedImpl kvi = new KreditvaerdighedImpl();
		kvi.setKreditvaerdighed("1234567891", new CallBack(){
			@Override
			public void onRequestComplete(){
				System.out.println(kvi.getkvAcceptabel());
				System.out.println(kvi.getTillaegspoint());

			}
		});
	}

}
