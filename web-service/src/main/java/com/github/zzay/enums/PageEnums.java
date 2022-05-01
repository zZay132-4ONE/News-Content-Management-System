package com.github.zzay.enums;

/**
 * @author zzay
 * @className PageEnums
 * @description Enums about pages
 * @create 2022/05/01 13:15
 */
public enum PageEnums {

    /**
     * Record count of each page
     */
    RECORDS_PER_PAGE(5);

    // Integer value
    private final Integer value;

    // Integer constructor
    PageEnums(Integer value) {
        this.value = value;
    }

    // Integer getter
    public Integer getValue() {
        return this.value;
    }

}
