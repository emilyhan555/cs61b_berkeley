package creatures;

import huglife.*;
import org.junit.Test;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {
    @Test
    public void testMove() {
        Clorus c = new Clorus(2);
        c.move();
        assertEquals(1.97, c.energy, 0.01);
        c.move();
        assertEquals(1.94, c.energy, 0.01);
    }

    @Test
    public void testSTAY() {
        Clorus c = new Clorus(3);
        c.stay();
        assertEquals(2.99, c.energy, 0.01);
        c.stay();
        assertEquals(2.98, c.energy, 0.01);
    }

    @Test
    public void testAttack() {
        Plip p = new Plip(2);
        Clorus c = new Clorus(1.5);
        c.attack(p);
        assertEquals(3.5, c.energy, 0.01);
        c.attack(p);
        assertEquals(5.5, c.energy, 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus offspring = c.replicate();
        assertEquals(1, c.energy, 0.01);
        assertEquals(1, offspring.energy, 0.01);
    }

    @Test
    public void testChooseAction() {
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.8);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // No empty adjacent spaces; stay
        HashMap<Direction, Occupant> allPlip = new HashMap<>();
        allPlip.put(Direction.TOP, new Plip(2));
        allPlip.put(Direction.BOTTOM, new Plip(2));
        allPlip.put(Direction.LEFT, new Plip(2));
        allPlip.put(Direction.RIGHT, new Plip(2));

        actual = c.chooseAction(allPlip);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // any Plips are seen, the Clorus will ATTACK one of them randomly.
        Plip p = new Plip(2);
        HashMap<Direction, Occupant> topPlip = new HashMap<>();
        topPlip.put(Direction.TOP, p);
        topPlip.put(Direction.RIGHT, new Empty());
        topPlip.put(Direction.LEFT, new Empty());
        topPlip.put(Direction.BOTTOM, new Empty());

        actual = c.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        // any Plips are seen, the Clorus will ATTACK one of them randomly.
        HashMap<Direction, Occupant> twoPlip = new HashMap<>();
        twoPlip.put(Direction.TOP, new Plip(2.8));
        twoPlip.put(Direction.RIGHT, new Plip(1.8));
        twoPlip.put(Direction.LEFT, new Plip(3.8));
        twoPlip.put(Direction.BOTTOM, new Empty());

        actual = c.chooseAction(twoPlip);
        Action expectedTOP = new Action(Action.ActionType.ATTACK, Direction.TOP);
        Action expectedRight = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        Action expectedLeft = new Action(Action.ActionType.ATTACK, Direction.LEFT);

        //assertEquals(expectedTOP, actual);
        //assertEquals(expectedRight, actual);
        //assertEquals(expectedLeft, actual);

        // if Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        c = new Clorus(1.6);
        HashMap<Direction, Occupant> topEmpty = new HashMap<>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.RIGHT, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.BOTTOM, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // if Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        c = new Clorus(1.6);
        HashMap<Direction, Occupant> twoEmpty = new HashMap<>();
        twoEmpty.put(Direction.TOP, new Empty());
        twoEmpty.put(Direction.RIGHT, new Empty());
        twoEmpty.put(Direction.LEFT, new Impassible());
        twoEmpty.put(Direction.BOTTOM, new Impassible());

        actual = c.chooseAction(twoEmpty);
        Action expectedTOPEmpty = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        Action expectedRightEmpty = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);

        //assertEquals(expectedTOPEmpty, actual);
        //assertEquals(expectedRightEmpty, actual);

        // if Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        c = new Clorus(0.9);

        actual = c.chooseAction(twoEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        //assertEquals(expected, actual);
    }
}
