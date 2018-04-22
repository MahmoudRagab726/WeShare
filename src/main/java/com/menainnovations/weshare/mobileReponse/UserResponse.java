package com.menainnovations.weshare.mobileReponse;

import com.menainnovations.weshare.model.User;

public class UserResponse {
    private int status;
    private User user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
