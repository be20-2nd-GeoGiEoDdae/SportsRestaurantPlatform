package com.ohgiraffers.geogieoddae.sports.command.service;

import com.ohgiraffers.geogieoddae.sports.command.dto.country.CountryCreateRequest;
import com.ohgiraffers.geogieoddae.sports.command.entity.CountryEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.CountryRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;
    private final SportsRepository sportsRepository;

    public Long createCountry(CountryCreateRequest request) {
        SportsEntity sport = sportsRepository.findById(request.getSportCode())
                .orElseThrow(() -> new IllegalArgumentException("종목 없음"));

        CountryEntity country = CountryEntity.builder()
                .name(request.getName())
                .sport(sport)
                .build();

        countryRepository.save(country);
        return country.getId();
    }

    public void updateCountry(Long id, CountryCreateRequest request) {
        CountryEntity entity = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("국가 없음"));

        entity.update(request.getName());
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
