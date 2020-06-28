package com.framstag.urlstr.domain;

import org.neo4j.driver.types.Node;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Article {
  private ArticleId id;
  private String name;
  private String url;
  private LocalDateTime createdAt;
  private List<Tag> tags;

  public Article() {
    tags = new LinkedList<>();
  }

  public ArticleId getId() {
    return id;
  }

  public void setId(ArticleId id) {
    this.id = id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Article withTag(Tag tag) {
    tags.add(tag);

    return this;
  }

  @Override
  public String toString() {
    return "Article{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", url='" + url + '\'' +
      ", createdAt=" + createdAt +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return id.equals(article.id) &&
      name.equals(article.name) &&
      url.equals(article.url) &&
      createdAt.equals(article.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, url, createdAt);
  }

  public static Article fromNode(Node node) {
    Article article = new Article();

    article.setId(ArticleId.withValue(node.get("id").asString()));
    article.setName(node.get("name").asString());
    article.setUrl(node.get("url").asString());
    article.setCreatedAt(node.get("createdAt").asLocalDateTime());

    return article;
  }
}
