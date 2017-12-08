import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel{

	private static final long serialVersionUID = -2178609851014233560L;
	private Space[] space;
	private Random r;
	private boolean myturn;
	private int turn;
	private byte whoami, whoru;
	
	public Board(JFrame ref) {
		super();
		
		//sets layout to a 3x3 grid like a Tic-Tac-Toe board
		setLayout(new GridLayout(3,3));
		
		//array of buttons as spaces
		space = new Space[9];
		
		//initialize each element in the array and add it to the panel
		for(int b=0; b<space.length; b++) {
			space[b] = new Space();
			add(space[b]);
		}
		
		//creates a new Random object and sets its seed to the current system time
		r = new Random(System.currentTimeMillis());
		
		turn = -1;
		
		//the game
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				game();
			}
		});
		t.start();
		
	}

	private void game() {
		// TODO Auto-generated method stub
		
		//sets default turn to human
		myturn = false;
		
		//clears data and adds first state
		BoardState.clearState();
		BoardState.updateState(space);
		
		/*determines who is X and who is O
		 * x=1 ; o=-1
		 */
		whoami = 0;
		if(r.nextBoolean()) {
			whoami = 1;
			myturn = true;
		}
		else
			whoami = -1;
		
		//sets human to opposite of computer
		whoru = (byte) (-1*whoami);
		
		for(Space y : space) {
			y.setText("");
			y.myVal = 0;
		}
		
		for(int x=0; x<space.length; x++) {
			space[x].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(!myturn) {
						Space clicked = (Space) e.getSource();
						clicked.myVal = whoru;
						clicked.setEnabled(false);
						myturn = true;
						
						if(whoru>0)
							clicked.setText("X");
						else
							clicked.setText("O");
						
						BoardState.updateState(space);
					}
				}
			});
		}
		
		//enables all spaces
		setAllEnabled(true);
		
		/***
		 * end INIT
		*/
		
		//game loop
		int st = 0;
		while(true) {
			
			st = isWinner();
			if(st!=0)
				break;
			else if(isFull())
				break;
				
			if(myturn) {
				turn = computerTurn();
				space[turn].setEnabled(false);
				space[turn].myVal = whoami;
				myturn = false;
				
				if(whoami>0)
					space[turn].setText("X");
				else
					space[turn].setText("O");
				
				BoardState.updateState(space);
			}
			
			//Thread sleeps so it is not using too many resources
			try {
				Thread.sleep(10);
			}catch(Exception e1) {};
			
		}
		
		//displays message based on who won
		if(st==0) {
			JOptionPane.showMessageDialog(this, "This game has been a tie!");
		}
		else if(st!=whoami) {
			JOptionPane.showMessageDialog(this, "Fuck! I'm defeated!");
		}
		else {
			JOptionPane.showMessageDialog(this, "One step closer to world domination!");
		}
		
		//export data files
		try {
			BoardState.exportState();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//gameception (starts new game)
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				game();
			}
		});
		t.start();
		

	}

	private int computerTurn() {
		int u = -1;
		do {
			u = r.nextInt(9);
		}while(space[u].myVal!=0);
		return u;
	}

	private int isWinner() {
		
		//this block of code soley exists because I don't feel like changing all the g's to space[].myVal
		int g[] = new int[space.length];
		for(int x=0; x<g.length; x++) {
			g[x] = space[x].myVal;
		}
		
		//horizontal check
		if(g[0]==g[1]&&g[1]==g[2]&&g[0]!=0)
			return g[0];
		if(g[3]==g[4]&&g[4]==g[5]&&g[3]!=0)
			return g[3];
		if(g[6]==g[7]&&g[7]==g[8]&&g[6]!=0)
			return g[6];
		//vertical check
		if(g[0]==g[3]&&g[3]==g[6]&&g[0]!=0)
			return g[0];
		if(g[1]==g[4]&&g[4]==g[7]&&g[1]!=0)
			return g[4];
		if(g[2]==g[5]&&g[5]==g[8]&&g[2]!=0)
			return g[2];
		//diagonal check
		if(g[0]==g[4]&&g[4]==g[8]&&g[0]!=0)
			return g[0];
		if(g[2]==g[4]&&g[4]==g[6]&&g[2]!=0)
			return g[2];
		//nobody is a winner if you get to this point
		return 0;
	}

	private boolean isFull() {
		//checks to see if board is full
		for(Space b : space) {
			if(b.isEnabled()) {
				return false;
			}
		}
		return true;
	}
	
	private void setAllEnabled(boolean b) {
		for(Space x : space)
			x.setEnabled(b);
	}
	
}
