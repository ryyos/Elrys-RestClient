package com.elrys.elrysrestclient.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Index {
    MAIN("elrys"),
    LOG("elrys_log");

    private final String index;
}
