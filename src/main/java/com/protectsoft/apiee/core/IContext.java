package com.protectsoft.apiee.core;

import com.protectsoft.apiee.entities.BaseEntity;
import com.protectsoft.apiee.masterdetail.MasterDetail;
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
}
