package com.daanta.camp.utils;

import com.daanta.camp.domain.Site;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;

@Slf4j
public class HTTPRequester {

    public static ResponseBody post(Site c) throws Exception {

        ResponseBody result;

        // FORM TYPE REQUEST
        Request req = makeRequest(c);

        // SHOOT
        Response resp; // Sync
        resp = Utils.okHttp.newCall(req).execute();
        if (resp.isSuccessful()) {
            result = resp.body();
        } else {
            throw new Exception();
        }

        return result;

    }

    @NonNull
    private static Request makeRequest(@NonNull Site c) {
        Request.Builder reqBuilder = new Request.Builder();

        // URL & HEADER
        reqBuilder.url(c.getUrl());
        for(Map.Entry<String, String> e: c.getHeader().entrySet()) {
            reqBuilder.header(e.getKey(), e.getValue());
        }

        // BODY
        FormBody.Builder form = new FormBody.Builder();
        for(Map.Entry<String, String> e: c.getFormBody().entrySet()) {
            form.add(e.getKey(), e.getValue());
        }
        RequestBody reqBody = form.build();
        reqBuilder.post(reqBody);

        return reqBuilder.build();
    }

}
