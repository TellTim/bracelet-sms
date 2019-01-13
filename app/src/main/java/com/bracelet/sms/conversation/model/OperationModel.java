package com.bracelet.sms.conversation.model;

import android.support.annotation.NonNull;
import android.util.Xml;

import com.bracelet.protocol.model.Operation;
import com.bracelet.sms.app.constant.AppConst;
import com.bracelet.sms.app.constant.StatusConst;
import com.bracelet.sms.app.manager.AppExecutors;
import com.bracelet.sms.base.IBaseCallback;
import com.bracelet.sms.utils.ResourceUtils;
import com.bracelet.sms.utils.ULog;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;



/**
 * @author :Tim.WJ
 * Created on 2019/1/13.
 */
public class OperationModel implements IOperationModel{

    private static final String TAG = "OperationModel";

    private LinkedHashMap<Integer, Operation> mOperationCache;

    private static final OperationModel OURINSTANCE = new OperationModel();

    public static OperationModel getInstance() {
        return OURINSTANCE;
    }

    private OperationModel() {
        mOperationCache = new LinkedHashMap<>();
    }

    /**
     * 应用加载操作数据,没有回调方法,应用数据初始化
     */
    public void initData() {
        AppExecutors.getDiskIOPool().execute(() -> {
            Operation operation = null;
            try {
                //在android下使用xmlpullparser进行解析
                XmlPullParser xmlPullParser = Xml.newPullParser();
                //设置xmlpullparser的一些参数
                xmlPullParser.setInput(ResourceUtils.getAssetFile("operation.xml"), "utf-8");

                //获取pull解析器对应事件类型
                int eventType = xmlPullParser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;

                        case XmlPullParser.START_TAG:
                            if (xmlPullParser.getName().equals(AppConst.Operation.CLASS_NAME)) {
                                operation = new Operation();
                                String index = xmlPullParser.getAttributeValue(0);
                                operation.setIndex(Integer.parseInt(index));
                                eventType = xmlPullParser.next();
                            } else if (xmlPullParser.getName().equals(AppConst.Operation.PARENT_INDEX)) {
                                String parentIndex = xmlPullParser.nextText();
                                ULog.d("operation.parentIndex", parentIndex);
                                operation.setParentIndex(Integer.parseInt(parentIndex));
                                eventType = xmlPullParser.next();
                            } else if (xmlPullParser.getName().equals(AppConst.Operation.RESPONSE_TIMESTAMP)) {
                                String responseTimestamp = xmlPullParser.nextText();
                                ULog.d("operation.responseTimestamp", responseTimestamp);
                                operation.setResponseTimestamp(Long.valueOf(responseTimestamp));
                                eventType = xmlPullParser.next();
                            } else if (xmlPullParser.getName().equals(AppConst.Operation.CONTENT)) {
                                String content = xmlPullParser.nextText();
                                ULog.d("operation.content", content);
                                operation.setContent(content);
                                eventType = xmlPullParser.next();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (xmlPullParser.getName().equals(AppConst.Operation.CLASS_NAME)) {
                                mOperationCache.put(operation.getIndex(), operation);
                                operation = null;
                            }
                            break;
                        default:
                            break;
                    }
                    eventType = xmlPullParser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public List<String> getOperationContent() {
        List<String> result = new ArrayList<>();

        if (mOperationCache.size() == 0) {
            return null;
        }

        for (int index = 0; index < mOperationCache.size(); index++) {
            Operation operation = mOperationCache.get(index);
            result.add(operation.getContent());
        }
        return result;
    }

    @Override
    public void reQueryAllOperation(@NonNull IBaseCallback<List<Operation>> baseCallback) {
        Runnable runnable = () -> {
            mOperationCache.clear();
            List<Operation> operations = null;
            Operation operation = null;
            try {
                //在android下使用xmlpullparser进行解析
                XmlPullParser xmlPullParser = Xml.newPullParser();
                //设置xmlpullparser的一些参数
                xmlPullParser.setInput(ResourceUtils.getAssetFile("operation.xml"), "utf-8");

                //获取pull解析器对应事件类型
                int eventType = xmlPullParser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            operations = new ArrayList<Operation>();
                            break;

                        case XmlPullParser.START_TAG:
                            if (xmlPullParser.getName().equals(AppConst.Operation.CLASS_NAME)) {
                                operation = new Operation();
                                String index = xmlPullParser.getAttributeValue(0);
                                operation.setIndex(Integer.parseInt(index));
                                eventType = xmlPullParser.next();
                            } else if (xmlPullParser.getName().equals(AppConst.Operation.PARENT_INDEX)) {
                                String parentIndex = xmlPullParser.nextText();
                                ULog.d("operation.parentIndex", parentIndex);
                                operation.setParentIndex(Integer.parseInt(parentIndex));
                                eventType = xmlPullParser.next();
                            } else if (xmlPullParser.getName().equals(AppConst.Operation.RESPONSE_TIMESTAMP)) {
                                String responseTimestamp = xmlPullParser.nextText();
                                ULog.d("operation.responseTimestamp", responseTimestamp);
                                operation.setResponseTimestamp(Long.valueOf(responseTimestamp));
                                eventType = xmlPullParser.next();
                            } else if (xmlPullParser.getName().equals(AppConst.Operation.CONTENT)) {
                                String content = xmlPullParser.nextText();
                                ULog.d("operation.content", content);
                                operation.setContent(content);
                                eventType = xmlPullParser.next();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (xmlPullParser.getName().equals(AppConst.Operation.CLASS_NAME)) {
                                mOperationCache.put(operation.getIndex(), operation);
                                operations.add(operation);
                                operation = null;
                            }
                            break;
                        default:
                            break;
                    }
                    eventType = xmlPullParser.next();
                }

                List<Operation> finalOperations = operations;
                AppExecutors.getMainThread().execute(()->{
                    if(finalOperations == null){
                        baseCallback.onFailure(StatusConst.EMPTY_RESULT);
                    }else {
                        baseCallback.onSuccess(finalOperations);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                baseCallback.onFailure(StatusConst.EMPTY_RESULT);
            }
        };
        AppExecutors.getDiskIOPool().execute(runnable);
    }
}
