/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: This class manages the whole scheme, it makes the blocks, ports, links, does saving and loading the scheme and more
 */

package srcBacked;

import errorHandler.MyException;
import errorHandler.MyExceptionTwo;
import srcBacked.blocks.*;
import srcGUI.DragIcon;
import srcGUI.Liner;
import srcGUI.PortIcon;
import srcGUI.RootLayout;

import java.io.*;

/**
 * SchemeManager.java - Class SchemeManager, it is the main class which controls storing all schemes
 */
public class SchemeManager  {

    transient Scheme scheme;
    Liner liner;
    RootLayout rootLayout;
    boolean calculateOnlyOneStep;

    /**
     * Constructor for creating the class
     */

    public SchemeManager() {
        scheme = new Scheme();
        this.liner = new Liner(this);
        calculateOnlyOneStep = false;
    }

    // **************************** PUBLIC SECTION  *****************************

    /**
     * Setter for RootLayout
     * @param rootLayout canvas
     */
    public void setRootLayout(RootLayout rootLayout) {
        this.rootLayout = rootLayout;
        liner.setRootLayout(rootLayout);
    }

    /**
     * Method calculating only one step
     * @return calculateOnlyOneStep
     */
    public boolean calculateOnlyOneStep() {
        return calculateOnlyOneStep;
    }

    /**
     * Getter for scheme
     * @return scheme
     */

    public Scheme getScheme() {
        return scheme;
    }

    /**
     * Method which setting calculating only one step
     * @param calculateOnlyOneStep calculating block by one step
     */

    public void setCalculateOnlyOneStep(boolean calculateOnlyOneStep) {
        this.calculateOnlyOneStep = calculateOnlyOneStep;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */

    public MyPort[][] createBlockAdd_2_1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockAdd_2_1 block = new BlockAdd_2_1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */

    public MyPort[][] createBlockSub_2_1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockSub_2_1 block = new BlockSub_2_1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] createBlockAdd_1_1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockAdd_1_1 block = new BlockAdd_1_1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] createBlockSub_1_1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockSub_1_1 block = new BlockSub_1_1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] createBlockMul_1_1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockMul_1_1 block = new BlockMul_1_1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] createBlockDiv_2_1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockDiv_2_1 block = new BlockDiv_2_1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] createBlockMod_2_2(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockMod_2_2 block = new BlockMod_2_2(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] BlockConvertT1toT2(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockConvertT1toT2 block = new BlockConvertT1toT2(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which creating concrete Block, fxObject represents GUI block and returns in and output ports - eq.[[in][in][in]] [[out][out]]
     * @param posX coordinate of x
     * @param posY coordinate of y
     * @param fxObject  GUI object
     * @return  instance of MyPort
     * @throws MyException exception
     */
    public MyPort[][] BlockConvertT2toT1(Integer posX, Integer posY, DragIcon fxObject) throws MyException {
        BlockConvertT2toT1 block = new BlockConvertT2toT1(scheme.getLinker());
        MyPort[][] inAndOutPorts = {block.getPortsIn(),  block.getPortsOut()};
        addNewBlockToArrayList(block, posX, posY);
        scheme.getLinkedFxBackendBlock().put(fxObject, block);
        return inAndOutPorts;
    }

    /**
     * Method which returning Liner
     * @return liner
     */
    public Liner getLiner(){
        return liner;
    }

    /**
     * Method which gives Block by FxObject
     * @param di block instance
     * @return  instance of block
     */
    public Block getBlockByFXObject(DragIcon di){
        return scheme.getLinkedFxBackendBlock().get(di);
    }

    /**
     * Method which gives FxObject by Block
     * @param block instance
     * @return Fxblock instance
     */
    public DragIcon getFXObjectByBlock(Block block){
        return scheme.getLinkedFxBackendBlock().inverse().get(block);
    }

    /**
     * Method which gives FxPort to backed port
     * @param pi instance of blockFX
     * @return MyPort
     */
    public MyPort getPortByFXObject(PortIcon pi){
        return scheme.getLinkedFxBackendPort().get(pi);
    }

    /**
     * Method which gives backend port to FxPort
     * @param port port
     * @return  PortIcon
     */
    public PortIcon getFXObjectByPort(MyPort port){
        return scheme.getLinkedFxBackendPort().inverse().get(port);
    }

    /**
     * Method connect GUI representation of ports to backend ports
     * @param fxPort GUI port
     * @param myPort myport
     */
    //
    public void linkedFXPortsToBackend(PortIcon fxPort, MyPort myPort){
        scheme.getLinkedFxBackendPort().put(fxPort, myPort);
    }

    /**
     * Method GUI should be able to access individual ports
     * @param portOut  output port
     * @param portIn    input port
     * @throws MyException exception
     * @throws MyExceptionTwo   exception
     */
    public void addLink(MyPort portOut, MyPort portIn) throws MyException, MyExceptionTwo {
        scheme.getLinker().addLink(portOut, portIn);
    }

    /**
     * Method which clear all calculations
     */
    public void clearCalculations(){
        scheme.getLinker().clearPorts();
        for(DragIcon dragIcon : scheme.getFXDragIcons()){
            dragIcon.setStyle(""); // cancel the highlight in GUI
        }
    }

    /**
     * Method which clear all scheme(blocks,ports,links etc.)
     */

    public void clearScheme(){
        this.scheme = new Scheme();
        this.liner = new Liner(this);
        liner.setRootLayout(this.rootLayout);
        calculateOnlyOneStep = false;
        rootLayout.getRight_pane().getChildren().clear(); // clear GUI
    }

    /**
     * Method which updating positions of blocks
     * @param block instance of block
     * @param posX coordinate of x
     * @param posY coordinate of y
     */
    public void updatePosition(Block block, Integer posX, Integer posY){
        for (BlockPosition blockPosition : scheme.getArrayOfBlocks()) {
            if(blockPosition.getBlock() == block){
                blockPosition.setPosX(posX);
                blockPosition.setPosY(posY);
                return;
            }
        }
    }

    /**
     * Method which calculating values in blocks
     * @throws InterruptedException exception
     */
    public void calculate() throws InterruptedException{

        // check if inputs has values, or is connected, it this thread
        for (Block block : scheme.getLinker().getBlocksWithUnlinkedOutputPort()) {
            try {
                block.checkAllInputs();
            } catch (MyException e) {
                rootLayout.emptyPortAlert();
                break;
            }
        }

        // run new thread and te calculations
        Thread thread = new Thread(() -> {
            for (Block block :  scheme.getLinker().getBlocksWithUnlinkedOutputPort()) {
                try {
                    block.calculate(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    /**
     * Method which removing concrete block in scheme
     * @param block instance of the block
     * @throws MyException when cannot remove connected block
     */
    public void removeBlock(Block block) throws MyException {
        for (BlockPosition blockPosition : scheme.getArrayOfBlocks()) {
            if(blockPosition.getBlock() == block){
                if(scheme.getLinker().isThisBlockConnected(block))
                    throw new MyException("Cannot remove connected block");
                scheme.getArrayOfBlocks().remove(blockPosition);
                return;
            }
        }
    }

    /**
     * Method whichs save concrete scheme which user will choose
     * @param file the file
     * @throws MyException when FileNotFoundException
     * @throws IOException when IOException
     */
    public void saveScheme(File file) throws MyException, IOException {
            ObjectOutputStream objectOutputStream = null;
            FileOutputStream fileOutputStream = null;
            try{
                file.delete();
                fileOutputStream = new FileOutputStream(file, true);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(scheme);
            } catch (FileNotFoundException e){
                throw new MyException("The file not found when saving the schema");
            } finally {
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
            }
    }

    /**
     * Method which load the concrete scheme which will user choose
     * @param filename the file
     * @throws MyException when loading wrong file
     */
    public void loadScheme(File filename) throws MyException {
        try{
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.scheme = (Scheme)objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e){
            throw new MyException("The file not found when saving the schema");
        } catch (IOException e) {
            throw new MyException("IOException when loading the file");
        } catch (ClassNotFoundException e) {
            throw new MyException("ClassNotFoundException when loading the file");
        }
    }


    // **************************** PRIVATE SECTION  *****************************

    /**
     * Method which store blocks to array list
     * @param block the block
     * @param posX its X position
     * @param posY its Y position
     */
    private void addNewBlockToArrayList(Block block, Integer posX, Integer posY){
        BlockPosition blockPosition = new BlockPosition(block, posX, posY);
        scheme.addBlockPosition(blockPosition);
    }

}
