/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Abstraction of the block class, except the block, we save also its position to be able to loaa the scheme later from a file
 */

package srcBacked;

import java.io.Serializable;

/**
 * BlockPosition.java - Class BlockPosition, it is the class which stores positions of block instances
 */
public class BlockPosition implements Serializable {

    private Integer posX;
    private Integer posY;
    private Block block;

    /**
     * Constructor of BlockPosition
     * @param block instance of block
     * @param posX  x coordinate
     * @param posY  y coordinate
     */
    public BlockPosition(Block block, Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
        this.block = block;
    }

    /**
     * Method which gives position of x coordinate
     * @return integer
     */
    public Integer getPosX() {
        return posX;
    }

    /**
     * Method which sets position of x coordinate
     * @param posX x coordinate
     */
    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    /**
     * Method which gives position of y coordinate
     * @return Integer
     */
    public Integer getPosY() {
        return posY;
    }

    /**
     * Method which sets position of y coordinate
     * @param posY y coordinate
     */
    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    /**
     * Method which gives concrete Block
     * @return concrete block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Method which sets concrete Block
     * @param block instance of block
     */
    public void setBlock(Block block) {
        this.block = block;
    }
}
