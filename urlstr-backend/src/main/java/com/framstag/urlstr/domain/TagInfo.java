package com.framstag.urlstr.domain;

import org.neo4j.driver.types.Node;

public class TagInfo {
  private TagId  id;
  private String name;
  private long useCount;

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

  public long getUseCount() {
    return useCount;
  }

  public void setUseCount(long useCount) {
    this.useCount = useCount;
  }

  @Override
  public String toString() {
    return "TagInfo{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", useCount=" + useCount +
      '}';
  }

  public static TagInfo fromNode(Node node,long useCount) {
    TagInfo info = new TagInfo();

    info.setId(TagId.withValue(node.get("id").asString()));
    info.setName(node.get("name").asString());
    info.setUseCount(useCount);

    return info;
  }

}
