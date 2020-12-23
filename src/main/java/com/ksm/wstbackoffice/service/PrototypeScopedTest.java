package com.ksm.wstbackoffice.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@Getter
@Setter
public class PrototypeScopedTest {
    private String country;

    @PostConstruct
    public void init() {
        this.country = "ua";
    }
}
