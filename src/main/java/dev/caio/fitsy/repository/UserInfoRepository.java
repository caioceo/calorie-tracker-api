package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.User;
import dev.caio.fitsy.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    boolean existsByUser(User user);

    UserInfo findByUser (User user);
}