<template>
    <v-app>
    <v-app-bar app dense fixed height=80 v-if="profile" >
    <img src="https://oblgaznnov.ru/images/logo.png" height=70/>
    <v-spacer></v-spacer>
   <span > {{profile.name}} </span>
    <v-btn color="primary" icon href="/logout">
    <v-icon> exit_to_app </v-icon>
    </v-btn>
    </v-app-bar>

    <v-content>
    <v-container v-if="!profile" >
    <login/>
    </v-container>
        <v-container v-if="profile">
           <router-view></router-view>
        </v-container>
    </v-content>

    </v-app>
</template>

<script>
        import Login from 'pages/Login.vue'
        import Datatable from 'pages/Datatable.vue'

        export default {

            components: {
                   Datatable,
                   Login
            },
            data()  {
                return  {
                        profile: frontendData.profile
                }
                }
                  }
</script>

<style>

@font-face {
font-family: 'Material Icons';
font-style: normal;
font-weight: 400;
src: local('Material Icons'), local('MaterialIcons-Regular'), url(https://fonts.gstatic.com/s/materialicons/v13/2fcrYFNaTjcS6g4U3t-Y5ZjZjT5FdEJ140U2DJYC3mY.woff2) format('woff2');
}

.material-icons {
font-family: 'Material Icons';
font-weight: normal;
font-style: normal;
font-size: 24px;
line-height: 1;
letter-spacing: normal;
text-transform: none;
display: inline-block;
white-space: nowrap;
word-wrap: normal;
direction: ltr;
-webkit-font-feature-settings: 'liga';
-webkit-font-smoothing: antialiased;
}

</style>