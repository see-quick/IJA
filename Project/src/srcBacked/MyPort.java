/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Basic class of the ports of the blocks
 */

package srcBacked;

import errorHandler.MyException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * MyPort.java - Class MyPort, it is the class which represets all ports in the scheme
 */
public class MyPort implements Serializable {

    private Map<String , Double> data = new HashMap<String , Double>();
    private Block parentBlock;
    private Linker linker;

    private static final long serialVersionUID = 2451501790672335506L;

    /**
     * Constructor for Myport, creates HashMap with keys, and puts values null
     * @param args arguments
     * @param parentBlock parent block
     */
    public MyPort(String[] args, Block parentBlock){
        for ( String arg : args){
            data.put(arg, null);
        }
        this.parentBlock = parentBlock;
        linker = parentBlock.getLinker();
    }

    /**
     * Method which gives parent block
     * @return instance of block
     */
    public Block getParentBlock() {
        return parentBlock;
    }

    /**
     * Method which gives Linker
     * @return instance of linker
     */
    public Linker getLinker() {
        return linker;
    }

    /**
     * Method which returns data of map
     * @return data of mapa
     */
    public Map<String, Double> getData() {
        return data;
    }

    /**
     * Helful Method which prints port data
     */
    public void printPortData(){
        System.out.println(getData());
    }

    // TODO: If key is not there, If value is null ... ???

    /**
     * Method which gives value by key
     * @param key concrete key
     * @return double value
     */
    public Double getValueByKey(String key){
        return getData().get(key);
    }

    // TODO check if it can be cast

    /**
     * Method which returning boolean value if concrete parent block calculated
     * @return  boolean value
     */
    public boolean isParentBlockCalculated(){
        return parentBlock.isCalculated();
    }

    //  throws NoSuchMethodException, InvocationTargetException, IllegalAccessException

    /**
     * Method which calculating parent block
     * @param sm scheme manager
     * @throws MyException exception
     * @throws InterruptedException exception
     */
    public void calculateParentBlock(SchemeManager sm) throws MyException, InterruptedException {
        parentBlock.calculate(sm);
    }

    /**
     * Method which checking all inputs in block
     * @throws MyException  exception
     * @throws InterruptedException exception
     */
    public void checkParentBlockInputs() throws MyException, InterruptedException {
        parentBlock.checkAllInputs();
    }

    /**
     * Method which returns data size
     * @return integer
     */
    public Integer howManyValues(){
        return data.size();
    }

    /**
     * Method which returns all keys from array
     * @return array of strings
     */
    public String[] getKeys(){
        return data.keySet().toArray(new String[data.size()]);
    }

    /**
     * Method which sets data in map
     * @param data map of data
     * @throws MyException Port incompatible types
     */
    public void setData(Map<String, Double> data) throws MyException {

        if(this.data.keySet().equals(data.keySet())){
            this.data = data;
        }
        else{
            throw new MyException("Port incompatible types");
        }
    }

    /**
     * Method which clear all values for concrete string key
     */
    public void clearValues(){

        for (String key : data.keySet()){
            data.put(key,null);
        }
    }

}
