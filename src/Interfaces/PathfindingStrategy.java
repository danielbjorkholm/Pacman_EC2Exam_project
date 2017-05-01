package Interfaces;


import Model.Field;
import Model.Maze;

import java.util.Set;
import java.util.Stack;

public interface PathfindingStrategy {

    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze);

    public Stack<Field> getPath();

}
