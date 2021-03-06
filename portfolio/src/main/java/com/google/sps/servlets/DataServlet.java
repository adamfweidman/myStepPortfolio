// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.Comment;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns comments content. */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private static final String NAME = "name";
  private static final String COMMENT = "comment";  

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
    Query query = new Query("input");

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
    
    List<Comment> comments = new ArrayList<>();
    
    for (Entity entity : results.asIterable()) {
    	String name = (String) entity.getProperty(NAME);
      String comment = (String) entity.getProperty(COMMENT);
      Comment com = new Comment(name, comment);
      comments.add(com);
    }    
  
    response.setContentType("application/json;");

    Gson gson = new Gson();
    String json = gson.toJson(comments);
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  throws IOException {
    String name = request.getParameter("user-name");
    String comment = request.getParameter("user-comment");

    //create entity for new input and put it in the datastore
    Entity nameComEntity = new Entity("input");
    nameComEntity.setProperty(NAME, name);
    nameComEntity.setProperty(COMMENT, comment);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(nameComEntity);

    response.sendRedirect("/index.html");
  }
}
