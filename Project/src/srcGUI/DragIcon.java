package srcGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;

/**
 * DragIcon.java - Class DragIcon, which represents concrete block
 */
public class DragIcon extends AnchorPane implements Serializable{
	

	private DragIconType mType = null;

	/**
	 * Constructor for DragIcom
	 */
	public DragIcon() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/srcGUI/resources/DragIcon.fxml")
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
	 * Method which inicializate
	 */
	@FXML
	private void initialize() {}

	/**
	 * Method which relocating points
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

	/**
	 * Method which gives type of block
	 * @return the type
	 */
	public DragIconType getType () { return mType; }

	/**
	 * Method which sets concrete type to the block
	 * @param type the type
	 */
	public void setType (DragIconType type) {
		
		mType = type;
		
		getStyleClass().clear();
		getStyleClass().add("dragicon");

		switch (mType) {
		
		case BlockAdd_1_1:
			getStyleClass().add("icon-BlockAdd_1_1");
		break;

		case BlockAdd_2_1:
			getStyleClass().add("icon-BlockAdd_2_1");
		break;

		case BlockSub_2_1:
			getStyleClass().add("icon-BlockSub_2_1");
		break;

		case BlockMod_2_2:
			getStyleClass().add("icon-BlockMod_2_2");
		break;

		case BlockDiv_2_1:
			getStyleClass().add("icon-BlockDiv_2_1");
		break;

		case BlockMul_1_1:
			getStyleClass().add("icon-BlockMul_1_1");
		break;

		case BlockSub_1_1:
			getStyleClass().add("icon-BlockSub_1_1");
		break;

		case BlockConvertT1toT2:
			getStyleClass().add("icon-BlockConvertT1toT2");
		break;
		case BlockConvertT2toT1:
			getStyleClass().add("icon-BlockConvertT2toT1");
		break;

		default:
		break;
		}
	}
}
