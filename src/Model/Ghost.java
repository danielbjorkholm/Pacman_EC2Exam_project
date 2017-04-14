package Model;


import Interfaces.Drawable;
import Interfaces.Updatable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class Ghost implements Updatable, Drawable{

    private int currX;
    private int currY;
    private Color mColor;

    public Ghost(int X, int Y, Color color) {
        currX = X;
        currY = Y;
        mColor = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        //Ghost body
        gc.setFill(mColor);
        gc.fillOval(0+(fieldWidth*0.1f), 0+(fieldHeight*0.1f), fieldWidth*0.8f,fieldHeight*0.8f);
        gc.fillRect(0+(fieldWidth*0.1f), fieldHeight/2, fieldWidth*0.8f, fieldHeight*0.4f);
        //Ghost eyes
        gc.setFill(Color.WHITE);
        gc.fillOval((fieldWidth/2)-(fieldWidth*0.20f),fieldHeight*0.35f,fieldWidth*0.15f,fieldHeight*0.15f);
        gc.fillOval((fieldWidth/2)+(fieldWidth*0.05f),fieldHeight*0.35f,fieldWidth*0.15f,fieldHeight*0.15f);
        gc.setLineWidth(8);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.strokeLine((fieldWidth/2)-(fieldWidth*0.125f),fieldHeight*0.420f,(fieldWidth/2)-(fieldWidth*0.125f),fieldHeight*0.430f);
        gc.strokeLine((fieldWidth/2)+(fieldWidth*0.125f),fieldHeight*0.420f,(fieldWidth/2)+(fieldWidth*0.125f),fieldHeight*0.430f);
    }
}
