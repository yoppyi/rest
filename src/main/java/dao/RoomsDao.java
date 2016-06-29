package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Rooms;

public class RoomsDao extends AbstractDao {

	public RoomsDao() throws SQLException {
		super();
	}

	public int update(Rooms rooms) throws SQLException {

		try (PreparedStatement ps = con
				.prepareStatement("UPDATE rooms SET name = ?, updated_user_id = ?, updated_at = ?"
						+ " WHERE id = ? AND updated_at = ?")) {
			ps.setString(1, rooms.getName());
			ps.setInt(3, 1);
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setInt(5, rooms.getId());
			ps.setTimestamp(6, rooms.getUpdatedAt());

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
				"UPDATE rooms SET updated_user_id = ?, updated_at = ?, deleted_at = ? WHERE id = ?")) {
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

	public int insert(Rooms rooms, int login) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"INSERT INTO rooms( id ,name, created_user_id ,updated_user_id ,created_at ,updated_at ,deleted_at ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)")) {
			ps.setInt(1, rooms.getId());
			ps.setString(2, rooms.getName());
			ps.setInt(3, login);
			ps.setInt(4, login);
			Timestamp time = new Timestamp(new Date().getTime());
			ps.setTimestamp(5, time);
			ps.setTimestamp(6, time);
			ps.setTimestamp(7, null);

			int result = ps.executeUpdate();
			con.commit();

			return result;

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

	}

	public Rooms selectById(String id) throws SQLException {

		Rooms rooms = null;

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM rooms WHERE id = ?")) {
			ps.setString(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					rooms = createRooms(rs);
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return rooms;
	}

	public List<Rooms> list(Integer limit, Integer offset) throws SQLException {

		List<Rooms> rooms = new ArrayList<>();

		String sql = "SELECT * FROM rooms";
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
					rooms.add(createRooms(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return rooms;
	}

	public List<Rooms> listAll() throws SQLException {

		List<Rooms> result = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM rooms")) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					result.add(createRooms(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return result;
	}

	private Rooms createRooms(ResultSet rs) throws SQLException {
		Rooms rooms = new Rooms();
		rooms.setCreatedAt(rs.getTimestamp("created_at"));
		rooms.setCreatedUserId(rs.getInt("created_user_id"));
		rooms.setDeletedAt(rs.getTimestamp("deleted_at"));
		rooms.setId(rs.getInt("id"));
		rooms.setName(rs.getString("name"));
		rooms.setUpdatedAt(rs.getTimestamp("updated_at"));
		rooms.setUpdatedUserId(rs.getInt("updated_user_id"));
		return rooms;
	}

}
