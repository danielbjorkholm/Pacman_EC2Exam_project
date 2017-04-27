package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.Random;
import java.util.Set;

public class RandomWalk implements PathfindingStrategy{

    private Random rand = new Random();
    private Field prevField = null;

    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {
        //For hvert felt i graph'en.
        for (Field f: maze.getFields()) {
            //Hvis mover er på dette felt...
            if(f.isAt(currentPosition.getPosX(), currentPosition.getPosY())){
                //Er det første kald, sæt prevField til nuværende placering.
                if(prevField == null){
                    prevField = f;
                }
                //Sæt resultatet til prevField
                Field result = prevField;
                //Find et nærtliggende felt, som mover ikke var på sidste update.
                while(prevField.equals(result)){
                    result = (Field) f.getConnectives().toArray()[rand.nextInt(f.getConnectives().size())];
                }
                prevField = f;
                return result;
            }
        }
        return null;
    }
}
