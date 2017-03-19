package com.kennard.nytimesearch.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Article implements Parcelable
{

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("abstract")
    @Expose
    private Object _abstract;
    @SerializedName("print_page")
    @Expose
    private Integer printPage;
    @SerializedName("blog")
    @Expose
    private List<Object> blog = null;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = null;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private Object subsectionName;
    @SerializedName("byline")
    @Expose
    private Byline byline;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private Integer wordCount;
    @SerializedName("slideshow_credits")
    @Expose
    private Object slideshowCredits;
    public final static Parcelable.Creator<Article> CREATOR = new Creator<Article>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Article createFromParcel(Parcel in) {
            Article instance = new Article();
            instance.webUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.snippet = ((String) in.readValue((String.class.getClassLoader())));
            instance.leadParagraph = ((String) in.readValue((String.class.getClassLoader())));
            instance._abstract = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.printPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.blog, (java.lang.Object.class.getClassLoader()));
            instance.source = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.multimedia, (com.kennard.nytimesearch.model.Multimedium.class.getClassLoader()));
            instance.headline = ((Headline) in.readValue((Headline.class.getClassLoader())));
            in.readList(instance.keywords, (com.kennard.nytimesearch.model.Keyword.class.getClassLoader()));
            instance.pubDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.documentType = ((String) in.readValue((String.class.getClassLoader())));
            instance.newsDesk = ((String) in.readValue((String.class.getClassLoader())));
            instance.sectionName = ((String) in.readValue((String.class.getClassLoader())));
            instance.subsectionName = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.byline = ((Byline) in.readValue((Byline.class.getClassLoader())));
            instance.typeOfMaterial = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.wordCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.slideshowCredits = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public Article[] newArray(int size) {
            return (new Article[size]);
        }

    }
            ;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public Object getAbstract() {
        return _abstract;
    }

    public void setAbstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public Integer getPrintPage() {
        return printPage;
    }

    public void setPrintPage(Integer printPage) {
        this.printPage = printPage;
    }

    public List<Object> getBlog() {
        return blog;
    }

    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Object getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(Object subsectionName) {
        this.subsectionName = subsectionName;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Object getSlideshowCredits() {
        return slideshowCredits;
    }

    public void setSlideshowCredits(Object slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(webUrl);
        dest.writeValue(snippet);
        dest.writeValue(leadParagraph);
        dest.writeValue(_abstract);
        dest.writeValue(printPage);
        dest.writeList(blog);
        dest.writeValue(source);
        dest.writeList(multimedia);
        dest.writeValue(headline);
        dest.writeList(keywords);
        dest.writeValue(pubDate);
        dest.writeValue(documentType);
        dest.writeValue(newsDesk);
        dest.writeValue(sectionName);
        dest.writeValue(subsectionName);
        dest.writeValue(byline);
        dest.writeValue(typeOfMaterial);
        dest.writeValue(id);
        dest.writeValue(wordCount);
        dest.writeValue(slideshowCredits);
    }

    public int describeContents() {
        return 0;
    }

}
