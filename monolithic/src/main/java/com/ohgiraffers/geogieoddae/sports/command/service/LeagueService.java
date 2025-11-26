package com.ohgiraffers.geogieoddae.sports.command.service;

import com.ohgiraffers.geogieoddae.sports.command.dto.league.LeagueCreateRequest;
import com.ohgiraffers.geogieoddae.sports.command.entity.CountryEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.CountryRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.LeagueRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueService {

    private final LeagueRepository leagueRepository;
    private final SportsRepository sportRepository;
    private final CountryRepository countryRepository;

    public Long createLeague(LeagueCreateRequest request) {
        SportsEntity sport = sportRepository.findById(request.getSportId())
                .orElseThrow(() -> new IllegalArgumentException("종목 없음"));

        CountryEntity country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("국가 없음"));

        LeagueEntity league = LeagueEntity.builder()
                .name(request.getName())
                .sport(sport)
                .country(country)
                .build();

        leagueRepository.save(league);
        return league.getId();
    }

    public void deleteLeague(Long id) {
        leagueRepository.deleteById(id);
    }
}
