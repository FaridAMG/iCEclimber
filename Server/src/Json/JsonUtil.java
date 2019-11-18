package Json;
import Logic.DataTypes;
import Logic.LinkedList;
import Logic.LinkedList.Node;
import Logic.Matrix;
import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;

public class JsonUtil extends JsonTest implements DataTypes {
	JsonUtil(Integer x, Integer y) {
		super();
	}
	public void JsonMapWrite(Matrix matrix, Integer x, Integer y, Integer ele) {
		matrix.setElement(ele);
		matrix.setPosX(x);
		matrix.setPosY(y);
		String jsonMatrix = convertJavaToJson(matrix);
		System.out.println(jsonMatrix);
	}
	public Integer JsonMapRead(String matrix, Integer x, Integer y) {
		Integer ele = 0;
		JsonNode parent = null;
		try {
			parent = mapper.readTree(matrix);
			if(x == parent.path("x").asInt() & y == parent.path("y").asInt()) {
				ele = parent.path("ele").asInt();
			} else {
				System.out.println("Couldn't find element!");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ele;
	}
	public LinkedList<Integer> JsonMapFind(String matrix, Integer ele) {
		LinkedList<Integer> pos = new LinkedList<Integer>();
		JsonNode parent = null;
		try {
			parent = mapper.readTree(matrix);
			if(ele == parent.path("ele ").asInt()) {
				pos.addAtFront(new Node<Integer>(parent.path("x").asInt()));
				pos.addAtFront(new Node<Integer>(parent.path("y").asInt()));
			} else {
				System.out.println("Couldn't find element!");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
	}
}
