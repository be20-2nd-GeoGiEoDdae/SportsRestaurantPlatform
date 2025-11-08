package com.geogieoddae.mainservice.viewing.command.service;

import com.geogieoddae.mainservice.client.UserFeignClient;
import com.geogieoddae.mainservice.client.UserInfoResponse;
import com.geogieoddae.mainservice.notification.command.event.AlarmCreatedEvent;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import com.geogieoddae.mainservice.restaurant.command.repository.bookmark.BookmarkRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.restaurant.RestaurantRepository;
import com.geogieoddae.mainservice.sports.command.entity.SportsEntity;
import com.geogieoddae.mainservice.sports.command.entity.TeamEntity;
import com.geogieoddae.mainservice.sports.command.repository.SportsRepository;
import com.geogieoddae.mainservice.sports.command.repository.TeamRepository;
import com.geogieoddae.mainservice.viewing.command.dto.ViewingDto;
import com.geogieoddae.mainservice.viewing.command.dto.ViewingUserDto;
import com.geogieoddae.mainservice.viewing.command.entity.ViewingEntity;
import com.geogieoddae.mainservice.viewing.command.entity.ViewingStatus;
import com.geogieoddae.mainservice.viewing.command.entity.ViewingUserEntity;
import com.geogieoddae.mainservice.viewing.command.repository.ViewingRepository;
import com.geogieoddae.mainservice.viewing.command.repository.ViewingUserRepository;
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
    private final ApplicationEventPublisher publisher;
    private final BookmarkRepository bookmarkRepository;
    private final UserFeignClient userFeignClient;

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

//      List<Long>userIds =bookmarkRepository.findByRestaurant_RestaurantCode(dto.getRestaurantId())
//          .stream()
//          .map(b->b.getId().getUserCode()
//          .distinct()              // 중복 제거(옵션)
//          .toList();
//      Long notificationTypeCode = (long)3;
//      for(Long u:userIds){
//        publisher.publishEvent(new AlarmCreatedEvent(u,notificationTypeCode) );
//      }
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
//  List<Long> userIds=viewingUserRepository.findByViewing_ViewingCode(viewingCode).stream()
//      .map(vu->vu.getMember().getUserCode())
//      .distinct()
//      .toList();
//  System.out.println("유저 아이디:"+userIds);
//  Long notificationTypeCode = (long)2;
//  for(Long u:userIds){
//    publisher.publishEvent(new AlarmCreatedEvent(u,notificationTypeCode) );
//  }
}

/* ================= 이용자 ================= */

@Override
public void applyViewing(ViewingUserDto dto) {
    UserInfoResponse user = userFeignClient.getUserById(dto.getUserCode());
    ViewingEntity viewing = viewingRepository.findById(dto.getViewingCode())
            .orElseThrow(() -> new IllegalArgumentException("관람이 존재하지 않습니다."));

    ViewingUserEntity viewingUser = ViewingUserEntity.builder()
            .viewingUserDeposit(dto.getViewingUserDeposit())
            .viewingUserIsAttend(false)
            .viewing(viewing)
            .memberCode(user.getUserCode())
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
