package Controllers;

import Logic.Game;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Canvas mCanvas;
    public Label mPinkCountLabel;
    public Label mBlueCountLabel;
    public Label mOrangeCountLabel;
    public Label mRedCountLabel;
    public CheckBox mPathCheckBox;

    private Game mGame = Game.getInstance();
    private float mRefreshRate = 0.25f; //in seconds

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mGame.restartGame();
        mPathCheckBox.setSelected(false);

        new AnimationTimer() {
            private long lastUpdate = 0;

            //Game loop
            public void handle(long now) {
                if (now - lastUpdate >= Math.round(mRefreshRate*1000000000)) {
                    lastUpdate = now;
                    mGame.update();
                    updateSearchCounts();
                    mGame.draw(mCanvas.getGraphicsContext2D(), mCanvas.getHeight() / mGame.getHeight(), mCanvas.getWidth() / mGame.getWidth());
                }
            }
        }.start();
    }

    private void updateSearchCounts() {
        Map<Color, Integer> searchCounts = mGame.getSearchCounts();
        for (Map.Entry<Color, Integer> countEntry : searchCounts.entrySet()) {
            if(countEntry.getKey() == Color.PINK) mPinkCountLabel.setText("Pink: " + countEntry.getValue());
            if(countEntry.getKey() == Color.BLUE) mBlueCountLabel.setText("Blue: " + countEntry.getValue());
            if(countEntry.getKey() == Color.ORANGE) mOrangeCountLabel.setText("Orange: " + countEntry.getValue());
            if(countEntry.getKey() == Color.RED) mRedCountLabel.setText("Red: " + countEntry.getValue());

        }
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP ||
                keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT) {
            System.out.println("Keypressed -> KeyCode: " + keyEvent.getCode());
            mGame.updatePlayerDirection(keyEvent.getCode());
            mGame.draw(mCanvas.getGraphicsContext2D(), mCanvas.getHeight() / mGame.getHeight(), mCanvas.getWidth() / mGame.getWidth());
        }
    }

    public void handleResetBtn(ActionEvent actionEvent) {
        mGame.restartGame();
        mPathCheckBox.setSelected(false);
    }

    public void handlePathVisibleCheckBox(ActionEvent actionEvent) {
        mGame.setPathVisible(mPathCheckBox.isSelected());
    }
}
