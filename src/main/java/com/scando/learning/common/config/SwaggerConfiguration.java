package com.scando.learning.common.config;

import com.google.common.base.Predicates;
import com.scando.learning.common.service.SwaggerUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${scando.server.host}")
    private String host;

    @Autowired
    private SwaggerUtilityService swaggerUtilityService;

    @Bean
    public Docket productApi() {

        Set<String> mediaTypes = new HashSet<>();
        mediaTypes.add("application/json");
        Tag[] tags = swaggerUtilityService.getAllSecuredApiTags();

        List<Parameter> aParameters = new ArrayList<>();
//        aParameters.add(getAuthHeaderParameter());
//        aParameters.add(versionParameterBuilder.build());
        aParameters.add(getDebugHeaderParameter());
        aParameters.add(getAuthorizationHeaderParameter());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(Predicates.and(
                                PathSelectors.ant("/api/**"),
                                PathSelectors.regex("^((?!/public/).)*$")
                        )
                )
                .build()
                .apiInfo(metaData())
                .tags(tags[0], tags)
                .host(host)
                .produces(mediaTypes)
                .globalOperationParameters(aParameters)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo metaData() {
        return new ApiInfo("Scando API Specification", "All API's are protected by token. The token should be passed as <b>'Authorization'</b> header in the following format <b>'bearer &lt;token&gt;'</b>" , "1.0", null, null, null, null, new ArrayList<>());

    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .defaultModelRendering(ModelRendering.MODEL)
                .defaultModelsExpandDepth(-1)
                .displayRequestDuration(true)
                .displayOperationId(false)
                .filter(true)
                .deepLinking(true).build();
    }

    private List<Parameter> getGlobalOperationParams() {
        /*List<String> versionList = new ArrayList<>();
        versionList.add(DEFAULT_VERSION);
        AllowableListValues versionValues = new AllowableListValues(versionList, STRING_TYPE);

        ParameterBuilder versionParameterBuilder = new ParameterBuilder();
        versionParameterBuilder.name("version")
                .modelRef(new ModelRef(STRING_TYPE)).parameterType("path")
                .defaultValue(DEFAULT_VERSION)
                .allowableValues(versionValues)
                .required(true)
                .build();*/

        List<Parameter> aParameters = new ArrayList<>();
//        aParameters.add(getAuthHeaderParameter());
//        aParameters.add(versionParameterBuilder.build());
        aParameters.add(getDebugHeaderParameter());
        return aParameters;

    }

    private Parameter getDebugHeaderParameter() {
        List<String> debugHeaderList = new ArrayList<>();
        debugHeaderList.add("true");
        debugHeaderList.add("false");
        AllowableListValues debugValues = new AllowableListValues(debugHeaderList, "string");

        ParameterBuilder debugHeaderBuilder = new ParameterBuilder();
        debugHeaderBuilder.name("debug")
                .modelRef(new ModelRef("string")).parameterType("header")
                .allowableValues(debugValues)
                .order(2)
                .required(false)
                .build();
        return debugHeaderBuilder.build();
    }

    private Parameter getAuthorizationHeaderParameter() {

        ParameterBuilder debugHeaderBuilder = new ParameterBuilder();
        debugHeaderBuilder.name("Authorization")
                .modelRef(new ModelRef("string")).parameterType("header")
                .defaultValue("Bearer <token>")
                .order(1)
                .required(false)
                .build();
        return debugHeaderBuilder.build();
    }

}