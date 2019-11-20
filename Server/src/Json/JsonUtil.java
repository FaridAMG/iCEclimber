



package Json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtil  {
	// GameMatrix Variables
	JSONObject GameMatrix = new JSONObject();
	int xpos;
	int ypos;
	
	public JsonUtil(int xpos, int ypos) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;	
		createTemplate();
	}
	
	private void createTemplate() {
		for(int x=0;x!=this.xpos; x++) {
	        JSONArray list = new JSONArray();
	        JSONObject ye = new JSONObject();
	        
			for(int y=0;y!=this.ypos; y++) {		        
		        ye.put(y,0);		
			}
			list.add(ye);
			this.GameMatrix.put(x,list);
			
		}
		
	}
	
	
	public void ConsolePrintGameMatrix() {
		System.out.print(this.GameMatrix);
	}
	
	
	
}