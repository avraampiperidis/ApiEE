/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.startup;

import com.protectsoft.apiee.AAA.IApiResource;
import com.protectsoft.apieeweb.endpoint.DepartmentResource;
import com.protectsoft.apieeweb.entity.Department;

/**
 *
 * @author piperidis.a
 */
public class Main {
    public static void main(String[] s) {
        System.out.println("MAIN");
        DepartmentResource d = new DepartmentResource();
        new IApiResource<Department>(){
            @Override
            public void meth() {
                System.out.println("ANONYMOUS CLASS ,->METH");
            }
        }.meth();
    }
}
