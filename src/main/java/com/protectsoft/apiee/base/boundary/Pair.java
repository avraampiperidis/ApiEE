package com.protectsoft.apiee.base.boundary;

import com.protectsoft.apiee.core.masterdetail.MasterDetail;


/**
 *
 * @author Abraham Piperidis
 * @param <L>
 * @param <R>
 */
public class Pair<L extends MasterDetail,R extends Api<?>> {
    
    private L masterDetailHolder;
    private R api;
    
    
    public Pair(L l,R r) {
        this.masterDetailHolder = l;
        this.api = r;
    }

    /**
     * @return the masterDetailHolder
     */
    public L getMasterDetailHolder() {
        return masterDetailHolder;
    }

    /**
     * @param masterDetailHolder the masterDetailHolder to set
     */
    public void setMasterDetailHolder(L masterDetailHolder) {
        this.masterDetailHolder = masterDetailHolder;
    }

    /**
     * @return the api
     */
    public R getApi() {
        return api;
    }

    /**
     * @param api the api to set
     */
    public void setApi(R api) {
        this.api = api;
    }
}
