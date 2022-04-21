package com.lcl.pname;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lcl.pname.entity.BaseEntity;

import java.util.Collections;

public class MPTest {
    public static void main(String[] args) {
        test01();
    }
    public static void test01(){
        // ����Ҫ�Զ����ɵı���
        //List<String> tables = new ArrayList<>();
        //Collections.addAll(tables,"goods","t_integral","t_order","u_account","user");
        // ���ݿ����Ӳ���
        String url = "jdbc:mysql:///edu?serverTimezone=GMT%2B8&amp&useSSL=false&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";

        // ��������Դ
        final DataSourceConfig.Builder dataSourceConfig =
                new DataSourceConfig.Builder(url, "root", "root")
                        // �������ݿ�����ת����
                        .typeConvert(new MySqlTypeConvertCustom());

        // ��ʼ�����Զ����ɲ���
        FastAutoGenerator
                .create(dataSourceConfig) // ������Դ����
                // ȫ������
                .globalConfig(builder -> {
                    builder.author("lcl")  //����
                            //���·��(д��javaĿ¼)
                            .outputDir(System.getProperty("user.dir")+"\\common\\src\\main\\java")
                            //.enableSwagger()  //����swagger
                            .disableOpenDir() // ��ֹ�����Ŀ¼
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();  //��������֮ǰ���ɵ��ļ�
                })
                // ������
                .packageConfig(builder -> {
                    builder.parent("com.lcl.pname")  // ����Ŀ¼
                            //.moduleName("practice") // ģ�����
                            .entity("entity") // ʵ�����
                            .service("service") // ��������
                            .serviceImpl("service.impl") // �����ʵ�ְ���
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            // ����mapper.xml���ļ����λ��
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    System.getProperty("user.dir")
                                            +"\\common\\src\\main\\resources\\com\\lcl\\pname\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude() //����Ҫ�Զ�������Щ������ʹ����list�ȴ�����
                            // ����Ҫ���˵ı�ǰ׺���������ú���Զ�ȥ��ָ����ǰ׺���������ö��
                            .addTablePrefix("t_","acl_","crm_","edu_")
                            // ���÷�������
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // ���÷����ӿڵĺ�׺��   %sΪռλ��
                            .formatServiceImplFileName("%sServiceImpl") // ����ʵ�����׺
                            // ����ʵ����
                            .entityBuilder()
                            .superClass(BaseEntity.class) // ���ø���
                            // ��дBaseEntity�еĹ����ֶ�
                            .addSuperEntityColumns("id", "gmt_create", "gmt_modified")
                            .enableLombok()  // ����lombokע��
                            .logicDeleteColumnName("is_deleted") // �����߼�ɾ���ֶ���
                            .enableRemoveIsPrefix()    // ����boolean�����Ƴ�ǰ׺
                            .idType(IdType.ASSIGN_ID)  // ��������idΪѩ���㷨
                            .enableTableFieldAnnotation() // �Զ������commentע����ӵ�ʵ����������
                            // ���ÿ�������
                            .controllerBuilder()
                            .formatFileName("%sController") // �����ƺ�׺
                            .enableRestStyle() // ����restFul���ע��
                            .enableHyphenStyle()  // url���շ�ת���ַ�
                            // mapper����
                            .mapperBuilder()
                            .enableBaseResultMap()  //����ͨ�õ�resultMap
                            .superClass(BaseMapper.class) // ���ø���
                            .formatMapperFileName("%sMapper") // �����ƺ�׺
                            .enableMapperAnnotation() // ����ע������
                            .formatXmlFileName("%sMapper"); // mapper.xml�ļ����ƣ�������Զ����.xml��׺
                })
                // ʹ��Freemarker����ģ�壬Ĭ�ϵ���Velocity����ģ��
                // ��������Զ���ģ��
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
