package com.chao.jsoup;

import com.chao.jsoup.model.SatinModel;
import com.chao.jsoup.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Chao on 2017/10/11.
 */
public class MainJava {

    public static void main(String[] args) {
        HibernateUtils.openSession();
        saveSatin(1);
    }

    public static void saveSatin(int page) {
        try {
            String htmlStr = HttpTool.doGet("http://www.budejie.com/text/" + page);
            // 将获取的网页 HTML 源代码转化为 Document
            Document doc = Jsoup.parse(htmlStr);
            //Elements list = doc.getElementsByClass("j-list-user");
            Elements elements = doc.getElementsByClass("j-r-list-c-desc");
            //doc.getElementsByClass("j-r-list-c-desc").get(0).getElementsByTag("a").get(0).getAllElements().get(0).text();
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                String text = element.getElementsByTag("a").get(0).getAllElements().get(0).text();
                Session session = HibernateUtils.openSession();
                Transaction transaction = session.beginTransaction();
                SatinModel model = new SatinModel();
                model.setId(null);
                model.setContent(text);
                session.save(model);
                transaction.commit();
                session.close();
                System.out.println(text);
            }
            Elements title = doc.getElementsByTag("title");
            System.out.println(title.text());
            saveSatin(page + 1);
        } catch (CommonException e) {
            e.printStackTrace();
            System.out.println("请求失败了");
        }
    }


}
