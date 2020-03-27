<template >
    <v-app>
    <v-app-bar app dense fixed height=80 v-if="profile" >
    <img src="https://oblgaznnov.ru/images/logo.png" height=70/>
    <v-spacer></v-spacer>
   <span > {{profile.name}} </span>
    <v-btn color="primary" icon href="/logout">
    <v-icon> {{ exitToAppIcon }} </v-icon>
    </v-btn>
    </v-app-bar>

    <v-content>
    <v-container v-if="!profile" >
    <Login/>
    </v-container>
        <v-container v-if="profile">
           <router-view></router-view>
        </v-container>
    </v-content>
    </v-app>
</template>

<script>
        import Login from 'pages/Login.vue'
        import DataTable from 'pages/DataTable.vue'
        import { mapState } from 'vuex'
        import { mdiExitToApp } from '@mdi/js'

        export default {
            components: {
                   DataTable,
                   Login
            },

            data () {
            return {
            exitToAppIcon: mdiExitToApp
               }
               },
             computed: mapState(['profile'])
               }
</script>

<style>

</style>