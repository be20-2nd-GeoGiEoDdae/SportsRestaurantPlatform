package com.ohgiraffers.geogieoddae.sports.query.service;

import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.LeagueRepository;
import com.ohgiraffers.geogieoddae.sports.query.dto.LeagueQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueQueryService {

    private final LeagueRepository leagueRepository;

    public List<LeagueQueryDto> findBySportAndCountry(Long sportId, Long countryId) {
        return leagueRepository.findBySport_SportCodeAndCountry_Id(sportId, countryId)
                .stream()
                .map(l -> new LeagueQueryDto(
                        l.getId(),
                        l.getName()
                ))
                .toList();
    }

    public LeagueQueryDto findOne(Long leagueId) {
        LeagueEntity l = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found: " + leagueId));

        return new LeagueQueryDto(
                l.getId(),
                l.getName()
        );
    }
}
