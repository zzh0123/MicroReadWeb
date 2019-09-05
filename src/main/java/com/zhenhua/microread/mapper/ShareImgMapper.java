package com.zhenhua.microread.mapper;

import com.zhenhua.microread.base.BaseMapper;
import com.zhenhua.microread.entity.ShareImg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShareImgMapper extends BaseMapper<ShareImg> {

    @Select("select img_url from share_img where share_id = #{shareId}")
    List<String> getShareImgList(@Param("shareId") String shareId);
}