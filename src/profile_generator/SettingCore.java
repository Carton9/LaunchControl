package profile_generator;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextField;

import debug.ValueProfile;

public class SettingCore {
	HashMap<String,HashMap<String,String>> MonitorProfile;
	HashMap<String,ArrayList<ValueProfile>> MonitorValueProfile;
	HashMap<String,String> dataCoreProfile;
	HashMap<String,ValueProfile> editingValueProfile;
	private final static SettingCore core=new SettingCore();
	SettingCore(){
		MonitorProfile=new HashMap<String,HashMap<String,String>> ();
		MonitorValueProfile=new HashMap<String,ArrayList<ValueProfile>> ();
		dataCoreProfile=new HashMap<String,String> ();
		editingValueProfile=new HashMap<String,ValueProfile> ();
	}
	public static SettingCore getCore() {
		return core;
	}
	
	
}
