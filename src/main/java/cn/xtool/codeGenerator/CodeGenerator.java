package cn.xtool.codeGenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Lists;

import java.util.Collections;

/**
 * @author zhaogaohui
 * @date 2021/3/25 9:04 上午
 **/
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://192.168.140.128:3306/saas_db?useSSL=false",
                "root",
                "123456")
                .globalConfig(builder -> {
                    builder.author("xwxu") // 设置作者
                            .outputDir(userDir + "/src/main/java") // 指定输出目录
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("cn.xtool") // 设置父包名
                            .entity("model.entity")
                            .mapper("dao")
                            .service("service")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, userDir + "/src/main/resources/sqlmap")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(Lists.newArrayList("tenant_info")); // 设置需要生成的表名
                    builder.serviceBuilder().formatServiceFileName("%sService"); // 设置生成的service接口文件名称的规则
                    builder.entityBuilder().enableLombok();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //是否禁止生成控制器等
                //.templateConfig(builder -> {
                //    builder.disable(TemplateType.CONTROLLER)
                //            .disable(TemplateType.SERVICE)
                //            .disable(TemplateType.SERVICE_IMPL)
				//			.disable(TemplateType.MAPPER)
				//			.disable(TemplateType.XML)
                //    ;
                //})
                .execute();
    }

}