package com.zhenhua.microread.mapper;

import com.github.pagehelper.Page;
import com.zhenhua.microread.base.BaseMapper;
import com.zhenhua.microread.entity.Share;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {

    // 这种写法查询结果有毛病
    @Select("select * from share, share_img where share.user_id = #{userId} and share.share_id = share_img.share_id")
    List<Share> getShareList(String userId);
}