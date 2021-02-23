package com.example.main.interceptor;

import com.example.main.util.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.io.IOUtils;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) {

        try {

            String jwt = context.getHeaders().getFirst("Authorization");

            if(jwt == null){
                throw new Exception("Authorization header is not present.");
            }

            if(!jwt.startsWith("Bearer ")){
                throw new Exception("Token not have a bearer syntax.");
            }

            jwt = jwt.replaceAll("Bearer ", "");

            System.out.println(jwt);

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResource("public.txt").openStream();

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, Charset.defaultCharset());
            String key = writer.toString();

            key = key.replaceAll("\\n", "");
            key = key.replaceAll("\\r", "");

            byte[] decoded = Base64.getDecoder().decode(key);
            RSAPublicKey pkcs1PublicKey = RSAPublicKeyImpl.newKey(decoded);

            Jwts.parser().setSigningKey(pkcs1PublicKey).parseClaimsJws(jwt).getBody();

        } catch (Exception e){
            UnauthorizedResponse unauthorizedResponse = UnauthorizedResponse.builder().status("Unauthorized").message(e.getMessage()).build();
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(unauthorizedResponse).build());
        }

    }
}