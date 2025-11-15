# 한화시스템 Beyond SW 20기 백엔드 프로젝트 — 거기어때?

<img width="1124" height="581" alt="Image" src="https://github.com/user-attachments/assets/f7965036-ef64-4dbb-9ddc-198d322ed567" />

> 스포츠단체관람을 위해 음식점을 통대관할수 있게 도와주는 서비스

---

## 👥 팀 구성

| 이름 | 역할 | 주요 기술 |
|---|---:|---|
| 박규진 | 관람, 식당 백엔드 및 총괄 | Java, Spring Boot, JPA, Mybatis | 
| 박인수 | 결제, 알림 백엔드 | Java, Spring Boot, JPA, Mybatis | 
| 김성현 | 인증/인가 백엔드 | Java, Spring Boot, JPA, Mybatis | 
| 성현제 | 공지사항, 신고 백엔드 | Java, Spring Boot, JPA, Mybatis |

---

## 🛠️ 기술스택택

### 백엔드
<a href="https://spring.io/" target="_blank"><img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring"></a>
<a href="https://spring.io/projects/spring-boot" target="_blank"><img src="https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"></a>
<a href="https://spring.io/projects/spring-security" target="_blank"><img src="https://img.shields.io/badge/springsecurity-%236DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security"></a>
<a href="https://www.java.com/ko/" target="_blank"><img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"></a>
<a href="https://hibernate.org/" target="_blank"><img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" alt="Hibernate(Spring Data JPA)"></a>
<a href="https://mariadb.org/" target="_blank"><img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white" alt="MariaDB"></a>
<a href="https://www.jwt.io/" target="_blank"><img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens" alt="JWT"></a>

---
## ✏️ 프로젝트 개요
<p>요즘 스포츠 팬들은 단순한 경기 시청보다 같은 팀을 응원하며 함께 즐기는 경험을 원합니다.하지만 일반 음식점에서는 원하는 경기를 틀어주지 않거나, 단체로 응원하기 어려운 환경이 많습니다. 
따라서, 저희 플랫폼인 ‘거기어때?’는 사용자가 원하는 경기, 시간, 인원수, 음식점 분위기등을 선택하면 원하는 시간에 해당 경기를 틀어주는 음식점을 찾아 예약 및 결제까지 한 번에 해결할 수 있는 스포츠 관람 예약 플랫폼입니다.</p>

---
## 📄 프로젝트 배경
<details>
   <summary><strong> 기존시장의 문제점 </strong></summary>
<img width="1040" height="1087" alt="Image" src="https://github.com/user-attachments/assets/5416f8ee-d2c6-40db-9f4d-f66efe8aace9" />

<p>[첨부1,2 각각 구글, 당근중고 거래사이트  -   ‘스포츠 틀어주는 가게’ 연관 검색 결과 (검색 일시 2025.11.06  ) ]</p> 

<p>오늘날 스포츠 팬들에게 경기는 단순한 시청을 넘어 함께 어울려 응원하는 사회적 경험이 되고 있습니다.  혼자 집에서 보는 것보다 여러 사람이 한데 모여 같은 팀을 응원할 때 경기의 즐거움과 몰입감이 훨씬 커지기 때문입니다.  실제 설문에서도 팬들의 89%가 가족이나 친구와 함께 경기를 보는 것이 스포츠를 즐기는 데 중요하다고 답했고, 거의 절반은 이를 가장 중요한 요소로 꼽았습니다. 미국의 조사이긴 하지만, 2024년 기준 60% 이상의 팬들이 주로 다른 사람들과 함께 스포츠를 시청하고, 약 40%는 미리 일정을 맞춰 함께 볼 정도로 스포츠 경기를 계획된 사회활동으로 여기고 있었습니다. 이처럼 스포츠 경기 관람은 마지막 남은 실시간 공동체적 여가라는 말이 나올 정도로, 팬들은 함께 모여 응원하는 경험을 갈망합니다. </p>

<p>그러나 현실에서는 원하는 경기를 틀어주는 장소를 찾기가 쉽지 않은 문제가 있습니다. 일반 식당이나 술집에 가면 내가 보고 싶은 경기를 요청하기 어렵거나, 가게마다 TV 채널 사정이 달라 원하는 중계를 못 보는 경우가 많습니다. 특별한 스포츠펍들이 존재하지만, 어떤 펍이 어느 경기를 상영해주는지 정보를 얻기가 어렵고 보통 입소문이나 개별 SNS 공지에 의존해야 합니다. 실제로 유명 스포츠펍들은 중요한 경기 날이면 공식 인스타그램을 통해 예약 방법을 안내하는 식으로 개별 대응을 하고 있습니다. 인기 스포츠 경기의 경우 자리 확보도 큰 문제입니다. 예를 들어 한 야구 테마 포차는 야구 시즌 경기 당일에는 예약 없이는 앉을 자리가 없을 정도로 만석이 되며, 서울의 유명 축구 펍 역시 중요 경기가 있는 날엔 예약하지 않으면 자리가 없을 정도로 인기가 많다고 알려져 있습니다. 이러한 불편함 때문에 팬들은 직접 여러 장소에 전화를 걸어 중계 여부를 묻거나, 인터넷 블로그・카페 글을 찾아 헤매야 하는 실정입니다.</p>
</details>


## 📌타 서비스와의 차별점 
<details>
   <summary><strong> 차별점 </strong></summary>
<p>현재 국내에는 스포츠 경기 관람에 특화된 예약 플랫폼이 전무한 상황입니다. 일반적인 맛집 앱이나 포털 예약 서비스는 식당의 분위기나 메뉴는 안내하지만, 어떤 경기를 중계하는지 정보 제공이 없고 사용자도 그런 조건으로 검색할 수 없습니다. 스포츠 팬들은 주로 지인들에게 물어보거나, 앞서 언급한 대로 여기저기 SNS와 커뮤니티를 뒤져가며 장소를 찾아야 합니다. </p>

<p>일부 해외에서는 DIRECTV의 스포츠 바 찾기나 FANZO와 같이 경기 중계 펍을 찾는 전문 앱이 존재하여 편의를 높이고 있습니다. 예를 들어 영국의 FANZO 앱은 “어디서 어떤 경기를 틀어줄까?” 하는 고민을 덜어주며, 보고 싶은 경기와 가까운 펍을 찾아주고 몇 초 만에 테이블 예약까지 가능하게 합니다. 이를 통해 이용자는 펍에 일일이 전화하지 않아도 되고, 어느 곳에서 원하는 경기를 확실히 틀어주는지 미리 알 수 있습니다. 결국 FANZO는“라이브 스포츠는 여럿이 함께할 때 더 즐겁다”는 모토 아래 전 세계 250만 명 이상의 팬들이 이용하는 대표 플랫폼으로 성장했습니다. “거기어때?”는 이처럼 스포츠 관람에 특화된 원스톱 예약 서비스라는 점에서 기존 국내 서비스들과 명확히 구별됩니다.</p>

<p>또한 경기별로 최적화된 검색과 필터 기능을 제공합니다. 사용자는 종목이나 리그뿐 아니라 특정 경기/팀 단위로 검색해 그 경기를 보여주는 장소만 추려볼 수 있습니다. 원하는 경우 응원 분위기, 좌석 형태, 주류 판매 여부 등 세부 조건으로 필터링하여 선호에 맞는 장소를 찾을 수 있습니다.  이런 세분화된 검색은 일반 예약 앱에서는 불가능한, 스포츠 플랫폼만의 강점입니다.  

마지막으로 앱 내 예약 및 결제 연동을 통해 편의성을 극대화했습니다. 전화 예약이나 현장 대기가 아닌 온라인 사전예약이므로 경기 시작에 맞춰 편하게 착석할 수 있고, 선결제 시스템으로 노쇼(no-show) 방지와 원활한 정산도 가능합니다. 이처럼 ”거기어때?”는 스포츠 팬들의 요구에 특화된 기능과 사용자 경험으로 타 서비스와 차별화를 이루고 있습니다.</p>
</details>


## 📌서비스 대상 
| 고객군 | 핵심 니즈 | 이용 시나리오 |
|---|---|---|
| 프로스포츠 팬층 (남녀노소) | 큰 화면·응원 분위기, 케이블/대형TV가 없는 대체 공간 | 가족·개인 단위 경기일 방문, 생중계 응원 |
| 직장 동료·단체 관람 수요 | 단체 좌석·간편 예약·좌석 확보 보장 | 부서 회식·동호회·대학 동아리 단체 예약 |
| 특정 팀/종목 팬클럽 | 팀 맞춤 좌석·응원 도구·시간대 맞춤 서비스 | 해외 새벽 경기 등 팬클럽 오프라인 응원전 |
| 스포츠 관심 일반 고객층 | 이벤트성 분위기·색다른 데이트/모임 코스 | 월드컵·올림픽 시즌, 데이트·소규모 모임 |



## 📌기대효과
<details>
  <summary>첫번째</summary>
- 스포츠 팬들의 만족도 증대: 가장 직접적인 효과는 팬들이 응원 파트너를 쉽게 찾고 함께 즐길 기회가 늘어난다는 점입니다. 이제까지는 혼자 TV로 보거나 장소 수소문하는데 어려움을 겪었다면, 저희 플랫폼으로 간편히 모임을 갖고 열띤 응원 분위기를 즐길 수 있습니다. 이는 팬 경험의 질을 높여 더욱 충성도 높은 팬으로 발전시키는 선순환을 낳습니다. 실제로 여럿이 함께 시청하는 팬들은 경기에 몰입하고 오래 시청하는 경향이 높아, 스포츠 소비 자체가 증진되는 효과도 기대됩니다. 특히 미리 약속을 잡고 모이는 ‘계획된 관람’의 경우 경기 시청을 위한 음식과 음료 소비도 증진시켜준 것으로 조사되었습니다. 이는 팬들에게는 즐거운 소비 경험이 되고, 스포츠 산업 전체로도 긍정적인 영향을 줍니다.
  </details>
  <details>
    <summary>두번째</summary>
- 음식점/펍 매출 및 홍보 효과: 플랫폼에 참여하는 업주들에게는 매출 증대와 신규 고객 유입 효과가 있습니다. 평소 스포츠 중계를 제공하던 매장은 저희 플랫폼을 통해 팬층을 직접 타겟팅하여 빈자리 없이 손님을 채울 수 있습니다. 특히 인기 경기 일정에 맞춘 예약 선점으로 노쇼 감소와 안정적인 운영이 가능해집니다. 또한 잘 알려지지 않은 장소도 앱에서 스포츠 중계 장소로 노출되면서 새로운 고객층을 확보할 수 있습니다. 플랫폼 사용자들이 후기와 평점을 남기면 입소문 이상의 홍보 효과를 기대할 수도 있습니다. 결국 플랫폼이 성장하면 참여 매장들은 단골 커뮤니티 확보와 비수기 타개 등의 부가 이익도 얻을 것입니다.
    </details>
    <details>
      <summary>세번째</summary>
- 플랫폼 수익 모델 및 성장성: “거기어때?”는 수익 측면에서도 여러 경로의 비즈니스 모델을 갖추고 있습니다. 우선 예약 중개 수수료를 통해 기본적인 수익을 올릴 수 있습니다 (예: 예약 좌석당 일정 금액 혹은 결제액의 일정 비율). 또한 인기 시즌 광고 수익이 기대되는데, 중요한 경기나 대회 시즌에 맞춰 관련 음식점들의 배너 광고나 프로모션 노출 서비스를 유료화할 수 있습니다. 이 밖에도 스포츠 관련 업계와의 제휴를 통한 부가 수익을 모색합니다. 주요 주류 브랜드와 협업하여 앱 이용자 대상 할인 쿠폰을 발행하거나, 굿즈/응원용품 판매를 연계함으로써 수익을 다각화할 수 있습니다. 이러한 다양한 수익 채널은 플랫폼의 안정적 운영과 향후 투자 유치에도 긍정적으로 작용할 것입니다.
      </details>
      <details>
        <summary>네번째</summary>
- 스포츠 문화 및 산업에의 기여: 궁극적으로 “거기어때?” 의 활성화는 국내 스포츠 문화의 저변 확대로 이어질 수 있습니다. 팬들이 쉽게 모여 응원함으로써 종목을 불문하고 직관 못지않은 응원 문화가 형성되고, 이는 프로구단이나 리그의 인기도를 높여줄 가능성이 있습니다. 경기 관람을 하나의 사회적 이벤트로 자리매김시킴으로써 스포츠가 가지는 커뮤니티적 가치를 부각하고, 더 많은 잠재 팬층을 끌어들이는 효과도 기대됩니다. 나아가 이런 팬 활동 증가와 데이터 축적은 스포츠 마케팅이나 스폰서십 기회의 증대로도 연결될 수 있어, 스포츠 산업 전반에 활력을 불어넣는 역할을 할 것입니다.
</details>


## 주요 기능
| 구분   | 기능명                 | 설명                                               |
|--------|------------------------|----------------------------------------------------|
| 사용자 | 경기 검색              | 날짜, 종목, 팀 별로 보고 싶은 경기 선택            |
| 사용자 | 🍽️ 음식점 예약         | 경기 상영 가능한 음식점 목록 확인 및 인원, 메뉴, 시간 예약 |
| 사용자 | ⭐ 리뷰                 | 음식, 응원 분위기, 관람 환경 평가                 |
| 점주   | 📺 경기 등록            | 상영 가능한 경기 관람 등록                         |
| 점주   | 📊 예약 관리           | 예약 내역, 메뉴 준비 관리                         |
| 결제   | 💳 카드/계좌 결제      | 예약금 환불 제도, 점주 구독 결제     
---



## 📄 요구사항명세서·WBS (Google Sheets)

<details>
  <summary>요구사항명세서</summary>
    https://docs.google.com/spreadsheets/d/1fdSv-AS8EdeEeRGrgxjWvRoyUO11oDH1wEkOj6HnHXg/edit?gid=1436566125#gid=1436566125
<img width="1623" height="1025" alt="Image" src="https://github.com/user-attachments/assets/3625dc9a-fdb3-4daf-8bea-8ef111ca2c6c" />
<img width="1624" height="887" alt="Image" src="https://github.com/user-attachments/assets/99049308-92f7-4af3-a1e2-475bac665c40" />
<img width="1623" height="1057" alt="Image" src="https://github.com/user-attachments/assets/df012088-0d65-4465-9057-b291770bb277" />
</details>

## 📄 DDD (miro)

<details>
   <summary>DDD</summary>
     https://miro.com/app/board/uXjVJ5lwdoc=/
<img width="1730" height="1083" alt="Image" src="https://github.com/user-attachments/assets/f6311ce3-3f07-4175-898f-e4c5e25978f9" />
</details>


## 📄 ERD (ERDCloud)

<details>
   <summary>ERD</summary>
     [https://www.erdcloud.com/d/BheLqjBhttiyWkdZj](https://www.erdcloud.com/d/A9A6aT89xQCdqGfiH)
   
  <img width="1516" height="795" alt="Image" src="https://github.com/user-attachments/assets/10ae4370-2ba3-4a85-b5fa-124cf6313740" />
</details>

  
 ## 📄POST MAN
 
 <details>
    <summary>POST MAN</summary>
https://www.postman.com/tjdguswp333-9617076/be20-geogieodae/collection/imgd5tf/geogieoddae?action=share&creator=49562230
</details>

 ## 📄Swagger

 <details>
    <summary>swagger</summary>
 [Geogieoddae REST API 명세서 (PDF)](https://drive.google.com/file/d/1B4QOAC8QPX8boM1XvGrU_wPsrS5z-yL5/view?usp=sharing))
</details>


 ## 📄**테스트 케이스 상세**
<details>

  
  ###  회원 관리 기능

  <details>
  <summary>소셜 회원가입</summary>
  
  ![Image](https://github.com/user-attachments/assets/fd0a5770-0885-4c33-a877-f001b5757f94)
  
 
</details>

<details>
<summary>소셜 로그인</summary>

![Image](https://github.com/user-attachments/assets/1eaf8785-b8bf-458a-aea5-ee1fb6d8012a)
</details>

<details>
<summary>회원 계정 복구</summary>

![Image](https://github.com/user-attachments/assets/989163c0-a8a1-470a-b629-d1193a8c6058)
</details>

<details>
<summary>테스트 유저 로그인</summary>

![Image](https://github.com/user-attachments/assets/cd88061c-6e70-420d-aaab-a652a2d8ac3a)
</details>


 ###  결제 관련 기능
 <details>
 <summary>회원 예약금 결제등록</summary>
 
 ![Image](https://github.com/user-attachments/assets/57ab064c-5d1e-400a-9ba0-573bb6a74ed0)
 </details>

<details>
 <summary>회원 예약금 결제api</summary>
 
 ![Image](https://github.com/user-attachments/assets/88153d2f-bc7f-4907-886b-ea82e528fcd7)
 </details>

 <details>
 <summary>회원 예약금 환불</summary>
 
 ![Image](https://github.com/user-attachments/assets/6eb1b5c9-f6d7-4057-992d-215b1866a7b1)
 </details>

 <details>
 <summary>점주 구독결제 customerkey생성 </summary>
 
 ![Image](https://github.com/user-attachments/assets/edeab915-2fae-4c2c-bcbc-60dbf2c1ff56)
 
 </details>

 <details>
 <summary>점주 구독상품 결제 빌링키 생성 </summary>
 
 ![Image](https://github.com/user-attachments/assets/f72c01c1-182d-44be-8a6d-47b08dfa1656)
 
 </details>

 <details>
 <summary> 정기 구독결제 승인</summary>
 
 ![Image](https://github.com/user-attachments/assets/cbb37630-a414-49ac-aad5-437417ca6ed4)
 
 </details>

 <details>
 <summary>정기 구독결제 삭제</summary>
 
 ![Image](https://github.com/user-attachments/assets/5accc921-9588-4a0e-a696-ce083013e81b)
 
 </details>
 <details>
 <summary>관람 결제정보 전체조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/d6b7538a-7e89-4ca0-b88b-17be640ec577)
 
 </details>
 <details>
 <summary>관람 결제정보 관람코드기준 조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/c92cb919-5d7c-4b55-b525-6cc45c4d1652)
 
 </details>

 <details>
 <summary>구독 결제 기간별 조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/73318b6b-05e2-47c0-baa0-142b74949c15)
 
 </details>
 <details>
 <summary>구독결제 사업자코드로 조회</summary>

 ![Image](https://github.com/user-attachments/assets/ce68d255-44f2-4bdf-badd-7eb362b9df91)
 
 
 </details>

  ###  가게 관련 기능
  <details>
  <summary>사업자 등록 신청</summary>
 
 ![Image](https://github.com/user-attachments/assets/00ca0ff5-9cbf-4f41-a052-3d072662464d)

  </details>
  <details>
  <summary>사업자 가게 등록</summary>
 
 
![Image](https://github.com/user-attachments/assets/0bf12597-be58-4bf0-b2b4-f241d19709ab)

  </details>
  <details>
  <summary>가게 삭제</summary>
  
  ![Image](https://github.com/user-attachments/assets/f6c8e7af-f7e7-4552-9b35-e6bb083e9c16)
  </details>
  <details>
  <summary>가게 키워드 등록</summary>
 
 ![Image](https://github.com/user-attachments/assets/2c60f1c5-e8c1-42b2-a6e4-8e01bf2396e5)
 
  </details>
  <details>
  <summary>가게 키워드 삭제</summary>
 
 ![Image](https://github.com/user-attachments/assets/62d06f5f-c9da-4fa5-a62f-13f42f0dcecd)
  </details>
  <details>
  <summary>가게 리뷰 등록</summary>
 
 ![Image](https://github.com/user-attachments/assets/77cdcf95-a4d8-4d88-ba56-d246a65441c5)
  </details>
  <details>
  <summary>가게 리뷰 수정</summary>
 
 
![Image](https://github.com/user-attachments/assets/df1d1397-e04e-456b-a347-e0fb0ceb5a8a)
  </details>
  <details>
  <summary>가게 리뷰 삭제</summary>
 
 ![Image](https://github.com/user-attachments/assets/b0cfc78c-256b-448c-961c-e11615fc8b49)
  </details>
  <details>
  <summary>가게 리뷰 점수별 조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/3c12d761-d932-479e-bb66-4817f4501fb8)

  </details>
  <details>
  <summary>가게 즐겨찾기 등록</summary>
 
 ![Image](https://github.com/user-attachments/assets/b1b91a9f-f4de-4c27-8715-d388b0d0204a)
  </details>
  <details>
  <summary>가게목록 전체 조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/adddbbe2-c1df-42ec-8ef4-f63cb4ad5180)
  </details>
   <details>
  <summary>가게 상세 조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/ef202faf-634c-4546-92f0-d829be03276d)
 
  </details>

 ###  관람 관련 기능
 <details>
 <summary>관람 검색(키워드 입력시)</summary>
 
 ![Image](https://github.com/user-attachments/assets/02ad21ce-b8e4-4822-a403-e5ae856c4283)
 </details>
 <details>
 <summary>관람 검색 (관람코드 입력시)</summary>
 
 ![Image](https://github.com/user-attachments/assets/42f515c0-e1eb-413d-a2ae-eb1e040605e8)
 </details>

<details>
 <summary>관람 결제 생성 </summary>
 
 ![Image](https://github.com/user-attachments/assets/b7084192-3789-4f93-8ad4-ba0e865e0716)
 </details>

<details>
 <summary>관람 결제요청 및 승인</summary>
 
 ![Image](https://github.com/user-attachments/assets/80f40f6c-b355-48e5-8389-3f66e95595d0)
 </details>

<details>
 <summary>관람결제 취소</summary>
 
 ![Image](https://github.com/user-attachments/assets/736ee75a-be44-4a89-8326-3ad0a7e06cfd)
 </details>

<details>
 <summary>관람결제 된 내역조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/1730579f-ece5-465b-86e6-a6ae30ceacb8)
 </details>
<details>
 <summary>관람 목록 조회</summary>
 
 ![Image](https://github.com/user-attachments/assets/f6be6199-0482-4b66-8a57-ee59c7f50678)
 </details>
<details>
 <summary>관람 삭제(점주)</summary>
 
 ![Image](https://github.com/user-attachments/assets/b97e0fcc-5fc3-4ded-be6c-fb10f07ed703)
 </details>
 <details>
 <summary>관람 수정(점주)</summary>
 
 ![Image](https://github.com/user-attachments/assets/1aa6cf77-93ac-4986-a213-f5776ffc84f1)
 </details>
 <details>
 <summary>관람 신청</summary>
 
 ![Image](https://github.com/user-attachments/assets/0a596f3c-532e-46d7-96c1-be3d4d8d49ec)
 </details>
 <details>
 <summary>관람 신청내역 수정 </summary>
 
 ![Image](https://github.com/user-attachments/assets/65e86e2f-b121-4a34-b711-1986ddd8d4d2)
 </details>
 <details>
 <summary>관람 삭제 </summary>
 
 ![Image](https://github.com/user-attachments/assets/9d30dbef-ff6e-4751-8e97-eef60fa38724)
 </details>
 <details>
 <summary>관람 검색</summary>
 
 ![Image](https://github.com/user-attachments/assets/fceaac0c-89e7-48dc-a422-4f36672d4949)
 </details>

###  신고 및 블랙리스트 관련기능
  <details>
  <summary>블랙리스트 추가</summary>

  ![Image](https://github.com/user-attachments/assets/23d3eea4-aeab-477e-81fe-39cc193a16ce)

  </details>
  <details>
  <summary>블랙리스트 삭제</summary>

  ![Image](https://github.com/user-attachments/assets/d2b32088-d0bc-455f-86fa-b88d98b4a891)
  </details>
   <details>
  <summary>신고타입 등록</summary>

  ![Image](https://github.com/user-attachments/assets/2cd2cbed-f8d2-4583-90e7-20cd74aba50e)

  </details>
   <details>
  <summary>신고타입 삭제</summary>

  ![Image](https://github.com/user-attachments/assets/e7cd0f85-91b6-4626-a292-815c63d9549b)
  </details>
   <details>
  <summary>신고 등록</summary>

  ![Image](https://github.com/user-attachments/assets/062c0edc-d2f0-42ff-94e5-4b0e9fc10fea)
  </details>
   <details>
  <summary>관리자 신고승인</summary>

  ![Image](https://github.com/user-attachments/assets/d41869f4-7496-481a-a70c-e56c1085ed93)
  </details>
   <details>
  <summary>관리자 신고삭제</summary>

  ![Image](https://github.com/user-attachments/assets/31a190b5-56d4-4e7b-a683-24623a98302a)
  </details>
 
 ###  알림 및 공지사항 
 <details>
 <summary>알림 유형 등록</summary>
 
 ![Image](https://github.com/user-attachments/assets/5b5cf3d9-665d-4871-81f4-7218e14c6019)
 </details>
<details>
 <summary>알림 수정</summary>

![Image](https://github.com/user-attachments/assets/8098782b-56a3-4bec-96d3-221320f9059b)
 </details>
<details>
 <summary>알림 유형 삭제</summary>

 ![Image](https://github.com/user-attachments/assets/77bce20f-c0cc-4ba1-8f1a-8ca629c7142d)
 </details>
<details>
 <summary>알림 유형 전체 조회</summary>

![Image](https://github.com/user-attachments/assets/5fa28d6c-3664-4c26-9cb7-cfb59e9e48b0)
 </details>
 <details>
 <summary>알림 유형 추가</summary>

![Image](https://github.com/user-attachments/assets/ecacc859-0b87-4a73-b4c1-1f542eb1e1ee)
 </details>
<details>
 <summary>관람 신청시 알림</summary>

![Image](https://github.com/user-attachments/assets/14169c1e-d593-4f71-863c-43ff5d968090)
 </details>
<details>
 <summary>공지사항 등록</summary>

![Image](https://github.com/user-attachments/assets/9e0094e4-6635-41c6-9712-5fbb12a94033)
 </details>
<details>
 <summary>공지사항 수정</summary>

![Image](https://github.com/user-attachments/assets/065a79ac-4cfd-44cb-b785-3e41f113a873)
 </details>
<details>
 <summary>공지사항 삭제</summary>

![Image](https://github.com/user-attachments/assets/09dea816-a7ec-4c9b-a7f1-cad3edd01b84)
 </details>
<details>
 <summary>공지사항 목록조회</summary>

![Image](https://github.com/user-attachments/assets/b4642ee7-49a0-45b0-9da7-9b4164cad1cc)
 </details>
<details>
 <summary>공지사항 상세조회</summary>

![Image](https://github.com/user-attachments/assets/5d7fd87d-7ee3-48c6-8a86-2402d0f42bb4)
 </details>

### 스포츠 종목 및 팀 기능
<details>
 <summary> 종목 등록 </summary>

![Image](https://github.com/user-attachments/assets/597234c0-e50a-46f5-ad5f-5e316edea60b)
 </details>
 <details>
 <summary> 종목 수정 </summary>

![Image](https://github.com/user-attachments/assets/da1fce82-3151-4399-8afb-5c0b57a4ad30)
 </details>
 <details>
 <summary> 종목 삭제 </summary>

![Image](https://github.com/user-attachments/assets/dd6ccb27-3c03-4143-b4c1-19a570344f60)
 </details>
 <details>
 <summary> 종목 조회 </summary>

![Image](https://github.com/user-attachments/assets/a69a1da2-9464-4d56-bdac-91210049a5fd)
 </details>
 <details>
 <summary> 종목 상세조회 </summary>

![Image](https://github.com/user-attachments/assets/cb63fb26-2faa-4a54-a02f-53ad2d141bbb)
 </details>
 <details>
 <summary> 팀 등록 </summary>

![Image](https://github.com/user-attachments/assets/73b87fba-54b4-4a78-8f0f-b5f4fbb22997)
 </details>
 <details>
 <summary> 팀 수정  </summary>

![Image](https://github.com/user-attachments/assets/5a98c195-605d-4700-b629-86a8aa161381)
 </details>
 <details>
 <summary> 팀 삭제 </summary>

![Image](https://github.com/user-attachments/assets/c306c29a-3747-4a5f-a19f-b6e77b61ec0b)
 </details>
 <details>
 <summary> 팀 조회 </summary>

![Image](https://github.com/user-attachments/assets/efde0a98-10f8-413e-95c3-7e10f330971d)
 </details>
 <details>
 <summary> 팀 상세조회 </summary>

![Image](https://github.com/user-attachments/assets/28a96f6d-f29f-403a-b255-1f22797415e2)
 </details>
 <details>
 <summary> 특정종목의 팀조회  </summary>

![Image](https://github.com/user-attachments/assets/292285e4-538d-4903-b103-311597e87ff0)
 </details>

</details>

## MSA 아키텍쳐 구조도 

<details>
   <summary>이미지</summary>
  
<img width="2430" height="390" alt="Image" src="https://github.com/user-attachments/assets/fe2e54ee-cd3f-44df-b0e8-0dd3de234871" />
</details>


## 팀원 회고
<details>
 <summary> 박인수 </summary>
처음에는 테이블을 충분히 고민해서 설계했다고 생각했지만,실제 개발을 진행하면서 지속적으로 수정이 발생했습니다.
이를 통해 앞으로 테이블을 정의할 때는이후 개발 과정과 변경 가능성까지 깊게 고려해야한다는걸 꺠달았고.
또한 개발을 시작하기 전에 팀원들과 응답 포맷, 쿼리 작성 방식 등의 규칙을 먼저 정해두었는데,
 이 덕분에 이후 수정·검토 과정에서 혼선을 줄이고 효율적으로 협업할 수 있었습니다.
백엔드 프로젝트에서는 외부 API를 연동하는 역할을 맡아, 사용에 필요한 조건들을 분석하고 이를 우리 프로젝트에 어떻게 적용할지 계속 고민하며 개발을 진행했습니다.
 다만 실제 적용 과정이 예상보다 오래 걸리면서 세부적인 부분까지 충분히 다루지 못했고,예외 처리를 제대로 하지 못한 점이 아쉽게 남았습니다.
 앞으로는 외부 연동 시 초기 설계 단계에서 예외 상황과 세부 로직까지 더 면밀히 계획하는 습관을 가져야겠다고 느꼈습니다

 </details>
<details>
 <summary> 박규진  </summary>
이번 프로젝트를 통해 REST API와 스프링 전반에 대한 이해가 확실히 깊어졌다. 다만 팀 인원이 줄어든 만큼 프로젝트 범위를 조금 더 축소하고, 각 기능을 더 깊게 파고들지 못한 점은 아쉬움으로 남는다. 시간적 여유가 있었다면 트랜잭션을 더 완성도 있게 적용하고, 서비스 특성에 맞춘 낙관적·비관적 락 전략, 그리고 유연한 MSA 설계까지 시도해보고 싶었는데 그러지 못해 아쉬움이 크다. 그럼에도 불구하고 좋은 팀원들을 만나 프로젝트를 끝까지 잘 마칠 수 있었던 거 같다.


 </details>
<details>
 <summary> 김성현 </summary>
 2번째 백엔드프로젝트 (거기어때) 회고

이번 백엔드프로젝트는 웹서비스가 어떻게 데이터베이스와 데이터를 주고 받는지를 집중적으로 익혀볼수 있는 좋은 기회였다고 생각된다. 나는 회원, 인증 관리 쪽을 주로 맡았는데 팀원들과 같이 협업을 할때 가장먼저 탄탄하게 구축이 되어야하는 가장 기본이 되는 파트라는 생각이 들었다. 백엔드 프로젝트에서는 데이터베이스 엔티티와 필드, 데이터타입 등이 설계 단계에서 구체적으로 잘 정의되어 있어야하는것 같은데, 설계단계에서 고려하지 못한 필드들이 구현단계에서 뒤늦게 생각나 작업시간이 더 길어진 측면도 있었다.
소셜 서비스를 연동한 웹서비스의 로그인 인증/인가 과정을 경험해본 것은 매우 귀중한 경험이었다. 여러 인증 방식중의 jwt 토큰이 보안측면에서 어떤 이점을 갖고있고, 암호의 구조는 어떻게 되어있는지 학습 해본것은 향후 웹개발자로써 귀중한 경험이 될 것 같다.
깃허브 로 팀원간 협력하는 것은 늘 어려운 일인것 같다. 이런 형상/이력관리 툴은 관리자와 일반사용자가 권한이 나뉘어 최종 승인은 관리자의 허가 하에 이뤄져야 이력관리툴의 본질에 더 맞겠다는 생각을 했다. 짧은 시간 동안 결과물을 내려고 하다보니, 각자의 개발 현황을 잘 추적하여 깃허브 운용을 하지 못했다는 것은 후회가 남는 점이다.
마지막으로, 내가 맡기로 한 두개의 도메인 중 하나는 아예 개발을 해보지 못해 팀동료가 대신하여 나의 몫을 매꾸줬다. 여전히 자바의 객체지향 방식으로 코드를 관리하고 역할을 분담하는 것에 익숙하지 않다보니 개발의 속도가 나지 못했고, 그런만큼 개발역량이 조금더 나은 팀동료가 더 고생을 하게 만든 것에 대한 미안함이 가득하다. 다음 프로젝트에서는, 조금더 팀원들간 소통을 활발히 하여 팀원의 부족한 점을 내가 채워줄수있는 개발자가 되고싶다.


 </details>
<details>
 <summary> 성현제 </summary>
이번 프로젝트는 저번 프로젝트 보다는 덜힘들었다. 그러나 자바도 완벽히하지 못했는데 스프링을 나가고, 스프링이 이해가 안되었지만 바로 프로젝트를 이어서 하느라 정신이 조금은 나가있었던 것 같다. 그래서 프로젝트 참여도도 첫프로젝트 보다는 적극적이지 못했다. 그래서 덜힘들었던 것 같기도 하다. 
<p>그리고, 타팀에 비해서 팀원수가 적다보니 각 팀원들이 더더욱 열심히 적극적으로 참여해야하는 상황이었고, 더더욱 나는 해본적이 없는 프로젝트이다 보니 해보신 분들에 비해서 몇배 정도 열심히 해야했음에도 불구하고 내가 생각하기에 노력의 총량이 팀원들에 비해 적었던 것 같아서 팀원들에게 미안했다. 팀원들이 배려를 해줘서보다 쉬운부분을 맡았음에도 불구하고 ,나에게는 쉬운부분을 CQRS를 맞추어 쓰는 것조차 버거웠다. 그래서 내가 느린 것과 사람이 부족한 부분때문에 프로젝트 발표전 테스트코드를 작성하지도 못했고, 예외처리 또한 전혀 하지못했다. 이렇게 완벽하지 못하게 프로젝트를 발표하게 된게 상당히 아쉬운 부분이었다. </p>
   <p>또한,  혼자서 코드를 짤때 인공지능의 도움을, 수업에 나와서는 github관련 문제, 코드문제 때문에 팀원에게 과의존하게 된 거 같아 나자신에게 아쉽고, 여러가지로 팀원에게 미안한 상황이 연출되어서 더더욱 아쉬운 프로젝트였다고 느껴지는 것 같다.</p>
그래서 내가 만족할 만한 프로젝트를 하기에는 실력과 의지 2가지 모두가 부족한 부분이 있는 것 같다. 조금 더 분발할 필요성을 느끼게 되는 프로젝트였다.


 </details>



