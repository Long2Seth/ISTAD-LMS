package co.istad.lms.features.auth;

import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    void logout(String token);
}
