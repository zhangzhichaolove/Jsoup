package com.chao.jsoup.model;

import java.util.List;

/**
 * Created by Chao on 2017-11-21.
 */
public class TouTiaoModel {

    /**
     * real_time_news : [{"group_id":"6490658706215141902","image_url":"https://p1.pstatp.com/list/240x240/473a000280a63ec9d132","title":"102岁老人，72岁的智障儿子，还有他们的两副寿材"},{"group_id":"6490749646812496397","image_url":"https://p3.pstatp.com/list/240x240/46f300045e76926ce980","title":"东营新婚女孩被劫杀案今日宣判 嫌疑人被判死刑立即执行"},{"group_id":"6490500109552321038","image_url":"https://p3.pstatp.com/list/240x240/47380005b34e277dd111","title":"兰世立：从湖北首富到红色通缉犯"},{"group_id":"6490778752409141773","image_url":"https://p3.pstatp.com/list/240x240/46f700030e8ac3fef8e5","title":"冒死脱北的朝鲜士兵醒了，第一个要求竟是\u2026\u2026"},{"group_id":"6490800723297567246","image_url":"https://p1.pstatp.com/list/240x240/46f80002ef32e5e5ac67","title":"他被抓了，省领导曾批示不要上他的当"},{"group_id":"6490834352992158222","image_url":"https://p3.pstatp.com/list/240x240/473c0001cd7a2c292eba","title":"国航停止往来平壤航班代表中国对朝政策？中方回应"},{"group_id":"6490708452501881358","image_url":"https://p3.pstatp.com/list/240x240/46f80001549c10cc902a","title":"杀孕妇、娶粉丝，这个最危险的杀人魔头，死了！"}]
     */
    private List<Real_time_newsEntity> real_time_news;

    public void setReal_time_news(List<Real_time_newsEntity> real_time_news) {
        this.real_time_news = real_time_news;
    }

    public List<Real_time_newsEntity> getReal_time_news() {
        return real_time_news;
    }

}
