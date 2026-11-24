package com.sos.backend.health;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthControllerTest {
    private final HealthController controller = new HealthController();

    @Test
    void healthReturnsUpStatusAndServiceName() {
        var response = controller.health();
        assertEquals("UP", response.get("status"));
        assertEquals("sos-backend", response.get("service"));
    }
}
