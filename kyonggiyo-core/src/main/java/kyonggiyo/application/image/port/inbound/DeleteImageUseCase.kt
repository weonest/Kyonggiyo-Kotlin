package kyonggiyo.application.image.port.inbound;

public interface DeleteImageUseCase {

    void deleteById(ImageDeleteCommand command);

}
