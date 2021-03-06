package com.hotel.service.api.service;

import com.hotel.dao.model.Room;
import com.hotel.dao.model.enums.SortedKey;
import com.hotel.service.dto.RoomDto;

import java.util.Collection;
import java.util.Comparator;
import java.util.UUID;

public interface RoomService {
    RoomDto create(RoomDto entity);

    Collection<RoomDto> findAll();

    RoomDto findById(UUID id);

    void delete(UUID id);

    void update(RoomDto entity);

    void changeStatus(UUID id);

    Collection<RoomDto> sortedByKey(SortedKey key);

    Comparator<Room> getComparator(SortedKey key);
}
