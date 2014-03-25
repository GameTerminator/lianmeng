/*
 * @(#)Robot.java	       Project:lianmeng
 * Date-Time:2013-10-11 下午6:57:31
 *
 * Copyright (c) 2013 CFuture09, Institute of Software, 
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.android.chimpchat.adb.AdbBackend;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.IChimpImage;
import com.android.chimpchat.core.TouchPressType;

/**
 * 主要操作类，进行截图，识别，转换，消除，按键等。
 * 
 * @author Geek_Soledad <a target="_blank" href=
 *         "http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=XTAuOSVzPDM5LzI0OR0sLHM_MjA"
 *         style="text-decoration:none;"><img src=
 *         "http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"
 *         /></a>
 */
public class Robot {
    /**
     * 按键延迟时间。太小会导致手机卡，从而影响截图及后续的模拟按键。
     */
    private static final int TOUCH_DELAY = 20;

    /**
     * 屏幕宽，视手机而修改
     */
    private static final int SCREEN_WIDTH = 480;
    /**
     * 屏幕高，视手机而修改
     */
    private static final int SCREEN_HEIGHT = 800;

    /**
     * 左边距，视手机而修改
     */
    private static final int PADDING_LEFT = 48;
    /**
     * 右边距，视手机而修改
     */
    private static final int PADDING_RIGHT = 72;
    /**
     * 上边距，视手机而修改
     */
    private static final int PADDING_TOP = 115;
    /**
     * 下边距，视手机而修改
     */
    private static final int PADDING_BOTTOM = 115;
    /**
     * 游戏方块列数（竖屏情况下，因为截的图是竖屏）
     */
    private static final int BOX_COL = 5;
    /**
     * 游戏方块行数（竖屏情况下，因为截的图是竖屏）
     */
    private static final int BOX_ROW = 10;
    /**
     * 图片宽
     */
    private static final int IMAGE_WIDTH = (SCREEN_WIDTH - PADDING_LEFT - PADDING_RIGHT) / BOX_COL;
    /**
     * 图片高
     */
    private static final int IMAGE_HEIGHT = (SCREEN_HEIGHT - PADDING_TOP - PADDING_BOTTOM)
            / BOX_ROW;
    /**
     * 截除的边角宽，视手机而修改（主要是去除道具影响）
     */
    private static final int CORNER_WIDTH = 24;
    /**
     * 截除的边角高，视手机而修改（主要是去除道具影响）
     */
    private static final int CORNER_HEIGHT = 27;

    /**
     * 数组行数。会比方块多两行是因为在进行连连看路径搜索时，需要增加顶部及底部一行。
     */
    private static final int CODE_ROW = 12;
    /**
     * 数组列数。会比方块多两列，原因如上。
     */
    private static final int CODE_COL = 7;

    /**
     * 表示每个方块图像的HASH值，视具体手机截取的图像而定。本次为HTC t528t下计算的结果。
     */
    private static final String[] GAME_IMAGE = {
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

    private BufferedImage images[][] = new BufferedImage[BOX_ROW][BOX_COL];
    /**
     * 表示图片的数组，为12 * 7个。 图片共有10*5个单位，但是在进行路径计算的时候还要考虑四周，所以是12 * 7 个单位。
     */
    private int imageCodes[][];
    /**
     * 用于计算图片hash值。
     */
    private ImageHash mImgHash = null;
    /**
     * 用于发送模拟按键事件。
     */
    private IChimpDevice mChimpDevice;
    /**
     * 用于调用adb连接手机及断开。
     */
    private AdbBackend adbBack;

    public Robot() {
        mImgHash = new ImageHash();
        adbBack = new AdbBackend();
        mChimpDevice = adbBack.waitForConnection();
    }

    /**
     * 截图
     */
    public BufferedImage snapshot() {
        IChimpImage img;
        // 当尝试次数太多时不再尝试。
        int tryTimes = 0;
        do {
            System.out.println("截图中.." + tryTimes);
            img = mChimpDevice.takeSnapshot();
            tryTimes++;
        } while (img == null && tryTimes < 15);
        if (img == null && tryTimes >= 15) {
            throw new RuntimeException("try to much times to take snapshot but failed");
        }
        return img.getBufferedImage();
    }

    /**
     * 该函数仅用来做一些测试，及获取方块的HASH值。并不在正式流程中调用。
     */
    public void test(File file) {
        try {
            System.out.println("test...");
            BufferedImage image = ImageIO.read(file);

            ImageHash p = new ImageHash();
            for (int i = 0; i < images.length; i++) {
                for (int j = 0; j < images[i].length; j++) {
                    images[i][j] = image.getSubimage(j * IMAGE_WIDTH + PADDING_LEFT + 3, i
                            * IMAGE_HEIGHT + PADDING_TOP + 3, IMAGE_WIDTH - CORNER_WIDTH - 3,
                            IMAGE_HEIGHT - CORNER_HEIGHT - 3);
                }
            }
            String gameHashs[] = new String[] { p.getHash(images[5][3]), p.getHash(images[1][0]),
                    p.getHash(images[1][2]), p.getHash(images[2][0]), p.getHash(images[2][4]), };
            for (String string : gameHashs) {
                System.out.println(string);
            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < images.length; i++) {
                for (int j = 0; j < images[i].length; j++) {
                    images[i][j] = image.getSubimage(j * IMAGE_WIDTH + PADDING_LEFT + 3, i
                            * IMAGE_HEIGHT + PADDING_TOP + 3, IMAGE_WIDTH - CORNER_WIDTH - 3,
                            IMAGE_HEIGHT - CORNER_HEIGHT - 3);
                    String hash = p.getHash(images[i][j]);
                    // System.out.println(i + ":" + j + " " + hash);
                    int minDis = Integer.MAX_VALUE;
                    for (int k = 0; k < GAME_IMAGE.length; k++) {
                        int dis = p.distance(GAME_IMAGE[k], hash);
                        if (dis < 5 && dis <= minDis) {
                            imageCodes[i + 1][j + 1] = k + 1;
                            minDis = dis;
                        }
                    }
                    System.out.print(imageCodes[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 触摸
     * 
     * @param p
     * @throws InterruptedException
     */
    public void touch(Point p) throws InterruptedException {
        Thread.sleep(TOUCH_DELAY);
        // 截图使用的是竖屏，这里触摸使用的是横屏
        int x = PADDING_TOP + (p.x - 1) * IMAGE_HEIGHT + CORNER_HEIGHT;
        int y = 480 - (PADDING_LEFT + (p.y - 1) * IMAGE_WIDTH + CORNER_WIDTH);
        mChimpDevice.touch(x, y, TouchPressType.DOWN_AND_UP);
    }

    /**
     * 通过获取的截图设置num数组
     */
    public void setNum(BufferedImage image) {
        imageCodes = new int[CODE_ROW][CODE_COL];
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                images[i][j] = image.getSubimage(j * IMAGE_WIDTH + PADDING_LEFT + 3, i
                        * IMAGE_HEIGHT + PADDING_TOP + 3, IMAGE_WIDTH - CORNER_WIDTH - 3,
                        IMAGE_HEIGHT - CORNER_HEIGHT - 3);
                String hash = mImgHash.getHash(images[i][j]);
                int minDis = Integer.MAX_VALUE;
                for (int k = 0; k < GAME_IMAGE.length; k++) {
                    int dis = mImgHash.distance(GAME_IMAGE[k], hash);
                    if (dis <= 8 && dis < minDis) {
                        imageCodes[i + 1][j + 1] = k + 1;
                        minDis = dis;
                        if (minDis <= 0) {
                            break;
                        }
                    }
                }
                // System.out.print(imageCodes[i + 1][j + 1] + "\t");
            }
            // System.out.println();
        }
    }

    public boolean startSearch() throws InterruptedException {
        boolean anyClear = false;
        do {
            anyClear = false;
            for (int i = 1; i < CODE_ROW - 1; i++) {
                for (int j = 1; j < CODE_COL - 1; j++) {
                    if (imageCodes[i][j] != 0) {
                        Point point = search(i, j);
                        if (point == null) {
                            continue;
                        }
                        touch(new Point(i, j));
                        touch(point);
                        imageCodes[i][j] = 0;
                        imageCodes[point.x][point.y] = 0;
                        anyClear = true;
                        System.out
                                .println(String.format("消除 %d:%d  %d:%d", i, j, point.x, point.y));
                    }
                }
            }
        } while (anyClear && !isEmpty());
        return anyClear;
    }

    /**
     * 是否全部消除完毕
     * 
     * @return 当且仅当全部消除完毕时返回true，否则返回false。
     */
    private boolean isEmpty() {
        for (int i = 1; i < CODE_ROW - 1; i++) {
            for (int j = 1; j < CODE_COL - 1; j++) {
                if (imageCodes[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 搜索，返回最优点。
     * 
     * @return
     */
    public Point search(int x, int y) {
        return LianlianKan.twoCornerSearch(imageCodes, x, y);
    }

    public void terminate() {
        if (adbBack != null) {
            adbBack.shutdown();
        }
    }

    /**
     * 连连看搜索算法。其实用递归，代码会更少。不过循环效率会略高。
     * 
     * @author Geek_Soledad
     */
    public static class LianlianKan {
        /**
         * 水平搜索
         * 
         * @param datas
         *            数组
         * @param row
         *            对比数值所在的行
         * @param col
         *            对比数值所在的列
         * @param atRow
         *            进行水平搜索时所在的行
         * @return
         */
        private static Point moveHorizon(int[][] datas, int row, int col, int atRow, int atCol) {
            // 再拐左
            for (int k = atCol - 1; k >= 1; k--) {
                if (datas[atRow][k] == datas[row][col]) {
                    return new Point(atRow, k);
                } else if (datas[atRow][k] != 0) {
                    break;
                }
            }
            // 再拐右
            for (int k = atCol + 1; k < CODE_COL - 1; k++) {
                if (datas[atRow][k] == datas[row][col]) {
                    return new Point(atRow, k);
                } else if (datas[atRow][k] != 0) {
                    break;
                }
            }
            return null;
        }

        /**
         * 竖直搜索
         * 
         * @param datas
         *            数组
         * @param row
         *            对比数值所在的行
         * @param col
         *            对比数值所在的列
         * @param atCol
         *            进行水平搜索时所在的列
         * @return
         */
        private static Point movePortrait(int[][] datas, int row, int col, int atRow, int atCol) {
            // 再拐上
            for (int k = atCol - 1; k >= 1; k--) {
                if (datas[k][atCol] == datas[row][col]) {
                    return new Point(k, atCol);
                } else if (datas[k][atCol] != 0) {
                    break;
                }
            }
            // 再拐下
            for (int k = atCol + 1; k < CODE_ROW - 1; k++) {
                if (datas[k][atCol] == datas[row][col]) {
                    return new Point(k, atCol);
                } else if (datas[k][atCol] != 0) {
                    break;
                }
            }
            return null;
        }

        /**
         * 两个角搜索（包括直线、直角搜索）
         * 
         * @param datas
         *            数组
         * @param x
         *            纵坐标
         * @param y
         *            横坐标
         * @return
         */
        public static Point twoCornerSearch(int[][] datas, int x, int y) {
            // 向左进行搜索
            for (int i = y - 1; i >= 0; i--) {
                // 向左进行直线搜索
                if (datas[x][i] == datas[x][y]) {
                    return new Point(x, i);
                } else if (datas[x][i] != 0) {
                    break;
                }
                // 向左，然后拐上进行直角搜索
                for (int j = x - 1; j >= 0; j--) {
                    if (datas[j][i] == datas[x][y]) {
                        return new Point(j, i);
                    } else if (datas[j][i] != 0) {
                        break;
                    }
                    // 向左，拐上，再水平搜索
                    Point point = moveHorizon(datas, x, y, j, i);
                    if (point != null) {
                        return point;
                    }
                }
                // 向左，然后拐下进行直角搜索
                for (int j = x + 1; j < CODE_ROW; j++) {
                    if (datas[j][i] == datas[x][y]) {
                        return new Point(j, i);
                    } else if (datas[j][i] != 0) {
                        break;
                    }
                    // 向左，拐下，再水平搜索
                    Point point = moveHorizon(datas, x, y, j, i);
                    if (point != null) {
                        return point;
                    }
                }
            }
            // 横向往右搜索
            for (int i = y + 1, length = CODE_COL; i < length; i++) {
                // 向右直线搜索
                if (datas[x][i] == datas[x][y]) {
                    return new Point(x, i);
                } else if (datas[x][i] != 0) {
                    break;
                }
                // 向右，然后拐上进行直角搜索。
                for (int j = x - 1; j >= 0; j--) {
                    if (datas[j][i] == datas[x][y]) {
                        return new Point(j, i);
                    } else if (datas[j][i] != 0) {
                        break;
                    }
                    // 向右，拐上，再水平搜索
                    Point point = moveHorizon(datas, x, y, j, i);
                    if (point != null) {
                        return point;
                    }
                }
                // 向右，然后拐下进行直角搜索
                for (int j = x + 1; j < CODE_ROW; j++) {
                    if (datas[j][i] == datas[x][y]) {
                        return new Point(j, i);
                    } else if (datas[j][i] != 0) {
                        break;
                    }
                    // 向右，拐下，再水平搜索。
                    Point point = moveHorizon(datas, x, y, j, i);
                    if (point != null) {
                        return point;
                    }
                }
            }
            // 纵向往上搜索
            for (int i = x - 1; i >= 0; i--) {
                if (datas[i][y] == datas[x][y]) {
                    return new Point(i, y);
                } else if (datas[i][y] != 0) {
                    break;
                }
                // 向上，然后拐左进行直角搜索。
                for (int j = y - 1; j >= 0; j--) {
                    if (datas[i][j] == datas[x][y]) {
                        return new Point(i, j);
                    } else if (datas[i][j] != 0) {
                        break;
                    }
                    // 向上，拐左，再竖直搜索
                    Point point = movePortrait(datas, x, y, i, j);
                    if (point != null) {
                        return point;
                    }
                }
                // 向上，然后拐右进行直角搜索
                for (int j = y + 1; j < CODE_COL; j++) {
                    if (datas[i][j] == datas[x][y]) {
                        return new Point(i, j);
                    } else if (datas[i][j] != 0) {
                        break;
                    }
                    // 向上，拐右，再竖直搜索
                    Point point = movePortrait(datas, x, y, i, j);
                    if (point != null) {
                        return point;
                    }

                }
            }
            // 纵向往下搜索
            for (int i = x + 1, length = CODE_ROW; i < length; i++) {
                if (datas[i][y] == datas[x][y]) {
                    return new Point(i, y);
                } else if (datas[i][y] != 0) {
                    break;
                }
                // 向下，然后拐左进行直角搜索。
                for (int j = y - 1; j >= 0; j--) {
                    if (datas[i][j] == datas[x][y]) {
                        return new Point(i, j);
                    } else if (datas[i][j] != 0) {
                        break;
                    }
                    // 向下，拐右，再竖直搜索
                    Point point = movePortrait(datas, x, y, i, j);
                    if (point != null) {
                        return point;
                    }
                }
                // 向下，然后拐右进行直角搜索
                for (int j = y + 1; j < CODE_COL; j++) {
                    if (datas[i][j] == datas[x][y]) {
                        return new Point(i, j);
                    } else if (datas[i][j] != 0) {
                        break;
                    }
                    // 向下，拐右，再竖直搜索
                    Point point = movePortrait(datas, x, y, i, j);
                    if (point != null) {
                        return point;
                    }
                }
            }
            return null;
        }
    }
}
