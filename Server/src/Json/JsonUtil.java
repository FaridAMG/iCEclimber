



package Json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * @author zuckerberg
 *
 */
public class JsonUtil  {
	
	// GameMatrix Variables
	JSONObject GameMatrix = new JSONObject();
	


	int xpos;
	int ypos;
	/**
	 * 
	 * @param xpos
	 * @param ypos
	 */
	public JsonUtil(int xpos, int ypos) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;	
		createTemplate();
	}
	
	/**
	 * 
	 * @return
	 */
	public JSONObject getGameMatrix() {
		return GameMatrix;
	}

	/**
	 * 
	 * @return
	 */
	public int getMatrixWidth() {
		return xpos;
	}
	/**
	 * 
	 * @return
	 */
	public int getMatrixHeight() {
		return ypos;
	}
	
	/**
	 * 
	 */
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
	/**
	 * 
	 * @param xpos
	 * @param ypos
	 * @return
	 */
	public int GetElementInPos(int xpos, int ypos) {
		
		if((xpos <= this.xpos || xpos >=0) & (ypos <= this.ypos || ypos >=0)) {
			JSONArray ex = new JSONArray();
			JSONObject uy = new JSONObject();
			ex = (JSONArray) this.GameMatrix.get(xpos);
			uy = (JSONObject) ex.get(0);
			int element = (int) uy.get(ypos);
			//System.out.println(element);
			return element;	
		}else {
			System.out.println("[-0-] ERROR IN ELEMENTINPOS (JSonUtil.java)");
			return -1;
		}
			
	}
	
	/**
	 * 
	 * @param xpos
	 * @param ypos
	 * @param newElement
	 */
	public void ChangeElementInPos(int xpos, int ypos, int newElement) {
		
		if((xpos <= this.xpos || xpos >=0) & (ypos <= this.ypos || ypos >=0)) {
			JSONArray ex = new JSONArray();
			JSONObject uy = new JSONObject();
			ex = (JSONArray) this.GameMatrix.get(xpos);
			uy = (JSONObject) ex.get(0);
			uy.replace(ypos, newElement);
		}else {
			System.out.println("[-0-] ERROR IN ELEMENTINPOS (JSonUtil.java)");
		}
			
	}
	/**
	 * 
	 * @param element
	 * @return
	 */
	public boolean doesElementExist(int element) {
		List<coordinates> ele = new ArrayList<coordinates>();
		ele = this.GetCoordinatesOfElements(element);
		
		if(ele.isEmpty() == false) {
			return true;
		}else {
			return false;
		}
		
	}
	/**
	 * 
	 * @param element
	 * @return
	 */
	public List<coordinates> GetCoordinatesOfElements(int element) {
		List<coordinates> ele = new ArrayList<coordinates>();
		JSONArray ex = new JSONArray();
		JSONObject uy = new JSONObject();
		
		
		for(int x = 0; x != this.xpos;x++) {
			ex = (JSONArray) this.GameMatrix.get(x);
			uy = (JSONObject) ex.get(0);
			
			for(int y = 0; y != this.ypos;y++) {
				
				if(element == (int) uy.get(y) ) {
					coordinates coord = new coordinates();	
					coord.xpos = x;
					coord.ypos = y;
					ele.add(coord);
				}
				
				}
		}
				
		return ele;
		
	}
	
	 
	/**
	 * 
	 */
	public void ConsolePrintGameMatrix() {
		System.out.println(this.GameMatrix);
	}
	
	
	
}



