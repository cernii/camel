/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.processor.aggregate.jdbc;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.junit.jupiter.api.Test;

public class JdbcAggregateRecoverDeadLetterChannelTest extends AbstractJdbcAggregationTestSupport {

    @Override
    void configureJdbcAggregationRepository() {
        // enable recovery
        repo.setUseRecovery(true);
        // exhaust after at most 3 attempts
        repo.setMaximumRedeliveries(3);
        // and move to this dead letter channel
        repo.setDeadLetterUri("mock:dead");
        // check faster
        repo.setRecoveryInterval(500, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testJdbcAggregateRecoverDeadLetterChannel() throws Exception {
        // should fail all times
        getMockEndpoint("mock:result").expectedMessageCount(0);
        getMockEndpoint("mock:aggregated").expectedMessageCount(4);
        getMockEndpoint("mock:dead").expectedBodiesReceived("ABCDE");
        getMockEndpoint("mock:dead").message(0).header(Exchange.REDELIVERED).isEqualTo(Boolean.TRUE);
        getMockEndpoint("mock:dead").message(0).header(Exchange.REDELIVERY_COUNTER).isEqualTo(3);

        template.sendBodyAndHeader("direct:start", "A", "id", 123);
        template.sendBodyAndHeader("direct:start", "B", "id", 123);
        template.sendBodyAndHeader("direct:start", "C", "id", 123);
        template.sendBodyAndHeader("direct:start", "D", "id", 123);
        template.sendBodyAndHeader("direct:start", "E", "id", 123);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                        .aggregate(header("id"), new MyAggregationStrategy())
                        .completionSize(5).aggregationRepository(repo)
                        .log("aggregated exchange id ${exchangeId} with ${body}")
                        .to("mock:aggregated")
                        .throwException(new IllegalArgumentException("Damn"))
                        .to("mock:result")
                        .end();
            }
        };
    }
}
