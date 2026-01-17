package dev.caio.fitsy.repository;

import dev.caio.fitsy.model.Meta;
import dev.caio.fitsy.model.UserInfo;
import dev.caio.fitsy.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    Meta findByUserInfoAndStatus(UserInfo userinfo, Status status);

    List<Meta> findByUserInfoOrderByDataInicioDesc(UserInfo userInfo);
}