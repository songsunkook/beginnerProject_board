package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackNotiAttachment {
    @JsonProperty("author_icon")
    private String authorIcon;
    @JsonProperty("author_link")
    private String authorLink;
    @JsonProperty("author_name")
    private String authorName;
    private String fallback;
    private String fields;
    private String footer;
    @JsonProperty("footer_icon")
    private String footerIcon;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("mrkdwn_in")
    private String mrkdwnIn;
    private String pretext;
    private String text;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    private String title;
    @JsonProperty("title_link")
    private String titleLink;
    private String ts;
    private String color;

    public String getAuthorIcon() {
        return authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFooterIcon() {
        return footerIcon;
    }

    public void setFooterIcon(String footerIcon) {
        this.footerIcon = footerIcon;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMrkdwnIn() {
        return mrkdwnIn;
    }

    public void setMrkdwnIn(String mrkdwnIn) {
        this.mrkdwnIn = mrkdwnIn;
    }

    public String getPretext() {
        return pretext;
    }

    public void setPretext(String pretext) {
        this.pretext = pretext;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
