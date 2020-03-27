import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import App from 'pages/App.vue'
import store from 'store/store'
import vuetify from 'plugins/vuetify'
import router from 'router/router'

new Vue({
    vuetify,
    router,
    store,
    el: '#app',
    render: a => a(App)
})