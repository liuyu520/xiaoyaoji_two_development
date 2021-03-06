package com.kunlunsoft.mybatis.mapper2;

import com.kunlunsoft.model2.InterfaceFolder;

import java.util.List;

public interface InterfaceFolderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..interface_folder
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..interface_folder
     *
     * @mbg.generated
     */
    int insert(InterfaceFolder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..interface_folder
     *
     * @mbg.generated
     */
    InterfaceFolder selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..interface_folder
     *
     * @mbg.generated
     */
    List<InterfaceFolder> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..interface_folder
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InterfaceFolder record);
}