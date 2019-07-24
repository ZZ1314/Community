package org.muye.community.enums;

/**
 * @author Zz
 * create 2019--07--23--11:26
 **/
public enum  CommentTypeEnum {
    QUESTION(1),COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
