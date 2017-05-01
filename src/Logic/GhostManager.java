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

    public GhostManager(Maze maze) {
        mGhosts.add(new Ghost(maze.getFieldAt(14,9), Color.PINK, maze, new AstarChase()));
        //mGhosts.add(new Ghost(maze.getFieldAt(14,10), Color.BLUE, maze, new RandomWalk()));
        //mGhosts.add(new Ghost(maze.getFieldAt(15,9), Color.ORANGE,maze, new RandomWalk()));
        //mGhosts.add(new Ghost(maze.getFieldAt(15,10), Color.RED, maze, new RandomWalk()));

    }

    @Override
    public void update() {
        for (Ghost gh: mGhosts) {
            gh.update();
        }
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {

        for (Ghost gh: mGhosts) {
            gh.draw(gc, fieldHeight, fieldWidth);
        }
    }

    public void setTargetLocation(Field targetLocation) {
        mPlayerPos = targetLocation;
        for (Ghost gh: mGhosts) {
            gh.setTarget(targetLocation);
        }

    }
}
