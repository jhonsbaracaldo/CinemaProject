package ProyectoIntegradorCine.aplication.config;

import ProyectoIntegradorCine.aplication.services.serviceJwt.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

 private final UserDetailsService userDetailsService;
   private final JwtServices jwtService ;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

    final String authorizationHeader = String.valueOf(request.getHeaders("Authorization"));
    final String jwt;
    final String userEmail;

     if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
             filterChain.doFilter(request,response);

             return;
     }
     jwt=authorizationHeader.substring(7);
  userEmail=jwtService.extractUserName(jwt);

  if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() ==null){
  }
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
  setAutenticationToContext(request,jwt,userDetails);
    }

    private void setAutenticationToContext(HttpServletRequest request, String jwt, UserDetails userDetails)
    {
        if(jwtService.isTokenValid(jwt, userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            //asignado la informacion que vien del jwt al security context holder
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
