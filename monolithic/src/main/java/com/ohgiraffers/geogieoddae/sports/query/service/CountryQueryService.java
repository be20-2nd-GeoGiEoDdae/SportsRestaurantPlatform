    package com.ohgiraffers.geogieoddae.sports.query.service;

    import com.ohgiraffers.geogieoddae.sports.command.entity.CountryEntity;
    import com.ohgiraffers.geogieoddae.sports.command.repository.CountryRepository;
    import com.ohgiraffers.geogieoddae.sports.query.dto.CountryQueryDto;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class CountryQueryService {

        private final CountryRepository countryRepository;

        public List<CountryQueryDto> findBySport(Long sportId) {
            return countryRepository.findBySport_SportCode(sportId)
                    .stream()
                    .map(c -> new CountryQueryDto(
                            c.getId(),
                            c.getName()
                    ))
                    .toList();
        }

        public CountryQueryDto findOne(Long countryId) {
            CountryEntity c = countryRepository.findById(countryId)
                    .orElseThrow(() -> new IllegalArgumentException("Country not found: " + countryId));

            return new CountryQueryDto(
                    c.getId(),
                    c.getName()
            );
        }
    }
