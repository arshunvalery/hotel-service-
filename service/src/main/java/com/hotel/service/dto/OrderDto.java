package com.hotel.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private LocalDate dateSettlement;
    private LocalDate dateFree;
    @NotNull
    private GuestDto guest;
    @NotNull
    private RoomDto room;
    @NotNull
    private List<FacilityDto> facilities;
}
