package io.github.cocodx.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_user
 */
public class UserDto implements Serializable {
    /**
     * 
     */
    @ExcelProperty("主键id")
    private Long id;

    /**
     * 
     */
    @ExcelProperty("用户名称")
    private String userName;

    /**
     * 
     */
    @ExcelProperty("密码")
    private String password;

    /**
     * 
     */
    @ExcelProperty("昵称")
    private String nickName;

    /**
     * 
     */
    @ExcelProperty("真实名称")
    private String trueName;

    /**
     * 
     */
    @ExcelProperty("地址")
    private String address;

    /**
     * 
     */
    @ExcelProperty("用户编号")
    private Integer idNumber;

    /**
     * 
     */
    @ExcelProperty("创建人")
    private Long createBy;

    /**
     * 
     */
    @ExcelProperty("修改人")
    private Long updateBy;

    /**
     * 
     */
    @ExcelProperty("创建时间")
    private Date createTime;

    /**
     * 
     */
    @ExcelProperty("更新时间")
    private Date updateTime;

    /**
     * 
     */
    @ExcelProperty("备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     */
    public Integer getIdNumber() {
        return idNumber;
    }

    /**
     * 
     */
    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserDto other = (UserDto) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getTrueName() == null ? other.getTrueName() == null : this.getTrueName().equals(other.getTrueName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getIdNumber() == null ? other.getIdNumber() == null : this.getIdNumber().equals(other.getIdNumber()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getTrueName() == null) ? 0 : getTrueName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIdNumber() == null) ? 0 : getIdNumber().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", trueName=").append(trueName);
        sb.append(", address=").append(address);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}