
package com.protectsoft.apiee.util;

import java.util.List;

/**
 *
 * @param <T>
 */
public class PagedList<T> {
    
    private List<T> filteredList;
    private Long originalSize;

    public PagedList(List<T> list, Long count) {
        this.filteredList = list;
        this.originalSize = count;
    }
    
    public List<T> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(List<T> list) {
        this.filteredList = list;
    }

    public Long getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(Long count) {
        this.originalSize = count;
    }
    
}
