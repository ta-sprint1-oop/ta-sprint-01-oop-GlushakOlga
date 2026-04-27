package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;
import com.softserve.academy.exception.NotDeliverableException;

public abstract class Notification implements Comparable<Notification> {
    protected String recipient;
    protected String message;
    protected int priority;
    protected NotificationStatus status;

    public Notification(String recipient, String message, int priority) {
        // TODO: Базова валідація в конструкторі:
        // порожній отримувач -> InvalidNotificationException
        // порожнє повідомлення (null) -> InvalidNotificationException
        // priority від 1 до 5, інакше -> InvalidNotificationException
        if (recipient == null || recipient.isBlank()) {
            throw new InvalidNotificationException("Recipient cannot be empty");
        }
        if (message == null || message.isBlank()) {
            throw new InvalidNotificationException("Message cannot be empty");
        }
        if (priority < 1 || priority > 5) {
            throw new InvalidNotificationException("Priority must be betweem 1 and 5");
        }

        this.recipient = recipient;
        this.message = message;
        this.priority = priority;
        this.status = NotificationStatus.PENDING;
    }

    public abstract boolean isDeliverable();

    public abstract String getFormattedMessage();

    public abstract int estimateDeliverySeconds();

    public boolean isHighPriority() {
        // TODO: Пріоритет >= 4
        return priority >= 4;
    }

    public void send() throws NotDeliverableException {
        // TODO: Шаблонний метод (Template Method):
        // 1. Перевірка isDeliverable()
        // 2. Якщо !isDeliverable() -> статус FAILED та throw NotDeliverableException
        // 3. performSend()
        // 4. Успіх -> статус SENT
        if (!isDeliverable()) {
            status = NotificationStatus.FAILED;
            throw new NotDeliverableException("Notification is not deliverable");
        }
        performSend();
        status = NotificationStatus.SENT;
    }

    protected abstract void performSend() throws NotDeliverableException;

    @Override
    public int compareTo(Notification other) {
        // TODO: Сортування за priority descending
        return Integer.compare(other.priority, this.priority);
    }

    // Getters
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public int getPriority() { return priority; }
    public NotificationStatus getStatus() { return status; }
}
