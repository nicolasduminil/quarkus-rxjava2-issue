package fr.simplex_software.workshop.client;

import io.reactivex.*;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.*;
import org.jboss.resteasy.annotations.*;

import java.util.*;

@Path("tz2")
@RegisterRestClient(configKey = "base_uri")
public interface TimeZoneResourceClient
{
  @GET
  @Stream(Stream.MODE.GENERAL)
  Flowable<TimeZone> getTimeZoneStream();
}
