package nl.novi.stuivenberg.springboot.example.security.exception;

//Deze klasse 'extends' een 'RuntimeException' klasse die in staat is om een exceptie te geven.
//De 'UserNotFoundException' klasse is toegepast in de logica van de implementatielaag in de service directory.
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
