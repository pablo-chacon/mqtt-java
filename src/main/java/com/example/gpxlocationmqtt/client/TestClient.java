package com.example.gpxlocationmqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TestClient {

    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";  // Use your Mosquitto broker URL
        String clientId = "testClient";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            sampleClient.connect();

            for (int i = 1; i <= 10; i++) {
                String payload = String.format("{\"Latitude\": %f, \"Longitude\": %f, \"Time\": \"%s\", \"user_id\": \"%s\"}",
                        59.289521, 18.056593, "2024-03-01T08:00:00", "user_" + i);
                MqttMessage message = new MqttMessage(payload.getBytes());
                sampleClient.publish("user/trajectory", message);
                Thread.sleep(1000);  // Adjust the sleep time as needed
            }

            sampleClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
