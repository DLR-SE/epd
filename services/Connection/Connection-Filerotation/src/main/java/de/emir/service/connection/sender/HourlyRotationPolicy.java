package de.emir.service.connection.sender;

import com.vlkan.rfos.Clock;
import com.vlkan.rfos.policy.TimeBasedRotationPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Policy for triggering a rotation at every hour.
 */
public class HourlyRotationPolicy extends TimeBasedRotationPolicy {

    private static final Logger LOGGER = LoggerFactory.getLogger(HourlyRotationPolicy.class);

    private static final HourlyRotationPolicy INSTANCE = new HourlyRotationPolicy();

    private HourlyRotationPolicy() {
        // Do nothing.
    }

    /**
     * @return an instance of this policy
     */
    public static HourlyRotationPolicy getInstance() {
        return INSTANCE;
    }

    /**
     * @return the instant of the upcoming midnight
     */
    @Override
    public Instant getTriggerInstant(Clock clock) {
        return clock.now().plus(1, ChronoUnit.HOURS);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    public String toString() {
        return "HourlyRotationPolicy";
    }

}