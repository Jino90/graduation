package website.yoborisov.graduation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import website.yoborisov.graduation.AuthorizedUser;
import website.yoborisov.graduation.model.User;
import website.yoborisov.graduation.service.UserService;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.getByEmail(name);

        if (password.equals(user.getPassword())) {

            // use the credentials
            // and authenticate against the third-party system

            return new UsernamePasswordAuthenticationToken(
                    new AuthorizedUser(user), password, user.getRoles());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
