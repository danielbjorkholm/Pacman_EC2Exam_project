package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.*;

public class AstarChase implements PathfindingStrategy{

    private Field rootElement;
    private Field prevField = null;

    private HashSet<Field> visitedNodes = new HashSet<>();
    private List<Field> recentlyAdded;
    private Map<Field, Double> absoluteCost = new HashMap<>();
    private Stack<Field> path = new Stack<>();

    private int numberOfSearches = 0;

    @Override
    public Field findNextMove(Field currentPosition, Field targetPosition, Maze maze) {

        path.clear();
        numberOfSearches = 0;
        if(currentPosition.equals(targetPosition)) return targetPosition;
        if(currentPosition.getConnectives().contains(targetPosition)) return targetPosition;


        //Oprydning 1.0
        visitedNodes.clear();
        absoluteCost.clear();
        //Når man adder til TreeSet bliver sorteret efter denne comparator
        recentlyAdded = new ArrayList<>();

        //Forstår ikke hvorfor treeset ikke virker, men sort af list virker fint.....
        //recentlyAdded = new TreeSet<>(((o1, o2) ->  (int) ((o1.calcHeuristicCost(targetPosition))-(o2.calcHeuristicCost(targetPosition)))));

        maze.clearParents();


        absoluteCost.put(currentPosition, 0.0);
        rootElement = currentPosition;
        visitedNodes.add(rootElement);
        recentlyAdded.add(rootElement);



        //Så længe vi ikke har fundet target...
        while(!visitedNodes.contains(targetPosition)){
            //System.out.println("RecentlyAdded Peek: " + recentlyAdded.first());
            recentlyAdded.sort(((o1, o2) ->  (int) ((absoluteCost.get(o1)+o1.calcHeuristicCost(targetPosition))-(absoluteCost.get(o2)+o2.calcHeuristicCost(targetPosition)))));
            Field current = recentlyAdded.remove(0);
            //For hver nabo til current...
            for (Field f: current.getConnectives()) {
                numberOfSearches++;
                if(!absoluteCost.containsKey(f)) absoluteCost.put(f, absoluteCost.get(current)+1.0);
                //...hvis den ikke allerede er fundet, besøgt eller hver spøgelsets tidligere position...
                if(!visitedNodes.contains(f) && !recentlyAdded.contains(f) && !f.equals(prevField)){
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


        //System.out.println("Depth First - Number of Searches: " + numberOfSearches);
        prevField = rootElement;
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

    @Override
    public int getSearchCount() {
        return numberOfSearches;
    }
}
