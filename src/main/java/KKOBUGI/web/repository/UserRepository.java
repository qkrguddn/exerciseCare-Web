package KKOBUGI.web.repository;

import KKOBUGI.web.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    /**
     * 닉네임으로 유저 찾아옴
     * */
    public User findByNickname(String nickname){
        User user = em.createQuery("select u from User u where u.nickname= :name", User.class)
                .setParameter("name", nickname).getSingleResult();
        return user;
    }
}
