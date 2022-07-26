package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.exception.DuplicateUserException;
import KKOBUGI.web.exception.FindNoUserException;
import KKOBUGI.web.exception.NoUserIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.constraints.Null;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    /**
     * 유저 저장
     * */
    public Long save(User user){
        em.persist(user);
        return user.getId();
    }

    public User findOne(Long id){
        if(em.find(User.class, id)==null)
            throw new NoUserIdException("잘못된 userId 입니다. (유저를 찾을 수 없음)");
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        return users;
    }

    /**
     * 유저 회원가입 시 loginID  검증
     * */
    public void duplicateCheck1(String loginId){
        try{em.createQuery("select u from User u where u.login_Id= :loginId", User.class)
                .setParameter("loginId",loginId).getSingleResult();}
        catch (NoResultException e){
            return;
        }
        throw new DuplicateUserException("이미 존재하는 loginID 입니다. 다시 설정해주세요");
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


    public Long delete(User user){
        Long userId = user.getId();
        em.remove(user);
        return userId;
    }

    /**
     * 닉네임으로 유저 찾아옴
     * */
    public User findByNickname(String nickname){
        User user = em.createQuery("select u from User u where u.nickname= :name", User.class)
                .setParameter("name", nickname).getSingleResult();
        return user;
    }

    /**
     * 로그인 아이디로 유저 찾아옴*/
    public User findByloginId(String loginId) {
        User user = em.createQuery("select u from User u where u.login_Id= :loginId", User.class)
                .setParameter("loginId", loginId).getSingleResult();
        return user;
    }


    /**
     * 로그인 검증 : loginID, PW 가 일치해야만 성공, 실패할 경우만 FindNoUserException 발생 */
    public void login(String loginId, String pw) {
        try{em.createQuery("select u from User u where u.login_Id= :loginId and u.login_Pw= :pw ", User.class)
                .setParameter("loginId", loginId)
                .setParameter("pw",pw)
                .getSingleResult();}
        catch (NoResultException e){
            throw new FindNoUserException("로그인 실패. id와 pw를 다시 확인해주세요.");
        }
    }
}
