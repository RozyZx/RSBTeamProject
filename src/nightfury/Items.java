package nightfury;

import javax.faces.bean.ManagedBean;

public class Items {
	private String responLabel, responValue;
	
	Items(String label, String value){
		responLabel= label;
		responValue = value;
	}
	
	public String getResponLabel() {
		return responLabel;
	}
	
	public String getResponValue() {
		return responValue;
	}
}
