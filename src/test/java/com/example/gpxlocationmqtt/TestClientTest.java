package com.example.gpxlocationmqtt;

import static org.mockito.Mockito.*;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;

public class TestClientTest {

    @Test
    public void testMqttClient() throws Exception {
        // Create a mock MQTT client
        MqttClient mockMqttClient = mock(MqttClient.class);

        // Simulate message publishing
        MqttMessage message = new MqttMessage("test message".getBytes());
        when(mockMqttClient.isConnected()).thenReturn(true);
        mockMqttClient.publish("test/topic", message);

        // Verify that the publish method was called with the correct parameters
        verify(mockMqttClient).publish("test/topic", message);
    }
}
