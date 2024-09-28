package com.myorg.kernel.domain.out.dynamo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TransactionDto implements Serializable {
    private String transactionId;
    private String creationDate;
    private String state;
    private String amount;
    private String accountId;
    private String userName;
    private String description;

}
