package org.muye.community.service;

import org.muye.community.dto.NotificationDTO;
import org.muye.community.dto.PaginationDTO;
import org.muye.community.enums.NotificationEnum;
import org.muye.community.mapper.NotificationMapper;
import org.muye.community.mapper.OriNotifyMapper;
import org.muye.community.mapper.QuestionMapper;
import org.muye.community.mapper.UserMapper;
import org.muye.community.model.Notification;
import org.muye.community.model.NotificationExample;
import org.muye.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zz
 * create 2019--07--25--01:36
 **/
@Service
public class NotificationService {
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    OriNotifyMapper oriNotifyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    public PaginationDTO List(Integer page, Integer size, User user) {
        //查询总数据计算总页数
        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(user.getId().longValue());
        Integer totalCount = notificationMapper.countByExample(notificationExample);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //对page范围进行约束
        if (totalPage <= 0) {
            totalPage = 1;
        }
        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        //计算分页的offset
        Integer offset = size * (page - 1);
        List<Notification> notifications = oriNotifyMapper.queryNotifyByReceiver(user.getId(), offset, size);
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        //列表获取用户信息 加入questionDTO中传输
        PaginationDTO paginationDTO = new PaginationDTO();
        if (notifications.size() == 0) {
            return paginationDTO;
        }
//        //获取通知的发送者
//        Set<Integer> disUserIds = notifications.stream().map(notify -> notify.getNotifier().intValue()).collect(Collectors.toSet());
//        ArrayList<Integer> userIds = new ArrayList<>(disUserIds);
//        UserExample userExample = new UserExample();
//        userExample.createCriteria().andIdIn(userIds);
//        List<User> users = userMapper.selectByExample(userExample);
//        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getId(), u -> u));
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setGmtCreate(notification.getGmeCreate());
            notificationDTO.setType(NotificationEnum.REPLY_QUESTION.getName());
            User notifier = userMapper.selectByPrimaryKey(notification.getNotifier().intValue());
            notificationDTO.setUser(notifier);
            String title = questionMapper.selectByPrimaryKey(notification.getOuterid().intValue()).getTitle();
            notificationDTO.setOuterTitle(title);
            notificationDTO.setStatus(notification.getStatus());
            notificationDTO.setId(notification.getId());
            notificationDTO.setOuterId(notification.getOuterid().intValue());
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setNotifications(notificationDTOS);
        //通过DTO内部方法赋值
        paginationDTO.setPagination(totalCount, page, size, totalPage);
        return paginationDTO;
    }
}
