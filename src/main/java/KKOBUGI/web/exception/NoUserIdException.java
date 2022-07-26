package KKOBUGI.web.exception;

public class NoUserIdException extends NullPointerException{
    public NoUserIdException(){
        super();
    }
    public NoUserIdException(String message){
        super(message);
    }
}
