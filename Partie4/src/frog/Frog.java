package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case position;
	private Direction direction;

	public Frog(Game game) {
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
		if(key == direction.up && this.position.ord < this.game.height-1) {
			this.position = new Case(this.position.absc,this.position.ord + 1);
		}
		if(key == direction.down && this.position.ord > 0) {
			this.position = new Case(this.position.absc,this.position.ord - 1);
		}
		if(key == direction.left && this.position.absc > 0) {
			this.position = new Case(this.position.absc - 1,this.position.ord);
		}
		if(key == direction.right && this.position.absc < this.game.width-1) {
			this.position = new Case(this.position.absc + 1,this.position.ord);
		}
	}
}
