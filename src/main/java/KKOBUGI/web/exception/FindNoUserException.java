package KKOBUGI.web.exception;

import javax.persistence.NoResultException;

public class FindNoUserException extends NoResultException{

    public FindNoUserException() {super();}

    public FindNoUserException(String message) {
        super(message);
    }
}
