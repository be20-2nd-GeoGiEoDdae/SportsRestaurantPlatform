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
public class SportsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_code")
    private Long sportCode;

    @Column(nullable = false)
    private String sportName;

    public void update(String name) {
        this.sportName = name;
    }
}