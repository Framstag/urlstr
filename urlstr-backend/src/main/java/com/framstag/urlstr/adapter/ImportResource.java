package com.framstag.urlstr.adapter;

import com.framstag.urlstr.domain.Article;
import com.framstag.urlstr.domain.Import;
import com.framstag.urlstr.domain.ArticleData;
import com.framstag.urlstr.domain.Tag;
import com.framstag.urlstr.repository.ArticleRepository;
import com.framstag.urlstr.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Path("/import")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImportResource {
  private static final Logger log = LoggerFactory.getLogger(ImportResource.class);

  @Inject
  TagRepository tagRepository;

  @Inject
  ArticleRepository articleRepository;

  @Context
  UriInfo uriInfo;

  @POST
  public Response addTag(Import importData) {
    Set<String> tagNames = new HashSet<>();
    Map<String, Tag> tagMap = new HashMap<>();

    log.info("Processing import...");

    for (ArticleData article : importData.getArticles()) {
      tagNames.addAll(article.getTags());
    }

    log.info("Creating/merging tags...");

    for (String tagName : tagNames) {
      log.info("Creating/merging tag '{}'...", tagName);
      Tag tag = tagRepository.createOrMergeTag(tagName);

      tagMap.put(tag.getName(), tag);
    }

    log.info("Creating articles...");

    for (ArticleData articleData : importData.getArticles()) {
      log.info("Creating/merging article '{}'...", articleData.getUrl());

      Article article = articleRepository.createOrMergeArticle(articleData,tagMap);
    }

    return Response.ok().build();
  }
}
