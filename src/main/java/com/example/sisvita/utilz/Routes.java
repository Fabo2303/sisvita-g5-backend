package com.example.sisvita.utilz;

public class Routes {

    private Routes() {
        throw new IllegalStateException("Utility class");
    }

    public static final String H2_CONSOLE = "/h2-console/**";
    public static final String AUTH = "/auth";
    public static final String AUTH_LOGIN = "/auth/login";
    public static final String AUTH_PUBLIC_ACCESS = "/auth/public-access";
    public static final String API_USER = "/api/user";
    public static final String API_ALTERNATIVE = "/api/alternative";
    public static final String API_ANSWER = "/api/answer";
    public static final String API_CLASSIFICATION = "/api/classification";
    public static final String API_PATIENT = "/api/patient";
    public static final String API_PERSON = "/api/person";
    public static final String API_SPECIALIST = "/api/specialist";
    public static final String API_QUESTION = "/api/question";
    public static final String API_RESOLVED_TEST = "/api/resolved-test";
    public static final String API_TEMPLATE_TEST = "/api/template-test";
    public static final String API_UBIGEO = "/api/ubigeo";
    public static final String API_ANXIETY_COLOR = "/api/anxiety-color";
    public static final String API_CONSIGNMENT = "/api/consignment";
    public static final String API_TREATMENT = "/api/treatment";
    public static final String API_DIAGNOSIS = "/api/diagnosis";
    public static final String API_DOCUMENT_TYPE = "/api/document-type";
    public static final String API_GENDER = "/api/gender";
}
