import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel{

	private static final long serialVersionUID = -2178609851014233560L;
	private JButton[] space;
	private byte[] g;
	private Random r;
	private boolean myturn;
	
	public Board() {
		
		//sets layout to a 3x3 grid like a Tic-Tac-Toe board
		setLayout(new GridLayout(3,3));
		
		//array of buttons as spaces
		space = new JButton[9];
		
		//same size as array of buttons; represents what space is taken by whom
		g = new byte[9];
		
		//initialize each element in the array and add it to the panel
		for(JButton b : space) {
			b = new JButton();
			add(b);
		}
		
		//creates a new Random object and sets its seed to the current system time
		r = new Random(System.currentTimeMillis());
			
		game();
		
	}

	private void game() {
		// TODO Auto-generated method stub
		
		//sets default turn to human
		myturn = false;
		
		/*determines who is X and who is O
		 * x=1 ; o=-1
		 */
		byte whoami = 0;
		if(r.nextBoolean()) {
			whoami = 1;
			myturn = true;
		}
		else
			whoami = -1;
		byte whoru = (byte) (-1*whoami); 
		//end INIT
		
		//game loop
		while(!(isWinner() || isFull())) {
			
			if(myturn) {
				
			}
			
		}
		
		
		
		
		
	}

	private boolean isWinner() {
		//horizontal check
		if(g[0]==g[1]&&g[1]==g[2]&&g[0]!=0)
			return true;
		if(g[3]==g[4]&&g[4]==g[5]&&g[3]!=0)
			return true;
		if(g[6]==g[7]&&g[7]==g[8]&&g[6]!=0)
			return true;
		//vertical check
		if(g[0]==g[3]&&g[3]==g[6]&&g[0]!=0)
			return true;
		if(g[1]==g[4]&&g[4]==g[7]&&g[1]!=0)
			return true;
		if(g[2]==g[5]&&g[5]==g[8]&&g[2]!=0)
			return true;
		//diagonal check
		if(g[0]==g[4]&&g[4]==g[8]&&g[0]!=0)
			return true;
		if(g[2]==g[4]&&g[4]==g[6]&&g[2]!=0)
			return true;
		//nobody is a winner
		return false;
	}

	private boolean isFull() {
		//checks to see if board is full
		for(JButton b : space) {
			if(b.isEnabled()) {
				return false;
			}
		}
		return true;
	}
	
}
