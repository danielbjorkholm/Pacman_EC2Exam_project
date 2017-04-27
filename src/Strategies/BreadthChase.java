package Strategies;


import Interfaces.PathfindingStrategy;
import Model.Field;
import Model.Maze;

import java.util.HashSet;
import java.util.Set;

public class BreadthChase implements PathfindingStrategy{

    private Node rootElement;
    private HashSet<Node> visitedNodes = new HashSet<>();

    public BreadthChase(Maze maze) {

    }

    // bliver kaldt af spøgelset
    @Override
    public Field findNextMove(int currX, int currY, int destinationX, int destinationY, Set<Field> graph) {

        if (rootElement == null)
        for (Field f: graph) {
            if(f.isAt(currX, currY)){
                rootElement = new Node(f);
                visitedNodes.add(rootElement);
            }
        }


       // while (pacmans pos != i visted nodes fortsætter vi)

       // for hvert barn uden children
               // hvilken children har du
               // tilføj dem til visitedNodes og set "mig" til parent, hvis den



       // finde vejen frem
       // start ved field der matcher pacman
        // så læge parent ikker er root
                    //tilføj parent til linkedlist (som er vejen du skal gå)

        return null;
    }
}
