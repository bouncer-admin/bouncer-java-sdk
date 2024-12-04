package com.usebouncer.model;

import java.util.Objects;

public class EmailDomain {

    private String name;
    private String acceptAll;
    private String free;
    private String disposable;

    public String getName() {
        return name;
    }

    public String getAcceptAll() {
        return acceptAll;
    }

    public String getFree() {
        return free;
    }

    public String getDisposable() {
        return disposable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcceptAll(String acceptAll) {
        this.acceptAll = acceptAll;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public void setDisposable(String disposable) {
        this.disposable = disposable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDomain that = (EmailDomain) o;
        return Objects.equals(name, that.name) && Objects.equals(acceptAll, that.acceptAll) && Objects.equals(free, that.free) && Objects.equals(disposable, that.disposable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, acceptAll, free, disposable);
    }

    @Override
    public String toString() {
        return "EmailDomain{" +
                "name='" + name + '\'' +
                ", acceptAll='" + acceptAll + '\'' +
                ", free='" + free + '\'' +
                ", disposable='" + disposable + '\'' +
                '}';
    }
}

