/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Error handler
 */

package errorHandler;

/**
 * MyExceptionTwo.java - a simple class for exception
 */
public class MyExceptionTwo extends Exception {
    /**
     * Throws an exception
     * @param message The message to be shown when exception
     */
    public MyExceptionTwo(String message){
        super(message);
    }
}
