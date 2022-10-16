package com.tuituidan.openhub.service;

import com.tuituidan.openhub.mapper.AttachFileMapper;
import com.tuituidan.openhub.pojo.entity.AttachFile;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * AttachFileService.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/15
 */
@Service
public class AttachFileService {

    @Resource
    private AttachFileMapper attachFileMapper;

    /**
     * selectByBelongId
     *
     * @param belongId belongId
     * @return List
     */
    public List<AttachFile> selectByBelongId(String belongId) {
        return attachFileMapper.select(new AttachFile().setBelongId(belongId));
    }

    /**
     * saveAttachFiles
     *
     * @param belongId 所属ID
     * @param fileIds 附件ID
     */
    public void saveAttachFiles(String belongId, String[] fileIds) {
        List<AttachFile> exists = attachFileMapper.select(new AttachFile().setBelongId(belongId));
        if (CollectionUtils.isEmpty(exists)) {
            if (ArrayUtils.isEmpty(fileIds)) {
                return;
            }
            Arrays.stream(fileIds).map(fileId -> new AttachFile().setId(fileId).setBelongId(belongId))
                    .forEach(fileItem -> attachFileMapper.updateByPrimaryKeySelective(fileItem));
            return;
        }
        Set<String> existIds = exists.stream().map(AttachFile::getId).collect(Collectors.toSet());
        Set<String> saveIds = ArrayUtils.isEmpty(fileIds)
                ? new HashSet<>() : Arrays.stream(fileIds).collect(Collectors.toSet());
        Set<String> deleteIds = new HashSet<>(existIds);
        // 已经存在的移除掉需要保存的剩下的就是需要删除的
        deleteIds.removeAll(saveIds);
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            attachFileMapper.deleteByIds("'" + StringUtils.join(deleteIds, "','") + "'");
        }
        // 需要保存移除掉已经存在的就是新增的
        saveIds.removeAll(existIds);
        if (CollectionUtils.isNotEmpty(saveIds)) {
            saveIds.stream().map(fileId -> new AttachFile().setId(fileId).setBelongId(belongId))
                    .forEach(fileItem -> attachFileMapper.updateByPrimaryKeySelective(fileItem));
        }
    }

}
