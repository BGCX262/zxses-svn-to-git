package com.lcweb.bean.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


 
public class NewsContentManage implements java.io.Serializable {

	 
 
	private static final long serialVersionUID = 1L;
	private long newsId;

	private String announceType;
	private String newsTitle;
	private String newsContent;
	private String newsKeyword;
	private String newsWriter;
	private String newsSource;
	private String announcePerson;
	private Date newsDate;
	private String newsTemplate;
	private String htmlFileName;
	private String ifImgNews;
	private String imgNewsAddress;
	private String imgNewsTitle;
	private String ifTitleImg;
	private String ifTopRow;
	private String ifRecommend;
	private String httpUrl;
	private String annexAddress;
	private String ifVodNews;
	private Integer displayOrderId;
	private String checkPerson;						 
	private Date checkDate;							 
	private String checkFlag;						 
	private Integer visitCount;						 
	private String newsFilesPath = null;			 
	
//	private boolean isNew = false;
	
	private String isNew = null;
	
	private Set newsContentReviews = new HashSet(0);
	
	private Set newsItemSmalls = new HashSet(0); 
	
	private BasicDepartment department = null;
	
	private BasicPerson basicPerson = null;
	

	public BasicDepartment getDepartment() {
		return department;
	}

	public void setDepartment(BasicDepartment department) {
		this.department = department;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public Set getNewsItemSmalls() {
		return newsItemSmalls;
	}

	public void setNewsItemSmalls(Set newsItemSmalls) {
		this.newsItemSmalls = newsItemSmalls;
	}

	public String getAnnounceType() {
		return this.announceType;
	}

	public void setAnnounceType(String announceType) {
		this.announceType = announceType;
	}

	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsKeyword() {
		return this.newsKeyword;
	}

	public void setNewsKeyword(String newsKeyword) {
		this.newsKeyword = newsKeyword;
	}

	public String getNewsWriter() {
		return this.newsWriter;
	}

	public void setNewsWriter(String newsWriter) {
		this.newsWriter = newsWriter;
	}

	public String getNewsSource() {
		return this.newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getAnnouncePerson() {
		return this.announcePerson;
	}

	public void setAnnouncePerson(String announcePerson) {
		this.announcePerson = announcePerson;
	}

	public Date getNewsDate() {
		return this.newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public String getNewsTemplate() {
		return this.newsTemplate;
	}

	public void setNewsTemplate(String newsTemplate) {
		this.newsTemplate = newsTemplate;
	}

	public String getHtmlFileName() {
		return this.htmlFileName;
	}

	public void setHtmlFileName(String htmlFileName) {
		this.htmlFileName = htmlFileName;
	}

	public String getIfImgNews() {
		return this.ifImgNews;
	}

	public void setIfImgNews(String ifImgNews) {
		this.ifImgNews = ifImgNews;
	}

	public String getImgNewsAddress() {
		return this.imgNewsAddress;
	}

	public void setImgNewsAddress(String imgNewsAddress) {
		this.imgNewsAddress = imgNewsAddress;
	}

	public String getImgNewsTitle() {
		return this.imgNewsTitle;
	}

	public void setImgNewsTitle(String imgNewsTitle) {
		this.imgNewsTitle = imgNewsTitle;
	}

	public String getIfTitleImg() {
		return this.ifTitleImg;
	}

	public void setIfTitleImg(String ifTitleImg) {
		this.ifTitleImg = ifTitleImg;
	}

	public String getIfTopRow() {
		return this.ifTopRow;
	}

	public void setIfTopRow(String ifTopRow) {
		this.ifTopRow = ifTopRow;
	}

	public String getIfRecommend() {
		return this.ifRecommend;
	}

	public void setIfRecommend(String ifRecommend) {
		this.ifRecommend = ifRecommend;
	}

	public String getHttpUrl() {
		return this.httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getAnnexAddress() {
		return this.annexAddress;
	}

	public void setAnnexAddress(String annexAddress) {
		this.annexAddress = annexAddress;
	}

	public String getIfVodNews() {
		return this.ifVodNews;
	}

	public void setIfVodNews(String ifVodNews) {
		this.ifVodNews = ifVodNews;
	}

	public Integer getDisplayOrderId() {
		return this.displayOrderId;
	}

	public void setDisplayOrderId(Integer displayOrderId) {
		this.displayOrderId = displayOrderId;
	}

	public String getCheckPerson() {
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckFlag() {
		return this.checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Integer getVisitCount() {
		return this.visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Set getNewsContentReviews() {
		return this.newsContentReviews;
	}

	public void setNewsContentReviews(Set newsContentReviews) {
		this.newsContentReviews = newsContentReviews;
	}

	public String getNewsFilesPath() {
		return newsFilesPath;
	}

	public void setNewsFilesPath(String newsFilesPath) {
		this.newsFilesPath = newsFilesPath;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	/**   
	 * @return the basicPerson   
	 */
	public BasicPerson getBasicPerson() {
		return basicPerson;
	}

	/**   
	 * @param basicPerson the basicPerson to set   
	 */
	public void setBasicPerson(BasicPerson basicPerson) {
		this.basicPerson = basicPerson;
	}

//	public boolean isNew() {
//		return isNew;
//	}
//
//	public void setNew(boolean isNew) {
//		this.isNew = isNew;
//	}

}