/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.skywalking.apm.collector.core.module;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wu-sheng
 */
public class ModuleManagerTest {
    @Test
    public void testInit() throws ServiceNotProvidedException, ModuleNotFoundException, ProviderNotFoundException, DuplicateProviderException {
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        configuration.addModule("Test").addProviderConfiguration("TestModule-Provider", null);
        configuration.addModule("BaseA").addProviderConfiguration("P-A", null);
        configuration.addModule("BaseB").addProviderConfiguration("P-B", null);

        ModuleManager manager = new ModuleManager();
        manager.init(configuration);

        BaseModuleA.ServiceABusiness1 serviceABusiness1 = manager.find("BaseA").provider().getService(BaseModuleA.ServiceABusiness1.class);
        Assert.assertTrue(serviceABusiness1 != null);
    }
}
