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

package org.apache.camel.component.hashicorp.vault.integration.operations;

import java.util.HashMap;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until we'll have a Camel-Hashicorp-vault test-infra module")
public class HashicorpProducerCreateSecretIT extends CamelTestSupport {

    @EndpointInject("mock:result")
    private MockEndpoint mock;

    @Test
    public void createSecretTest() {

        mock.expectedMessageCount(1);
        Exchange exchange = template.request("direct:createSecret", new Processor() {
            @Override
            public void process(Exchange exchange) {
                HashMap map = new HashMap();
                map.put("integer", "30");
                exchange.getIn().setBody(map);
            }
        });

    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:createSecret")
                        .to("hashicorp-vault://secret?operation=createSecret&token=RAW(token)&host=localhost&scheme=http&secretPath=test")
                        .to("mock:result");
            }
        };
    }
}
