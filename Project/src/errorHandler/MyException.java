/**
 * @author Tomas Kunickij
 * @author Maros Orsak
 *
 * Project "Scheme calculator" for IJA at FIT VUT, 2018
 * Description of a file: Error handler
 */

package errorHandler;

/**
 * MyException.java - a simple class for exception
 */
public class MyException extends Exception {
    /**
     * Throws an exception
     * @param message  message to be shown when exception
     */
    public MyException(String message){
        super(message);
    }
}
