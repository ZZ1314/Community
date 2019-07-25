package org.muye.community.dto;

import com.sun.tools.corba.se.idl.InterfaceGen;
import lombok.Data;
import org.muye.community.model.User;

/**
 * @author Zz
 * create 2019--07--25--01:33
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User user;
    private String outerTitle;
    private String type;
    private Integer outerId;
}
