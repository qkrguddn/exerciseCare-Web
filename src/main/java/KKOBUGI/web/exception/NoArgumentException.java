package KKOBUGI.web.exception;

import javax.persistence.NoResultException;

public class NoArgumentException extends NoResultException {
    public NoArgumentException(){super();}

    public NoArgumentException(String message){
        super(message);
    }
}
