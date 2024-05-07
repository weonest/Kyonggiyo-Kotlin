package kyonggiyo.auth.repository

import kyonggiyo.auth.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository

interface RefreshTokenDataRedisRepository : CrudRepository<RefreshTokenEntity, Long>
