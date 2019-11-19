/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.boundary;

import com.protectsoft.apiee.core.Api;
import com.protectsoft.apieeweb.entity.Employee;
import javax.ejb.Stateless;

/**
 *
 * @author piper
 */
@Stateless
public class EmployeeFacade extends Api<Employee> {
    
    public EmployeeFacade() {
        super(Employee.class);
    }
    
}
