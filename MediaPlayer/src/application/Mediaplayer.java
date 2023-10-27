package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;


public class Mediaplayer extends Application {
	private static final String URL
	= "https://jsoncompare.org/LearningContainer/SampleFiles/Video/MP4/sample-mp4-file.mp4"; // <= put Video URL Here
	@Override
	public void start(Stage primaryStage) {
		
		Media media = new Media(URL);
		MediaPlayer mediaplayer = new MediaPlayer(media);
		MediaView mediaview = new MediaView(mediaplayer);
		
		
		BorderPane pane = new BorderPane();
		Button Play = new Button(">");
		Button skip5 = new Button("+5");
		Button back5 = new Button("-5");
		Button reset = new Button("<<");
		Slider vol = new Slider(0, 100, 0);

		vol.setShowTickMarks(true);
		
		
		Play.setOnAction(e ->{
			if(Play.getText() == ">") {
				mediaplayer.play();
				Play.setText("||");
			} else {
				mediaplayer.pause();
				Play.setText(">");
			}
		});
		
		skip5.setOnAction(e->{
			mediaplayer.seek(mediaplayer.getCurrentTime().add(Duration.seconds(5)));
		});
		
		back5.setOnAction(e ->{
			mediaplayer.seek(mediaplayer.getCurrentTime().subtract(Duration.seconds(5)));
		});
		
		vol.setPrefWidth(150);
		vol.setMaxWidth(Region.USE_PREF_SIZE);
		vol.setValue(50);
		vol.setMinWidth(30);
		
		mediaplayer.volumeProperty().bind(vol.valueProperty().divide(100));
		
		HBox bpane = new HBox(10);
		bpane.setAlignment(Pos.TOP_CENTER);
		bpane.getChildren().addAll(Play, back5, skip5, new Label("Volume"), vol);
		bpane.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
		pane.setCenter(mediaview);
		pane.setBottom(bpane);
		
		Scene scene = new Scene(pane, 480, 360);
		mediaview.setFitWidth(scene.getWidth());
		mediaview.setFitHeight(scene.getHeight()-30);
		
		scene.widthProperty().addListener(ov ->{
			mediaview.setFitWidth(scene.getWidth());
		});
		scene.heightProperty().addListener(ov ->{
			mediaview.setFitHeight(scene.getHeight()-30);
		});
		
		
		primaryStage.setTitle("Elixir Media Streaming");
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(360);
		primaryStage.setMinWidth(480);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
