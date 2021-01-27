/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: The block class, blocks are the basic components of the scheme
 */

package srcBacked;

import errorHandler.MyException;
import srcBacked.blocks.*;
import srcBacked.port.MyPortIn;
import srcBacked.port.MyPortOut;

import java.io.Serializable;
import java.util.Map;

/**
 * Block.java - Class block, creates the instances of the blocks in backend which are later calculated
 */
public class Block implements Serializable {

    private MyPortIn[] portsIn;
    private MyPortOut[] portsOut;
    boolean isCalculated;
    private Linker linker;

    private static final long serialVersionUID = 2485824512991791982L;

    /**
     * Constructor for creating the class
     * @param portsIn eg. [[type1, type2][type3]] - two ports, first has to types
     * @param portsOut eg. [[type1][type2]] - two ports
     * @param linker    block linker
     */
    public Block(String[][] portsIn, String[][] portsOut, Linker linker) {

        this.portsIn = new MyPortIn[portsIn.length];
        this.portsOut = new MyPortOut[portsOut.length];
        this.isCalculated = false;
        this.linker = linker;


        for (int i = 0; i < portsIn.length; i++) {
            // i is eg [type1, type2]
            this.portsIn[i] = new MyPortIn(portsIn[i], this);
        }

        for (int i = 0; i < portsOut.length; i++) {
            this.portsOut[i] = new MyPortOut(portsOut[i], this);
        }
    }

    /**
     * Gets linker
     * @return linker
     */
    public Linker getLinker() {
        return linker;
    }

    /**
     * Gets ports in
     * @return ports in
     */
    public MyPortIn[] getPortsIn(){
        return portsIn;
    }

    /**
     * Gets ports out
     * @return ports out
     */
    public MyPortOut[] getPortsOut(){
        return portsOut;
    }

    /**
     * Sets if the block is calculated or not
     * @param value boolean value
     */
    public void setCalculated(boolean value){
        this.isCalculated = value;
    }

    /**
     * Is the block calculated?
     * @return boolean calculated
     */
    public boolean isCalculated(){
        return this.isCalculated;
    }

    /**
     * Gets information about the block and its ports
     * @return String
     * @throws Exception When missing if statement inside of the method
     */
    public String getData() throws Exception {

        StringBuilder wholeString = new StringBuilder();

        if(this instanceof BlockAdd_2_1){
            wholeString.append("Block(+) - (2-IN)(1-OUT)");
        } else if (this instanceof BlockSub_2_1){
            wholeString.append("Block(-) - (2-IN)(1-OUT)");
        } else if (this instanceof BlockAdd_1_1){
            wholeString.append("Block(+) - (1-IN)(1-OUT)");
        } else if (this instanceof BlockSub_1_1){
            wholeString.append("Block(-) - (1-IN)(1-OUT)");
        } else if (this instanceof BlockMul_1_1){
            wholeString.append("Block(*) - (1-IN)(1-OUT)");
        } else if (this instanceof BlockDiv_2_1){
            wholeString.append("Block(/) - (2-IN)(1-OUT)");
        } else if (this instanceof BlockMod_2_2){
            wholeString.append("Block(/ and %) - (2-IN)(2-OUT)");
        } else if (this instanceof BlockConvertT1toT2){
            wholeString.append("Block(T1 -> T2) - (1-IN)(1-OUT)");
        } else if (this instanceof BlockConvertT2toT1){
            wholeString.append("Block(T2 -> T1) - (1-IN)(1-OUT)");
        } else {
            throw new Exception("Missing if statement in block getData().");
        }

        wholeString.append("\n");

        wholeString.append("INPORTS:");
        for (int i = 0; i < portsIn.length; i++) {
            wholeString.append("\n");
            wholeString.append((portsIn[i].getData()));
        }

        wholeString.append("\n");

        wholeString.append("OUTPORTS:");
        for (int i = 0; i < portsOut.length; i++) {
            wholeString.append("\n");
            wholeString.append(portsOut[i].getData());
        }

        return wholeString.toString();
    }

    /**
     * Just print the data about the block, for debugging
     */
    public void printData() {

        System.out.println("--------------------");
        try {
            System.out.println(getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");
    }

    /**
     * Method which gives input ports values
     * @param portIndex index of input port
     * @param key   key value
     * @return  Double
     * @throws MyException exception
     */
    public java.lang.Double getPortInValue(int portIndex, String key) throws MyException{
        if(portIndex < 0 || portIndex >= getNumberOfInputPorts()){
            throw new MyException("Wrong index of input ports getPortInValue()");
        }

        Map<String, Double> map = getPortInByIndex(portIndex).getData();
        if(map.containsKey(key)){
            return map.get(key);
        } else {
            throw new MyException("Wrong key when getting a value from a port by getPortInValue()");
        }
    }

    /**
     * Method which gives all ouput port values
     * @param portIndex index of out port
     * @param key   key value
     * @return  Double
     * @throws MyException Wrong index of input ports
     */
    public java.lang.Double getPortOutValue(int portIndex, String key) throws MyException{
        if(portIndex < 0 || portIndex >= getNumberOfOutputPorts()){
            throw new MyException("Wrong index of input ports getPortInValue()");
        }

        Map<String , Double> map = getPortOutByIndex(portIndex).getData();
        if(map.containsKey(key)){
            return map.get(key);
        } else {
            throw new MyException("Wrong key when getting a value from a port by getPortInValue()");
        }
    }

    /**
     * Method which gives count of input ports
     * @return  integer
     */
    public int getNumberOfInputPorts(){
        return this.portsIn.length;
    }

    /**
     * Method which gives count of output ports
     * @return  integer
     */
    public int getNumberOfOutputPorts(){
        return this.portsOut.length;
    }

    /**
     * Method which sets input ports values
     * @param data  data of port
     * @param index index of port
     * @throws MyException Wrong index of input ports
     */

    public void setPortInValues(Map<String, Double> data, int index) throws MyException {
        if((index < 0 || index >= getNumberOfInputPorts())) {
            throw new MyException("Wrong index of input ports setPortInValues()");
        }
        portsIn[index].setData(data);
    }

    /**
     * Method which sets output ports values
     * @param data  data of port
     * @param index index of port
     * @throws MyException Wrong index of output ports
     */
    public void setPortOutValues(Map<String, Double> data, int index) throws MyException {
        if((index < 0 || index >= getNumberOfInputPorts())) {
            throw new MyException("Wrong index of output ports setPortOutValues()");
        }
        portsOut[index].setData(data);
    }

    /**
     * Method which helping calculating whole process
     * @param sm scheme manager
     * @throws MyException  exception
     * @throws InterruptedException exception
     */
    protected void calculateHelper(SchemeManager sm) throws MyException, InterruptedException {

    }

    /**
     * Method which checks if inputs has values
     * @throws MyException exception
     */
    public void checkInputs() throws MyException {

    }

    /**
     * Method which gets the GUI reference of the block that is being calculate
     * @param sm scheme manager
     * @throws InterruptedException exception
     */
    protected void GUIHighlightBlock(SchemeManager sm) throws InterruptedException {
        // TODO styling
        // style set when being calculated
        System.out.println("Block changed style - counting");
        sm.getFXObjectByBlock(this).setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 50, 0, 0, 0)");
        // sleep time
        Thread.sleep(2000);
        // style set when calculation is done
        System.out.println("Block changed style - done");
        sm.getFXObjectByBlock(this).setStyle("-fx-effect: dropshadow(three-pass-box, rgb(0,0,0), 4, 2, 0, 0)");
    }

    /**
     * Method which calculating block values recursively
     * @param sm scheme manager
     * @throws MyException Warning
     * @throws InterruptedException Exception
     */
    //scheme manager because calculate has to graphically show in GUI which block is being calculated
    public void calculate(SchemeManager sm) throws MyException, InterruptedException {

        if(!isCalculated()){ // if this block is not already calculated, calculate. Its because of that, we can call the same block more times when calling calculate on unconnected output ports at the beginning
            for(int i = 0; i < getNumberOfInputPorts(); i++){ // iterate through ports in
                MyPortIn portIn = getPortInByIndex(i);
                if(getLinker().isThisInputPortConnected(portIn)){ // this port in is connected
                    MyPortOut connectedOutputPort = getLinker().getOutputPortLinkedToThisInputPort(portIn);
                    if(!(connectedOutputPort.isParentBlockCalculated())){ // calculate the block we are reading from when not already calculated..
                        connectedOutputPort.calculateParentBlock(sm); // calculate first
                    }
                    getLinker().setInportPortByConnectedOutputPortValue(portIn); // get the map on the output port
                    System.out.println(portIn.getData());
                } // else user has to input the values
            }
            calculateHelper(sm);
            if (sm.calculateOnlyOneStep()){
                throw new MyException("Actually not a exception, just warning. Calculated only one step.");
            }
        }
    }

    /**
     * Method which checks if all inputs has values
     * @throws MyException exception
     * @throws InterruptedException exception
     */
    public void checkAllInputs() throws MyException, InterruptedException {

        for(int i = 0; i < getNumberOfInputPorts(); i++){ // iterate through ports in
            MyPortIn portIn = getPortInByIndex(i);
            if(getLinker().isThisInputPortConnected(portIn)){ // this port in is connected
                MyPortOut connectedOutputPort = getLinker().getOutputPortLinkedToThisInputPort(portIn);
                connectedOutputPort.checkParentBlockInputs();
            } // else user has to input the values
        }
        checkInputs();
    }

    /**
     * Method which gives input ports by index
     * @param index port index
     * @return portsIn[index]
     * @throws MyException exception
     */
    public MyPortIn getPortInByIndex(int index) throws MyException {
        if(index < 0 || index >= getNumberOfInputPorts()){
            throw new MyException("Wrong index of input ports getPortInByIndex()");
        }
        return portsIn[index];
    }

    /**
     * Method which gives output ports by index
     * @param index index port
     * @return portsOut[index]
     * @throws MyException exception
     */
    public MyPortOut getPortOutByIndex(int index) throws MyException {
        if(index < 0 || index >= getNumberOfOutputPorts()){
            throw new MyException("Wrong index of input ports getPortOutByIndex()");
        }
        return portsOut[index];
    }
}

