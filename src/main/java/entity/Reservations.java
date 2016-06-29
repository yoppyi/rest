package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 */
public class Reservations implements Serializable {

	private static final long serialVersionUID = 7781780297009642328L;

	Integer id;

	String title;

	Timestamp start;

	Timestamp end;

	Integer roomId;

	Integer reservedUserId;

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
	 * Returns the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the start.
	 * 
	 * @return the start
	 */
	public Timestamp getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 * 
	 * @param start
	 *            the start
	 */
	public void setStart(Timestamp start) {
		this.start = start;
	}

	/**
	 * Returns the end.
	 * 
	 * @return the end
	 */
	public Timestamp getEnd() {
		return end;
	}

	/**
	 * Sets the end.
	 * 
	 * @param end
	 *            the end
	 */
	public void setEnd(Timestamp end) {
		this.end = end;
	}

	/**
	 * Returns the roomId.
	 * 
	 * @return the roomId
	 */
	public Integer getRoomId() {
		return roomId;
	}

	/**
	 * Sets the roomId.
	 * 
	 * @param roomId
	 *            the roomId
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	/**
	 * Returns the reservedUserId.
	 * 
	 * @return the reservedUserId
	 */
	public Integer getReservedUserId() {
		return reservedUserId;
	}

	/**
	 * Sets the reservedUserId.
	 * 
	 * @param reservedUserId
	 *            the reservedUserId
	 */
	public void setReservedUserId(Integer reservedUserId) {
		this.reservedUserId = reservedUserId;
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