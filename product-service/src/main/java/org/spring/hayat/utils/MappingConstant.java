package org.spring.hayat.utils;

public class MappingConstant {
    private MappingConstant() {
        // private constructor to prevent instantiation
    }

    public static class AuthMapping {
        private AuthMapping() {
        }

        public static final String BASE = "/api/v1/auth";
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
        public static final String REFRESH = "/refresh-token";
        public static final String UPDATE_PASSWORD = "/update-password";
        public static final String UPDATE_USER = "/update-user";
    }

    public static class CategoryMapping {
        private CategoryMapping() {
        }

        public static final String UPDATE_BY_NAME = "update/{name}";
        public static final String CREATE = "/create";
        public static final String BASE = "/api/v1/category";
        public static final String GET_BY_NAME = "/get/{name}";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String FIND_ALL = "/find-all";
        public static final String DELETE_BY_ID = "/delete";
        public static final String DELETE_BY_NAME = "/delete/{name}";

    }

    public static class BrandMapping {
        public static final String SEARCH = "/search";

        private BrandMapping() {
        }


        public static final String BASE = "/api/v1/brand";
        public static final String CREATE = "/create";
        public static final String UPDATE_BY_NAME = "/update/{name}";
        public static final String GET_BY_NAME = "/get/{name}";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String FIND_ALL = "/find-all";
        public static final String REGISTER = "/register";
        public static final String DELETE_BY_ID = "/delete";
        public static final String DELETE_BY_NAME = "/delete/{name}";
    }

    public static class Product {
        private Product() {
        }

        public static final String BASE = "/api/v1/product";
        public static final String CREATE = "/create";
        public static final String UPDATE_BY_NAME = "/update/{name}";
        public static final String GET_BY_NAME = "/get/{name}";
        public static final String GET_BY_ID = "/get/{id}";
        public static final String FIND_ALL = "/find-all";
        public static final String DELETE_BY_ID = "/delete";
        public static final String DELETE_BY_NAME = "/delete/{name}";
    }

}
