<template>
        <div class="start-auth" v-if="!profile">
        <div class="start-auth-logo">
        <img src="http://oblgaznnov.ru/images/logo.png"/>
        </div>
        <div class="start-auth-link">
        <a class ="auth-link" href="/login">Авторизация</a>
        </div>
        </div>
            <div v-else>
             <div class="main">
              <div class="header">
                                <div class="title"></div>
                                <div class="logo"><img src="http://oblgaznnov.ru/images/logo.png"/></div>
                                <div class="auth">{{profile.name}}&nbsp;<a href="/logout">Выйти</a>
                                </div>
                                </div>
               <div class="content"><bypass-list :bypasses="bypasses"/></div>
               <div class="sidebar">
               <div class="item-1">Заявки</div>
               <!--<div class="item-2">Item 2</div>
               <div class="item-3">Item 3</div>-->
               </div>
              <div class="options">
              </div>
               </div>
               </div>
</template>

<script>
        import BypassList from 'components/bypasses/BypassList.vue'

        export default {
            components: {
                    BypassList
            },
            data()  {
                return  {
                        bypasses: frontendData.bypasses,
                        profile: frontendData.profile
                }
            }
        }
</script>

<style>

/*CТАРТОВАЯ АВТОРИЗАЦИЯ*/
.start-auth {
display:flex;
flex-direction:row;
align-items:center;
}

.start-auth-logo {
display:block;
align-self: flex-start;
    display: block;
    text-decoration: none;
    height:109px;
    width:196px;
margin:30px 30px;
}

.start-auth-title {
display:block;
text-align:center;
}

.start-auth-link {
display:block;
}

.auth-link {
display:block;
border:solid 1px black;
padding:10px;
text-decoration:none;
font-family: Helios, sans-serif;
font-size:25px;
}

.auth-link:hover {
    background: yellow;
   }

.auth-link:visited {
color:black;
}

/* Grid-layout CSS */
.main {
height:100vh;
 display: grid;
 grid-template-areas: "header header"
 "sidebar options"
 "sidebar content";
 grid-template-columns: 100px 1fr;
 grid-template-rows: 120px 50px 1fr;
}

/* Блок контента*/
.content {
grid-area: content;
background-image: url(https://russiancouncil.ru/upload/blog/images/image/background_site_news.jpg);
background-size: cover;
background-repeat:no-repeat;
background-position:center;
background-attachment:fixed;
overflow:auto;
}

/*Опции*/
.options {
grid-area:options;
background-color: aliceblue;
text-align:center;
}

/*Шапка веб-приложения,заголовок,логотип,авторизация*/
.header {
display:grid;
grid-template-areas: "logo title auth";
grid-template-columns: 250px auto 200px;
grid-template-rows:120px;
grid-area: header;
background-color:white;
position:fixed;
width:100%;
}

.title {
text-align:center;
grid-area:title;
margin:auto;
font-family: helios, sans-serif;
font-style:italic;
color: #4169E1;
}

.logo {
align-self: flex-start;
    display: block;
    text-decoration: none;
    height:109px;
    width:196px;
grid-area:logo;
margin:5px;
}

.auth {
background-color:transparent;
grid-area:auth;
margin:auto;
font-family: Helios,sans-serif;
}

/*SIDEBAR*/

.sidebar {
display:flex;
flex-direction:column;
align-items:center;
grid-area:sidebar;
background-color:whitesmoke;
justify-content:end;
flex-wrap:wrap;
}

/*Навигационные ссылки в sidebar*/
.item-1 {
display:block;
padding:15px 10px;
margin-bottom: 5px;
margin-top:50px;
border-bottom:1px solid grey;
font-family: Helios, sans-serif;
}
.item-1:hover {
background-color:yellow;
}

.item-2 {
display:block;
padding:15px 10px;
margin-bottom: 5px;
border-bottom:1px solid grey;
font-family: Helios, sans-serif;
}
.item-2:hover {
background-color:yellow;
}

.item-3 {
display:block;
padding:15px 10px;
margin-bottom: 5px;
border-bottom:1px solid grey;
font-family: Helios, sans-serif;
}
.item-3:hover {
background-color:yellow;
}

</style>