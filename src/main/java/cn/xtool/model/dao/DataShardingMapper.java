package cn.xtool.model.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface DataShardingMapper {
    @Select("select count(*) from table_class where relname =  #{table}")
    Integer getTablesNumByName(@Param("table") String table);

    @Update("CREATE TABLE ${newTable}  LIKE ${originalTable} ")
    void copyTables(@Param("originalTable") String originalTable, @Param("newTable") String newTable);

    @Insert("INSERT INTO table_class (`relname`) VALUES (#{table}})")
    void saveRealTableName(@Param("table") String table);
}