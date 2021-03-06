package com.xl.aspectjplugin

import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project

public class AspectjPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.dependencies {
            implementation 'org.aspectj:aspectjrt:1.8.9'
        }

        project.android.applicationVariants.all { variant ->
            def javaCompile = variant.javaCompile

            final def log = project.logger
            log.error "========================";
            log.error "Aspectj切片开始编织Class!";
            log.error "========================";

            //在javaCompile Task之后添加
            javaCompile.doLast {
                String[] args = ["-showWeaveInfo",
                                 "-1.7",//对应插件module声明的Java版本
                                 "-inpath", javaCompile.destinationDir.toString(),
                                 "-aspectpath", javaCompile.classpath.asPath,
                                 "-d", javaCompile.destinationDir.toString(),
                                 "-classpath", javaCompile.classpath.asPath,
                                 "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]

                MessageHandler handler = new MessageHandler(true);
                new Main().run(args, handler)
                for (IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            log.error message.message, message.thrown
                            break
                        case IMessage.WARNING:
                            log.warn message.message, message.thrown
                            break
                        case IMessage.INFO:
                            log.info message.message, message.thrown
                            break
                        case IMessage.DEBUG:
                            log.debug message.message, message.thrown
                            break
                    }
                }
            }
        }
    }
}
