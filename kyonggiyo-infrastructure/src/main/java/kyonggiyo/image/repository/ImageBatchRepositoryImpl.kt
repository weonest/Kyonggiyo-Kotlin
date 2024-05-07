package kyonggiyo.image.repository

import kyonggiyo.application.image.domain.entity.Image
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository

@Repository
class ImageBatchRepositoryImpl(
        private val jdbcTemplate: NamedParameterJdbcTemplate
) : ImageBatchRepository {

    override fun saveAllInBatch(images: List<Image>) {
        val sqlParameterSources = arrayOfNulls<SqlParameterSource>(images.size)
        for ((idx, image) in images.withIndex()) {
            val source: SqlParameterSource = MapSqlParameterSource()
                    .addValue("upload_key", image.key)
                    .addValue("image_type", image.imageType.name)
                    .addValue("reference_id", image.referenceId)
                    .addValue("created_at", image.createdAt)
                    .addValue("updated_at", image.updatedAt)
            sqlParameterSources[idx] = source
        }
        val sql = """
                insert into images (upload_key, image_type, reference_id, created_at, updated_at)
                values(:upload_key, :image_type, :reference_id, :created_at, :updated_at)
                """
        jdbcTemplate.batchUpdate(sql, sqlParameterSources)
    }

}
