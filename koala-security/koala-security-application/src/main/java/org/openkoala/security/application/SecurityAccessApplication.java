package org.openkoala.security.application;

import java.util.List;
import java.util.Set;

import org.openkoala.security.core.domain.*;

public interface SecurityAccessApplication {

	/**
	 * 判断用户是否有权限Permission
	 * 
	 * @param user
	 *            用户
	 */
	boolean hasPermission(User user);

	/**
	 * 判断用户是否拥有权限资源securityResource
	 * 
	 * @param user
	 *            用户
	 * @param securityResource
	 *            权限资源
	 */
	boolean hasOwnSecurityResource(User user, SecurityResource securityResource);

	/**
	 * 根据账户查找该用户拥有的所有角色
	 * 
	 * @param userAccount
	 *            账户
	 * @return
	 */
	List<Role> findAllRolesByUserAccount(String userAccount);

	/**
	 * 根据账户查找该用户拥有的所有角色
	 * 
	 * @param userAccount
	 *            账户
	 * @return
	 */
	List<Permission> findAllPermissionsByUserAccount(String userAccount);

	/**
	 * 根据用户ID获取用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	User getUserById(Long userId);

	<T extends Actor> T getActorById(Long actorId);

	/**
	 * 根据角色ID获取角色
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	Role getRoleBy(Long roleId);

	/**
	 * 根据账户获取用户
	 * 
	 * @param userAccount
	 *            账户
	 * @return
	 */
	User getUserByUserAccount(String userAccount);

	/**
	 * 根据权限ID得到权限
	 * 
	 * @param permissionId
	 *            权限ID
	 * @return
	 */
	Permission getPermissionBy(Long permissionId);

	/**
	 * 根据菜单资源ID得到菜单资源
	 * 
	 * @param menuResourceId
	 *            菜单资源ID
	 * @return
	 */
	MenuResource getMenuResourceBy(Long menuResourceId);

	/**
	 * 根据URL访问资源ID得到URL访问资源
	 * 
	 * @param urlAccessResourceId
	 * @return
	 */
	UrlAccessResource getUrlAccessResourceBy(Long urlAccessResourceId);

	/**
	 * 根据页面元素ID得到页面元素资源
	 * 
	 * @param pageElementResourceId
	 *            页面元素ID
	 * @return
	 */
	PageElementResource getPageElementResourceBy(Long pageElementResourceId);

	/**
	 * 根据页面元素名称得到页面元素资源
	 * 
	 * @param pageElementResourceName
	 *            页面元素资源名称
	 * @return
	 */
	PageElementResource getPageElementResourceBy(String pageElementResourceName);

	/**
	 * 根据角色名称得到角色。
	 * 
	 * @param roleName
	 *            角色名称
	 * @return
	 */
	Role getRoleBy(String roleName);

	<T extends Scope> T getScope(Long scopeId);

	/**
	 * 根据账户查找拥有的菜单资源
	 * 
	 * @param userAccount
	 *            账户
	 * @return
	 */
	List<MenuResource> findMenuResourceByUserAccount(String userAccount);

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 *            用户
	 * @param oldUserPassword
	 *            旧密码
	 * @return
	 */
	boolean updatePassword(User user, String userPassword, String oldUserPassword);

	/**
	 * 通过角色下的用户检查Authorization是否存在
	 * 
	 * @param userAccount
	 *            账户
	 * @param role
	 *            角色
	 */
	void checkAuthorization(String userAccount, Role role);

	/**
	 * 根据角色查找菜单资源
	 * 
	 * @param role
	 *            角色
	 * @return
	 */
	List<MenuResource> findAllMenuResourcesByRole(Role role);

	/**
	 * 查找所有的角色
	 * 
	 * @return
	 */
	List<Role> findAllRoles();

	/**
	 * 查找所有的URL访问资源。
	 * 
	 * @return
	 */
	List<UrlAccessResource> findAllUrlAccessResources();

	/**
	 * 查找所有的菜单资源
	 * 
	 * @return
	 */
	List<MenuResource> findAllMenuResorces();

	boolean hasPageElementResource(String identifier);

	User getUserByEmail(String email);

	User getUserByTelePhone(String telePhone);

	List<MenuResource> findAllMenuResourcesByIds(Long[] menuResourceIds);

	/**
	 * 检测角色名是否存在
	 * 
	 * @param roleName
	 * @return
	 */
	boolean checkRoleByName(String roleName);

    <T extends Authority> T getAuthority(Long authorityId);
}