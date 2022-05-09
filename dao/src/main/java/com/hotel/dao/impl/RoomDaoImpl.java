package com.hotel.dao.impl;

import com.hotel.dao.api.RoomDao;
import com.hotel.dao.model.Guest;
import com.hotel.dao.model.Order;
import com.hotel.dao.model.Room;
import com.hotel.dao.model.Room_;
import com.hotel.dao.model.enums.RoomStatus;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    public RoomDaoImpl() {
        super(Room.class);
    }

    @Override
    public Room changeStatus(Room room) {
        switch (room.getStatus()) {
            case FREE: {
                room.setStatus(RoomStatus.REPAIRED);
                break;
            }

            case REPAIRED: {
                room.setStatus(RoomStatus.FREE);
                break;
            }
            default:
                break;
        }
        return room;
    }

    @Override
    public Room autoChangeStatus(Room room) {
        switch (room.getStatus()) {
            case FREE: {
                room.setStatus(RoomStatus.BUSY);
                break;
            }

            case BUSY: {
                room.setStatus(RoomStatus.FREE);
                break;
            }
            default:
                break;
        }
        return room;
    }

    @Override
    public Boolean existsByRoomNumber(String number) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = builder.createQuery(Room.class);
        Root<Room> roomRoot = query.from(Room.class);

        query.select(roomRoot);
        query.where(builder.equal(roomRoot.get(Room_.number), number));

        return entityManager.createQuery(query).getResultList().isEmpty();
    }
}