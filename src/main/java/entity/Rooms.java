package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Rooms implements Serializable {

	private static final long serialVersionUID = -2891024733389883718L;

	Integer id;

	String name;

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
	 * @param id
	 *            the id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @param name
	 *            the name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param createdUserId
	 *            the createdUserId
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
	 * @param updatedUserId
	 *            the updatedUserId
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
	 * @param createdAt
	 *            the createdAt
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
	 * @param updatedAt
	 *            the updatedAt
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
	 * @param deletedAt
	 *            the deletedAt
	 */
	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}
}