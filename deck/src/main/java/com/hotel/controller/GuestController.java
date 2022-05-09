package com.hotel.controller;

import com.hotel.dao.model.enums.SortedKey;
import com.hotel.service.api.service.GuestService;
import com.hotel.service.dto.GuestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestDto> create(@Valid
                                           @RequestBody GuestDto guestDto) {
        log.info("Я - МЕТОД СОЗДАНИЯ В КОНТРОЛЛЕРЕ ГОСТЯ!!!");
        return ResponseEntity.ok(guestService.create(guestDto));
    }

    @GetMapping
    public ResponseEntity<Collection<GuestDto>> findAll() {
        log.info("Я - МЕТОД ВЫВОДА ВСЕГО В КОНТРОЛЛЕРЕ ГОСТЯ!!!");
        return ResponseEntity.ok(guestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> findById(@PathVariable UUID id) {
        log.info("Я - МЕТОД ВЫВОДА ОДНОГО В КОНТРОЛЛЕРЕ ГОСТЯ!!!");
        return ResponseEntity.ok(guestService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        log.info("Я - МЕТОД УДАЛЕНИЯ ОДНОГО В КОНТРОЛЛЕРЕ ГОСТЯ!!!");
        guestService.delete(id);
    }

    @PatchMapping
    public void update(@RequestBody GuestDto guestDto) {
        log.info("Я - МЕТОД ИЗМЕНЕНИЯ ОДНОГО В КОНТРОЛЛЕРЕ ГОСТЯ!!!");
        guestService.update(guestDto);
    }

    @GetMapping("/sort/{key}")
    public ResponseEntity<Collection<GuestDto>> getSortedByKey(@PathVariable SortedKey key) {
        log.info("Я - МЕТОД ВЫВОДА ВСЕГО ОТСОРТИРОВАННОГО В КОНТРОЛЛЕРЕ ГОСТЯ!!!");
        return ResponseEntity.ok(guestService.sortedByKey(key));
    }
}
