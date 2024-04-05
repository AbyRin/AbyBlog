package com.cpr.abyblog.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class Reptile {
    // 爬取网站：Unsplash-个人收藏夹
    // 公开免费
    private static final String url = "https://unsplash.com/@abyvov/likes";

    //存储已下载图片的Hash，避免重复下载
    private final Set<String> downloadedImageHashes = new HashSet<>();

    // 使用一个单线程的Executor，以确保爬虫任务在后台单独的线程中运行，避免阻塞应用程序主线程
    private final Executor executor = Executors.newSingleThreadExecutor();

    // 启动项目立刻执行
    @PostConstruct
    public void startCrawling() {
        executor.execute(this::crawlAndSaveImages);
    }

    // 定时器
    @Scheduled(fixedRate = 3600000 * 24) // 每天执行一次
    public void scheduleCrawling() {
        executor.execute(this::crawlAndSaveImages);
    }

    // 爬取并保存图片
    private void crawlAndSaveImages() {
        try {
            // 连接到网址并获取页面内容
            Document document = Jsoup.connect(url).get();

            // 选择DOM元素：a.rEAWd
            Elements imgDetailUrls = document.select("a.rEAWd");  // 选择类名为MorZF的div

            // 创建存储图片的目录
            File imageDir = new File("src/main/resources/static/image/new_reptile_image");
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }

            // 遍历同类DOM
            for (Element imgDetailUrl : imgDetailUrls) {
                String imageUrl = "https://unsplash.com" + imgDetailUrl.attr("href");  // 拼接图片详情页url
                System.out.println("已访问到图片详情页，链接为：" + imageUrl);  // 测试用

                 String imageName = imgDetailUrl.attr("title");  // 以a标签的title属性作为图片名

                // 访问图片详情页
                Document detailDocument = Jsoup.connect(imageUrl).get();  // .ignoreContentType(true) // 忽略内容类型检查
                // 选择DOM元素
                Elements hdImageLinks = detailDocument.select("a[data-test=\"non-sponsored-photo-download-button\"]");

                // 遍历每个高清图片链接
                for (Element hdImageLink : hdImageLinks) {
                    // 截取查询字符串前面的部分作为图片链接
                    String hdImageUrl = hdImageLink.attr("href").split("\\?")[0];
                    System.out.println("已爬取原图链接为：" + hdImageUrl);  // 测试用

                    // 调用函数：计算图片的哈希值
                    String hdImageHash = calculateImageHash(hdImageUrl);

                    // 检查图片是否已存在
                    if (!downloadedImageHashes.contains(hdImageHash)) {
                        // 使用URLConnection下载图片
                        downloadImage(hdImageUrl, imageName, imageDir);

                        // 将图片的哈希值添加到已下载的图片集合中
                        downloadedImageHashes.add(hdImageHash);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 下载图片并保存到本地
    private void downloadImage(String imageUrl, String imageName, File imageDir) {
        try {
            System.out.println("开始下载图片：" + imageUrl);   // 打印开始下载消息

            // 构建URL对象
            URL url = new URL(imageUrl);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法
            connection.setRequestMethod("GET");
            // 设置连接超时时间为10秒
            connection.setConnectTimeout(10 * 1000);

            // 获取输入流
            InputStream inputStream = connection.getInputStream();

            // 将输入流复制到输出流
            // 创建 buffer 字符串，设置 4KB 的数据缓冲
            byte[] buffer = new byte[4096];
            // //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int bytesRead;

            // 输出文件流
            File file = new File(imageDir, imageName + ".png");
            // 创建输出流
            FileOutputStream outputStream = new FileOutputStream(file);

            // 使用一个输入流从 buffer 里把数据读取出来
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                //用输出流往 buffer 里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outputStream.write(buffer, 0, bytesRead);
            }

            // 关闭流
            outputStream.close();
            inputStream.close();
            // 断开连接
            connection.disconnect();

            System.out.println("图片下载完成：" + imageUrl); // 打印下载完成消息
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时：" + imageUrl); // 打印超时信息
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 计算图片的哈希值
    private String calculateImageHash(String imageUrl) {
        try {
            // 使用URLConnection下载图片
            InputStream inputStream = new URL(imageUrl).openStream();

            // 使用 MD5 算法计算图片的哈希值
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
            byte[] digest = md.digest();

            // 将字节数组转换为十六进制字符串
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : digest) {
                hashBuilder.append(String.format("%02x", b));
            }

            // 关闭流
            inputStream.close();

            return hashBuilder.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
