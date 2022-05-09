package com.hotel.service.impl;

import com.hotel.dao.api.RoomDao;
import com.hotel.dao.model.Room;
import com.hotel.dao.model.enums.RoomStatus;
import com.hotel.dao.model.enums.SortedKey;
import com.hotel.service.exceptions.ResourceIsUsedException;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.api.mapper.RoomMapper;
import com.hotel.service.api.service.RoomService;
import com.hotel.service.dto.RoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;
    private final RoomMapper mapper;

    @Transactional
    @Override
    public RoomDto create(RoomDto dto) {
        log.info("Я - МЕТОД СОЗДАНИЯ В СЕРВИСЕ КОМНАТЫ!!!");
        Room entity = mapper.toEntity(dto);

        if(!roomDao.existsByRoomNumber(entity.getNumber()))
        {
           throw new ResourceIsUsedException("Комната", "номером", entity.getNumber());
        }

        entity.setStatus(RoomStatus.FREE);
        return mapper.toDto(roomDao.save(entity));
    }

    @Transactional
    @Override
    public Collection<RoomDto> findAll() {
        log.info("Я - МЕТОД ВЫВОДА ВСЕГО В СЕРВИСЕ КОМНАТЫ!!!");
        return roomDao.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public RoomDto findById(UUID id) {
        log.info("Я - МЕТОД ВЫВОДА ОДНОГО В СЕРВИСЕ КОМНАТЫ!!!");
        return roomDao.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "ID", id));
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        log.info("Я - МЕТОД УДАЛЕНИЯ ОДНОГО В СЕРВИСЕ КОМНАТЫ!!!");
        roomDao.delete(id);
    }

    @Transactional
    @Override
    public void update(RoomDto dto) {
        log.info("Я - МЕТОД ИЗМЕНЕНИЯ ОДНОГО В СЕРВИСЕ КОМНАТЫ!!!");
        Room entity = mapper.toEntity(dto);
        roomDao.save(entity);
    }

    @Transactional
    @Override
    public void changeStatus(UUID id) {
        log.info("Я - МЕТОД ИЗМЕНЕНИЯ СТАТУСА ОДНОГО В СЕРВИСЕ КОМНАТЫ!!!");
        Room entity = roomDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "ID", id));
        roomDao.changeStatus(entity);
    }

    @Transactional
    @Override
    public Collection<RoomDto> sortedByKey(SortedKey key) {
        log.info("Я - МЕТОД ВЫВОДА ВСЕГО ОТСОРТИРОВАННОГО В СЕРВИСЕ КОМНАТЫ!!!");
        return roomDao.findAll()
                .stream()
                .sorted(getComparator(key))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Comparator<Room> getComparator(SortedKey key){
        Comparator<Room> comparator = null;
        switch (key){
            case PRICE:
                comparator = Comparator.comparing(Room::getPrice);
                break;
            case NUMBER:
                comparator = Comparator.comparing(Room::getNumber);
                break;
            case STATUS:
                comparator = Comparator.comparing(Room::getStatus);
                break;
        }
        return comparator;
    }
}
