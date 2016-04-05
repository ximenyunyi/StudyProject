package com.yyty.hao.studyproject.bean;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description  刻录盘实体类
 * @e-mail yyty208@gmail.com
 */

public class RecordingDiskBean {

    private int startColor;

    private int centerColor;

    private int endColor;

    private int totalRadian;

    private String titles;

    private String content;

    private boolean isNeedTitle;

    private boolean isNeedRecordingDisk;

    private int maxCount;

    private int circularBg;

    private String  hintText;

    private int hintTextBg;

    private int titleBg;

    private int contentBg;

    private int actualCount;

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getCenterColor() {
        return centerColor;
    }

    public void setCenterColor(int centerColor) {
        this.centerColor = centerColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public int getTotalRadian() {
        return totalRadian;
    }

    public void setTotalRadian(int totalRadian) {
        this.totalRadian = totalRadian;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isNeedRecordingDisk() {
        return isNeedRecordingDisk;
    }

    public void setIsNeedRecordingDisk(boolean isNeedRecordingDisk) {
        this.isNeedRecordingDisk = isNeedRecordingDisk;
    }

    public boolean isNeedTitle() {
        return isNeedTitle;
    }

    public void setIsNeedTitle(boolean isNeedTitle) {
        this.isNeedTitle = isNeedTitle;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getCircularBg() {
        return circularBg;
    }

    public void setCircularBg(int circularBg) {
        this.circularBg = circularBg;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public int getHintTextBg() {
        return hintTextBg;
    }

    public void setHintTextBg(int hintTextBg) {
        this.hintTextBg = hintTextBg;
    }

    public int getTitleBg() {
        return titleBg;
    }

    public void setTitleBg(int titleBg) {
        this.titleBg = titleBg;
    }

    public int getContentBg() {
        return contentBg;
    }

    public void setContentBg(int contentBg) {
        this.contentBg = contentBg;
    }

    public int getActualCount() {
        return actualCount;
    }

    public void setActualCount(int actualCount) {
        this.actualCount = actualCount;
    }
}
