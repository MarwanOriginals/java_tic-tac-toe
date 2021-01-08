import javax.swing.*;
import java.awt.Component;

public class Controleur {
	private static final long serialVersionUID = 1L;
	Modele modele;
	View view;
	
	ControleurOperator jcoperateur;
	ControleurChiffre  jcchiffre;
	
	boolean clicOperateur = false;
	boolean update = false;
	private String operation = "";
	double nombre;



	public Controleur(){
		jcchiffre = new ControleurChiffre(this);
		jcoperateur = new ControleurOperator(this);
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
	public Component getChiffre() {
		return jcchiffre;
	}
	public Component getOperateur() {
		return jcoperateur;
	}
	private void calcul(){
		if(operation.equals("+")) {
			nombre = nombre + modele.getDouble();
			changeValue(nombre);
		}
		if(operation.equals("-")) {
			nombre = nombre - modele.getDouble();
			changeValue(nombre);
		}               
		if(operation.equals("*")) {
			nombre = nombre * modele.getDouble();
			changeValue(nombre);
		}       
		if(operation.equals("/")) {
			try{
				nombre = nombre / modele.getDouble();
				changeValue(nombre);
			}catch(ArithmeticException e){
				changeValue(0);
			}
		}
	}	
	public void setChiffre(String str) {
		changeValue("");
		if(update){
			update = false;
		} else {
			if(!modele.getValue().equals("0")){
				if (str.equals(".") )
					if (modele.getValue().contains("."))
						str = modele.getValue();
					else
						str = modele.getValue() + str;
				else	
					str = modele.getValue() + str;
			}
		}
		changeValue(str);
	}
	public void egal() {
		calcul();
		update = true;
		clicOperateur = false;
	}
	public void raz() {
		clicOperateur = false;
		update = true;
		nombre = 0;
		operation = "";
		changeValue(nombre);
	}
	public void plus() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "+";
		update = true;
	}
	public void moins() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "-";
		update = true;
	}
	public void multiplier() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "*";
		update = true;
	}
	public void diviser() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "/";
		update = true;
	}
	private void changeValue(double chiffre1) {
		modele.setValue(String.valueOf(chiffre1));
		view.setValue(modele.getValue());
	}
	private void changeValue(String chiffre) {
		modele.setValue(chiffre);
		view.setValue(modele.getValue());
	}
}
