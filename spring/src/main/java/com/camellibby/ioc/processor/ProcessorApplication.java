package com.camellibby.ioc.processor;

import com.camellibby.ioc.processor.bean.Order;
import com.camellibby.ioc.processor.bean.Product;
import com.camellibby.ioc.processor.handler.CustomImportBeanDefinitionRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(CustomImportBeanDefinitionRegistrar.class)
@ComponentScan(basePackages = "com.camellibby.ioc.processor")
public class ProcessorApplication {
    public static void main(String[] args) {
        ApplicationContext ctx  = new AnnotationConfigApplicationContext(ProcessorApplication.class);
        Product product = ctx.getBean(Product.class);
        Order order = ctx.getBean(Order.class);
        product.display();
        order.display();
    }
}
