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
package pw.msdx.lianmengassistant.configs;

/**
 * @author Geek_Soledad <a target="_blank" href=
 *         "http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=XTAuOSVzPDM5LzI0OR0sLHM_MjA"
 *         style="text-decoration:none;"><img src=
 *         "http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"
 *         /></a>
 */
public interface HUAWEI_3C_V2 {
    /**
     * 屏幕宽，视手机而修改
     */
    int SCREEN_WIDTH = 480;
    /**
     * 屏幕高，视手机而修改
     */
    int SCREEN_HEIGHT = 800;

    /**
     * 左边距，视手机而修改
     */
    int PADDING_LEFT = 48;
    /**
     * 右边距，视手机而修改
     */
    int PADDING_RIGHT = 40;
    /**
     * 上边距，视手机而修改
     */
    int PADDING_TOP = 152;
    /**
     * 下边距，视手机而修改
     */
    int PADDING_BOTTOM = 151;
    /**
     * 游戏方块列数（竖屏情况下，因为截的图是竖屏）
     */
    int BOX_COL = 7;
    /**
     * 游戏方块行数（竖屏情况下，因为截的图是竖屏）
     */
    int BOX_ROW = 7;
    /**
     * 数组行数。会比方块多两行是因为在进行连连看路径搜索时，需要增加顶部及底部一行。
     */
    int CODE_ROW = BOX_ROW + 2;
    /**
     * 数组列数。会比方块多两列，原因如上。
     */
    int CODE_COL = BOX_COL + 2;
    /**
     * 图片宽
     */
    int IMAGE_WIDTH = (SCREEN_WIDTH - PADDING_LEFT - PADDING_RIGHT) / BOX_COL;
    /**
     * 图片高
     */
    int IMAGE_HEIGHT = (SCREEN_HEIGHT - PADDING_TOP - PADDING_BOTTOM) / BOX_ROW;
    /**
     * 截除的边角左边距
     */
    public static final int CORNER_LEFT = 3;
    /**
     * 截除的边角上边距
     */
    public static final int CORNER_TOP = 3;
    /**
     * 截除的边角右边距
     */
    public static final int CORNER_RIGHT = 3; 
    /**
     * 截除的边角下边距
     */
    public static final int CORNER_BOTTOM = 3;
}
