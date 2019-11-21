package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Json.ClientJsonReader;
import Json.JsonUtil;
import Json.coordinates;

public class IceClimberGame {
	//Nada:0  Hielo:1  Yetis:2   Aves:3  Popo:4   Nana:5   Pterodactilo:6
	//Escalera:7   Naranja:8    Banano:9  Berenjena:10  Lechuga:11   NoRompible:12
	//
	public JsonUtil JsonGameMatrix;
	public ClientJsonReader clientJReader = new ClientJsonReader();
	
	public IceClimberGame(int gameMatrxWidth,int gameMatrxHeight) {
		super();
		this.JsonGameMatrix = new JsonUtil(gameMatrxWidth,gameMatrxHeight);
		sculpInitialMatrix();
	}
	
	
	private void sculpInitialMatrix() {
		int MatrixWidth = this.JsonGameMatrix.getMatrixWidth();
		int MatrixHeight = this.JsonGameMatrix.getMatrixHeight();
		
		for(int i=0; i != MatrixWidth ; i++) {			     // Create no breakable boarder.
			this.JsonGameMatrix.ChangeElementInPos(i, 0, 12);			
		}
		for(int i=0; i != MatrixHeight ; i++) {			
			this.JsonGameMatrix.ChangeElementInPos(0, i, 12);			
		}
		
		
		addBreakableIceFloor();
		addYetis();
		addPopo();
		addNana();
		addBirds();
		
		
		
	}
	
	
	private void addBreakableIceFloor() {
		int MatrixWidth = this.JsonGameMatrix.getMatrixWidth();
		int MatrixHeight = this.JsonGameMatrix.getMatrixHeight();
		
		boolean yesNo = true;
		for(int y = 0; y != MatrixHeight ; y++) {
			
			if(yesNo == true) {
				
				for(int x = 0; x != MatrixWidth ; x++) {
					this.JsonGameMatrix.ChangeElementInPos(x, y, 1);
				}
				
				yesNo = false;
			}else {
				yesNo = true;
			}
			
			
		}
		
	}
	
	private void addYetis() {

		
		int MatrixWidth = this.JsonGameMatrix.getMatrixWidth();
		int MatrixHeight = this.JsonGameMatrix.getMatrixHeight();
		
		boolean yesNo = false;
		for(int y = 0; y != MatrixHeight ; y++) {
			
			if(yesNo == true) {
				
				for(int x = 0; x != MatrixWidth ; x++) {
					
					if(this.JsonGameMatrix.GetElementInPos(x, y) == 0) {
						this.JsonGameMatrix.ChangeElementInPos(x, y, 2);
						break;
					}
					
				}
				
				yesNo = false;
			}else {
				yesNo = true;
			}
			
			
		}
		
	}
	
	private void addBirds() {
		
		int MatrixWidth = this.JsonGameMatrix.getMatrixWidth();
		int MatrixHeight = this.JsonGameMatrix.getMatrixHeight();
		
		if(this.JsonGameMatrix.doesElementExist(3) != true) {
			
			if( (MatrixWidth - 3 > 1)  & (MatrixHeight - 3 > 1) ) {
				
				
				if(this.JsonGameMatrix.GetElementInPos(MatrixWidth - 3,MatrixHeight - 3) != 4 & this.JsonGameMatrix.GetElementInPos(MatrixWidth - 3,MatrixHeight - 3) != 5 ) {
					this.JsonGameMatrix.ChangeElementInPos(MatrixWidth - 3,MatrixHeight - 3, 3);
				}
				
				
			}
			
		}
		
		
		
		
	}
	
	private void addPopo() {
		int MatrixWidth = this.JsonGameMatrix.getMatrixWidth();
				
				for(int x = 0; x != MatrixWidth ; x++) {
					
					if(this.JsonGameMatrix.GetElementInPos(x, 1) == 0) {
						this.JsonGameMatrix.ChangeElementInPos(x, 1, 4);
						break;
					}
					
				}
				
			
			
			
		
		
		
	}
	
	private void addNana() {
		int MatrixWidth = this.JsonGameMatrix.getMatrixWidth();
				
				for(int x = 0; x != MatrixWidth ; x++) {
					
					if(this.JsonGameMatrix.GetElementInPos(x, 1) == 0) {
						this.JsonGameMatrix.ChangeElementInPos(x, 1, 5);
						break;
					}
					
				}
				
		
	}
	
	
	
	
	public void moveNPCs() {
		
		
		moveYetis();
		moveBird();
		
		
	}
	
	private void moveYetis() {
		List<coordinates> yetiList = new ArrayList<coordinates>();
		yetiList = this.JsonGameMatrix.GetCoordinatesOfElements(2);
		
		yetiList.forEach((coor) -> {			
			int	xcoor = coor.xpos;
			int	ycoor = coor.ypos;
			
			if(this.JsonGameMatrix.GetElementInPos(xcoor + 1, ycoor) != 12) {
				
				this.JsonGameMatrix.ChangeElementInPos(xcoor, ycoor, 0);
				this.JsonGameMatrix.ChangeElementInPos(xcoor + 1, ycoor, 2);
				
			}else {
				
				this.JsonGameMatrix.ChangeElementInPos(xcoor, ycoor, 0);
				this.JsonGameMatrix.ChangeElementInPos(1, ycoor, 2);
				
			}
			
		});
		
		
	}
	
	
	
	private void moveBird() {
	}
	
	
	
	public void actionPopoAndNana(String theJsonMessage) {
		this.clientJReader.insertClientMessage(theJsonMessage);
		
		//See if attack
			this.seeAttack(4, this.clientJReader.seeVision(4), this.clientJReader.seeAtack(4));
			this.seeAttack(5, this.clientJReader.seeVision(5), this.clientJReader.seeAtack(5));

		//See Y mov
			this.seeYmov(4, this.clientJReader.seeYmovment(4));
			this.seeYmov(5, this.clientJReader.seeYmovment(5));

		//See X mov
			this.seeXmov(4, this.clientJReader.seeXmovment(4));
			this.seeXmov(5, this.clientJReader.seeXmovment(5));
		
	}
	
	private void seeAttack(int personaje, int orientacion, int orden) {
		List<coordinates> person = new ArrayList<coordinates>();
		if(orden == 0) {}else {
			person = this.JsonGameMatrix.GetCoordinatesOfElements(personaje);
			person.forEach((coor) -> {
				int xpos = coor.xpos;
				int ypos = coor.ypos;
				
				if(orientacion == 1) {
					
					int whatIsIt = this.JsonGameMatrix.GetElementInPos(xpos + 1, ypos);

					if(whatIsIt == 1) {
						this.JsonGameMatrix.ChangeElementInPos(xpos + 1, ypos, 0);
					}else if(whatIsIt == 2) {
						this.JsonGameMatrix.ChangeElementInPos(xpos + 1, ypos, 0);
					}else if(whatIsIt == 3) {
						this.JsonGameMatrix.ChangeElementInPos(xpos + 1, ypos, 0);
					}
					
					
					
				}else if(orientacion == -1) {
					
					
					int whatIsIt = this.JsonGameMatrix.GetElementInPos(xpos - 1, ypos);

					if(whatIsIt == 1) {
						this.JsonGameMatrix.ChangeElementInPos(xpos - 1, ypos, 0);
					}else if(whatIsIt == 2) {
						this.JsonGameMatrix.ChangeElementInPos(xpos - 1, ypos, 0);
					}else if(whatIsIt == 3) {
						this.JsonGameMatrix.ChangeElementInPos(xpos - 1, ypos, 0);
					}
					
					
				}
				
			});
			
		}
			
		
		
	}
	
	private void seeYmov(int personaje, int mov) {
		
		
		List<coordinates> person = new ArrayList<coordinates>();
			person = this.JsonGameMatrix.GetCoordinatesOfElements(personaje);
			person.forEach((coor) -> {
				int xpos = coor.xpos;
				int ypos = coor.ypos;
				
				if(mov == 1) {
					
					int whatIsIt = this.JsonGameMatrix.GetElementInPos(xpos, ypos + 1);

					if(whatIsIt == 0) {
						this.JsonGameMatrix.ChangeElementInPos(xpos, ypos, 0);
						this.JsonGameMatrix.ChangeElementInPos(xpos, ypos + 1, personaje);
					}
					
					
					
				}else if(mov == -1) {
					
					
					int whatIsIt = this.JsonGameMatrix.GetElementInPos(xpos, ypos - 1);

					if(whatIsIt == 0) {
						this.JsonGameMatrix.ChangeElementInPos(xpos, ypos, 0);
						this.JsonGameMatrix.ChangeElementInPos(xpos, ypos - 1, personaje);
					}
					
					
				}
				
			});
			
		}
			
		
		
		
	
	
	private void seeXmov(int personaje, int mov) {
		
		
		List<coordinates> person = new ArrayList<coordinates>();
		person = this.JsonGameMatrix.GetCoordinatesOfElements(personaje);
		person.forEach((coor) -> {
			int xpos = coor.xpos;
			int ypos = coor.ypos;
			
			if(mov == 1) {
				
				int whatIsIt = this.JsonGameMatrix.GetElementInPos(xpos + 1, ypos);

				if(whatIsIt == 0) {
					this.JsonGameMatrix.ChangeElementInPos(xpos, ypos, 0);
					this.JsonGameMatrix.ChangeElementInPos(xpos + 1, ypos, personaje);
				}
				
				
				
			}else if(mov == -1) {
				
				
				int whatIsIt = this.JsonGameMatrix.GetElementInPos(xpos - 1, ypos);

				if(whatIsIt == 0) {
					this.JsonGameMatrix.ChangeElementInPos(xpos, ypos, 0);
					this.JsonGameMatrix.ChangeElementInPos(xpos - 1, ypos , personaje);
				}
				
				
			}
			
		});
		
		
		
	}
	
	
	
}










