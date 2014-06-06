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
    int SCREEN_WIDTH = 720;
    /**
     * 屏幕高，视手机而修改
     */
    int SCREEN_HEIGHT = 1280;

    /**
     * 左边距，视手机而修改
     */
    int PADDING_LEFT = 65;
    /**
     * 右边距，视手机而修改
     */
    int PADDING_RIGHT = 65;
    /**
     * 上边距，视手机而修改
     */
    int PADDING_TOP = 246;
    /**
     * 下边距，视手机而修改
     */
    int PADDING_BOTTOM = 246;
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
    float IMAGE_WIDTH = (SCREEN_WIDTH - PADDING_LEFT - PADDING_RIGHT) * 1.0f / BOX_COL;
    /**
     * 图片高
     */
    float IMAGE_HEIGHT = (SCREEN_HEIGHT - PADDING_TOP - PADDING_BOTTOM) * 1.0f / BOX_ROW;
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
    /**
     * 表示游戏方块的数据。 注：华为截图之后的图像成色有变。这里以截到的图片来说明。
     */
    public static final String[] GAME_IMAGE = {
            "1001011001000001001000001111001101010100100111001"/* 大白菜 */,
            "0001000001010100001110001111111000010010110001110"/* 梨 */,
            "0110101001001010010100011111000011000001011000010"/* 茄子(桃子) */,
            "1001000001011001001010011000001001010010000100011"/* 葡萄 */,
            "1001100110010001101000010110111001010010010110110"/* 椰子 */,
            "1100001000010100111100010010110010000101101100001"/* 香蕉 */,
            "1101001001011100101010011011100001010010000000011"/* 草莓 */,
            "0001001001001001110000010010110001100110000111000"/* 荔枝 */,
            "1100110100011101101111001110011000010011000011110"/* 菠萝 */,
            "0110000001011101001100010110110001110110101001110"/* 蓝色的 */,

            "0000101001010010010100010111011000010100100011010"/* 月亮 */,
            "0001001001000001001000011010100101100100100110000"/* 雪花 */,
            "0011001100000100100100010111110100110110101001101"/* 雪人 */,
            "1000001001001101101001000001000110111000101001110"/* 闪电 */,
            "1001001001001101100101001001011010010011001101100"/* 蓝炫风 */,
            "1000110001000100001001001011100100000000010110100"/* 太阳 */,
            "0010010001001100011000011110110100100101100100101"/* 云彩 */,

            "0001000001010101000000011010010001110110100001000"/* 奶油蛋糕 */,
            "0010011000010010001011011010100110001101011001100"/* 咖啡 */,
            "1001000100001001001100001101001000100101000010001"/* 冰淇淋 */,
            "1001100100011011001000011110011000100111001100011"/* 蛋糕（三角） */,
            "1100000001110110110011101110100110000011101100110"/* 柠檬饮料（三角杯子） */,
            "1101001101001000111100010001011010000101110100001"/* 冰棍 */,
            "1101010000010100001000011010001101100110001101000"/* 卷心蛋糕 */,

            "0010001011011000110101100010010110110000100010100"/* 小狗 */,
            "0000100011010110010101010011000100100100100010100"/* 黄色的小熊 */,
            "0001000011010100000110010100110000010010100010110"/* 蓝色的动物 */,
            "0001100001000101010011001010011001110001101100001"/* 猪 */,
            "0110001011010000000100010000100110000110100000110"/* 兔子 */,
            "0010110000010100100100010000101001010010011010010"/* 狐狸 */,
            "0010110010010100110011011010110000100101001100101"/* 猴子 */,
            "0000110011010011010001011011001111110001111100000"/* 熊猫 */,

            "0101001001010010000100000101001101010110101001011"/* 温度计 */,
            "1001010101001001101000001101011000100001100011111"/* 铅笔 */,
            "0011111001100111000001001001101100010001110100101"/* 雨伞 */, };
}
