package Inf;

import java.util.ArrayList;

import environment.Lane;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvInf implements IEnvironment {

    private Game game;
    private ArrayList<Lane> lanes ;

    public EnvInf(Game game) {

        this.game = game;
        this.lanes = new ArrayList<>();
        this.lanes.add(new Lane(this.game,0,0,true,0)); //première voie sans rien pour visibilité
        this.lanes.add(new Lane(this.game,1,0,true,0)); //voie libre pour le depart de la grenouille
        for (int i =2; i< game.height-1;i++){
            this.ajouteLane(i);
        }


    }

    @Override
    public boolean isSafe(Case c) {
        return this.lanes.get(c.ord).isSafe(c);  //On veut connaitre la sureté d'une case dans le tableau infini
    }

    @Override
    public boolean isWinningPosition(Case c) {return false;
    }

    @Override
    public void update() {
        for (Lane l: this.lanes) {
            l.update();
        }

        System.out.println(lanes.size());
    }


    public void ajouteLane(int i) {
        this.lanes.add(new Lane(this.game, i));
    }

    public void ajouteLane() {  // ajoute une ligne
        this.ajouteLane(this.lanes.size());
    }
}

