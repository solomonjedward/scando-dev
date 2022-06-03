package com.scando.learning;

import com.scando.learning.common.config.interceptor.ApiSpecAuthenticationInterceptor;
import com.scando.learning.common.config.interceptor.CommonIntercepter;
import com.scando.learning.common.config.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class LearningConfig implements WebMvcConfigurer {

    @Autowired
    private ApiSpecAuthenticationInterceptor apiSpecAuthenticationInterceptor;

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Autowired
    private CommonIntercepter commonIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonIntercepter);
         registry.addInterceptor(apiSpecAuthenticationInterceptor).addPathPatterns("/api-doc", "/swagger-ui.html*");
         registry.addInterceptor(securityInterceptor).addPathPatterns("/api/**").excludePathPatterns("/actuator/**",
                "/api/auth/**" ,"/api/app/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api-doc", "/swagger-ui.html");
    }

    @Bean
    public ITemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowCredentials(true)
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowedOrigins("http://dev.scando.world" , "http://localhost:4200");
            }
        };
    }
}
