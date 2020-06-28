package com.framstag.urlstr.domain;

import java.util.Objects;

public class TagId {
  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String asString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TagId articleId = (TagId) o;
    return value.equals(articleId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "TagId{" +
      "value='" + value + '\'' +
      '}';
  }

  public static TagId withValue(String value) {
    TagId id = new TagId();
    id.setValue(value);

    return id;
  }

}
