package com.kunlunsoft.mybatis.mapper2;

import com.kunlunsoft.model2.EmailToken;

import java.util.List;

public interface EmailTokenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..email_token
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..email_token
     *
     * @mbg.generated
     */
    int insert(EmailToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..email_token
     *
     * @mbg.generated
     */
    EmailToken selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..email_token
     *
     * @mbg.generated
     */
    List<EmailToken> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xiaoyaoji..email_token
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EmailToken record);
}