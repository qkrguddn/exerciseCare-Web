package KKOBUGI.web.service;

import KKOBUGI.web.domain.User;
import KKOBUGI.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional // default : false => 그래야 정보 수정 가능(in DB)
    public Long join(User user){
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

}
