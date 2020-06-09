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
 * Retrives the text on the .../data URL and adds it to the DOM
 * Edit so that it shows in stacked form like on /data page
 */
function getData() {
  fetch('/data').then(response => response.json()).then((comments) => {
    const printCom = document.getElementById('data-container');
    printCom.innerHTML = '';
    comments.forEach((comment) => {
     printCom.appendChild(
        createCommentFormat("Name: " + comment.getUsername()));
      printCom.appendChild(
        createCommentFormat("Comment: " + comment.getComment()));
    })
  });
}

function createCommentFormat(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
