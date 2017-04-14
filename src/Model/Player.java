package Model;

import Interfaces.Drawable;
import Interfaces.Updatable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;


public class Player implements Updatable, Drawable {
    private int currX;
    private int currY;
    private boolean mAlive;
    private Maze mMaze;
    private KeyCode mKeyCode;
    private int mStartAngle;

    public Player(int currX, int currY, Maze maze) {
        this.currX = currX;
        this.currY = currY;
        mMaze = maze;
        mAlive = true;
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }

    @Override
    public void update() {
        int prevX = currX;
        int prevY = currY;

        if (mKeyCode != null) {
            switch (mKeyCode) {
                case DOWN:
                    this.currY++;
                    break;
                case LEFT:
                    this.currX--;
                    break;
                case RIGHT:
                    this.currX++;
                    break;
                case UP:
                    this.currY--;
                    break;
            }
        }
        //Reverse move, hvis player er uden for banen eller spilleren ikke er på et path felt.
        if(currX < 0 || currX >= mMaze.getWidth() || currY < 0 || currY >= mMaze.getHeight() ||
                mMaze.getFieldProperty(currX, currY) != FieldProperty.PATH){
            currX = prevX;
            currY = prevY;
        }
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {

        gc.setFill(Color.YELLOW);
        gc.fillArc((currX*fieldWidth)+(0+(fieldWidth*0.05f)), (currY*fieldHeight)+(0+(fieldHeight*0.05f)), fieldWidth*0.9f,fieldHeight*0.9f, mStartAngle, 270 , ArcType.ROUND);
    }

    public void updateDirection(KeyCode code) {
        mKeyCode = code;
        if (mKeyCode != null) {
            switch (mKeyCode) {
                case DOWN:
                    mStartAngle = 315;
                    break;
                case LEFT:
                    mStartAngle = 225;
                    break;
                case RIGHT:
                    mStartAngle = 45;
                    break;
                case UP:
                    mStartAngle = 135;
                    break;
            }
        }
    }
}
