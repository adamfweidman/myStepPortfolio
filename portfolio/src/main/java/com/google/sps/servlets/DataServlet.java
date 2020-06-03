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

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  //ArrayList<String> inputs = new ArrayList<String>();
  ArrayList<String> names = new ArrayList<String>();
  ArrayList<String> comments = new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
  throws IOException {
    response.setContentType("application/json;");

    /**Gson gson = new Gson();
    String output = gson.toJson(inputs);
    response.getWriter().println(output);**/
  
    //patches together the name and comment
    for (int i = 0; i < names.size(); i++) {
      Gson gson = new Gson();
      String userName = gson.toJson(names.get(i));
      String userComment = gson.toJson(comments.get(i));
      String output = userName + ":"  + "\n" + userComment;
      response.getWriter().println(output);
    }
      
      //response.sendRedirect("/index.html");**/
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  throws IOException {
    String name = request.getParameter("user-name");
    String comment = request.getParameter("user-comment");

    names.add(name);
    comments.add(comment);

    response.sendRedirect("/index.html");
  }

}
