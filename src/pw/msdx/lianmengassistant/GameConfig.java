/*
 * @(#)GameConfig.java		       Project:lianmeng
 * Date:2014-3-25
 *
 * Copyright (c) 2014 CFuture09, Institute of Software, 
 * Guangdong Ocean University, Zhanjiang, GuangDong, China.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pw.msdx.lianmengassistant;

import pw.msdx.lianmengassistant.configs.HTC_T528T_V1;
import pw.msdx.lianmengassistant.configs.HUAWEI_3C_V2;

/**
 * @author Geek_Soledad <a target="_blank" href=
 *         "http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=XTAuOSVzPDM5LzI0OR0sLHM_MjA"
 *         style="text-decoration:none;"><img src=
 *         "http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"
 *         /></a>
 */
public class GameConfig {
    public static final Class<?> config = HUAWEI_3C_V2.class;
    
    private static final int getInt(String name) {
        try {
            return config.getDeclaredField(name).getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("could not load the config");
    }
    private static final float getFloat(String name) {
        try {
            return config.getDeclaredField(name).getFloat(null);
        } catch (Exception e) {
            e.printStackTrace();
            return getInt(name);
        }
    }
    /**
     * 屏幕宽，视手机而修改
     */
    public static final int SCREEN_WIDTH = getInt("SCREEN_WIDTH");
    /**
     * 屏幕高，视手机而修改
     */
    public static final int SCREEN_HEIGHT = getInt("SCREEN_HEIGHT");

    /**
     * 左边距，视手机而修改
     */
    public static final int PADDING_LEFT = getInt("PADDING_LEFT");
    /**
     * 右边距，视手机而修改
     */
    public static final int PADDING_RIGHT = getInt("PADDING_RIGHT");
    /**
     * 上边距，视手机而修改
     */
    public static final int PADDING_TOP = getInt("PADDING_TOP");
    /**
     * 下边距，视手机而修改
     */
    public static final int PADDING_BOTTOM = getInt("PADDING_BOTTOM");
    /**
     * 游戏方块列数（竖屏情况下，因为截的图是竖屏）
     */
    public static final int BOX_COL = getInt("BOX_COL");
    /**
     * 游戏方块行数（竖屏情况下，因为截的图是竖屏）
     */
    public static final int BOX_ROW = getInt("BOX_ROW");
    /**
     * 数组行数。会比方块多两行是因为在进行连连看路径搜索时，需要增加顶部及底部一行。
     */
    public static final int CODE_ROW = getInt("CODE_ROW");
    /**
     * 数组列数。会比方块多两列，原因如上。
     */
    public static final int CODE_COL = getInt("CODE_COL");
    /**
     * 图片宽
     */
    public static final float IMAGE_WIDTH = getFloat("IMAGE_WIDTH");
    /**
     * 图片高
     */
    public static final float IMAGE_HEIGHT = getFloat("IMAGE_HEIGHT");
    /**
     * 截除的边角左边距
     */
    public static final int CORNER_LEFT = getInt("CORNER_LEFT");
    /**
     * 截除的边角上边距
     */
    public static final int CORNER_TOP = getInt("CORNER_TOP");
    /**
     * 截除的边角右边距
     */
    public static final int CORNER_RIGHT = getInt("CORNER_RIGHT"); 
    /**
     * 截除的边角下边距
     */
    public static final int CORNER_BOTTOM = getInt("CORNER_BOTTOM");
    
    /**
     * 表示游戏方块的数据。
     */
    public static final String[] GAME_IMAGE = getStringArray("GAME_IMAGE");
    
    private static final String[] getStringArray(String name) {
        try {
            return (String[]) config.getDeclaredField(name).get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("could not load the config");
    }
}
