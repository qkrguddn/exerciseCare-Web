package KKOBUGI.web.service;

import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.exception.DuplicateUserException;
import KKOBUGI.web.exception.FindNoUserException;
import KKOBUGI.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;
    /**
     * 회원가입
     * */
    @Transactional // default : false => 그래야 정보 수정 가능(in DB)
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * 로그인 검증 : loginID, PW 가 일치해야만 성공, 실패할 경우만 FindNoUserException 발생 */
    public void login(String loginId, String pw) {
        try{em.createQuery("select u from User u where u.loginId= :loginId and u.loginPw= :pw ", User.class)
                .setParameter("loginId", loginId)
                .setParameter("pw",pw)
                .getSingleResult();}
        catch (NoResultException e){
            throw new FindNoUserException("로그인 실패. id와 pw를 다시 확인해주세요.");
        }
    }


    public User findOne(Long userId){
         return userRepository.findById(userId).get();
    }

    public User findByloginId(String loginId){
        return userRepository.findByLoginId(loginId);
    }


    public void duplicateCheck(String loginId, String nickname){
        // loginID 검증
        duplicateCheck1(loginId);
        // nickname 검증
        duplicateCheck2(nickname);
    }

    /**
     * 유저 회원가입 시 loginID  검증
     * */
    public void duplicateCheck1(String loginId){
        try{em.createQuery("select u from User u where u.loginId= :loginId", User.class)
                .setParameter("loginId",loginId).getSingleResult();}
        catch (NoResultException e){
            return;
        }
        throw new
                DuplicateUserException("이미 존재하는 loginID 입니다. 다시 설정해주세요");
    }

    /**
     * 유저 회원가입 시 nickname  검증
     * */
    public void duplicateCheck2(String nickname){
        try{em.createQuery("select u from User u where u.nickname= :nickname", User.class)
                .setParameter("nickname",nickname).getSingleResult();}
        catch (NoResultException e){
            return;
        }
        throw new DuplicateUserException("이미 존재하는 닉네임입니다. 다시 설정해주세요");
    }

}
