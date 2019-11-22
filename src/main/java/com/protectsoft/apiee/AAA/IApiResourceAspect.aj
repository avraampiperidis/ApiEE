package com.protectsoft.apiee.AAA;

public aspect IApiResourceAspect {

    pointcut callIApiResource(com.protectsoft.apiee.AAA.IApiResource rs)
        :call(void com.protectsoft.apiee.AAA.IApiResource+.meth()) && target(rs);

    before(com.protectsoft.apiee.AAA.IApiResource rs) : callIApiResource(rs) {
        System.out.println("beforeMeth");
    }
    
    void around(com.protectsoft.apiee.AAA.IApiResource rs):callIApiResource(rs){
        System.out.println("AroundMeth"+rs.getClass());
        proceed(rs);
    }

    after(com.protectsoft.apiee.AAA.IApiResource rs):callIApiResource(rs){
        System.out.println("afterMeth");
    }


    after(com.protectsoft.apiee.AAA.IApiResource rs): execution(com.protectsoft.apiee.AAA.IApiResource+.new()) && this(rs) {
        System.out.println("After:"+rs.getClass());
    }

}