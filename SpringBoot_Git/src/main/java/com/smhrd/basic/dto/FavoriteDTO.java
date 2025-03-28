package com.smhrd.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavoriteDTO {
    private Integer favoriteIdx;
    private String userEmail;
    private Integer bidx;
}