package mvc.bookmanager.exeption;

/**
 * @author Maxim Dorokhin
 *         09.09.2016.
 */
public class AppException extends RuntimeException {

    public AppException(String message){
        super(message);
    }
}
