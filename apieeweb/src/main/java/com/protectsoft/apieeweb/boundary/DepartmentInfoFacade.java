/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.boundary;

import com.protectsoft.apiee.base.core.Api;
import com.protectsoft.apieeweb.entity.DepartmentInfo;
import javax.ejb.Stateless;

/**
 *
 * @author Abraham Piperidis
 */
@Stateless
public class DepartmentInfoFacade extends Api<DepartmentInfo> {
    
    public DepartmentInfoFacade() {
        super(DepartmentInfo.class);
    }
    
}
