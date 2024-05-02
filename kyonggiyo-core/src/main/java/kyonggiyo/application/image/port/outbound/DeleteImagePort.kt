package kyonggiyo.application.image.port.outbound

interface DeleteImagePort {
    
    fun deleteById(id: Long)

    fun deleteAllByIdInBatch(ids: List<Long>)

}
