package org.muye.community.enums;

/**
 * @author Zz
 * create 2019--07--25--01:07
 **/
public enum NotificationEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(1,"回复了评论")
    ;
    private int type;
    private String name;

    NotificationEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
