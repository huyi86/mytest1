package my.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import my.test.pojo.User;

public interface UserMapper {
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> selectAll();
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);
    
    /**
     * 添加用户
     * @param record
     * @return
     */
    int insert(User record);
    
    /**
     * 添加用户，只保存非空数据
     * @param record
     * @return
     */
    int insertSelective(User record);
    
    /**
     * 根据id查询用户，同时查询出对应的角色
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);
    
    /**
     * 根据Id修改用户信息，只修改非空字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);
    /**
     * 根据Id修改用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
    
    /**
     * 给用户添加角色
     * @param id
     * @param rid
     */
    void insertUserRole(@Param("id")Long id,@Param("rid")String rid);
    
    /**
     * 根据用户code查询用户,同时查询出对应的角色
     * @param name
     * @return
     */
    User selectUserByCode(String code);
}