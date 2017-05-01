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
    private boolean mAlive;
    private Color mColor;

    private Field mTargetPos;
    private Maze mMaze;
    private PathfindingStrategy mStrategy;

    public Ghost(Field position, Color color, Maze maze, PathfindingStrategy strategy) {
       mPosition = position;
        mColor = color;
        mAlive = true;
        mMaze = maze;
        mStrategy = strategy;
    }

    public void setTarget(Field targetPos){
        mTargetPos = targetPos;
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

        for (Field f : mStrategy.getPath()) {
            gc.fillOval((f.getPosX()*fieldWidth)+(0+(fieldWidth*0.05f)), (f.getPosY()*fieldHeight)+(0+(fieldHeight*0.05f)), fieldWidth*0.9f,fieldHeight*0.9f);

        }
    }
}
