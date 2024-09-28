package com.myorg.adapter.out.dynamo.mapper;

import com.myorg.adapter.out.dynamo.entity.TransactionEntity;
import com.myorg.kernel.domain.out.dynamo.TransactionDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DynamoTransactionMapper {

    public static TransactionDto entityToDto(TransactionEntity entity) {
        return TransactionDto
                .builder()
                .transactionId(entity.getUuid())
                .creationDate(entity.getCreationDate())
                .state(entity.getState())
                .amount(entity.getAmount())
                .accountId(entity.getAccountId())
                .userName(entity.getUsername())
                .description(entity.getDescription())
                .build();
    }

    public static TransactionEntity dtoToEntity(TransactionDto dto) {
        return TransactionEntity
                .builder()
                .uuid(dto.getTransactionId())
                .creationDate(dto.getCreationDate())
                .state(dto.getState())
                .amount(dto.getAmount())
                .accountId(dto.getAccountId())
                .username(dto.getUserName())
                .description(dto.getDescription())
                .build();

    }
}
