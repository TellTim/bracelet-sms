package com.bracelet.sms.conversation.model;

import android.util.Xml;

import com.bracelet.protocol.model.Operation;
import com.bracelet.sms.utils.ResourceUtils;
import com.bracelet.sms.utils.ULog;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Tim.WJ
 * Created on 2019/1/10.
 */
public class OperationModel /*implements IOperationModel*/ {



    public static List<Operation> getOperations() {

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
                        if (xmlPullParser.getName().equals("operation")) {
                            operation = new Operation();
                            String index = xmlPullParser.getAttributeValue(0);
                            operation.setIndex(Integer.parseInt(index));
                            eventType = xmlPullParser.next();
                        } else if (xmlPullParser.getName().equals("parentIndex")) {
                            String parentIndex = xmlPullParser.nextText();
                            ULog.d("operation.parentIndex", parentIndex);
                            operation.setParentIndex(Integer.parseInt(parentIndex));
                            eventType = xmlPullParser.next();
                        } else if (xmlPullParser.getName().equals("responseTimestamp")) {
                            String responseTimestamp = xmlPullParser.nextText();
                            ULog.d("operation.responseTimestamp", responseTimestamp);
                            operation.setResponseTimestamp(Long.valueOf(responseTimestamp));
                            eventType = xmlPullParser.next();
                        } else if (xmlPullParser.getName().equals("content")) {
                            String content = xmlPullParser.nextText();
                            ULog.d("operation.content", content);
                            operation.setContent(content);
                            eventType = xmlPullParser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (xmlPullParser.getName().equals("operation")) {
                            operations.add(operation);
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
        return operations;
    }
}
