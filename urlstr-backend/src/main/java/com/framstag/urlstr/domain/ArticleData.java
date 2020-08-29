package com.framstag.urlstr.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleData {
  private String id;
  private String name;
  private String url;
  private List<String> tags;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public static ArticleData fromArticle(Article article) {
    ArticleData data = new ArticleData();

    data.id = article.getId().getValue();
    data.name= article.getName();
    data.url = article.getUrl();
    data.tags = article.getTags().stream().map(Tag::getName).collect(Collectors.toList());

    return data;
  }
}
