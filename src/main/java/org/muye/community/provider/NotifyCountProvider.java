package org.muye.community.provider;

import org.muye.community.mapper.NotificationMapper;
import org.muye.community.model.NotificationExample;
import org.muye.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * @author Zz
 * create 2019--07--25--12:40
 **/
@Component
public class NotifyCountProvider {
    @Autowired
    NotificationMapper notificationMapper;
    public void getNotifyCount(Model model, User user) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(user.getId().longValue()).andStatusEqualTo(0);
        int count = notificationMapper.countByExample(notificationExample);
        model.addAttribute("count",count);
    }
}
