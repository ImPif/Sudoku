import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class CardPanel extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static int width = 100, height = 100;
	private String l;
	private boolean back;
	JTextField text;
	public int count;
	public ArrayList<Integer> useable = new ArrayList<Integer>();

	public CardPanel(boolean background, int c) {
		setPreferredSize(new Dimension(width, height));
		setLayout(new FlowLayout());
		setVisible(true);
		back = background;
		text = new JTextField();
		text.setPreferredSize(new Dimension(50, 50));
		Font font = new Font("SansSerif", Font.BOLD, 20);
		text.setFont(font);
		text.setText("?");
		text.setToolTipText("?");
		text.setOpaque(false);
		text.setBorder(null);
		MouseListener mL = new EventHandle();
		KeyListener kL = new EventHandle();
		text.addMouseListener(mL);
		text.addKeyListener(kL);
		text.addActionListener(this);
		this.add(text);
		count = c;

	}

	public String setToolTip() {
		int row = 0;
		int column = 0;
		ArrayList<Integer> available;
		ArrayList<Integer> available1;
		ArrayList<Integer> available2;
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this == Viewer.gameBoard[i][j]) {
					row = i;
					column = j;
				}
			}
		}
		available = Viewer.rowChecker(row);
		available1 = Viewer.columnChecker(column);

		int panel = 0;
		LargePanel[] l = Viewer.lp;
		for(int i = 0;i<9;i++){
			for(int j = 0;j<9;j++){
				if(this == l[i].getList()[j]){
					panel = i;
				}
			}
		}
		
		available2 = Viewer.largePanelChecker(panel);
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		for (int a = 0; a < available.size(); a++) {
			for (int b = 0; b < available1.size(); b++) {
				if (available.get(a) == available1.get(b)) {
					t.add(available.get(a));
					//temp.add(Integer.toString(available.get(a)));
				}
			}
		}
		for(int a = 0;a<t.size();a++){
			for(int b = 0;b<available2.size();b++){
				if(t.get(a) == available2.get(b)){
					useable.add(available2.get(b));
					temp.add(Integer.toString(available2.get(b)));
				}
			}
		}
		

		StringJoiner an = new StringJoiner(",");
		while (temp.size() > 0) {
			an.add(temp.get(0));
			temp.remove(0);

		}
		String answer = an.toString();
		
		//System.out.println(answer);

		// String test = Integer.toString(4);
		text.setToolTipText(answer);
		return answer;
	}

	public void setBackground(Color c) {
		// this.setBackground(c);
	}

	public int getL() {
		int il = Integer.parseInt(l);
		return il;
	}

	public void setLabel(String label) {
		text.setText(label);
		l = label;
	}

	@Override
	public void setBorder(Border border) {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (back == true) {
			g.setColor(Color.GREEN);

		}
		if (back == false) {
			g.setColor(Color.GRAY);

		}
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int test = Viewer.lastEntered.get((Viewer.lastEntered.size() - 2));
		boolean good = false;
		
		
		if(test != 0){
			for(int i = 0;i<useable.size();i++){
				if(test == useable.get(i)){
					good = true;
				}
			}
			if(good == true){
				setLabel(Integer.toString(test));
			}
			
			if(good != true){
				System.out.println("That number doesn't work");
				this.setLabel("0");
			}
		}
		
		if (test == 0
				&& Viewer.lastEntered.get(Viewer.lastEntered.size() - 2) == 0) {
			System.out.println("Invalid key entered.");
			setLabel("0");
		}
		for(int i = 0; i< 9;i++){
			for(int j = 0;j<9;j++){
				Viewer.gameBoard[i][j].setToolTip();
			}
		}
		boolean win = Viewer.checkWin();
		if(win == true){
			System.out.println("Yay you win...");
		}

	}
	

}