package com.daanta.utils;

import com.daanta.domain.CampBase;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;

@Slf4j
public class HTTPRequester {

    public static ResponseBody post(CampBase c) {

        ResponseBody result = null;

        // MODE 1. FORM TYPE REQUEST
        try{

            Request.Builder reqBuilder = new Request.Builder();

            // URL & HEADER
            reqBuilder.url(c.getUrl());
            for(Map.Entry<String, String> e: c.getHeader().entrySet()) {
                reqBuilder.header(e.getKey(), e.getValue());
            }

            // BODY
            FormBody.Builder form = new FormBody.Builder();
            for(Map.Entry<String, String> e: c.getBody().entrySet()) {
                form.add(e.getKey(), e.getValue());
            }
            RequestBody reqBody = form.build();
            reqBuilder.post(reqBody);

            Request req = reqBuilder.build();

            // SHOOT!
            Response resp = Utils.okHttp.newCall(req).execute(); // Sync
            if (resp.isSuccessful()) {
                result = resp.body();
            } else {
                throw new Exception();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;

    }

}
