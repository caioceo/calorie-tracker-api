package dev.caio.fitsy.repository.metas;

import dev.caio.fitsy.model.enums.Status;
import dev.caio.fitsy.model.metas.Meta;
import dev.caio.fitsy.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    Meta findByUserInfoAndDataFimIsNull(UserInfo userinfo);

    List<Meta> findByUserInfoOrderByDataInicioDesc(UserInfo userInfo);

    boolean existsByUserInfo(UserInfo userInfo);

    Meta findByUserInfoAndStatus(UserInfo userInfo, Status status);
}