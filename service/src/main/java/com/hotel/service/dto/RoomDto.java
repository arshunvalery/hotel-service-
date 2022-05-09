package com.hotel.service.dto;

import com.hotel.dao.model.enums.RoomStatus;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class RoomDto {
    private UUID id;
    @Size(max = 4)
    @Pattern(regexp = "^[1-9][0-9]*")
    private String number;
    private RoomStatus status;
    private int price;
}
