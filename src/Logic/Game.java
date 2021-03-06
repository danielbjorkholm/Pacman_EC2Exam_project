package Logic;

import Interfaces.Drawable;
import Interfaces.Updatable;
import Model.Maze;
import Model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Map;

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
        mPlayer.setFrenzy(mMaze.eatPill(mPlayer.getCurrX(), mPlayer.getCurrY()));
        if(mPlayer.isFrenzy()){
            mGhostManager.setScared(true);
        } else {
            mGhostManager.setScared(false);
        }
        mGhostManager.setTargetLocation(mMaze.getFieldAt(mPlayer.getCurrX(), mPlayer.getCurrY()));
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

    public void setPathVisible(boolean visible) {
        mGhostManager.setPathVisible(visible);
    }

    public Map<Color, Integer> getSearchCounts() {
        return mGhostManager.getSearchCounts();
    }
}
