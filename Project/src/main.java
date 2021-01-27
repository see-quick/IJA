import errorHandler.MyException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import srcBacked.SchemeManager;
import srcGUI.RootLayout;

/* IMPORTS FROM BACKEND */
/**
 * main.java - Class main, which runs whole process of program
 */
public class main extends Application {

	/**
	 * Method start which creating scene and sets whole layout of app
	 * @param primaryStage
	 * @throws MyException
	 */
	@Override
	public void start(Stage primaryStage) throws MyException {
		BorderPane root = new BorderPane();

		try {
			Scene scene = new Scene(root,1190,720);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

		SchemeManager sm = new SchemeManager();
		RootLayout rootLayout = new RootLayout(sm);
		// do not touch my precious, magic here
		sm.setRootLayout(rootLayout);

		root.setCenter(rootLayout);
	}

	/**
	 * Method main for arguments
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
