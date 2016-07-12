package com.tookbra.shy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityToolboxView;
import org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.servlet.Filter;

/**
 * Created by tookbra on 2016/7/7.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    //todo toolbox不能正常解析
    @Bean
    public VelocityLayoutViewResolver  velocityViewResolver(VelocityProperties properties) {
        VelocityLayoutViewResolver  resolver = new VelocityLayoutViewResolver ();
//        resolver.setDateToolAttribute("dateTool");
        resolver.setViewClass(VelocityLayoutToolboxView.class);
        properties.applyToViewResolver(resolver);
        resolver.setLayoutUrl("layout/default.vm");
        resolver.setExposeSpringMacroHelpers(true);
        return resolver;
    }



}
