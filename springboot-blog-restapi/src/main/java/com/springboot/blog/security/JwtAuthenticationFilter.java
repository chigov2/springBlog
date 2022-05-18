package com.springboot.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //get JWT token from http request - 1
        String token = getJwtFromHttpRequest(request);
        //validate token - 2
        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
            //get username from token - 3
            String username = tokenProvider.getUsernameFromJwt(token);
            //load user associated with token from db - 4
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,null,userDetails.getAuthorities()
            );
            //pass request object to authentification token
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //after loading user info - set this info to spring security - 5
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
        //load user associated with token from db - 4

    }

    //1
    //Bearer <accessToken> need to extract access token
    private String getJwtFromHttpRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            System.out.println(bearerToken);
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    //-2

}
