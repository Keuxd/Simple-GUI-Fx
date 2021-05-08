package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class RegisterScreen {
	private Pane root;
	
	public RegisterScreen() {
		root = new Pane();
		root.setId("register-screen");
		root.getChildren().add(initButton());
		root.getChildren().add(initLabel());
		root.getChildren().add(initVBox());
		root.getChildren().add(Main.initCloseButton());
		root.getChildren().add(Main.initMinimizeButton());
	}
	
	private Button initButton() {
		var button = new Button("Send data");
		
		button.setScaleX(1.25);
		button.setScaleY(1.25);
		
		button.setTranslateX((440 / 2) - 41);
		button.setTranslateY((680 / 2) + 270);
		
		button.setOnMousePressed(e -> {
			button.setStyle("-fx-background-color: red;");
		});
		
		button.setOnMouseReleased(e -> {
			button.setStyle("-fx-background-color: #CCFF99;");
		});
		
		button.setOnAction(e -> {
			var vBoxClone = (VBox)this.root.getChildren().get(2);
			
			//name validation
			var nameField = (TextField)vBoxClone.getChildren().get(0);
			if(nameField.getText().isBlank()) {
				 new Alert(AlertType.ERROR,"Invalid name").showAndWait();
				 return;
			}
			
			//age validation
			var ageObj = (TextField)vBoxClone.getChildren().get(1);
			int age = -1;
			try {
				age = Integer.parseInt(ageObj.getText());
			}catch (Exception exc) {
				new Alert(AlertType.ERROR,"Invalid age").showAndWait();
				return;
			}
			
			//sex validation
			var sexOption = getSexSelectedOption((HBox)vBoxClone.getChildren().get(2));
			if(sexOption == -1) {
				new Alert(AlertType.ERROR, "Invalid sex").showAndWait();
				return;
			}
			
			//pet validation
			var petOption = getPetSelectedOption((HBox)vBoxClone.getChildren().get(3));
			if(petOption == -1) {
				new Alert(AlertType.ERROR, "Choose an animal").showAndWait();
				return;
			}
			
			this.root.getScene().setRoot(new DataScreen(nameField.getText(),age,sexOption,petOption).getPane());
		});
		
		return button;
	}
	
	protected int getSexSelectedOption(HBox checkBoxes) {
		CheckBox check;
		
		for(int i = 0; i < 3; i++) {
			check = (CheckBox)checkBoxes.getChildren().get(i);
			if(check.isSelected())
				return i;
		}
		
		return -1;
	}
	
	protected int getPetSelectedOption(HBox pets) {
		ImageView pet;
		ColorAdjust saturation;
		
		for(int i = 0; i < 2; i++) {
			pet = (ImageView)pets.getChildren().get(i);
			saturation = (ColorAdjust)pet.getEffect();
			
			if(saturation.getSaturation() == 1.0) {
				return i;
			}
		}
		
		return -1;
	}
	
	private VBox initVBox() {
		var vbox = new VBox();
		vbox.setSpacing(10.0);
		vbox.setAlignment(Pos.CENTER);
		
		vbox.setTranslateX((440/2) - 75);
		vbox.setTranslateY((680/2) - 125);
		
		var tf1 = new TextField();
		tf1.setPromptText("First Name");
		
		var tf2 = new TextField();
		tf2.setPromptText("Age");
		tf2.setPrefColumnCount(10);
		
		vbox.getChildren().addAll(tf1,tf2);
		
		
		var sexHBox = new SexHBoxComponents().getHBox();
		var petHBox = new PetHBoxComponents().getHBox();
		
		vbox.getChildren().addAll(sexHBox,petHBox);
		
		return vbox;
	}
	
	private Label initLabel() {
		var label = new Label("Register");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setAlignment(Pos.CENTER);
		label.setWrapText(true);

		label.setTranslateX((440/2) - 35);
		label.setTranslateY((680/2) - 330);
		
		label.setScaleX(2.0);
		label.setScaleY(2.0);

		return label;
	}
	
	public void setPane(Pane pane) {
		this.root = pane;
	}
	
	public Pane getPane() {
		return this.root;
	}	
}

class SexHBoxComponents implements EventHandler<ActionEvent> {
	private HBox hbox = new HBox();
	
	public SexHBoxComponents() {
		hbox.getChildren().addAll(new CheckBox("Male"),new CheckBox("Female"), new CheckBox("Other"));
		
		for(int i = 0; i < 3; i++) {
			var check = (CheckBox)hbox.getChildren().get(i);
			check.setOnAction(this);
		}
	}
	
	protected HBox getHBox() {
		return this.hbox;
	}
	
	@Override
	public void handle(ActionEvent e) {
		var checkBox = (CheckBox)e.getSource();
		
		for(int i = 0; i < 3; i++) {
			var check = (CheckBox)hbox.getChildren().get(i);
			
			if(check.isSelected() && !check.equals(checkBox))
				check.setSelected(false);
		}
	}	
}

class PetHBoxComponents implements EventHandler<MouseEvent> {
	private HBox hBox = new HBox();
	
	public PetHBoxComponents() {
		var saturation = new ColorAdjust();
		saturation.setSaturation(-1.0);
		
		var dog = new ImageView();
		dog.setStyle("-fx-image: url(\"dog.png\");");
		dog.setOnMouseClicked(this);
		dog.setEffect(saturation);
		
		var cat = new ImageView();
		cat.setStyle("-fx-image: url(\"cat.png\");");
		cat.setOnMouseClicked(this);
		cat.setEffect(saturation);
		
		hBox.setSpacing(40.0);
		hBox.getChildren().addAll(dog,cat);
	}
	
	@Override
	public void handle(MouseEvent e) {
		var saturation = new ColorAdjust();
		saturation.setSaturation(1.0);
		
		var animal = (ImageView)e.getSource();
		animal.setEffect(saturation);
		
		var saturationC =  new ColorAdjust();
		saturationC.setSaturation(-1.0);
		
		if(e.getSource().equals(hBox.getChildren().get(0))) {
			var cat = (ImageView)hBox.getChildren().get(1);
			cat.setEffect(saturationC);
		}
		else {
			var dog = (ImageView)hBox.getChildren().get(0);
			dog.setEffect(saturationC);
		}		
	}
	
	protected HBox getHBox() {
		return this.hBox;
	}
}