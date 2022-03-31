package com.epam.liegou.framework.utils;

import io.qameta.allure.DefaultConfiguration;
import io.qameta.allure.Extension;
import io.qameta.allure.allure1.Allure1Plugin;
import io.qameta.allure.allure2.Allure2Plugin;
import io.qameta.allure.category.CategoriesPlugin;
import io.qameta.allure.category.CategoriesTrendPlugin;
import io.qameta.allure.context.FreemarkerContext;
import io.qameta.allure.context.JacksonContext;
import io.qameta.allure.context.MarkdownContext;
import io.qameta.allure.context.RandomUidContext;
import io.qameta.allure.core.*;
import io.qameta.allure.duration.DurationPlugin;
import io.qameta.allure.duration.DurationTrendPlugin;
import io.qameta.allure.environment.Allure1EnvironmentPlugin;
import io.qameta.allure.executor.ExecutorPlugin;
import io.qameta.allure.history.HistoryPlugin;
import io.qameta.allure.history.HistoryTrendPlugin;
import io.qameta.allure.idea.IdeaLinksPlugin;
import io.qameta.allure.influxdb.InfluxDbExportPlugin;
import io.qameta.allure.launch.LaunchPlugin;
import io.qameta.allure.owner.OwnerPlugin;
import io.qameta.allure.prometheus.PrometheusExportPlugin;
import io.qameta.allure.retry.RetryPlugin;
import io.qameta.allure.retry.RetryTrendPlugin;
import io.qameta.allure.severity.SeverityPlugin;
import io.qameta.allure.status.StatusChartPlugin;
import io.qameta.allure.suites.SuitesPlugin;
import io.qameta.allure.summary.SummaryPlugin;
import io.qameta.allure.tags.TagsPlugin;
import io.qameta.allure.timeline.TimelinePlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllureConfig {

  private final List<Extension> extensions = new ArrayList<>();

  public AllureConfig useDefault() {
    fromExtensions(
        Arrays.asList(
            new JacksonContext(),
            new MarkdownContext(),
            new FreemarkerContext(),
            new RandomUidContext(),
            new MarkdownDescriptionsPlugin(),
            new RetryPlugin(),
            new RetryTrendPlugin(),
            new TagsPlugin(),
            new SeverityPlugin(),
            new OwnerPlugin(),
            new IdeaLinksPlugin(),
            new HistoryPlugin(),
            new HistoryTrendPlugin(),
            new CategoriesPlugin(),
            new CategoriesTrendPlugin(),
            new DurationPlugin(),
            new DurationTrendPlugin(),
            new StatusChartPlugin(),
            new TimelinePlugin(),
            new SuitesPlugin(),
            new ReportWebPlugin(),
            new TestsResultsPlugin(),
            new AttachmentsPlugin(),
            new InfluxDbExportPlugin(),
            new PrometheusExportPlugin(),
            new SummaryPlugin(),
            new ExecutorPlugin(),
            new LaunchPlugin(),
            new Allure1Plugin(),
            new Allure1EnvironmentPlugin(),
            new Allure2Plugin()));
    return this;
  }

  public void fromExtensions(final List<Extension> extensions) {
    this.extensions.addAll(extensions);
  }

  public Configuration build() {
    return new DefaultConfiguration(
        Collections.unmodifiableList(extensions), Collections.unmodifiableList(new ArrayList<>()));
  }
}
