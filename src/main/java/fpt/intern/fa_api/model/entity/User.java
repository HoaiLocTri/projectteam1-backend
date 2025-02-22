package fpt.intern.fa_api.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fpt.intern.fa_api.model.enums.Gender;
import fpt.intern.fa_api.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@Entity
@Table(name ="User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name may not be blank")
    @Column(nullable = false,name="username", unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(unique=true)
    private String phone;

    private Date dateOfBirth;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(unique = true)
    private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private Roles roles;
   
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserClass> userClassList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getName()));
        authorities.add(new SimpleGrantedAuthority("Training."+roles.getTraining()));
        authorities.add(new SimpleGrantedAuthority("Class."+roles.getClassRoles()));
        authorities.add(new SimpleGrantedAuthority("User."+roles.getUser()));
        authorities.add(new SimpleGrantedAuthority("Syllabus."+roles.getSyllabus()  ));
        return authorities;    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Long getRoles(Roles roles) {
    	return roles.getId();
    }
}
