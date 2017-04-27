package Interfaces;


import Model.Field;
import Model.Maze;

import java.util.Set;

public interface PathfindingStrategy {

    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze);

}
