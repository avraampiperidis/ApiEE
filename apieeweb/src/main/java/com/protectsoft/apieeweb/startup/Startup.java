/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.startup;

import com.protectsoft.apiee.util.BaseConfig;
import javax.ws.rs.ApplicationPath;

/**
 *
 * @author piper 
 */
@ApplicationPath("api")
public class Startup extends BaseConfig {
   
    public Startup() {
        packages("com.protectsoft.apieeweb.endpoint;");       
    }
    
}
