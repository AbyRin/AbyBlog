package com.cpr.abyblog.Utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

// Mybatis-plus 代码生成器（新）
public class CodeGenerator {

    public static void main(String[] args) {
        generator();
    }

    private static void generator() {
//        FastAutoGenerator.create("jdbc:mysql://localhost:3306/cpr_blog", "root", "123456")
//                .globalConfig(builder -> {
//                    builder.author("AbyRin") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
////                            .fileOverride() // 覆盖已生成文件，已弃用，迁移至 strategyConfig
//                            .outputDir("D:\\IDEAWorkSpace\\AbyBlog\\AbyBlog-app\\src\\main\\java"); // 指定输出目录
//                })
//                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
//                    return typeRegistry.getColumnType(metaInfo);
//
//                }))
//                .packageConfig(builder -> {
//                    builder.parent("com.cpr.abyblog") // 设置父包名
//                            .moduleName("AbyBlog") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\IDEAWorkSpace\\AbyBlog\\AbyBlog-app\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("consignee")
//                            .addTablePrefix() // 设置过滤表前缀
//                            // 覆盖已生成文件（新）
//                            .entityBuilder().enableFileOverride()
//                            .serviceBuilder().enableFileOverride()
//                            .mapperBuilder().enableFileOverride()
//                            .controllerBuilder().enableFileOverride()
//                    ;
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();


        FastAutoGenerator
            // 配置数据源
            .create("jdbc:mysql://localhost:3306/cpr_blog?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true", "root", "123456")
            // 全局配置
            .globalConfig(builder -> {
                builder.author("Aby") // 设置作者
                    .commentDate("yyyy-MM-dd hh:mm:ss")   // 注释日期
                    .outputDir("D:\\IDEAWorkSpace\\AbyBlog\\AbyBlog-app\\src\\main\\java") // 指定输出目录
                    .disableOpenDir() // 禁止打开输出目录，默认打开
                ;
            })
            // 包配置
            .packageConfig(builder -> {
                builder.parent("com.cpr.abyblog") // 设置父包名
                    .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\IDEAWorkSpace\\AbyBlog\\AbyBlog-app\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
            })
            // 策略配置
            .strategyConfig(builder -> {
                builder.addInclude("inspiration") // 设置需要生成的表名
                    .addTablePrefix() // 设置过滤表前缀

                    // Entity 策略配置
                    .entityBuilder()
                    .enableLombok() //开启 Lombok
                    .enableFileOverride() // 覆盖已生成文件
                    .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                    .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命

                    // Mapper 策略配置
                    .mapperBuilder()
                    .enableFileOverride() // 覆盖已生成文件

                    // Service 策略配置
                    .serviceBuilder()
                    .enableFileOverride() // 覆盖已生成文件
                    .formatServiceFileName("%sService") // 格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                    .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                    // Controller 策略配置
                    .controllerBuilder()
                    .enableFileOverride() // 覆盖已生成文件
                ;
            })
            .execute();
    }
}
