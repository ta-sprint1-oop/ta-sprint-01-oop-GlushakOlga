package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;

public class PushNotification extends Notification {
    private String deviceToken;
    private String iconUrl;

    public PushNotification(String recipient, String message, int priority, String deviceToken, String iconUrl) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.deviceToken = deviceToken;
        this.iconUrl = iconUrl;

    }

    @Override
    public boolean isDeliverable() {
        // TODO: deviceToken не blank і довжина > 10
        return deviceToken != null &&
                !deviceToken.isBlank() &&
                deviceToken.length() > 10;
    }

    public boolean isSilent() {
        // TODO: true якщо message порожнє (тільки тайтл)
        return message == null || message.isBlank();
    }

    @Override
    public String getFormattedMessage() {
        // TODO: 🔔 {message} (якщо silent -> 🔔 (silent))
        if (isSilent()) {
            return "🔔 (silent)";
        }
        return "🔔 " + message;
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 1
        return 1;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        System.out.println("Sending PUSH notification to: " + recipient);
        System.out.println(getFormattedMessage());
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
