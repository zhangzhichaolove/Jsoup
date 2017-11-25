package com.chao.jsoup.main;

import com.chao.jsoup.model.FemaleNameModel;
import com.chao.jsoup.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

/**
 * Created by Chao on 2017-11-25.
 */
public class FemaleNameMain {
    public static void main(String[] args) {
        HibernateUtils.openSession();
        //取得根目录路径
        String rootPath = FemaleNameMain.class.getResource("/FemaleNameData.cc").getFile().toString();
        //当前目录路径
        String currentPath1 = FemaleNameMain.class.getResource(".").getFile().toString();
        //当前目录的上级目录路径
        String parentPath = FemaleNameMain.class.getResource("../").getFile().toString();
        File file = new File(rootPath);
        saveNeme(file);
    }

    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String saveNeme(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
                FemaleNameModel femaleNameModel = new FemaleNameModel();
                femaleNameModel.setFemalename(s);
                session.save(femaleNameModel);
                System.err.println(s);
            }
            transaction.commit();
            session.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}
