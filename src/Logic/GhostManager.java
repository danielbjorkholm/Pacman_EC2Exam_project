package Logic;

import Interfaces.Drawable;
import Interfaces.Updatable;
import Model.Field;
import Model.Ghost;
import Model.Maze;
import Strategies.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GhostManager implements Updatable, Drawable {

    private List<Ghost> mGhosts = new ArrayList<>();
    private Field mPlayerPos;
    private boolean mScared;
    private int mMoveCounter;
    private boolean targetReached = false;

    public GhostManager(Maze maze) {
        mGhosts.add(new Ghost(maze.getFieldAt(14,9), Color.PINK, maze, new BreadthChase()));
        mGhosts.add(new Ghost(maze.getFieldAt(14,10), Color.BLUE, maze, new BidirectionalChase()));
        mGhosts.add(new Ghost(maze.getFieldAt(15,9), Color.ORANGE,maze, new BestChase()));
        mGhosts.add(new Ghost(maze.getFieldAt(15,10), Color.RED, maze, new AstarChase()));
    }

    @Override
    public void update() {
        mMoveCounter++;
        //Hvis ghost ikke er scared...
        if(!mScared) {
            //...skift mellem random og chase...
            if (mMoveCounter == 20) {
                setGhostToChase();
            } else if (mMoveCounter == 10) {
                for (Ghost gh : mGhosts) {
                    gh.setStrategy(new RandomWalk());
                }
            }
        //...ellers hvis ghost er scared og de ikke allerede flygter...
        } else if (mScared && mGhosts.get(0).getStrategy().getClass() != Fleeing.class){
            //...sæt ghost til at flygte.
            for (Ghost gh: mGhosts) {
                gh.setStrategy(new Fleeing());
            }
        }
        if(mMoveCounter >= 21) mMoveCounter = 0;

        //Update ghosts
        for (Ghost gh: mGhosts) {
            gh.update();
            if(gh.getPosition().equals(mPlayerPos)){
                if (mScared){
                    gh.setStrategy(new Fleeing());
                    gh.resetPosition();
                } else {
                    targetReached = true;
                    //TODO: Player dead
                }
            }
        }




    }



    private void setGhostToChase() {
        for (Ghost gh: mGhosts) {
            if (gh.getColor() == Color.PINK) gh.setStrategy(new BreadthChase());
            if (gh.getColor() == Color.BLUE) gh.setStrategy(new BidirectionalChase());
            if (gh.getColor() == Color.ORANGE) gh.setStrategy(new BestChase());
            if (gh.getColor() == Color.RED) gh.setStrategy(new AstarChase());
        }
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {

        for (Ghost gh: mGhosts) {
            gh.draw(gc, fieldHeight, fieldWidth);
        }
    }

    public void setScared(boolean scared) {
        mScared = scared;
    }

    public void setTargetLocation(Field targetLocation) {
        mPlayerPos = targetLocation;
        for (Ghost gh: mGhosts) {
            gh.setTarget(targetLocation);
        }

    }
}
