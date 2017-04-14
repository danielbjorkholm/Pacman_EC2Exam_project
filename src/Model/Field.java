package Model;

import Interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Field implements Drawable{

    private int posX;
    private int posY;
    private FieldProperty mFieldProperty;

    public Field(int posX, int posY, FieldProperty property) {
        this.posX = posX;
        this.posY = posY;
        mFieldProperty = property;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public FieldProperty getFieldProperty() {
        return mFieldProperty;
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        if (mFieldProperty == FieldProperty.WALL) {
            gc.setFill(Color.BLACK);
        } else {
            gc.setFill(Color.LIGHTGREY);
        }
        gc.fillRoundRect(posX * fieldWidth, posY * fieldHeight, fieldWidth, fieldHeight, 6, 6);
    }
}
