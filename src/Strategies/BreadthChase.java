package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.*;

public class BreadthChase implements PathfindingStrategy{

    private Field rootElement;
    private HashSet<Field> visitedNodes = new HashSet<>();
    private Queue<Field> recentlyAdded = new LinkedList<>();
    private Stack<Field> path = new Stack<>();

    private List<Field> fieldsToBeRemoved = new ArrayList<>();
    private List<Field> fieldsToBoAdded = new ArrayList<>();

    private int numberOfSearches = 0;

    // bliver kaldt af spøgelset
    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {

        path.clear();
        numberOfSearches = 0;
        if(currentPosition.equals(targetPosition)) return targetPosition;

        //Oprydning 1.0

        visitedNodes.clear();
        recentlyAdded.clear();
        maze.clearParents();

        rootElement = currentPosition;
        recentlyAdded.add(rootElement);

        // while (pacmans pos != i visted nodes fortsætter vi)
        while(!visitedNodes.contains(targetPosition)){
            Field current = recentlyAdded.poll();
            //For hver nabo til current...
            for (Field f: current.getConnectives()) {
                numberOfSearches++;
                //...hvis den ikke allerede er fundet, besøgt eller hver spøgelsets tidligere position...
                if(!visitedNodes.contains(f) && !recentlyAdded.contains(f)){
                    //...så add til recentlyAdded..
                    recentlyAdded.add(f);
                    //... og sæt dens parent.
                    f.setParent(current);
                }
            }
            //... og så er current blevet besøgt.
            visitedNodes.add(current);
            }

        Field nextField = calculatePath(targetPosition);

        System.out.println("Breadth First - Number of Searches: " + numberOfSearches);
        return nextField;
    }

    private Field calculatePath(Field targetPosition) {
        Field currentField = targetPosition;
        path.push(targetPosition);
        while(!path.contains(rootElement)){
            path.push(currentField.getParent());
            currentField = currentField.getParent();
        }
        path.pop();
        return path.pop();
    }

    @Override
    public Stack<Field> getPath() {

        return path;
    }
}
