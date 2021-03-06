package com.kunlunsoft.model2;

import java.util.Date;

public class ProjectUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.projectId
     *
     * @mbg.generated
     */
    private String projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.userId
     *
     * @mbg.generated
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.createTime
     *
     * @mbg.generated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.editable
     *
     * @mbg.generated
     */
    private String editable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xiaoyaoji..project_user.commonlyUsed
     *
     * @mbg.generated
     */
    private String commonlyused;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.id
     *
     * @return the value of xiaoyaoji..project_user.id
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.id
     *
     * @param id the value for xiaoyaoji..project_user.id
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.projectId
     *
     * @return the value of xiaoyaoji..project_user.projectId
     * @mbg.generated
     */
    public String getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.projectId
     *
     * @param projectid the value for xiaoyaoji..project_user.projectId
     * @mbg.generated
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.userId
     *
     * @return the value of xiaoyaoji..project_user.userId
     * @mbg.generated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.userId
     *
     * @param userid the value for xiaoyaoji..project_user.userId
     * @mbg.generated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.createTime
     *
     * @return the value of xiaoyaoji..project_user.createTime
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.createTime
     *
     * @param createtime the value for xiaoyaoji..project_user.createTime
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.status
     *
     * @return the value of xiaoyaoji..project_user.status
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.status
     *
     * @param status the value for xiaoyaoji..project_user.status
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.editable
     *
     * @return the value of xiaoyaoji..project_user.editable
     * @mbg.generated
     */
    public String getEditable() {
        return editable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.editable
     *
     * @param editable the value for xiaoyaoji..project_user.editable
     * @mbg.generated
     */
    public void setEditable(String editable) {
        this.editable = editable == null ? null : editable.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xiaoyaoji..project_user.commonlyUsed
     *
     * @return the value of xiaoyaoji..project_user.commonlyUsed
     * @mbg.generated
     */
    public String getCommonlyused() {
        return commonlyused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xiaoyaoji..project_user.commonlyUsed
     *
     * @param commonlyused the value for xiaoyaoji..project_user.commonlyUsed
     * @mbg.generated
     */
    public void setCommonlyused(String commonlyused) {
        this.commonlyused = commonlyused == null ? null : commonlyused.trim();
    }
}