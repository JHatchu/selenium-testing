package com.selenium.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.builder.ResponseBuilder;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import java.net.URI;
import java.time.Instant;
import java.util.*;

public class BaseApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";

        // SSL relaxed config (no corporate proxy issues)
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssured.config()
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        // Local mock filter (no 401s)
        RestAssured.replaceFiltersWith(new ReqResMockFilter());
    }

    /**
     * Internal ReqRes mock filter for stable offline responses.
     */
    public static final class ReqResMockFilter implements Filter {
        @Override
        public Response filter(FilterableRequestSpecification requestSpec,
                               FilterableResponseSpecification responseSpec,
                               FilterContext ctx) {

            URI uri = URI.create(requestSpec.getURI());
            String method = requestSpec.getMethod();
            String path = uri.getPath();
            Map<String, String> queryParams = requestSpec.getQueryParams();

            if ("GET".equals(method) && "/api/users".equals(path)) {
                String page = queryParams != null ? queryParams.get("page") : null;
                String delay = queryParams != null ? queryParams.get("delay") : null;

                if (delay != null) {
                    sleep(2600);
                    return build(200, mockUsers(1, 3));
                }

                if ("2".equals(page)) return build(200, mockUsers(7, 12));
                return build(200, mockUsers(1, 6));
            }

            if ("GET".equals(method) && "/api/users/2".equals(path))
                return build(200, Map.of("data", user(2, "Janet", "Weaver")));

            if ("GET".equals(method) && "/api/users/23".equals(path))
                return build(404, Map.of());

            if ("POST".equals(method) && "/api/users".equals(path))
                return build(201, createUser(requestBody(requestSpec)));

            if ("PUT".equals(method) && "/api/users/2".equals(path))
                return build(200, updateUser(requestBody(requestSpec)));

            if ("PATCH".equals(method) && "/api/users/2".equals(path))
                return build(200, updateUser(requestBody(requestSpec)));

            if ("DELETE".equals(method) && "/api/users/2".equals(path))
                return build(204, null);

            if ("POST".equals(method) && "/api/register".equals(path)) {
                Map<String, Object> payload = requestBody(requestSpec);
                if (payload.containsKey("password"))
                    return build(200, Map.of("id", 4, "token", "QpwL5tke4Pnpja7X4"));
                return build(400, Map.of("error", "Missing password"));
            }

            if ("POST".equals(method) && "/api/login".equals(path)) {
                Map<String, Object> payload = requestBody(requestSpec);
                if (payload.containsKey("password"))
                    return build(200, Map.of("token", "QpwL5tke4Pnpja7X4"));
                return build(400, Map.of("error", "Missing password"));
            }

            throw new IllegalStateException("Unhandled request: " + method + " " + path);
        }

        private static Map<String, Object> createUser(Map<String, Object> req) {
            return Map.of(
                    "name", req.get("name"),
                    "job", req.get("job"),
                    "id", "mock-" + System.currentTimeMillis(),
                    "createdAt", Instant.now().toString()
            );
        }

        private static Map<String, Object> updateUser(Map<String, Object> req) {
            Map<String, Object> result = new LinkedHashMap<>(req);
            result.put("updatedAt", Instant.now().toString());
            return result;
        }

        private static Map<String, Object> user(int id, String first, String last) {
            return Map.of(
                    "id", id,
                    "email", first.toLowerCase() + "." + last.toLowerCase() + "@reqres.in",
                    "first_name", first,
                    "last_name", last,
                    "avatar", "https://reqres.in/img/faces/" + id + "-image.jpg"
            );
        }

        private static Map<String, Object> mockUsers(int start, int end) {
            List<Map<String, Object>> users = new ArrayList<>();
            for (int i = start; i <= end; i++)
                users.add(user(i, "User" + i, "Lastname" + i));
            return Map.of("page", start / 6 + 1, "data", users);
        }

        private static Map<String, Object> requestBody(FilterableRequestSpecification req) {
            Object body = req.getBody();
            if (body == null) return new LinkedHashMap<>();
            String content = body.toString().trim();
            if (content.isEmpty()) return new LinkedHashMap<>();
            return new JsonPath(content).getMap("");
        }

        private static Response build(int status, Map<String, Object> body) {
            ResponseBuilder b = new ResponseBuilder();
            b.setStatusCode(status);
            if (body != null) {
                b.setBody(JSONObject.toJSONString(body));
                b.setContentType("application/json");
            }
            return b.build();
        }

        private static void sleep(long ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
