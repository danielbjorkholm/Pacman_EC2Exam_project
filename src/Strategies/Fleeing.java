package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.Stack;
import java.util.TreeSet;

public class Fleeing implements PathfindingStrategy {

    private TreeSet<Field> sortedConnectives;

    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {

        sortedConnectives = new TreeSet<>(((o1, o2) ->  (int) (o1.calcHeuristicCost(targetPosition)-o2.calcHeuristicCost(targetPosition))));

        sortedConnectives.addAll(currentPosition.getConnectives());

        return sortedConnectives.pollLast();
    }

    @Override
    public Stack<Field> getPath() {
        return null;
    }

    @Override
    public int getSearchCount() {
        return 0;
    }


}
