package com.example.gpxlocationmqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class MqttConfigTest {

    private MqttClient mqttClient;

    @BeforeEach
    public void setUp() throws Exception {
        String brokerUrl = "tcp://broker.hivemq.com:1883";
        String clientId = "testClient";
        MemoryPersistence persistence = new MemoryPersistence();
        mqttClient = new MqttClient(brokerUrl, clientId, persistence);

        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        mqttClient.connect(connectOptions);
    }

    @Test
    public void testMqttConnection() throws Exception {
        assertTrue(mqttClient.isConnected());
    }

    @Test
    public void testPublishMessage() throws Exception {
        String payload = "Test Message";
        mqttClient.publish("test/topic", payload.getBytes(), 0, false);
        assertTrue(mqttClient.isConnected());
    }

    @Test
    public void testDisconnect() throws Exception {
        mqttClient.disconnect();
        assertFalse(mqttClient.isConnected());
    }
}
