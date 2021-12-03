package Inf;


import gameCommons.Game;
import gameCommons.IFrog;

import util.Case;
import util.Direction;

public class FrogInf implements IFrog {

    private Game game;
    private Case position;
    private Direction direction;


    public FrogInf(Game game) {
        this.game = game;
        this.position = new Case(game.width/2,0);
        this.direction=Direction.up;
    }


    @Override
    public Case getPosition() {
        return position;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(Direction key) {
        if(key == direction.up ) {  // la grenouille peut dépasser la limite en hauteur
            this.position = new Case(this.position.absc,this.position.ord +1);
            this.game.score = this.game.score + 1;   // on aurait pu remplacer par un "++".
            if (this.game.score > this.game.limite) {
                this.game.limite = this.game.score;  // le score augmente a chaque fois que la grenouille monte
                this.game.ajouteLane();  // ajout d'une voie a chaque fois

            }
        }
        if(key == direction.down && this.position.ord > 0) {
            this.position = new Case(this.position.absc,this.position.ord - 1);
            this.game.score = this.game.score - 1;    // on aurait pu remplacer par un "--".
        }
        if(key == direction.left && this.position.absc > 0) {
            this.position = new Case(this.position.absc - 1,this.position.ord);
        }
        if(key == direction.right && this.position.absc < this.game.width-1) {
            this.position = new Case(this.position.absc + 1,this.position.ord);
        }

    }
}
