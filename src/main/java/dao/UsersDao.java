package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Users;

public class UsersDao extends AbstractDao {

	public UsersDao() throws SQLException {
		super();
	}

	public int update(Users users) throws SQLException {

		try (PreparedStatement ps = con
				.prepareStatement("UPDATE users SET name = ?, password = ?, updated_user_id = ?, updated_at = ?"
						+ " WHERE id = ? AND updated_at = ?")) {
			ps.setString(1, users.getName());
			ps.setString(2, users.getPassword());
			ps.setInt(3, 1);
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setInt(5, users.getId());
			ps.setTimestamp(6, users.getUpdatedAt());

			int result = ps.executeUpdate();
			con.commit();

			return result;

		} catch (Exception e) {
			con.rollback();
			throw e;
		}

	}

	public int delete(String id) throws SQLException {

		// 楽観ロック、論理削除を入れています
		try (PreparedStatement ps = con.prepareStatement(
				"UPDATE users SET updated_user_id = ?, updated_at = ?, deleted_at = ? WHERE id = ?")) {
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

	public int insert(Users users) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"INSERT INTO users( id, account, name, password, role_id, created_user_id, updated_user_id, created_at, updated_at, deleted_at ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			ps.setInt(1, users.getId());
			ps.setString(2, users.getAccount());
			ps.setString(3, users.getName());
			ps.setString(4, users.getPassword());
			ps.setInt(5, users.getRoleId());
			// 作成者更新者は、1で固定しておきます。
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

	public Users selectById(String id) throws SQLException {

		Users users = null;

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?")) {
			ps.setString(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					users = createUsers(rs);
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return users;
	}

	public List<Users> list(Integer limit, Integer offset) throws SQLException {

		List<Users> users = new ArrayList<>();

		String sql = "SELECT * FROM users";
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
				System.out.println(index + " " +limit);
				ps.setInt(index++, limit);
			}
			if (offset != null) {
				ps.setInt(index++, offset);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					users.add(createUsers(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return users;
	}

	public Users findLoginUser(String id, String password) throws SQLException {

		Users users = null;

		try (PreparedStatement ps = con
				.prepareStatement("SELECT * FROM users WHERE id = ? AND  password = ? AND deleted_at IS NULL")) {
			ps.setString(1, id);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					users = createUsers(rs);
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return users;
	}

	public List<Users> listAll() throws SQLException {

		List<Users> result = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM users")) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					result.add(createUsers(rs));
				}
			}
		} catch (Exception e) {
			con.rollback();
			throw e;
		}

		return result;
	}

	private Users createUsers(ResultSet rs) throws SQLException {
		Users users = new Users();
		users.setAccount(rs.getString("account"));
		users.setCreatedAt(rs.getTimestamp("created_at"));
		users.setCreatedUserId(rs.getInt("created_user_id"));
		users.setDeletedAt(rs.getTimestamp("deleted_at"));
		users.setId(rs.getInt("id"));
		users.setName(rs.getString("name"));
		users.setPassword(rs.getString("password"));
		users.setRoleId(rs.getInt("role_id"));
		users.setUpdatedAt(rs.getTimestamp("updated_at"));
		users.setUpdatedUserId(rs.getInt("updated_user_id"));
		return users;
	}
}
