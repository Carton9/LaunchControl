package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.*;

public class DisplayUnit extends JPanel {
	JLabel dataName;
	JLabel value;
	String dataTerm;
	//color: Red-Error Yellow-Warning Green-Normal
	public DisplayUnit(String dataTerm) {
		super(new GridLayout(1,2));
		dataName=new JLabel();
		value=new JLabel();
		this.add(dataName);
		this.add(value);
		this.dataTerm=dataTerm;
	}
	public void updateInfo(HashMap<String,String> info) {
		if(!info.get("DataTerm").equals(dataTerm))return;
		String name=dataTerm.split("#")[1];
		dataName.setText(name);
		String valueInfo=info.get("Value");
		if(valueInfo.contains("%")) {
			value.setText(valueInfo);
			this.setBackground(Color.WHITE);
		}else if(valueInfo.contains("A")) {
			value.setText(info.get("RawData"));
			if(valueInfo.equals("PASS"))this.setBackground(Color.GREEN);
			if(valueInfo.equals("FAIL"))this.setBackground(Color.RED);
		}else {
			value.setText(valueInfo);
			this.setBackground(Color.WHITE);
		}
		this.repaint();
	}
}
