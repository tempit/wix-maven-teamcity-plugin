package com.wixpress.ci.teamcity.dependenciesTab;

import com.wixpress.ci.teamcity.DependenciesAnalyzer;
import com.wixpress.ci.teamcity.teamCityAnalyzer.BuildTypesDependencyAnalyzer;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.util.EventDispatcher;

/**
 * @author yoav
 * @since 2/23/12
 */
public class TeamCityEventsListener {
    private EventDispatcher<BuildServerListener> eventDispatcher;
    private DependenciesAnalyzer dependenciesAnalyzer;

    public TeamCityEventsListener(EventDispatcher<BuildServerListener> eventDispatcher,
                                  BuildTypesDependencyAnalyzer buildTypesAnalyzer) {
        this.eventDispatcher = eventDispatcher;
        this.dependenciesAnalyzer = buildTypesAnalyzer;
        eventDispatcher.addListener(new DependenciesEventListener());
    }

    public class DependenciesEventListener extends BuildServerAdapter {
        public void buildStarted(SRunningBuild build) {
            dependenciesAnalyzer.forceAnalyzeDependencies(build.getBuildType());
        }
    }
}