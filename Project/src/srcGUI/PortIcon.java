package srcGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;
/**
 * PortIcon.java - Class PortIcon, which represents concrete ports in block
 */
public class PortIcon extends AnchorPane implements Serializable{
	private DragIconType mType = null;

	/**
	 * Constructor for port icon
	 */
	public PortIcon() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/srcGUI/resources/PortIcon.fxml")
				);
		
		fxmlLoader.setRoot(this); 
		fxmlLoader.setController(this);
		
		try { 
			fxmlLoader.load();
        
		} catch (IOException exception) {
		    throw new RuntimeException(exception);
		}
	}

	/**
	 * Method which initializing
	 */
	@FXML
	private void initialize() {}

	/**
	 * Method which relocating point
	 * @param p
	 */
	public void relocateToPoint (Point2D p) {

		//relocates the object to a point that has been converted to
		//scene coordinates
		Point2D localCoords = getParent().sceneToLocal(p);

		relocate (
				(int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
				(int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
			);
	}
	
}
