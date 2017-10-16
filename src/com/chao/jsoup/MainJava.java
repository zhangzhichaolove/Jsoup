package com.chao.jsoup;

import com.chao.jsoup.model.BuDeJieContent;
import com.chao.jsoup.model.BuDeJieModel;
import com.chao.jsoup.model.SatinModel;
import com.chao.jsoup.util.ExecutorServiceUtils;
import com.chao.jsoup.util.HibernateUtils;
import com.google.gson.Gson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Chao on 2017/10/11.
 */
public class MainJava {
    private static Gson gson = new Gson();
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static boolean startRun = false;

    public static void main(String[] args) {
        HibernateUtils.openSession();
        //saveSatin(1);
        //saveBaiSiBuDeJieApi(1, null);
        start();
    }

    public static void start() {
        if (startRun) {
            return;
        }
        startRun = true;
        HibernateUtils.openSession();
        ExecutorServiceUtils.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    saveSatin(1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void stop() {
        startRun = false;
    }

    public static void saveSatin(int page) throws ParseException {
        try {
            //String htmlStr = HttpTool.doGet("http://www.budejie.com/text/" + page);
            String htmlStr = HttpTool.doGet("http://www.budejie.com/" + page);
            // 将获取的网页 HTML 源代码转化为 Document
            Document doc = Jsoup.parse(htmlStr);
            Elements root = doc.getElementsByClass("j-r-list");
            int identical = 0;
            for (int i = 0; i < root.size(); i++) {
                Element list = root.get(i);
                Elements users = root.get(i).getElementsByClass("j-list-user");
                if (users != null && users.size() > 0) {
                    for (int j = 0; j < users.size(); j++) {
                        //list.getElementsByClass("j-r-list-c").get(j);
                        String u_img = list.getElementsByClass("u-logo lazy").get(j).attr("data-original");
                        String u_name = list.getElementsByClass("u-logo lazy").get(j).attr("alt");
                        String time = doc.getElementsByClass("u-time  f-ib f-fr").get(j).text();
                        Elements video = list.getElementsByClass("j-r-list-c").get(j).getElementsByClass(" j-video");
                        Elements img = list.getElementsByClass("j-r-list-c").get(j).getElementsByClass(/*"j-r-list-c-img"*/"lazy");
                        Elements text = list.getElementsByClass("j-r-list-c").get(j).getElementsByClass("j-r-list-c-desc");
                        BuDeJieContent model = new BuDeJieContent();
                        model.setProfile_image(u_img);
                        model.setName(u_name);
                        model.setText(text.get(0).text());
                        model.setCreated_at(format.parse(time));
                        if (video != null && video.size() > 0) {
                            String text_content = video.get(0).text();
                            String video_img = video.get(0).attr("data-poster");
                            String video_url = video.get(0).attr("data-mp4");
                            model.setCdn_img(video_img);
                            model.setVoiceuri(video_url);
                            model.setType("41");
                            //model.setText(text_content);
                        } else if (img != null && img.size() > 0) {
                            //String text_title = img.get(0).attr("title");
                            String text_content = img.get(0).text();
                            String text_img = img.get(0).attr("data-poster");
                            model.setCdn_img(text_img);
                            //model.setText(text_content);
                            model.setType("10");
                        } else if (text != null && text.size() > 0) {
                            String text_content = text.get(0).text();
                            //model.setText(text_content);
                            model.setType("29");
                        }
                        Session session = HibernateUtils.openSession();
                        Criteria criteria = session.createCriteria(BuDeJieContent.class);
                        criteria.add(Restrictions.eq("text", model.getText()));
                        BuDeJieContent content = (BuDeJieContent) criteria.uniqueResult();
                        if (content == null) {//没有重复
                            Transaction transaction = session.beginTransaction();
                            session.save(model);
                            transaction.commit();
                        } else {
                            //identical = identical + 1;
                        }
                        session.close();
                        System.out.println(model);
                    }
                }
            }
            if (identical >= 20) {
                System.out.println("重复数据超过限制，爬取其他接口。");
                saveBaiSiBuDeJieApi(1, null);
            }
            page = page + 1;
            if (startRun) {
                saveSatin(page);
            } else {
                System.out.println("服务停止!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求失败了");
            saveBaiSiBuDeJieApi(1, null);
        }
    }


    public static void saveBaiSiBuDeJieApi(int page, String maxtime) throws ParseException {
        System.out.println("当前加载页码：" + page);
        String json = HttpTool.doGet("http://api.budejie.com/api/api_open.php?a=list&c=data&type=1&page=" + page + "&maxtime=" + maxtime);
        BuDeJieModel model = gson.fromJson(json, BuDeJieModel.class);
        if (model != null) {
            maxtime = model.getInfo().getMaxtime();
            int identical = 0;
            for (int i = 0; i < model.getList().size(); i++) {
                BuDeJieContent content = model.getList().get(i);
                System.out.println(content.getText());
                Session session = HibernateUtils.openSession();
                Criteria criteria = session.createCriteria(BuDeJieContent.class);
                criteria.add(Restrictions.eq("text", content.getText()));
                BuDeJieContent buDeJieContent = (BuDeJieContent) criteria.uniqueResult();
                if (buDeJieContent == null) {//没有重复
                    Transaction transaction = session.beginTransaction();
                    //content.setId(null);
                    session.save(content);
                    transaction.commit();
                } else {
                    identical = identical + 1;
                }
                session.close();
            }
            page = page + 1;
            if (identical >= model.getList().size()) {
                System.out.println("重复数据超过限制，爬取其他接口。");
                saveSatin(1);
            }
            if (startRun) {
                saveBaiSiBuDeJieApi(page, maxtime);
            } else {
                System.out.println("服务停止!");
            }
        } else {
            System.out.println("保存完毕!");
            saveSatin(1);
        }

    }

}
