package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

    private Game game;
    private IEnvironment env;
    private ArrayList<Lane> lanes = new ArrayList<>();

    public Environment(Game game) {
        this.game = game;
    }

    @Override
    public boolean isSafe(Case c) {
       return env.isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return env.isWinningPosition(c);
    }

    @Override
    public void update() {
        env.update();
    }


}

