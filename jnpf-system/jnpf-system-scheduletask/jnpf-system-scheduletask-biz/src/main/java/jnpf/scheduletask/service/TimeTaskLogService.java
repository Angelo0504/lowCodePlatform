package jnpf.scheduletask.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jnpf.scheduletask.entity.TimeTaskLogEntity;

import java.util.List;


/**
 * 执行记录
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月27日 上午9:18
 */
public interface TimeTaskLogService extends IService<TimeTaskLogEntity> {

    /**
     * 获取记录总数
     * @param taskId 任务id
     * @return
     */
    List<TimeTaskLogEntity> getTaskList(String taskId);

    /**
     * 获取记录总数
     * @param taskId
     * @return
     */
    List<TimeTaskLogEntity> getTaskList(List<String> taskId);


}
