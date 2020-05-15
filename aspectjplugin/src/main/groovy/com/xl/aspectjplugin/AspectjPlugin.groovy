package com.xl.aspectjplugin;

import org.gradle.api.Plugin
import org.gradle.api.Project

public class AspectjPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
         System.out.println("AspectjPlugin")
    }
}
