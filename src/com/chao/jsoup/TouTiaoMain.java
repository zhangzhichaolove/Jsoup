package com.chao.jsoup;

import com.chao.jsoup.model.BuDeJieContent;
import com.chao.jsoup.model.Real_time_newsEntity;
import com.chao.jsoup.model.TouTiaoModel;
import com.chao.jsoup.util.HibernateUtils;
import com.google.gson.Gson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Chao on 2017-11-21.
 */
public class TouTiaoMain {
    public static void main(String[] args) {
        HibernateUtils.openSession();
//        String htmlStr = HttpTool.doGet("https://www.toutiao.com/news_finance/");
//        // 将获取的网页 HTML 源代码转化为 Document
//        Document doc = Jsoup.parse(htmlStr);
//        Elements elementsByClass = doc.getElementsByClass("bui-box single-mode");
//        Elements title = elementsByClass.get(0).getElementsByClass("title-box");
//        Elements link = elementsByClass.get(0).getElementsByClass("link");

        // 需要爬的网页的文章列表
        String url = "https://www.toutiao.com/news_finance/";
        //文章详情页的前缀(由于今日头条的文章都是在group这个目录下,所以定义了前缀,而且通过请求获取到的html页面)
        String url2 = "http://www.toutiao.com/group/";
        //链接到该网站
        Connection connection = Jsoup.connect(url);
        Document content = null;
        try {
            //获取内容
            content = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //转换成字符串
        String htmlStr = content.html();
        //Element doc = content.body();
        System.err.println(htmlStr);
        int data = htmlStr.indexOf("_data");
        String contentText = htmlStr.substring(data + 8);
        int end = contentText.indexOf("};");
        String json = contentText.substring(0, end + 1);
        //System.err.println(json);
        TouTiaoModel tiaoModel = new Gson().fromJson(json, TouTiaoModel.class);
        if (tiaoModel != null) {
            for (int i = 0; i < tiaoModel.getReal_time_news().size(); i++) {
                Real_time_newsEntity entity = tiaoModel.getReal_time_news().get(i);
                System.err.println(entity.getGroup_id());
                System.err.println(entity.getTitle());
                System.err.println(entity.getImage_url());
                Session session = HibernateUtils.openSession();
                Criteria criteria = session.createCriteria(Real_time_newsEntity.class);
                criteria.add(Restrictions.eq("title", entity.getTitle()));
                Real_time_newsEntity news = (Real_time_newsEntity) criteria.uniqueResult();
                if (news == null) {//没有重复
                    Transaction transaction = session.beginTransaction();
                    session.save(entity);
                    transaction.commit();
                } else {
                    //identical = identical + 1;
                }
                session.close();
            }
        }
        main(null);
    }

}
