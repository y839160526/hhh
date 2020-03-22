package com.wyps.entity.tools;

import com.wyps.entity.Conf;
import com.wyps.entity.Field;
import com.wyps.entity.LoginConf;
import com.wyps.entity.Path;
import com.wyps.entity.PathType;
import static com.wyps.entity.tools.CommonUtils.isEmpty;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Connection.Response;

import org.jsoup.Jsoup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GrapPage {

    private boolean stopflag = false;
    private String FilePath = "F:/demo";
    private DefaultTableModel listRecords;

    public DefaultTableModel getListRecords() {
        return listRecords;
    }

    public void setListRecords(DefaultTableModel listRecords) {
        this.listRecords = listRecords;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public boolean isStopflag() {
        return stopflag;
    }

    public void setStopflag(boolean stopflag) {
        this.stopflag = stopflag;
    }

    public void parse(WebDriver webDriver, Conf conf) {

        // WebDriver driver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        // webDriver.manage().window().
        webDriver.get(conf.getListConf().getUrl());
        List<WebElement> list = findElements(webDriver, conf.getListConf()
                .getListPath());
        System.out.println(conf.getListConf()
                .getListPath().getValue());
        List<WebElement> nextUrl = null;
        String nextUrlVal = "";
        try {
            nextUrl = findElements(webDriver, conf.getListConf().getNextUrl());
            nextUrlVal = nextUrl.get(0).getAttribute("href");
        } catch (Exception e) {
            
        }
        List urlList = new ArrayList();

        for (WebElement l : list) {
            urlList.add(l.getAttribute("href"));
        }
        for (int i = 0; i < urlList.size(); i++) {
            try {
                Thread.sleep(conf.getWaitTime());
            } catch (InterruptedException ex) {
                
            }
            if (stopflag) {
                break;
            }
            try {

                //   System.out.println();
                webDriver.get(urlList.get(i).toString());
                Vector v = new Vector();
                for (Field field : conf.getFields()) {
                    if (field.isIsUse()) {
                        String fieldVal=parseField(findElements(webDriver, field.getFieldVal()).get(0), field.getFieldGet());
                        if(fieldVal.equals("click")){
                            findElements(webDriver, field.getFieldVal()).get(0).click();
                        }else{
                          v.add(fieldVal);  
                        }
                        
                    }
                }
                listRecords.addRow(v);
                listRecords.fireTableDataChanged();
            } catch (Exception e) {
                continue;
            }
        }

        if (stopflag) {
            webDriver.quit();
            return;
        }
        conf.getListConf().setPageCount(conf.getListConf().getPageCount() - 1);
        if (conf.getListConf().getPageCount() <= 0) {
            webDriver.quit();
            return;
        }

        if (!CommonUtils.isEmpty(nextUrlVal)) {
            conf.getListConf().setUrl(nextUrlVal);
            parse(webDriver, conf);
        } else {
            webDriver.quit();
        }

    }

    public String parseField(WebElement webElement, Path path) {
        String field = "";
       
        if (webElement == null) {
            return field;
        }
        switch (path.getType()) {
            case attr:
                field = webElement.getAttribute(path.getValue());
                break;
            case text:
                field = webElement.getText();
                break;
            case click:
                field = "click";
                break;
            default:
                field = webElement.getText();
                break;
        }
        System.out.println(field);
        return field;
    }

    public List<WebElement> findElements(WebDriver webDriver, Path path) {
        List<WebElement> elements = null;
        switch (path.getType()) {
            case className:
                elements = webDriver.findElements(By.className(path.getValue()));
                break;
            case cssSelector:
                elements = webDriver.findElements(By.cssSelector(path.getValue()));
                break;
            case id:
                elements = webDriver.findElements(By.id(path.getValue()));
                break;
            case linkText:
                elements = webDriver.findElements(By.linkText(path.getValue()));
                break;
            case name:
                elements = webDriver.findElements(By.name(path.getValue()));
                break;
            case partialLinkText:
                elements = webDriver.findElements(By.partialLinkText(path
                        .getValue()));
                break;
            case tagName:
                elements = webDriver.findElements(By.tagName(path.getValue()));
                break;
            case xpath:
                elements = webDriver.findElements(By.xpath(path.getValue()));
                break;
            default:
                break;
        }
        return elements;
    }

    public List<String> findLoginSign(String loginUrl) {
        List<String> list = new ArrayList<String>();
        if (loginUrl.contains("login")) {
            list.add("login");
        }
        if (loginUrl.contains("passport")) {
            list.add("passport");
        }
        if (loginUrl.contains("signin")) {
            list.add("signin");
        }
        if (loginUrl.contains("Login")) {
            list.add("Login");
        }
        return list;
    }

    public String validateConf(Conf conf) {
        String msg = "";
        LoginConf loginConf = conf.getLoginconf();
        if (loginConf.isNeedLogin()) {
            if (isEmpty(loginConf.getLoginUrl())) {
                msg = "";
            }
        } else if (isEmpty(conf.getListConf().getUrl())) {

        }
        return msg;
    }

    public String downloadFile(String path, String fileName, String imgUrl)
            throws IOException {
        // Open a URL Stream
        Response resultImageResponse = Jsoup
                .connect(imgUrl)
                .header("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
                .ignoreContentType(true).execute();
        File file = new File(FilePath + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // output here
        FileOutputStream out = (new FileOutputStream(new java.io.File(
                FilePath + path + fileName)));
        out.write(resultImageResponse.bodyAsBytes());
        // resultImageResponse.body() is where the image's contents are.
        out.close();
        return path + fileName;
    }

    public void downHtmlFile(String fileName, String html) throws IOException {
        FileOutputStream out = (new FileOutputStream(new java.io.File(
                FilePath + fileName)));
        out.write(html.getBytes("UTF-8"));
        // resultImageResponse.body() is where the image's contents are.
        out.close();
    }

    public String getPath(String source) {
        System.out.println(source);
        if (!source.contains("http")) {
            source = "https://www.itjuzi.com" + source;
        }
        URL url = null;
        try {
            url = new URL(source);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String path = url.getPath();
        return path;
    }

    public String downloadSource(String path, String source) throws IOException {
        // String
        // source="https://cdn.itjuzi.com/assets/front/scripts/vendor/modernizr.js";

        int index = path.lastIndexOf("/");
        //System.out.println(index);
        if (index == -1) {
            return "";
        }
        String fileName = path.substring(index);
        System.out.println(fileName);
        if (!path.contains("http")) {
            return "";
        }
        return downloadFile(path.replace(fileName, ""), fileName, source);
    }

    public void text() {
        System.setProperty("webdriver.chrome.driver", "f:\\\\chromedriver.exe");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.itjuzi.com/company");
        Path path = new Path();
        path.setType(PathType.xpath);
        path.setValue("/html/body/div[2]/div[1]/div[2]/div[3]/div/div[1]/ul[2]/li/i[2]/p[1]/a");
        List<WebElement> list = findElements(webDriver, path);
        for (WebElement l : list) {
            if (stopflag) {
                break;
            }
            System.out.println(l.getAttribute("href"));
        }
    }

    public static void main(String[] args) throws IOException {
        GrapPage t = new GrapPage();
        t.text();
//        Document doc = Jsoup
//                .connect("http://www.jd.com/?juc=emp1&cu=true&utm_source=www.hao123.com&utm_medium=tuiguang&utm_campaign=t_75_&utm_term=24ae83ebe5214dec856417ccd0c85069")
//                .timeout(3000)
//                .header("User-Agent",
//                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")
//                .get();
//        Elements links = doc.select("img");
//        String html = doc.html();
//        for (Element link : links) {
//            if (link.hasAttr("src")) {
//                String path = t.getPath(link.attr("src"));
//                String filePath = t.downloadSource(path, link.attr("src"));
//                html = html.replaceAll(path, filePath);
//            }
//
//        }
//        links = doc.select("script");
//        for (Element link : links) {
//            if (link.hasAttr("src")) {
//                String path = t.getPath(link.attr("src"));
//                String filePath = t.downloadSource(path, link.attr("src"));
//                html = html.replaceAll(path, filePath);
//            }
//
//        }
//        links = doc.select("link");
//        for (Element link : links) {
//            if (link.hasAttr("href")) {
//                String path = t.getPath(link.attr("href"));
//                String filePath = t.downloadSource(path, link.attr("href"));
//                html = html.replaceAll(path, filePath);
//            }
//
//        }
//        t.downHtmlFile("index.html", html);
//        System.out.println(html);

    }
}
