package Utility;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    private static Navigator ourInstance = new Navigator();
    private Stage mStage;

    public static Navigator getInstance() {
        return ourInstance;
    }

    private Navigator() {
    }

    public void setStage(Stage stage) {
        mStage = stage;
    }

    public void switchToGame(){
        switchToView("../FXML/gameView.fxml", 800, 640);
    }

    private void switchToView(String view, int width, int height){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(view));
            mStage.setScene(new Scene(root, width, height));
            //Request focus gør root layout i stand til at lytte efter key inputs.
            mStage.getScene().getRoot().requestFocus();
        } catch (IOException e) {
            System.err.printf("Failed to load %s!%n", view);
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
