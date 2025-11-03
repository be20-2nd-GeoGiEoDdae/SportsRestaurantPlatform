package com.ohgiraffers.geogieoddae.viewing.command.service;

import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingDto;
import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingUserDto;

public interface ViewingService {

    //관람 개설(사업자)
    void createViewing(ViewingDto dto);
    //관람 수정(사업자)
    void updateViewing(ViewingDto dto);
    //관람 취소(사업자)
    void deleteViewing(Long viewingCode);

    //관람 참가(이용자)
    void applyViewing(ViewingUserDto dto);
    //관람 수정(이용자)
    void modifyViewing(ViewingUserDto dto);
    //관람 취소(이용자)
    void cancelViewing(Long viewingUserCode);



}
