package de.bejoschgaming.orderofelements.main;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private Label label = null;
	
	public Frame() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setName("Prototyp - V0.0");
		this.setLocationRelativeTo(null);
		this.setLocation(0, 0);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//frame.setSize(1280, 720);
		
		this.label = new Label(this);
		
		this.requestFocus();
		
	}
	
	public Label getLabel() {
		return label;
	}
	
}
