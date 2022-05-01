package com.github.zzay.enums;

/**
 * @author zzay
 * @className NewsEnums
 * @description Enums about news
 * @create 2022/05/01 13:24
 */
public enum NewsEnums {

    /**
     * News is published
     */
    PUBLISHED(1),

    /**
     * News is unpublished
     */
    UNPUBLISHED(0);

    // Integer value
    private final Integer value;

    // Integer constructor
    NewsEnums(Integer value) {
        this.value = value;
    }

    // Integer getter
    public Integer getValue() {
        return this.value;
    }

}
