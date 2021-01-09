import java.awt.BorderLayout;

import javax.swing.JFrame;


public class TicTacToeGame extends JFrame {
	private static final long serialVersionUID = 1L;

	public TicTacToeGame(){
		this.setSize(180, 240);
		this.setTitle("Tic-Tac-Toe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		initComposant();
		this.setVisible(true);
	}

	private void initComposant(){
		Controleur controleur = new Controleur();
		
		Modele modele = new Modele();
		View view = new View();
		
		controleur.setModele(modele);
		controleur.setView(view);
		
		this.getContentPane().add(view, BorderLayout.NORTH);
		this.getContentPane().add(controleur.getTicTacToe(), BorderLayout.WEST);
	}
}
