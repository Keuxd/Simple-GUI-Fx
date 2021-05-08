package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class DataScreen {
	private Pane root;
	
	private String name;
	private int age;
	private int sexOption;
	private int petOption;
		
	public DataScreen(String name, int age, int sexOption, int petOption) {
		this.name = name;
		this.age = age;
		this.sexOption = sexOption;
		this.petOption = petOption;
		
		root = new Pane();
		root.setId("data-screen");
		root.getChildren().add(initButton());
		root.getChildren().add(initVBox());
	}
	
	private Button initButton() {
		var button = new Button("Try again");
		
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
			this.root.getScene().setRoot(new RegisterScreen().getPane());
		});
		
		return button;
	}
	
	private VBox initVBox() {
		var vBox = new VBox();
		vBox.setSpacing(7.25);
		vBox.setAlignment(Pos.CENTER);
		vBox.setTranslateX((440/2) - 82);
		vBox.setTranslateY((680/2) - 80);
		
		var label1 = new Label();
		label1.setText("Welcome " + name);
		label1.setTextAlignment(TextAlignment.JUSTIFY);
		label1.setWrapText(true);
		
		var label2 = new Label();
		label2.setText("You're " + age + " years old");
		label2.setTextAlignment(TextAlignment.JUSTIFY);
		label2.setWrapText(true);
		
		var label3 = new Label();
		label3.setText("Your sex is " + getSexByOption(sexOption));
		label3.setTextAlignment(TextAlignment.JUSTIFY);
		label3.setWrapText(true);
		
		vBox.getChildren().addAll(label1,label2,label3);
		
		var label4 = new Label();
		label4.setText("Your pet is a "+ getPetByOption(petOption));
		label4.setTextAlignment(TextAlignment.JUSTIFY);
		label3.setWrapText(true);
		
		vBox.getChildren().add(label4);
		
		return vBox;
	}
	
	private String getSexByOption(int option) {
		switch(option) {
			case 0: return "male";
			case 1: return "female";
			case 2: return "undefined";
			default: return "NULL";
		}
	}
	
	private String getPetByOption(int option) {
		switch(option) {
			case 0: return "dog";
			case 1: return "cat";
			default: return "NULL";
		}
	}

	public void setPane(Pane pane) {
		this.root = pane;
	}
	
	public Pane getPane() {
		return this.root;
	}	
}
