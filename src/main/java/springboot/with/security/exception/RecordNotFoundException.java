package springboot.with.security.exception;

//Deze klasse 'extends' een 'RuntimeException' klasse die in staat is om een exceptie te geven.
//De 'RecordNotFoundException' klasse is toegepast in de logica van de implementatielaag in de service directory.
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

}
