package org.muye.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zz
 * create 2019--07--14--09:42
 **/
@Data
public class PaginationDTO {
    //当前展示信息列表
    private List<QuestionDTO> questions;
    //显示前一页
    private boolean showPrevious;
    //显示第一页
    private boolean showFirstPage;
    //显示最后一页
    private boolean showEndPage;
    //显示下一页
    private boolean showNext;
    //当前页码
    private Integer page;
    //totalpage
    private Integer totalPage;
    //总计显示的页码列表
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size, Integer totalPage) {
        this.totalPage = totalPage;
        this.page=page;
        //页码显示
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //只有页码为1时 不显示 上一页按钮
        showPrevious = page==1?false:true;
        //同理 下一页按钮逻辑
        showNext = page==totalPage?false:true;
        //如果页码列表里有第一页则不显示第一页按钮 同理最后一页
        showFirstPage = pages.contains(1)?false:true;
        showEndPage = pages.contains(totalPage)?false:true;
    }
}
