package kyonggiyo.auth.repository;

import kyonggiyo.auth.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenDataRedisRepository extends CrudRepository<RefreshTokenEntity, Long> {

}
