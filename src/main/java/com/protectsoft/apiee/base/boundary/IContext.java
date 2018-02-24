package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.core.masterdetail.MasterDetail;
import java.util.List;

/**
 *
 * @author Abraham Piperidis
 * @param <T>
 */
interface IContext<T extends BaseEntity> {
    
    void setParent(Api<T> parent);
    Api<T> getParent();
    Api<T> getService();
    List<Pair<MasterDetail,Api<T>>> getChilds();
    void addChild(Api<T> child);
}
