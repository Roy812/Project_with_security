package springboot.with.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )

    //Attributen van deze Entity User.
    @Column(columnDefinition = "serial")
    private long id;
    private String username;
    private String email;
    private String password;
    private Long coinBalance;
    private Boolean subscribeToNewsletter;
    @Lob
    private byte[] profilePicture;

    //Relatie: 1 User kan zich bevinden in meerdere instanties van Agenda.
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Agenda> agenda;

    //Relatie: 1 User kan zich bevinden in meerdere instanties van Review.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    //Relatie: Elke gebruiker kan 1 of meerdere rollen hebben.
    @ManyToMany
    @JoinTable (name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //Constructor
    public User() {

    }

    //Constructor met arguments voor payload --> service --> AuthorizationService (die kan een gebruiker aanmaken).
    public User(String username, String email, String password, Long coinBalance, Boolean subscribeToNewsletter, byte[] profilePicture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.coinBalance = coinBalance;
        this.subscribeToNewsletter = subscribeToNewsletter;
        this.profilePicture = profilePicture;
    }

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
