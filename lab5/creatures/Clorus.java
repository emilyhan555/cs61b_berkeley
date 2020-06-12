package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    protected double energy;

    /**
     * All Clorus must have a name equal to "clorus".
     */
    public Clorus (double e){
        super("clorus");
        energy = e;
        r = 0;
        g = 0;
        b = 0;
    }

    /**
     * Clorus should always return the color (34,0,231).
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature's energy.
     */
    public void attack(Creature C) {
        energy = energy + C.energy();
    }

    /**
     * Clorus should lose 0.03 units of energy on a move action.
     */
    public void move() {
        energy = energy - 0.03;
    }

    /**
     * Clorus should lose 0.01 units of energy on a staty action.
     */
    public void stay() {
        energy = energy - 0.01;
    }

    /**
     * When a Clorus replicates, it keeps 50% of its energy. The other 50% goes to its offspring.
     * No energy is lost in the replication process.
     */
    public Clorus replicate() {
        double pEnergy = energy;
        energy = 0.5 * energy;
        Clorus offspring = new Clorus(pEnergy - energy);
        return offspring;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbours) {
        /* Rule 1
            If there are no empty squares, the Clorus will Stay.
         */
        Deque<Direction> emptySquares = new ArrayDeque<>();
        for (Direction dir : neighbours.keySet()) {
            String value = neighbours.get(dir).name();
            if (value.equals("empty")) {
                emptySquares.addFirst(dir);
            }
        }

        if (emptySquares.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        /* Rule 2
            if any Plips are seen, the Clorus will ATTACK one of them randomly.
         */
        else {
            Deque<Direction> plipDir = new ArrayDeque<>();
            for (Direction dir : neighbours.keySet()) {
                if (neighbours.get(dir).name().equals("plip")) {
                    plipDir.addFirst(dir);
                }
            }
            if (plipDir.size() > 0) {
                Direction randomEntry = HugLifeUtils.randomEntry(plipDir);
                return new Action(Action.ActionType.ATTACK, randomEntry);
            }
            /*
               Rule 3:
               if the clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
             */
            else if (plipDir.size() == 0 && energy >= 1) {
                Direction randomREP = HugLifeUtils.randomEntry(emptySquares);
                return new Action(Action.ActionType.REPLICATE, randomREP);
            }
            /*
              Rule 4:
              otherwise, clorus will move to a random empty square.
             */
            else {
                Direction randomMove = HugLifeUtils.randomEntry(emptySquares);
                return new Action(Action.ActionType.MOVE, randomMove);
            }
        }
    }
}
