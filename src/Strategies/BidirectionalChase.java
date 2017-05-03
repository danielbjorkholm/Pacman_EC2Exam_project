package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.*;

public class BidirectionalChase implements PathfindingStrategy{

    private HashSet<Field> visitedFromStart = new HashSet<>();
    private HashSet<Field> visitedFromTarget = new HashSet<>();
    private Queue<Field> recentlyAddedStart = new LinkedList<>();
    private Queue<Field> recentlyAddedTarget = new LinkedList<>();
    private int numberOfSearches = 0;
    private boolean searchesJoined;
    private Stack<Field> path = new Stack<>();

    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {

        path.clear();
        numberOfSearches = 0;
        //Hvis den er på target eller ved siden af, returnér target.
        if(currentPosition.equals(targetPosition)) return targetPosition;
        if(currentPosition.getConnectives().contains(targetPosition)) return targetPosition;

        //Oprydning
        visitedFromStart.clear();
        visitedFromTarget.clear();
        recentlyAddedStart.clear();
        recentlyAddedTarget.clear();
        maze.clearParents();

        recentlyAddedStart.add(currentPosition);
        recentlyAddedTarget.add(targetPosition);

        //Så længe de ikke har mødt hinanden...
        searchesJoined = false;
        Field startJointNode = null;
        Field targetJointNode = null;
        while(!searchesJoined){

            //Search from Start
            Field currentFromStart = recentlyAddedStart.poll();

            //... for hver nabo...
            for (Field f : currentFromStart.getConnectives()) {
                numberOfSearches++;
                //......hvis den ikke allerede er fundet eller besøgt...
                if (!visitedFromStart.contains(f) && !recentlyAddedStart.contains(f)){
                    //...og hvis den ikke allerede er fundet af den anden søgning...
                    if(f.getParent() == null) {
                        //...så add til recently...
                        recentlyAddedStart.add(f);
                        //...og sæt dens parent.
                        f.setParent(currentFromStart);
                    } else {
                        searchesJoined = true;
                        startJointNode = currentFromStart;
                        targetJointNode = f;
                    }
                }
            }
            visitedFromStart.add(currentFromStart);

            if(searchesJoined){
                break;
            }

            //Search from Target
            Field currentFromTarget = recentlyAddedTarget.poll();

            //... for hver nabo...
            for (Field f : currentFromTarget.getConnectives()) {
                numberOfSearches++;
                //......hvis den ikke allerede er fundet eller besøgt...
                if (!visitedFromTarget.contains(f) && !recentlyAddedTarget.contains(f)){
                    //...og hvis den ikke allerede er fundet af den anden søgning...
                    if(f.getParent() == null) {
                        //...så add til recently...
                        recentlyAddedTarget.add(f);
                        //...og sæt dens parent.
                        f.setParent(currentFromTarget);
                    } else {
                        searchesJoined = true;
                        startJointNode = f;
                        targetJointNode = currentFromTarget;
                    }
                }
            }
            visitedFromTarget.add(currentFromTarget);
        }

        //System.out.println("Bidirectional First - Number of Searches: " + numberOfSearches);
        return calculatePath(currentPosition, targetPosition, startJointNode, targetJointNode);
    }

    private Field calculatePath(Field currentPosition, Field targetPosition, Field startJointNode, Field targetJointNode) {

        //Last part of Path
        Stack<Field> reversedPath = new Stack<>();
        Field currentField = targetJointNode;
        //reversedPath.push(targetJointNode);
        while(currentField != null){
            reversedPath.push(currentField);
            currentField = currentField.getParent();
        }
        //reverse path
        while(!reversedPath.empty()){
            path.push(reversedPath.pop());
        }

        //Beginning of path
        currentField = startJointNode;
        path.push(currentField);
        while(!path.contains(currentPosition)){
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
