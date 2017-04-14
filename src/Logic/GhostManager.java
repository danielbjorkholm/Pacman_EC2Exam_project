package Logic;

import Interfaces.Drawable;
import Interfaces.Updatable;
import Model.Ghost;
import Model.Maze;
import Strategies.RandomWalk;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GhostManager implements Updatable, Drawable {

    private List<Ghost> mGhosts = new ArrayList<>();
    private int mPlayerPosX;
    private int mPlayerPosY;

    public GhostManager(Maze maze) {
        mGhosts.add(new Ghost(14, 9, Color.PINK, maze, new RandomWalk()));
        mGhosts.add(new Ghost(14, 10, Color.BLUE, maze, new RandomWalk()));
        mGhosts.add(new Ghost(15, 9, Color.ORANGE,maze, new RandomWalk()));
        mGhosts.add(new Ghost(15, 10, Color.RED, maze, new RandomWalk()));

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {

        for (Ghost gh: mGhosts) {
            gh.draw(gc, fieldHeight, fieldWidth);
        }
    }

    public void setTargetLocation(int currX, int currY) {
        mPlayerPosX = currX;
        mPlayerPosY = currY;
        for (Ghost gh: mGhosts) {
            gh.setTarget(currX, currY);
        }

    }
}
