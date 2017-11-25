package com.chao.jsoup.result;


import com.chao.jsoup.model.MeiTuModel;

import java.util.List;

/**
 * http://gank.io/api/data/福利/10/1
 * Created by Chao on 2017-11-25.
 */
public class MeiTuResult {


    /**
     * error : false
     * results : [{"createdAt":"2017-11-23T08:32:29.546Z","publishedAt":"2017-11-24T11:08:03.624Z","_id":"5a16171d421aa90fef203553","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg","desc":"11-23","who":"daimajia"},{"createdAt":"2017-11-20T07:49:41.797Z","publishedAt":"2017-11-20T12:42:06.454Z","_id":"5a121895421aa90fe50c021e","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171120074925_ZXDh6l_joanne_722_20_11_2017_7_49_16_336.jpeg","desc":"2017-11-20","who":"daimajia"},{"createdAt":"2017-11-17T10:31:41.155Z","publishedAt":"2017-11-17T12:39:48.189Z","_id":"5a0e4a0d421aa90fe7253643","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-11-17-22794158_128707347832045_9158114204975104000_n.jpg","desc":"11-17","who":"代码家"},{"createdAt":"2017-11-16T11:57:11.4Z","publishedAt":"2017-11-16T12:01:05.619Z","_id":"5a0d0c97421aa90fe2f02c60","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171116115656_vnsrab_Screenshot.jpeg","desc":"11-16","who":"代码家"},{"createdAt":"2017-11-14T10:13:21.137Z","publishedAt":"2017-11-14T10:43:36.180Z","_id":"5a0a5141421aa90fef203525","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171114101305_NIAzCK_rakukoo_14_11_2017_10_12_58_703.jpeg","desc":"11-14","who":"daimajia"},{"createdAt":"2017-11-13T08:42:35.306Z","publishedAt":"2017-11-13T12:10:58.643Z","_id":"5a08ea7b421aa90fe7253628","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg","desc":"11-13","who":"daimajia"},{"createdAt":"2017-11-09T09:53:06.802Z","publishedAt":"2017-11-10T08:10:02.838Z","_id":"5a03b502421aa90fe7253618","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171109095254_dOw5qh_bluenamchu_9_11_2017_9_52_47_256.jpeg","desc":"11-9","who":"daimajia"},{"createdAt":"2017-11-07T10:02:58.73Z","publishedAt":"2017-11-08T11:00:50.559Z","_id":"5a011452421aa90fe7253606","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171107100244_0fbENB_yyannwong_7_11_2017_10_2_5_982.jpeg","desc":"11-7","who":"daimajia"},{"createdAt":"2017-11-02T09:23:05.497Z","publishedAt":"2017-11-06T12:40:39.976Z","_id":"59fa7379421aa90fe50c01cc","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171102092251_AY0l4b_alrisaa_2_11_2017_9_22_44_335.jpeg","desc":"11-2","who":"daimajia"},{"createdAt":"2017-11-01T14:18:52.937Z","publishedAt":"2017-11-01T14:20:59.209Z","_id":"59f9674c421aa90fe50c01c6","source":"chrome","used":true,"type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171101141835_yQYTXc_enakorin_1_11_2017_14_16_45_351.jpeg","desc":"11-1","who":"daimajia"}]
     */
    private boolean error;
    private List<MeiTuModel> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<MeiTuModel> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public List<MeiTuModel> getResults() {
        return results;
    }

}
