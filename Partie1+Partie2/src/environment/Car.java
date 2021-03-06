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
	public Car(Game game, Case leftPosition, boolean leftToRight) { //Constructeur de la voiture : //Construit à partir de cette ligne dans Lane : "cars.add(new Car(game, getBeforeFirstCase(), leftToRight));"
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight; //ne peut pas être choisi aléatoirement, car le sens de la voiture doit être coordonné avec celui des autres voitures de sa voie
		this.length = game.randomGen.nextInt(3); //methode random( (max-min)) qui permet d'entrer en arg une valeur entière qui servira de limite


	}
	//TODO : ajout de methodes

	public boolean isSafe(Case c){ // test si la case est vide
		if (c.absc == this.leftPosition.absc){
			return false;
		}return true;
	}

	//Voiture qui bouge selon son sens mais uniquement sur l'axe des absc
	public void update(boolean bouge){
		if(bouge){  //Déplacement s'il y a une voiture
			if (leftToRight)  //Si ca va de gauche à droite on avance de 1 à droite
				leftPosition = new Case(this.leftPosition.absc+1, this.leftPosition.ord);
			else{ //inversement
				leftPosition = new Case(this.leftPosition.absc-1, this.leftPosition.ord);
			}

		}this.addToGraphics();  //l'excécute quelque soit la valeur de car
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
