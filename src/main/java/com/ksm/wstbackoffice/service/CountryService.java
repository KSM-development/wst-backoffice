package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.mapper.CountryMapper;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    private final SingletonScopedTest singletonScopedTest;
    private final RequestScopedTest requestScopedTest;

    @Lookup
    public PrototypeScopedTest getPrototypeScopedTest() {
        return null;
    }

    public CountryService(CountryRepository countryRepository,
                          CountryMapper countryMapper,
                          RequestScopedTest requestScopedTest,
                          SingletonScopedTest singletonScopedTest) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.requestScopedTest = requestScopedTest;
        this.singletonScopedTest = singletonScopedTest;
    }

    public List<CountryDto> findAll() {
        // TODO OPINTA
        System.out.println(" ======== NEW REQUEST START ======== ");

        // TODO OPINTA singletonScopedTest is not PROXIED so to get his real hash you just call hashCode() on the object
        System.out.println("SINGLETON scope test 1: hash " + singletonScopedTest.hashCode() + " locale " + singletonScopedTest.getLocale());
        // after setting "new Lang" the request scoped bean should hold new value until new request is coming. On new HTTP request is going to be recreated
        singletonScopedTest.setLocale("new Locale " + UUID.randomUUID());
        System.out.println("SINGLETON scope test 2: hash " + singletonScopedTest.hashCode() + " locale " + singletonScopedTest.getLocale());

        try {
            // TODO OPINTA requestScopedTest is PROXIED so to get his real hash and not hash of the PROXY you should do this
            //      proxyMode = ScopedProxyMode.TARGET_CLASS in the RequestScopedTest class says to use PROXY
            int realHash = ((Advised) requestScopedTest).getTargetSource().getTarget().hashCode();
            System.out.println("    REQUEST scope test 1: hash " + realHash + " lang " + requestScopedTest.getLang());
            // after setting "new Lang" the request scoped bean should hold new value until new request is coming. On new HTTP request is going to be recreated
            requestScopedTest.setLang("new Lang " + UUID.randomUUID());
            System.out.println("    REQUEST scope test 2: hash " + realHash + " lang " + requestScopedTest.getLang());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO OPINTA prototypeScopedTest is not PROXIED so to get his hash you just call hashCode() on the object
        PrototypeScopedTest prototypeScopedTest = getPrototypeScopedTest();
        System.out.println("        PROTOTYPE scope test 1: hash " + prototypeScopedTest.hashCode() + " country " + prototypeScopedTest.getCountry());
        // here we change country but after that get prototype bean from the container. As far as it is prototype the container is going to recreate it
        //      and the country is going to be set to the default value
        prototypeScopedTest.setCountry("new Country " + UUID.randomUUID());
        prototypeScopedTest = getPrototypeScopedTest();
        System.out.println("        PROTOTYPE scope test 2: hash " + prototypeScopedTest.hashCode() + " country " + prototypeScopedTest.getCountry());

        System.out.println(" ======== NEW REQUEST END ======== ");

        //return countryMapper.toDtos(countryRepository.findAll());
        return Collections.emptyList();
    }

    public CountryDto findById(String id) {
        return countryMapper.toDto(countryRepository.findById(id).orElse(null));
    }
}
