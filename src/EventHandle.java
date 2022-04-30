import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EventHandle implements MouseListener, KeyListener, MouseMotionListener {
	
	private int num = 0;

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_NUMPAD9 || key == KeyEvent.VK_9){
			num = 9;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD8 || key == KeyEvent.VK_8){
			num = 8;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD7 || key == KeyEvent.VK_7){
			num = 7;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD6 || key == KeyEvent.VK_6){
			num = 6;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD5 || key == KeyEvent.VK_5){
			num = 5;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_4){
			num = 4;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD3 || key == KeyEvent.VK_3){
			num = 3;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD2 || key == KeyEvent.VK_2){
			num = 2;
			Viewer.lastEntered.add(num);
		}
		if(key == KeyEvent.VK_NUMPAD1 || key == KeyEvent.VK_1){
			num = 1;
			Viewer.lastEntered.add(num);
			
		}
		if(key == KeyEvent.VK_BACK_SPACE){
			
		}
		
		if(key == KeyEvent.VK_ENTER){
			if(num <= 9 && num != 0){
			//System.out.println(num);
			//System.out.println(Viewer.lastEntered);
			}
			for(int i = 0; i< 9;i++){
				for(int j = 0;j<9;j++){
					Viewer.gameBoard[i][j].setToolTip();
				}
			}
		}
		else{
			Viewer.lastEntered.add(0);
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	// CardPanel temp = (CardPanel) arg0.getSource();
	// temp.setToolTip();
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
			
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		
	}
	
	
	
	

}
