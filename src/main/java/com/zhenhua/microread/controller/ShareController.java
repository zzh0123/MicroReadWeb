package com.zhenhua.microread.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zhenhua.microread.entity.Result;
import com.zhenhua.microread.entity.Share;
import com.zhenhua.microread.entity.ShareImg;
import com.zhenhua.microread.service.ShareImgService;
import com.zhenhua.microread.service.ShareService;
import com.zhenhua.microread.util.DateUtil;
import com.zhenhua.microread.util.ResultUtil;
import com.zhenhua.microread.util.URLConstant;
import com.zhenhua.microread.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzh
 * @create 2019-08-24 13:38
 * @desc 发布微读
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ShareService shareService;
    @Autowired
    private ShareImgService shareImgService;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file){
        Result result = new Result();
        if (file.isEmpty()){
//      Result.setCode(1);
            result.setMessage("上传失败，请选择文件！");
            return result;
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/Users/zzh/workspace_web/microread/src/main/webapp/WEB-INF/imgs";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功！");
            result.setCode(1);
            result.setMessage("上传成功！");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.toString(), e);
        }

//    Result.setCode(1);
        result.setMessage("上传失败");
        return result;
    }

    @GetMapping("/multiUpload")
    public String multiUpload() {
        return "multiUpload";
    }

    /**
     * 发布分享
     * @param userId
     * @param content
     * @param typeList
     * @param request
     * @return
     */
    @PostMapping("/multiUpload")
    @ResponseBody
    public Result multiUpload(
            @RequestParam(value = "userId", required = true) String userId,
                              @RequestParam(value = "content", required = true) String content,
                              @RequestParam(value = "typeList", required = true) String typeList,
//            @RequestBody String userId,
//            @RequestBody String content,
//            @RequestBody String typeList,
                              HttpServletRequest request) {
        LOGGER.info("--content--" + content);
        LOGGER.info("--userId--" + userId);
        LOGGER.info("--typeList--" + typeList);
        List<Integer> list_data = JSONObject.parseArray(typeList, Integer.class);//把字符串转换成集合
        LOGGER.info("--list_data.s--" + list_data.size());

        Result result = new Result();
        Share share = new Share();
        String shareId = UUIDUtil.getUUID();

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        LOGGER.info("--files-size-" + files.size());
        String dateStr = DateUtil.getStringAllDate();
        String filePath = "D:\\web_workspace\\microread_images\\"
                + userId + "\\" + dateStr + "\\";
        List<ShareImg> shareImgList = new ArrayList<ShareImg>();

        File file1 = new File(filePath);
        boolean s = file1.mkdirs();
        LOGGER.info("--s--" + s);
        if (files != null && files.size() > 0){
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                if (file.isEmpty()) {
                    result.setMessage("上传第" + (i++) + "个文件失败");
                    return result;
                }
                String fileName = file.getOriginalFilename();

                File dest = new File(filePath + fileName);
                try {
                    file.transferTo(dest);
                    String imgUrl = dest.getAbsolutePath();
                    LOGGER.info("--imgUrl--" + imgUrl);
                    ShareImg shareImg = new ShareImg();
                    shareImg.setImgId(UUIDUtil.getUUID());
                    shareImg.setImgType(list_data.get(i));
                    shareImg.setImgUrl(URLConstant.BASE_URL + "/images/" + userId + "/" + dateStr + "/"+ fileName);
                    shareImg.setShareId(shareId);
                    shareImgList.add(shareImg);
                    LOGGER.info("--shareImgList000--" + shareImgList.size());
                    LOGGER.info("第" + (i + 1) + "个文件上传成功");
                } catch (IOException e) {
                    LOGGER.error(e.toString(), e);
                    result.setMessage("上传第" + (i++) + "个文件失败");
                    return result;
                }
            }
        }


        share.setShareId(shareId);
        share.setUserId(userId);
        share.setContent(content);
        LOGGER.info("--NowDate--" + DateUtil.getNow());
        share.setCreateDate(DateUtil.getNow());
        Integer shareCount = shareService.insertShare(share);
        Integer imgListCount = null;
        LOGGER.info("--shareImgList--" + shareImgList.size());
        if (!shareImgList.isEmpty()){
            imgListCount = shareImgService.insertShareImgList(shareImgList);
        }

        if (shareCount != null || imgListCount != null) {
            result.setCode(800);
//            result.setData(share);
            result.setMessage("上传成功！");
        } else {
            result.setMessage("插入数据库失败！");
        }

        return result;

    }

    /**
     * 获取分享列表
     * @param userId
     * @param type
     * @param type
     * @return
     */
    @GetMapping("/getShareList")
    @ResponseBody
    public Result getShareList(@RequestParam(value = "userId", required = true) String userId,
                           @RequestParam(value = "type", defaultValue = "001") String type,
                               @RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "5") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Result result = null;
//        List<Share> shareList = shareService.getShareList(userId);
        List<Share> shareList = shareService.selectAll(userId);
//        LOGGER.info("--shareList--" + shareList.size());
        for (int i = 0; i < shareList.size(); i++){
            String shareId = shareList.get(i).getShareId();
//            LOGGER.info("--shareId--" + shareId);
            List<String> shareImgList = shareImgService.getShareImgList(shareId);
//            LOGGER.info("--shareImgList--" + shareImgList.size());
            shareList.get(i).setImgList(shareImgList);
        }
        result = ResultUtil.getSearchResult(shareList);
        return result;
    }

    @PostMapping("/test")
    public void test(
            @RequestParam(value = "typeList", required = true) String typeList,
                     @RequestParam(value = "userName", required = true) String userName,
                     @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "age", required = true) String age,
            HttpServletRequest request){

//            @RequestBody String typeList,
//                     @RequestBody String userName,
//                     @RequestBody String name){
        LOGGER.info("--userName--" + userName);
        LOGGER.info("--name--" + name);

        LOGGER.info("--request--" + request.getParameter(name) + "---" + request.getParameter(age)
        + request.getAttribute(name) + "---" +request.getAttribute(age));
//    public void test(@RequestBody List<Integer> typeList){
//        LOGGER.info("--typeList.size--" + typeList.size());
        LOGGER.info("--typeList--" + typeList);

        String json = JSONObject.toJSONString(typeList);
        LOGGER.info("--typeList-json-" + json);
//        String json1 = "[0,0,0,0,1,1,1,1]";
//        List<Integer> list_data = JSONObject.parseArray(json, Integer.class);//把字符串转换成集合
//        LOGGER.info("--list_data.s--" + list_data.size());
    }


}
