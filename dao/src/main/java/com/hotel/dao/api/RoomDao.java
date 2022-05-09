package com.hotel.dao.api;

import com.hotel.dao.model.Room;

public interface RoomDao extends BaseDao<Room> {
    Room changeStatus(Room room);

    Room autoChangeStatus(Room room);

    Boolean existsByRoomNumber(String number);
}
