import javax.swing.JFrame;

public class TicRunner extends JFrame{
	
	/***
	 * Originally written by Haylee Thomas
	 * 7 December 2017 ©
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TicRunner();	
	}
	
	public TicRunner() {
		
		//sets window size
		setSize(300,300);
		
		//set close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//puts the window in the middle of the screen at start
		setLocationRelativeTo(null);
		
		//adds Board object to frame (Board extends JPanel)
		add(new Board());
		
		//open window
		setVisible(true);
		
	}
	

}
