package com.daanta.camp.utils;

import com.daanta.camp.domain.Site;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HTTPRequester {

    @NonNull
    public static ResponseBody post(@NonNull Site s) throws Exception {

        ResponseBody result;

        // FORM TYPE REQUEST
        Request req = makeRequest(s);

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
    private static Request makeRequest(@NonNull Site s) {

        Request.Builder reqBuilder = new Request.Builder();

        // URL
        reqBuilder.url(s.getUrl());

        // HEADER
        List<String> header = new ArrayList<>(List.of(s.getHeader().split("&")));
        for(String h: header) {
            String[] entry = h.split("=");
            reqBuilder.header(entry[0], entry[1]);
        }

        // BODY
        FormBody.Builder form = new FormBody.Builder();
        List<String> formBody = new ArrayList<>(List.of(s.getFormBody().split("&")));
        for(String f: formBody) {
            String[] entry = f.split("=");
            form.add(entry[0], entry[1]);
        }
        RequestBody reqBody = form.build();

        // SHOOT!
        reqBuilder.post(reqBody);
        return reqBuilder.build();

    }

}
