package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;

import java.util.Random;
import java.util.Set;

public class RandomWalk implements PathfindingStrategy{

    Random rand = new Random();

    @Override
    public Field findNextMove(int currX, int currY, int destinationX, int destinationY, Set<Field> graph) {
        for (Field f: graph) {
            if(f.isAt(currX, currY)){
               return (Field) f.getConnectives().toArray()[rand.nextInt(f.getConnectives().size())];
            }
        }
        return null;
    }
}
