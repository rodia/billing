package io.billing.services;

public enum Stats {
    INSTANCE;

    private final StatsInterface statsService;

    Stats() {
        this.statsService = new StatsService();
    }

    public StatsInterface getStatsService() {
        return this.statsService;
    }
}
