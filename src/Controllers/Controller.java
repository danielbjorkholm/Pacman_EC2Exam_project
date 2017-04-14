package Controllers;

import Logic.Game;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Canvas mCanvas;
    private Game mGame = Game.getInstance();
    private float mRefreshRate = 0.25f; //in seconds

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mGame.restartGame();

        new AnimationTimer() {
            private long lastUpdate = 0;

            //Game loop
            public void handle(long now) {
                if (now - lastUpdate >= Math.round(mRefreshRate*1000000000)) {
                    lastUpdate = now;
                    mGame.update();
                    mGame.draw(mCanvas.getGraphicsContext2D(), mCanvas.getHeight() / mGame.getHeight(), mCanvas.getWidth() / mGame.getWidth());
                }
            }
        }.start();
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP ||
                keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT) {
            System.out.println("Keypressed -> KeyCode: " + keyEvent.getCode());
            mGame.updatePlayerDirection(keyEvent.getCode());
            mGame.draw(mCanvas.getGraphicsContext2D(), mCanvas.getHeight() / mGame.getHeight(), mCanvas.getWidth() / mGame.getWidth());
        }
    }
}
