package com.ohgiraffers.geogieoddae.sports.command.entity;

import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_code")
    private Long teamCode;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_description")
    private String teamDescription;

    @ManyToOne
    @JoinColumn(name = "sport_code", nullable = false)
    private SportEntity sport;

    @OneToMany(mappedBy = "team")
    private List<ViewingEntity> viewings;
}
