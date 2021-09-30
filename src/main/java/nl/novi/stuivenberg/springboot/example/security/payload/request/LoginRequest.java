package nl.novi.stuivenberg.springboot.example.security.payload.request;

//Deze klasse wordt toegepast in de 'AuthorizationService' binnen de service directory.
//Met de 'AuthorizationService' wordt voor de gebruiker die inlogt een jtw-token gegenereerd.
public class LoginRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
