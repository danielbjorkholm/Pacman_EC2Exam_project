import Utility.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Navigator.getInstance().setStage(primaryStage);
        Navigator.getInstance().switchToGame();
        primaryStage.setTitle("Maze Runner");
        primaryStage.show();
        System.out.println();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
