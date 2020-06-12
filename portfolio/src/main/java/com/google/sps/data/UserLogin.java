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

package com.google.sps.data;

/**
 * Class to keep track of user's email, logout URL, logged in status
 */
public class UserLogin {
  private String email;
  private String url;
  private boolean login;

  public UserLogin(String email, String logoutURL, boolean login) {
    this.email = email;
    this.url = logoutURL;
    this.login = login;
  }

  public UserLogin(String loginURL, boolean login) {
    this.url = loginURL;
    this.login = login;
  }
}
