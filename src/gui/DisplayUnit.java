package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.*;

public class DisplayUnit extends JPanel {
	JLabel dataName;
	JLabel value;
	String DataTerm;
	DisplayUnit next;
	//color: Red-Error Yellow-Warning Green-Normal
	public DisplayUnit(String DataTerm) {
		super(new GridLayout(2,1));
		dataName=new JLabel();
		value=new JLabel();
		this.add(dataName);
		this.add(value);
		this.DataTerm=DataTerm;
	}
	public String getDataType() {
		return DataTerm.split("#")[0];
	}
	public String getDataName() {
		return DataTerm.split("#")[1];
	}
	public void updateInfo(HashMap<String,String> info) {
		if(!DataTerm.equals(info.get("DataTerm"))) {
			if(next!=null) {
				next.updateInfo(info);
				return;
			}
			return;
		}
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
	public void addNext(DisplayUnit nextU) {
		if(next!=null)next.addNext(nextU);
		else next=nextU;
	}
	public void remove(String DataTerm) {
		if(next!=null&&DataTerm.equals(next.getDataType()+"#"+next.getDataName())) {
			next=next.breakChain();
		}
	}
	public DisplayUnit breakChain() {
		return next;
	}
}
