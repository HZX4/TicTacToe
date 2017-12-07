import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel{

	private JButton[] space;
	
	public Board() {
		
		//array of buttons as spaces
		space = new JButton[9];
		
		//initialize each element in the array
		for(JButton b : space)
			b = new JButton();
		
	}
	
}
