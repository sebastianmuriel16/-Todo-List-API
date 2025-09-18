package practica_spring_boot.Todo_List_API.securiity;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void
    doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, java.io.IOException{

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        if(header != null || header.startsWith("Bearer ")){
            String token = header.substring(7);
            UserTodoDetails userTodoDetails = jwtService.validationToken(token);
            try {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userTodoDetails,null,null);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            catch (Exception e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    
}
