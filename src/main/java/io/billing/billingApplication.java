package io.billing;

import io.billing.resources.BillingController;
import io.billing.resources.StatsController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class billingApplication extends Application<billingConfiguration> {
    public static void main(final String[] args) throws Exception {
        new billingApplication().run(args);
    }

    @Override
    public String getName() {
        return "billing";
    }

    @Override
    public void initialize(final Bootstrap<billingConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final billingConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new BillingController());
        environment.jersey().register(new StatsController());
    }
}
