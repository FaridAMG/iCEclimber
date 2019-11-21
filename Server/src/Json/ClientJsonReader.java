package Json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientJsonReader {
	
	JSONObject ClientJson = new JSONObject();

	public ClientJsonReader() {
		super();
	}
	
	public void insertClientMessage(String theJsonMessage) {
		JSONParser parser = new JSONParser();
		
		try {
			this.ClientJson = (JSONObject) parser.parse(theJsonMessage);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	
	public int seeAtack(int popoONana) {
		
			JSONArray sujeto = new JSONArray();
			JSONObject acciones = new JSONObject();
			
			
			sujeto = (JSONArray) this.ClientJson.get(Integer.toString(popoONana));
			acciones = (JSONObject) sujeto.get(0);
			int acc =   (int)(long) acciones.get("at");
			return acc;	
		
			}

	public int seeYmovment(int popoONana) {
		
		JSONArray sujeto = new JSONArray();
		JSONObject acciones = new JSONObject();
		
		
		sujeto = (JSONArray) this.ClientJson.get(Integer.toString(popoONana));
		acciones = (JSONObject) sujeto.get(0);
		int acc = (int)(long) acciones.get("ye");
		return acc;	
		
	}
	
	public int seeXmovment(int popoONana) {
		
		JSONArray sujeto = new JSONArray();
		JSONObject acciones = new JSONObject();
		
		
		sujeto = (JSONArray) this.ClientJson.get(Integer.toString(popoONana));
		acciones = (JSONObject) sujeto.get(0);
		int acc = (int)(long) acciones.get("equis");
		return acc;	
	}
	
	public int seeVision(int popoONana) {
		JSONArray sujeto = new JSONArray();
		JSONObject acciones = new JSONObject();
		
		
		sujeto = (JSONArray) this.ClientJson.get(Integer.toString(popoONana));
		acciones = (JSONObject) sujeto.get(0);
		int acc = (int)(long) acciones.get("vis");
		return acc;	
		}
	
}
