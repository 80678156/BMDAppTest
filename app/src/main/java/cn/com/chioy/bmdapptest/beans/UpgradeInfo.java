package cn.com.chioy.bmdapptest.beans;

/**
 * Created by zhaowh on 2017/2/20.
 * public String id = "";//唯一标识
 public String title = "";//升级提示标题
 public String newFeature = "";//升级特性描述
 public long publishTime = 0;//升级发布时间,ms
 public int publishType = 0;//升级类型 0测试 1正式
 public int upgradeType = 1;//升级策略 1建议 2强制 3手工
 public int popTimes = 0;//提醒次数
 public long popInterval = 0;//提醒间隔
 public int versionCode;
 public String versionName = "";
 public String apkMd5;//包md5值
 public String apkUrl;//APK的CDN外网下载地址
 public long fileSize;//APK文件的大小
 pubilc String imageUrl; // 图片url
 */
/**
 * App更新信息实体类
 * @author zhaowh
 * @Date 2017/2/23
 */
public class UpgradeInfo {
    public static final int TYPE_FIX = 0x01;
    public static final int TYPE_UPDATE = 0x02;

    private String versionName;
    private int versionCode;
    private int type;
    private boolean forceUpdate;
    private String date;
    private String path;
    private String description;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
