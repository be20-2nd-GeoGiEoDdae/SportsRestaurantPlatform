package com.ohgiraffers.geogieoddae.pay.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayPaginationButton {

  private int currentPage;
  private int startPage;
  private int endPage;

  public PayPaginationButton() {
  }

  public PayPaginationButton(int currentPage, int startPage, int endPage) {
    this.currentPage = currentPage;
    this.startPage = startPage;
    this.endPage = endPage;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getStartPage() {
    return startPage;
  }

  public void setStartPage(int startPage) {
    this.startPage = startPage;
  }

  public int getEndPage() {
    return endPage;
  }

  public void setEndPage(int endPage) {
    this.endPage = endPage;
  }

  @Override
  public String toString() {
    return "PagingButton{" +
        "currentPage=" + currentPage +
        ", startPage=" + startPage +
        ", endPage=" + endPage +
        '}';
  }
}

