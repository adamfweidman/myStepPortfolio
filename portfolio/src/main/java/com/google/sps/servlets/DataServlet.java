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

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
  throws IOException {
    Query query = new Query("input");

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(0;)
    PreparedQuery results = datastore.prepare(query);

    List<String> names = new ArrayList<String>();
    List<String> comments = new ArrayList<String>();

    for (Entity entity : results.asIterable()) {
      String name = entity.getProperty("name");
      String comment = entity.getProperty("comment");
      names.add(name);
      comment.add(comment);
    }
  

    response.setContentType("application/json;");

    //patches together the name and comment
    for (int i = 0; i < names.size(); i++) {
      Gson gson = new Gson();
      String userName = gson.toJson(names.get(i));
      String userComment = gson.toJson(comments.get(i));
      String output = userName + ":"  + "\n" + userComment;
      response.getWriter().println(output);
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  throws IOException {
    String name = request.getParameter("user-name");
    String comment = request.getParameter("user-comment");

    //create entity for new input and put it in the datastore
    Entity nameComEntity = new Entity("input");
    nameComEntity.setProperty("name", name);
    nameComEntity.setProperty("comment", comment);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(nameComEntity);

    response.sendRedirect("/index.html");
  }

}
