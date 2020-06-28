package com.framstag.urlstr.repository;

import com.framstag.urlstr.domain.Tag;
import com.framstag.urlstr.domain.TagInfo;
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
public class TagRepository {
  private static final Logger log = LoggerFactory.getLogger(TagRepository.class);

  @Inject
  Driver driver;

  public Tag addTag(Tag tag) {
    log.info("Adding tag...");

    String id = UUID.randomUUID().toString();

    try (Session session = driver.session()) {
      Result result = session.run("CREATE (t:Tag {id: $id, name: $name}) RETURN t",
        parameters("id", id, "name", tag.getName()));
      Record record = result.single();
      Node node = record.get("t").asNode();
      Tag createdTag = Tag.fromNode(node);

      log.info("Added tag with id {}", createdTag.getId().asString());

      return createdTag;
    }
  }

  public Tag createOrMergeTag(String tagName) {
    log.info("Create/merge tag '{}'...", tagName);

    String id = UUID.randomUUID().toString();

    try (Session session = driver.session()) {
      Result result = session.run("MERGE (t:Tag {name: $name}) ON CREATE SET t.id=$id RETURN t",
        parameters("id", id, "name", tagName));

      Record record = result.single();
      Node node = record.get("t").asNode();
      Tag tag = Tag.fromNode(node);

      log.info("Created/merged tag with id {}", tag.getId().asString());

      return tag;
    }
  }

  public List<Tag> getTags() {
    log.info("Get tags...");

    List<Tag> result = new LinkedList<>();

    try (Session session = driver.session()) {
      Result queryResult = session.run("MATCH (t:Tag) RETURN t");

      while (queryResult.hasNext()) {
        Record record = queryResult.next();
        Node node = record.get("t").asNode();

        Tag tag = Tag.fromNode(node);

        log.info("Tag: {}",tag);

        result.add(tag);
      }
    }

    log.info("Found {} tags", result.size());

    return result;
  }

  public List<TagInfo> getTagsForStartPage() {
    log.info("Get tags for start page...");

    List<TagInfo> result = new LinkedList<>();

    try (Session session = driver.session()) {
      Result queryResult = session.run("match (a: Article)-[r:TAG]->(t:Tag) return t, Count(r) as useCount ORDER BY useCount DESC");

      while (queryResult.hasNext()) {
        Record record = queryResult.next();
        Node node = record.get("t").asNode();
        long useCount = record.get("useCount").asLong();

        TagInfo info = TagInfo.fromNode(node,useCount);

        log.info("Tag: {}",info);

        result.add(info);
      }
    }

    log.info("Found {} tags", result.size());

    return result;
  }
}
