/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Subclass of a class Block, defines one kind of a block which can be used in calculations
 */


package srcBacked.blocks;

import errorHandler.MyException;
import srcBacked.Block;
import srcBacked.Linker;
import srcBacked.SchemeManager;

import java.util.HashMap;
import java.util.Map;

/**
 * BlockConvertT1toT2.java - Subclass of a class Block, defines one kind of a block which can be used in calculations
 */
public class BlockConvertT1toT2 extends Block {

    static private final String[][] portsIn = new String[][]{{"T1"}};
    static private final String[][] portsOut = new String[][]{{"T2"}};

    /**
     * Constructor for creating the class
     * @param linker An instance of the linker, which manages and saves all the links
     * @throws MyException When wrong number or ports
     */
    public BlockConvertT1toT2(Linker linker) throws MyException {

        super(portsIn, portsOut , linker);

        if (super.getNumberOfInputPorts() != 1 || super.getNumberOfOutputPorts() != 1) {
            throw new MyException("Wrong number of ports was entered when constructing a BlockAdd_2_1");
        }
    }

    /**
     * Checks if the inputs of this block has values on ports or are connected
     * @throws MyException When input values are not set
     */
    public void checkInputs() throws MyException {

        if(!(getLinker().isThisInputPortConnected(getPortInByIndex(0))) && getPortInValue(0, "T1") == null){
            throw new MyException("Set values in input ports first before calling calculate()");
        }
    }

    /**
     * Calculates this one concrete block and sets the results to int input port(s)
     * @param sm An instance of SchemeManager which manages everything
     * @throws MyException When input values are not set
     * @throws InterruptedException When thread work went wrong
     */
    @Override
    protected void calculateHelper(SchemeManager sm) throws MyException, InterruptedException {

        checkInputs();

        Double temp1 = getPortInValue(0, "T1");

        double result = temp1;
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("T2", result);

        super.GUIHighlightBlock(sm);
        super.setPortOutValues(map, 0);
        // DO NOT FORGET THIS
        setCalculated(true);
    }

}
