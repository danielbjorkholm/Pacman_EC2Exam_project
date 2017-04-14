package Model;


import Interfaces.Drawable;
import Logic.MazeDataManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Maze implements Drawable {
    private double mHeight;
    private double mWidth;
    private List<Field> mFields;


    public Maze(double height, double width) {
        mHeight = height;
        mWidth = width;
        mFields = MazeDataManager.getInstance().loadStandardMaze();

        System.out.println("Size: " + mFields.size());
        System.out.println(mFields.toString());
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, fieldWidth, fieldHeight);
        for (Field f: mFields) {
            f.draw(gc, fieldHeight, fieldWidth);
        }
    }


    public double getHeight() {
        return mHeight;
    }

    public double getWidth() {
        return mWidth;
    }


    public FieldProperty getFieldProperty(int X, int Y) {
        for (Field f: mFields) {
            if (f.getPosX() == X && f.getPosY() == Y){
                return f.getFieldProperty();
            }
        }
        return null;
    }
}
