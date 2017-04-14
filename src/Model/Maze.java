package Model;


import Interfaces.Drawable;
import Logic.MazeDataManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Maze implements Drawable {
    private double mHeight;
    private double mWidth;
    private Set<Field> mFields;

    public Maze(double height, double width) {
        mHeight = height;
        mWidth = width;
        mFields = MazeDataManager.getInstance().loadStandardMaze();

        //Set edges på hver field.
        List<Field> connectedFields = new ArrayList<>(4);
        //For hvert felt...
        for (Field f : mFields) {
            connectedFields.clear();
            //...og hvert other felt...
            for (Field other : mFields) {
                if (other.getPosX() == (f.getPosX() - 1) && other.getPosY() == f.getPosY())
                    connectedFields.add(other);
                if (other.getPosX() == (f.getPosX() + 1) && other.getPosY() == f.getPosY())
                    connectedFields.add(other);
                if (other.getPosX() == f.getPosX() && other.getPosY() == (f.getPosY() - 1))
                    connectedFields.add(other);
                if (other.getPosX() == f.getPosX() && other.getPosY() == (f.getPosY() + 1))
                    connectedFields.add(other);
            }
            for (Field c: connectedFields) {
                if(c. getFieldProperty() == FieldProperty.PATH) f.addConnectedField(c);
            }
        }


        System.out.println("Size: " + mFields.size());
        System.out.println(mFields.toString());
    }

    public Set<Field> getFields() {
        return mFields;
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, fieldWidth, fieldHeight);
        for (Field f : mFields) {
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
        for (Field f : mFields) {
            if (f.getPosX() == X && f.getPosY() == Y) {
                return f.getFieldProperty();
            }
        }
        return null;
    }
}
