package nl.novi.stuivenberg.springboot.example.security.payload.request;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Lob;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

//    @NotBlank
    private Long coinBalance;

    private Boolean subscribeToNewsletter;

    @Lob
    private byte[] profilePicture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public Long getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(Long coinBalance) {
        this.coinBalance = coinBalance;
    }

    public Boolean getSubscribeToNewsletter() {
        return subscribeToNewsletter;
    }

    public void setSubscribeToNewsletter(Boolean subscribeToNewsletter) {
        this.subscribeToNewsletter = subscribeToNewsletter;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
