package com.app.dlm.mysdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.app.dlm.mysdk.okhttp.execption.OkHttpException;
import com.app.dlm.mysdk.okhttp.listener.DisponseDataHandle;
import com.app.dlm.mysdk.okhttp.listener.DisposDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/28.
 * 专门处理JSON数据的回调
 */
public class CommonJsonCallback implements Callback {


    //与服务器返回的字段的对应关系
    protected final String RESULT_CODE = "ecode";//有返回对http请求来说是成功的，但是可能有些数据上的异常
    protected final int RESULT_CODE_VALUE = 0;//成功，和服务器一致
    protected final String ERROR_MSG = "error_msg";//错误信息
    protected final String EMPTY_MSG = "";//空的信息

    //自定义异常类型
    protected final int NETWORK_ERROR = -1;//网络错误
    protected final int JSON_ERROR = -2;//JSON格式错误
    protected final int OTHER_ERROR = -3;//其他的错误


    private Handler mDeliverHandler;//进行消息的转发
    private DisposDataListener mListener;
    private Class<?> mClass;


    public CommonJsonCallback(DisponseDataHandle handle) {
        this.mClass = handle.mClass;
        this.mListener = handle.mListener;
        mDeliverHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {

        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFaiure(new OkHttpException(NETWORK_ERROR, e));
            }
        });

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        final String result = response.body().string();
        mDeliverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleRespnse(result);
            }


        });
    }


    private void handleRespnse(Object result) {

        if (result == null && result.toString().trim().equals("")) {
            mListener.onFaiure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
//            JSONObject jsonObject = new JSONObject(result.toString());
            JSONObject jsonObject = new JSONObject("{ \"ecode\": \"0\",\n" +
                    "    \"emsg\": \"\",\n" +
                    "    \"data\": {\n" +
                    "        \"head\": {\n" +
                    "            \"ads\": [\n" +
                    "                \"http://img.mukewang.com/54bf7e1f000109c506000338-590-330.jpg\",\n" +
                    "                \"http://upload.techweb.com.cn/2015/0114/1421211858103.jpg\",\n" +
                    "                \"http://img1.cache.netease.com/catchpic/A/A0/A0153E1AEDA115EAE7061A0C7EBB69D2.jpg\",\n" +
                    "                \"http://image.tianjimedia.com/uploadImages/2015/202/27/57RF8ZHG8A4T_5020a2a4697650b89c394237ba9ffbb45fe8555a2cbec-6O6nmI_fw658.jpg\"\n" +
                    "            ],\n" +
                    "            \"middle\": [\n" +
                    "                \"http://img.mukewang.com/54f55ee60001850f05000280.jpg\",\n" +
                    "                \"http://img7.doubanio.com/view/note/large/public/p26832324.jpg\",\n" +
                    "                \"http://img.mukewang.com/546570370001f8a906000338-590-330.jpg\",\n" +
                    "                \"http://img.mukewang.com/55f93f980001f52125001408-590-330.jpg\"\n" +
                    "            ],\n" +
                    "            \"footer\": [\n" +
                    "                {\n" +
                    "                    \"title\": \"5个新ios课程\",\n" +
                    "                    \"info\": \"jack大拿发布\",\n" +
                    "                    \"from\": \"来自上海的讲师\",\n" +
                    "                    \"imageOne\": \"http://i7.hexunimg.cn/2015-07-07/177346079.jpg\",\n" +
                    "                    \"imageTwo\": \"http://imgsrc.baidu.com/forum/w%3D580/sign=2ca8123a261f95caa6f592bef9167fc5/bc1cae51f81986188dd8f8c049ed2e738ad4e6da.jpg\",\n" +
                    "                    \"destationUrl\": \"http://www.imooc.com/learn/677\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"title\": \"3个新android课程\",\n" +
                    "                    \"info\": \"herry-android高级开发\",\n" +
                    "                    \"from\": \"来自北京的讲师\",\n" +
                    "                    \"imageOne\": \"http://imgsrc.baidu.com/forum/w%3D580/sign=4e0ee1bcd2c8a786be2a4a065709c9c7/bc71953eb13533fae99ad268abd3fd1f40345bf5.jpg\",\n" +
                    "                    \"imageTwo\": \"http://images.cnitblog.com/news/66372/201412/041520272014384.png\",\n" +
                    "                    \"destationUrl\": \"http://www.imooc.com/learn/690\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        \"list\": [\n" +
                    "            {\n" +
                    "                \"type\": 2,\n" +
                    "                \"logo\": \"http://v1.qzone.cc/avatar/201305/17/22/06/519639762c4e6138.jpg%21200x200.jpg\",\n" +
                    "                \"title\": \"tb73399384\",\n" +
                    "                \"info\": \"1\",\n" +
                    "                \"price\": \"$1200\",\n" +
                    "                \"text\": \"还在手动写繁锁的页面吗？GRUT让你脱离苦海.\",\n" +
                    "                \"from\": \" 来自北京|新中关\",\n" +
                    "                \"zan\": \"5\",\n" +
                    "                \"url\": [\n" +
                    "                    \"http://img.mukewang.com/54f55ee60001850f05000280.jpg\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 3,\n" +
                    "                \"title\": \"7天搞定Node.js微信公众号@前端到后台ThinkPHP开发整站@组件方式开发WebAPP全站@飞速上手的跨平台App开始@Yii2.0全力出击打造完整电商\",\n" +
                    "                \"price\": \"当前价格:128.00@当前价格:268.00@当前价格:78.00@当前价格:88.00@当前价格:168.00\",\n" +
                    "                \"info\": \"【专治各种疑问】什么是收费课?@【专治各种疑问】为什么要收费?@【专治各种疑问】收费课程的核心价值?@【专治各种疑问】收费课与免费课的区别?@【专治各种疑问】如何购买课程?\",\n" +
                    "                \"text\": \"842人在学@1353人在学@345人在学@1264人在学@737人在学\",\n" +
                    "                \"url\": [\n" +
                    "                    \"http://img.zcool.cn/community/01bba8564aefde32f87512f6a47ea3.jpg\",\n" +
                    "                    \"http://i8.hexunimg.cn/2015-08-21/178515688.jpg\",\n" +
                    "                    \"http://img.mukewang.com/54b619d70001294906000338-590-330.jpg\",\n" +
                    "                    \"http://img.mukewang.com/539a5dac0001199007200404-500-284.jpg\",\n" +
                    "                    \"http://img.mukewang.com/55657fa600015fe206000338-590-330.jpg\",\n" +
                    "                    \"http://img.mukewang.com/5668dc790001aa2b06000338-590-330.jpg\",\n" +
                    "                    \"http://img.mukewang.com/55f93f980001f52125001408-590-330.jpg\",\n" +
                    "                    \"http://img0.imgtn.bdimg.com/it/u=2174499043,1857890027&fm=21&gp=0.jpg\",\n" +
                    "                    \"http://img.mukewang.com/56556ecb00010b7806000338-590-330.jpg\",\n" +
                    "                    \"http://img4.imgtn.bdimg.com/it/u=3072502270,3193310350&fm=21&gp=0.jpg\",\n" +
                    "                    \"http://img.mukewang.com/56e617dd0001583d06000338-590-330.jpg\",\n" +
                    "                    \"http://img.mukewang.com/5487a78e000105b606000338-590-330.jpg\",\n" +
                    "                    \"http://img3.imgtn.bdimg.com/it/u=1105015795,3331123080&fm=21&gp=0.jpg\",\n" +
                    "                    \"http://i0.hdslb.com/bfs/archive/cd265f24dae73ad0afaae638735b67749a8acfb5.jpg\",\n" +
                    "                    \"http://i0.hdslb.com/bfs/archive/db522f316a9f555d8fa9bfe856c5a606612747d6.jpg\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 0,\n" +
                    "                \"logo\": \"http://b.hiphotos.baidu.com/zhidao/pic/item/d1a20cf431adcbefef0f982fabaf2edda3cc9fe4.jpg\",\n" +
                    "                \"title\": \"今日必学\",\n" +
                    "                \"info\": \"1\",\n" +
                    "                \"text\": \"想成为全栈工程师吗，MogoDB你的不二法宝.\",\n" +
                    "                \"site\": \"http://www.imooc.com/learn/595\",\n" +
                    "                \"from\": \"来自南京\",\n" +
                    "                \"zan\": \"10\",\n" +
                    "                \"resource\": \"http://fairee.vicp.net:83/2016rm/0116/baishi160116.mp4\",\n" +
                    "                \"adid\": \"00000001112\",\n" +
                    "                \"clickUrl\": \"http://www.imooc.com/\",\n" +
                    "                \"clickMonitor\": [\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http://imooc.com/click??click=1\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http://imooc.com/click?click=2\"\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"startMonitor\": [\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http://imooc.com/show?impression=1\"\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http: //imooc.com/show?impression=2\"\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"middleMonitor\": [\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http://imooc.com/show?SU=1\",\n" +
                    "                        \"time\": 5\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http: //imooc.com/show?impression=2\",\n" +
                    "                        \"time\": 5\n" +
                    "                    }\n" +
                    "                ],\n" +
                    "                \"endMonitor\": [\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http://imooc.com/show?end=1\",\n" +
                    "                        \"time\": 5\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        \"ver\": \"0\",\n" +
                    "                        \"url\": \"http: //imooc.com/show?end=2\",\n" +
                    "                        \"time\": 5\n" +
                    "                    }\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 1,\n" +
                    "                \"logo\": \"http://img3.duitang.com/uploads/item/201407/01/20140701222607_AnKfj.thumb.224_0.jpeg\",\n" +
                    "                \"title\": \"qndroid\",\n" +
                    "                \"info\": \"2\",\n" +
                    "                \"price\": \"$2400\",\n" +
                    "                \"text\": \"来慕课网,你可以学到任何你想学的知识.看视频还不够？别担心，我们还有大神手把手教你。\",\n" +
                    "                \"from\": \" 来自北京|德外大街\",\n" +
                    "                \"zan\": \"5\",\n" +
                    "                \"url\": [\n" +
                    "                    \"http://img.mukewang.com/549bda090001c53e06000338-590-330.jpg\",\n" +
                    "                    \"http://img.mukewang.com/5707604300018d0406000338-590-330.jpg\",\n" +
                    "                    \"http://articles.csdn.net/uploads/allimg/150617/6_150617172820_1.png\",\n" +
                    "                    \"http://f1.diyitui.com/b3/e1/db/5f/24/ea/d8/59/1e/ea/28/04/b3/57/d6/6f.jpg\",\n" +
                    "                    \"http://upload1.techweb.com.cn/s/320/2015/0527/1432714922459.jpg\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 2,\n" +
                    "                \"logo\": \"http://img0.imgtn.bdimg.com/it/u=3266845821,3017593921&fm=21&gp=0.jpg\",\n" +
                    "                \"title\": \"臭脚0000\",\n" +
                    "                \"info\": \"1\",\n" +
                    "                \"price\": \"$1199\",\n" +
                    "                \"text\": \"还不会IM程序吗？找我就对了，5小时让你从IM小白到大神.\",\n" +
                    "                \"from\": \"来自珠海\",\n" +
                    "                \"zan\": \"2\",\n" +
                    "                \"url\": [\n" +
                    "                    \"http://it.enorth.com.cn/pic2014/002/000/092/00200009288_502cc21c.png\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 1,\n" +
                    "                \"logo\": \"http://minimg.hexun.com/i1.hexunimg.cn/2014-03-07/162829724_250x200.jpg\",\n" +
                    "                \"title\": \"慕课风采\",\n" +
                    "                \"info\": \"3\",\n" +
                    "                \"price\": \"只求赞\",\n" +
                    "                \"text\": \"慕课网，一家致力于为所有互联网学子提供帮助的情怀培训机构，还要犹豫什么，加入慕课网这个大家庭，一起和喜欢的人做喜欢的事儿.\",\n" +
                    "                \"from\": \"来自北京\",\n" +
                    "                \"zan\": \"1\",\n" +
                    "                \"url\": [\n" +
                    "                    \"http://img3.imgtn.bdimg.com/it/u=3328472612,2780698499&fm=15&gp=0.jpg\",\n" +
                    "                    \"http://minimg.hexun.com/i1.hexunimg.cn/2014-03-07/162829724_250x200.jpg\",\n" +
                    "                    \"http://www.tzkd.com/uploadfile/2015/0917/20150917114818920.jpg\"\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"type\": 2,\n" +
                    "                \"logo\": \"http://awb.img.xmtbang.com/img/uploadnew/201510/23/ccaeb2d2abe94fa6b3efe014e9146e94.jpg\",\n" +
                    "                \"title\": \"xiaopangzi\",\n" +
                    "                \"info\": \"3\",\n" +
                    "                \"price\": \"$130\",\n" +
                    "                \"text\": \"还在为面试而发愁吗，还在为BAT而日思夜想吗？来跟大神学面试技巧.\",\n" +
                    "                \"from\": \"来自大连\",\n" +
                    "                \"zan\": \"3\",\n" +
                    "                \"url\": [\n" +
                    "                    \"http://awb.img1.xmtbang.com/cover201510/20151023/thumb/abdaa24b3aa742e880c743e49a516b78.jpg\"\n" +
                    "                ]\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}}");

            if (jsonObject.has(RESULT_CODE)) {

                //如果服务器的响应码和本地响应码一致 则表示成功
                if (jsonObject.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    //不需要解析，直接返回数据的到应用层
                    if (mClass == null) {
                        mListener.onSuccess(result);
                    } else {
                        // 需要将json对象转化为实体
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(jsonObject, mClass);
                        if (obj != null) {
                            //转化正确
                            mListener.onSuccess(obj);
                        } else {
                            //返回的json格式不正确
                            mListener.onFaiure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {

                    //响应码不为0，说明服务器返回数据有问题
                    mListener.onFaiure(new OkHttpException(OTHER_ERROR, jsonObject.optInt(RESULT_CODE)));
                }

            } else {
                //不存在响应码
                mListener.onFaiure(new OkHttpException(OTHER_ERROR, EMPTY_MSG));
            }

        } catch (JSONException e) {
            mListener.onFaiure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }

    }
}
