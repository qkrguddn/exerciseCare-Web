package KKOBUGI.web.controller.exceptionHandler;


import KKOBUGI.web.exception.DuplicateUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    /**
     * 유저 회원가입 시 중복 검증 */
    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity duplicateUserException(DuplicateUserException e){
        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+e.getLocalizedMessage());
    }


}
