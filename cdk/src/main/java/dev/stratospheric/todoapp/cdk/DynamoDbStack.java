package dev.stratospheric.todoapp.cdk;

import dev.stratospheric.cdk.ApplicationEnvironment;
import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;

public class DynamoDbStack extends Stack {

  private final ApplicationEnvironment applicationEnvironment;

  public DynamoDbStack(
    final Construct scope,
    final String id,
    final Environment awsEnvironment,
    final ApplicationEnvironment applicationEnvironment,
    final String tableName
  ) {
    super(
      scope,
      id,
      StackProps.builder()
        .stackName(applicationEnvironment.prefix("DynamoDb"))
        .env(awsEnvironment)
        .build()
    );

    this.applicationEnvironment = applicationEnvironment;

    Table.Builder
      .create(scope, tableName)
      .tableName(tableName)
      .partitionKey(Attribute.builder().name("id").type(AttributeType.STRING).build())
      .billingMode(BillingMode.PROVISIONED)
      .readCapacity(10L)
      .writeCapacity(10L)
      .build();
  }
}
