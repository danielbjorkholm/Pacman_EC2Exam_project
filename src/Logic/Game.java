package Logic;

import Interfaces.Drawable;
import Interfaces.Updatable;
import Model.Maze;
import Model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class Game implements Updatable, Drawable{
    private static Game ourInstance = new Game();

    private GhostManager mGhostManager;
    private Player mPlayer;
    private Maze mMaze;

    private final double mHeight = 20;
    private final double mWidth = 30;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        restartGame();
    }

    @Override
    public void update() {

        mPlayer.update();
        mGhostManager.setTargetLocation(mPlayer.getCurrX(), mPlayer.getCurrY());
        mGhostManager.update();
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        mMaze.draw(gc, fieldHeight, fieldWidth);
        mPlayer.draw(gc, fieldHeight, fieldWidth);
        mGhostManager.draw(gc, fieldHeight, fieldWidth);
    }

    public void restartGame() {
        mMaze = new Maze(mHeight, mWidth);
        mGhostManager = new GhostManager(mMaze);
        mPlayer = new Player(0,0, mMaze);
    }

    public double getHeight() {
        return mHeight;
    }

    public double getWidth() {
        return mWidth;
    }

    public void updatePlayerDirection(KeyCode code) {
        mPlayer.updateDirection(code);
    }
}
