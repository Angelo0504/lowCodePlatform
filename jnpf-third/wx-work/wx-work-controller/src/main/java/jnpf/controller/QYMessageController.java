package jnpf.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.util.JsonUtil;
import jnpf.QYMessageEntity;
import jnpf.util.StringUtil;
import jnpf.base.ActionResult;
import jnpf.base.vo.ListVO;
import jnpf.base.vo.PaginationVO;
import jnpf.exception.DataException;
import jnpf.exception.WxErrorException;
import jnpf.model.qymessage.PaginationQYMessage;
import jnpf.model.qymessage.QYMessageListVO;
import jnpf.model.qymessage.QYMessageSentForm;
import jnpf.model.qymessage.QYMessageUserListVO;
import jnpf.permission.UsersApi;
import jnpf.permission.model.user.UserAllModel;
import jnpf.service.QYMessageService;
import jnpf.util.type.StringNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 企业消息
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
@Api(tags = "企业消息", value = "QYMessage")
@RestController
@RequestMapping("/WeChat/QYMessage")
public class QYMessageController {

    @Autowired
    private QYMessageService qyMessageService;
    @Autowired
    private UsersApi usersApi;

    /**
     * 列表
     *
     * @param paginationQYMessage
     * @return
     */
    @ApiOperation("企业消息列表")
    @GetMapping
    public ActionResult list(PaginationQYMessage paginationQYMessage) {
        List<QYMessageEntity> data = qyMessageService.getList(paginationQYMessage);
        List<UserAllModel> userAll = usersApi.getAll().getData();
        List<QYMessageListVO> listVO = new ArrayList<>();
        for (QYMessageEntity entity : data) {
            QYMessageListVO vo = JsonUtil.getJsonToBean(entity, QYMessageListVO.class);
            if (StringUtil.isNotEmpty(entity.getToUserId())) {
                StringBuilder builder = new StringBuilder();
                for (String id : entity.getToUserId().split(",")) {
                    UserAllModel model = userAll.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(new UserAllModel());
                    builder.append(model.getRealName() + "/" + model.getAccount() + ",");
                }
                builder.deleteCharAt(builder.length() - 1);
                vo.setToUser(builder.toString());
            } else {
                vo.setToUser("@all");
            }
            listVO.add(vo);
        }
        PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationQYMessage, PaginationVO.class);
        return ActionResult.page(listVO,paginationVO);
    }

    /**
     * 列表
     *
     * @param
     * @return
     */
    @ApiOperation("企业号用户列表")
    @GetMapping("/Users")
    public ActionResult list() {
        List<UserAllModel> list = usersApi.getAll().getData();
        List<QYMessageUserListVO> listVO = new ArrayList<>();
        for (UserAllModel userAll : list) {
            QYMessageUserListVO user = new QYMessageUserListVO();
            user.setId(userAll.getId());
            user.setIcon("fa fa-user");
            user.setNickName(userAll.getRealName() + "/" + userAll.getAccount());
            listVO.add(user);
        }
        ListVO vo = new ListVO();
        vo.setList(listVO);
        return ActionResult.success(vo);
    }

    /**
     * 上传附件
     *
     * @return
     */
//    @PostMapping("/UploadFile")
//    public ActionResult UploadFile() {
//        MultipartFile file = UpUtil.getFileAll().get(0);
//        String fileType = UpUtil.getFileType(file);
//        if (!OptimizeUtil.fileType(configValueUtil.getWeChatUploadFileType(),fileType)) {
//            return ActionResult.fail("上传失败，文件格式不允许上传");
//        }
//        String filePath = configValueUtil.getTemporaryFilePath();
//        String fileName = DateUtil.dateNow("yyyyMMdd") + "_" + RandomUtil.uuId() + "." + fileType;
//        FileUtil.upFile(file, filePath, fileName);
//        return ActionResult.success("上传成功", fileName);
//    }
//
//    /**
//     * 上传封面
//     *
//     * @return
//     */
//    @PostMapping("/UploadCover")
//    public ActionResult UploadCover() {
//        MultipartFile file = UpUtil.getFileAll().get(0);
//        String fileType = UpUtil.getFileType(file);
//        String type = "jpg,jpeg,png";
//        if(!OptimizeUtil.imageType(type,fileType)){
//            return ActionResult.fail("上传失败，图片格式不正确");
//        }
//        if(OptimizeUtil.fileSize(file.getSize(),1024000)){
//            return ActionResult.fail("上传失败，图片大小超过1M");
//        }
//        String filePath = configValueUtil.getTemporaryFilePath();
//        String fileName = DateUtil.dateNow("yyyyMMdd") + "_" + RandomUtil.uuId() + "." + fileType;
//        FileUtil.upFile(file, filePath, fileName);
//        return ActionResult.success("上传成功", fileName);
//    }

    /**
     * 发送
     *
     * @param qyMessageSentForm 实体对象
     * @return
     */
    @ApiOperation("发送消息")
    @PostMapping
    public ActionResult sendGroupMessageByTagId(@RequestBody @Valid QYMessageSentForm qyMessageSentForm) throws WxErrorException, DataException {
        QYMessageEntity entity = JsonUtil.getJsonToBeanEx(qyMessageSentForm, QYMessageEntity.class);
        if (StringNumber.ZERO.equals(entity.getFAll()) && StringUtil.isEmpty(entity.getToUserId())) {
            return ActionResult.fail("发送范围必填");
        }
        switch (entity.getMsgType()) {
            case 1:
                if (StringUtil.isEmpty(entity.getTxtContent())) {
                    return ActionResult.fail("内容必填");
                }
                break;
            case 2:
                if (StringUtil.isEmpty(entity.getFileJson())) {
                    return ActionResult.fail("素材必填");
                }
                break;
            case 3:
                if (StringUtil.isEmpty(entity.getFileJson())) {
                    return ActionResult.fail("素材必填");
                }
                break;
            case 4:
                if (StringUtil.isEmpty(entity.getFileJson())) {
                    return ActionResult.fail("素材必填");
                }
                if (StringUtil.isEmpty(entity.getTitle())) {
                    return ActionResult.fail("标题必填");
                }
                if (StringUtil.isEmpty(entity.getTxtContent())) {
                    return ActionResult.fail("描述必填");
                }
                break;
            case 5:
                if (StringUtil.isEmpty(entity.getFileJson())) {
                    return ActionResult.fail("素材必填");
                }
                break;
            case 6:
                if (StringUtil.isEmpty(entity.getFileJson())) {
                    return ActionResult.fail("素材必填");
                }
                if (StringUtil.isEmpty(entity.getTitle())) {
                    return ActionResult.fail("标题必填");
                }
                if (StringUtil.isEmpty(entity.getContent())) {
                    return ActionResult.fail("内容必填");
                }
                break;
            default:
                return ActionResult.fail("没有此类型");
        }
        qyMessageService.sent(entity);
        return ActionResult.success("发送成功");
    }
}
