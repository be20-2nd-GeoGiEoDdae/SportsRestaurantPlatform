package com.ohgiraffers.geogieoddae.pay.query.dto;

import org.springframework.data.domain.Page;

public class PayPagination {
  public static PayPaginationButton getPagingButtonInfo(Page page) {
    int currentPage = page.getNumber() + 1;

    int defaultButtonCount = 3;

    int startPage
        = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1)
        * defaultButtonCount + 1;
    int endPage = startPage + defaultButtonCount - 1;

    if(page.getTotalPages() < endPage) endPage = page.getTotalPages();

    if(page.getTotalPages() == 0 && endPage == 0) endPage = startPage;

    return new PayPaginationButton(currentPage, startPage, endPage);
  }
}
