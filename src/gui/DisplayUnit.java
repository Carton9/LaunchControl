package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.*;

public class DisplayUnit extends JPanel {
	JLabel dataName;
	JLabel value;
	String DataTerm;
	//color: Red-Error Yellow-Warning Green-Normal
	public DisplayUnit(String DataTerm) {
		super(new GridLayout(2,1));
		dataName=new JLabel();
		value=new JLabel();
		this.add(dataName);
		this.add(value);
	}
	public void updateInfo(HashMap<String,String> info) {
		String name=info.get("DataTerm").split("#")[1];
		dataName.setText(name);
		String valueInfo=info.get("Value");
		if(valueInfo.contains("%")) {
			value.setText(valueInfo);
		}else if(valueInfo.contains("A")) {
			value.setText(info.get("RawData"));
			if(valueInfo.equals("PASS"))this.setBackground(Color.GREEN);
			if(valueInfo.equals("FAIL"))this.setBackground(Color.RED);
		}else {
			value.setText(valueInfo);
		}
		this.repaint();
	}
}
