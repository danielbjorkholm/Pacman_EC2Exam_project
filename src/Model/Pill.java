package Model;


import Interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pill implements Drawable {

    private int posX;
    private int posY;

    public Pill(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {
        gc.setFill(Color.YELLOW);
        gc.fillOval((posX * fieldWidth)+0+(fieldWidth*0.05f), (posY * fieldHeight)+0+(fieldHeight*0.35f), fieldWidth*0.9f,fieldHeight*0.3f);
    }
}
