package com.cpr.abyblog.reptile;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class Reptile {
    private static String url = "https://unsplash.com/@abyvov/likes";
    private Set<String> downloadedImageHashes = new HashSet<>();

    // 使用一个单线程的Executor，以确保爬虫任务在后台单独的线程中运行，避免阻塞应用程序主线程
    private final Executor executor = Executors.newSingleThreadExecutor();

    // 启动项目立刻执行
    @PostConstruct
    public void startCrawling() {
        executor.execute(this::crawlAndSaveImages);
    }

    // 定时器
    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void scheduleCrawling() {
        executor.execute(this::crawlAndSaveImages);
    }

    // 爬取并保存图片
    private void crawlAndSaveImages() {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.MorZF");  // 选择类名为MorZF的div
            File imageDir = new File("src/main/resources/static/image/reptile_image");
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }

            for (int i = 0; i < elements.size(); i++) {
                Elements imgElement = elements.get(i).select("img");
                String imageUrl = imgElement.attr("src");
                String imageName = String.valueOf(i);

                // 调用函数：计算图片的哈希值
                String imageHash = calculateImageHash(imageUrl);

                // 检查图片是否已存在
                if (!downloadedImageHashes.contains(imageHash)) {
                    Connection.Response response = Jsoup.connect(imageUrl)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                            .ignoreContentType(true)
                            .execute();
                    ByteArrayInputStream stream = new ByteArrayInputStream(response.bodyAsBytes());

                    // 将图片的二进制数据保存到本地文件
                    FileUtils.copyInputStreamToFile(stream, new File(imageDir, imageName + ".png"));

                    // 将图片的哈希值添加到已下载的图片集合中
                    downloadedImageHashes.add(imageHash);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 计算图片的哈希值
    private String calculateImageHash(String imageUrl) {
        try {
            byte[] imageBytes = Jsoup.connect(imageUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                    .ignoreContentType(true)
                    .execute()
                    .bodyAsBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(imageBytes);
            byte[] digest = md.digest();
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : digest) {
                hashBuilder.append(String.format("%02x", b));
            }
            return hashBuilder.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // 或者抛出其他适当的异常
        }
    }

}
