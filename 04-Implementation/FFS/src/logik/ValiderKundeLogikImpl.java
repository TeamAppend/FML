package logik;

public class ValiderKundeLogikImpl implements ValiderKundeLogik {
	
	/* (non-Javadoc)
	 * @see logik.ValiderKundeLogik#validerCPR(java.lang.String)
	 */
	@Override
	public boolean validerCPR(String s) {
		boolean b = true;
		if (s.length() != 10)
			b = false;
		else if(!s.matches("[0-9]+"))
			b = false;
		return b;
	}

	/* (non-Javadoc)
	 * @see logik.ValiderKundeLogik#validerNavn(java.lang.String)
	 */
	@Override
	public boolean validerNavn(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if(!s.matches("[a-zA-ZæøåÆØÅ ]+"))
			b = false;
		return b;
	}

	/* (non-Javadoc)
	 * @see logik.ValiderKundeLogik#validerAdresse(java.lang.String)
	 */
	@Override
	public boolean validerAdresse(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if(!s.matches("[0-9a-zA-ZæøåÆØÅ. ]+"))
			b = false;
		return b;
	}

	/* (non-Javadoc)
	 * @see logik.ValiderKundeLogik#validerPostnr(java.lang.String)
	 */
	@Override
	public boolean validerPostnr(String s) {
		boolean b = true;
		if (s.length() != 4)
			b = false;
		else if(!s.matches("[0-9]+"))
			b = false;
		return b;
	}

	/* (non-Javadoc)
	 * @see logik.ValiderKundeLogik#validerTelefon(java.lang.String)
	 */
	@Override
	public boolean validerTelefon(String s) {
		boolean b = true;
		if (s.length() != 8)
			b = false;
		else if(!s.matches("[0-9]+"))  //tjekker at s kun indeholder tallene 0-9
			b = false;
		
		return b;

	}
}
