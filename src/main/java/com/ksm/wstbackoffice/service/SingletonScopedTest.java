package com.ksm.wstbackoffice.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Getter
@Setter
// TODO OPINTA
public class SingletonScopedTest {
    private String locale;

    @PostConstruct
    public void init() {
        this.locale = "ua_UA";
    }
}
