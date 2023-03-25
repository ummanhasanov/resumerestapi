package com.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Value("${security.jwt.client-id}")
//    private String clientId;
//
//    @Value("${security.jwt.client-secret}")
//    private String clientSecret;
//
//    @Value("${security.jwt.grant-type}")
//    private String grantType;
//
//    @Value("${security.jwt.scope-read}")
//    private String scopeRead;
//
//    @Value("${security.jwt.scope-write}")
//    private String scopeWrite = "write";
//
//    @Value("${security.jwt.resource-ids}")
//    private String resourceIds;

    @Autowired//SecurityConfig-den
    private TokenStore tokenStore;

    @Autowired//SecurityConfig-den
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired//SecurityConfig-den
    private AuthenticationManager authenticationManager;

    @Autowired//SecurityConfig-den
    private PasswordEncoder passwordEncoder;

//    @Override // yuxaridaki commment olunmazsa bele yazilmalidir
//    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception{
//        configurer
//                .inMemory()
//                .withClient(clientId)
//                .secret(passwordEncoder.encode(clientSecret))
//                .authorizedGrantTypes(grantType)
//                .scopes(scopeRead,scopeWrite)
//                .resourceIds(resourceIds);
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception{
        configurer
                .inMemory()
                .withClient("alma")
                .secret(passwordEncoder.encode("alma"))
                .authorizedGrantTypes("password")
                .scopes("read","write")
                .resourceIds("resumeapi");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));

        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
    }


}
