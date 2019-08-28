package com.zhenhua.microread.service;

import com.zhenhua.microread.entity.Result;
import com.zhenhua.microread.entity.ShareImg;
import com.zhenhua.microread.mapper.ShareImgMapper;
import com.zhenhua.microread.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzh
 * @create 2019-08-24 15:06
 * @desc 分享图片的service
 */
@Service
public class ShareImgService {

    @Autowired
    private ShareImgMapper mapper;

    public Integer insertShareImgList(List<ShareImg> shareImgList){
        Integer count = null;
        count = mapper.insertList(shareImgList);
        return count;
    }

}
