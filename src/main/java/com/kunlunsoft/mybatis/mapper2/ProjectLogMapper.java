package com.kunlunsoft.mybatis.mapper2;

import com.kunlunsoft.model2.ProjectLog;

import java.util.List;

public interface ProjectLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..project_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..project_log
     *
     * @mbg.generated
     */
    int insert(ProjectLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..project_log
     *
     * @mbg.generated
     */
    ProjectLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..project_log
     *
     * @mbg.generated
     */
    List<ProjectLog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..project_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ProjectLog record);
}