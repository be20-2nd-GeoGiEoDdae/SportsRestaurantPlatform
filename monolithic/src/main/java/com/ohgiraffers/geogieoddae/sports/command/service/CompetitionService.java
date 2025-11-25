package com.ohgiraffers.geogieoddae.sports.command.service;

import com.ohgiraffers.geogieoddae.sports.command.dto.competition.CompetitionCreateRequest;
import com.ohgiraffers.geogieoddae.sports.command.entity.CompetitionEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.CountryEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.CompetitionRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.CountryRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final SportsRepository sportRepository;

    public Long createCompetition(CompetitionCreateRequest request) {
        SportsEntity sport = sportRepository.findById(request.getSportId())
                .orElseThrow(() -> new IllegalArgumentException("종목 없음"));

        CompetitionEntity competition = CompetitionEntity.builder()
                .name(request.getName())
                .sport(sport)
                .build();

        competitionRepository.save(competition);
        return competition.getId();
    }

    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }
}
