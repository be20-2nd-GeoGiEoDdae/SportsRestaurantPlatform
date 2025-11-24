package com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword;

import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.restaurant.entity.RestaurantKeywordEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Keyword")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeywordEntity extends BaseTimeEntity {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_code")
    private Long keywordCode;

    @Column(name = "keyword_category")
    private String keywordCategory;

    @Column(name = "keyword")
    private String keyword;

    @OneToMany(mappedBy = "keyword")
    private List<RestaurantKeywordEntity> restaurantKeywords;
}
