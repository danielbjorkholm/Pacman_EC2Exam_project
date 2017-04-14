import Utility.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

      /*  Parent root = FXMLLoader.load(getClass().getResource("FXML/gameView.fxml"));
        primaryStage.setScene(new Scene(root, 800, 640));
        //Request focus gør root layout i stand til at lytte efter key inputs.
        primaryStage.getScene().getRoot().requestFocus();*/


        Navigator.getInstance().setStage(primaryStage);
        Navigator.getInstance().switchToGame();
        primaryStage.setTitle("Maze Runner");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
