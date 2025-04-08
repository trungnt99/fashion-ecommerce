package com.gnurt.fashion_ecommerce.constant;

public class Constant {
	public static final Integer BAD_REQUEST = 400;
	public static final Integer UNAUTHORIZED = 401;
	public static final Integer FORBIDDEN = 403;
	public static final Integer NOT_FOUND = 404;
	public static final Integer NOT_ACCEPTABLE = 406;
	public static final Integer CONFLICT = 409;
	public static final Integer INTERNAL_SERVER_ERROR = 500;
	public static final Integer BAD_GATEWAY = 502;
	public static final Integer SERVICE_UNAVAILABLE = 503;
	public static final Integer GATEWAY_TIMEOUT = 504;

	public static class AuthConstant {
		public static String TYPE_TOKEN = "Bearer ";
		public static String AUTHORIZATION = "Authorization";
		public static final int AUTHORIZATION_TYPE_SIZE = 7;
		public static final String INVALID_TOKEN = "Token is invalid";
		public static final String EXPIRED_TOKEN = "Token is expired";

	}
}
