package com.framstag.urlstr.repository;

import com.framstag.urlstr.domain.Article;
import com.framstag.urlstr.domain.ArticleId;
import com.framstag.urlstr.domain.ArticleData;
import com.framstag.urlstr.domain.Tag;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

import static org.neo4j.driver.Values.parameters;

@ApplicationScoped
@Transactional
public class ArticleRepository {
  private static final Logger log = LoggerFactory.getLogger(ArticleRepository.class);

  @Inject
  Driver driver;

  public Article addArticle(Article article) {
    log.info("Adding article...");

    UUID uuid = UUID.randomUUID();

    ArticleId id = ArticleId.withValue(uuid.toString());

    article.setId(id);

    try (Session session = driver.session()) {
      session.run("CREATE (a:Article {id: $id, name: $name, url: $url, createdAt: datetime()})",
        parameters("id", id.asString(),
          "name", article.getName(),
          "url", article.getUrl()));
    }

    log.info("Added article with id {}", uuid);

    return article;
  }

  public Article createOrMergeArticle(ArticleData articleData, Map<String, Tag> tagMap) {
    log.info("Create/Merge article '{}'...", articleData.getName());

    String id = UUID.randomUUID().toString();

    try (Session session = driver.session()) {
      Result result = session.run("MERGE (a:Article {url: $url}) ON CREATE SET a.id = $id, a.name=$name, a.createdAt=localdatetime() RETURN a",
        parameters("id", id,
          "name", articleData.getName(),
          "url", articleData.getUrl()));

      Record record = result.single();

      Node node = record.get("a").asNode();
      Article article = Article.fromNode(node);

      for (String tagName : articleData.getTags()) {
        Tag tag = tagMap.get(tagName);

        session.run("MATCH (a: Article), (t: Tag) WHERE a.id=$articleId AND t.id=$tagId CREATE (a)-[r:TAG]->(t)",
          parameters("articleId", article.getId().asString(), "tagId", tag.getId().asString()));

        article = article.withTag(tag);
      }

      log.info("Added article with id {}", article.getId().asString());

      return article;
    }
  }

  public List<Article> getArticles() {
    log.info("Get articles...");

    List<Article> result = new LinkedList<>();

    try (Session session = driver.session()) {
      Result queryResult = session.run("MATCH (a:Article) RETURN a");

      while (queryResult.hasNext()) {
        Record record = queryResult.next();
        Node node = record.get("a").asNode();
        Article article = Article.fromNode(node);

        log.info("Article: {}", article);

        result.add(article);
      }
    }

    log.info("Found {} articles", result.size());

    return result;
  }

  public List<Article> searchArticles(List<String> searchTags, String search, long limit) {
    log.info("Search for articles...");

    List<Article> result = new LinkedList<>();

    try (Session session = driver.session()) {
      Result queryResult;

      // https://stackoverflow.com/questions/21092163/neo4j-cypher-how-to-find-all-nodes-that-have-a-relationship-to-list-of-nodes
      
      if (searchTags != null && !searchTags.isEmpty() && search != null && !search.isEmpty()) {
        queryResult = session.run("MATCH (a:Article)-[r:TAG]->(t:Tag) WHERE a.name CONTAINS $search OR t.name CONTAINS $search WITH a, collect(t.name) as tagNames WHERE ALL(n IN $searchTags WHERE n IN tagNames) RETURN DISTINCT(a) ORDER BY a.name ASC LIMIT $limit",
          parameters("searchTags", searchTags,
            "search",search,
            "limit", limit));
      }
      else if (searchTags != null && !searchTags.isEmpty()) {
        queryResult = session.run("MATCH (a:Article)-[r:TAG]->(t:Tag) WITH a, collect(t.name) as tagNames WHERE ALL(n IN $searchTags WHERE n IN tagNames) RETURN a ORDER BY a.name ASC LIMIT $limit",
          parameters("searchTags", searchTags,
            "limit", limit));
      }
      else if (search != null && !search.isEmpty()) {
        queryResult = session.run("MATCH (a:Article)-[r:TAG]->(t:Tag) WHERE a.name CONTAINS $search OR t.name CONTAINS $search RETURN DISTINCT(a) ORDER BY a.name ASC LIMIT $limit",
          parameters("search", search,
            "limit", limit));
      }
      else {
        queryResult = session.run("MATCH (a:Article) RETURN a ORDER BY a.name ASC LIMIT $limit",
          parameters("limit", limit));
      }

      while (queryResult.hasNext()) {
        Record record = queryResult.next();
        Node node = record.get("a").asNode();
        Article article = Article.fromNode(node);

        log.info("Article: {}", article);

        result.add(article);
      }
    }

    log.info("Found {} articles", result.size());

    return result;
  }
}
