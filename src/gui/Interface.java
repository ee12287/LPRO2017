package gui;

import logic.*;
import cli.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class Interface {

	private JFrame frmHereBeDragons;
	private JTextField numDragons;
	JTextArea board = new JTextArea();
	JLabel console = new JLabel("Console messages");
	JButton btnUp = new JButton("Up");
	JButton btnLeft = new JButton("Left");
	JButton btnDown = new JButton("Down");
	JButton btnRight = new JButton("Right");
	int numberOfGames = 0;
	
	
	Mapa mapaObject = Mapa.getInstance();
	String [][] mapa = mapaObject.getMapa();
	Movimento movimentoObject = new Movimento();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmHereBeDragons.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}
//--------------------------------------------------------------------------------------------------------------------------	
	private void printMap() {
		
		board.setText("\n");

		
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa.length; j++)
				board.append(mapa[i][j]);
			board.append("\n");
		}
	}
	
	
	
	private int insertElements() {
		
		//System.out.print(numDragons.getText());
		if(numDragons.getText().equals("")) {
			console.setText("Numero de dragões inválido . 0 < Nº dragões < 5");
			return 1;		
		}
		
		int num = Integer.valueOf(numDragons.getText());
		
		if(num < 1 || num > 4 ) {
				console.setText("Numero de dragões inválido . 0 < Nº dragões < 5");
				return 1;		
			}
		console.setText("Console Messages");
		//mapaObject = null;
		mapaObject = Mapa.getInstance();
		mapaObject.setNumDragons(num);
		mapaObject.InsereDragoes();
		mapaObject.ColocaElementos();
		return 0;
		
	}
	
	
	
	private int estadoJogo() {
		
		if (mapaObject.getHeroObject().getWin() == 1) {
			console.setText("Ganhou");
			return 1;
		}
			
		if (mapaObject.getHeroObject().getWin() == -1) {
			mapaObject.printMap();
			console.setText("Perdeu");
			return 1;
		}
		
		return 0;
		
	}
	
	
	
	private void stateButtons(int active) { // active 0 desactiva - active 1 - activa
		if(active == 1) {
			btnUp.setEnabled(true);
			btnDown.setEnabled(true);
			btnLeft.setEnabled(true);
			btnRight.setEnabled(true);
		}
	
		else if(active == 0) {
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
		}
	}
	
	private void removeElements() {
		mapaObject.removeElements();
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHereBeDragons = new JFrame();
		frmHereBeDragons.setTitle("Here Be Dragons");
		frmHereBeDragons.setBounds(100, 100, 834, 580);
		frmHereBeDragons.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmHereBeDragons.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		GridBagConstraints gbc_lblNumberOfDragons = new GridBagConstraints();
		gbc_lblNumberOfDragons.gridwidth = 7;
		gbc_lblNumberOfDragons.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfDragons.gridx = 2;
		gbc_lblNumberOfDragons.gridy = 1;
		frmHereBeDragons.getContentPane().add(lblNumberOfDragons, gbc_lblNumberOfDragons);
		
		numDragons = new JTextField();
		GridBagConstraints gbc_numDragons = new GridBagConstraints();
		gbc_numDragons.gridwidth = 5;
		gbc_numDragons.insets = new Insets(0, 0, 5, 5);
		gbc_numDragons.fill = GridBagConstraints.HORIZONTAL;
		gbc_numDragons.gridx = 10;
		gbc_numDragons.gridy = 1;
		frmHereBeDragons.getContentPane().add(numDragons, gbc_numDragons);
		numDragons.setColumns(10);
		
		JLabel lblDragonMovement = new JLabel("Dragon Movement");
		GridBagConstraints gbc_lblDragonMovement = new GridBagConstraints();
		gbc_lblDragonMovement.gridwidth = 7;
		gbc_lblDragonMovement.insets = new Insets(0, 0, 5, 5);
		gbc_lblDragonMovement.gridx = 2;
		gbc_lblDragonMovement.gridy = 2;
		frmHereBeDragons.getContentPane().add(lblDragonMovement, gbc_lblDragonMovement);
		

	
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Random", "Outro"}));
		comboBox.setToolTipText("0");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 5;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 10;
		gbc_comboBox.gridy = 2;
		frmHereBeDragons.getContentPane().add(comboBox, gbc_comboBox);

		//New Game button-------------------------------------------------------------------------------------------	
		board.setColumns(10);
		board.setRows(10);
		board.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 29));
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				numberOfGames++;
				
				if (numberOfGames == 1) {	
					if(insertElements() == 0) {
							printMap();
							stateButtons(1);
					}
				}
				
				else {
					removeElements();
					if(insertElements() == 0) {
						printMap();
						stateButtons(1);
					}
				}
				
			}
		
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 18;
		gbc_btnNewGame.gridy = 2;
		frmHereBeDragons.getContentPane().add(btnNewGame, gbc_btnNewGame);
		
		
		board.setTabSize(10);
		board.setText("Loading...");
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.gridwidth = 13;
		gbc_board.gridheight = 7;
		gbc_board.insets = new Insets(0, 0, 5, 5);
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.gridx = 3;
		gbc_board.gridy = 3;
		frmHereBeDragons.getContentPane().add(board, gbc_board);
		
		
		// Up button-----------------------------------------------------------------
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movimentoObject.movement(-1, 0);
				mapa = mapaObject.getMapa();
				//board.setText("up");
				printMap();
				
				if(estadoJogo() != 0)
					stateButtons(0);
				
			}
		});
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 18;
		gbc_btnUp.gridy = 5;
		frmHereBeDragons.getContentPane().add(btnUp, gbc_btnUp);
		
		// Left button-----------------------------------------------------------------
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movimentoObject.movement(0, -1);
				mapa = mapaObject.getMapa();
				//board.setText("left");
				printMap();
				
				if(estadoJogo() != 0)
					stateButtons(0);
			}
		});
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 17;
		gbc_btnLeft.gridy = 6;
		frmHereBeDragons.getContentPane().add(btnLeft, gbc_btnLeft);
		
		// Right button-----------------------------------------------------------------
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movimentoObject.movement(0, 1);
				mapa = mapaObject.getMapa();
				//board.setText("right");
				printMap();
				
				if(estadoJogo() != 0)
					stateButtons(0);
			}
		});
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.gridx = 19;
		gbc_btnRight.gridy = 6;
		frmHereBeDragons.getContentPane().add(btnRight, gbc_btnRight);
		
		// Down button-----------------------------------------------------------------
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				movimentoObject.movement(1, 0);
				mapa = mapaObject.getMapa();
				//board.setText("down");
				printMap();
				
				if(estadoJogo() != 0)
					stateButtons(0);

			}
		});
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.gridx = 18;
		gbc_btnDown.gridy = 7;
		frmHereBeDragons.getContentPane().add(btnDown, gbc_btnDown);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 8;
		frmHereBeDragons.getContentPane().add(textArea, gbc_textArea);
		
		// Exit button-----------------------------------------------------------------
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 18;
		gbc_btnExit.gridy = 9;
		frmHereBeDragons.getContentPane().add(btnExit, gbc_btnExit);
		
	
		GridBagConstraints gbc_console = new GridBagConstraints();
		gbc_console.gridwidth = 14;
		gbc_console.insets = new Insets(0, 0, 5, 5);
		gbc_console.gridx = 2;
		gbc_console.gridy = 11;
		frmHereBeDragons.getContentPane().add(console, gbc_console);
	}

}
