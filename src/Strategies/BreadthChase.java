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

    // bliver kaldt af spøgelset
    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {

        path.clear();
        visitedNodes.clear();
        recentlyAdded.clear();
        rootElement = null;
        maze.clearParents();

        if(currentPosition.equals(targetPosition)) return targetPosition;

        if (rootElement == null) {
            rootElement = currentPosition;
            visitedNodes.add(rootElement);
            recentlyAdded.add(rootElement);
        }

        // while (pacmans pos != i visted nodes fortsætter vi)
        while(!visitedNodes.contains(targetPosition)){
            //For hvert seneste tilføjet Fields
            for (Field f: recentlyAdded) {
                //Tag dennes børn...
                for (Field child: f.getConnectives()) {
                    //...Hvis Barnet ikke allerede har været undersøgt..
                    if (child.getParent() == null) {
                        //...set barnets parent...
                        child.setParent(f);
                        //...og smid dem i visited og recentlyadded
                        visitedNodes.add(child);
                        fieldsToBoAdded.add(child);
                    }
                }
                //Fjern parenten fra recently added.
                fieldsToBeRemoved.add(f);
            }
            recentlyAdded.removeAll(fieldsToBeRemoved);
            fieldsToBeRemoved.clear();
            recentlyAdded.addAll(fieldsToBoAdded);
            fieldsToBoAdded.clear();
        }

        Field nextField = calculatePath(targetPosition);


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
}
