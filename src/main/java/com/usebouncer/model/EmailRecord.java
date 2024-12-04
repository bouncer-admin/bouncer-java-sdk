package com.usebouncer.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Objects;

public class EmailRecord {

    String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name;
    String status;
    String reason;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    EmailDomain domain;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    EmailAccount account;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    EmailDns dns;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String provider;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String didYouMean;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer score;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer toxicity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Instant retryAfter;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public EmailDomain getDomain() {
        return domain;
    }

    public void setDomain(EmailDomain domain) {
        this.domain = domain;
    }

    public EmailAccount getAccount() {
        return account;
    }

    public void setAccount(EmailAccount account) {
        this.account = account;
    }

    public EmailDns getDns() {
        return dns;
    }

    public void setDns(EmailDns dns) {
        this.dns = dns;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDidYouMean() {
        return didYouMean;
    }

    public void setDidYouMean(String didYouMean) {
        this.didYouMean = didYouMean;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getToxicity() {
        return toxicity;
    }

    public void setToxicity(Integer toxicity) {
        this.toxicity = toxicity;
    }

    public Instant getRetryAfter() {
        return retryAfter;
    }

    public void setRetryAfter(Instant retryAfter) {
        this.retryAfter = retryAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailRecord that = (EmailRecord) o;
        return Objects.equals(email, that.email)
                && Objects.equals(name, that.name)
                && Objects.equals(status, that.status)
                && Objects.equals(reason, that.reason)
                && Objects.equals(domain, that.domain)
                && Objects.equals(account, that.account)
                && Objects.equals(dns, that.dns)
                && Objects.equals(provider, that.provider)
                && Objects.equals(didYouMean, that.didYouMean)
                && Objects.equals(score, that.score)
                && Objects.equals(toxicity, that.toxicity)
                && Objects.equals(retryAfter, that.retryAfter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, status, reason, domain, account, dns, provider, didYouMean, score, toxicity, retryAfter);
    }

    @Override
    public String toString() {
        return "EmailRecord{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", domain=" + domain +
                ", account=" + account +
                ", dns=" + dns +
                ", provider='" + provider + '\'' +
                ", didYouMean='" + didYouMean + '\'' +
                ", score=" + score +
                ", toxicity=" + toxicity +
                ", retryAfter=" + retryAfter +
                '}';
    }
}
