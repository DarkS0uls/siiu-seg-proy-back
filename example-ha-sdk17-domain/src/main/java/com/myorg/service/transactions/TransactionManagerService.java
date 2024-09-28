package com.myorg.service.transactions;

import com.myorg.kernel.domain.out.dynamo.TransactionDto;
import com.myorg.kernel.domain.util.AbstractVo;
import com.myorg.ports.TransactionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class TransactionManagerService {

    private final TransactionPort transactionPort;

    public Mono<AbstractVo<TransactionDto>> createTransaction(TransactionDto dto){
        log.info("TransactionManagerService.createTransaction,Creating transaction with id: {}", dto.getTransactionId());
        return transactionPort.createTransaction(dto)
                .doOnSuccess(m -> log.info("TransactionManagerService.createTransaction,Transaction created successfully, with id: {}", m.getTransactionId()))
                .map(responseDto -> new AbstractVo<>(true, "Transaction created successfully", responseDto))
                .doOnError(e -> log.error("TransactionManagerService.createTransaction,Error creating transaction, with transaction id: {}, details:{}", dto.getTransactionId(), e.getMessage()))
                .onErrorResume(e -> Mono.just(new AbstractVo<>(false, "Error creating transaction", null)));
    }
    public Mono<AbstractVo<TransactionDto>> searchByKey(String key) {
        log.info("TransactionManagerService.searchByKey, Search By Key, with key: {}", key);
        return transactionPort.searchByKey(key)
                .doOnSuccess(m -> log.info("TransactionManagerService.searchByKey,Transaction found with id: {}", m.getTransactionId()))
                .map(responseDto -> new AbstractVo<>(true, "Transaction found successfully", responseDto))
                .doOnError(e -> log.error("TransactionManagerService.searchByKey,Error getting transaction by key: {}, details:{}", key, e.getMessage()))
                .onErrorResume(e -> Mono.just(new AbstractVo<>(false, "Error getting transaction by key", null)));
    }
}
