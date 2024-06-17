package co.istad.lms.security;

import co.istad.lms.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {


    private User user;
    private String rawPassword;

    public CustomUserDetails(User user , String rawPassword) {
        this.rawPassword = rawPassword;
        this.user = user;
    }


    //method for get role of user
    public Set<String> getRoles() {
        Set<String> roles = new HashSet<>();

        if (user.getAdmin() != null) {
            roles.add("admin");
        }
        if (user.getStudent() != null) {
            roles.add("student");
        }
        if (user.getInstructor() != null) {
            roles.add("instructor");
        }
        if (user.getAcademic() != null) {
            roles.add("academic");
        }

        return roles;
    }



    // make the proper format for the authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // create a list of authorities
        List<GrantedAuthority> authorities = new ArrayList<>();

        // add the authorities to the list
        user.getAuthorities().forEach(
                authority -> {
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
                }
        );

        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        return user.getEmail();
    }


    // will add it tmr!
    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }



    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }



    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }



    @Override
    public boolean isEnabled() {
        return !user.getStatus();
    }


}