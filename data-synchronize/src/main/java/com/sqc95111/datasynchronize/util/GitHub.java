package com.sqc95111.datasynchronize.util;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface GitHub {
  
  @RequestLine("GET /repos/{owner}/{repo}/contributors")
  List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repository);
  
  class Contributor {
    String login;
    int contributions;
  }
}

