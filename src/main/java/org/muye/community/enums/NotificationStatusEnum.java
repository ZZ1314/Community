package org.muye.community.enums;

/**
 * @author Zz
 * create 2019--07--25--01:16
 **/
public enum NotificationStatusEnum {
    UNREAD(0),READ(1)
    ;
    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
