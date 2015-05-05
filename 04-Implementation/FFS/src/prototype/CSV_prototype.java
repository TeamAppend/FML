package prototype;

public class CSV_prototype {
	StringBuffer sb = new StringBuffer();
	
	public void append(String s){
		sb.append(";" + s);
	}
}
