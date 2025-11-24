package com.ohgiraffers.geogieoddae.sports.command.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "sport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_code")
    private Long sportCode;

    @Column(name = "sport_name", nullable = false)
    private String sportName;

    @Column(name = "sport_decription")
    private String sportDescription;

    @OneToMany(mappedBy = "sport")
    private List<TeamEntity> teams;
}
