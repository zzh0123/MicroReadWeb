package com.zhenhua.microread.service;

import com.zhenhua.microread.entity.Result;
import com.zhenhua.microread.entity.Share;
import com.zhenhua.microread.mapper.ShareMapper;
import com.zhenhua.microread.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

/**
 * @author zzh
 * @create 2019-08-24 13:39
 * @desc 发布微读服务
 */
@Service
public class ShareService {

    @Autowired
    private ShareMapper mapper;

    /**
     * 功能描述: 发布share
     * @param:
     * @return:
     * @auther: zzh
     * @date: 2019-08-24 13:49
     */
    public Integer insertShare(Share share){
        Integer count = mapper.insert(share);
//        if (count == 1){
//            result = ResultUtil.getInsertResult(share);
//        } else {
//            result = ResultUtil.getInsertResult(null);
//        }
        return count;
    }

    public List<Share> getShareList(String userId){
        List<Share> shareList = mapper.getShareList(userId);
        return shareList;
    }

    public List<Share> selectAll(String userId){
        List<Share> shareList = mapper.selectAll();
        return shareList;
    }

}
