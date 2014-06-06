/*
 * @(#)Main.java	       Project:lianmeng
 * Date-Time:2013-10-17 下午12:40:34
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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

public class Main {
    private static BufferedImage img = null;
    private static Executor executors = Executors.newCachedThreadPool();
    private static boolean isOver = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        // splitBoxes();
        // loopSaveSnapshot();
        // testForV2();
        playGame();
    }

    public static void testForV2() {
        Robot robot = new Robot();
        try {
            while (true) {
                robot.splitAndGetHashValue(new File("E:\\tmp\\"));
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void splitBoxes() {
        Robot.test(new File("E:\\tmp\\1402051737673.png"));
        System.exit(0);
    }

    /**
     * 循环保存截图。
     */
    public static void loopSaveSnapshot() {
        final Robot robot = new Robot();
        try {
            while (true) {
                BufferedImage img = robot.snapshot();
                if (img != null) {
                    File file = new File("E:\\tmp\\" + System.currentTimeMillis() + ".png");
                    ImageIO.write(img, "png", file);
                    // robot.test(file);
                }
                Thread.sleep(2000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testBox() {
        System.out.println("testBox");
        final Robot robot = new Robot();
        BufferedImage image = robot.snapshot();
        try {
            File file = new File("E:\\tmp.png");
            ImageIO.write(image, "png", file);
            Robot.test(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playGame() throws IOException, InterruptedException {
        final Robot robot = new Robot();
        final long startTime = System.currentTimeMillis();
        new Thread() {
            public void run() {
                try {
                    while (!isOver) {
                        executors.execute(new Runnable() {

                            @Override
                            public void run() {
                                img = robot.snapshot();
                            }
                        });
                        Thread.sleep(350);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        BufferedImage currentImage = null;
        long lastClearTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - lastClearTime < 5000
                || System.currentTimeMillis() - startTime < 60000) {
            long snapTime = System.currentTimeMillis();

            while (img == null || img == currentImage) {
                Thread.sleep(50);
            }
            currentImage = img;
            System.out.println("snapTime:" + (System.currentTimeMillis() - snapTime));
            robot.setNum(img);
            if (robot.startSearch()) {
                System.out.println("有清除");
                lastClearTime = System.currentTimeMillis();
            }
            System.out.println("playTime:" + (System.currentTimeMillis() - snapTime));
        }
        System.out.println("Maybe game over");
        isOver = true;
        System.exit(0);
    }

}
