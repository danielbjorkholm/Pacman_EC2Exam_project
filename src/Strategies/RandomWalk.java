package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.Random;
import java.util.Stack;

public class RandomWalk implements PathfindingStrategy{

    private Random rand = new Random();
    private Field prevField = null;

    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {
        //Er det første kald, sæt prevField til nuværende placering.
        if(prevField == null){
            prevField = currentPosition;
        }
        //Sæt resultatet til prevField
        Field result = prevField;
        //Find et nærtliggende felt, som mover ikke var på sidste update.
        while(prevField.equals(result)){
            result = (Field) currentPosition.getConnectives().toArray()[rand.nextInt(currentPosition.getConnectives().size())];
        }
        prevField = currentPosition;
        return result;
    }

    @Override
    public Stack<Field> getPath() {

        return null;
    }
}
