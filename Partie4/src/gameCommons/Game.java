package gameCommons;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	private Instant t0 = Instant.now();
	private Instant t1;
	private boolean stop = false;
	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;

	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		return !environment.isSafe(frog.getPosition());
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		return environment.isWinningPosition(frog.getPosition());
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		testWin();
		if (testLose()){
			if(!stop){ //Ca permet de stop le temps
				stop = true;
				t1 = Instant.now(); //Prend le temps quand la partie est perdue
			}
			double temps = Duration.between(t0, t1).toMillis();//On fait la différence entre les deux et on le veut en secondes

			graphic.endGameScreen("T'es nul, t'as mis : " + (float)temps/1000.f + "s.");
		}
		if (testWin()){
			if(!stop){     //Ca permet de stop le temps
				stop = true;
				t1 = Instant.now();    //Prend le temps quand la partie est gagnée
			}
			double temps = Duration.between(t0, t1).toMillis();    //On fait la différence entre les deux et on le veut en secondes
			graphic.endGameScreen("GG bro, t'as mis :" + (float)temps/1000.f + "s.");
		}
	}


}
