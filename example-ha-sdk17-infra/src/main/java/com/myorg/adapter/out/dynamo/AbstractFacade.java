package com.myorg.adapter.out.dynamo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.kernel.exception.DynamoTransactionException;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@SuppressWarnings("PMD")
public class AbstractFacade<T> {

    private Class<T> entityClass;

    private static final String ERROR = "Error,%s, Persistence Error: %s";

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * This Method brings a list of elements with a specific query conditional
     *
     * @param mappedTable
     * @param queryConditional
     * @return
     */
    public List<T> getItemsByQueryConditional(DynamoDbTable<T> mappedTable, QueryConditional queryConditional) throws DynamoTransactionException {
        log.info("AbstractFacade.getItemsByQueryConditional, Get Items By Query Conditional, with queryConditional: {}", queryConditional.toString());
        try {
            Iterable<T> iterator = mappedTable
                    .query(r -> r.queryConditional(queryConditional))
                    .items();
            List<T> responseList=StreamSupport.stream(iterator.spliterator(), false)
                    .collect(Collectors.toList());
            log.info("Total Find Elements:{}",responseList.size());
            return responseList;
        } catch (Exception e) {
            log.error("Error," + entityClass.getName() + ", Pesistant Error:" + e.getMessage());
            throw new DynamoTransactionException(String.format(ERROR, entityClass.getName(), e.getMessage()), e);
        }
    }


    public T getItemByKey(DynamoDbTable<T> mappedTable, Key key) {
        log.info("AbstractFacade.getItemByKey, Get Item By Key, with key: {}", key.partitionKeyValue());
        return mappedTable.getItem(r -> r.key(key));
    }

    public T getFirstOrNullable(List<T> dataList) throws DynamoTransactionException {
        log.info("AbstractFacade.getFirstOrNullable, Get First Or Nullable");
        if (dataList == null) {
            log.error("Error, Key don't exists in TransactionEntity");
            throw new DynamoTransactionException("Error, Key don't exists in " + entityClass.getName() + "Details: Error, Key don't exists ", null);
        } else {
            if (!dataList.isEmpty()) {
                log.info("Success, Persistence getFirstOrNullable ");
                try {
                    log.info("data uuid:{}",new ObjectMapper().writeValueAsString(dataList.get(0)));
                } catch (Exception e) {
                    log.error("Error, Persistence getFirstOrNullable:" + e.getMessage());
                }
                return dataList.get(0);
            } else {
                log.error(String.format("Error, Key don't exists in PseCashout "));
                throw new DynamoTransactionException("Error, Key don't exists in " + entityClass.getName() + "Details: Error, Key don't exists ", null);
            }
        }

    }
}
