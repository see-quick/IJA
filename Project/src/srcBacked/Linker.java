/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Describes the class Linker, Linker is used for saving and managing the links between the blocks and its ports
 */

package srcBacked;

import errorHandler.MyException;
import errorHandler.MyExceptionTwo;
import srcBacked.port.MyPortIn;
import srcBacked.port.MyPortOut;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Linker.java - Class Linker, it is the class which links all ports in concrete blocks
 */
public class Linker implements Serializable {

    // hashMap storing all the connections
    private Map<MyPortIn, MyPortOut> linksMap = new HashMap<MyPortIn, MyPortOut>();
    // arraylist for adding ports in and ports out...
    private ArrayList<MyPortIn> allPortsIn = new ArrayList<>();
    private ArrayList<MyPortOut> allPortsOut = new ArrayList<>();

    private static final long serialVersionUID = 3478769158068306825L;

    private MyPortIn lastInsertedMyPortIn;
    private MyPortOut lastInsertedMyPortOut;

    /**
     * Constructor for linker
     */
    public Linker(){
    }

    /**
     * Method which gives linked map
     * @return map of ports
     */
    public Map<MyPortIn, MyPortOut> getLinksMap() {
        return linksMap;
    }

    /**
     * Method which adding link and controling cycle dependency
     * @param portOut   output ports
     * @param portIn    input ports
     * @throws MyException   addLink() needs instance of MyPortIn and MyPortOut
     * @throws MyExceptionTwo   Cycle dependency detected
     */
    public void addLink(MyPort portOut, MyPort portIn) throws MyException, MyExceptionTwo {
        if (portIn instanceof MyPortIn && portOut instanceof MyPortOut) {
            this.addLinkBetweenInAndOut((MyPortOut)portOut, (MyPortIn)portIn);
        } else {
            throw new MyException("addLink() needs instance of MyPortIn and MyPortOut");
        }
        // check for cycle dependency
        if(hasCycles()){
            // throw ex, catch in GUI
            throw new MyExceptionTwo("Cycle dependency detected.");
        }
        printNumberOfLinks();
    }

    /**
     * Helful method which prints number of links
     */
    public void printNumberOfLinks(){
        System.out.println("Number of links in backend: " + linksMap.size());
    }

    /**
     * Method which controlling if blocks has cycle steps
     * @param iteratorBlock passing throw connections
     * @param originalBlock block to compare the iterator block
     * @param linkBlocks   linked block
     * @return boolean value
     */
    private boolean hasCyclesStep(Block iteratorBlock, Block originalBlock, Map<Block, Block> linkBlocks){
        if(iteratorBlock == originalBlock){
            System.out.println("Cycle found.");
            return true; // found cycle
        }
        if(linkBlocks.containsKey(iteratorBlock)){
            // FIXME, what is we have A->B, A->C, key, which has more values
            if(hasCyclesStep(linkBlocks.get(iteratorBlock), originalBlock, linkBlocks)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method which controlling if concrete block has cycles
     * @return boolean value
     */
    public boolean hasCycles(){
        Map<Block, Block> linkBlocks = new HashMap<Block, Block>();

        // create hash map of connections, but generally with the blocks
        for (Map.Entry<MyPortIn, MyPortOut> entry : linksMap.entrySet()) {
            linkBlocks.put(entry.getValue().getParentBlock(), entry.getKey().getParentBlock());
        }
//        System.out.println("linkBlocks: " + linkBlocks);

        // check for cycles, first is out, second is in
        boolean result = false;
        for (Map.Entry<Block, Block> entry : linkBlocks.entrySet()) {
            if(hasCyclesStep(entry.getValue(), entry.getKey(), linkBlocks)){ // if returned true
                removeLastInsertedLink(); // remove the last inserted link, which is creating the cycle
                return true;
            }
        }
        return false;
    }

    /**
     * Method which gives blocks with unlinked output port
     * @return ArrayList of blocks
     */
    public ArrayList<Block> getBlocksWithUnlinkedOutputPort(){
        ArrayList<Block> result = new ArrayList<>();

        for (MyPortOut myPortOut : allPortsOut) {
            if (!(linksMap.containsValue(myPortOut))){
                if(!(result.contains(myPortOut))){
                    result.add(myPortOut.getParentBlock());
                }
            }
        }
        return result;
    }

    /**
     * Method which returns boolean value if block is connected
     * @param block instance of block
     * @return boolean value
     */
    public boolean isThisBlockConnected(Block block){
        for (MyPortIn portIn : block.getPortsIn()){
            if(linksMap.containsKey(portIn))
                return true;
        }
        for (MyPortOut portOut : block.getPortsOut()){
            if(linksMap.containsValue(portOut))
                return true;
        }
        return false;
    }

    /**
     * Method which adding all input ports
     * @param myPortIn input ports
     */
    public void addMyPortIn(MyPortIn myPortIn){
        allPortsIn.add(myPortIn);
    }

    /**
     * Method which adding all output ports
     * @param myPortOut output ports
     */
    public void addMyPortOut(MyPortOut myPortOut){
        allPortsOut.add(myPortOut);
    }

    // private function, public is addLink()

    /**
     * Method which adding link between input and ouput port
     * @param portOut output ports
     * @param portIn input ports
     * @throws MyException  Cannot link incompatible types.
     */
    private void addLinkBetweenInAndOut(MyPortOut portOut, MyPortIn portIn) throws MyException {
        // if types are not compatible
        if(!(portIn.getData().keySet().equals(portOut.getData().keySet()))){
            throw new MyException("Cannot link incompatible types.");
        }

        if(linksMap.containsKey(portIn)){
            linksMap.remove(portIn);
        }
        if(linksMap.containsValue(portOut)){
            // better would be to use bidirectional map
            for (MyPortIn portIn2 : linksMap.keySet()){
                if(linksMap.get(portIn2) == portOut)
                    linksMap.remove(portIn2);
            }
        }

        saveLastLink(portIn, portOut);
        linksMap.put(portIn, portOut);
    }

    /**
     * Method which remember last link
     * @param lastInsertedMyPortIn
     * @param lastInsertedMyPortOut
     */
    private void saveLastLink(MyPortIn lastInsertedMyPortIn, MyPortOut lastInsertedMyPortOut){
        this.lastInsertedMyPortIn = lastInsertedMyPortIn;
        this.lastInsertedMyPortOut = lastInsertedMyPortOut;
    }

    /**
     * Method which remove last inserted link
     */
    public void removeLastInsertedLink(){
        linksMap.remove(this.lastInsertedMyPortIn, this.lastInsertedMyPortOut);
    }

    /**
     * Method which gives output port linked to concrete input port
     * @param portIn input ports
     * @return output ports
     */
    public MyPortOut getOutputPortLinkedToThisInputPort(MyPortIn portIn){
        return linksMap.get(portIn);
    }

    /**
     * Method which gives input port linked to concrete output port
     * @param portIn input ports
     * @return  map of linked input ports
     */
    public Map<String , Double> getOutputPortMapLinkedToThisInputPort(MyPortIn portIn){
        return linksMap.get(portIn).getData();
    }

    /**
     * Method which sets input port by connected output port value
     * @param portIn    input ports
     * @throws MyException  exception
     */
    public void setInportPortByConnectedOutputPortValue(MyPortIn portIn) throws MyException {
        System.out.println("Setting input data from connection. In before is "  + portIn.getData() + "  out is " + linksMap.get(portIn).getData());
        portIn.setData(linksMap.get(portIn).getData());
        System.out.println("Port in after set data is "  + portIn.getData());
    }

    /**
     * Method which controlling if concrete block is connected
     * @param portIn input ports
     * @return  boolean value
     */
    public boolean isThisInputPortConnected(MyPortIn portIn){
        if(linksMap.get(portIn) == null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method which clear all ports values
     */
    public void clearPorts(){

        for (MyPortIn myPortIn : allPortsIn) {
            myPortIn.clearValues();
            myPortIn.getParentBlock().setCalculated(false);
        }
        for (MyPortOut myPortOut : allPortsOut) {
            myPortOut.clearValues();
            myPortOut.getParentBlock().setCalculated(false);
        }
    }

}
