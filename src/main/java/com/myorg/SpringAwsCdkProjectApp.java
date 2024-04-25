package com.myorg;

import com.myorg.stack.*;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ses.actions.Sns;

public class SpringAwsCdkProjectApp {

    public static void main(final String[] args) {
        App app = new App();

        VPCStack vpcStack = new VPCStack(app, "Vpc", StackProps.builder().env(Environment.builder()
                .account("683414317101")
                .region("us-east-1")
                .build()).build());

        ClusterStack clusterStack = new ClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        RDSStack rdsStack = new RDSStack(app, "Rds",StackProps.builder().env(Environment.builder()
                .account("683414317101")
                .region("us-east-1")
                .build()).build() ,vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        SNSStack snsStack = new SNSStack(app, "Sns");

        Service01Stack service01Stack = new Service01Stack(app, "Service01Stack",StackProps.builder().env(Environment.builder()
                .account("683414317101")
                .region("us-east-1")
                .build()).build() ,clusterStack.getCluster());
        service01Stack.addDependency(clusterStack);
        service01Stack.addDependency(rdsStack);

        app.synth();
    }
}

