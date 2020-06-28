package com.framstag.urlstr.adapter;

import com.framstag.urlstr.domain.Tag;
import com.framstag.urlstr.domain.TagInfo;
import com.framstag.urlstr.repository.TagRepository;
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

@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {
  private static final Logger log = LoggerFactory.getLogger(TagResource.class);

  @Inject
  TagRepository repository;

  @Context
  UriInfo uriInfo;

  @GET
  public List<Tag> getTags() {
    log.info("Get tags...");

    return repository.getTags();
  }

  @GET
  @Path("/start")
  public List<TagInfo> getTagsFromStartPage() {
    log.info("Get tags for start page...");

    return repository.getTagsForStartPage();
  }

  @POST
  public Response addTag(Tag tag) {
    log.info("Adding tag...");

    tag = repository.addTag(tag);

    URI uri = uriInfo.getAbsolutePathBuilder().path(tag.getId().asString()).build();

    return Response.created(uri).entity(tag).build();
  }
}
