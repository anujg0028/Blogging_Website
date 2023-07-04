package com.anuj.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get jwt
        //Bearer
        //validate
        String requestTokenHeader = request.getHeader("Authorization");
        String username=null;
        String jwtToken=null;

        //null and format || fetech the token
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
        {
            jwtToken=requestTokenHeader.substring(7);
            try{
                username = this.jwtTokenHelper.getUsernameFromToken(jwtToken);
            }
//            catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException e)
//            {
//                e.printStackTrace();
//            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //once we get the token we validate it
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //shi chal rha h ab authenticate kro
                if(this.jwtTokenHelper.validateToken(jwtToken,userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                else {
                    System.out.println("Invalid JWT Token");
                }
            }
            else {
                System.out.println("Token is not validated..");
            }
        }
        filterChain.doFilter(request,response);
    }
}
