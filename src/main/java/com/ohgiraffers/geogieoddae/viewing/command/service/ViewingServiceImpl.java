package com.ohgiraffers.geogieoddae.viewing.command.service;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import com.ohgiraffers.geogieoddae.notification.command.event.NotificationCreatedEvent;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.bookmark.BookmarkRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.TeamRepository;
import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingDto;
import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingUserDto;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingEntity;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingStatus;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import com.ohgiraffers.geogieoddae.viewing.command.repository.ViewingRepository;
import com.ohgiraffers.geogieoddae.viewing.command.repository.ViewingUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewingServiceImpl implements ViewingService {

    private final ViewingRepository viewingRepository;
    private final ViewingUserRepository viewingUserRepository;
    private final RestaurantRepository restaurantRepository;
    private final SportsRepository sportsRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;
    private final BookmarkRepository bookmarkRepository;

    /* ================= 사업자 ================= */
    @Transactional
    @Override
    public void createViewing(ViewingDto dto) {

        ViewingEntity viewing = ViewingEntity.builder()
                .viewingTitle(dto.getViewingTitle())
                .viewingBody(dto.getViewingBody())
                .viewingAt(dto.getViewingAt())
                .viewingTotalDeposit(dto.getViewingTotalDeposit())
                .viewingStatus(ViewingStatus.ACTIVE)
                .viewingMinNum(dto.getViewingMinNum())
                .viewingMaxNum(dto.getViewingMaxNum())
                .build();

        RestaurantEntity restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));
        viewing.setRestaurant(restaurant);

        SportsEntity sport = sportsRepository.findById(dto.getSportsId())
                .orElseThrow(() -> new IllegalArgumentException("종목이 존재하지 않습니다."));
        viewing.setSport(sport);


        if (dto.getTeamId() != null && !dto.getTeamId().isEmpty()) {

            List<TeamEntity> teams = teamRepository.findAllById(dto.getTeamId());

            if (teams.isEmpty()) {
                throw new IllegalArgumentException("유효한 팀이 없습니다.");
            }
            if (teams.size() > 2) {
                throw new IllegalArgumentException("팀은 최대 2개까지만 선택 가능합니다.");
            }

            viewing.setTeam(teams.get(0));

            if (teams.size() == 2) {
                System.out.println("두 번째 팀: " + teams.get(1).getTeamName());
            }
        }

      List<Long>userIds =bookmarkRepository.findByRestaurant_RestaurantCode(dto.getRestaurantId())
          .stream()
          .map(b->b.getMember().getUserCode())
          .distinct()              // 중복 제거(옵션)
          .toList();
      Long notificationTypeCode = (long)3;
      for(Long u:userIds){
        publisher.publishEvent(new NotificationCreatedEvent(u,notificationTypeCode) );
      }
        viewingRepository.save(viewing);


}

@Transactional
@Override
public void updateViewing(ViewingDto dto) {
    ViewingEntity viewing = viewingRepository.findById(dto.getViewingCode())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관람입니다."));

    viewing.setViewingTitle(dto.getViewingTitle());
    viewing.setViewingBody(dto.getViewingBody());
    viewing.setViewingAt(dto.getViewingAt());
    viewing.setViewingTotalDeposit(dto.getViewingTotalDeposit());
    viewing.setViewingMinNum(dto.getViewingMinNum());
    viewing.setViewingMaxNum(dto.getViewingMaxNum());

    if (dto.getRestaurantId() != null) {
        RestaurantEntity restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));
        viewing.setRestaurant(restaurant);
    }

    if (dto.getSportsId() != null) {
        SportsEntity sport = sportsRepository.findById(dto.getSportsId())
                .orElseThrow(() -> new IllegalArgumentException("종목이 존재하지 않습니다."));
        viewing.setSport(sport);
    }

    if (dto.getTeamId() == null || dto.getTeamId().size() != 2) {
        throw new IllegalArgumentException("팀은 반드시 2개를 선택해야 합니다.");
    }

    List<TeamEntity> teams = teamRepository.findAllById(dto.getTeamId());
    if (teams.size() != 2) {
        throw new IllegalArgumentException("유효한 팀이 2개 존재해야 합니다.");
    }

    viewing.setTeam(teams.get(0));

    System.out.println("두 번째 팀: " + teams.get(1).getTeamName());

    viewingRepository.save(viewing);
}

@Override
public void deleteViewing(Long viewingCode) {
    ViewingEntity viewing = viewingRepository.findById(viewingCode)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관람입니다."));
    viewing.setViewingStatus(ViewingStatus.DELETED);
  List<Long> userIds=viewingUserRepository.findByViewing_ViewingCode(viewingCode).stream()
      .map(vu->vu.getMember().getUserCode())
      .distinct()
      .toList();
  System.out.println("유저 아이디:"+userIds);
  Long notificationTypeCode = (long)2;
  for(Long u:userIds){
    publisher.publishEvent(new NotificationCreatedEvent(u,notificationTypeCode) );
  }
}

/* ================= 이용자 ================= */

@Override
public void applyViewing(ViewingUserDto dto) {
    UserEntity user = userRepository.findById(dto.getUserCode())
            .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

    ViewingEntity viewing = viewingRepository.findById(dto.getViewingCode())
            .orElseThrow(() -> new IllegalArgumentException("관람이 존재하지 않습니다."));

    ViewingUserEntity viewingUser = ViewingUserEntity.builder()
            .viewingUserDeposit(dto.getViewingUserDeposit())
            .viewingUserIsAttend(false)
            .viewing(viewing)
            .member(user)
            .build();

    viewingUserRepository.save(viewingUser);

}

@Override
public void modifyViewing(ViewingUserDto dto) {
    ViewingUserEntity viewingUser = viewingUserRepository.findById(dto.getViewingUserCode())
            .orElseThrow(() -> new IllegalArgumentException("참가자가 존재하지 않습니다."));
    viewingUser.setViewingUserDeposit(dto.getViewingUserDeposit());
    viewingUser.setViewingUserIsAttend(dto.getViewingUserIsAttend());
}

@Override
public void cancelViewing(Long viewingUserCode) {
    ViewingUserEntity viewingUser = viewingUserRepository.findById(viewingUserCode)
            .orElseThrow(() -> new IllegalArgumentException("참가자가 존재하지 않습니다."));
    viewingUserRepository.delete(viewingUser);
}
}
