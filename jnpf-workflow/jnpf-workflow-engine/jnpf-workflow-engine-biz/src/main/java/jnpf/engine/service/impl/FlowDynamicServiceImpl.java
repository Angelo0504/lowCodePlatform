package jnpf.engine.service.impl;

import jnpf.base.BillRuleApi;
import jnpf.base.UserInfo;
import jnpf.base.model.FormDataModel;
import jnpf.engine.entity.FlowEngineEntity;
import jnpf.engine.entity.FlowTaskEntity;
import jnpf.engine.model.flowdynamic.FormAllModel;
import jnpf.engine.model.flowdynamic.FormEnum;
import jnpf.engine.model.flowtask.FlowTableModel;
import jnpf.engine.model.flowtask.FlowTaskInfoVO;
import jnpf.engine.service.FlowDynamicService;
import jnpf.engine.service.FlowEngineService;
import jnpf.engine.service.FlowTaskService;
import jnpf.engine.util.FlowDataUtil;
import jnpf.engine.util.FormCloumnUtil;
import jnpf.exception.DataException;
import jnpf.exception.WorkFlowException;
import jnpf.onlinedev.model.fields.FieLdsModel;
import jnpf.util.JsonUtil;
import jnpf.util.JsonUtilEx;
import jnpf.util.RandomUtil;
import jnpf.util.UserProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在线开发工作流
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021/3/15 9:19
 */
@Slf4j
@Service
public class FlowDynamicServiceImpl implements FlowDynamicService {

    @Autowired
    private BillRuleApi billRuleApi;
    @Autowired
    private FlowTaskService flowTaskService;
    @Autowired
    private FlowEngineService flowEngineService;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private FlowDataUtil flowDataUtil;

    @Override
    public FlowTaskInfoVO info(FlowTaskEntity entity) throws WorkFlowException, DataException, SQLException {
        FlowEngineEntity flowentity = flowEngineService.getInfo(entity.getFlowId());
        List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(flowentity.getTables(), FlowTableModel.class);
        FlowTaskInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, FlowTaskInfoVO.class);
        //formTempJson
        FormDataModel formData = JsonUtil.getJsonToBean(entity.getFlowForm(), FormDataModel.class);
        List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
        Map<String, Object> result = flowDataUtil.info(list, entity, tableModelList, false);
        vo.setData(JsonUtilEx.getObjectToString(result));
        return vo;
    }

    @Override
    public void save(String id, String flowId, String data) throws WorkFlowException, DataException, SQLException {
        FlowEngineEntity entity = flowEngineService.getInfo(flowId);
        UserInfo info = userProvider.get();
        String billNo = "单据规则不存在";
        String title = info.getUserName() + "的" + entity.getFullName();
        String formId = RandomUtil.uuId();
        //formTempJson
        FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
        List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
        List<FormAllModel> formAllModel = new ArrayList<>();
        FormCloumnUtil.recursionForm(list, formAllModel);
        List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getJnpfKey())).collect(Collectors.toList());
        //主表的单据数据
        Map<String, String> billData = new HashMap<>(16);
        boolean type = id != null;
        if (type) {
            formId = id;
        } else {
            FormAllModel formModel = mastForm.stream().filter(t -> "billRule".equals(t.getFormColumnModel().getFieLdsModel().getConfig().getJnpfKey())).findFirst().orElse(null);
            if (formModel != null) {
                FieLdsModel fieLdsModel = formModel.getFormColumnModel().getFieLdsModel();
                String ruleKey = fieLdsModel.getConfig().getRule();
                billNo = billRuleApi.getBillNumber(ruleKey).getData();
                billData.put(fieLdsModel.getVModel().toLowerCase(), billNo);
            } else {
                throw new WorkFlowException("未找到单据控件");
            }
        }
        //tableJson
        List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(entity.getTables(), FlowTableModel.class);
        //表单值
        Map<String, Object> dataMap = JsonUtil.stringToMap(data);
        Map<String, Object> result = new HashMap<>(16);
        if (type) {
            result = flowDataUtil.update(dataMap, list, tableModelList, formId);
        } else {
            result = flowDataUtil.create(dataMap, list, tableModelList, formId, billData);
        }
        String resultData = JsonUtilEx.getObjectToString(result);
        if (tableModelList.size() > 0) {
            flowTaskService.save(id, flowId, formId, title, 1, billNo);
        } else {
            //流程信息
            flowTaskService.save(id, flowId, formId, title, 1, billNo, resultData);
        }
    }

    @Override
    public void submit(String id, String flowId, String data, String freeUserId) throws WorkFlowException, DataException, SQLException {
        FlowEngineEntity entity = flowEngineService.getInfo(flowId);
        UserInfo info = userProvider.get();
        String billNo = "单据规则不存在";
        String title = info.getUserName() + "的" + entity.getFullName();
        String formId = RandomUtil.uuId();
        //formTempJson
        FormDataModel formData = JsonUtil.getJsonToBean(entity.getFormData(), FormDataModel.class);
        List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
        List<FormAllModel> formAllModel = new ArrayList<>();
        FormCloumnUtil.recursionForm(list, formAllModel);
        List<FormAllModel> mastForm = formAllModel.stream().filter(t -> FormEnum.mast.getMessage().equals(t.getJnpfKey())).collect(Collectors.toList());
        //主表的单据数据
        Map<String, String> billData = new HashMap<>(16);
        boolean type = id != null;
        if (type) {
            formId = id;
        } else {
            FormAllModel formModel = mastForm.stream().filter(t -> "billRule".equals(t.getFormColumnModel().getFieLdsModel().getConfig().getJnpfKey())).findFirst().orElse(null);
            if (formModel != null) {
                FieLdsModel fieLdsModel = formModel.getFormColumnModel().getFieLdsModel();
                String ruleKey = fieLdsModel.getConfig().getRule();
                billNo = billRuleApi.getBillNumber(ruleKey).getData();
                billData.put(fieLdsModel.getVModel().toLowerCase(), billNo);
            } else {
                throw new WorkFlowException("未找到单据控件");
            }
        }
        //tableJson
        List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(entity.getTables(), FlowTableModel.class);
        //表单值
        Map<String, Object> dataMap = JsonUtil.stringToMap(data);
        Map<String, Object> result = new HashMap<>(16);
        if (type) {
            result = flowDataUtil.update(dataMap, list, tableModelList, formId);
        } else {
            result = flowDataUtil.create(dataMap, list, tableModelList, formId, billData);
        }
        //流程信息
        flowTaskService.submit(id, flowId, formId, title, 1, billNo, result, freeUserId);
    }

    @Override
    public Map<String, Object> getData(String flowId, String id) throws WorkFlowException, SQLException, DataException {
        FlowTaskEntity entity = flowTaskService.getInfo(id);
        FlowEngineEntity flowentity = flowEngineService.getInfo(flowId);
        List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(flowentity.getTables(), FlowTableModel.class);
        //formTempJson
        FormDataModel formData = JsonUtil.getJsonToBean(entity.getFlowForm(), FormDataModel.class);
        List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
        Map<String, Object> resultData = flowDataUtil.info(list, entity, tableModelList, true);
        return resultData;
    }

}
