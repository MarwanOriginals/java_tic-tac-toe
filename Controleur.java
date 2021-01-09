import javax.swing.*;
import java.awt.Component;

public class Controleur {
	private static final long serialVersionUID = 1L;
	Modele modele;
	View view;

	ControleurTicTacToe jcTicTacToe;



	public Controleur(){
		jcTicTacToe = new ControleurTicTacToe(this);
		modele = new Modele();
	}

	public void setPlayerTurn(String coordinates)
	{
		modele.playerTurn(coordinates);
	}

	public String setComputerTurn()
	{
		String loading_dot = "";
		for (int i = 0; i <= 3; i++)
		{
			String finalLoading_dot = loading_dot;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					changeValue("L'ordinateur réfléchi" + finalLoading_dot);
				}
			});
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			loading_dot += ".";
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				changeValue("Jouez !");
			}
		});

		return modele.computerTurn();
	}

	public int isGameFinish()
	{
		if (modele.isWon())
		{
			if (modele.isPlayerWon())
			{
				changeValue("Bravo ! Vous avez gagné !");
			}
			if (modele.isComputerWon())
			{
				changeValue("L'ordinateur a gagné.");
			}
			if (modele.isComputerWon() == false && modele.isPlayerWon()== false) {
				changeValue("Egalité.");
				return 2;
			}
			return 1;
		}
		return 0;
	}

	public void resetGame()
	{
		modele.resetGame();
	}

	public void setModele(Modele modele){
		this.modele=modele;
	}
	public void setView(View view) {
		this.view=view;
	}
	public Component getTicTacToe() {
		return jcTicTacToe;
	}

	private void changeValue(String message) {
		view.setValue(message);
	}
}
