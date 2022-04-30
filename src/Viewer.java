import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Viewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Container gameWindow;
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	private JMenu drawMenu;

	public static LargePanel[] lp = new LargePanel[9];
	public static CardPanel[][] gameBoard = new CardPanel[9][9];
	public static ArrayList<Integer> lastEntered = new ArrayList<Integer>();
	public static int lPCounter = 0;
	public static int counter = 0;
	public static int[][] asdf = new int[9][9];

	private static boolean win = true;
	public static boolean rowCheck = false;
	public static boolean colomnCheck = false;

	/**
	 * Contructor. Creates JFrame and adds 9 large panels to frame
	 */
	private Viewer() {
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Sudoku");
		setLayout(new GridLayout(3, 3));
		buildMenu();

		Container c = getContentPane();
		LargePanel temp;
		for (int i = 0; i < 9; i++) {
			if (i == 1 || i == 3 || i == 5 || i == 7) {
				temp = new LargePanel(true);
			} else {
				temp = new LargePanel(false);
			}
			c.add(temp);
			lp[i] = temp;
		}
		create2DArray();
		// gameBoard[6][5].setLabel("works");

		setVisible(true);

	}

	public void create2DArray() {
		int temp = 0;
		for (int wow = 0; wow < 3; wow++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 0) {
						gameBoard[counter][j] = lp[temp].getList()[j];
					}
					if (i == 1) {
						gameBoard[counter][j + 3] = lp[temp + 1].getList()[j];
					}
					if (i == 2) {
						gameBoard[counter][j + 6] = lp[temp + 2].getList()[j];
					}
				}

			}
			counter++;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 0) {
						gameBoard[counter][j] = lp[temp].getList()[j + 3];
					}
					if (i == 1) {
						gameBoard[counter][j + 3] = lp[temp + 1].getList()[j + 3];
					}
					if (i == 2) {
						gameBoard[counter][j + 6] = lp[temp + 2].getList()[j + 3];
					}
				}

			}
			counter++;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 0) {
						gameBoard[counter][j] = lp[temp].getList()[j + 6];
					}
					if (i == 1) {
						gameBoard[counter][j + 3] = lp[temp + 1].getList()[j + 6];
					}
					if (i == 2) {
						gameBoard[counter][j + 6] = lp[temp + 2].getList()[j + 6];
					}
				}

			}
			counter++;
			temp = temp + 3;
		}

	}

	/**
	 * \ sets cards background
	 * 
	 * @param c
	 */
	private static void setCardBackground(Color c) {
		for (int i = 1; i < 9; i = i + 2) {
			lp[i].setBackgroundColor(c);
		}
	}

	/**
	 * loads a game based on text file that is chosen in the JMenu
	 * 
	 * @param f
	 */
	public static void loadGameBoard(File f) {

		try {
			Scanner sc = new Scanner(f);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					asdf[i][j] = sc.nextInt();
				}

			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("This game is invalid.");
			e.printStackTrace();
		}

	}

	/**
	 * clears the board and sets all squares to "?"
	 */
	public static void clearBoard() {
		for (int i = 0; i < 9; i++) {
			CardPanel[] temp = lp[i].getList();
			for (int j = 0; j < 9; j++) {
				temp[j].setLabel("?");
			}
		}
	}

	/**
	 * sets board based on what game you loaded
	 */
	public static void setBoard() {
		for (int i = 0; i < 9; i++) {
			CardPanel[] temp = lp[i].getList();
			for (int j = 0; j < 9; j++) {
				temp[j].setLabel(Integer.toString(asdf[i][j]));
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				gameBoard[i][j].setToolTip();
			}
		}

	}

	/**
	 * builds menu bar to be added to frame
	 */
	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("New");

		JMenu menuItem1 = new JMenu("Read");

		JMenuItem menuItem2 = new JMenuItem("Save");

		JMenuItem menuItem3 = new JMenuItem("Exit");

		JMenuItem subMenuFile = new JMenuItem("Pre-made board 1");
		JMenuItem subMenuFile1 = new JMenuItem("Pre-made board 2");
		JMenuItem subMenuFile2 = new JMenuItem("Pre-made board 3");
		JMenuItem subMenuItem = new JMenuItem("Read in board");

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearBoard();
			}

		});
		subMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					int result = fileChooser.showOpenDialog(menuItem2);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					}
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					loadGameBoard(file);
					setBoard();
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showSaveDialog(menuItem2);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				
				try {
					PrintWriter writer = new PrintWriter(file);
					for(int i = 0;i<9;i++){
						for(int j = 0;j<9;j++){
							writer.write(Integer.toString(lp[i].getList()[j].getL()) + " ");
						}writer.println();
					}
					
					writer.close();
					System.out.println("Your file has been saved to: " + fileChooser.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}

		});
		menuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(NORMAL);
			}

		});

		subMenuFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File game1 = new File("game1.txt");
				loadGameBoard(game1);
				setBoard();

			}

		});
		subMenuFile1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File game2 = new File("game2.txt");
				loadGameBoard(game2);
				setBoard();

			}

		});
		subMenuFile2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File game3 = new File("game3.txt");
				loadGameBoard(game3);
				setBoard();

			}

		});

		menuItem1.add(subMenuFile);
		menuItem1.add(subMenuFile1);
		menuItem1.add(subMenuFile2);
		menuItem1.add(subMenuItem);

		fileMenu.add(menuItem);
		fileMenu.add(menuItem1);
		fileMenu.add(menuItem2);
		fileMenu.add(menuItem3);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

	}

	/**
	 * checks to make sure there are no duplicates in row
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static ArrayList<Integer> rowChecker(int x) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int i = 1;
		while (i < 10) {
			temp.add(i);
			i++;
		}
		for (int j = 0; j < 9; j++) {
			for (int a = 1; a < temp.size(); a++) {
				if (temp.get(a) == gameBoard[x][j].getL()) {
					temp.remove(a);

				}
			}
		}

		return temp;
	}

	/**
	 * checks to make sure there are no duplicates in column
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static ArrayList<Integer> columnChecker(int x) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int i = 1;
		while (i < 10) {
			temp.add(i);
			i++;
		}
		for (int j = 0; j < 9; j++) {
			for (int a = 1; a < temp.size(); a++) {
				if (temp.get(a) == gameBoard[j][x].getL()) {
					temp.remove(a);

				}
			}
		}

		return temp;
	}

	public static ArrayList<Integer> largePanelChecker(int x) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int i = 1;
		while (i < 10) {
			temp.add(i);
			i++;
		}

		CardPanel[] t = lp[x].getList();
		for (int j = 0; j < t.length; j++) {
			for (int a = 0; a < temp.size(); a++) {
				if (t[j].getL() == temp.get(a)) {
					temp.remove(a);
				}
			}
		}

		return temp;
	}

	/**
	 * checks to see if the board has been won
	 * 
	 * @return
	 */
	public static boolean checkWin() {
		int temp = 0;
		for(int i = 0;i<9;i++){
			for(int j = 0;j<9;j++){
				temp = temp + gameBoard[i][j].getL();
			}
			if(temp ==45){
				win = true;
			}
			if(temp != 45){
				win = false;
				break;
			}
			temp = 0;
		}
		return win;
	}

	public void win(){
		
	}

	public static void main(String[] args) {
		Viewer game = new Viewer();

	}

}
