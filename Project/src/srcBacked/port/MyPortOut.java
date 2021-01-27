/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Subclass of a class MyPortIn, defines the output ports of the blocks
 */

package srcBacked.port;

import srcBacked.Block;
import srcBacked.MyPort;

/**
 * MyPortOut.java - Subclass of a class MyPort, defines ports out in the blocks
 */
public class MyPortOut extends MyPort  {
    /**
     * Constructor for creating the class, creates HashMap with keys, and puts values null
     * @param args Types of the values
     * @param parentBlock Port should know who is is parent block
     */
    public MyPortOut(String[] args, Block parentBlock)
    {
        super(args, parentBlock);
        super.getLinker().addMyPortOut(this);
    }
}
