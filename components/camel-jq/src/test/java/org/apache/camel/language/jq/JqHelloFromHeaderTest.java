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
package org.apache.camel.language.jq;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.camel.builder.RouteBuilder;
import org.junit.jupiter.api.Test;

public class JqHelloFromHeaderTest extends JqTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                        .transform().jq(".foo", "Content")
                        .to("mock:result");
            }
        };
    }

    @Test
    public void testHelloHeader() throws Exception {
        getMockEndpoint("mock:result")
                .expectedBodiesReceived(new TextNode("bar"));

        ObjectNode node = MAPPER.createObjectNode();
        node.put("foo", "bar");

        template.sendBodyAndHeader("direct:start", null, "Content", node);

        assertMockEndpointsSatisfied();
    }
}
