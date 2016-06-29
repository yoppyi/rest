package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 */
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227878400085645756L;

	Integer id;

	String account;

	String name;

	String password;

	Integer roleId;

	Integer createdUserId;

    Integer updatedUserId;

    Timestamp createdAt;

    Timestamp updatedAt;

    Timestamp deletedAt;

	/**
	 * Returns the id.
	 * 
	 * @return the id
	 */
    public Integer getId() {
        return id;
    }

    /** 
     * Sets the id.
     * 
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * Returns the account.
     * 
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /** 
     * Sets the account.
     * 
     * @param account the account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /** 
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** 
     * Sets the name.
     * 
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Returns the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /** 
     * Sets the password.
     * 
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * Returns the roleId.
     * 
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /** 
     * Sets the roleId.
     * 
     * @param roleId the roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 
     * Returns the createdUserId.
     * 
     * @return the createdUserId
     */
    public Integer getCreatedUserId() {
        return createdUserId;
    }

    /** 
     * Sets the createdUserId.
     * 
     * @param createdUserId the createdUserId
     */
    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    /** 
     * Returns the updatedUserId.
     * 
     * @return the updatedUserId
     */
    public Integer getUpdatedUserId() {
        return updatedUserId;
    }

    /** 
     * Sets the updatedUserId.
     * 
     * @param updatedUserId the updatedUserId
     */
    public void setUpdatedUserId(Integer updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    /** 
     * Returns the createdAt.
     * 
     * @return the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /** 
     * Sets the createdAt.
     * 
     * @param createdAt the createdAt
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /** 
     * Returns the updatedAt.
     * 
     * @return the updatedAt
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /** 
     * Sets the updatedAt.
     * 
     * @param updatedAt the updatedAt
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** 
     * Returns the deletedAt.
     * 
     * @return the deletedAt
     */
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    /** 
     * Sets the deletedAt.
     * 
     * @param deletedAt the deletedAt
     */
    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}