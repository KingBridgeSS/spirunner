package org.bridge.spirunner.runner;

public class Results {
    public String dockerLogs;
    public String systemLogs;

    public Results() {
    }

    public Results(String dockerLogs, String systemLogs) {
        this.dockerLogs = dockerLogs;
        this.systemLogs = systemLogs;
    }
}
