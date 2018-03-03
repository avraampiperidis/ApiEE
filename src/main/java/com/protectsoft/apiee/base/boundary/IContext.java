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
    Api<T> getParent();
    Api<T> getService();
    List<Pair<MasterDetail,Api<? extends BaseEntity>>> getChildDetails();
    List<Api<? extends BaseEntity>> getChilds();
    <D extends BaseEntity> void addChild(Api<D> child);
}
