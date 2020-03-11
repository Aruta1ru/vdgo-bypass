import Vue from 'vue'
import VueRouter from 'vue-router'
import Datatable from 'pages/Datatable.vue'

Vue.use(VueRouter)

const routes = [
    { path: '*', component: Datatable },
    { path: '/', component: Datatable}
]

export default new VueRouter({
 mode: 'history',
 routes
})