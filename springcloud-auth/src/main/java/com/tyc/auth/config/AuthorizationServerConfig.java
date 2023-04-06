package com.tyc.auth.config;

import com.tyc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置授权服务器
 *
 * @author tyc
 * @version 1.0
 * @date 2023-04-04 17:49:50
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private UserService userService;

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        //配置JWT的内容增强器
////        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
////        List<TokenEnhancer> delegates = new ArrayList<>();
////        delegates.add(jwtTokenEnhancer);
////        delegates.add(jwtAccessTokenConverter);
////        enhancerChain.setTokenEnhancers(delegates);
//        endpoints.authenticationManager(authenticationManagerBean) //使用密码模式需要配置
//                .tokenStore(tokenStore)  //配置存储令牌策略
////                .accessTokenConverter(jwtAccessTokenConverter)
////                .tokenEnhancer(enhancerChain) //配置tokenEnhancer
//                .reuseRefreshTokens(false)  //refresh_token是否重复使用
//                .userDetailsService(userService) //刷新令牌授权包含对用户信息的检查
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST); //支持GET,POST请求
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置JWT的内容增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManagerBean) //使用密码模式需要配置
                .tokenStore(tokenStore)  //配置存储令牌策略
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain) //配置tokenEnhancer
                .reuseRefreshTokens(false)  //refresh_token是否重复使用
                .userDetailsService(userService) //刷新令牌授权包含对用户信息的检查
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST); //支持GET,POST请求
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //配置client_id
                .withClient("client")
                //配置client-secret
                .secret(passwordEncoder.encode("123123"))
                //配置访问token的有效期
                .accessTokenValiditySeconds(3600)
                //配置刷新token的有效期
                .refreshTokenValiditySeconds(864000)
                //配置redirect_uri，用于授权成功后跳转
                .redirectUris("http://www.baidu.com")
                //配置申请的权限范围
                .scopes("all")
                /**
                 * 配置grant_type，表示支持哪种授权类型
                 * authorization_code 授权码模式
                 * implicit 简化模式
                 * password 密码模式
                 * client_credentials 客户端模式
                 * refresh_token 更新令牌
                 */
                .authorizedGrantTypes("authorization_code","implicit","password","client_credentials","refresh_token");
    }
}
