import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;


public class ControleurTicTacToe extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	String[] tab_string = {"", "", "", "", "", "", "", "", ""};
	JButton[] tab_button = new JButton[tab_string.length];
	private Dimension dim = new Dimension(50, 50);
	private Controleur controleur;
	public ControleurTicTacToe(Controleur controleur){
		this.controleur=controleur;
		setPreferredSize(new Dimension(165, 225));
		int x = 0;
		int y = 0;
		for(int i = 0; i < tab_string.length; i++) {
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dim);
			add(tab_button[i]);
			tab_button[i].addActionListener(this);
			tab_button[i].setName(String.valueOf(x) + String.valueOf(y));
			if(x == 2)
			{
				x = 0;
				y++;
			}
			else
				x++;
		}
	}
	public void actionPerformed(ActionEvent e) {
		JButton button = ((JButton)e.getSource());
		String raw_coordinates = button.getName();
		controleur.setPlayerTurn(raw_coordinates);
		button.setEnabled(false);
		button.setText("X");

		if (controleur.isGameFinish() == 1)
		{
			for(int i = 0; i < tab_string.length; i++)
			{
					tab_button[i].setEnabled(false);
			}
			return;
		}
		else if (controleur.isGameFinish() == 2)
		{
			controleur.resetGame();
			for(int i = 0; i < tab_string.length; i++)
			{
				tab_button[i].setEnabled(true);
				tab_button[i].setText("");
			}
			return;
		}


		new Thread(new Runnable() {
			public void run()
			{
				for(int i = 0; i < tab_string.length; i++)
				{
					tab_button[i].setEnabled(false);
				}
				String computerChoice = controleur.setComputerTurn();
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {


							for(int i = 0; i < tab_string.length; i++) {
								if (tab_button[i].getName().equals(computerChoice))
								{
									tab_button[i].setEnabled(false);
									tab_button[i].setText("O");
								}
							}

							if (controleur.isGameFinish() == 1)
							{
								for(int i = 0; i < tab_string.length; i++)
								{
									tab_button[i].setEnabled(false);
								}
								return;
							}
							else if (controleur.isGameFinish() == 2)
							{
								controleur.resetGame();
								for(int i = 0; i < tab_string.length; i++)
								{
									tab_button[i].setEnabled(true);
									tab_button[i].setText("");
								}
								return;
							}


						}
					});
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				} catch (InvocationTargetException invocationTargetException) {
					invocationTargetException.printStackTrace();
				}
				for(int i = 0; i < tab_string.length; i++)
				{
					if (tab_button[i].getText().equals("") && controleur.isGameFinish() == 0)
						tab_button[i].setEnabled(true);
				}
			}
		}).start();
	}
}
