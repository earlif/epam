/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.liegou.projects.demo.runners;

import com.epam.liegou.projects.TestRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/main/java/com/epam/liegou/projects/demo/features/demo.feature"
        },
        glue = {"com/epam/liegou/projects/demo/steps"},
        monochrome = true)
public class DemoTestRunner extends TestRunner {}
