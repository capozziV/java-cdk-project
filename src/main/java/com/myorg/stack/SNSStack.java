package com.myorg.stack;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.events.targets.SnsTopic;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.EmailSubscription;
import software.constructs.Construct;

public class SNSStack extends Stack {

    private final SnsTopic productEventsTopic;

    public SNSStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SNSStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        productEventsTopic = SnsTopic.Builder.create(Topic.Builder.create(this, "ProductEventsTopic")
                                                            .topicName("product-events")
                                                            .build())
                                                    .build();
        productEventsTopic.getTopic().addSubscription(EmailSubscription.Builder.create("vitorcapozzi4@gmail.com")
                        .json(true)
                        .build());
    }

    public SnsTopic getProductEventsTopic() {
        return productEventsTopic;
    }
}
