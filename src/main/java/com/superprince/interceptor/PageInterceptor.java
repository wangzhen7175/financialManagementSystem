package com.superprince.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

//拦截StatementHandler接口中的参数为Connection的prepare方法
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PageInterceptor implements Interceptor
{
  private String databaseType;//数据库类型  
  private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
  private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
  private static String DEFAULT_PAGE_SQL_ID = ".*Page$"; // 需要拦截的ID正则匹配

  @Override
  public Object intercept(Invocation invocation) throws Throwable
  {
    StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
            DEFAULT_OBJECT_WRAPPER_FACTORY);
    RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
    // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
    while (metaStatementHandler.hasGetter("h")) {
        Object object = metaStatementHandler.getValue("h");
        metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
    // 分离最后一个代理对象的目标类
    while (metaStatementHandler.hasGetter("target")) {
        Object object = metaStatementHandler.getValue("target");
        metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }

    // property在mybatis settings文件内配置
    Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

    // 设置pageSqlId
    String pageSqlId = "";
    Properties p = configuration.getVariables();
    if (p != null)
      pageSqlId = p.getProperty("pageSqlId");
    if (null == pageSqlId || "".equals(pageSqlId)) {
        pageSqlId = DEFAULT_PAGE_SQL_ID;
    }

    MappedStatement mappedStatement = (MappedStatement)
            metaStatementHandler.getValue("delegate.mappedStatement");
    // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
    if (mappedStatement.getId().matches(pageSqlId)) {
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject == null) {
            throw new NullPointerException("parameterObject is null!");
        } else {
            String sql = boundSql.getSql();
            // 重写sql,变成类似select * from table where ... limit 0,20
            if (this.databaseType.equals("mysql"))
              sql = sql + " LIMIT " + rowBounds.getOffset() + "," + rowBounds.getLimit();
            metaStatementHandler.setValue("delegate.boundSql.sql", sql);
            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        }
    }
    // 将执行权交给下一个拦截器
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target)
  {
    //目标类是StatementHandler的时候才拦截
    if (target instanceof StatementHandler)
    {
      return Plugin.wrap(target, this);
    } 
    else
    {
      return target;
    }
  }

  @Override
  public void setProperties(Properties properties)
  {
    this.databaseType = properties.getProperty("databaseType"); 
  }
  
}
