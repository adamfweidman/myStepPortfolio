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

/**
 * Adds a random picture of Luna to the page.
 */
function randomLuna() {
    const lunaNames = ['luna-beach.jpg', 'luna-beach-2.jpg', 'luna-hat.JPG', 
    'luna-park.JPG', 'luna-rosie.jpg'];
    const lunaIndex = Math.floor(Math.random() * lunaNames.length);

    const lunaUrl = 'luna/' + lunaNames[lunaIndex];
    const imgLuna = document.createElement('img');
    imgLuna.src = lunaUrl;

    const lunaHolder = document.getElementById('random-luna-holder');
    //remove previous image and upload new one
    lunaHolder.innerHTML = '';
    lunaHolder.appendChild(imgLuna); 
}

/**
 * Adds a random picture of Food to the page.
 */
function randomFood() {
    const foodNames = ['eggs.jpg', 'meat.JPG', 'pesto.JPG', 'stirfry.jpg'];
    const foodIndex = Math.floor(Math.random() * foodNames.length);

    const foodUrl = 'food/' + foodNames[foodIndex];
    const imgFood = document.createElement('img');
    imgFood.src = foodUrl;

    const foodHolder = document.getElementById('random-food-holder');
    //remove previous image and upload new one
    foodHolder.innerHTML = '';
    foodHolder.appendChild(imgFood);
}

/** 
 * fetches the information from the /data URL and then displays it on the 
 * current page (index.html)
 * Edit so that it shows in stacked form like on /data page
 */
function getData() {
  fetch('/data').then(response => response.json()).then((comments) => {
    const printCom = document.getElementById('data-container');
    printCom.innerHTML = '';
    comments.forEach((comment) => {
     printCom.appendChild(
        createElementFormat(comment.username + ":    " + comment.comment, 'li'));
    });
  });
}

/**
 * create element in doc w text and type input
 */
function createElementFormat(text, elementType) {
  const element = document.createElement(elementType);
  element.innerText = text;
  return element;
}

/**
 * Display the user's login information
 */
function showLoginInfo(user) {
  const userInfo = document.getElementById('login-container');
  userInfo.innerText = '';
	
  // display email and logout if logged in, otherwise login
  if (user.email != null) {
    userInfo.append(createElementFormat(user.email, 'p'));

    const printLogout = document.createElement('a');
    printLogout.href = user.url;
    printLogout.innerText = 'Logout Here';

    userInfo.append(printLogout);
  } else {
    const printLogin = document.createElement('a');
    printLogin.href = user.url;
    printLogin.innerText = 'Login Here';

    userInfo.append(printLogin);
  }
}

/**
 * Function that finds if user is logged in, to determine showing comments
 */
function loadCommentDiv(user) {
  if (user.email != null) {
    document.getElementById('comment').style.display = 'block';
    placeEmail(user);
  } else {
    document.getElementById('comment').style.display = 'none';
  }
}

/** Put email in the "name" textbox*/
function placeEmail(user) {
  const nameBox = document.getElementById('user-name');
  nameBox.value = user.email;
}

/**
 * helper function to load all necessary information "onload"
 */
function onLoad() {
  fetch('/login').then(response => response.json()).then((user) => {
    showLoginInfo(user);
    loadCommentDiv(user);
  });
}
