
package com.protectsoft.apiee.util;

import java.util.List;

/**
 *
 * @param <T>
 */
public class CountedList<T> {
    
    private List<T> list;
    private Long count;

    public CountedList(List<T> list, Long count) {
        this.list = list;
        this.count = count;
    }
    
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
}
