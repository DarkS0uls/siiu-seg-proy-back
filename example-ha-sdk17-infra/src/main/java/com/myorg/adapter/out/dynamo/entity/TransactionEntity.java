package com.myorg.adapter.out.dynamo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serializable;

@Setter
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String uuid;
    private String username;
    private String creationDate;
    private String state;
    private String amount;
    private String accountId;

    private String description;


    //Getters
    @DynamoDbPartitionKey
    public String getUuid() {
        return uuid;
    }
    @DynamoDbAttribute("username")
    public String getUsername() {
        return username;
    }

    @DynamoDbAttribute("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @DynamoDbAttribute("state")
    public String getState() {
        return state;
    }

    @DynamoDbAttribute("amount")
    public String getAmount() {
        return amount;
    }

    @DynamoDbAttribute("accountId")
    public String getAccountId() {
        return accountId;
    }

    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }
}
