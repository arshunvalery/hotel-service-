package com.hotel.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class FacilityDto {
    private UUID id;
    @NotBlank
    @Pattern(regexp = "^([А-Я][а-я]*|[A-Z][a-z]*)")
    @Size(max = 10)
    private String title;
    @NotBlank
    private int price;

}
