package com.rowan.core.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 事务处理
 * @author: rowan
 * @date: 2021-1-14 19:8:52
 **/
@Configuration
public class TransactionConfig {

    @Autowired
    TransactionManager transactionManager;

    @Bean("txAdvice")
    public TransactionInterceptor createTransactionInterceptor() {
        //只读事务
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        //如果存在事务就加入当前事务，如果没有加创建一个事务
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setTimeout(10);
        //通过方法名的匹配来寻找TransactionAttribute
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        Map<String, TransactionAttribute> txMap = new HashMap<>(10);
        txMap.put("save*", requiredTx);
        txMap.put("add*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("del*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        source.setNameMap(txMap);
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager, source);
        return tsi;
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor txAdvice) {
        DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
        pointcutAdvisor.setAdvice(txAdvice);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution (* com.*.service.*.*(..))");
        pointcutAdvisor.setPointcut(pointcut);
        return pointcutAdvisor;
    }

}
