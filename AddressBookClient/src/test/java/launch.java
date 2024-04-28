import com.AddressBookClient.Launch.UserMain;
import com.AddressBookClient.Utils.ScreenSize;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class launch extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("这是主界面");

        Button mainButton = new Button("打开副界面");
        mainButton.setOnAction(e -> openSubStage());

        StackPane layout = new StackPane();
        layout.getChildren().add(mainButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openSubStage() {
        Stage subStage = new Stage();
        subStage.setTitle("副界面");

        Button subButton = new Button("关闭副界面");
        subButton.setOnAction(e -> subStage.close());

        StackPane subLayout = new StackPane();
        subLayout.getChildren().add(subButton);

        Scene subScene = new Scene(subLayout, 200, 150);
        subStage.setScene(subScene);
        subStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
