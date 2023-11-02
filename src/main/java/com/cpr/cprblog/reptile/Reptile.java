package com.cpr.cprblog.reptile;

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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class Reptile {
    private static String url = "https://unsplash.com/@abyvov/likes";

    // 使用一个单线程的Executor，以确保爬虫任务在后台单独的线程中运行，避免阻塞应用程序主线程
    private final Executor executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void startCrawling() {
        executor.execute(this::crawlAndSaveImages);
    }

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void scheduleCrawling() {
        executor.execute(this::crawlAndSaveImages);
    }

    private void crawlAndSaveImages() {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.MorZF");  // 选择类名为MorZF的div
            for (int i = 0; i < elements.size(); i++) {
                Elements imgElement = elements.get(i).select("img");
                Connection.Response response = Jsoup.connect(imgElement.attr("src"))
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                        .ignoreContentType(true)
                        .execute();
                // String name = imgElement.attr("alt");
                // 修改图片名
                String name = String.valueOf(i);
                ByteArrayInputStream stream = new ByteArrayInputStream(response.bodyAsBytes());
                // 图片保存路径
                // FileUtils.copyInputStreamToFile 方法：将图片的二进制数据保存到本地文件
                FileUtils.copyInputStreamToFile(stream, new File("src/main/resources/static/image/reptile_image/" + name + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
