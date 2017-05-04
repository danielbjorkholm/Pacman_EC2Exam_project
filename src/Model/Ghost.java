package Model;


import Interfaces.Drawable;
import Interfaces.PathfindingStrategy;
import Interfaces.Updatable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;

public class Ghost implements Updatable, Drawable{

    private Field mPosition;
    private Field mStartngPosition;

    private boolean mRevealPath;
    private Color mColor;

    private Field mTargetPos;
    private Maze mMaze;
    private PathfindingStrategy mStrategy;
    private double mDotMargin = 0.0;

    public Ghost(Field position, Color color, Maze maze, PathfindingStrategy strategy) {
       mPosition = position;
        mStartngPosition = position;
        mColor = color;
        mRevealPath = false;
        mMaze = maze;
        mStrategy = strategy;

        if (mColor == Color.PINK) mDotMargin = 0.00;
        if (mColor == Color.BLUE) mDotMargin = 0.15;
        if (mColor == Color.ORANGE) mDotMargin = 0.30;
        if (mColor == Color.RED) mDotMargin = 0.45;
    }

    public Color getColor() {
        return mColor;
    }

    public Field getPosition() {
        return mPosition;
    }

    public int getSearchCount(){
        return mStrategy.getSearchCount();
    }

    public void setTarget(Field targetPos){
        mTargetPos = targetPos;
    }

    public void setStrategy(PathfindingStrategy strategy){
        mStrategy = strategy;
    }

    public void setRevealPath(boolean revealPath) {
        mRevealPath = revealPath;
    }

    public PathfindingStrategy getStrategy() {
        return mStrategy;
    }

    @Override
    public void update() {
        mPosition = mStrategy.findNextMove(mPosition, mTargetPos, mMaze);
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        //Ghost body
        gc.setFill(mColor);
        gc.fillOval((mPosition.getPosX()*fieldWidth)+0+(fieldWidth*0.1f), (mPosition.getPosY()*fieldHeight)+0+(fieldHeight*0.1f), fieldWidth*0.8f,fieldHeight*0.8f);
        gc.fillRect((mPosition.getPosX()*fieldWidth)+0+(fieldWidth*0.1f), (mPosition.getPosY()*fieldHeight)+(fieldHeight/2), fieldWidth*0.8f, fieldHeight*0.4f);
        //Ghost eyes
        gc.setFill(Color.WHITE);
        gc.fillOval((mPosition.getPosX()*fieldWidth)+((fieldWidth/2)-(fieldWidth*0.20f)),(mPosition.getPosY()*fieldHeight)+(fieldHeight*0.35f),fieldWidth*0.15f,fieldHeight*0.15f);
        gc.fillOval((mPosition.getPosX()*fieldWidth)+((fieldWidth/2)+(fieldWidth*0.05f)),(mPosition.getPosY()*fieldHeight)+(fieldHeight*0.35f),fieldWidth*0.15f,fieldHeight*0.15f);
        gc.setLineWidth(2);
        //gc.setStroke(Color.BLACK);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.strokeLine((mPosition.getPosX()*fieldWidth)+((fieldWidth/2)-(fieldWidth*0.125f)),(mPosition.getPosY()*fieldHeight)+(fieldHeight*0.420f),
                      (mPosition.getPosX()*fieldWidth)+((fieldWidth/2)-(fieldWidth*0.125f)),(mPosition.getPosY()*fieldHeight)+(fieldHeight*0.430f));
        gc.strokeLine((mPosition.getPosX()*fieldWidth)+((fieldWidth/2)+(fieldWidth*0.125f)),(mPosition.getPosY()*fieldHeight)+(fieldHeight*0.420f),
                      (mPosition.getPosX()*fieldWidth)+((fieldWidth/2)+(fieldWidth*0.125f)),(mPosition.getPosY()*fieldHeight)+(fieldHeight*0.430f));

        // draw path
        if(mRevealPath){
            if(mStrategy.getPath() != null) {
                for (Field f : mStrategy.getPath()) {
                    gc.setFill(mColor);
                    gc.fillOval((f.getPosX() * fieldWidth) + (0 + (fieldWidth * (0.05f + mDotMargin))), (f.getPosY() * fieldHeight) + (0 + (fieldHeight * (0.05f + mDotMargin))), fieldWidth * 0.4f, fieldHeight * 0.4f);

                }
            }
        }
    }

    public void resetPosition() {
        mPosition = mStartngPosition;

    }
}
