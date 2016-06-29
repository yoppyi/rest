package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Roles;

public class RolesDao extends AbstractDao {

	public RolesDao() throws SQLException {
		super();
	}

	public int update(Roles roles) throws SQLException {

		try (PreparedStatement ps = con
				.prepareStatement("UPDATE roles SET name = ?, role = ?, updated_user_id = ?, updated_at = ?"
						+ " WHERE id = ? AND updated_at = ?")) {
			ps.setString(1, roles.getName());
			ps.setString(2, roles.getRole());
			ps.setInt(3, 1);
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setInt(5, roles.getId());
			ps.setTimestamp(6, roles.getUpdatedAt());

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
				"UPDATE roles SET updated_user_id = ?, updated_at = ?, deleted_at = ? WHERE id = ?")) {
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

	public int insert(Roles roles) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"INSERT INTO roles( id ,name, role, created_user_id ,updated_user_id ,created_at ,updated_at ,deleted_at ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)")) {
			ps.setInt(1, roles.getId());
			ps.setString(2, roles.getName());
			ps.setString(3, roles.getRole());
			ps.setInt(4, 1);
			ps.setInt(5, 1);
			Timestamp time = new Timestamp(new Date().getTime());
			ps.setTimestamp(6, time);
			ps.setTimestamp(7, time);
			ps.setTimestamp(8, null);

			int result = ps.executeUpdate();
			con.commit();

			return result;

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

	}

	public Roles selectById(String id) throws SQLException {

		Roles roles = null;

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM roles WHERE id = ?")) {
			ps.setString(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					roles = createRoles(rs);
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return roles;
	}

	public List<Roles> list(Integer limit, Integer offset) throws SQLException {

		List<Roles> roles = new ArrayList<>();

		String sql = "SELECT * FROM roles";
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
					roles.add(createRoles(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return roles;
	}

	public List<Roles> listAll() throws SQLException {

		List<Roles> result = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM roles")) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					result.add(createRoles(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return result;
	}

	private Roles createRoles(ResultSet rs) throws SQLException {
		Roles roles = new Roles();
		roles.setCreatedAt(rs.getTimestamp("created_at"));
		roles.setCreatedUserId(rs.getInt("created_user_id"));
		roles.setDeletedAt(rs.getTimestamp("deleted_at"));
		roles.setId(rs.getInt("id"));
		roles.setName(rs.getString("name"));
		roles.setRole(rs.getString("role"));
		roles.setUpdatedAt(rs.getTimestamp("updated_at"));
		roles.setUpdatedUserId(rs.getInt("updated_user_id"));
		return roles;
	}

}
