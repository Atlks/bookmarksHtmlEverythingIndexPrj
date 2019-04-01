package com.attilax.wechatToto;

import java.util.Map;

import org.apache.http.auth.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

import com.alibaba.fastjson.JSONObject;
 

/**implements MenuService
 * 对订阅号的菜单的操作
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
 
public class MenuSeviceImpl  {

    //@Autowired
//    private ISysConfigService iSysConfigService;

    private static Logger log = LoggerFactory.getLogger(MenuSeviceImpl.class);

    // 菜单创建（POST） 限1000（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 菜单查询（POST） 限10000（次/天）
    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    // 菜单删除（POST） 限1000（次/天）
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**
     * 查询菜单
     * @param accessToken 有效的access_token
     * @return
     * @throws AuthenticationException 
     */
    public JSONObject getMenu(String accessToken)   {

        // 拼装创建菜单的url
        String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口查询菜单
        JSONObject jsonObject = HttpUtil.httpRequest(url, "GET", null);
        
        if(jsonObject.getInteger("errcode")!=null)
        	throw new RuntimeException(jsonObject.toJSONString());

        return jsonObject;
    }

    /**
     * 创建菜单(替换旧菜单)
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public int createMenu(String menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        log.info("菜单url" + url);
        // 将菜单对象转换成json字符串
        // String jsonMenu = JSONObject.toJSONString(menu);
     //   SysConfig config = iSysConfigService.selectOne(new EntityWrapper<SysConfig>().eq("type", 0).eq("keyCode", "Menu"));
        // log.info("菜单:" + jsonMenu);
        // 调用接口创建菜单
        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", menu);

        if (null != jsonObject && jsonObject.getInteger("errcode")==null) {
           
              throw new RuntimeException(jsonObject.toJSONString());
        }
        return result;
    }

//    public int createMenu(Map<String, Object> menu, String accessToken) {
//        int result = 0;
//
//        // 拼装创建菜单的url
//        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
//        log.info("菜单url" + url);
//        // 将菜单对象转换成json字符串
//        // String jsonMenu = JSONObject.toJSONString(menu);
//        SysConfig config = iSysConfigService.selectOne(new EntityWrapper<SysConfig>().eq("type", 0).eq("keyCode", "Menu"));
//        // log.info("菜单:" + jsonMenu);
//        // 调用接口创建菜单
//        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", config.getValue());
//
//        if (null != jsonObject) {
//            if (0 != jsonObject.getInteger("errcode")) {
//                result = jsonObject.getInteger("errcode");
//                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
//                log.error("****" + config.getValue() + "****");
//            }
//        }
//        return result;
//    }

    /**
     * 删除菜单
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public int deleteMenu(String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);

        // 调用接口创建菜单
        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", null);

        if (null != jsonObject) {
            if (0 != jsonObject.getInteger("errcode")) {
                result = jsonObject.getInteger("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
