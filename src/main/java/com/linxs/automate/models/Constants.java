package com.linxs.automate.models;

public class Constants {

    public static final String VIN_REPORT = "VIN_REPORT";
    public static final String OBLIGATIONS_REPORT = "OBLIGATIONS_REPORT";
    public static final String TOLL_REPORT = "TOLL_REPORT";

    public static final String VIN_URI = "https://public-eis.rta.government.bg/public-vehicle-check/api/vehicle-check/" +
            "history?page=1&pageSize=100&orderBy=insp.id&direction=desc&";
    public static final String VIN_URI_reCAPTCHA = "captchaResponse=0cAFcWeA7dwY8bKfCLgcNn8Q_ZQUHyCipL2URfqdSp3579cgK4v" +
            "JGiYG_UpWMDONI4NUEpz9hX4wJd9EjQI4iJcM9YPgkNlDK4b2dirGBMMoK4xUHCH9cSdQbcUP5w3ZcMtQ3er9evvKlj_g-8CuTjGMIM5qw" +
            "pUsVOgA4JiFS1qpv4rUFCUJ8o_1hv5JNtkAq8_l4rcVD6Ka8CFCYnglKKBYEN1TK9Tu6i_MOhr6oKN8_IhSz9itdcwlHYhJwAwfVx2UmB0" +
            "u415MFK2alKb_LUlfljLKYSdErRprYjIl4LeoAlNo80g6h3Tp2rVZ-D5Qb69ewMowSZM7SEKwgRpchSiohsPoGptV160lHc9vk9YE9Al61" +
            "UOxXBQ1CPLTQPlUX7n4KAdqrnsQA9KVbna98sAZRk3gegRpYiF4Gt_TZja8c4AdRYGsVbfapkgMsyTvXEtmSBiB7EneLT1VY-DocyAYm5J" +
            "6o3VTjrwIjvHgZM3UL08Kiu5biE62xFulXpEYsjmNzC8QyMfqz1dN_Yr74cUEwExVBIUs2BOJao5GQuvNB5XO6l4A8qN-RbWbdhtfR8iZE" +
            "kKj254_bi2nOXKzVtgsZP0WVBr5e2PwQmzjpwHiR-D6x004-UlplhEP-CSMsiaTYV9U0vK1epS0hw5zkvovupABvVfgqtArZbnxHeDM8Ao" +
            "y8vezfG6RpZe3a6aB_2ec90E0aw8TMiul6g5gdf9zf57cFP02fa_FbHgMHvbxwkY4QVqQIvFyDka7B-kenWiGjQk9dXZN0v6Dz_GKywWzT" +
            "lNNDfrqBtcWu3_cEKcDR0ruLIx80c99pBnTEGYFwTJRtBZPr7W6zG8HdICd20rpriHRBsscUlx8HV0YdmNAf6BuZoCDYQAoK8EEu0hfdw" +
            "wVYcs1FOgjC-lX98SvM0NzLL-i7ZclTKFAq357JERUY3zEFI5UOxgVIBk6flGHK5V7C-E8HuueWl9mQQSia00SVVoxIluE_f0lBfnJafB" +
            "lVwARWP7w0E2ZHVBQefPHQd41yXLQ6_afy0UBDV-vIcqb5ZnRcm-uftXD-QeegC5u7yAN3mtplbJWpR8RbU8b8zPRxIEaBN02fXlIRXjb92" +
            "OrfNLbyXu31UMs9OO81zjhFNsH5-eibgEALXjrUn8MFK4jT-ofT_gCuFsYt8155ZRqmHPbLfnf02Llorya0pLlUOMJmwq9MlJaYSnFHcoU" +
            "sLG36UTUiz0PtInzSo4dweqnkvmPaFO6wvJ4C5MfQryFRowBS84kuFE5-t3omxtHZrnkzAIcMDKWF5P_FxVQEb_-9rKrG8FctnRtnlf7yD" +
            "y7iZj1cIzw55qMAjxkaXo-SZ9r6ZGrao8nv7Zmb7grMeOyXe_FYdM9NBe5Y-d2kpCBicNAHQhjqZMme1NeX_W5PdKeojzDbaBhhK4iTeB" +
            "dvZZN5b14qVorV14R5QRfqG8F50UZJw_YBWp6dIagceqDPBAO2qC6JwWZqrg6RFFz4xSmeeWbkXvazf6-Cd4kygYkmsIrQ39AyEkTYRvK" +
            "d_9t-IAcv0iISFkmq_p3ilthhCHdARlAiPWQMnncCn0CYe-fnnNIihAiG4HoWqqAAFCg5xukTmPFnfrIVY_TN4g0XnLevGUMvH5keb7CNQ" +
            "r0I5xiEBgwDjOtDx6zN46-otrEGSPtWLF93MOTDGXBdCyJanU9EMQBOqmLRNoJj7ocxg8cPTzsd230Y3ncLU5E_nbyG1Y9kla7Z-7MUkJl" +
            "qtTY3Yu2yStY6rT_tm_qyX6V5IH6DdYUmmEHYtQdlQqrgYiWNOmN8KzpE00hoy";

    public static final String OBLIGATIONS_URI = "https://e-uslugi.mvr.bg/api/Obligations/AND?" +
            "obligatedPersonType=1&additinalDataForObligatedPersonType=1&mode=1&";

    public static final String TOLL_URI = "https://check.bgtoll.bg/check/vignette/plate/BG/";

    public static final Integer INTERNAL_SERVER_ERROR = 500;

    public static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error";

    public static final Integer OK = 200;

    public static final String OK_MSG = "OK";

    public static final Integer BAD_REQUEST = 400;

    public static final String BAD_REQUEST_MSG = "BAD REQUEST";
}
