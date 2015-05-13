package domain;

public class CPRnummerImpl implements CPRnummer {
	private int CPR_id;
	private String CPRnummer;
	
	
	@Override
	public int getCPR_id() {
		return CPR_id;
	}
	
	@Override
	public void setCPR_id(int cPR_id) {
		CPR_id = cPR_id;
	}

	@Override
	public String getCPRnummer() {
		return CPRnummer;
	}

	@Override
	public void setCPRnummer(String cPRnummer) {
		CPRnummer = cPRnummer;
	}
}
