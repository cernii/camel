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
package org.apache.camel.builder.component.dsl;

import javax.annotation.Generated;
import org.apache.camel.Component;
import org.apache.camel.builder.component.AbstractComponentBuilder;
import org.apache.camel.builder.component.ComponentBuilder;
import org.apache.camel.component.ignite.cache.IgniteCacheComponent;

/**
 * Perform cache operations on an Ignite cache or consume changes from a
 * continuous query.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.ComponentDslMojo")
public interface IgniteCacheComponentBuilderFactory {

    /**
     * Ignite Cache (camel-ignite)
     * Perform cache operations on an Ignite cache or consume changes from a
     * continuous query.
     * 
     * Category: cache,datagrid
     * Since: 2.17
     * Maven coordinates: org.apache.camel:camel-ignite
     */
    static IgniteCacheComponentBuilder igniteCache() {
        return new IgniteCacheComponentBuilderImpl();
    }

    /**
     * Builder for the Ignite Cache component.
     */
    interface IgniteCacheComponentBuilder
            extends
                ComponentBuilder<IgniteCacheComponent> {
        /**
         * Resource from where to load configuration.
         * 
         * The option is a: <code>java.lang.Object</code> type.
         * 
         * Group: common
         */
        default IgniteCacheComponentBuilder configurationResource(
                java.lang.Object configurationResource) {
            doSetProperty("configurationResource", configurationResource);
            return this;
        }
        /**
         * Ignite instance.
         * 
         * The option is a: <code>org.apache.ignite.Ignite</code> type.
         * 
         * Group: common
         */
        default IgniteCacheComponentBuilder ignite(
                org.apache.ignite.Ignite ignite) {
            doSetProperty("ignite", ignite);
            return this;
        }
        /**
         * Ignite configuration.
         * 
         * The option is a:
         * <code>org.apache.ignite.configuration.IgniteConfiguration</code>
         * type.
         * 
         * Group: common
         */
        default IgniteCacheComponentBuilder igniteConfiguration(
                org.apache.ignite.configuration.IgniteConfiguration igniteConfiguration) {
            doSetProperty("igniteConfiguration", igniteConfiguration);
            return this;
        }
        /**
         * Allows for bridging the consumer to the Camel routing Error Handler,
         * which mean any exceptions occurred while the consumer is trying to
         * pickup incoming messages, or the likes, will now be processed as a
         * message and handled by the routing Error Handler. By default the
         * consumer will use the org.apache.camel.spi.ExceptionHandler to deal
         * with exceptions, that will be logged at WARN or ERROR level and
         * ignored.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: consumer
         */
        default IgniteCacheComponentBuilder bridgeErrorHandler(
                boolean bridgeErrorHandler) {
            doSetProperty("bridgeErrorHandler", bridgeErrorHandler);
            return this;
        }
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: producer
         */
        default IgniteCacheComponentBuilder lazyStartProducer(
                boolean lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
        /**
         * Whether the component should use basic property binding (Camel 2.x)
         * or the newer property binding with additional capabilities.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: advanced
         */
        default IgniteCacheComponentBuilder basicPropertyBinding(
                boolean basicPropertyBinding) {
            doSetProperty("basicPropertyBinding", basicPropertyBinding);
            return this;
        }
    }

    class IgniteCacheComponentBuilderImpl
            extends
                AbstractComponentBuilder<IgniteCacheComponent>
            implements
                IgniteCacheComponentBuilder {
        @Override
        protected IgniteCacheComponent buildConcreteComponent() {
            return new IgniteCacheComponent();
        }
        @Override
        protected boolean setPropertyOnComponent(
                Component component,
                String name,
                Object value) {
            switch (name) {
            case "configurationResource": ((IgniteCacheComponent) component).setConfigurationResource((java.lang.Object) value); return true;
            case "ignite": ((IgniteCacheComponent) component).setIgnite((org.apache.ignite.Ignite) value); return true;
            case "igniteConfiguration": ((IgniteCacheComponent) component).setIgniteConfiguration((org.apache.ignite.configuration.IgniteConfiguration) value); return true;
            case "bridgeErrorHandler": ((IgniteCacheComponent) component).setBridgeErrorHandler((boolean) value); return true;
            case "lazyStartProducer": ((IgniteCacheComponent) component).setLazyStartProducer((boolean) value); return true;
            case "basicPropertyBinding": ((IgniteCacheComponent) component).setBasicPropertyBinding((boolean) value); return true;
            default: return false;
            }
        }
    }
}