package Interfaces;


import Model.Field;

import java.util.Set;

public interface PathfindingStrategy {

    public Field findNextMove(int currX, int currY, int destinationX, int destinationY, Set<Field> graph);

}
