package com.usebouncer.model;

import java.util.Objects;

public class EmailAccount {

    String disabled;
    String fullMailbox;
    String role;

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getFullMailbox() {
        return fullMailbox;
    }

    public void setFullMailbox(String fullMailbox) {
        this.fullMailbox = fullMailbox;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAccount that = (EmailAccount) o;
        return Objects.equals(disabled, that.disabled) && Objects.equals(fullMailbox, that.fullMailbox) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disabled, fullMailbox, role);
    }

    @Override
    public String toString() {
        return "EmailAccount{" +
                "disabled='" + disabled + '\'' +
                ", fullMailbox='" + fullMailbox + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
