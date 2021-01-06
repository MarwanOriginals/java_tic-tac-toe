import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;


public class View extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel value;
	public View(){
		this.setPreferredSize(new Dimension(180, 30));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		value=new JLabel();
		Font police = new Font("Arial", Font.BOLD, 12);
		value.setFont(police);
		value.setHorizontalAlignment(JLabel.RIGHT);
		value.setPreferredSize(new Dimension(180, 20));
		value.setText("Jouez !");
		value.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(value);
	}
	public void setValue(String value){
		this.value.setText(value);
	}
}
