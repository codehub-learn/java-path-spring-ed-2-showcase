package gr.codelearn.spring.showcase.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtService jwtService;

	@Override
	public String authenticate(final String username, final String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		return jwtService.generateToken(userDetailsService.loadUserByUsername(username));
	}
}
