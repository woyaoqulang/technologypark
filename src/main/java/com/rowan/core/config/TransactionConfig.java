package com.rowan.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        List<RollbackRuleAttribute> attributeList = new ArrayList<>();

        //通过方法名的匹配来寻找TransactionAttribute
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        Map<String, TransactionAttribute> txMap = new HashMap<>(10);
        source.setNameMap(txMap);
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager, source);
        return tsi;
    }
}
