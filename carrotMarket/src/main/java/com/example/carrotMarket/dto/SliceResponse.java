package com.example.carrotMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SliceResponse<T> {
    private final List<T> posts;
    private final boolean hasNext;

}
