import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BoardState {

	public static ArrayList<String> state = new ArrayList<String>();
	
	public static void updateState(Space[] s) {
		String temp = "";
		for(int x=0; x<s.length; x++) {
			temp += s[x].myVal;
			if(x<s.length-1) {
				temp+="@";
			}
		}
		state.add(temp);
	}
	
	public static void clearState() {
		state = new ArrayList<String>();
	}
	
	public static void exportState() throws IOException {
		
		File folder = new File("training_data/");
	    if (!folder.exists())
	        folder.mkdir();
		
		String fname = Long.toString(System.currentTimeMillis());
		Random r = new Random(System.nanoTime());
		String ext = Integer.toString(Math.abs(r.nextInt()));
		File file = new File("training_data/"+fname+ext+".training");
		FileWriter fw = new FileWriter(file);
		
		for(int x=0; x<state.size(); x++) {
			if(x>0) {
				fw.write("$");
			}
			fw.write(state.get(x));
		}
		
		fw.flush();
		fw.close();
		
		
	}
	
}
