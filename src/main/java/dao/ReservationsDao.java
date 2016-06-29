package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Reservations;

public class ReservationsDao extends AbstractDao {

	public ReservationsDao() throws SQLException {
		super();
	}

	public int update(Reservations reservations) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"UPDATE reservations SET title = ?, start = ?, end = ?, room_id = ?, updated_user_id = ?, updated_at = ?"
						+ " WHERE id = ? AND updated_at = ?")) {
			ps.setString(1, reservations.getTitle());
			ps.setTimestamp(2, reservations.getStart());
			ps.setTimestamp(3, reservations.getEnd());
			ps.setInt(4, reservations.getRoomId());
			ps.setInt(5, 1);
			ps.setTimestamp(6, new Timestamp(new Date().getTime()));
			ps.setInt(7, reservations.getId());
			ps.setTimestamp(8, reservations.getUpdatedAt());

			int result = ps.executeUpdate();
			con.commit();

			return result;

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

	}

	public int delete(String id) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"UPDATE reservations SET updated_user_id = ?, updated_at = ?, deleted_at = ? WHERE id = ?")) {
			ps.setInt(1, 1);
			Timestamp time = new Timestamp(new Date().getTime());
			ps.setTimestamp(2, time);
			ps.setTimestamp(3, time);
			ps.setString(4, id);

			int result = ps.executeUpdate();
			con.commit();

			return result;

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

	}

	public int insert(Reservations reservations) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"INSERT INTO reservations( id, title, start, end, room_id, created_user_id, updated_user_id, created_at, updated_at, deleted_at ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			ps.setInt(1, reservations.getId());
			ps.setString(2, reservations.getTitle());
			ps.setTimestamp(3, reservations.getStart());
			ps.setTimestamp(4, reservations.getEnd());
			ps.setInt(5, reservations.getRoomId());
			ps.setInt(6, 1);
			ps.setInt(7, 1);
			Timestamp time = new Timestamp(new Date().getTime());
			ps.setTimestamp(8, time);
			ps.setTimestamp(9, time);
			ps.setTimestamp(10, null);

			int result = ps.executeUpdate();
			con.commit();

			return result;

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

	}

	public Reservations selectById(String id) throws SQLException {

		Reservations reservations = null;

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations WHERE id = ?")) {
			ps.setString(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					reservations = createReservations(rs);
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return reservations;
	}

	public List<Reservations> list(Integer limit, Integer offset) throws SQLException {

		List<Reservations> reservations = new ArrayList<>();

		String sql = "SELECT * FROM reservations";
		if (limit != null) {
			sql += " LIMIT ?";
		}
		if (offset != null) {
			sql += " OFFSET ?";
		}
		System.out.println(sql);
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int index = 1;
			if (limit != null) {
				System.out.println(index + " " + limit);
				ps.setInt(index++, limit);
			}
			if (offset != null) {
				ps.setInt(index++, offset);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					reservations.add(createReservations(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return reservations;
	}

	public List<Reservations> listAll() throws SQLException {

		List<Reservations> result = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations")) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					result.add(createReservations(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return result;
	}

	private Reservations createReservations(ResultSet rs) throws SQLException {
		Reservations reservations = new Reservations();
		reservations.setCreatedAt(rs.getTimestamp("created_at"));
		reservations.setCreatedUserId(rs.getInt("created_user_id"));
		reservations.setDeletedAt(rs.getTimestamp("deleted_at"));
		reservations.setId(rs.getInt("id"));
		reservations.setTitle(rs.getString("title"));
		reservations.setStart(rs.getTimestamp("start"));
		reservations.setEnd(rs.getTimestamp("end"));
		reservations.setRoomId(rs.getInt("room_id"));
		reservations.setUpdatedAt(rs.getTimestamp("updated_at"));
		reservations.setUpdatedUserId(rs.getInt("updated_user_id"));
		return reservations;
	}

}
