package Core.Filter;

import Core.Entity.Account;
import Core.Repository.AccountRepository;
import Core.Service.AccountService;
import Core.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final static String TOKEN_HEADER = "Authorization";

    @Autowired
    JwtService jwtService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(TOKEN_HEADER);
        //Cookie[] allCookies = httpRequest.getCookies();
        if(authToken != null){
            if (jwtService.validateTokenLogin(authToken)) {
                String email = jwtService.getEmailFromToken(authToken);
                Account account = accountRepository.findByEmail(email);
                if (account != null) {
                    boolean enabled = true;
                    boolean accountNonExpired = true;
                    boolean credentialsNonExpired = true;
                    boolean accountNonLocked = true;
                    UserDetails userDetail = new User(email, account.getPassword(), enabled, accountNonExpired,
                            credentialsNonExpired, accountNonLocked,account.getAuthorities());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                            null, userDetail.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
