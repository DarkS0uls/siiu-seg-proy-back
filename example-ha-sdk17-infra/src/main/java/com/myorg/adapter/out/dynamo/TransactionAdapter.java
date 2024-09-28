package com.myorg.adapter.out.dynamo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.adapter.out.dynamo.entity.TransactionEntity;
import com.myorg.adapter.out.dynamo.mapper.DynamoTransactionMapper;
import com.myorg.kernel.domain.out.dynamo.TransactionDto;
import com.myorg.kernel.exception.DynamoTransactionException;
import com.myorg.ports.TransactionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SuppressWarnings("PMD")

@Slf4j
public class TransactionAdapter extends AbstractFacade<TransactionEntity> implements TransactionPort {

    private static final String ERROR = "Error,%s, Persistence Error: %s";
    private final static String TABLE_NAME = "test_dynamo";
    private final DynamoDbEnhancedClient enhancedClient;

    public TransactionAdapter(final DynamoDbEnhancedClient enhancedClient) {
        super(TransactionEntity.class);
        this.enhancedClient = enhancedClient;
    }


    @Override
    public Mono<TransactionDto> createTransaction(TransactionDto dto) {
        return Mono.fromCallable(() -> enhancedClient.table(TABLE_NAME, TableSchema.fromBean(TransactionEntity.class)))
                .map(mappedTable -> {
                    mappedTable.putItem(DynamoTransactionMapper.dtoToEntity(dto));
                    return dto;
                })
                .onErrorResume(DynamoDbException.class, e -> {
                    log.error("TransactionAdapter.createTransaction,Dynamo Exception:" + e);
                    throw new DynamoTransactionException(String.format("TransactionAdapter.createTransaction, Error Creating Transaction details:%s", e.getMessage()), e);
                })
                .onErrorResume(Mono::error);
    }
    @Override
    public Mono<TransactionDto> searchByKey(String key) {
        log.info("TransactionAdapter.searchByKey, Search By Key, with key: {}", key);
        return Mono.fromCallable(() -> enhancedClient.table(TABLE_NAME, TableSchema.fromBean(TransactionEntity.class)))
                .map(mappedTable -> getItemsByQueryConditional(mappedTable, buildQueryByKey(key)))
                .map(entityList->{
                    log.info("TransactionAdapter.searchByKey, Get First Or Nullable, size:{}", entityList.size());
                    log.info("My Transaction, {}", entityList.get(0).getUuid());
                    return getFirstOrNullable(entityList);
                })

                .map(transactionEntity -> {
                    log.info("HERE!!!");
                    try{

                     log.info("TransactionAdapter.searchByKey, TransactionEntity: {}", transactionEntity.toString());
                    }
                    catch (Exception e){
                        log.error("TransactionAdapter.searchByKey, Error getting Transaction  By Id details:{}", e.getMessage());
                    }
                    return transactionEntity;
                })
                .map(DynamoTransactionMapper::entityToDto)
                .onErrorResume(DynamoDbException.class, e -> {
                    log.error("TransactionAdapter.searchByKey,Dynamo Exception:" + e);
                    throw new DynamoTransactionException(String.format("TransactionAdapter.searchByKey, Error getting Transaction  By Id details:%s", e.getMessage()), e);
                })
                .onErrorResume(Mono::error);
    }
    private QueryConditional buildQueryByKey(String key) {
        log.info("TransactionAdapter.queryConditional, Build Query Conditional By Key: {}", key);
        return QueryConditional.keyEqualTo(
                Key
                        .builder()
                        .partitionValue(key)
                        .build()
        );
    }

    /*
    public TransactionEntity getFirstOrNullable(List<TransactionEntity> dataList) throws DynamoTransactionException {
        log.info("TransactionAdapter.getFirstOrNullable, Get First Or Nullable");
        if (dataList == null && dataList.isEmpty()) {
            log.error("Error, Key don't exists in TransactionEntity");
            throw new DynamoTransactionException("Error, Key don't exists in TransactionEntity Details: Error, Key don't exists ", null);
        } else {
            if (dataList.size() > 0) {
                log.info("Success, Persistence getFirstOrNullable ");
                try {
                    log.info("data uuid:{}",new ObjectMapper().writeValueAsString(dataList.get(0)));
                } catch (Exception e) {
                    log.error("Error, Persistence getFirstOrNullable:" + e.getMessage());
                }
                return dataList.get(0);
            } else {
                log.error(String.format("Error, Key don't exists in PseCashout "));
                throw new DynamoTransactionException("Error, Key don't exists in TransactionEntity Details: Error, Key don't exists ", null);
            }
        }

    }

    public List<TransactionEntity> getItemsByQueryConditional(DynamoDbTable<TransactionEntity> mappedTable, QueryConditional queryConditional) throws DynamoTransactionException {
        log.info("AbstractFacade.getItemsByQueryConditional, Get Items By Query Conditional, with queryConditional: {}", queryConditional.toString());
        try {
            Iterable<TransactionEntity> iterator = mappedTable
                    .query(r -> r.queryConditional(queryConditional))
                    .items();
            List<TransactionEntity> responseList= StreamSupport.stream(iterator.spliterator(), false)
                    .collect(Collectors.toList());
            log.info("Total Find Elements:{}",responseList.size());
            return responseList;
        } catch (Exception e) {
            log.error("Error, Pesistant Error:" + e.getMessage());
            throw new DynamoTransactionException(String.format(ERROR, "TransactionEntity", e.getMessage()), e);
        }
    }

     */
}
