package com.usebouncer.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Objects;

public class BatchStatus {

    String batchId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name;
    Instant created;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Instant started;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Instant completed;
    String status;
    Integer quantity;
    Integer duplicates;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer credits;

    // processed, stats included only if with-stats=true
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer processed;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Stats stats;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getStarted() {
        return started;
    }

    public void setStarted(Instant started) {
        this.started = started;
    }

    public Instant getCompleted() {
        return completed;
    }

    public void setCompleted(Instant completed) {
        this.completed = completed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(Integer duplicates) {
        this.duplicates = duplicates;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchStatus that = (BatchStatus) o;
        return Objects.equals(batchId, that.batchId) && Objects.equals(name, that.name) && Objects.equals(created, that.created) && Objects.equals(started, that.started) && Objects.equals(completed, that.completed) && Objects.equals(status, that.status) && Objects.equals(quantity, that.quantity) && Objects.equals(duplicates, that.duplicates) && Objects.equals(credits, that.credits) && Objects.equals(processed, that.processed) && Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchId, name, created, started, completed, status, quantity, duplicates, credits, processed, stats);
    }

    @Override
    public String toString() {
        return "BatchStatus{" +
                "batchId='" + batchId + '\'' +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", started=" + started +
                ", completed=" + completed +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", duplicates=" + duplicates +
                ", credits=" + credits +
                ", processed=" + processed +
                ", stats=" + stats +
                '}';
    }

    public static class Stats {
        Integer deliverable;
        Integer risky;
        Integer undeliverable;
        Integer unknown;

        public Integer getDeliverable() {
            return deliverable;
        }

        public void setDeliverable(Integer deliverable) {
            this.deliverable = deliverable;
        }

        public Integer getRisky() {
            return risky;
        }

        public void setRisky(Integer risky) {
            this.risky = risky;
        }

        public Integer getUndeliverable() {
            return undeliverable;
        }

        public void setUndeliverable(Integer undeliverable) {
            this.undeliverable = undeliverable;
        }

        public Integer getUnknown() {
            return unknown;
        }

        public void setUnknown(Integer unknown) {
            this.unknown = unknown;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stats stats = (Stats) o;
            return Objects.equals(deliverable, stats.deliverable) && Objects.equals(risky, stats.risky) && Objects.equals(undeliverable, stats.undeliverable) && Objects.equals(unknown, stats.unknown);
        }

        @Override
        public int hashCode() {
            return Objects.hash(deliverable, risky, undeliverable, unknown);
        }

        @Override
        public String toString() {
            return "Stats{" +
                    "deliverable=" + deliverable +
                    ", risky=" + risky +
                    ", undeliverable=" + undeliverable +
                    ", unknown=" + unknown +
                    '}';
        }
    }
}
