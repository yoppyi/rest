package provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class AllExceptionMapper implements ExceptionMapper<Exception> {

	static final Logger logger = LoggerFactory.getLogger(AllExceptionMapper.class);

	@Override
	public Response toResponse(Exception e) {
		int statusCode = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		if (e instanceof WebApplicationException) {
			statusCode = ((WebApplicationException) e).getResponse().getStatus();
		} else {
			logger.error(e.getMessage(), e);
		}
		return Response.status(statusCode).type(MediaType.APPLICATION_JSON)
				.entity(new ErrorMessage(new ErrorDetail(statusCode, "error message", "error info"))).build();
	}

	public static class ErrorMessage {

		public ErrorMessage(ErrorDetail error) {
			super();
			this.error = error;
		}

		private ErrorDetail error;

		public ErrorDetail getError() {
			return error;
		}

		public void setError(ErrorDetail error) {
			this.error = error;
		}

	}

	public static class ErrorDetail {

		public ErrorDetail(int code, String message, String info) {
			super();
			this.code = code;
			this.message = message;
			this.info = info;
		}

		private int code;
		private String message;
		private String info;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

	}
}
