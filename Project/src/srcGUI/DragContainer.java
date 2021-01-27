package srcGUI;

import javafx.scene.input.DataFormat;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * DragContainer.java - Class DragContainer, it is the class which contains of FX blocks
 */
public class DragContainer implements Serializable {

	private static final long serialVersionUID = -5994484067950869123L;

	public static final DataFormat AddNode = new DataFormat("ahoj");

	private final List <Pair<String, Object> > mDataPairs = new ArrayList <Pair<String, Object> > ();
	
	public void addData (String key, Object value) {
		mDataPairs.add(new Pair<String, Object>(key, value));
	}

	/**
	 * Method which gives value by string key
	 * @param key the key
	 * @param <T>
	 * @return
	 */
	public <T> T getValue (String key) {
		
		for (Pair<String, Object> data: mDataPairs) {
			
			if (data.getKey().equals(key))
				return (T) data.getValue();
				
		}
		
		return null;
	}

	/**
	 * Method which returns data pairs
	 * @return data pair
	 */
	public List <Pair<String, Object> > getData () { return mDataPairs; }	
}
