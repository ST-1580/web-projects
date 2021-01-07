package ru.itmo.wp.form;

import javax.validation.constraints.Pattern;

public class UserInTable {
    private long id;

    @Pattern(regexp = "^(Enable|Disable)$")
    private String action;

    public long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
