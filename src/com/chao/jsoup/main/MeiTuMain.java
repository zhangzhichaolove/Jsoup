package com.chao.jsoup.main;

import com.chao.jsoup.model.MeiTuModel;
import com.chao.jsoup.result.MeiTuResult;
import com.chao.jsoup.util.HibernateUtils;
import com.chao.jsoup.util.OKHttpUtils;
import com.google.gson.Gson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Chao on 2017-11-25.
 */
public class MeiTuMain {
    private static Gson gson = new Gson();
    private static int page = 1;

    public static void main(String[] args) throws Exception {
        HibernateUtils.openSession();
        requestData();
    }

    private static void requestData() throws Exception {
        List<MeiTuModel> meiTu = getMeiTu(page);
        if (meiTu == null) {
            page = 1;
            System.err.println("数据异常，停止。");
        } else {
            page = page + 1;
            Session session = HibernateUtils.openSession();
            for (int i = 0; i < meiTu.size(); i++) {
                Criteria criteria = session.createCriteria(MeiTuModel.class);
                criteria.add(Restrictions.eq("url", meiTu.get(i).getUrl()));
                MeiTuModel news = (MeiTuModel) criteria.uniqueResult();
                if (news == null) {//没有重复
                    Transaction transaction = session.beginTransaction();
                    session.save(meiTu.get(i));
                    transaction.commit();
                } else {
                    //identical = identical + 1;
                }
            }
            session.close();
            requestData();
        }
    }

    public static List<MeiTuModel> getMeiTu(int page) throws Exception {
        String data = OKHttpUtils.get("http://gank.io/api/data/福利/10/" + page);
        MeiTuResult meiTuResult = gson.fromJson(data, MeiTuResult.class);
        return meiTuResult.isError() ? null : meiTuResult.getResults();
    }
}
