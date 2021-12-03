package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

    private Game game;
    private ArrayList<Lane> lanes ;

    public Environment(Game game) {

        this.game = game;
        this.lanes = new ArrayList<>();
        this.lanes.add(new Lane(this.game,0,0,true,0));
        for (int i =1; i< game.height-1;i++){
            this.lanes.add(new Lane (game, i,3 , game.randomGen.nextBoolean() ,0.2));
        }
        this.lanes.add(new Lane(this.game,this.game.height-1,0,true,0));    }

    @Override
    public boolean isSafe(Case c) {
        return this.lanes.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {return c.ord== game.height-1;
    }

    @Override
    public void update() {
        for (Lane l: this.lanes) {
            l.update();
        }

    }

    @Override
    public void ajouteLane() {

    }

}

