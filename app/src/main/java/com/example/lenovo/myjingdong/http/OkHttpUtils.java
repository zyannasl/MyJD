package com.example.lenovo.myjingdong.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    private static OkHttpUtils okHttpUtils;
    private Context context;
    private OkHttpClient okHttpClient;
    static String result = "";
    private static Handler handler;
    private ICallback iCallback;


    public OkHttpUtils(Context context) {
        this.context = context;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (iCallback != null) {
                    iCallback.getData(msg.obj.toString());
                }
            }
        };
        initOK();
    }

    public void setiCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }

    /**
     * 创建ok
     */
    private void initOK() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .connectTimeout(5, TimeUnit.SECONDS).build();
    }

    public static OkHttpUtils getInstance(Context context) {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils(context);
                }
            }
        }

        return okHttpUtils;
    }

    /**
     * get请求
     *
     * @return
     */
    public void getData(String url) {

        Request request = new Request.Builder()
                .url(url)
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    if (response.code() == 200) {

                        result = response.body().string();

                        Message message = new Message();
                        message.obj = result;
                        handler.sendMessage(message);
                    }

                }

            }
        });


    }

    /**
     * post请求:1原生表单 2.支持文件的，或者两个方式封装到一个方法里
     *
     * @return
     */
    public void post(String url, Map<String, String> params) {

        //1.FORM原生表单,<form></form>（单纯提交字符串类型的键值对）2.multipart/form-data（1.字符串类型键值对和文件） 3.raw(json、xml) 4.binary(文件流)
        //1.普通表单

        //普通表单
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {

            builder.add(stringStringEntry.getKey(), stringStringEntry.getValue());

        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    if (response.code() == 200) {

                        result = response.body().string();

                        Message message = new Message();
                        message.obj = result;
                        handler.sendMessage(message);
                    }

                }

            }
        });




    }
    /**
     * post请求:2.支持文件的，或者两个方式封装到一个方法里
     *
     * @return
     */
    public void postFile(String url, Map<String, Object> params) {

        //1.FORM原生表单,<form></form>（单纯提交字符串类型的键值对）2.multipart/form-data（1.字符串类型键值对和文件） 3.raw(json、xml) 4.binary(文件流)
        //1.普通表单

        //multipart/form-data
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();

            if (value instanceof File){
                File file = (File) value;
                builder.addFormDataPart(key,file.getName(), RequestBody.create(MediaType.parse("image/*"),file));
            }else{
                builder.addFormDataPart(key,value.toString());
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    if (response.code() == 200) {

                        result = response.body().string();

                        Message message = new Message();
                        message.obj = result;
                        handler.sendMessage(message);
                    }

                }

            }
        });




    }


    public interface ICallback {
        void getData(String result);
    }

}
