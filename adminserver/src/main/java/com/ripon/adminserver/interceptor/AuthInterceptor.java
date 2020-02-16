package com.ripon.adminserver.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ripon.adminserver.annotation.VerifyToken;
import com.ripon.adminserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(VerifyToken.class)) {
            VerifyToken verifyToken = method.getAnnotation(VerifyToken.class);
            if (verifyToken.required()) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new RuntimeException("无token,请重新登录");
                }
                final String token = authHeader.substring(7);
                String username;
                try {
                    username = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException exception) {
                    exception.printStackTrace();
                    throw new RuntimeException("401");
                }
                try {
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    JWTVerifier verifier = JWT.require(algorithm)
                            .withAudience(username)
                            .build();
                    DecodedJWT jwt = verifier.verify(token);
                } catch (JWTVerificationException exception){
                    exception.printStackTrace();
                    throw new RuntimeException("token无效");
                }
                request.setAttribute("username", username);
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
