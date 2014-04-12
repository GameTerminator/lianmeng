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
public interface HTC_T528T_V1 {
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
    int PADDING_RIGHT = 72;
    /**
     * 上边距，视手机而修改
     */
    int PADDING_TOP = 115;
    /**
     * 下边距，视手机而修改
     */
    int PADDING_BOTTOM = 115;
    /**
     * 游戏方块列数（竖屏情况下，因为截的图是竖屏）
     */
    int BOX_COL = 5;
    /**
     * 游戏方块行数（竖屏情况下，因为截的图是竖屏）
     */
    int BOX_ROW = 10;
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
     * 截除的边角宽，视手机而修改（主要是去除道具影响）
     */
    int CORNER_WIDTH = 24;
    /**
     * 截除的边角高，视手机而修改（主要是去除道具影响）
     */
    int CORNER_HEIGHT = 27;
    /**
     * 表示每个方块图像的HASH值，视具体手机截取的图像而定。本次为HTC t528t下计算的结果。
     */
    String[] GAME_IMAGE = {
            "0110000100110010101000110111110000010010101001110"/* 煎蛋 */,
            "0000001100000000011110100101100110111100000110000"/* 紫猫 */,
            "0010000101010101010100101110100000110101001101111"/* 白菜 */,
            "0000001001101001100011100110101110010100000010011"/* 茄子 */,
            "1001100100000101001100100111001100101110110100010"/* 兔子 */,
            "1000010001000101111010100100100011010010111011000"/* 莲藕 */,
            "0010010010100100101100110101100011011010010010001"/* 红虾 */,
            "1000000000101000100010111000110000011001111011100"/* 玉米 */,
            "0001100001100101101001010100111001101010110101100"/* 闪电 */,
            "0000000000100001010100011111101010010000000100000"/* 狐狸 */,
            "1100000101000000011110111011010011011100001100000"/* 白云 */,
            "1000011000110101100000110100010001110011001100000"/* 菠萝 */,
            "1000111001100101101010110100100001110110101011000"/* 草莓 */,
            "0000000001110011100001000011001110111001001100110"/* 蘑菇 */,
            "1111011000110100111001110100000101011000011100111"/* 蓝鼠 */,
            "1000000000001000111100110000110011110000011011101"/* 太阳 */,
            "1001100000001101100010111000110001010001110011100"/* 月亮 */,
            "1011100000001110100100101100010100101001011011010"/* 雪人 */,
            "1000011001101101011000111100101001111100011001100"/* 熊猫 */,
            "1000000000000001010100001110101010111000010100000"/* 黄熊 */,
            "1000000110100011001110111001100110001100001001011"/* 彩虹 */,
            "1010100000001001001000101001010111011000111001000"/* 雪花 */,
            "1000110001110001100010100000100111010100010010000"/* 西瓜 */,
            "1000001101011000011110110001010011001001010110110"/* 香蕉 */,
            "0001100000001101000001000000001100001001110100000"/* 蓝果实 */,
            "1000100011101111010001000110001000000010010100110"/* 葡萄 */,
            "0000100000011100110000101010100000111000101000111"/* 红果实 */,
            "1001100000110001000110110011001110001011000110011"/* 黄梨 */, };
}
