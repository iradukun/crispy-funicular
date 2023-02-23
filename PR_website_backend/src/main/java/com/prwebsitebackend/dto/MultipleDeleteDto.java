package com.prwebsitebackend.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MultipleDeleteDto {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }
}
