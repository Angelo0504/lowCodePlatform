package jnpf.onlinedev.util;

import jnpf.base.UserInfo;
import jnpf.base.service.BillRuleService;
import jnpf.exception.DataException;
import jnpf.onlinedev.entity.VisualdevModelDataEntity;
import jnpf.onlinedev.model.fields.FieLdsModel;
import jnpf.permission.entity.OrganizeEntity;
import jnpf.permission.entity.PositionEntity;
import jnpf.permission.entity.UserEntity;
import jnpf.permission.model.user.UserAllModel;
import jnpf.permission.service.OrganizeService;
import jnpf.permission.service.PositionService;
import jnpf.permission.service.UserService;
import jnpf.util.*;
import jnpf.util.context.SpringContext;
import jnpf.util.visiual.JnpfKeyConsts;

import java.util.List;
import java.util.Map;

/**
 * 处理自动生成字段
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2021-03-15
 */
public class AutoFeildsUtil {
    private static OrganizeService organizeService;
    private static BillRuleService billRuleService;
    private static UserService userService;
    private static UserProvider userProvider;
    private static PositionService positionService;


    //初始化

    public static void init() {
        billRuleService = SpringContext.getBean(BillRuleService.class);
        userService = SpringContext.getBean(UserService.class);
        userProvider = SpringContext.getBean(UserProvider.class);
        organizeService = SpringContext.getBean(OrganizeService.class);
        positionService=SpringContext.getBean(PositionService.class);
    }


    /**
     * 生成系统自动生成字段
     *
     * @return Map<String, Object>
     */
    public static Map<String, Object> createFeilds(List<FieLdsModel> fieLdsModelList, Map<String, Object> allDataMap) throws DataException {
        init();
        //模型循环
        for (FieLdsModel fieLdsModel : fieLdsModelList) {
            if ("table".equals(fieLdsModel.getConfig().getJnpfKey()) && allDataMap.get(fieLdsModel.getVModel()) != null) {
                List<FieLdsModel> childFieLdsModelList = fieLdsModel.getConfig().getChildren();
                Object object = allDataMap.get(fieLdsModel.getVModel());
                if (object instanceof List) {
                    List<Map<String, Object>> childAllDataMapList = JsonUtil.getJsonToListMap(JsonUtilEx.getObjectToString(object));
                    for (Map<String, Object> childmap : childAllDataMapList) {
                        for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
                            //数据循环
                            for (Map.Entry<String, Object> data : childmap.entrySet()) {
                                UserInfo userInfo = userProvider.get();
                                if (childFieLdsModel.getVModel().equals(data.getKey())) {
                                    String jnpfKeyType = childFieLdsModel.getConfig().getJnpfKey();
                                    switch (jnpfKeyType) {
                                        case JnpfKeyConsts.BILLRULE:
                                            String billNumber = billRuleService.getBillNumber(childFieLdsModel.getConfig().getRule(), false);
                                            if (!"单据规则不存在".equals(billNumber)) {
                                                data.setValue(billNumber);
                                            } else {
                                                data.setValue("");
                                            }
                                            break;
                                        case JnpfKeyConsts.CREATEUSER:
                                            data.setValue(userInfo.getUserId());
                                            break;
                                        case JnpfKeyConsts.CREATETIME:
                                            data.setValue(DateUtil.getNow("+8"));
                                            break;
                                        case JnpfKeyConsts.MODIFYUSER:
                                        case JnpfKeyConsts.MODIFYTIME:
                                            data.setValue("");
                                            break;
                                        case JnpfKeyConsts.CURRORGANIZE:
                                            if (StringUtil.isNotEmpty(userInfo.getOrganizeId())) {
                                                data.setValue(userInfo.getOrganizeId());
                                            } else {
                                                data.setValue("");
                                            }
                                            break;
                                        case JnpfKeyConsts.CURRDEPT:
                                            if (StringUtil.isNotEmpty(userInfo.getDepartmentId())) {
                                                data.setValue(userInfo.getDepartmentId());
                                            } else {
                                                data.setValue("");
                                            }
                                            break;
                                        case JnpfKeyConsts.CURRPOSITION:
                                            UserEntity userEntity = userService.getInfo(userInfo.getUserId());
                                            if (StringUtil.isNotEmpty(userEntity.getPositionId())) {
                                                PositionEntity positionEntity = positionService.getInfo(userEntity.getPositionId().split(",")[0]);
                                                if (positionEntity != null) {
                                                    data.setValue(positionEntity.getId());
                                                } else {
                                                    data.setValue("");
                                                }
                                            }
                                            break;
                                        default:
                                    }
                                }
                            }
                        }
                        allDataMap.put(fieLdsModel.getVModel(), childAllDataMapList);
                    }
                } else {
                    Map<String, Object> childAllDataMap = JsonUtil.stringToMap(JsonUtilEx.getObjectToString(object));
                    for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
                        //数据循环
                        for (Map.Entry<String, Object> data : childAllDataMap.entrySet()) {
                            UserInfo userInfo = userProvider.get();
                            if (childFieLdsModel.getVModel().equals(data.getKey())) {
                                String jnpfKeyType = childFieLdsModel.getConfig().getJnpfKey();
                                switch (jnpfKeyType) {
                                    case JnpfKeyConsts.BILLRULE:
                                        String billNumber = billRuleService.getBillNumber(childFieLdsModel.getConfig().getRule(), false);
                                        if (!"单据规则不存在".equals(billNumber)) {
                                            data.setValue(billNumber);
                                        } else {
                                            data.setValue("");
                                        }
                                        break;
                                    case JnpfKeyConsts.CREATEUSER:
                                        data.setValue(userInfo.getUserId());
                                        break;
                                    case JnpfKeyConsts.CREATETIME:
                                        data.setValue(DateUtil.getNow("+8"));
                                        break;
                                    case JnpfKeyConsts.MODIFYUSER:
                                    case JnpfKeyConsts.MODIFYTIME:
                                        data.setValue("");
                                        break;
                                    case JnpfKeyConsts.CURRORGANIZE:
                                        if (StringUtil.isNotEmpty(userInfo.getOrganizeId())) {
                                            data.setValue(userInfo.getOrganizeId());
                                        } else {
                                            data.setValue("");
                                        }
                                        break;
                                    case JnpfKeyConsts.CURRDEPT:
                                        if (StringUtil.isNotEmpty(userInfo.getDepartmentId())) {
                                            data.setValue(userInfo.getDepartmentId());
                                        } else {
                                            data.setValue("");
                                        }
                                        break;
                                    case JnpfKeyConsts.CURRPOSITION:
                                        UserEntity userEntity = userService.getInfo(userInfo.getUserId());
                                        if (StringUtil.isNotEmpty(userEntity.getPositionId())) {
                                            PositionEntity positionEntity = positionService.getInfo(userEntity.getPositionId().split(",")[0]);
                                            if (positionEntity != null) {
                                                data.setValue(positionEntity.getId());
                                            } else {
                                                data.setValue("");
                                            }
                                        }
                                        break;
                                    default:
                                }
                            }
                        }
                    }
                    allDataMap.put(fieLdsModel.getVModel(), childAllDataMap);
                }

            }
            //数据循环
            for (Map.Entry<String, Object> data : allDataMap.entrySet()) {
                UserInfo userInfo = userProvider.get();
                if (fieLdsModel.getVModel().equals(data.getKey())) {
                    String jnpfKeyType = fieLdsModel.getConfig().getJnpfKey();
                    switch (jnpfKeyType) {
                        case JnpfKeyConsts.BILLRULE:
                            String billNumber = billRuleService.getBillNumber(fieLdsModel.getConfig().getRule(), false);
                            if (!"单据规则不存在".equals(billNumber)) {
                                data.setValue(billNumber);
                            } else {
                                data.setValue("");
                            }
                            break;
                        case JnpfKeyConsts.CREATEUSER:
                            data.setValue(userInfo.getUserId());
                            break;
                        case JnpfKeyConsts.CREATETIME:
                            data.setValue(DateUtil.getNow("+8"));
                            break;
                        case JnpfKeyConsts.MODIFYUSER:
                        case JnpfKeyConsts.MODIFYTIME:
                            data.setValue("");
                            break;
                        case JnpfKeyConsts.CURRORGANIZE:
                            if (StringUtil.isNotEmpty(userInfo.getOrganizeId())) {
                                data.setValue(userInfo.getOrganizeId());
                            } else {
                                data.setValue("");
                            }
                            break;
                        case JnpfKeyConsts.CURRDEPT:
                            if (StringUtil.isNotEmpty(userInfo.getDepartmentId())) {
                                data.setValue(userInfo.getDepartmentId());
                            } else {
                                data.setValue("");
                            }
                            break;
                        case JnpfKeyConsts.CURRPOSITION:
                            UserEntity userEntity = userService.getInfo(userInfo.getUserId());
                            if (StringUtil.isNotEmpty(userEntity.getPositionId())) {
                                PositionEntity positionEntity = positionService.getInfo(userEntity.getPositionId().split(",")[0]);
                                if (positionEntity != null) {
                                    data.setValue(positionEntity.getId());
                                } else {
                                    data.setValue("");
                                }
                            }
                            break;
                        default:
                    }
                }
            }

        }
        return allDataMap;
    }

    /**
     * 生成系统自动生成字段
     *
     * @return Map<String, Object>
     */
    public static Map<String, Object> updateFeilds(List<FieLdsModel> fieLdsModelList, Map<String, Object> allDataMap) {
        init();
        //模型循环
        for (FieLdsModel fieLdsModel : fieLdsModelList) {
            if ("table".equals(fieLdsModel.getConfig().getJnpfKey()) && allDataMap.get(fieLdsModel.getVModel()) != null) {
                List<FieLdsModel> childFieLdsModelList = fieLdsModel.getConfig().getChildren();
                Object object = allDataMap.get(fieLdsModel.getVModel());
                if (object instanceof List) {
                    List<Map<String, Object>> childAllDataMapList = JsonUtil.getJsonToListMap(JsonUtilEx.getObjectToString(object));
                    for (Map<String, Object> childmap : childAllDataMapList) {
                        for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
                            //数据循环
                            for (Map.Entry<String, Object> data : childmap.entrySet()) {
                                UserInfo userInfo = userProvider.get();
                                if (childFieLdsModel.getVModel().equals(data.getKey())) {
                                    String jnpfKeyType = childFieLdsModel.getConfig().getJnpfKey();
                                    switch (jnpfKeyType) {
                                        case JnpfKeyConsts.MODIFYUSER:
                                            data.setValue(userInfo.getUserId());
                                            break;
                                        case JnpfKeyConsts.MODIFYTIME:
                                            data.setValue(DateUtil.getNow("+8"));
                                            break;
                                        case JnpfKeyConsts.CURRORGANIZE:
                                            if (StringUtil.isNotEmpty(userInfo.getOrganizeId())) {
                                                data.setValue(userInfo.getOrganizeId());
                                            } else {
                                                data.setValue("");
                                            }
                                            break;
                                        case JnpfKeyConsts.CURRDEPT:
                                            if (StringUtil.isNotEmpty(userInfo.getDepartmentId())) {
                                                data.setValue(userInfo.getDepartmentId());
                                            } else {
                                                data.setValue("");
                                            }
                                            break;
                                        case JnpfKeyConsts.CURRPOSITION:
                                            PositionEntity positionEntity = positionService.getInfo(userInfo.getPositionIds()[0]);
                                            if (positionEntity != null) {
                                                data.setValue(positionEntity.getId());
                                            } else {
                                                data.setValue("");
                                            }
                                            break;
                                        default:
                                    }
                                }
                            }
                        }
                        allDataMap.put(fieLdsModel.getVModel(), childAllDataMapList);
                    }
                } else {
                    Map<String, Object> childAllDataMap = JsonUtil.stringToMap(JsonUtilEx.getObjectToString(object));
                    for (FieLdsModel childFieLdsModel : childFieLdsModelList) {
                        //数据循环
                        for (Map.Entry<String, Object> data : childAllDataMap.entrySet()) {
                            UserInfo userInfo = userProvider.get();
                            if (childFieLdsModel.getVModel().equals(data.getKey())) {
                                String jnpfKeyType = childFieLdsModel.getConfig().getJnpfKey();
                                switch (jnpfKeyType) {
                                    case JnpfKeyConsts.MODIFYUSER:
                                        data.setValue(userInfo.getUserId());
                                        break;
                                    case JnpfKeyConsts.MODIFYTIME:
                                        data.setValue(DateUtil.getNow("+8"));
                                        break;
                                    case JnpfKeyConsts.CURRORGANIZE:
                                        if (StringUtil.isNotEmpty(userInfo.getOrganizeId())) {
                                            data.setValue(userInfo.getOrganizeId());
                                        } else {
                                            data.setValue("");
                                        }
                                        break;
                                    case JnpfKeyConsts.CURRDEPT:
                                        if (StringUtil.isNotEmpty(userInfo.getDepartmentId())) {
                                            data.setValue(userInfo.getDepartmentId());
                                        } else {
                                            data.setValue("");
                                        }
                                        break;
                                    case JnpfKeyConsts.CURRPOSITION:
                                        if (userInfo.getPositionIds().length > 0) {
                                            PositionEntity positionEntity = positionService.getInfo(userInfo.getPositionIds()[0]);
                                            if (positionEntity != null) {
                                                data.setValue(positionEntity.getId());
                                            } else {
                                                data.setValue("");
                                            }
                                        }
                                        break;
                                    default:
                                }
                            }
                        }
                    }
                    allDataMap.put(fieLdsModel.getVModel(), childAllDataMap);
                }

            }
            //数据循环
            for (Map.Entry<String, Object> data : allDataMap.entrySet()) {
                UserInfo userInfo = userProvider.get();
                if (data.getKey().contains(fieLdsModel.getVModel())) {
                    String jnpfKeyType = fieLdsModel.getConfig().getJnpfKey();
                    switch (jnpfKeyType) {
                        case JnpfKeyConsts.MODIFYUSER:
                            data.setValue(userInfo.getUserId());
                            break;
                        case JnpfKeyConsts.MODIFYTIME:
                            data.setValue(DateUtil.getNow("+8"));
                            break;
                        case JnpfKeyConsts.CURRORGANIZE:
                            if (StringUtil.isNotEmpty(userInfo.getOrganizeId())) {
                                data.setValue(userInfo.getOrganizeId());
                            } else {
                                data.setValue("");
                            }
                            break;
                        case JnpfKeyConsts.CURRDEPT:
                            if (StringUtil.isNotEmpty(userInfo.getDepartmentId())) {
                                data.setValue(userInfo.getDepartmentId());
                            } else {
                                data.setValue("");
                            }
                            break;
                        case JnpfKeyConsts.CURRPOSITION:
                            if (userInfo.getPositionIds() != null && userInfo.getPositionIds().length > 0) {
                                PositionEntity positionEntity = positionService.getInfo(userInfo.getPositionIds()[0]);
                                if (positionEntity != null) {
                                    data.setValue(positionEntity.getId());
                                } else {
                                    data.setValue("");
                                }
                            }
                            break;
                        default:
                    }
                }
            }

        }
        return allDataMap;
    }

    /**
     * 列表系统自动生成字段转换
     *
     * @return List<VisualdevModelDataEntity>
     */
    public static List<VisualdevModelDataEntity> autoFeildsList(List<FieLdsModel> fieLdsModelList, List<VisualdevModelDataEntity> list) {
        init();
        List<OrganizeEntity> orgMapList = organizeService.getOrgRedisList();
        List<UserAllModel> allUser = userService.getAll();
        List<PositionEntity> mapList = positionService.getPosRedisList();
        for (FieLdsModel fieLdsModel : fieLdsModelList) {
            for (VisualdevModelDataEntity entity : list) {
                Map<String, Object> dataMap = JsonUtil.stringToMap(entity.getData());
                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    if (fieLdsModel.getVModel().equals(entry.getKey())) {
                        String jnpfKeyType = fieLdsModel.getConfig().getJnpfKey();
                        switch (jnpfKeyType) {
                            case JnpfKeyConsts.CURRORGANIZE:
                            case JnpfKeyConsts.CURRDEPT:
                                if (StringUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
                                    OrganizeEntity organizeEntity = orgMapList.stream().filter(t -> t.getId().equals(String.valueOf(entry.getValue()))).findFirst().orElse(null);
                                    if (organizeEntity != null) {
                                        entry.setValue(organizeEntity.getFullName());
                                    }
                                }
                                break;
                            case JnpfKeyConsts.CREATEUSER:
                            case JnpfKeyConsts.MODIFYUSER:
                                if (StringUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
                                    UserAllModel userAllModel = allUser.stream().filter(t -> t.getId().equals(String.valueOf(entry.getValue()))).findFirst().orElse(null);
                                    if (userAllModel != null) {
                                        entry.setValue(userAllModel.getRealName());
                                    }
                                }
                                break;
                            case JnpfKeyConsts.CURRPOSITION:
                                if (StringUtil.isNotEmpty(String.valueOf(entry.getValue()))) {
                                    PositionEntity positionEntity = mapList.stream().filter(t -> t.getId().equals(String.valueOf(entry.getValue()))).findFirst().orElse(null);
                                    if (positionEntity != null) {
                                        entry.setValue(positionEntity.getFullName());
                                    }
                                }
                                break;
                            default:
                        }
                    }
                }
                entity.setData(JsonUtilEx.getObjectToString(dataMap));
            }
        }
        return list;
    }

    /**
     * 列表系统自动生成字段转换
     *
     * @return String
     */
    public static String autoFeilds(List<FieLdsModel> fieLdsModelList, String data) {
        init();
        for (FieLdsModel fieLdsModel : fieLdsModelList) {
            Map<String, Object> dataMap = JsonUtil.stringToMap(data);
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                if (fieLdsModel.getVModel().equals(entry.getKey())) {
                    String jnpfKeyType = fieLdsModel.getConfig().getJnpfKey();
                    switch (jnpfKeyType) {
                        case JnpfKeyConsts.CURRORGANIZE:
                        case JnpfKeyConsts.CURRDEPT:
                            List<OrganizeEntity> orgMapList = organizeService.getOrgRedisList();
                            for (OrganizeEntity organizeEntity : orgMapList) {
                                if (String.valueOf(entry.getValue()).equals(organizeEntity.getId())) {
                                    entry.setValue(organizeEntity.getFullName());
                                }
                            }
                            break;
                        case JnpfKeyConsts.CREATEUSER:
                        case JnpfKeyConsts.MODIFYUSER:
                            UserEntity userCreEntity = userService.getInfo(String.valueOf(entry.getValue()));
                            if (userCreEntity != null) {
                                entry.setValue(userCreEntity.getRealName());
                            }
                            break;
                        case JnpfKeyConsts.CURRPOSITION:
                            List<PositionEntity> mapList = positionService.getPosRedisList();
                            for (PositionEntity positionEntity : mapList) {
                                if (String.valueOf(entry.getValue()).equals(positionEntity.getId())) {
                                    entry.setValue(positionEntity.getFullName());
                                }
                            }
                            break;
                        default:
                    }
                }
            }
            data = JsonUtilEx.getObjectToString(dataMap);

        }
        data = data.replaceAll("\"\\[", "\\[");
        data = data.replaceAll("\\]\"", "\\]");
        return data.replaceAll("\\\\", "");
    }


}
