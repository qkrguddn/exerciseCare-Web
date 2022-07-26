package KKOBUGI.web.controller.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 보편적인 Error 핸들링
     * */

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity exception(Exception e){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass().getName()+" / "
//        + new String("오류 오류"));
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(Exception e){

        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+"Request 인자값을 모두 정확하게 입력해주세요.");
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity InvalidDataAccessApiUsageException(Exception e){

        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+"Request 인자값을 모두 정확하게 적어주세요.");
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity TransactionSystemException(Exception e){

        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+"데이터베이스에 존재하지 않는 값 참조");
    }


    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NullPointerException(Exception e){

        System.out.println("===============================");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("===============================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getClass().getName()+" / "+e.getLocalizedMessage());
    }
}
