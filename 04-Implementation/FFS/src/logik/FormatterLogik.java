package logik;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class FormatterLogik {
	
	public static String formatTwoDigits(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }
	
	public static String dotSeperator(Number n){
		
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setGroupingSeparator('.');

		DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
		return formatter.format(n);
	}
	

}