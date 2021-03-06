package jnpf.onlinedev.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jnpf.base.UserInfo;
import jnpf.base.VisualdevEntity;
import jnpf.base.model.ColumnDataModel;
import jnpf.base.model.FormDataModel;
import jnpf.base.model.TableFields;
import jnpf.base.model.Template6.ColumnListField;
import jnpf.base.model.Template6.IndexGridField6Model;
import jnpf.base.service.*;
import jnpf.base.entity.DictionaryDataEntity;
import jnpf.base.entity.DictionaryTypeEntity;
import jnpf.base.entity.ProvinceEntity;
import jnpf.base.util.genUtil.custom.DynamicUtil;
import jnpf.base.util.genUtil.custom.VisualUtils;
import jnpf.onlinedev.model.*;
import jnpf.onlinedev.model.fields.FieLdsModel;
import jnpf.onlinedev.model.fields.config.ConfigModel;
import jnpf.onlinedev.model.fields.config.ConfigPropsModel;
import jnpf.onlinedev.model.fields.props.PropsBeanModel;
import jnpf.onlinedev.model.fields.slot.SlotModel;
import jnpf.onlinedev.util.*;
import jnpf.permission.entity.OrganizeEntity;
import jnpf.permission.entity.PositionEntity;
import jnpf.permission.entity.UserEntity;
import jnpf.onlinedev.entity.VisualdevModelDataEntity;
import jnpf.exception.DataException;
import jnpf.permission.model.user.UserAllModel;
import jnpf.onlinedev.mapper.VisualdevModelDataMapper;
import jnpf.onlinedev.service.VisualdevModelDataService;
import jnpf.permission.service.OrganizeService;
import jnpf.permission.service.PositionService;
import jnpf.permission.service.UserService;
import jnpf.util.*;
import jnpf.util.context.SpringContext;
import jnpf.util.visiual.DataTypeConst;
import jnpf.util.visiual.JnpfKeyConsts;
import lombok.Cleanup;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 0?????????????????????
 * ????????? V3.0.0
 * ????????? ??????????????????????????????
 * ????????? ?????????/admin
 * ????????? 2020-07-24 11:59
 */
@Service
public class VisualdevModelDataServiceImpl extends ServiceImpl<VisualdevModelDataMapper, VisualdevModelDataEntity> implements VisualdevModelDataService {

    @Autowired
    private UserProvider userProvider;


    @Override
    public List<VisualdevModelDataEntity> getList(String modelId) {
        QueryWrapper<VisualdevModelDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VisualdevModelDataEntity::getVisualDevId, modelId);
        return this.list(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getListResult(VisualdevEntity visualdevEntity, PaginationModel paginationModel) throws IOException, ParseException, DataException, SQLException {
        List<Map<String, Object>> realList = new ArrayList<>();
        List<VisualdevModelDataEntity> list = null;
        if (!StringUtil.isEmpty(visualdevEntity.getTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getTables())) {
            ColumnDataModel columnData = JsonUtil.getJsonToBean(visualdevEntity.getColumnData(), ColumnDataModel.class);
            List<ColumnListField> modelList = JsonUtil.getJsonToList(columnData.getColumnList(), ColumnListField.class);
            List<Map<String, Object>> mapList = JsonUtil.getJsonToListMap(visualdevEntity.getTables());
            String mainTable = mapList.get(0).get("table").toString();

            list = KeyDataUtil.getHasTableList(list, mainTable, modelList, columnData);
        } else {
            list = this.getList(visualdevEntity.getId());
        }

        //???????????????
        if (list.size() > 0) {
            if (StringUtil.isNotEmpty(paginationModel.getSidx())) {
                //????????????
                list.sort((o1, o2) -> {
                    Map<String, Object> i1=JsonUtil.stringToMap(o1.getData());
                    Map<String, Object> i2=JsonUtil.stringToMap(o2.getData());
                    String s1=String.valueOf(i1.get(paginationModel.getSidx()));
                    String s2=String.valueOf(i2.get(paginationModel.getSidx()));
                    if ("desc".equalsIgnoreCase(paginationModel.getSort())) {
                        return s2.compareTo(s1);
                    } else  {
                        return s1.compareTo(s2);
                    }
                });
            }
            //?????????????????????json??????map
            Map<String, Object> keyJsonMap = JsonUtil.stringToMap(paginationModel.getJson());
            //????????????
            realList = ListDataHandler.swapListData(keyJsonMap, visualdevEntity, list, realList);
            //????????????
            paginationModel.setTotal(realList.size());
            realList = PageUtil.getListPage((int) paginationModel.getCurrentPage(), (int) paginationModel.getPageSize(), realList);

            ColumnDataModel columnDataModel = JsonUtil.getJsonToBean(visualdevEntity.getColumnData(), ColumnDataModel.class);
            //????????????????????????
            if (OnlineDevData.TYPE_THREE_COLUMNDATA.equals(columnDataModel.getType())) {
                return KeyDataUtil.changeGroupDataList(columnDataModel, realList);
            }
            return realList;

        }
        return realList;
    }


    @Override
    public List<Map<String, Object>> exportData(String[] keys, PaginationModelExport paginationModelExport, VisualdevEntity visualdevEntity) throws IOException, ParseException, SQLException, DataException {
        List<VisualdevModelDataEntity> list = null;
        List<Map<String, Object>> realList = new ArrayList<>();
        if (!StringUtil.isEmpty(visualdevEntity.getTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getTables())) {
            ColumnDataModel columnData = JsonUtil.getJsonToBean(visualdevEntity.getColumnData(), ColumnDataModel.class);
            List<ColumnListField> modelList = JsonUtil.getJsonToList(columnData.getColumnList(), ColumnListField.class);
            List<Map<String, Object>> mapList = JsonUtil.getJsonToListMap(visualdevEntity.getTables());
            String mainTable = mapList.get(0).get("table").toString();
            list = KeyDataUtil.getHasTableList(list, mainTable, modelList, columnData);
        } else {
            list = this.getList(visualdevEntity.getId());
        }
        //??????????????????
        list = VisualUtils.deleteKey(list, keys);
        //???????????????
        if (list.size() > 0) {
            //?????????????????????json??????map
            Map<String, Object> keyJsonMap = JsonUtil.stringToMap(paginationModelExport.getJson());
            //????????????
            realList = ListDataHandler.swapListData(keyJsonMap, visualdevEntity, list, realList);
            //????????????
            if (!"1".equals(paginationModelExport.getDataType())) {
                paginationModelExport.setTotal(realList.size());
                realList = PageUtil.getListPage((int) paginationModelExport.getCurrentPage(), (int) paginationModelExport.getPageSize(), realList);
            }
            return realList;
        }
        return realList;
    }


    @Override
    public VisualdevModelDataEntity getInfo(String id) {
        QueryWrapper<VisualdevModelDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VisualdevModelDataEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    public VisualdevModelDataInfoVO infoDataChange(String id, VisualdevEntity visualdevEntity) throws IOException, ParseException, DataException, SQLException {
        Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());
        List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
        //????????????????????????
        modelList = VisualUtils.deleteMore(modelList);

        QueryWrapper<VisualdevModelDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VisualdevModelDataEntity::getId, id);
        VisualdevModelDataEntity visualdevModelDataEntity = this.getOne(queryWrapper);
        if (visualdevModelDataEntity != null) {
            Map<String, Object> newDataMap = JsonUtil.stringToMap(visualdevModelDataEntity.getData());
            Map<String, Object> newDataMapOperate = new HashMap<>(16);
            newDataMapOperate.putAll(newDataMap);
            Map<String, Object> newMainDataMap = new HashMap<>(16);
            for (Map.Entry<String, Object> entryMap : newDataMapOperate.entrySet()) {
                String key = entryMap.getKey();
                if (!key.contains("table")) {
                    newMainDataMap.put(key, entryMap.getValue());
                    newDataMap.remove(key);
                }
            }
            //??????????????????
            newDataMap.put("main", JSON.toJSONString(newMainDataMap));
            int t = 0;
            //???????????????????????????
            for (FieLdsModel fieLdsModel : modelList) {
                for (Map.Entry<String, Object> entryMap : newDataMap.entrySet()) {
                    //?????????????????????
                    if (entryMap.getKey().equals(fieLdsModel.getVModel()) || "main".equals(entryMap.getKey())) {
                        List<VisualdevModelDataEntity> list = new ArrayList<>();
                        //?????????????????????json??????map
                        Map<String, Object> keyJsonMap = new HashMap<>(16);
                        List<FieLdsModel> formDatas;
                        if ("main".equals(entryMap.getKey()) && t == 0) {
                            formDatas = modelList;
                            VisualdevModelDataEntity entity = new VisualdevModelDataEntity();
                            entity.setData(entryMap.getValue().toString());
                            list.add(entity);
                            t++;
                            //???????????????????????????id???????????????
                            Map<String, Object> keyAndList = KeyDataUtil.getKeyData(formDatas, keyJsonMap, list, visualdevEntity.getId());

                            //?????????????????????????????????
                            keyAndList = ListDataHandler.getRealData(formDatas, keyJsonMap, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), VisualdevModelDataEntity.class));
                            //??????????????????????????????
                            list = VisualUtils.stringToList(formDatas, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), VisualdevModelDataEntity.class));
                            //??????????????????
                            list = AutoFeildsUtil.autoFeildsList(formDatas, list);
                            entryMap.setValue(list);
                        } else if (entryMap.getKey().equals(fieLdsModel.getVModel())) {
                            formDatas = JsonUtil.getJsonToList(fieLdsModel.getConfig().getChildren(), FieLdsModel.class);
                            List<Map<String, Object>> childMapList = (List<Map<String, Object>>) entryMap.getValue();
                            for (Map<String, Object> objectMap : childMapList) {
                                VisualdevModelDataEntity entity = new VisualdevModelDataEntity();
                                entity.setData(JsonUtilEx.getObjectToString(objectMap));
                                list.add(entity);
                            }
                            //???????????????????????????id???????????????
                            Map<String, Object> keyAndList = KeyDataUtil.getKeyData(formDatas, keyJsonMap, list, visualdevEntity.getId());

                            //?????????????????????????????????
                            keyAndList = ListDataHandler.getRealData(formDatas, keyJsonMap, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), VisualdevModelDataEntity.class));
                            //??????????????????????????????
                            list = VisualUtils.stringToList(formDatas, JsonUtil.getJsonToList(keyAndList.get(DataTypeConst.LIST), VisualdevModelDataEntity.class));
                            //??????????????????
                            list = AutoFeildsUtil.autoFeildsList(formDatas, list);
                            entryMap.setValue(list);
                        }
                    }
                }
            }

            List<VisualdevModelDataEntity> mainList = (List<VisualdevModelDataEntity>) newDataMap.get("main");

            Map<String, Object> realDataMap = JsonUtil.stringToMap(mainList.get(0).getData());
            newDataMap.remove("main");

            for (Map.Entry<String, Object> entryMap : newDataMap.entrySet()) {
                List<VisualdevModelDataEntity> childList = (List<VisualdevModelDataEntity>) entryMap.getValue();
                List<String> array = new ArrayList<>();
                for (VisualdevModelDataEntity childEntity : childList) {
                    array.add(childEntity.getData());
                }
                entryMap.setValue(array);
            }
            realDataMap.putAll(newDataMap);
            VisualdevModelDataInfoVO vo = new VisualdevModelDataInfoVO();
            String tmp1 = StringEscapeUtils.unescapeJavaScript(JsonUtilEx.getObjectToString(realDataMap));
            tmp1 = tmp1.replace("\"{", "{");
            tmp1 = tmp1.replace("}\"", "}");
            tmp1 = tmp1.replace("\"[", "[");
            tmp1 = tmp1.replace("]\"", "]");
            vo.setData(tmp1);
            vo.setId(visualdevModelDataEntity.getId());
            return vo;
        }
        return null;
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????Json???????????????
     *
     * @param id
     * @param visualdevEntity
     * @return
     * @throws DataException
     * @throws ParseException
     */
    @Override
    public VisualdevModelDataInfoVO tableInfo(String id, VisualdevEntity visualdevEntity) throws DataException, SQLException, ParseException, IOException {
        Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());
        List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);

        //????????????????????????
        modelList = VisualUtils.deleteMore(modelList);

        List<Map<String, Object>> tableMapList = JsonUtil.getJsonToListMap(visualdevEntity.getTables());
        String mainTable = tableMapList.get(0).get("table").toString();
        @Cleanup Connection conn = VisualUtils.getTableConn();
        //????????????
        String pKeyName = VisualUtils.getpKey(conn, mainTable);

        StringBuilder mainfeild = new StringBuilder();
        String main = "select * from" + " " + tableMapList.get(0).get("table") + " where " + pKeyName + "='" + id + "'";
        List<Map<String, Object>> mainMap = VisualUtils.getTableDataInfo(main);
        return TableInfoUtil.getTableInfo(pKeyName,mainfeild,modelList,mainMap,tableMapList);
    }


    /**
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????Json???????????????(??????????????????)
     *
     * @param id
     * @param visualdevEntity
     * @return
     * @throws DataException
     * @throws ParseException
     */
    @Override
    public VisualdevModelDataInfoVO tableInfoDataChange(String id, VisualdevEntity visualdevEntity) throws DataException, IOException, SQLException, ParseException {
        Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());

        List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
        //????????????????????????
        modelList = VisualUtils.deleteMore(modelList);
        List<Map<String, Object>> tableMapList = JsonUtil.getJsonToListMap(visualdevEntity.getTables());
        String mainTable = tableMapList.get(0).get("table").toString();

        return TableInfoUtil.getTableInfoDataChange(visualdevEntity.getId(),modelList,tableMapList,mainTable);
    }


    /**
     * ?????????????????????????????????????????????????????????????????????
     *
     * @param visualdevEntity
     * @param visualdevModelDataCrForm
     * @throws DataException
     */
    @Override
    public void create(VisualdevEntity visualdevEntity, VisualdevModelDataCrForm visualdevModelDataCrForm) throws DataException, SQLException {
        Map<String, Object> allDataMap = JsonUtil.stringToMap(visualdevModelDataCrForm.getData());
        List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(JsonUtil.stringToMap(visualdevEntity.getFormData()).get("fields"), FieLdsModel.class);
        //????????????????????????
        fieLdsModelList = VisualUtils.deleteMore(fieLdsModelList);

        //?????????????????????
        fieLdsModelList = VisualUtils.deleteVmodel(fieLdsModelList);

        //??????????????????????????????
        allDataMap = AutoFeildsUtil.createFeilds(fieLdsModelList, allDataMap);

        Map<String, Object> newMainDataMap = JsonUtil.stringToMap(visualdevModelDataCrForm.getData());


        if (!StringUtil.isEmpty(visualdevEntity.getTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getTables())) {
            OnlineDevDbUtil.insertTable(visualdevEntity, fieLdsModelList, allDataMap, newMainDataMap);
        } else {
            VisualdevModelDataEntity entity = new VisualdevModelDataEntity();
            entity.setData(JsonUtilEx.getObjectToString(allDataMap));
            entity.setVisualDevId(visualdevEntity.getId());

            entity.setId(RandomUtil.uuId());
            entity.setSortcode(RandomUtil.parses());
            entity.setCreatortime(new Date());
            entity.setCreatoruserid(userProvider.get().getUserId());
            entity.setEnabledmark(1);
            this.save(entity);
        }
    }

    @Override
    public boolean update(String id, VisualdevEntity visualdevEntity, VisualdevModelDataUpForm visualdevModelDataUpForm) throws DataException, SQLException {

        Map<String, Object> allDataMap = JsonUtil.stringToMap(visualdevModelDataUpForm.getData());
        List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(JsonUtil.stringToMap(visualdevEntity.getFormData()).get("fields"), FieLdsModel.class);
        //????????????????????????
        fieLdsModelList = VisualUtils.deleteMore(fieLdsModelList);
        //?????????????????????
        fieLdsModelList = VisualUtils.deleteVmodel(fieLdsModelList);

        //??????????????????????????????
        allDataMap = AutoFeildsUtil.updateFeilds(fieLdsModelList, allDataMap);

        Map<String, Object> newMainDataMap = JsonUtil.stringToMap(visualdevModelDataUpForm.getData());

        if (!StringUtil.isEmpty(visualdevEntity.getTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getTables())) {
            return OnlineDevDbUtil.updateTable(id, visualdevEntity, fieLdsModelList, allDataMap, newMainDataMap);
        }

        VisualdevModelDataEntity entity = new VisualdevModelDataEntity();
        entity.setData(JsonUtilEx.getObjectToString(allDataMap));
        entity.setVisualDevId(visualdevEntity.getId());
        entity.setId(id);
        entity.setLastmodifytime(new Date());
        entity.setLastmodifyuserid(userProvider.get().getUserId());
        return this.updateById(entity);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(VisualdevModelDataEntity entity) {
        if (entity != null) {
            this.removeById(entity.getId());
        }
    }

    @Override
    public boolean tableDelete(String id, VisualdevEntity visualdevEntity) throws DataException, SQLException {

        return OnlineDevDbUtil.deleteTable(id, visualdevEntity);
    }

    @Override
    public boolean tableDeleteMore(String ids, VisualdevEntity visualdevEntity) throws DataException, SQLException {

        return OnlineDevDbUtil.deleteTables(ids, visualdevEntity);
    }


    @Override
    public void importData(List<VisualdevModelDataEntity> list) {

    }

}
