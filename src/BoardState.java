import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BoardState {

	public static ArrayList<Space[]> state;
	
	public static void updateState(Space[] s) {
		state.add(s);
	}
	
	public static void clearState() {
		state = new ArrayList<Space[]>();
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
			fw.write("$");
			for(int y=0; y<state.get(x).length; y++) {
				fw.write(Integer.toString(state.get(x)[y].myVal));
				if(y<8){
					fw.write("@");
				}
			}
		}
		
		fw.flush();
		fw.close();
		
		
	}
	
}
