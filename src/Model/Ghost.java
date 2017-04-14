package Model;


import Interfaces.Drawable;
import Interfaces.PathfindingStrategy;
import Interfaces.Updatable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class Ghost implements Updatable, Drawable{

    private int currX;
    private int currY;
    private boolean mAlive;
    private Color mColor;

    private int targetX = 0;
    private int targetY = 0;
    private Maze mMaze;
    private PathfindingStrategy mStrategy;

    public Ghost(int X, int Y, Color color, Maze maze, PathfindingStrategy strategy) {
        currX = X;
        currY = Y;
        mColor = color;
        mAlive = true;
        mMaze = maze;
        mStrategy = strategy;
    }

    public void setTarget(int x, int y){
        targetX = x;
        targetY = y;
    }

    @Override
    public void update() {
        Field newPosition = mStrategy.findNextMove(currX, currY, targetX, targetY, mMaze.getFields());
        currX = newPosition.getPosX();
        currY = newPosition.getPosY();
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        //Ghost body
        gc.setFill(mColor);
        gc.fillOval((currX*fieldWidth)+0+(fieldWidth*0.1f), (currY*fieldHeight)+0+(fieldHeight*0.1f), fieldWidth*0.8f,fieldHeight*0.8f);
        gc.fillRect((currX*fieldWidth)+0+(fieldWidth*0.1f), (currY*fieldHeight)+(fieldHeight/2), fieldWidth*0.8f, fieldHeight*0.4f);
        //Ghost eyes
        gc.setFill(Color.WHITE);
        gc.fillOval((currX*fieldWidth)+((fieldWidth/2)-(fieldWidth*0.20f)),(currY*fieldHeight)+(fieldHeight*0.35f),fieldWidth*0.15f,fieldHeight*0.15f);
        gc.fillOval((currX*fieldWidth)+((fieldWidth/2)+(fieldWidth*0.05f)),(currY*fieldHeight)+(fieldHeight*0.35f),fieldWidth*0.15f,fieldHeight*0.15f);
        gc.setLineWidth(2);
        //gc.setStroke(Color.BLACK);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.strokeLine((currX*fieldWidth)+((fieldWidth/2)-(fieldWidth*0.125f)),(currY*fieldHeight)+(fieldHeight*0.420f),
                      (currX*fieldWidth)+((fieldWidth/2)-(fieldWidth*0.125f)),(currY*fieldHeight)+(fieldHeight*0.430f));
        gc.strokeLine((currX*fieldWidth)+((fieldWidth/2)+(fieldWidth*0.125f)),(currY*fieldHeight)+(fieldHeight*0.420f),
                      (currX*fieldWidth)+((fieldWidth/2)+(fieldWidth*0.125f)),(currY*fieldHeight)+(fieldHeight*0.430f));
    }
}
