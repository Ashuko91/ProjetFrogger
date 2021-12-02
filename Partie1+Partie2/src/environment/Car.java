package environment;

import java.awt.Color;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length = game.randomGen.nextInt(3);


	}
	//TODO : ajout de methodes

	public boolean isSafe(Case c){
		if (c.absc == this.leftPosition.absc){
			return false;
		}return true;
	}

	public void update(boolean bouge){
		if(bouge){
			if (leftToRight)
				leftPosition = new Case(this.leftPosition.absc+1, this.leftPosition.ord);
			else{
				leftPosition = new Case(this.leftPosition.absc-1, this.leftPosition.ord);
			}

		}this.addToGraphics();
	}

	public Case getLeftPosition(){
		return this.leftPosition;
	}

	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
