package kr.flab.usermanager.repository;

import kr.flab.usermanager.domain.User;
import kr.flab.usermanager.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMysqlRepository implements UserRepository {
    private final EntityManager em;

    @Override
    public Long saveUser(User user) {
        em.persist(user);
        return user.getId();
    }
    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUserId(String userId) {
        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<UserDto> findAll() {
        return em.createQuery("select new kr.flab.usermanager.dto.UserDto(u.id, u.name, u.gender, u.joinDate) from User u", UserDto.class)
                .getResultList();
    }

    @Override
    public List<UserDto> findByUserName(String name) {
        return em.createQuery("select new kr.flab.usermanager.dto.UserDto(u.id, u.name, u.gender, u.joinDate) from User u where u.name = :name", UserDto.class)
                .setParameter("name", name)
                .getResultList();
    }
}
