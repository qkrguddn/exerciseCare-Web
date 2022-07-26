package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.UserDto;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.exception.NoUserIdException;
import KKOBUGI.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

import static KKOBUGI.web.domain.dto.UserDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * */
    @Transactional // default : false => 그래야 정보 수정 가능(in DB)
    public Long save(User user){
        return userRepository.save(user);
    }

    /**
     *유저 조회
     * */
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
         return userRepository.findOne(userId);
    }

    public User findByloginId(String loginId){
        return userRepository.findByloginId(loginId);
    }


    public void duplicateCheck(String loginId, String nickname){
        // loginID 검증
        userRepository.duplicateCheck1(loginId);
        // nickname 검증
        userRepository.duplicateCheck2(nickname);
    }


    @Transactional
    public Long delete(User user){
        return userRepository.delete(user);
    }

    /** 유저 정보 수정 */
    @Transactional
    public void update(Long id, String nickname){
        User user = userRepository.findOne(id);
        user.fixNickname(nickname);
    }

    public void login(String loginId, String pw) {
        userRepository.login(loginId, pw);
    }
}
