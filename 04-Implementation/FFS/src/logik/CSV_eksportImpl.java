package logik;

import javax.swing.table.TableModel;

public class CSV_eksportImpl implements CSV_eksport {
	private StringBuffer sb = new StringBuffer();
	private TableModel t;
	private int cCount = 0;
	private int rCount = 0;
	
	CSV_eksportImpl(TableModel t){
		this.t = t;
		cCount = t.getColumnCount();
		rCount = t.getRowCount();
	}
	
	public String eksport(){
		tableToStringBuffer();
		String s = sbToString();
		return s;
	}
	
	public void tableToStringBuffer(){
		for(int i = 0; i < rCount; i++){
			for(int x = 0; x < cCount - 1; x++){
				append((String) t.getValueAt(x, i));
			}
			lastValue((String) t.getValueAt(cCount, i));
		}
	}
	
	public void append(String s){
		sb.append(s + ",");
	}

	public void lastValue(String s){
		sb.append(s + " \n");
	}
	
	public String sbToString(){
		return sb.toString();
	}
}
