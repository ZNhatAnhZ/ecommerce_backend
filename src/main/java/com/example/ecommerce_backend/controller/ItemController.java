package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityIndexDto;
import com.example.ecommerce_backend.dto.ItemEntity.ItemEntityUpdateDto;
import com.example.ecommerce_backend.service.interfaces.ItemServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/items")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemServiceInterface itemServiceInterface;
    @PutMapping("/{id}")
    public ResponseEntity<ItemEntityIndexDto> update(@PathVariable("id") int id, @RequestBody ItemEntityUpdateDto itemEntityUpdateDto) {
        itemEntityUpdateDto.setId(id);
        return ResponseEntity.ok(itemServiceInterface.updateItemEntity(itemEntityUpdateDto));
    }

    @PostMapping("/batchUpdate")
    public ResponseEntity<List<ItemEntityIndexDto>> batchUpdate(@RequestBody List<ItemEntityUpdateDto> itemEntityUpdateDtoList) {
        return ResponseEntity.ok(itemServiceInterface.batchUpdateItemEntity(itemEntityUpdateDtoList));
    }
}
