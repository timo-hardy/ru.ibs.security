package ru.ibs.tkv.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class Task {
    private final Long id;
    private final String name;
    private final String description;
}
