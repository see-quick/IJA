package srcGUI;

import errorHandler.MyException;
import errorHandler.MyExceptionTwo;
import javafx.scene.control.Alert;
import javafx.scene.shape.Line;
import srcBacked.SchemeManager;
import srcBacked.port.MyPortIn;
import srcBacked.port.MyPortOut;

import java.util.ArrayList;

/**
 * Liner.java - Class responsible for drawing the links between ports in gui
 */
public class Liner {
    private boolean portClicked; // check when connecting nodes if we clicked for the first or second time
    private MyPortOut portOut;
    private SchemeManager sm;
    private RootLayout rootLayout;
    private ArrayList<Line> linesGUI;

    /**
     * Constructor for the class
     * @param sm Instance of the SchemeManager which controls everything
     */
    public Liner(SchemeManager sm) {
        this.portClicked = false;
        this.portOut = null;
        this.sm = sm;
        linesGUI = new ArrayList<Line>();
    }

    /**
     * Sets the rootLayout, needs to be set after creatin of this class
     * @param rootLayout the instance of the RootLayout class
     */
    public void setRootLayout(RootLayout rootLayout) {
        this.rootLayout = rootLayout;
    }

    public boolean wasClicked(){
        return portClicked;
    }

    /**
     * Draws all lines between ports in the gui
     */
    public void drawAllLinks(){
        deleteGUILines();
        for(MyPortIn portIn : sm.getScheme().getLinker().getLinksMap().keySet()){
            drawLine(sm.getFXObjectByPort(portIn), sm.getFXObjectByPort(sm.getScheme().getLinker().getOutputPortLinkedToThisInputPort(portIn)));
        }
    }

    /**
     * Deletes the lines from GUI
     */
    private void deleteGUILines(){
        for(Line line : linesGUI){
            rootLayout.right_pane.getChildren().remove(line);
        }
        linesGUI.clear();
    }

    /**
     * Draws one line in GUI between two javaFX object (ports)
     * @param portIcon2 The first port
     * @param portIcon1 The second port
     */
    public void drawLine(PortIcon portIcon2, PortIcon portIcon1){
        System.out.println("Creating a line");
        Line line = new Line();
        line.setStartX(portIcon1.getLayoutX() + 20);
        line.setStartY(portIcon1.getLayoutY() + 10);
        line.setEndX(portIcon2.getLayoutX());
        line.setEndY(portIcon2.getLayoutY() + 10);
        rootLayout.right_pane.getChildren().add(line);
        linesGUI.add(line);
    }

    /**
     * Call this method with every click on the port, it is responsible for handling creating the links when connecting the right ports, Click first out port, then in port to create the link
     * @param portIcon The port icon which was clicked
     * @throws MyException error when connecting wrong ports
     */
    public void click(PortIcon portIcon) throws MyException {
        if(sm.getPortByFXObject(portIcon) instanceof MyPortOut) {
            this.portOut = (MyPortOut) sm.getPortByFXObject(portIcon);
            System.out.println("Port out saved. Now click on port in.");
        } else {
            if(portOut == null){
                throw new MyException("Choose the output port first.");
            } else {
                try {
                    sm.addLink(portOut, (MyPortIn)sm.getPortByFXObject(portIcon));
                    drawAllLinks(); // when cycle dependency this will not be called
                    System.out.println("Port in clicked after port out. Connected.");
                } catch (MyException exception){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Incompatible port types");
                    alert.setContentText("Please, check the types in ports before connecting them. You cannot connect incompatible types.");
                    alert.showAndWait();
                    System.out.println("NOT connected, incompatible types.");
                } catch (MyExceptionTwo ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Cycles shell not pass");
                    alert.setContentText("You cannot create cycle dependency by linking block to cycle.");
                    alert.showAndWait();
                }
            }
        }
    }
}
