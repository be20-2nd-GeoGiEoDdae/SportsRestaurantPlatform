package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PictureDto {
    @NotBlank
    private String pictureUrl;
}
