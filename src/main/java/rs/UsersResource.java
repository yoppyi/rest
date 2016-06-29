package rs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dao.UsersDao;
import entity.Users;

@Path("/v1/users")
public class UsersResource {

	static final Logger logger = LoggerFactory.getLogger(UsersResource.class);

	@GET
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Users get(@PathParam("id") String id) throws IOException, SQLException {
		try (UsersDao dao = new UsersDao()) {
			Users result = dao.selectById(id);

			if (result == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}

			return result;
		}
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Users> get(@BeanParam GetParam param) throws IOException, SQLException {

		if (logger.isDebugEnabled()) {
			logger.debug(param.toString());
		}

		try (UsersDao dao = new UsersDao()) {
			return dao.list(param.limit, param.offset);
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void post(Users users) throws IOException, SQLException {
		try (UsersDao dao = new UsersDao()) {
			dao.insert(users);
		}
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void put(Users users) throws IOException, SQLException {
		try (UsersDao dao = new UsersDao()) {
			dao.update(users);
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void delete(@PathParam(value = "id") String id) throws IOException, SQLException {
		try (UsersDao dao = new UsersDao()) {
			dao.delete(id);
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GetParam {

		@QueryParam("offset")
		Integer offset;
		@QueryParam("limit")
		Integer limit;

		public Integer getOffset() {
			return offset;
		}

		public void setOffset(Integer offset) {
			this.offset = offset;
		}

		public Integer getLimit() {
			return limit;
		}

		public void setLimit(Integer limit) {
			this.limit = limit;
		}

		@Override
		public String toString() {
			return "GetParam [offset=" + offset + ", limit=" + limit + "]";
		}

	}

}