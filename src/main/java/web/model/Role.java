package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="role")
    private  String role;


//    @ManyToMany(cascade = CascadeType.MERGE)

//    @JoinTable(name = "user_role",
//          joinColumns = @JoinColumn(name ="role_id"),
//          inverseJoinColumns = @JoinColumn(name ="user_id"))
//    @ManyToMany
//    private Set<User> users;


    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//
//    public Set<User> getUsers() {
//        return  users;
//    }
}