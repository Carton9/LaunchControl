import java.io.Serializable;
import java.util.HashMap;

public class ValueProfile implements Serializable {
	private String type;
	private String name;
	private String display;
	private double highLimited;
	private double lowLimited;
	private double passValue;
	int order;
	public ValueProfile(String type,String name,String display,int order,double high,double low,double pass) {
		this.type=type;
		this.name=name;
		this.display=display;
		this.highLimited=high;
		this.lowLimited=low;
		this.passValue=pass;
		this.order=order;
	}
	public static ValueProfile compileProfileFromString(String profile) {
		ValueProfile vp;
		String type="";
		String name="";
		String display="";
		double highLimited=0;
		double lowLimited=0;
		double passValue=0;
		int order=0;
		if(profile==null)return null;
		String values[]=profile.split("$");
		if(values[0].equals(""))return null;
		type=values[0];
		if(values[1].equals(""))return null;
		name=values[1];
		if(values[2].equals(""))return null;
		try {
			order=Integer.parseInt(values[4]);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		if(values[3].equals(""))return null;
		if(values[3].equals("VALUE"));
		if(values[3].equals("PASS")) {
			try {
				passValue=Double.parseDouble(values[4]);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		if(values[3].equals("H/L")) {
			try {
				highLimited=Double.parseDouble(values[4]);
				lowLimited=Double.parseDouble(values[5]);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		vp=new ValueProfile(type, name, display, order, passValue, passValue, passValue);
		return vp;
	}
	public String compileProfileToString() {
		String result="";
		if(type!=null)result+=type;result+="$";
		if(name!=null)result+=name;result+="$";
		if(display!=null)result+=display;result+="$";
		if(order>-1)result+=order;result+="$";
		result+=highLimited;result+="$";
		result+=lowLimited;result+="$";
		result+=passValue;
		return result;
	}
	public HashMap<String,String> compileData(double[] datas){
		double data=datas[this.order];
		String value="";
		if(this.display.equals("H/L")) {
			value+=((data/(this.highLimited-this.lowLimited))*100)+"%";
		}else if(this.display.equals("PASS")) {
			value=(data>=this.passValue)?"PASS":"FAIL";
		}else if(this.display.equals("VALUE")){
			value=data+"";
		}
		HashMap<String,String> result=new HashMap<String,String>();
		result.put("DataTerm", this.type+"#"+this.name);
		result.put("Value", value);
		result.put("RawData", data+"");
		return result;
	}
}
