package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Json.ClientJsonReader;
import Json.JsonUtil;

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
	
	
	
	
	
	
	
}










