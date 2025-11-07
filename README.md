# 한화시스템 Beyond SW 20기 백엔드 프로젝트 — 거기어떄

<img width="1124" height="581" alt="Image" src="https://github.com/user-attachments/assets/f7965036-ef64-4dbb-9ddc-198d322ed567" />

> 스포츠단체관람을 위해 음식점을 통대관할수 있게 도와주는 서비스

---

## 👥 팀 구성

| 역할 | 이름  |
|---|-----|
| 팀장 | 성현제 |
| 팀원 | 김성현 |
| 팀원 | 박규진 |
| 팀원 | 박인수 |

---

## 🛠️ 스텍

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
<p>요즘 스포츠 팬들은 단순한 경기 시청보다 **같은 팀을 응원하며 함께 즐기는 경험**을 원합니다.하지만 일반 음식점에서는 원하는 경기를 틀어주지 않거나, 단체로 응원하기 어려운 환경이 많습니다. 

저희 플랫폼인 ‘거기어때?’는 사용자가 원하는 **경기, 시간, 인원수, 음식점 분위기등을** 선택하면 원하는 **시간에 해당 경기를 틀어주는 음식점**을 찾아 **예약 및 결제**까지 한 번에 해결할 수 있는 **스포츠 관람 예약 플랫폼**입니다.</p>

---
## 📄 프로젝트 배경

<img width="1040" height="1087" alt="Image" src="https://github.com/user-attachments/assets/5416f8ee-d2c6-40db-9f4d-f66efe8aace9" />

<p>[첨부1,2 각각 구글, 당근중고 거래사이트  -   ‘스포츠 틀어주는 가게’ 연관 검색 결과 (검색 일시 2025.11.06  ) ]</p> 

<p>오늘날 스포츠 팬들에게 경기는 단순한 시청을 넘어 함께 어울려 응원하는 사회적 경험이 되고 있습니다.  혼자 집에서 보는 것보다 여러 사람이 한데 모여 같은 팀을 응원할 때 경기의 즐거움과 몰입감이 훨씬 커지기 때문입니다.  실제 설문에서도 팬들의 89%가 가족이나 친구와 함께 경기를 보는 것이 스포츠를 즐기는 데 중요하다고 답했고, 거의 절반은 이를 가장 중요한 요소로 꼽았습니다. 미국의 조사이긴 하지만, 2024년 기준 60% 이상의 팬들이 주로 다른 사람들과 함께 스포츠를 시청하고, 약 40%는 미리 일정을 맞춰 함께 볼 정도로 스포츠 경기를 계획된 사회활동으로 여기고 있었습니다. 이처럼 스포츠 경기 관람은 마지막 남은 실시간 공동체적 여가라는 말이 나올 정도로, 팬들은 함께 모여 응원하는 경험을 갈망합니다. 그럼에도 현재 이러한 기능이 포함된 플랫폼은 나오지 못한 실정입니다. </p>

<details>
  <summary><strong> 기존시장의 문제점 </strong></summary>

원하는 경기를 틀어주는 장소 찾기 불가 

같은 팀을 응원하는 사람끼리 모이는 공간 찾기 어려움 

그러나 현실에서는 원하는 경기를 틀어주는 장소를 찾기가 쉽지 않은 문제가 있습니다. 일반 식당이나 술집에 가면 내가 보고 싶은 경기를 요청하기 어렵거나, 가게마다 TV 채널 사정이 달라 원하는 중계를 못 보는 경우가 많습니다. 특별한 스포츠펍들이 존재하지만, 어떤 펍이 어느 경기를 상영해주는지 정보를 얻기가 어렵고 보통 입소문이나 개별 SNS 공지에 의존해야 합니다. 실제로 유명 스포츠펍들은 중요한 경기 날이면 공식 인스타그램을 통해 예약 방법을 안내하는 식으로 개별 대응을 하고 있습니다. 인기 스포츠 경기의 경우 자리 확보도 큰 문제입니다. 예를 들어 한 야구 테마 포차는 야구 시즌 경기 당일에는 예약 없이는 앉을 자리가 없을 정도로 만석이 되며, 서울의 유명 축구 펍 역시 중요 경기가 있는 날엔 예약하지 않으면 자리가 없을 정도로 인기가 많다고 알려져 있습니다. 이러한 불편함 때문에 팬들은 직접 여러 장소에 전화를 걸어 중계 여부를 묻거나, 인터넷 블로그・카페 글을 찾아 헤매야 하는 실정입니다.
</details>

---
## 타 서비스와의 차별점 
<p>현재 국내에는 스포츠 경기 관람에 특화된 예약 플랫폼이 전무한 상황입니다. 일반적인 맛집 앱이나 포털 예약 서비스는 식당의 분위기나 메뉴는 안내하지만, 어떤 경기를 중계하는지 정보 제공이 없고 사용자도 그런 조건으로 검색할 수 없습니다. 스포츠 팬들은 주로 지인들에게 물어보거나, 앞서 언급한 대로 여기저기 SNS와 커뮤니티를 뒤져가며 장소를 찾아야 합니다. </p>

<p>일부 해외에서는 DIRECTV의 스포츠 바 찾기나 FANZO와 같이 경기 중계 펍을 찾는 전문 앱이 존재하여 편의를 높이고 있습니다. 예를 들어 영국의 FANZO 앱은 “어디서 어떤 경기를 틀어줄까?” 하는 고민을 덜어주며, 보고 싶은 경기와 가까운 펍을 찾아주고 몇 초 만에 테이블 예약까지 가능하게 합니다. 이를 통해 이용자는 펍에 일일이 전화하지 않아도 되고, 어느 곳에서 원하는 경기를 확실히 틀어주는지 미리 알 수 있습니다. 결국 FANZO는“라이브 스포츠는 여럿이 함께할 때 더 즐겁다”*는 모토 아래 전 세계 250만 명 이상의 팬들이 이용하는 대표 플랫폼으로 성장했습니다. “거기어때?”는 이처럼 스포츠 관람에 특화된 원스톱 예약 서비스라는 점에서 기존 국내 서비스들과 명확히 구별됩니다.</p>

<p>또한 **경기별로 최적화된 검색과 필터** 기능을 제공합니다. 사용자는 종목이나 리그뿐 아니라 **특정 경기/팀** 단위로 검색해 **그 경기를 보여주는 장소만** 추려볼 수 있습니다. 원하는 경우 **응원 분위기, 좌석 형태, 주류 판매 여부** 등 세부 조건으로 필터링하여 선호에 맞는 장소를 찾을 수 있습니다.  이런 세분화된 검색은 일반 예약 앱에서는 불가능한, **스포츠 플랫폼만의 강점**입니다.  

마지막으로 **앱 내 예약 및 결제 연동**을 통해 편의성을 극대화했습니다. 전화 예약이나 현장 대기가 아닌 **온라인 사전예약**이므로 경기 시작에 맞춰 편하게 착석할 수 있고, **선결제 시스템**으로 노쇼(no-show) 방지와 원활한 정산도 가능합니다. 이처럼 ”거기어때?”는 **스포츠 팬들의 요구에 특화된 기능과 사용자 경험**으로 타 서비스와 차별화를 이루고 있습니다.</p>

---
## 서비스 대상 
<p>- 프로스포츠 팬층 (남녀노소 불문): 축구, 야구, 농구, 배구 등 국내외 프로스포츠 리그를 즐기는 모든 연령대의 팬들. 경기일마다 집 밖에서 응원 분위기를 느끼며보고 싶어하는 개인 또는 가족 단위의 팬들을 겨냥합니다. 특히 집에 큰 화면이나 케이블 채널이 없는 팬들도 대신 펍에서 단체응원의 재미를 느낄 수 있습니다.
</p>
<p>직장 동료 및 단체 관람 수요: 평소 친목 도모를 위해 스포츠 경기를 단체 관람하려는 직장인 모임, 동호회, 대학 동아리 등이 포함됩니다. 예를 들어 부서 회식으로 한국 축구 국가대표팀 경기를 함께 보고자 할 때, 플랫폼을 통해 장소 예약부터 좌석 확보까지 손쉽게 진행할 수 있습니다.
</p>
<p>특정 팀/종목 팬클럽: 특정 구단이나 선수의 팬클럽, 온라인 커뮤니티 회원들이 오프라인에서 함께 모여 응원하고자 할 때 유용합니다. 응원전용 공간을 찾아 단체 응원전을 펼치고 싶은 팬들에게, 저희 플랫폼은 원활한 장소 섭외 창구가 됩니다. 예를 들어 해외 축구팀 팬들이 새벽 경기 때 모일 술집을 찾는 경우 등입니다
</p>
<p>스포츠 관람에 관심 있는 일반 고객층: 큰 팬은 아니더라도 월드컵이나 올림픽 시즌에 분위기를 즐기고 싶은 일반인, 혹은 색다른 데이트/모임 코스로 스포츠 관람을 선택하는 고객들도 잠재 수요입니다. 거기어때는 이러한 라이트 유저들에게도 손쉽게 스포츠바 체험을 제공하여 새로운 여가 문화를 제안합니다.
</p>

---
# 기대효과

- **스포츠 팬들의 만족도 증대**: 가장 직접적인 효과는 팬들이 **응원 파트너를 쉽게 찾고 함께 즐길 기회**가 늘어난다는 점입니다. 이제까지는 혼자 TV로 보거나 장소 수소문하는데 어려움을 겪었다면, 저희 플랫폼으로 간편히 모임을 갖고 **열띤 응원 분위기**를 즐길 수 있습니다. 이는 **팬 경험의 질을 높여** 더욱 충성도 높은 팬으로 발전시키는 선순환을 낳습니다. 실제로 여럿이 함께 시청하는 팬들은 **경기에 몰입하고 오래 시청**하는 경향이 높아, 스포츠 소비 자체가 증진되는 효과도 기대됩니다. 특히 미리 약속을 잡고 모이는 ‘계획된 관람’의 경우 경기 시청을 위한 **음식과 음료 소비**도 증진시켜준 것으로 조사되었습니다. 이는 팬들에게는 즐거운 소비 경험이 되고, 스포츠 산업 전체로도 긍정적인 영향을 줍니다.
- **음식점/펍 매출 및 홍보 효과**: 플랫폼에 참여하는 업주들에게는 **매출 증대와 신규 고객 유입** 효과가 있습니다. 평소 스포츠 중계를 제공하던 매장은 저희 플랫폼을 통해 **팬층을 직접 타겟팅**하여 빈자리 없이 손님을 채울 수 있습니다. 특히 인기 경기 일정에 맞춘 **예약 선점**으로 **노쇼 감소**와 안정적인 운영이 가능해집니다. 또한 잘 알려지지 않은 장소도 앱에서 **스포츠 중계 장소로 노출**되면서 새로운 고객층을 확보할 수 있습니다. 플랫폼 사용자들이 **후기와 평점**을 남기면 입소문 이상의 홍보 효과를 기대할 수도 있습니다. 결국 플랫폼이 성장하면 참여 매장들은 **단골 커뮤니티 확보**와 **비수기 타개** 등의 부가 이익도 얻을 것입니다.
- **플랫폼 수익 모델 및 성장성**: “거기어때?”는 수익 측면에서도 **여러 경로의 비즈니스 모델**을 갖추고 있습니다. 우선 **예약 중개 수수료**를 통해 기본적인 수익을 올릴 수 있습니다 (예: 예약 좌석당 일정 금액 혹은 결제액의 일정 비율). 또한 **인기 시즌 광고** 수익이 기대되는데, 중요한 경기나 대회 시즌에 맞춰 관련 음식점들의 **배너 광고**나 프로모션 노출 서비스를 유료화할 수 있습니다. 이 밖에도 **스포츠 관련 업계와의 제휴**를 통한 부가 수익을 모색합니다. 주요 **주류 브랜드와 협업**하여 앱 이용자 대상 할인 쿠폰을 발행하거나, **굿즈/응원용품 판매**를 연계함으로써 수익을 다각화할 수 있습니다. 이러한 다양한 수익 채널은 플랫폼의 안정적 운영과 향후 투자 유치에도 긍정적으로 작용할 것입니다.
- **스포츠 문화 및 산업에의 기여**: 궁극적으로 “거기어때?” 의 활성화는 **국내 스포츠 문화의 저변 확대**로 이어질 수 있습니다. 팬들이 쉽게 모여 응원함으로써 종목을 불문하고 **직관 못지않은 응원 문화**가 형성되고, 이는 프로구단이나 리그의 인기도를 높여줄 가능성이 있습니다. 경기 관람을 하나의 **사회적 이벤트**로 자리매김시킴으로써 스포츠가 가지는 커뮤니티적 가치를 부각하고, 더 많은 잠재 팬층을 끌어들이는 효과도 기대됩니다. 나아가 이런 팬 활동 증가와 데이터 축적은 **스포츠 마케팅**이나 **스폰서십** 기회의 증대로도 연결될 수 있어, 스포츠 산업 전반에 활력을 불어넣는 역할을 할 것입니다.
---
## 주요 기능
<img width="1040" height="1087" alt="Image" src="https://github.com/user-attachments/assets/aace16b4-3f50-4d07-9014-76fee2f39003" />

---
## 📄 산출물 링크

- **기획서(PDF)**

- **요구사항정의서·WBS (Google Sheets)**  
  https://docs.google.com/spreadsheets/d/1fdSv-AS8EdeEeRGrgxjWvRoyUO11oDH1wEkOj6HnHXg/edit?gid=1436566125#gid=1436566125

  <img width="1111" height="678" alt="Image" src="https://github.com/user-attachments/assets/be53e680-9206-4e3a-bafd-1558fdd540c5" />

<img width="989" height="711" alt="Image" src="https://github.com/user-attachments/assets/5acff6e7-f87a-4305-ad2f-2e88a889d85e" />

<img width="920" height="450" alt="Image" src="https://github.com/user-attachments/assets/67466620-72a5-4f3e-b72b-f42b71625b96" />


- **ERD (ERDCloud)**  
  [https://www.erdcloud.com/d/BheLqjBhttiyWkdZj](https://www.erdcloud.com/d/A9A6aT89xQCdqGfiH)
  ![프로젝트 배경 1.PNG](./images/erd.png)

  <img width="1516" height="795" alt="Image" src="https://github.com/user-attachments/assets/10ae4370-2ba3-4a85-b5fa-124cf6313740" />

- **DDD (miro)**  
  https://miro.com/app/board/uXjVJ5lwdoc=/
-  ![스웨거.PNG](./images/swagger.png)

<img width="1155" height="722" alt="Image" src="https://github.com/user-attachments/assets/c5bd1de0-6ce3-4bde-b88b-af62d4923f0b" />

<img width="543" height="586" alt="Image" src="https://github.com/user-attachments/assets/13aefb07-4716-4f69-81f1-219813191f18" />
  ---

  ## 📄 API 명세서 & 단위 테스트

  
- **POST MAN**
https://documenter.getpostman.com/view/48691546/2sB3QQH7M6

- **Swagger**
  ![스웨거.PNG](./images/swagger.png)






 ## 📄**테스트 케이스 상세**

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
 <summary></summary>
 
 
 </details>
 <details>
 <summary></summary>
 
 
 </details>

<details>
 <summary></summary>
 
 
 </details>

<details>
 <summary></summary>
 
 
 </details>

<details>
 <summary></summary>
 
 
 </details>

<details>
 <summary></summary>
 
 
 </details>
<details>
 <summary></summary>
 
 
 </details>
<details>
 <summary></summary>
 
 
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
 
