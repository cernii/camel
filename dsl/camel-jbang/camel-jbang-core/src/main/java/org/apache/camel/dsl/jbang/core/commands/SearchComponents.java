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
package org.apache.camel.dsl.jbang.core.commands;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import org.apache.camel.dsl.jbang.core.common.MatchExtractor;
import org.apache.camel.dsl.jbang.core.common.exceptions.ResourceDoesNotExist;
import org.apache.camel.dsl.jbang.core.components.ComponentConverter;
import org.apache.camel.dsl.jbang.core.components.ComponentDescriptionMatching;
import org.apache.camel.dsl.jbang.core.components.ComponentPrinter;
import org.apache.camel.dsl.jbang.core.types.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "components", description = "Search for Camel components")
@Deprecated
class SearchComponents extends AbstractSearch implements Callable<Integer> {

    /*
     * Matches the following line. Separate them into groups and pick the last
     * which contains the description:
     *
     * * xref:ROOT:index.adoc[Components]
     */
    private static final Pattern PATTERN = Pattern.compile("(.*):(.*)\\[(.*)\\]");

    //CHECKSTYLE:OFF
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Display the help and sub-commands")
    private boolean helpRequested = false;
    //CHECKSTYLE:ON

    @Option(names = { "--search-term" }, defaultValue = "", description = "Default debug level")
    private String searchTerm;

    @Option(names = { "--base-resource-location" }, defaultValue = "github:apache", hidden = true,
            description = "Where to download the resources from")
    private String resourceLocation;

    @Option(names = { "--branch" }, defaultValue = "main", hidden = true,
            description = "The branch to use when downloading or searching resources (mostly used for development/testing)")
    private String branch;

    @Override
    public void printHeader() {
        System.out.printf("%-35s %-45s %s%n", "COMPONENT", "DESCRIPTION", "LINK");
        System.out.printf("%-35s %-45s %s%n", "-------", "-----------", "-----");
    }

    @Override
    public Integer call() throws Exception {
        setResourceLocation(resourceLocation, "camel:docs/components/modules/ROOT/nav.adoc");
        setBranch(branch);

        MatchExtractor<Component> matchExtractor;
        if (searchTerm.isEmpty()) {
            matchExtractor = new MatchExtractor<>(PATTERN, new ComponentConverter(), new ComponentPrinter());

        } else {
            matchExtractor = new MatchExtractor<>(
                    PATTERN, new ComponentConverter(),
                    new ComponentDescriptionMatching(searchTerm));

        }

        try {
            search(matchExtractor);
            return 0;
        } catch (ResourceDoesNotExist e) {
            System.err.println(e.getMessage());
            return 1;
        }
    }
}
