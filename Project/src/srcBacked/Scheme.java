/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Class which saves everything about the current scheme, block, ports, links, positions
 */

package srcBacked;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import srcGUI.DragIcon;
import srcGUI.PortIcon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
/**
 * Scheme.java - Class Scheme, it is the class which controls whole data flow of program
 */
public class Scheme implements Serializable{

    private ArrayList<BlockPosition> arrayOfBlocks;
    private Linker linker; // links in backend
    private BiMap<DragIcon, Block> linkedFxBackendBlock;
    private BiMap<PortIcon, MyPort> linkedFxBackendPort;

    private static final long serialVersionUID = -5238114094979801550L;

    /**
     * Constructor for creating the class
     */
    public Scheme() {
        linkedFxBackendBlock = HashBiMap.create();
        linkedFxBackendPort = HashBiMap.create();
        arrayOfBlocks = new ArrayList<>();
        linker = new Linker();
    }

    /**
     * Method which clears linked backend and Fxblocks
     */
    public void clearlinkedFxBackendBlock(){
        linkedFxBackendBlock.clear();
    }

    /**
     * Method which clears linked backed and Fx ports
     */
    public void clearlinkedFxBackendPort(){
        linkedFxBackendPort.clear();
    }

    /**
     * Method which gives Blocks Icon
     * @return set of blocks
     */
    public Set<DragIcon> getFXDragIcons(){
        return linkedFxBackendBlock.keySet();
    }

    /**
     * Method which gives ports icons
     * @return set of ports
     */
    public Set<PortIcon> getFXPortIcons(){
        return linkedFxBackendPort.keySet();
    }

    /**
     * Method which returns linked Fx and backed block
     * @return linked Fx and backed block
     */
    public BiMap<DragIcon, Block> getLinkedFxBackendBlock() {
        return linkedFxBackendBlock;
    }

    /**
     * Method which returns linked Fx and backed port
     * @return linked Fx and backed port
     */
    public BiMap<PortIcon, MyPort> getLinkedFxBackendPort() {
        return linkedFxBackendPort;
    }

    /**
     * Method which return array of blocks
     * @return array of blocks
     */
    public ArrayList<BlockPosition> getArrayOfBlocks() {
        return arrayOfBlocks;
    }

    /**
     * Method which adding all block postions to array
     * @param blockPosition position of block
     */
    public void addBlockPosition(BlockPosition blockPosition){
        arrayOfBlocks.add(blockPosition);
    }

    /**
     * Method which gives linker
     * @return linker
     */
    public Linker getLinker() {
        return linker;
    }

    /**
     * Method which sets linker
     * @param linker sets the linker
     */
    public void setLinker(Linker linker) {
        this.linker = linker;
    }
}
