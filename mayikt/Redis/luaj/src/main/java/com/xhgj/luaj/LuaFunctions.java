package com.xhgj.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2022-03-02 0:24
 */
public class LuaFunctions {
    public static void main(String[] args) throws URISyntaxException {
        // lua脚本文件所在路径
        LuaFunctions luaFunctions = new LuaFunctions();
        String luaFileName = luaFunctions.getClass().getClassLoader().getResource("hello.lua").toURI().getPath();
        // String luaFunctions = "hello.lua";
        // 加载脚本文件func.lua，并编译
        Globals globals = JsePlatform.standardGlobals();         //全局
        LuaValue luaObj = globals.loadfile(luaFileName).call();  //局部

        /**
         * 调用无参lua函数
         */
        LuaValue helloSimple = globals.get(LuaValue.valueOf("helloSimple"));
        LuaValue result = helloSimple.call();
        System.out.println("result=" + result);

        /**
         * 调用有返回，无参数lua函数
         */
        LuaValue hello = luaObj.get(LuaValue.valueOf("hello"));
        String result2 = hello.call().toString();
        System.out.println("result2=" + result2);

        /**
         * 调用返回一个lua对象的lua函数
         */
        LuaValue getObj = luaObj.get(LuaValue.valueOf("getObj"));
        LuaValue hTable = getObj.call();
        //解析返回来的table，这里按照格式，一个个参数去取
        String userId = hTable.get("userId").toString();
        LuaTable servicesTable = (LuaTable) CoerceLuaToJava.coerce(hTable.get("services"), LuaTable.class);
        List<String> servciesList = new ArrayList<>();
        for (int i = 1; i <= servicesTable.length(); i++) {
            int length = servicesTable.get(i).length();
            StringBuilder service = new StringBuilder();
            for (int j = 1; j <= length; j++) {
                service.append(servicesTable.get(i).get(j).toString());
            }
            servciesList.add(service.toString());
        }
        System.out.println("userId:" + userId);
        System.out.println("servcies:" + servciesList);

        /**
         * 传入一个java对象到lua函数
         */
        LuaValue func = luaObj.get(LuaValue.valueOf("readInfo"));
        LuaValue luaValue = new LuaTable();
        luaValue.set("userId", "11111");
        String userIdIn = func.invoke(luaValue).toString();
        System.out.println("readObj-userIdIn:" + userIdIn);
    }
}
