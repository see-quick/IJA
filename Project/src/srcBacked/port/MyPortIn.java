/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Subclass of a class MyPortIn, defines the input ports of the blocks
 */


package srcBacked.port;

import srcBacked.Block;
import srcBacked.MyPort;

/**
 * MyPortIn.java - Subclass of a class MyPort, defines ports in in the blocks
 */
public class MyPortIn extends MyPort {
    /**
     * Constructor for creating the class, creates HashMap with keys, and puts values null
     * @param args Types of the values
     * @param parentBlock Port should know who is is parent block
     */
    public MyPortIn(String[] args, Block parentBlock)
    {
        super(args, parentBlock);
        super.getLinker().addMyPortIn(this);
    }
}
