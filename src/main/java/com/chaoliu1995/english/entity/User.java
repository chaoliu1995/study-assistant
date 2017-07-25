package com.chaoliu1995.english.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chaoliu1995.english.util.Constants;

@Table(name="SYS_USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 用户名
	 */
	@Column(name="USER_NAME")
	private String userName;


	/**
	 * 用户状态【0启用、1停用】中文描述
	 */
	@Transient
	private String statusDesc;

	/**
	 * 角色ID
	 */
	@Transient
	private Long roleId;
	@Transient
	private String roleName;

	/**
	 * 密码
	 */
	@Column(name="PASSWORD")
	private String password;

	/**
	 * 手机号
	 */
	@Column(name="PHONE")
	private String phone;

	/**
	 * 邮箱
	 */
	@Column(name="EMAIL")
	private String email;

	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="REMARK")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
