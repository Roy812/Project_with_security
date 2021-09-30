package nl.novi.stuivenberg.springboot.example.security.exception;

//Deze klasse 'extends' een 'RuntimeException' klasse die in staat is om een exceptie te geven.
//De 'BadrequestException' klasse is toegepast in de logica van de implementatielaag in de service directory.
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

}
