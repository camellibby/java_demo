package com.camellibby.cycle;

import com.camellibby.cycle.bean.A;
import com.camellibby.cycle.bean.B;
import com.camellibby.cycle.bean.C;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@EnableAspectJAutoProxy
public class CycleApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CycleApplication.class);
        A a = ctx.getBean(A.class);
        B b = ctx.getBean(B.class);
        C c = ctx.getBean(C.class);
        System.out.println(a);
        System.out.println(a.b);
        System.out.println(a.c);
        System.out.println(a.getB());
        System.out.println(a.getC());
        System.out.println(b);
        System.out.println(b.getA());
        System.out.println(b.getC());
        System.out.println(c);
        System.out.println(c.getA());
        System.out.println(c.getB());
        a.hello("Jeffrey");
        b.hello("Jeffrey");
    }
}
