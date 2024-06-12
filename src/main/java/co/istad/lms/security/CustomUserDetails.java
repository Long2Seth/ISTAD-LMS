package co.istad.lms.security;

import co.istad.lms.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {


    private User user;



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