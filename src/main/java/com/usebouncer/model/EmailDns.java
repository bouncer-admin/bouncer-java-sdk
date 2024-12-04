package com.usebouncer.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

public class EmailDns {

    private String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String record;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DnsRecord> records;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public List<DnsRecord> getRecords() {
        return records;
    }

    public void setRecords(List<DnsRecord> records) {
        this.records = records;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDns emailDns = (EmailDns) o;
        return Objects.equals(type, emailDns.type) && Objects.equals(record, emailDns.record) && Objects.equals(records, emailDns.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, record, records);
    }

    @Override
    public String toString() {
        return "EmailDns{" +
                "type='" + type + '\'' +
                ", record='" + record + '\'' +
                ", records=" + records +
                '}';
    }

    public static class DnsRecord {
        String type;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String ip;
        String name;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Integer priority;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DnsRecord dnsRecord = (DnsRecord) o;
            return Objects.equals(type, dnsRecord.type) && Objects.equals(ip, dnsRecord.ip) && Objects.equals(name, dnsRecord.name) && Objects.equals(priority, dnsRecord.priority);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, ip, name, priority);
        }

        @Override
        public String toString() {
            return "DnsRecord{" +
                    "type='" + type + '\'' +
                    ", ip='" + ip + '\'' +
                    ", name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }
}
