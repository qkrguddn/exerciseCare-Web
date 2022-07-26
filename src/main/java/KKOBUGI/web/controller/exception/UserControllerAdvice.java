package KKOBUGI.web.controller.exception;


import KKOBUGI.web.exception.DuplicateUserException;
import KKOBUGI.web.exception.FindNoUserException;
import KKOBUGI.web.exception.NoUserIdException;
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


    /**
     * 유저를 찾지 못했을 경우
     * */
    @ExceptionHandler(value = FindNoUserException.class)
    public ResponseEntity findNoUserException(FindNoUserException e){
        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+e.getLocalizedMessage());
    }


    /**
     * UserID 가 db 상에 존재하지 않는 값일 때
     * */
    @ExceptionHandler(value = NoUserIdException.class)
    public ResponseEntity NoUserIdException(NoUserIdException e){
        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+e.getLocalizedMessage());
    }
}
