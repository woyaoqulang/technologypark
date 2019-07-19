package com.clare.util.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 自定义插件
 *
 * @author CZH
 */
public class GenPlugin extends PluginAdapter {
    private Set<String> mappers = new HashSet<String>();

    /**
     * 注释生成器
     */
    private CommentGeneratorConfiguration commentCfg;

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        // 设置默认的注释生成器
        commentCfg = new CommentGeneratorConfiguration();
        commentCfg.setConfigurationType(GenCommentGenerator.class.getCanonicalName());
        context.setCommentGeneratorConfiguration(commentCfg);
        // 支持oracle获取注释#114
        context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String mappers = this.properties.getProperty("mappers");
        for (String mapper : mappers.split(",")) {
            this.mappers.add(mapper);
        }
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        //添加对象的import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("javax.persistence.Table");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");

        //添加对象注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Table");

        //添加对象的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * ");
        topLevelClass.addJavaDocLine(" * @author : zhangHao");
        topLevelClass.addJavaDocLine(" * @data : " + getDate());
        topLevelClass.addJavaDocLine("*/");

        return true;
    }


    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }


    /**
     * 生成的Mapper接口
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 获取实体类
        FullyQualifiedJavaType entityType = new
                FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import接口
        for (String mapper : mappers) {
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" +
                    entityType.getShortName() + ">"));
        }
        // import实体类
        interfaze.addImportedType(entityType);

        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
        interfaze.addImportedType(javaType);

        //注解
        interfaze.addAnnotation("@Repository");
        //注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * ");
        interfaze.addJavaDocLine(" * @author : zhangHao");
        interfaze.addJavaDocLine(" * @data : " + getDate());
        interfaze.addJavaDocLine("*/");

        return true;
    }

    /**
     * 拼装SQL语句生成Mapper接口映射文件
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        return true;
    }


    // 以下设置为false,取消生成默认增删查改xml
    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String result = format.format(new Date());
        return result;
    }

}
