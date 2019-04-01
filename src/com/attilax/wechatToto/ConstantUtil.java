/**
 * Copyright 2018-2020 jerry.wang
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.attilax.wechatToto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量
 * @author jerry.wang
 * @date 2017-08-17 16:55
 */
@Component
public class ConstantUtil {

    @Value("${tt.file-upload.base-savepath}")
    public static String baseSavePath;

    @Value("${tt.wx.appid}")
    public static String APPID;

    @Value("${tt.wx.appid}")
    public void setAppid(String appid) {
        APPID = appid;
    }

    @Value("${tt.file-upload.http-domain}")
    public static String httpDomain;

    @Value("${tt.file-upload.base-savepath}")
    public void setBaseSavePath(String savePath) {
        baseSavePath = savePath;
    }

    @Value("${tt.file-upload.http-domain}")
    public void setHttpDomain(String domain) {
        httpDomain = domain;
    }

}
