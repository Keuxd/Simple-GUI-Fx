package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	private Scene scene;
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Accurate research");
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image("gear.png"));
		initScene();
		primaryStage.setScene(scene);
		primaryStage.show();
		stage = primaryStage;
	}
	
	private void initScene() {
		scene = new Scene(new RegisterScreen().getPane(),440,680);
		scene.getStylesheets().add("application.css");
	}

	protected static ImageView initCloseButton() {
		var closeBut = new ImageView();
		closeBut.setId("button-close");
		closeBut.setScaleX(0.5);
		closeBut.setScaleY(0.5);
		
		closeBut.setTranslateX(354);
		closeBut.setTranslateY(40);
		
		closeBut.setOnMouseClicked(e -> {
			stage.close();
		});
		
		return closeBut;
	}
	
	protected static ImageView initMinimizeButton() {
		var minimBut = new ImageView();
		minimBut.setId("button-minimize");
		minimBut.setScaleX(0.5);
		minimBut.setScaleY(0.5);
		
		minimBut.setTranslateX(-30);
		minimBut.setTranslateY(40);
		
		minimBut.setOnMouseClicked(e -> {
			stage.setIconified(true);
			stage.toBack();
		});
		
		return minimBut;
	}
	
	
	
	
}