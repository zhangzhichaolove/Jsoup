package com.chao.jsoup.model;

/**
 * Created by Chao on 2017-11-25.
 */
public class MeiTuModel {

    /**
     * createdAt : 2017-11-23T08:32:29.546Z
     * publishedAt : 2017-11-24T11:08:03.624Z
     * _id : 5a16171d421aa90fef203553
     * source : chrome
     * used : true
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg
     * desc : 11-23
     * who : daimajia
     */
    private transient Integer id;
    private String createdAt;
    private String publishedAt;
    private String _id;
    private String source;
    private boolean used;
    private String type;
    private String url;
    private String desc;
    private String who;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String get_id() {
        return _id;
    }

    public String getSource() {
        return source;
    }

    public boolean isUsed() {
        return used;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    public String getWho() {
        return who;
    }
}
