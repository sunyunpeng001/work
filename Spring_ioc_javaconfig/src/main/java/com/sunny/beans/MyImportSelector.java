package com.sunny.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

@ComponentScan
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //可以注册多个 Bean
        return new String[]{"com.sunny.beans.Person",Wife.class.getName()};
    }
}
