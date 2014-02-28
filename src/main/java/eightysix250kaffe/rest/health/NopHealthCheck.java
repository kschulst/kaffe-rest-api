package eightysix250kaffe.rest.health;

import com.codahale.metrics.health.HealthCheck;
import org.springframework.stereotype.Component;

@Component
public class NopHealthCheck extends HealthCheck{
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
