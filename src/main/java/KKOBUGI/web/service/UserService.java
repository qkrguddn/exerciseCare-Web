package KKOBUGI.web.service;

import KKOBUGI.web.domain.dto.UserDto;
import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }


    public DuplicateCheck duplicateCheck(String loginId, String nickname){
        Boolean b1 = userRepository.duplicateCheck1(loginId);
        Boolean b2 = userRepository.duplicateCheck2(nickname);
        return new DuplicateCheck(b1,b2);
    }

    /***
     * 유저 loginId, pw 검증
     */
    public Boolean check(String loginId, String pw){
        return userRepository.check(loginId, pw);
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

}
