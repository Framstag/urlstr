package com.framstag.urlstr.adapter;

import com.framstag.urlstr.domain.Article;
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
  public List<Article> getArticles() {
    log.info("Get articles...");

    return repository.getArticles();
  }

  @GET
  @Path("/search")
  public List<Article> searchArticles(@QueryParam("tags") List<String> searchTags,
                                      @QueryParam("search") String search) {
    log.info("Search articles by tags {} and search '{}'...", searchTags, search);

    return repository.searchArticles(searchTags, search, 15);
  }

  @POST
  public Response addArticle(Article article) {
    log.info("Adding article...");

    article = repository.addArticle(article);

    URI uri = uriInfo.getAbsolutePathBuilder().path(article.getId().asString()).build();

    return Response.created(uri).entity(article).build();
  }
}
