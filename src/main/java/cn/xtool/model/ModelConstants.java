package cn.xtool.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.Optional;
 
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ModelConstants {
 
    /**
     * message_id_mapping 逻辑表名
     */
    public static final String MESSAGE_ID_MAPPING = "park_dept";
 
    /**
     * message_id_mapping 分表前缀
     */
    public static final String MESSAGE_ID_MAPPING_PREFIX = "park_dept_";
 
    /**
     * message_id_mapping 单分片表数量
     */
    public static final Long MESSAGE_ID_MAPPING_SINGLE_TABLE_CAPACITY = 20000000L;
 
}