package profile_generator;

import java.util.ArrayList;
import java.util.HashMap;

import debug.ValueProfile;

public class SettingCore {
	HashMap<String,HashMap<String,String>> MonitorProfile;
	HashMap<String,ArrayList<ValueProfile>> MonitorValueProfile;
	HashMap<String,String> dataCoreProfile;
	ValueProfile editingValueProfile;
}
