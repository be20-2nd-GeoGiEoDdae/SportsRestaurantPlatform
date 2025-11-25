package com.ohgiraffers.geogieoddae.sports.query.service;

import com.ohgiraffers.geogieoddae.sports.command.entity.CompetitionEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.CompetitionRepository;
import com.ohgiraffers.geogieoddae.sports.query.dto.CompetitionQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionQueryService {

    private final CompetitionRepository competitionRepository;

    public List<CompetitionQueryDto> findBySport(Long sportId) {
        return competitionRepository.findBySport_SportCode(sportId)
                .stream()
                .map(c -> new CompetitionQueryDto(
                        c.getId(),
                        c.getName()
                ))
                .toList();
    }

    public CompetitionQueryDto findOne(Long competitionId) {
        CompetitionEntity c = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found: " + competitionId));

        return new CompetitionQueryDto(
                c.getId(),
                c.getName()
        );
    }
}
