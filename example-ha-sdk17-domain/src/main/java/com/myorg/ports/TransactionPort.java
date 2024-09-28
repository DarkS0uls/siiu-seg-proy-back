package com.myorg.ports;

import com.myorg.kernel.domain.out.dynamo.TransactionDto;
import reactor.core.publisher.Mono;

public interface TransactionPort {

    Mono<TransactionDto> createTransaction(TransactionDto dto);
    Mono<TransactionDto> searchByKey(String key);

}
