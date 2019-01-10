package com.bracelet.sms.utils;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author :Tim.WJ
 * Created on 2019/1/10.
 */
public class ResourceUtils {

    public static InputStream getAssetFile(String filename) throws IOException {
        AssetManager manager = UIUtils.getContext().getAssets();
        InputStream inputStream = manager.open(filename);
        return inputStream;
    }
}
