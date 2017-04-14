package Logic;

import Interfaces.Drawable;
import Interfaces.Updatable;
import Model.Ghost;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class GhostManager implements Updatable, Drawable {

    List<Ghost> mGhosts;

    public GhostManager() {
        mGhosts.add(new Ghost(14, 9, Color.PINK));
        mGhosts.add(new Ghost(14, 10, Color.BLUE));
        mGhosts.add(new Ghost(15, 9, Color.ORANGE));
        mGhosts.add(new Ghost(15, 10, Color.RED));

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(GraphicsContext gc, double fieldHeight, double fieldWidth) {

    }
}
