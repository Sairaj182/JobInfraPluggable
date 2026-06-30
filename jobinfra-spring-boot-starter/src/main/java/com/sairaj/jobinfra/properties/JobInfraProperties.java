package com.sairaj.jobinfra.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jobinfra")
public class JobInfraProperties {
    private Worker worker = new Worker();
    private Retry retry = new Retry();
    private Webhook webhook = new Webhook();

    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public Retry getRetry() {
        return retry;
    }
    public void setRetry(Retry retry) {
        this.retry = retry;
    }
    public Webhook getWebhook() {
        return webhook;
    }
    public void setWebhook(Webhook webhook) {
        this.webhook = webhook;
    }

    public static class Worker {
        private int count = 3;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static class Retry {
        private int maxAttempts = 3;

        public int getMaxAttempts() {
            return maxAttempts;
        }

        public void setMaxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }
    }

    public static class Webhook {
        private String secret = "default_secret_please_change";
        private int timeoutMs = 5000;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public int getTimeoutMs() {
            return timeoutMs;
        }

        public void setTimeoutMs(int timeoutMs) {
            this.timeoutMs = timeoutMs;
        }
    }
}
