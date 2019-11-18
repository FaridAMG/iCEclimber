package Logic;
import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder(value = {
	"name",
	"lives",
	"power"
})
public class Player implements Serializable, DataTypes {
	private Integer lives;
	private String name;
	private Integer power;
	public Integer getLives() {
		return lives;
	}
	public void setLives(Integer lives) {
		this.lives = lives;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	
}
