package Model;

import Interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class Field implements Drawable{

    private int posX;
    private int posY;
    private FieldProperty mFieldProperty;

    private Set<Field> mConnectedFields = new HashSet<>();

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

    public void addConnectedField(Field other){
        mConnectedFields.add(other);
    }

    public Set<Field> getConnectives(){
        return mConnectedFields;
    }

    public boolean isAt(int x, int y){
        if (posX != x) return false;
        if (posY != y) return false;
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (posX != field.posX) return false;
        if (posY != field.posY) return false;
        return true;
    }

    @Override
    public String toString() {

        String result = "";

        result += "Field{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", mFieldProperty=" + mFieldProperty +
        ", connected: ";
        for (Field f: mConnectedFields) {
            result += "-x:" + f.getPosX() + "y:" + f.getPosY() + "-";
        }


                //", mConnectedFields=" + mConnectedFields +
                result += '}';
        return result;
    }
}
