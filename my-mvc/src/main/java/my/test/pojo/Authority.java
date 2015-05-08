package my.test.pojo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Authority {
    private Long id;
    private String code;
    private String name;
    private String url;
    private Integer sort;
    //父权限
    private Long pid;
    private Integer type;
    
    

	//子权限
    List<Authority> authorities; 
    
    private String createUser;
    private Date createTime;
    private String lastModifiedUser;
    private Date lastModifiedTime;
    private String remark;

    //页面用字段
    public String getText(){
    	return name;
    }
    public String getState() {
    	if(getPid()!=null&&getPid()==0){
    		return "closed";
    	}
    	return "open";
    }


    
  

	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.id
     *
     * @return the value of sys_authority.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.id
     *
     * @param id the value for sys_authority.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.code
     *
     * @return the value of sys_authority.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.code
     *
     * @param code the value for sys_authority.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.name
     *
     * @return the value of sys_authority.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.name
     *
     * @param name the value for sys_authority.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.url
     *
     * @return the value of sys_authority.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.url
     *
     * @param url the value for sys_authority.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.sort
     *
     * @return the value of sys_authority.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.sort
     *
     * @param sort the value for sys_authority.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.type
     *
     * @return the value of sys_authority.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.type
     *
     * @param type the value for sys_authority.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }
   
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.create_user
     *
     * @return the value of sys_authority.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.create_user
     *
     * @param createUser the value for sys_authority.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.create_time
     *
     * @return the value of sys_authority.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.create_time
     *
     * @param createTime the value for sys_authority.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.last_modified_user
     *
     * @return the value of sys_authority.last_modified_user
     *
     * @mbggenerated
     */
    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.last_modified_user
     *
     * @param lastModifiedUser the value for sys_authority.last_modified_user
     *
     * @mbggenerated
     */
    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser == null ? null : lastModifiedUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.last_modified_time
     *
     * @return the value of sys_authority.last_modified_time
     *
     * @mbggenerated
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.last_modified_time
     *
     * @param lastModifiedTime the value for sys_authority.last_modified_time
     *
     * @mbggenerated
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_authority.remark
     *
     * @return the value of sys_authority.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_authority.remark
     *
     * @param remark the value for sys_authority.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}