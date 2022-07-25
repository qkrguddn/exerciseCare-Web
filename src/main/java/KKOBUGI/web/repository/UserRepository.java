package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.User;
import KKOBUGI.web.exception.FindNoUserException;
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
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        return users;
    }

    public Boolean duplicateCheck1(String loginId){
        try{User user = em.createQuery("select u from User u where u.login_Id= :loginId", User.class)
                .setParameter("loginId",loginId).getSingleResult();}
        catch(NoResultException e){
            return true;    // DB에 해당하는 loginId가 없음!
        }
        return false;   // 중복됨
    }

    public Boolean duplicateCheck2(String nickname){
        try{User user = em.createQuery("select u from User u where u.nickname= :nickname", User.class)
                .setParameter("nickname", nickname).getSingleResult();}
        catch(NoResultException e){
            return true;    // DB에 해당하는 nickname이 없음!
        }
        return false;   // 중복됨
    }

    public Boolean check(String loginId, String pw) {
        try{
            User user = em.createQuery("select u from User u where u.login_Id= :loginId and u.login_Pw= :pw", User.class)
                    .setParameter("loginId", loginId).setParameter("pw", pw)
                    .getSingleResult();
        }
        catch (NoResultException e){
            throw new FindNoUserException("아이디나 비밀번호를 다시 한 번 확인해주세요.");
        }
        return true;
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
}
