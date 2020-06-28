package com.framstag.urlstr.domain;

import org.neo4j.driver.types.Node;

import java.util.Objects;

public class Tag {
  private TagId  id;
  private String name;

  public TagId getId() {
    return id;
  }

  public void setId(TagId id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag = (Tag) o;
    return id.equals(tag.id) &&
      name.equals(tag.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Tag{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }

  public static Tag fromNode(Node node) {
    Tag tag = new Tag();

    tag.setId(TagId.withValue(node.get("id").asString()));
    tag.setName(node.get("name").asString());

    return tag;
  }
}
