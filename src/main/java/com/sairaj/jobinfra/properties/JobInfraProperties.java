package com.sairaj.jobinfra.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jobinfra")
public class JobInfraProperties {
    private Worker worker = new Worker();
    private Retry retry = new Retry();

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
}
