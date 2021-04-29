package com.framstag.urlstr.adapter;

import com.framstag.urlstr.domain.Article;
import com.framstag.urlstr.domain.ArticleData;
import com.framstag.urlstr.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {
  private static final Logger log = LoggerFactory.getLogger(ArticleResource.class);

  @Inject
  ArticleRepository repository;

  @Context
  UriInfo uriInfo;

  @GET
  @Path("/{id}")
  public ArticleData getArticle(@PathParam("id") String id) {
    log.info("Loading article with id {}...", id);

    Article article = repository.getArticle(id);

    return ArticleData.fromArticle(article);
  }

  @GET
  public List<ArticleData> getArticles() {
    log.info("Get articles...");

    List<Article> articles = repository.getArticles();

    return articles.stream().map(ArticleData::fromArticle).collect(Collectors.toList());
  }

  @GET
  @Path("/search")
  public List<ArticleData> searchArticles(@QueryParam("tags") List<String> searchTags,
                                      @QueryParam("search") String search) {
    log.info("Search articles by tags {} and search '{}'...", searchTags, search);

    List<Article> articles = repository.searchArticles(searchTags, search, 15);

    return articles.stream().map(ArticleData::fromArticle).collect(Collectors.toList());
  }

  @POST
  public Response addArticle(Article article) {
    log.info("Adding article...");

    article = repository.addArticle(article);

    var data = ArticleData.fromArticle(article);

    URI uri = uriInfo.getAbsolutePathBuilder().path(data.getId()).build();

    return Response.created(uri).entity(data).build();
  }
}
