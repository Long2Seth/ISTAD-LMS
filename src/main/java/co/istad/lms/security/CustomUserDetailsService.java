    package co.istad.lms.security;

    import co.istad.lms.features.user.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;


    @Service
    @RequiredArgsConstructor
    public class CustomUserDetailsService implements UserDetailsService {
        private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
            var user = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUser(user);
            return customUserDetails;
        }
    }
