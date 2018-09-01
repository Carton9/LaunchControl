import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.carton.common.util.ConfigAccesser;
import org.jdom.JDOMException;

public class DataCore {
	ConfigAccesser dataFormatConfig;
	ConfigAccesser generalConfig;
	HashMap<String,HashMap<String,String>> dataItems;
	HashMap<String,String> generalSetting;
	public DataCore() throws FileNotFoundException, JDOMException, IOException {
		File localPath=new File("").getAbsoluteFile();
		File dataFormatFile=new File(localPath.getAbsolutePath()+File.separator+"data_format.info");
		File generalConfigFile=new File(localPath.getAbsolutePath()+File.separator+"general_config.info");
		dataFormatConfig=new ConfigAccesser(dataFormatFile);
		generalConfig=new ConfigAccesser(generalConfigFile);
		loadDataItems();
	}
	public void loadDataItems() {
		ArrayList<String> items=new ArrayList<String>();
		dataFormatConfig.loadListFromElement("DataItems", items);
		dataItems=new HashMap<String,HashMap<String,String>>();
		for(String i:items) {
			HashMap<String,String> dataFormat=new HashMap<String,String>();
			dataFormatConfig.loadMapFromElement(i, dataFormat);
			dataItems.put(i, dataFormat);
		}
		///////////////////////////////////////////
		generalConfig.loadMapFromElement("GeneralSetting", generalSetting);
	}
	
	
}
