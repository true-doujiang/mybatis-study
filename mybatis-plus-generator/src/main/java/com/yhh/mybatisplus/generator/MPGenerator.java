package com.yhh.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MPGenerator {


    public static String OUTPUT_DIR = "D:/Work/git/mybatis-study/mybatis-plus-generator/src/main/java";
    public static String AUTHOR = "-小野猪-";
    public static String TABLE_PREFIX = "";
    public static String PARENT = "cn";
    public static String MODULE_NAME = "";


    //customer_sales_ref

    public static String[] TABLE_NAMES = new String[]{"customer_account"};

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUTPUT_DIR);


        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setKotlin(false); //是否生成 kotlin 代码
        gc.setAuthor(AUTHOR);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setServiceName("MP%sService");
        gc.setServiceName("%sService");

//         gc.setServiceImplName("%sServiceImpl");
//         gc.setControllerName("%Controller");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setOpen(true);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                //System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://47.107.149.155:3306/sms?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8&useSSL=false");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix(TABLE_PREFIX);


        strategy.setInclude(TABLE_NAMES); // 需要生成的表

        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        //strategy.setSuperEntityClass("com.weimob.saas.dto.BaseDataDto");

        // 自定义实体，公共字段
        //strategy.setSuperEntityColumns(new String[]{"id", "create_user", "update_user", "create_time", "update_time", "version", "is_del"});
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 facade 父类
        strategy.setSuperServiceClass(null);
        // 自定义 facade 实现类父类
        //strategy.setSuperServiceImplClass("cn.dianda.marketing.campaign.service.BaseServiceImpl");
        // 自定义 controller 父类
        //strategy.setSuperControllerClass("cn.dianda.marketing.campaign.controller.BaseController");

        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        strategy.setEntityBuilderModel(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT);
        pc.setModuleName(MODULE_NAME);
        pc.setEntity("entity");
        pc.setService("remote");
        mpg.setPackageInfo(pc);


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        mpg.setCfg(cfg);

        /*// 调整 xmlDto 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xmlDto.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "D:\\WorkSpaces\\saas-crm\\saas-crm-generator\\src\\main\\resources\\xmlDto\\process\\" + tableInfo.getEntityName() + "Mapper.xmlDto";
            }
        });*/

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 xmlDto 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();

        mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

    }

}