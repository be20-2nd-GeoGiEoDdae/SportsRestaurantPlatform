package com.geogieoddae.mainservice.restaurant.command.entity.keyword;

import com.geogieoddae.mainservice.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "keyword_category", nullable = false)
    private KeywordCategory keywordCategory;

    @Column(name = "keyword")
    private String keyword;

    @OneToMany(mappedBy = "keyword")
    private List<RestaurantKeywordEntity> restaurantKeywords;
}
