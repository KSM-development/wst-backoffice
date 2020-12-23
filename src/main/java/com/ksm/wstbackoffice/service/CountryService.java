package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.mapper.CountryMapper;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final RequestScopedTest requestScopedTest;

    @Lookup
    public PrototypeScopedTest getPrototypeScopedTest() {
        return null;
    }

    public CountryService(CountryRepository countryRepository,
                          CountryMapper countryMapper,
                          RequestScopedTest requestScopedTest) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.requestScopedTest = requestScopedTest;
    }

    public List<CountryDto> findAll() {
        try {
            int realHash = ((Advised) requestScopedTest).getTargetSource().getTarget().hashCode();
            System.out.println("REQUEST scope test 1 is " + System.identityHashCode(requestScopedTest) + " ref " + requestScopedTest + " hash " + requestScopedTest.hashCode() + " lang " + requestScopedTest.getLang() + " real hash " + realHash);
            requestScopedTest.setLang("new Lang");
            System.out.println("REQUEST scope test 2 is " + System.identityHashCode(requestScopedTest) + " ref " + requestScopedTest + " hash " + requestScopedTest.hashCode() + " lang " + requestScopedTest.getLang() + " real hash " + realHash);
            System.out.println("REQUEST scope test 3 is " + System.identityHashCode(requestScopedTest) + " ref " + requestScopedTest + " hash " + requestScopedTest.hashCode() + " lang " + requestScopedTest.getLang() + " real hash " + realHash);
        } catch (Exception e) {
            e.printStackTrace();
        }

        requestScopedTest.setLang("new Lang");

        PrototypeScopedTest prototypeScopedTest = getPrototypeScopedTest();
        System.out.println("PROTOTYPE scope test 1 is " + System.identityHashCode(prototypeScopedTest) + " ref " + prototypeScopedTest + " hash " + prototypeScopedTest + " country " + prototypeScopedTest.getCountry());
        prototypeScopedTest.setCountry("new Country");
        prototypeScopedTest = getPrototypeScopedTest();
        System.out.println("PROTOTYPE scope test 2 is " + System.identityHashCode(prototypeScopedTest) + " ref " + prototypeScopedTest + " hash " + prototypeScopedTest + " country " + prototypeScopedTest.getCountry());
        prototypeScopedTest = getPrototypeScopedTest();
        System.out.println("PROTOTYPE scope test 3 is " + System.identityHashCode(prototypeScopedTest) + " ref " + prototypeScopedTest + " hash " + prototypeScopedTest + " country " + prototypeScopedTest.getCountry());

        getPrototypeScopedTest().setCountry("new Country");

        return countryMapper.toDtos(countryRepository.findAll());
    }

    public CountryDto findById(String id) {
        return countryMapper.toDto(countryRepository.findById(id).orElse(null));
    }
}
